package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = BookEntity.TABLE_NAME)
public class BookEntity {
    public static final String TABLE_NAME = "book";
    public static final String COLUMN_ID_NAME = "id";
    public static final String COLUMN_YEAR_NAME = "publish_year";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_DESCRIPTION_NAME = "description";
    public static final String COLUMN_AUTHOR_NAME = "author_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    Long id;

    @Column(name = COLUMN_NAME_NAME, nullable = false)
    private String name;

    @Column(name = COLUMN_YEAR_NAME)
    private String year;

    @Column(name = COLUMN_DESCRIPTION_NAME)
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_AUTHOR_NAME)
    private AuthorEntity author;
}
