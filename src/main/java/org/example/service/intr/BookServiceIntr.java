package org.example.service.intr;

import org.example.dto.model.program.BookDto;
import org.example.dto.model.request.BookCreateDto;
import org.example.entity.BookEntity;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NotFoundException;

import java.util.List;

public interface BookServiceIntr {

    public BookDto get(long id) throws NotFoundException;

    public List<BookDto> getAll();

    public BookDto create(BookCreateDto book) throws NotFoundException, InvalidArgumentException;

    public void delete(long id) throws NotFoundException;
}
