package com.pergamon.application.command.book;

import java.time.LocalDate;

import com.pergamon.core.enums.Availability;
import com.pergamon.core.enums.BookType;

public record CreateBookCommand(
String name,
String author,
 BookType booktype,
 Availability availability,
 int pagenumber) {}
