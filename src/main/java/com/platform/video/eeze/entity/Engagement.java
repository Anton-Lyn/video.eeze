package com.platform.video.eeze.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "engagements")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Engagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long engagementId;
    @OneToOne
    @JoinColumn(name = "video_id", referencedColumnName = "videoId")
    private Video video;
    @Builder.Default
    private Long impressions = 0L;
    @Builder.Default
    private Long views = 0L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engagement that = (Engagement) o;
        return Objects.equals(engagementId, that.engagementId) && Objects.equals(video, that.video)
            && Objects.equals(impressions, that.impressions) && Objects.equals(views, that.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engagementId, video, impressions, views);
    }
}

