package dev.kemalyi.bookmarker.bookmark.dto;

import java.time.Instant;

public record BookmarkDto(Long id, String title, String url, Instant createdAt, Instant updatedAt) {
}