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
    public ResponseEntity<?> bringAccommodations(
            @RequestParam(required = false, defaultValue = "_") String keyword,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int numOfRows,
            @RequestParam(required = false) String areaCode
    ) {
        return ResponseEntity.ok(
                tourAPIService.bringAccommodationsForSearch(
                        pageNo,
                        numOfRows,
                        keyword,
                        areaCode
                )
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

}
