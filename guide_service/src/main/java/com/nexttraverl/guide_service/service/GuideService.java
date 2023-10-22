package com.nexttraverl.guide_service.service;

import com.nexttraverl.guide_service.dto.GuideDTO;

import java.util.List;

public interface GuideService {
    boolean save(GuideDTO guideDTO);
    boolean existsByGuideId(String id);
    boolean deleteGuideById(String id);
    boolean updateGuideById(GuideDTO guideDTO);
    String getOnGoingGuideId();
    GuideDTO getGuideById(String id);

    List<GuideDTO> getAllGuides();
}
