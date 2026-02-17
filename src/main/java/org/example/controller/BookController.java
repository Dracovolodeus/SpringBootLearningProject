package org.example.controller;

import com.sun.net.httpserver.HttpsServer;
import lombok.AllArgsConstructor;
import org.example.dto.converter.BookConverter;
import org.example.dto.model.program.BookDto;
import org.example.dto.model.request.BookCreateDto;
import org.example.dto.model.request.BookUpdateDto;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NotFoundException;
import org.example.service.intr.BookServiceIntr;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;


@RestController
@RequestMapping("/api/v1/book")
@AllArgsConstructor
public class BookController {
    private BookServiceIntr bookService;

    @GetMapping("/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getById(@PathVariable long id) throws NotFoundException, InvalidArgumentException {
        return bookService.get(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @PostMapping("/")
    public BookDto createBook(BookCreateDto book) {
        return bookService.create(book);
    }

    @PutMapping("/")
    public void updateBook(BookUpdateDto book) {
        bookService.update(book);
    }

    @DeleteMapping("/{id}/")
    public void deleteBook(@PathVariable long id) {
        bookService.delete(id);
    }

}