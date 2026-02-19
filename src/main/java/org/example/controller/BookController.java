package org.example.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.example.dto.model.book.BookCreateDto;
import org.example.dto.model.book.BookDto;
import org.example.dto.model.book.BookUpdateDto;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NotFoundException;
import org.example.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@AllArgsConstructor
public class BookController {
  private BookService bookService;

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookDto getById(@PathVariable long id) throws NotFoundException, InvalidArgumentException {
    return bookService.get(id);
  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public List<BookDto> getAll() {
    return bookService.getAll();
  }

  @PostMapping("")
  public BookDto createBook(@RequestBody BookCreateDto book) {
    return bookService.create(book);
  }

  @PutMapping("")
  public BookDto updateBook(@RequestBody BookUpdateDto book) {
    return bookService.update(book);
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable long id) {
    bookService.delete(id);
  }
}
