package org.example.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.example.dto.model.author.AuthorCreateDto;
import org.example.dto.model.author.AuthorDto;
import org.example.dto.model.author.AuthorUpdateDto;
import org.example.dto.model.book.BookDto;
import org.example.service.AuthorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/author")
@AllArgsConstructor
public class AuthorController {

  private AuthorService authorService;

  @GetMapping("/{id}")
  public AuthorDto get(@PathVariable long id) {
    return authorService.get(id);
  }

  @GetMapping("")
  public List<AuthorDto> getAll() {
    return authorService.getAll();
  }

  @GetMapping("/{id}/books")
  public List<BookDto> getAllBooks(@PathVariable long id) {
    return authorService.getAllBooks(id);
  }

  @PostMapping("")
  public AuthorDto create(@RequestBody AuthorCreateDto authorCreateDto) {
    return authorService.create(authorCreateDto);
  }

  @PutMapping("")
  public AuthorDto update(@RequestBody AuthorUpdateDto authorUpdateDto) {
    return authorService.update(authorUpdateDto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable long id) {
    authorService.delete(id);
  }
}
