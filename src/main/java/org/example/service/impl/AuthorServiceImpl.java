package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dto.converter.AuthorConverter;
import org.example.dto.converter.BookConverter;
import org.example.dto.model.program.AuthorDto;
import org.example.dto.model.program.BookDto;
import org.example.dto.model.request.AuthorCreateDto;
import org.example.dto.model.request.AuthorUpdateDto;
import org.example.entity.AuthorEntity;
import org.example.exception.NotFoundException;
import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.example.service.intr.AuthorServiceIntr;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorServiceIntr {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Override
    public AuthorDto get(long id) throws NotFoundException {
        return AuthorConverter.toDto(authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id " + id + " not found.")));
    }

    @Override
    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream().map(AuthorConverter::toDto).toList();
    }

    @Override
    public List<BookDto> getAllBooks(long id) throws NotFoundException {
//        return bookRepository.findByAuthorId(id).stream().map(BookConverter::toDto).toList();
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author with id " + id + " not found."))
                .getBooks()
                .stream()
                .map(BookConverter::toDto)
                .toList();
    }

    @Override
    public AuthorDto create(AuthorCreateDto authorDto) {
        AuthorEntity author = new AuthorEntity();
        author.setName(authorDto.getName());
        authorRepository.save(author);
        return AuthorConverter.toDto(author);
    }

    @Override
    public AuthorDto update(AuthorUpdateDto authorDto) throws NotFoundException {
        AuthorEntity author = authorRepository.findById(authorDto.getId()).orElseThrow(() -> new NotFoundException("Author with id " + authorDto.getId() + " not found."));
        author.setName(authorDto.getName());
        authorRepository.save(author);
        return AuthorConverter.toDto(author);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Author with id " + id + "not found.");
        }
        authorRepository.deleteById(id);
    }
}
