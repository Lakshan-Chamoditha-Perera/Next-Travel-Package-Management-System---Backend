package com.nexttraverl.guide_service.service.custom;

import com.nexttraverl.guide_service.dto.GuideDTO;
import com.nexttraverl.guide_service.entity.Guide;
import com.nexttraverl.guide_service.repository.GuideRepository;
import com.nexttraverl.guide_service.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {
    private final GuideRepository guideRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean save(GuideDTO guideDTO) {
        Guide guide = modelMapper.map(guideDTO, Guide.class);
        guideDTO.getImages_list().forEach(ele -> {
            guide.getImages_list().add(ele);
        });
        System.out.println("Guide -> " + guide);
        guideRepository.save(guide);
        return true;
    }


    @Override
    public boolean existsByGuideId(String guide_id) {
        return guideRepository.existsById(guide_id);
    }

    @Override
    public boolean deleteGuideById(String id)throws RuntimeException  {
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
        List<Guide> lastInsertedUser = guideRepository.getLastGuideId();
//        System.out.println(lastInsertedUser);
        if (lastInsertedUser.isEmpty()) return "G00001";
//        System.out.println("last user id: " + lastInsertedUser.get(0).getUser_id());
        String lastId = lastInsertedUser.get(0).getId();
        String[] split = lastId.split("[G]");
//        System.out.println("split: " + split[1]);
        int lastDigits = Integer.parseInt(split[1]);
        lastDigits++;
        return (String.format("G%05d", lastDigits));
    }

    @Override
    public GuideDTO getGuideById(String id) {
        Guide guideById = guideRepository.getGuideById(id);
        return modelMapper.map(guideById, GuideDTO.class);
    }

    @Override
    public List<GuideDTO> getAllGuides() {
        System.out.println("GuideServiceImpl -> getAllGuides");
        List<Guide> guideList = guideRepository.findAll();
        List<GuideDTO> guideDTOList = new ArrayList<>();
        if (guideList.size() == 0) return guideDTOList;
        guideList.forEach(ele -> {
            guideDTOList.add(modelMapper.map(ele, GuideDTO.class));
        });
        return guideDTOList;
    }

}
