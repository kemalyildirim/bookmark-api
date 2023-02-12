package dev.kemalyi.bookmarker.bookmark.rest;

import dev.kemalyi.bookmarker.bookmark.BookmarkService;
import dev.kemalyi.bookmarker.bookmark.dto.BookmarksDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {
    private final BookmarkService service;

    @GetMapping
    public BookmarksDto getBookmarks(@RequestParam(name = "page") Optional<Integer> page, @RequestParam(name = "query", required = false, defaultValue = "") String query) {
        int actualPage = page.orElse(1);
        if (query == null || query.isEmpty())
            return service.getBookmarks(actualPage);
        return service.searchBookmarks(query, actualPage);
    }
}
