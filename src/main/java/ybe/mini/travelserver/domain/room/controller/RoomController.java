package ybe.mini.travelserver.domain.room.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponseFromAPI;
import ybe.mini.travelserver.domain.room.service.RoomService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{accommodationId}")
    public ResponseDto<List<RoomGetResponseFromAPI>> getRooms(
            @PathVariable Long accommodationId,
            @RequestParam(value = "check-in") String checkIn,
            @RequestParam(value = "check-out") String checkOut
    ) {
        List<RoomGetResponseFromAPI> rooms = roomService.bringRoomsFromAPI(accommodationId, checkIn, checkOut);
        return new ResponseDto<>(HttpStatus.OK.value(), rooms);
    }
}
