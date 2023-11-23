package ybe.mini.travelserver.domain.room.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.accommodation.Location;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.entity.AccommodationType;
import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RoomServiceTest {

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AccommodationRepository accommodationRepository;

    @Test
    void GetAllRoomByAccommodation_success() {
        // given
        Accommodation accommodation = createAccommodation();
        Room room = createRoom(accommodation);

        // when
        List<RoomGetResponse> roomGetResponseList =
                roomService.getAllRoomByAccommodation(accommodation.getId());

        // then
        assertThat(roomGetResponseList.get(0).capacity()).isEqualTo(room.getCapacity());
        assertThat(roomGetResponseList.get(0).description()).isEqualTo(room.getDescription());
        assertThat(roomGetResponseList.get(0).image()).isEqualTo(room.getImage());
        assertThat(roomGetResponseList.get(0).name()).isEqualTo(room.getName());
        assertThat(roomGetResponseList.get(0).price()).isEqualTo(room.getPrice());
        assertThat(roomGetResponseList.get(0).roomTypeId()).isEqualTo(room.getRoomTypeId());
        assertThat(roomGetResponseList.get(0).stock()).isEqualTo(room.getStock());

        assertThat(accommodation.getAccommodationType()).isEqualTo(AccommodationType.TOURIST_HOTEL);
        assertThat(accommodation.getName()).isEqualTo("세인트존스 호텔");
        assertThat(accommodation.getLocation().getAddress()).isEqualTo("강원특별자치도 강릉시 창해로 307 ");
        assertThat(accommodation.getLocation().getPhone()).isEqualTo("033-660-9000");
        assertThat(accommodation.getLocation().getAreaCode()).isEqualTo("32");
        assertThat(accommodation.getLocation().getLatitude()).isEqualTo(37.7940780970);
        assertThat(accommodation.getLocation().getLongitude()).isEqualTo(128.9186301059);
        assertThat(accommodation.getImage())
                .isEqualTo("http://tong.visitkorea.or.kr/cms/resource/54/2603354_image2_1.jpg");
        assertThat(accommodation.getDescription())
                .isEqualTo("푸른 해송숲과 청정 바다가 펼쳐진 강문해변에서의 자연 휴양과...");


    }

    private Room createRoom(Accommodation accommodation) {
        Room room = roomRepository.save(Room.builder()
                .capacity(2)
                .description("객실 설명 1")
                .image("이미지 1")
                .name("객실 이름 1")
                .price(100000)
                .roomTypeId(1L)
                .stock(50)
                .accommodation(accommodation)
                .build());
        return room;
    }

    private Accommodation createAccommodation() {
        Accommodation accommodation = accommodationRepository.save(Accommodation.builder()
                .accommodationType(AccommodationType.TOURIST_HOTEL)
                .name("세인트존스 호텔")
                .location(Location.builder()
                        .address("강원특별자치도 강릉시 창해로 307 ")
                        .phone("033-660-9000")
                        .areaCode("32")
                        .latitude(37.7940780970)
                        .longitude(128.9186301059)
                        .build())
                .image("http://tong.visitkorea.or.kr/cms/resource/54/2603354_image2_1.jpg")
                .description("푸른 해송숲과 청정 바다가 펼쳐진 강문해변에서의 자연 휴양과...")
                .build());
        return accommodation;
    }
}