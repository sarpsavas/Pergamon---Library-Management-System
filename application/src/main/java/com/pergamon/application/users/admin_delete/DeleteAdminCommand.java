package com.pergamon.application.users.admin_delete;

import java.util.UUID;

public record DeleteAdminCommand(UUID adminId, String organizationPerId, UUID sAdminId) {

}
