package org.example.dto.model.program;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.AuthorEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private long id;
    private String name;
    private String description;
    private String year;
    private AuthorEntity author;
}
