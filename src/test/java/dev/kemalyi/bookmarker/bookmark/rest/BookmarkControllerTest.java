package dev.kemalyi.bookmarker.bookmark.rest;

import dev.kemalyi.bookmarker.bookmark.jpa.BookmarkRepository;
import dev.kemalyi.bookmarker.bookmark.jpa.entity.Bookmark;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.org.hamcrest.CoreMatchers;

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

    private List<Bookmark> bookmarks;
    @Autowired
    private MockMvc mvc;
    @Autowired
    BookmarkRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAllInBatch();
        bookmarks = List.of(
            new Bookmark(null, "test", "test.com", Instant.now(), null),
            new Bookmark(null, "test2", "test2.com", Instant.now(), null),
            new Bookmark(null, "Google", "google.com", Instant.now(), Instant.now().plusSeconds(60))
        );
        repository.saveAll(bookmarks);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 3, 2, 1",
            "2, 3, 2, 2"
    })
    void shouldGetBookmarks(int page, int totalElements, int totalPages, int currentPage) throws Exception {
        mvc.perform(get("/api/bookmarks/" + page))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(totalElements))
                .andExpect(jsonPath("$.totalPages").value(totalPages))
                .andExpect(jsonPath("$.currentPage").value(currentPage));
    }
}