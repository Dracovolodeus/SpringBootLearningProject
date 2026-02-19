package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = AuthorEntity.TABLE_NAME)
public class AuthorEntity {
  public static final String TABLE_NAME = "authors";
  public static final String MAPPED_BY_BOOKS_NAME = "author";
  public static final String COLUMN_ID_NAME = "id";
  public static final String COLUMN_NAME_NAME = "name";
  public static final String COLUMN_BOOKS_NAME = "books";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = COLUMN_ID_NAME, nullable = false)
  private Long id;

  @Column(name = COLUMN_NAME_NAME, nullable = false)
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = MAPPED_BY_BOOKS_NAME, fetch = FetchType.LAZY)
  private List<BookEntity> books;
}
