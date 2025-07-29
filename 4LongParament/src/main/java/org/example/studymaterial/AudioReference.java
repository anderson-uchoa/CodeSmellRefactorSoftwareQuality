package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }
    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality){
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality){
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

     public void editAudio(AudioMetadata audioMetadata){
        editBasic(audioMetadata.title(), audioMetadata.description(), audioMetadata.link());
        this.setAccessRights(audioMetadata.accessRights());
        this.setLicense(audioMetadata.license());
        this.setAudioQuality(audioMetadata.audioQuality());
        editVideoAttributes(audioMetadata.rating(), audioMetadata.language(), audioMetadata.viewCount(), audioMetadata.shareCount(), audioMetadata.isDownloadable());
     }

     public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable){
         this.editAudio(new AudioMetadata(audioQuality, isDownloadable, properties.get(0), properties.get(1), properties.get(2), properties.get(3), properties.get(4), properties.get(5), intProperties.get(0), intProperties.get(1), intProperties.get(2)));
     }

     private void editVideoAttributes(int rating, String language, int viewCount, int shareCount,boolean isDownloadable){
         this.setRating(rating);
         this.registerShare(shareCount);
         this.registerView(viewCount);
         this.setDownloadable(isDownloadable);
         this.setLanguage(language);
     }

     public void editBasic(String title, String description, String link){
         this.updateMetadata(title, description, link);
     }

}
