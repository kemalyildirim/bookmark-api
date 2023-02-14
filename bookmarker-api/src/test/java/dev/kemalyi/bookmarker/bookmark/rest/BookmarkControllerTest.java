package dev.kemalyi.bookmarker.bookmark.rest;

import dev.kemalyi.bookmarker.bookmark.jpa.BookmarkRepository;
import dev.kemalyi.bookmarker.bookmark.jpa.entity.Bookmark;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:15.2:///test"
})
class BookmarkControllerTest {

    private static final String BOOKMARK_API = "/api/bookmarks";

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
        mvc.perform(get(BOOKMARK_API + "?page=" + page))
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
    @DisplayName("Found bookmark count and total pages match with expected values")
    void shouldGetFilteredBookmarks(String query, int totalElements, int totalPages) throws Exception {
        mvc.perform(get(BOOKMARK_API + "?query=" + query))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(totalElements))
                .andExpect(jsonPath("$.totalPages").value(totalPages));
    }

    @Test
    @DisplayName("Verify found bookmark JSON values in the response are as expected")
    void verifyFoundBookmark() throws Exception {
        mvc.perform(get(BOOKMARK_API + "?query=Twitter"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookmarks[0].title").value("Twitter"))
                .andExpect(jsonPath("$.bookmarks[0].url").value("www.twitter.com"))
                .andDo(print());
    }

    @Test
    @DisplayName("Create Bookmark Success Case")
    void createBookmark() throws Exception {
        mvc.perform(post(BOOKMARK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title":"kemal blog",
                            "url":"kemalyi.dev"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(notNullValue()))
                .andExpect(jsonPath("$.title", is("kemal blog")))
                .andExpect(jsonPath("$.url", is("kemalyi.dev")));
    }
}