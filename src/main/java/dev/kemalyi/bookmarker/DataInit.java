package dev.kemalyi.bookmarker;

import dev.kemalyi.bookmarker.bookmark.jpa.Bookmark;
import dev.kemalyi.bookmarker.bookmark.jpa.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final BookmarkRepository repository;
    @Override
    public void run(String... args) throws Exception {
        repository.save(new Bookmark(null, "Google", "www.google.com.tr", Instant.now(), null));
        repository.save(new Bookmark(null, "Twitter", "www.twitter.com", Instant.now(), null));
        repository.save(new Bookmark(null, "Reddit", "www.reddit.com", Instant.now(), null));
    }
}
