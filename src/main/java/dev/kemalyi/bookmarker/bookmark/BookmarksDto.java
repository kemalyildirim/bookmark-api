package dev.kemalyi.bookmarker.bookmark;

import dev.kemalyi.bookmarker.bookmark.jpa.Bookmark;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
public class BookmarksDto {
    private List<Bookmark> bookmarks;
    private int totalPages;
    private long totalElements;
    private int currentPage;

    public BookmarksDto(Page<Bookmark> bookmarkPage) {
        this.bookmarks = bookmarkPage.getContent();
        this.totalElements = bookmarkPage.getTotalElements();
        this.currentPage = bookmarkPage.getNumber() + 1;
        this.totalPages = bookmarkPage.getTotalPages();
    }
}
