package ybe.mini.travelserver.domain.room.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.service.RoomService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/")
    public ResponseDto<List<RoomGetResponse>> bringRooms(
            @PathVariable Long accommodationId
    ) {
        List<RoomGetResponse> rooms = roomService.bringRooms(accommodationId);
        return new ResponseDto<>(HttpStatus.OK.value(), rooms);
    }

    @GetMapping("{roomId}")
    public ResponseDto<RoomGetResponse> bringRoom(
            @PathVariable Long roomId
    ) {
        RoomGetResponse room = roomService.bringRoom(roomId);
        return new ResponseDto<>(HttpStatus.OK.value(), room);
    }

}