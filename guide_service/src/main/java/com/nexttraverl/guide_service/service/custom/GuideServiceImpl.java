package com.nexttraverl.guide_service.service.custom;

import com.nexttraverl.guide_service.dto.GuideDTO;
import com.nexttraverl.guide_service.entity.Guide;
import com.nexttraverl.guide_service.repository.GuideRepository;
import com.nexttraverl.guide_service.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {
    private final GuideRepository guideRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean save(GuideDTO guideDTO) {
        if (guideRepository.existsById(guideDTO.getId())) {
            throw new RuntimeException("Guide already exists");
        }
        Guide guide = modelMapper.map(guideDTO, Guide.class);
        guideRepository.save(guide);
        return true;
    }


    @Override
    public boolean existsByGuideId(String guide_id) {
        return guideRepository.existsById(guide_id);
    }

    @Override
    public boolean deleteGuideById(String id) {
        if (guideRepository.existsById(id)) {
            guideRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Guide not found");
    }

    @Override
    public boolean updateGuideById(GuideDTO guideDTO) {
        if (guideRepository.existsById(guideDTO.getId())) {
            Guide guide = modelMapper.map(guideDTO, Guide.class);
            guideRepository.save(guide);
            return true;
        }
        throw new RuntimeException("Guide not found");
    }


}
