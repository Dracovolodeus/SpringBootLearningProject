package org.example.dto.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.model.author.AuthorDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private long id;
    private String name;
    private String description;
    private String year;
    private AuthorDto author;
}
