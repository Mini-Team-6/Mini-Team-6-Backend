package ybe.mini.travelserver.domain.room.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.service.RoomService;
import ybe.mini.travelserver.global.common.ResponseDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/temp/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{roomId}")
    public ResponseDto<RoomGetResponse> bringRoom(
            @PathVariable Long roomId
    ) {
        RoomGetResponse room = roomService.bringRoom(roomId);
        return new ResponseDto<>(HttpStatus.OK.value(), room);
    }

}