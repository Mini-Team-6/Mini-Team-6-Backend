package ybe.mini.travelserver.domain.room.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ybe.mini.travelserver.domain.room.DummyObjectForRepository;
import ybe.mini.travelserver.domain.room.entity.Room;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RoomRepositoryTest extends DummyObjectForRepository {

    @Mock
    RoomRepository roomRepository;

    @DisplayName("객실 단건 조회")
    @Test
    void findById_success() {
        // given
        Long roomId = 1L;
        Room expectedRoom = dummyRoom(dummyAccommodation());

        // when
        given(roomRepository.findById(roomId)).willReturn(Optional.of(expectedRoom));
        Optional<Room> actualRoom = roomRepository.findById(roomId);

        // then
        assertTrue(actualRoom.isPresent());
        Room actual = actualRoom.get();
        assertEquals(expectedRoom, actual);
        then(roomRepository).should(times(1)).findById(roomId);
    }

    @DisplayName("객실 단건 조회 - 존재하지 않는 경우")
    @Test
    void findById_notFound() {
        // given
        Long roomId = 2L;

        // when
        given(roomRepository.findById(roomId)).willReturn(Optional.empty());
        Optional<Room> actualRoom = roomRepository.findById(roomId);

        // then
        assertTrue(actualRoom.isEmpty());
        then(roomRepository).should().findById(roomId);
    }
}