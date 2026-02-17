package org.example.service;

import org.example.dto.model.book.BookDto;
import org.example.dto.model.book.BookCreateDto;
import org.example.dto.model.book.BookUpdateDto;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NotFoundException;

import java.util.List;

public interface BookService {

    public BookDto get(long id) throws NotFoundException;

    public List<BookDto> getAll();

    public BookDto create(BookCreateDto book) throws NotFoundException, InvalidArgumentException;

    public BookDto update(BookUpdateDto book) throws NotFoundException;

    public void delete(long id) throws NotFoundException;
}
