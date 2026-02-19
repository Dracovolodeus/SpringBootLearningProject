package org.example.dto.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateDto {
  private long id;
  private long authorId;
  private String name;
  private String description;
  private String year;
}
