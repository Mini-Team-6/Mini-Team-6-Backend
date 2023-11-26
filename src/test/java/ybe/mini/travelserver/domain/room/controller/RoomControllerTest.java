package ybe.mini.travelserver.domain.room.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.domain.room.DummyObjectForController;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.service.RoomService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class RoomControllerTest extends DummyObjectForController {
    @Mock
    RoomService roomService;
    @InjectMocks
    RoomController roomController;

    @DisplayName("객실 상세 조회")
    @Test
    void bringRoom() {
        //given
        Long roomId = 1L;
        RoomGetResponse room = dummyRoomGetResponse();
        given(roomService.bringRoom(roomId)).willReturn(room);

        //when
        ResponseDto<RoomGetResponse> response = roomController.bringRoom(roomId);

        //then
        assertEquals(HttpStatus.OK.value(), response.status());
        assertEquals(room, response.data());
        then(roomService).should().bringRoom(roomId);
    }

}

