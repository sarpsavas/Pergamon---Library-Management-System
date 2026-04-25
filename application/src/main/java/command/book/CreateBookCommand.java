package command.book;

import java.time.LocalDate;

import enums.Availability;
import enums.BookType;

public record CreateBookCommand(
String name,
String author,
 BookType booktype,
 Availability availability,
 int pagenumber) {}
