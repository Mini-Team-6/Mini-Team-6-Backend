package ybe.mini.travelserver.global.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tour-api")
public class TourAPIController {
    TourAPIService tourAPIService;

    public TourAPIController(TourAPIService tourAPIService) {
        this.tourAPIService = tourAPIService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> bringAccommodationsByKeyword(
            @RequestParam String keyword
    ) {
        return ResponseEntity.ok(
                TourAPIUtils.bringAccommodationByKeyword(keyword)
        );
    }

    @GetMapping("/accommodation/{contentId}")
    public ResponseEntity<?> bringAccommodation(@PathVariable long contentId) {
        return ResponseEntity.ok(
                TourAPIUtils.bringAccommodationDetail(contentId)
        );
    }

    @GetMapping("/accommodation/{contentId}/rooms")
    public ResponseEntity<?> bringRooms(@PathVariable long contentId) {
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
}
