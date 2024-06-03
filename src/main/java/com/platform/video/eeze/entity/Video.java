package com.platform.video.eeze.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE videos SET soft_deleted = true WHERE video_id=? ")
@SQLRestriction("soft_deleted = false")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;
    private String title;
    private String synopsis;
    private String director;
    private int releaseYear;
    private int duration;
    private String genre;
    private String mainActor;
    private LocalDate uploadDate;
    private boolean softDeleted = Boolean.FALSE;
    private String content;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "video_actors",
        joinColumns = @JoinColumn(name = "video_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

    @PrePersist
    protected void onCreate() {
        uploadDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return releaseYear == video.releaseYear && duration == video.duration && softDeleted == video.softDeleted
            && Objects.equals(videoId, video.videoId) && Objects.equals(title, video.title)
            && Objects.equals(synopsis, video.synopsis) && Objects.equals(director, video.director)
            && Objects.equals(genre, video.genre) && Objects.equals(mainActor, video.mainActor)
            && Objects.equals(uploadDate, video.uploadDate) && Objects.equals(content, video.content)
            && Objects.equals(actors, video.actors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(videoId, title, synopsis, director, releaseYear, duration, genre, mainActor,
            uploadDate, softDeleted, content, actors);
    }
}
