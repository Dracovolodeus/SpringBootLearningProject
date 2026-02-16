package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dto.converter.BookConverter;
import org.example.dto.model.program.BookDto;
import org.example.dto.model.request.BookCreateDto;
import org.example.entity.AuthorEntity;
import org.example.entity.BookEntity;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NotFoundException;
import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.example.service.intr.BookServiceIntr;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookServiceIntr {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public BookDto get(long id) throws NotFoundException {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException("Book not found.");
        }
        return BookConverter.toDto(book.get());
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(BookConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDto create(BookCreateDto bookDto) throws NotFoundException, InvalidArgumentException{
        if (bookDto.getName().isBlank()) {
            throw new InvalidArgumentException("Invalid book name.");
        }
        AuthorEntity author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found."));
        BookEntity book = new BookEntity();
        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setYear(bookDto.getYear());
        book.setDescription(bookDto.getDescription());
        bookRepository.save(book);
        return BookConverter.toDto(book);
    }

    @Override
    public void delete(long id) throws NotFoundException{
        if (!bookRepository.existsById(id)){
            throw new NotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}
