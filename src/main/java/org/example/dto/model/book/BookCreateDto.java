package org.example.dto.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDto {
    private long authorId;
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String year;
}
