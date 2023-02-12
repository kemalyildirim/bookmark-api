package dev.kemalyi.bookmarker.bookmark.dto;

import org.springframework.data.domain.Page;
import java.util.List;

public record BookmarksDto(int totalPages, long totalElements, int currentPage, List<BookmarkDto> bookmarks) {

    public BookmarksDto(Page<BookmarkDto> bookmarks) {
        this(bookmarks.getTotalPages(), bookmarks.getTotalElements(), bookmarks.getNumber() + 1, bookmarks.getContent());
    }
}
