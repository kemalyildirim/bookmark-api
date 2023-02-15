package dev.kemalyi.bookmarker;

import dev.kemalyi.bookmarker.bookmark.rest.exception.BookmarkNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BookmarkApiExceptionHandler extends ResponseEntityExceptionHandler {
//    TODO: https://www.sivalabs.in/spring-boot-3-error-reporting-using-problem-details/

    @ExceptionHandler(BookmarkNotFoundException.class)
    ProblemDetail handleBookmarkNotFoundException(BookmarkNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Bookmark not found");
        return problemDetail;
    }
}
