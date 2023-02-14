package dev.kemalyi.bookmarker.bookmark.rest.requests;

import jakarta.validation.constraints.NotEmpty;

public record CreateBookmarkRequest(
        @NotEmpty(message = "title cannot be empty") String title,
        @NotEmpty(message = "url cannot be empty") String url
    ) {

}
