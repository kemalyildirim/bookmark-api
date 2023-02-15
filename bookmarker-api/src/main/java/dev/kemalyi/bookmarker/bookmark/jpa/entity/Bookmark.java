package dev.kemalyi.bookmarker.bookmark.jpa.entity;

import dev.kemalyi.bookmarker.bookmark.dto.BookmarkDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Table(name = "bookmarks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bookmark {
    @Id
    @SequenceGenerator(name = "bookmark_id_seq_gen", sequenceName = "bookmark_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookmark_id_seq_gen")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public BookmarkDto toModel() {
        return new BookmarkDto(this.id, this.title, this.url, this.createdAt, this.updatedAt);
    }
}
