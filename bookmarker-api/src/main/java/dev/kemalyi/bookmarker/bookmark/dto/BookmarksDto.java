package dev.kemalyi.bookmarker.bookmark.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
public class BookmarksDto {
    private List<BookmarkDto> bookmarks;
    private int totalPages;
    private long totalElements;
    private int currentPage;

    public BookmarksDto(Page<BookmarkDto> bookmarkPage) {
        this.bookmarks = bookmarkPage.getContent();
        this.totalElements = bookmarkPage.getTotalElements();
        this.currentPage = bookmarkPage.getNumber() + 1;
        this.totalPages = bookmarkPage.getTotalPages();
    }
}
