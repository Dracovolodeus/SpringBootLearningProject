package org.example.dto.converter;

import lombok.experimental.UtilityClass;
import org.example.dto.model.program.BookDto;
import org.example.entity.BookEntity;

@UtilityClass
public class BookConverter {
    public BookDto toDto(BookEntity book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .description(book.getDescription())
                .author(book.getAuthor())
                .build();
    }

    public BookEntity toEntity(BookDto book) {
        return BookEntity.builder()
                .id(book.getId())
                .name(book.getName())
                .description(book.getDescription())
                .year(book.getYear())
                .author(book.getAuthor())
                .build();
    }
}
