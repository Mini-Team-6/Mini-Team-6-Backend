package ybe.mini.travelserver.global.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour-api")
public class TourAPIController {

    private final TourAPIService tourAPIService;

    @GetMapping("/search")
    public ResponseEntity<?> bringAccommodationsByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(
                TourAPIUtils.bringAccommodationByKeyword(keyword)
        );
    }

    @GetMapping("/accommodation/{contentId}")
    public ResponseEntity<?> bringAccommodation(@PathVariable int contentId) {
        return ResponseEntity.ok(
                TourAPIUtils.bringAccommodationDetail(contentId)
        );
    }

    @GetMapping("/accommodation/{contentId}/rooms")
    public ResponseEntity<?> bringRooms(@PathVariable int contentId) {
        return ResponseEntity.ok(
                TourAPIUtils.bringRoom(contentId)
        );
    }

    @GetMapping("/page/{pageNo}")
    public ResponseEntity<?> bringAccommodations(@PathVariable int pageNo) {
        int numOfRows = 10;
        return ResponseEntity.ok(
                TourAPIUtils.bringAccommodationByPagenation(pageNo, numOfRows)
        );
    }

    @GetMapping("/save")
    public ResponseEntity<?> saveAccommodations() {
        tourAPIService.saveAccommodationsToDBFromFile();
        return ResponseEntity.ok().build();
    }
}
