package dev.kemalyi.bookmarker.bookmark;

import dev.kemalyi.bookmarker.bookmark.dto.BookmarkDto;
import dev.kemalyi.bookmarker.bookmark.dto.BookmarksDto;
import dev.kemalyi.bookmarker.bookmark.jpa.BookmarkRepository;
import dev.kemalyi.bookmarker.bookmark.jpa.entity.Bookmark;
import dev.kemalyi.bookmarker.bookmark.rest.exception.BookmarkNotFoundException;
import dev.kemalyi.bookmarker.bookmark.rest.requests.CreateBookmarkRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookmarkService {
    private static final int PAGE_SIZE = 2;
    private final BookmarkRepository repository;

    private Pageable bookmarkPageableBootstrap(int page) {
        page = page < 1 ? 0 : page - 1;
        return PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, "createdAt");
    }

    @Transactional(readOnly = true)
    public BookmarksDto getBookmarks(int page) {
        Pageable pageable = bookmarkPageableBootstrap(page);
        return new BookmarksDto(repository.findBookmarks(pageable));
    }

    @Transactional(readOnly = true)
    public BookmarksDto searchBookmarks(String query, int page) {
        Pageable pageable = bookmarkPageableBootstrap(page);
        return new BookmarksDto(repository.searchBookmarks(query, pageable));
    }

    public BookmarkDto createBookmark(CreateBookmarkRequest request) {
        Bookmark bookmark = new Bookmark(null, request.title(), request.url(), Instant.now(), null);
        return repository.save(bookmark).toModel();
    }

    public BookmarkDto getBookmarkById(Long id) {
        return repository.findBookmarkById(id).orElseThrow(() -> new BookmarkNotFoundException("Bookmark not found"));
    }
}
