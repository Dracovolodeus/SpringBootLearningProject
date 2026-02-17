package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.model.program.AuthorDto;
import org.example.dto.model.program.BookDto;
import org.example.dto.model.request.AuthorCreateDto;
import org.example.dto.model.request.AuthorUpdateDto;
import org.example.service.intr.AuthorServiceIntr;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
@AllArgsConstructor
public class AuthorController {

    private AuthorServiceIntr authorService;

    @GetMapping("/{id}/")
    public AuthorDto get(@PathVariable long id) {
        return authorService.get(id);
    }

    @GetMapping("/")
    public List<AuthorDto> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}/books/")
    public List<BookDto> getAllBooks(@PathVariable long id) {
        return authorService.getAllBooks(id);
    }

    @PostMapping("/")
    public AuthorDto create(AuthorCreateDto authorCreateDto) {
        return authorService.create(authorCreateDto);
    }

    @PutMapping("/")
    public AuthorDto update(AuthorUpdateDto authorUpdateDto) {
        return authorService.update(authorUpdateDto);
    }

    @DeleteMapping("/{id}/")
    public void delete(@PathVariable long id) {
        authorService.delete(id);
    }

}
