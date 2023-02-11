package dev.kemalyi.bookmarker.bookmark.rest;

import dev.kemalyi.bookmarker.bookmark.BookmarkService;
import dev.kemalyi.bookmarker.bookmark.dto.BookmarksDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bookmarks/")
public class BookmarkController {
    private final BookmarkService service;

    @GetMapping("{page}")
    public BookmarksDto getBookmarks(@PathVariable(name = "page") Optional<Integer> page) {
        return service.getBookmarks(page.orElse(1));
    }
}
