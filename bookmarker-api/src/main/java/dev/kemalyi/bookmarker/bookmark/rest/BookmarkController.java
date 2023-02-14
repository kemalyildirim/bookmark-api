package dev.kemalyi.bookmarker.bookmark.rest;

import dev.kemalyi.bookmarker.bookmark.BookmarkService;
import dev.kemalyi.bookmarker.bookmark.dto.BookmarkDto;
import dev.kemalyi.bookmarker.bookmark.dto.BookmarksDto;
import dev.kemalyi.bookmarker.bookmark.rest.requests.CreateBookmarkRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bookmarks")
@Slf4j
public class BookmarkController {
    private final BookmarkService service;

    @GetMapping
    public BookmarksDto getBookmarks(@RequestParam(name = "page") Optional<Integer> page, @RequestParam(name = "query", required = false, defaultValue = "") String query) {
        int actualPage = page.orElse(1);
        if (query == null || query.isEmpty())
            return service.getBookmarks(actualPage);
        return service.searchBookmarks(query, actualPage);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkDto createBookmark(@RequestBody @Valid CreateBookmarkRequest request) {
        log.info("[createBookmark] body: {}", request.toString());
        return service.createBookmark(request);
    }
}
