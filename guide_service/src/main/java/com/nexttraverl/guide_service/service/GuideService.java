package com.nexttraverl.guide_service.service;

import com.nexttraverl.guide_service.dto.GuideDTO;

public interface GuideService {
    boolean save(GuideDTO guideDTO);
    boolean existsByGuideId(String id);
    boolean deleteGuideById(String id);
    boolean updateGuideById(GuideDTO guideDTO);
    String getOnGoingGuideId();
}
