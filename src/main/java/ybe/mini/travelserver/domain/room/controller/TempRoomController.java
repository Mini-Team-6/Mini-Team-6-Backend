package ybe.mini.travelserver.domain.room.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.global.common.ResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class TempRoomController {

    @GetMapping("/{roomId}")
    public ResponseDto<RoomGetResponse> bringRoom(
            @PathVariable Long roomId
    ) {
        Room room = Room.builder()
                .capacity(2)
                .description("객실 설명 1")
                .price(100000)
                .image("이미지 1")
                .name("객실 이름 1")
                .roomTypeId(1L)
                .stock(50)
                .build();

        RoomGetResponse roomGetResponse = RoomGetResponse.fromEntity(room);

        return new ResponseDto<>(HttpStatus.OK.value(), roomGetResponse);
    }

}
