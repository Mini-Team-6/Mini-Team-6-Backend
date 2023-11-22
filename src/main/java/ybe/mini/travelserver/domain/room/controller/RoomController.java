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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

    @GetMapping("/{accommodation_id}")
    public ResponseDto<List<RoomGetResponse>> getRoomByAccommodation(
            @PathVariable(name = "accommodation_id") Long accommodationId
    ) {
        Room room = Room.builder()
                .capacity(2)
                .description("객실 설명 1")
                .image("이미지 1")
                .name("객실 이름 1")
                .price(100000)
                .roomTypeId(1L)
                .stock(50)
                .build();

        Room room1 = Room.builder()
                .capacity(4)
                .description("객실 설명 2")
                .image("이미지 2")
                .name("객실 이름 2")
                .price(85000)
                .roomTypeId(2L)
                .stock(30)
                .build();

        RoomGetResponse roomGetResponse = RoomGetResponse.fromEntity(room);
        RoomGetResponse roomGetResponse1 = RoomGetResponse.fromEntity(room1);

        List<RoomGetResponse> responseList = new ArrayList<>();
        responseList.add(roomGetResponse);
        responseList.add(roomGetResponse1);

        return new ResponseDto<>(HttpStatus.OK.value(), responseList);
    }

}
