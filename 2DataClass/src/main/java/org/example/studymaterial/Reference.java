package org.example.studymaterial;

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;

    private int viewCount = 0;
    private int downloadCount = 0;
    private int shareCount = 0;

    // === Domain Logic ===

    public void registerView(int view) {
        viewCount = view;
    }

    public void registerDownload() {
        if (!isDownloadable) {
            throw new IllegalStateException("This reference is not downloadable.");
        }
        downloadCount++;
    }

    public void registerShare(int share) {
        shareCount = share;
    }

    public boolean isTrending() {
        return (viewCount + downloadCount + shareCount) > 100;
    }

    public double calculateEngagementScore() {
        return (viewCount * 0.1) + (downloadCount * 0.5) + (shareCount * 0.4);
    }

    public String getSummary() {
        return String.format(
                "%s (%s)\nRating: %d\nLicense: %s\nLanguage: %s\nEngagement Score: %.2f",
                title, description, rating, license, language, calculateEngagementScore()
        );
    }

    public void updateMetadata(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }

    // === Setters com controle ===

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setDownloadable(boolean downloadable) {
        this.isDownloadable = downloadable;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // === Read-Only Accessors ===

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLink() { return link; }
    public String getLanguage() { return language; }
    public String getAccessRights() { return accessRights; }
    public String getLicense() { return license; }
    public boolean getIsDownloadable() { return isDownloadable; }
    public int getRating() { return rating; }
    public int getViewCount() { return viewCount; }
    public int getDownloadCount() { return downloadCount; }
    public int getShareCount() { return shareCount; }

    public int getTotalEngagements() {
        return viewCount + downloadCount + shareCount;
    }
}
