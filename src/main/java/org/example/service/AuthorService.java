package org.example.service;

import org.example.dto.model.program.AuthorDto;
import org.example.dto.model.program.BookDto;
import org.example.dto.model.request.AuthorCreateDto;
import org.example.dto.model.request.AuthorUpdateDto;
import org.example.exception.NotFoundException;

import java.util.List;

public interface AuthorService {

    public AuthorDto get(long id) throws NotFoundException;

    public List<AuthorDto> getAll();

    public List<BookDto> getAllBooks(long id) throws NotFoundException;

    public AuthorDto create(AuthorCreateDto authorDto);

    public AuthorDto update(AuthorUpdateDto authorDto) throws NotFoundException;

    public void delete(long id) throws NotFoundException;
}
