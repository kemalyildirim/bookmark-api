package dev.kemalyi.bookmarker.bookmark.rest;

import dev.kemalyi.bookmarker.bookmark.jpa.BookmarkRepository;
import dev.kemalyi.bookmarker.bookmark.jpa.entity.Bookmark;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:15.2:///test"
})
class BookmarkControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    BookmarkRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAllInBatch();
        List<Bookmark> bookmarks = List.of(
                new Bookmark(null, "Google", "www.google.com.tr", Instant.now(), null),
                new Bookmark(null,"Twitter", "www.twitter.com", Instant.now(), null),
                new Bookmark(null,"Facebook", "www.facebook.com", Instant.now(), null),
                new Bookmark(null,"Twitch", "www.twitch.tv", Instant.now(), null),
                new Bookmark(null,"Amazon", "www.amazon.com", Instant.now(), null),
                new Bookmark(null,"GitHub", "www.github.com", Instant.now(), null),
                new Bookmark(null,"FreeCodeCamp", "www.freecodecamp.com", Instant.now(), null),
                new Bookmark(null,"LinkedIn", "www.linkedin.com", Instant.now(), null),
                new Bookmark(null,"Youtube", "www.youtube.com", Instant.now(), null),
                new Bookmark(null,"Reddit", "www.reddit.com", Instant.now(), null),
                new Bookmark(null, "test", "test.com", Instant.now(), null),
                new Bookmark(null, "test2", "test2.com", Instant.now(), null)
        );
        repository.saveAll(bookmarks);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 12, 6, 1",
            "2, 12, 6, 2"
    })
    void shouldGetBookmarks(int page, int totalElements, int totalPages, int currentPage) throws Exception {
        mvc.perform(get("/api/bookmarks?page=" + page))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(totalElements))
                .andExpect(jsonPath("$.totalPages").value(totalPages))
                .andExpect(jsonPath("$.currentPage").value(currentPage));
    }

    @ParameterizedTest
    @CsvSource({
            "tes, 2, 1",
            "it, 4, 2"
    })
    void shouldGetFilteredBookmarks(String query, int totalElements, int totalPages) throws Exception {
        mvc.perform(get("/api/bookmarks?query=" + query))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(totalElements))
                .andExpect(jsonPath("$.totalPages").value(totalPages));
    }
}