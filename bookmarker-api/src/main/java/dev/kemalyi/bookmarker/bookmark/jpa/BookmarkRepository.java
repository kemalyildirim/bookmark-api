package dev.kemalyi.bookmarker.bookmark.jpa;

import dev.kemalyi.bookmarker.bookmark.dto.BookmarkDto;
import dev.kemalyi.bookmarker.bookmark.jpa.entity.Bookmark;
import dev.kemalyi.bookmarker.bookmark.rest.requests.CreateBookmarkRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("""
            SELECT new dev.kemalyi.bookmarker.bookmark.dto.BookmarkDto(
                    b.id,
                    b.title,
                    b.url,
                    b.createdAt
                )
            FROM Bookmark b
            """)
    Page<BookmarkDto> findBookmarks(Pageable pageable);

    @Query("""
            SELECT new dev.kemalyi.bookmarker.bookmark.dto.BookmarkDto(
                    b.id,
                    b.title,
                    b.url,
                    b.createdAt
                )
            FROM Bookmark b
            WHERE lower(b.title) like lower(concat('%', :query, '%'))
            """)
    Page<BookmarkDto> searchBookmarks(String query, Pageable pageable);
}
