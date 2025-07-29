package org.example.studymaterial;

public class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private int viewCount;
    private int downloadCount;
    private int shareCount;

    public Reference() {
        // Construtor padrão
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getAccessRights() { return accessRights; }
    public void setAccessRights(String accessRights) { this.accessRights = accessRights; }

    public String getLicense() { return license; }
    public void setLicense(String license) { this.license = license; }

    public boolean getIsDownloadable() { return isDownloadable; }
    public void setDownloadable(boolean downloadable) { this.isDownloadable = downloadable; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }

    public int getDownloadCount() { return downloadCount; }
    public void setDownloadCount(int downloadCount) { this.downloadCount = downloadCount; }

    public int getShareCount() { return shareCount; }
    public void setShareCount(int shareCount) { this.shareCount = shareCount; }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public void incrementDownloadCount() {
        this.downloadCount++;
    }

    public void incrementShareCount() {
        this.shareCount++;
    }

    public double calculatePopularityScore() {
        return rating * 0.5 + viewCount * 0.2 + downloadCount * 0.2 + shareCount * 0.1;
    }

    public void updateRating(int newRating) {
        if (newRating >= 0 && newRating <= 10) {
            this.rating = (this.rating + newRating) / 2;
        }
    }

    /**
     * Aplica uma interação do usuário com a referência,
     * podendo ser uma visualização, download ou compartilhamento.
     * Atualiza contadores e flags de acordo com o tipo de interação e sucesso.
     *
     * @param interactionType tipo de interação: "view", "download" ou "share"
     * @param successful indica se a interação foi bem sucedida
     * @return true se a interação foi aplicada, false se inválida
     */
    public boolean applyUserInteraction(String interactionType, boolean successful) {
        if (!successful) {
            return false;
        }

        switch (interactionType.toLowerCase()) {
            case "view":
                incrementViewCount();
                return true;
            case "download":
                if (!isDownloadable) {
                    return false;
                }
                incrementDownloadCount();
                return true;
            case "share":
                incrementShareCount();
                return true;
            default:
                return false;
        }
    }

    /**
     * Atualiza múltiplos atributos da referência com lógica condicional,
     * combinando avaliação, visualização, download e compartilhamento.
     *
     * @param newRating nova avaliação (0 a 10)
     * @param viewed indica se foi visualizada
     * @param downloaded indica se foi baixada
     * @param shared indica se foi compartilhada
     * @return resumo das ações realizadas
     */
    public String performComplexUserAction(int newRating, boolean viewed, boolean downloaded, boolean shared) {
        StringBuilder result = new StringBuilder();

        if (newRating >= 0 && newRating <= 10) {
            updateRating(newRating);
            result.append("Rating updated; ");
        }

        if (viewed) {
            incrementViewCount();
            result.append("View counted; ");
        }

        if (downloaded) {
            if (isDownloadable) {
                incrementDownloadCount();
                result.append("Download counted; ");
            } else {
                result.append("Download blocked; ");
            }
        }

        if (shared) {
            incrementShareCount();
            result.append("Share counted; ");
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return "Reference{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", accessRights='" + accessRights + '\'' +
                ", license='" + license + '\'' +
                ", isDownloadable=" + isDownloadable +
                ", rating=" + rating +
                ", language='" + language + '\'' +
                ", viewCount=" + viewCount +
                ", downloadCount=" + downloadCount +
                ", shareCount=" + shareCount +
                '}';
    }

    public String getSummary() {
        return String.format("Reference: '%s' [%s], Rating: %d",
                title != null ? title : "N/A",
                language != null ? language : "N/A",
                rating);
    }

    public boolean isValid() {
        return title != null && !title.isEmpty() && language != null && !language.isEmpty();
    }
}