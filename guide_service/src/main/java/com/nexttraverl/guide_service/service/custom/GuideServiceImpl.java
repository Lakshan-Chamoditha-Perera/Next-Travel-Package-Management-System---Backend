package com.nexttraverl.guide_service.service.custom;

import com.nexttraverl.guide_service.repository.GuideRepository;
import com.nexttraverl.guide_service.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {
    private final GuideRepository guideRepository;

    @Override
    public boolean existsByGuideId(String guide_id) {
        return guideRepository.existsById(guide_id);
    }
}
