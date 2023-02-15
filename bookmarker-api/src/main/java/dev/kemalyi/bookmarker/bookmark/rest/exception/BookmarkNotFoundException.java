package dev.kemalyi.bookmarker.bookmark.rest.exception;

public class BookmarkNotFoundException extends RuntimeException {
    public BookmarkNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public BookmarkNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
