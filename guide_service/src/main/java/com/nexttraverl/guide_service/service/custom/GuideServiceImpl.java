package com.nexttraverl.guide_service.service.custom;

import com.nexttraverl.guide_service.dto.GuideDTO;
import com.nexttraverl.guide_service.entity.Guide;
import com.nexttraverl.guide_service.repository.GuideRepository;
import com.nexttraverl.guide_service.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {
    private final GuideRepository guideRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean save(GuideDTO guideDTO) {
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

    @Override
    public String getOnGoingGuideId() {
        String lastId = guideRepository.getLastGuideId();
        if(lastId==null) return "G00001";
        String[] split = lastId.split("[G]");
//        System.out.println("split: " + split[1]);
        int lastDigits = Integer.parseInt(split[1]);
        lastDigits++;
        return (String.format("G%05d", lastDigits));
    }


}
