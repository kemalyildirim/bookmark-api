package dev.kemalyi.bookmarker.bookmark.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
