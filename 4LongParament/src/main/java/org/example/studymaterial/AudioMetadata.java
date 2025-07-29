package org.example.studymaterial;

public record AudioMetadata(AudioReference.AudioQuality audioQuality, boolean isDownloadable, String title,
                            String description, String link, String accessRights, String license, String language,
                            int rating, int viewCount, int shareCount) {
}