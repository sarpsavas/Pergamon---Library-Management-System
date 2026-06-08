# 🏛️ Pergamon — Kütüphane Yönetim Sistemi

> Kütüphanelere dijital yönetim hizmeti sunan çok kiracılı platform.

**Canlı:** [pergamon.sarpsavas.com](https://pergamon.sarpsavas.com) · [management-pergamon.sarpsavas.com](https://management-pergamon.sarpsavas.com)

---

## 👥 Ekip

| Öğrenci No | Ad Soyad | Rol |
|---|---|---|
| 1247008025 | Sarp SAVAŞ | Backend, Frontend, DevOps, CI/CD, Güvenlik |
| 1247008047 | Merdan Meriç NURAY | Veritabanı |
| 1247008065 | Melisa BÜYÜKKOYUNCU | Test |
| 1247008033 | Kerem Can HAYVACI | Test |
| 1247008034 | Sami Bilal GÖLGECİ | Test |

---

## 📋 İçindekiler

1. [Proje Genel Bakış](#1-proje-genel-bakış)
2. [Backend Mimarisi](#2-backend-mimarisi)
3. [Veritabanı](#3-veritabanı)
4. [Güvenlik](#4-güvenlik)
5. [Frontend](#5-frontend)
6. [CI/CD Pipeline](#6-cicd-pipeline)
7. [DevOps & Altyapı](#7-devops--altyapı)
8. [Mevcut Durum & Yol Haritası](#8-mevcut-durum--yol-haritası)
9. [Test](#-test)

---

## 1. Proje Genel Bakış
> 👤 **Sarp SAVAŞ**

Pergamon, kütüphanelere dijital kütüphane yönetimi hizmeti sunmak amacıyla geliştirilmiş çok kiracılı (multi-tenant) bir platformdur. Fiziksel kütüphane şubelerini **organizasyon** soyutlaması altında birleştirerek; üye yönetimi, kitap kataloğu, ödünç takibi, feedback ve raporlama işlevlerini tek bir sistem üzerinden karşılar.

Platform iki ayrı web arayüzüyle hizmet verir:

| Site | URL | Hedef Kullanıcı |
|---|---|---|
| **Pergamon** | `pergamon.sarpsavas.com` | Üyeler, Default Admin, Master Admin |
| **Pergamon Management** | `management-pergamon.sarpsavas.com` | General Admin |

### Teknoloji Özeti

| Katman | Teknoloji |
|---|---|
| Backend | Java · Spring Boot · JDBI · Oracle DB |
| Frontend | Vanilla JS · HTML5 · CSS3 |
| Auth | JWT (JSON Web Token) |
| Veritabanı | Oracle Database |
| Container | Docker |
| CI/CD | GitHub Actions → Docker Hub → Watchtower |
| Altyapı | Ubuntu LTS VM · Cloudflare Tunnel |

---

## 2. Backend Mimarisi
> 👤 **Sarp SAVAŞ**

### 2.1 Genel Yaklaşım

Backend Java ve Spring Boot kullanılarak geliştirilmiştir. **Clean Architecture** prensipleri benimsenmiş; sorumluluklar dört katmana ayrılmıştır. Uygulama içi komut/sorgu ayrımı için **CQRS** pattern uygulanmakta, domain modeli ise **DDD** ilkeleriyle şekillendirilmektedir.

### 2.2 Katman Yapısı

```
Pergamon---Library-Management-System/
├── api/              → Controller'lar, HTTP katmanı
├── application/      → Command/Query sınıfları, Handler'lar, Response DTO'lar
├── core/             → Entity'ler, Value Object'ler, Enum'lar, iş kuralları
└── infrastructure/   → JDBI repository'ler, Oracle bağlantısı
```

| Katman | Sorumluluk |
|---|---|
| `api` | HTTP isteklerini alır, yanıt döndürür |
| `application` | İş akışını yönetir; Command/Query handler'ları barındırır |
| `core` | Domain modeli; dış bağımlılıktan tamamen izole |
| `infrastructure` | Veritabanı ve harici servis erişimi |

### 2.3 Domain Modeli

#### Entity Hiyerarşisi

```
User (base)
├── Admin      → AdminProfile: DEFAULT_ADMIN | MASTER_ADMIN | GENERAL_ADMIN
└── Visitor    → AccountProfile: VISITOR | TEACHER
```

**User** ortak alanları: `id (UUID)`, `accountId`, `name`, `lastname`, `passwordHash`, `eMail` *(Value Object)*, `organizationPerId`

#### Tüm Entity'ler

| Entity | Anahtar Alanlar |
|---|---|
| `Book` | `id (PB+7h)`, `name`, `author`, `bookType`, `availability`, `pageNumber`, `imageUrl`, `organizationPerId` |
| `Barrowed` | `barrowedId (UUID)`, `bookId`, `visitorId`, `organizationPerId`, `barrStartTime`, `barrEndTime` |
| `Transaction` | `transactionId (UUID)`, `organizationPerId`, `userId`, `type`, `transactionTime`, `succes`, `description` |
| `Feedback` | `feedbackId (UUID)`, `organizationPerId`, `feedbackText`, `feedbackTime` |
| `Organization` | `organizationId (UUID)`, `organizationPerId (L+6h)`, `organizationName`, `organizationMasterAdminId` |

#### Value Objects

`EMail` — e-posta doğrulama mantığını domain katmanında kapsüller; ilkel `String` yerine kullanılır.

### 2.4 Periyodik ID Sistemi

UUID'ye ek olarak insan tarafından okunabilir periyodik ID'ler kullanılır:

| Prefix | Örnek | Kapsam |
|---|---|---|
| `PA` + 7 hane | `PA1234567` | Admin hesapları |
| `PS` + 7 hane | `PS1234567` | Standart üye hesapları |
| `PT` + 7 hane | `PT1234567` | Premium üye hesapları |
| `PB` + 7 hane | `PB1234567` | Kitap kayıtları |
| `L` + 6 hane | `L123456` | Organizasyonlar |

### 2.5 Endpoint Listesi

Tüm endpoint'ler `/api/v1` prefix'i ile başlar.

#### `/api/v1/user`

| Endpoint | Metod | Yetki |
|---|---|---|
| `/log-in` | GET | Herkese açık |
| `/visitor-register` | POST | Default Admin, Master Admin |
| `/visitor-register-request` | POST | Herkese açık |
| `/visitor-register-approval` | POST | Default Admin, Master Admin |
| `/visitor-search` | GET | Default Admin, Master Admin |
| `/visitor-delete` | DELETE | Visitor (kendisi), Default Admin, Master Admin |
| `/teacher-register` | POST | Default Admin, Master Admin |
| `/admin-register` | POST | Master Admin |
| `/admin-update` | PATCH | Master Admin |
| `/admin-delete` | DELETE | Master Admin |
| `/admin-search` | GET | Master Admin |
| `/master-admin-register` | POST | General Admin |

#### `/api/v1/books`

| Endpoint | Metod | Yetki |
|---|---|---|
| `/book-search` | GET | Herkese açık |

#### `/api/v1/barrowed`

| Endpoint | Metod | Yetki |
|---|---|---|
| `/visitor-barroweds` | GET | Visitor, Teacher |
| `/all-barroweds` | GET | Default Admin, Master Admin |
| `/add-barrowed` | POST | Default Admin, Master Admin |
| `/take-barrowed` | DELETE | Default Admin, Master Admin |

#### `/api/v1/transaction`

| Endpoint | Metod | Yetki |
|---|---|---|
| `/get-transactions` | GET | Default Admin, Master Admin |

#### `/api/v1/feedback`

| Endpoint | Metod | Yetki |
|---|---|---|
| `/add-feedback` | POST | Visitor, Teacher |
| `/get-feedbacks` | GET | Default Admin, Master Admin |

#### `/api/v1/management`

| Endpoint | Metod | Yetki |
|---|---|---|
| `/get-organizations` | GET | General Admin |
| `/add-organization` | POST | General Admin |
| `/organization-delete` | DELETE | General Admin |

#### `/api/v1/management-transaction`

| Endpoint | Metod | Yetki |
|---|---|---|
| `/view-management-transactions` | GET | General Admin |

---

## 3. Veritabanı
> 👤 **Merdan Meriç NURAY**

### 3.1 Teknoloji

**Oracle Database** kullanılmaktadır. Veri erişim katmanında ORM yerine **JDBI** (Java Database Interface) tercih edilmiştir. JDBI, SQL üzerinde tam kontrol sağlarken boilerplate kodu minimize eder; Oracle'a özgü query optimizasyonlarının açık biçimde yazılabilmesine olanak tanır.

### 3.2 Tablo Yapısı

| Tablo | Birincil Anahtar | Yabancı Anahtarlar |
|---|---|---|
| `USERS` | `id (UUID)` | — |
| `ADMINS` | `id (UUID)` | `id → USERS` |
| `VISITORS` | `id (UUID)` | `id → USERS` |
| `BOOKS` | `id (PB+7h)` | `organizationPerId → ORGANIZATIONS` |
| `BARROWEDS` | `barrowedId (UUID)` | `visitorId → VISITORS`, `bookId → BOOKS`, `organizationPerId → ORGANIZATIONS` |
| `TRANSACTIONS` | `transactionId (UUID)` | `userId → USERS`, `organizationPerId → ORGANIZATIONS` |
| `FEEDBACKS` | `feedbackId (UUID)` | `organizationPerId → ORGANIZATIONS` |
| `ORGANIZATIONS` | `organizationId (UUID)` | — |

### 3.3 Veritabanı Şeması (ER Diyagramı)

<!-- ER diyagramı buraya eklenecek: docs/diagrams/er-diagram.png -->
![ER Diyagramı](docs/diagrams/er-diagram.png)

### 3.4 Tablo Detayları

<!-- Tablo detay şeması buraya eklenecek: docs/diagrams/table-details.png -->
![Tablo Detayları](docs/diagrams/table-details.png)

---

## 4. Güvenlik
> 👤 **Sarp SAVAŞ**

### 4.1 JWT Kimlik Doğrulama

Kullanıcı `/api/v1/user/log-in` endpoint'ine giriş yaptığında sunucu, kullanıcının **rolünü payload'a dahil ederek** imzalı bir JWT üretir. İstemci sonraki tüm isteklerde bu token'ı `Authorization: Bearer <token>` başlığıyla gönderir; sunucu her endpoint'te token'ı doğrular ve role göre erişim verir.

### 4.2 Rol Tabanlı Erişim Kontrolü

| Rol | Platform | Yetkiler |
|---|---|---|
| `VISITOR` | Pergamon | Kitap görüntüleme, kendi ödünçleri, feedback, hesap silme |
| `TEACHER` | Pergamon | Visitor yetkileri + premium içerik |
| `DEFAULT_ADMIN` | Pergamon | Üye yönetimi, ödünç ver/al, kitap ve feedback görüntüleme |
| `MASTER_ADMIN` | Pergamon | Default Admin yetkileri + admin register/update/delete |
| `GENERAL_ADMIN` | Management | Organizasyon CRUD, master admin oluşturma, management transaction |

### 4.3 Çift ID Güvenlik Katmanı

Her kullanıcının iki kimlik değeri bulunur: **UUID** (dahili) ve **periyodik accountId** (görünür). Request sınıflarında işlemler her zaman UUID üzerinden doğrulanır.

> **Garanti:** Aynı role sahip bir kullanıcı başka birinin UUID'sini bilse bile o kullanıcı adına işlem yapamaz; JWT içindeki `id` ile request'teki `id` sunucu tarafında karşılaştırılır.

### 4.4 Ağ Güvenliği — Cloudflare Tunnel

Sunucu doğrudan internete açık değildir. Tüm dış trafik **Cloudflare Tunnel** üzerinden yönlendirilir:

- Sunucunun gerçek IP adresi gizli kalır
- DDoS koruması ve bot filtrelemesi Cloudflare katmanında gerçekleşir
- TLS sonlandırması Cloudflare tarafından yapılır

### 4.5 Şifre Güvenliği

Kullanıcı şifreleri veritabanında `passwordHash` alanında **hash'lenmiş** olarak saklanır. Hiçbir aşamada açık metin şifre depolanmaz.

---

## 5. Frontend
> 👤 **Sarp SAVAŞ**

### 5.1 Genel Yaklaşım

Her iki frontend de herhangi bir JavaScript framework'ü kullanılmadan **saf HTML5, CSS3 ve Vanilla JS** ile geliştirilmiştir. Renk paleti: `#592232` (bordo) ve `#EEEAEA` (krem).

Her site üç dosyadan oluşur:

| Dosya | İçerik |
|---|---|
| `index.html` | Sayfa yapısı, auth ekranları, tüm sayfa ve modal HTML'leri |
| `style.css` | Renk paleti, layout, bileşen stilleri |
| `app.js` | API katmanı, rol yönetimi, sayfa navigasyonu, mock data (DEV modu) |

### 5.2 Pergamon — Ana Platform

Dört farklı role göre dinamik sidebar navigasyonu sunar:

| Rol | Erişilebilir Sayfalar |
|---|---|
| **Visitor** | Kitap kataloğu, Ödünçlerim, Geri Bildirim, Hesabım |
| **Teacher (Premium)** | Visitor sayfaları + Premium rozeti |
| **Default Admin** | İşlemler, Ödünç Yönetimi, Kitaplar, Üye Listesi, Onay Bekleyenler, Üye Kaydı, Geri Bildirimler |
| **Master Admin** | Default Admin sayfaları + Admin Yönetimi |

### 5.3 Pergamon Management — Yönetim Paneli

Yalnızca General Admin'e açık, minimal kurumsal arayüz. Giriş ekranında "Kısıtlı Erişim" uyarısı bulunur.

| Sayfa | Açıklama |
|---|---|
| Organizasyonlar | Kütüphane şubelerini kart görünümüyle listeler; ekleme/düzenleme/silme |
| Yönetim İşlemleri | Management transaction'larını istatistik kartlarıyla gösterir |
| Master Admin Ekle | Seçilen organizasyona Master Admin atama formu |

### 5.4 DEV / PROD Modu

`app.js` dosyasının en üstündeki tek bir sabit ile geçiş yapılır:

```js
const DEV = true;  // Geliştirme modu
const DEV = false; // Üretim modu — gerçek API'ye bağlanır
```

| Özellik | `DEV = true` | `DEV = false` |
|---|---|---|
| Auth | Atlanır, direkt uygulamaya girilir | JWT zorunlu |
| API | Mock data döner | Gerçek endpoint'lere istek |
| Validasyon | Devre dışı | Aktif |
| Banner | Sarı DEV banner + rol değiştirici | Gizli |

---

## 6. CI/CD Pipeline
> 👤 **Sarp SAVAŞ**

### 6.1 Genel Akış

```
Geliştirici (git push)
       │
       ▼
 GitHub Actions
  ├── Maven Build & Test
  └── Docker Image Build
       │
       ▼
   Docker Hub
  (Image push)
       │
       ▼
  Watchtower (VM)
  ├── Yeni image algılar
  ├── Mevcut container'ı durdurur
  └── Yeni versiyonla başlatır
```

### 6.2 Pipeline Adımları

| Adım | Araç | Açıklama |
|---|---|---|
| 1. Kaynak Kod | Git / GitHub | `master` branch'e push |
| 2. Build & Test | GitHub Actions | Maven ile derleme ve test |
| 3. Image Build | Docker | JAR → Docker image |
| 4. Registry Push | Docker Hub | Image tag'lenerek push edilir |
| 5. Auto-Deploy | Watchtower | Yeni image algılanır, container otomatik güncellenir |

### 6.3 Pipeline Diyagramı

<!-- CI/CD diyagramı buraya eklenecek: docs/diagrams/cicd-pipeline.png -->
![CI/CD Pipeline](docs/diagrams/cicd-pipeline.png)

### 6.4 Watchtower

Watchtower, Docker Hub'da yeni image tespit ettiğinde mevcut container'ı durdurup otomatik yeniden başlatır. Ek orchestration altyapısına ihtiyaç duymadan **sıfır-dokunuş deployment** sağlar.

---

## 7. DevOps & Altyapı
> 👤 **Sarp SAVAŞ**

### 7.1 Sunucu Ortamı

| Bileşen | Teknoloji | Detay |
|---|---|---|
| İşletim Sistemi | Ubuntu LTS | Sanal makine |
| Container Runtime | Docker | Tüm servisler container'da |
| Uygulama | Spring Boot JAR | Docker image içinde paketlenmiş |
| Watchtower | Docker Container | Otomatik image güncelleme |
| Ağ | Cloudflare Tunnel | Güvenli dış erişim |

### 7.2 Ağ Topolojisi

```
İstemci
   │
   ▼
Cloudflare (DNS + DDoS + WAF + TLS)
   │  Cloudflare Tunnel (şifreli)
   ▼
Ubuntu LTS VM
   └── Docker
        ├── Spring Boot Container  (API)
        └── Watchtower Container   (auto-update)
```

Sunucunun herhangi bir portunu internete açmak gerekmez; tüm dış trafik tünel üzerinden akar.

### 7.3 Sunucu Şeması

<!-- Sunucu/altyapı şeması buraya eklenecek: docs/diagrams/server-diagram.png -->
![Sunucu Şeması](docs/diagrams/server-diagram.png)

### 7.4 Domain Yapılandırması

| Domain | Hedef | Yönetim |
|---|---|---|
| `pergamon.sarpsavas.com` | Pergamon Frontend | Cloudflare DNS + Tunnel |
| `management-pergamon.sarpsavas.com` | Pergamon Management Frontend | Cloudflare DNS + Tunnel |

---

## 8. Mevcut Durum & Yol Haritası
> 👤 **Sarp SAVAŞ**

### ✅ Tamamlanan

- Clean Architecture + CQRS backend iskeleti
- Oracle DB entegrasyonu (JDBI)
- Tüm entity ve domain modeli
- Controller ve endpoint tanımları
- JWT kimlik doğrulama ve rol kontrolü
- Çift ID güvenlik katmanı
- Pergamon ve Pergamon Management frontend arayüzleri
- DEV/PROD çift modlu frontend mimarisi
- Docker container'laştırma
- GitHub Actions CI/CD pipeline
- Watchtower otomatik deployment
- Cloudflare Tunnel ağ güvenliği
- Domain yapılandırması

### 🔜 Planlanan

- Spring Security endpoint authorization anotasyonları
- Kitap ekleme/güncelleme/silme admin endpoint'leri
- Ödünç süre uzatma talebi endpoint'i
- Pending başvuru listeleme endpoint'i
- Frontend DEV → PROD geçişi ve gerçek API entegrasyonu
- Görsel kitap kapağı yönetimi (`imageUrl`)
- Rate limiting ve CORS yapılandırması

---

## 🧪 Test
> 👤 **Melisa BÜYÜKKOYUNCU · Kerem Can HAYVACI · Sami Bilal GÖLGECİ**

> Test senaryoları ve sonuçları bu bölümde raporlanacaktır.

---

<div align="center">

`pergamon.sarpsavas.com` · `management-pergamon.sarpsavas.com`

</div>