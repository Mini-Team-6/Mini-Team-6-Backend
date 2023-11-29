package ybe.mini.travelserver.domain.room.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.room.DummyObjectForControllerAndService;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponseFromAPI;
import ybe.mini.travelserver.domain.room.service.RoomService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class RoomControllerTest extends DummyObjectForControllerAndService {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @DisplayName("객실 리스트 조회 - Controller 테스트")
    @Test
    void getRooms_success() {
        // given
        Accommodation accommodation = dummyAccommodation();
        List<RoomGetResponseFromAPI> expectedRooms = Arrays.asList(
                RoomGetResponseFromAPI.fromEntity(dummyRoom(accommodation)),
                RoomGetResponseFromAPI.fromEntity(dummyRoom1(accommodation))
        );
        given(roomService.bringRoomsFromAPI(anyLong(), anyString(), anyString())).willReturn(expectedRooms);


        // when
        ResponseDto<List<RoomGetResponseFromAPI>> responseDto =
                roomController.getRooms(accommodation.getId(), "20240102", "20240202");

        // then
        assertNotNull(responseDto);
        assertEquals(HttpStatus.OK.value(), responseDto.status());
        assertEquals(expectedRooms, responseDto.data());
        then(roomService).should().bringRoomsFromAPI(anyLong(), anyString(), anyString());
    }

}
