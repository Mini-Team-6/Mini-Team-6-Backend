package ybe.mini.travelserver.domain.room.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ybe.mini.travelserver.domain.room.DummyObjectForService;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest extends DummyObjectForService {

    @InjectMocks
    RoomService roomService;
    @Mock
    RoomRepository roomRepository;

    @DisplayName("객실 상세 조회")
    @Test
    void bringRoom_success() {
        // given
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.of(dummyRoom(dummyAccommodation())));

        // when
        var actual = roomService.bringRoom(1L);

        // then
        var expected = dummyRoomGetResponse();
        Assertions.assertEquals(expected, actual);
        then(roomRepository).should().findById(anyLong());
    }

}