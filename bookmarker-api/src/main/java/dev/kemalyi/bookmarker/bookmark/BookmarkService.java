package dev.kemalyi.bookmarker.bookmark;

import dev.kemalyi.bookmarker.bookmark.dto.BookmarksDto;
import dev.kemalyi.bookmarker.bookmark.jpa.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
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
}
