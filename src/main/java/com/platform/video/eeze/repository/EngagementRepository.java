package com.platform.video.eeze.repository;

import com.platform.video.eeze.entity.Engagement;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EngagementRepository extends JpaRepository<Engagement, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Engagement e SET e.views = e.views + 1 WHERE e.video.videoId = :videoId")
    void incrementViewsForVideoByVideoId(@Param("videoId") Long videoId);

    @Modifying
    @Transactional
    @Query("UPDATE Engagement e SET e.impressions = e.impressions + 1 WHERE e.video.videoId = :videoId")
    void incrementImpressionsForVideoByVideoId(@Param("videoId") Long videoId);

    Engagement findByVideoVideoId(Long videoId);
}
