package org.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.example.dto.converter.BookConverter;
import org.example.dto.model.book.BookCreateDto;
import org.example.dto.model.book.BookDto;
import org.example.dto.model.book.BookUpdateDto;
import org.example.entity.AuthorEntity;
import org.example.entity.BookEntity;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NotFoundException;
import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  @Override
  @Transactional(readOnly = true)
  public BookDto get(long id) throws NotFoundException {
    BookEntity book =
        bookRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Book with id " + id + " not found."));
    return BookConverter.toDto(book);
  }

  @Override
  @Transactional(readOnly = true)
  public List<BookDto> getAll() {
    return bookRepository.findAll().stream().map(BookConverter::toDto).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public BookDto create(BookCreateDto bookDto) throws NotFoundException, InvalidArgumentException {
    if (bookDto.getName().isBlank()) {
      throw new InvalidArgumentException("Invalid book name.");
    }
    AuthorEntity author =
        authorRepository
            .findById(bookDto.getAuthorId())
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "Author with id " + bookDto.getAuthorId() + " not found."));
    BookEntity book = new BookEntity();
    book.setName(bookDto.getName());
    book.setAuthor(author);
    book.setYear(bookDto.getYear());
    book.setDescription(bookDto.getDescription());
    bookRepository.save(book);
    return BookConverter.toDto(book);
  }

  @Override
  @Transactional
  public BookDto update(BookUpdateDto bookDto) throws NotFoundException {
    BookEntity book =
        bookRepository
            .findById(bookDto.getId())
            .orElseThrow(
                () -> new NotFoundException("Book with id " + bookDto.getId() + " not found."));
    if (bookDto.getAuthorId() != book.getAuthor().getId())
      book.setAuthor(
          authorRepository
              .findById(bookDto.getAuthorId())
              .orElseThrow(
                  () ->
                      new NotFoundException(
                          "Author with id " + bookDto.getAuthorId() + " not found.")));
    book.setName(bookDto.getName());
    book.setDescription(bookDto.getDescription());
    book.setYear(bookDto.getYear());
    bookRepository.save(book);
    return BookConverter.toDto(book);
  }

  @Override
  @Transactional
  public void delete(long id) throws NotFoundException {
    if (!bookRepository.existsById(id)) {
      throw new NotFoundException("Book with id " + id + " not found.");
    }
    bookRepository.deleteById(id);
  }
}
