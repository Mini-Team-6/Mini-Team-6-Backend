package ybe.mini.travelserver.domain.room.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.reservation_room.repository.ReservationRoomRepository;
import ybe.mini.travelserver.domain.room.DummyObjectForControllerAndService;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponseFromAPI;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest extends DummyObjectForControllerAndService {

    @Mock
    private TourAPIService tourAPIService;

    @Mock
    ReservationRoomRepository reservationRoomRepository;

    @Mock
    RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @DisplayName("객실 리스트 조회 - Service 테스트")
    @Test
    void getRooms_success() {
        // given
        Accommodation accommodation = dummyAccommodation();
        List<Room> expectedRooms = Arrays.asList(
                dummyRoom(accommodation), dummyRoom1(accommodation)
        );
        List<RoomGetResponseFromAPI> responseDto = expectedRooms.stream()
                .map(RoomGetResponseFromAPI::fromEntity)
                .toList();
        given(tourAPIService.bringRooms(anyLong())).willReturn(expectedRooms);

        // when
        List<RoomGetResponseFromAPI> actualRooms =
                roomService.bringRoomsFromAPI(accommodation.getId(), "20240101", "20240102");

        // then
        assertNotNull(actualRooms);
        assertEquals(responseDto.size(), actualRooms.size());
        assertEquals(responseDto, actualRooms);
        then(tourAPIService).should().bringRooms(eq(accommodation.getId()));

    }
}