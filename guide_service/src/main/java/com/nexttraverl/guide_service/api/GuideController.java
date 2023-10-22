package com.nexttraverl.guide_service.api;

import com.nexttraverl.guide_service.dto.GuideDTO;
import com.nexttraverl.guide_service.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/guide")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GuideController {
    public final GuideService guideService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(
            @RequestPart("nic_front") byte[] nic_front,
            @RequestPart("nic_back") byte[] nic_back,
            @RequestPart("guide_id_front") byte[] guide_id_front,
            @RequestPart("guide_id_back") byte[] guide_id_back,
            @RequestPart("profile") byte[] guide_img,
            @RequestPart("guide") GuideDTO guide) {

        System.out.println("GuideController -> " + guide);

        guide.getImages_list().add(guide_img);
        guide.getImages_list().add(nic_front);
        guide.getImages_list().add(nic_back);
        guide.getImages_list().add(guide_id_front);
        guide.getImages_list().add(guide_id_back);
        System.out.println("GuideController -> " + guide);
        try {
            validateGuideDetails(guide);
            System.out.println("validated");
            if (guideService.existsByGuideId(guide.getId())) {

                System.out.println("exists");
                return ResponseEntity.badRequest().body("Guide already exists!");
            }
            guideService.save(guide);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    private void validateGuideDetails(GuideDTO guide) {
        if (!Pattern.compile("^G\\d{3,}$").matcher(guide.getId()).matches())
            throw new RuntimeException("Invalid driver id");
        if (!Pattern.compile("^[a-zA-Z '-]+$").matcher(guide.getName()).matches())
            throw new RuntimeException("Invalid driver name");
        if (!Pattern.compile("^\\d{10}$").matcher(guide.getContact_number()).matches())
            throw new RuntimeException("Invalid driver contact number");
        if (!Pattern.compile("^(Male|Female)$").matcher(guide.getGender()).matches())
            throw new RuntimeException("Invalid gender type");
        if (!Pattern.compile("^[a-zA-Z0-9\\s]+$").matcher(guide.getAddress()).matches())
            throw new RuntimeException("Invalid address");
        try {
            if (!Pattern.compile("^\\d+$").matcher(String.valueOf(guide.getAge())).matches())
                throw new RuntimeException("Invalid seat capacity!");
            if (!Pattern.compile("^\\d+(\\.\\d+)?$").matcher(String.valueOf(guide.getMan_day_value())).matches())
                throw new RuntimeException("invalid price per day!");
        } catch (NumberFormatException e) {
            throw new RuntimeException("invalid price per day!");
        }
        guide.getImages_list().forEach((element) -> {
            if (element == null || element.length == 0)
                throw new RuntimeException("Invalid or empty image found in the list.");
        });
    }

    @GetMapping("/get/lastId")
    public ResponseEntity<?> getOngoingUserID() {
        System.out.println("get last id");
        String guideId = guideService.getOnGoingGuideId();
        return ResponseEntity.ok(guideId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        System.out.println("Guide Controller -> getAll");
        List<GuideDTO> allGuides = guideService.getAllGuides();
        System.out.println(allGuides.size());
        if (allGuides.size() == 0) return ResponseEntity.ok().body("");
        System.out.println("done");
        return ResponseEntity.ok().body(allGuides);

    }

    @PatchMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> patch(
            @RequestPart byte[] nic_front,
            @RequestPart byte[] nic_back,
            @RequestPart byte[] guide_id_front,
            @RequestPart byte[] guide_id_back,
            @RequestPart byte[] guide_img,
            @RequestPart("guide") GuideDTO guide) {
        guide.getImages_list().add(guide_img);
        guide.getImages_list().add(nic_front);
        guide.getImages_list().add(nic_back);
        guide.getImages_list().add(guide_id_front);
        guide.getImages_list().add(guide_id_back);
        try {
            validateGuideDetails(guide);
            System.out.println("validated");
            if (guideService.existsByGuideId(guide.getId())) {
                guideService.save(guide);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Guide not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteGuide(@RequestHeader("id") String id) {
        System.out.println("GuideController -> deleteGuide");
        if (!Pattern.compile("^G\\d{3,}$").matcher(id).matches())
            return ResponseEntity.badRequest().body("Invalid guide id");
        try {
            guideService.deleteGuideById(id);
            return ResponseEntity.ok().body("Guide deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getGuideById(@RequestHeader String id) {
        System.out.println("VehicleController -> getVehicleByVehicleID: " + id);
        Boolean isExists = guideService.existsByGuideId(id);
        if (!isExists) return ResponseEntity.badRequest().body("Guide not found !");
//        GuideDTO guide = guideService.getVehicleByVehicleId(vehicle_id);
        return ResponseEntity.ok(guide);
    }
}
