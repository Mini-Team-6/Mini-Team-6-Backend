package ybe.mini.travelserver.domain.room.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.service.RoomService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{accommodation_id}")
    public ResponseDto<List<RoomGetResponse>> getAllRoomByAccommodation(
            @PathVariable(name = "accommodation_id") Long accommodationId
    ) {
        List<RoomGetResponse> rooms = roomService.getAllRoomByAccommodation(accommodationId);
        return new ResponseDto<>(HttpStatus.OK.value(), rooms);
    }

}
