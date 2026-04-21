package com.pergamon.application.book.create_book;

import java.time.LocalDate;

import enums.Availability;
import enums.BookType;

public record CreateBookCommand(String Id, // {PL}+{_______}7int
String Name,
String Author,
 BookType BookType,
 Availability Availability,
 int PageNumber) {}
