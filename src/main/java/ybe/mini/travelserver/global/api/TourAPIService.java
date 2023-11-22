package ybe.mini.travelserver.global.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ybe.mini.travelserver.domain.accommodation.Location;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.room.entity.Room;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourAPIService {
    // 숙박(accommodation) 객체에 매핑 후 db에 저장
    private final AccommodationRepository accommodationRepository;
    private final RoomRepository roomRepository;

    private static final Random random = new Random();

    private void saveRoom(List<Long> accommodationIds) {
        for (var accommodationId : accommodationIds) {
            var roomItems = TourAPIUtils.bringRoom(accommodationId).response().body().items().item();
            int randomPrice = (random.nextInt() * 45) * 10000 + 50000;
            int randomStock = (random.nextInt() * 10) + 1;
            List<Room> rooms = new ArrayList<>();

            for (var roomItem : roomItems) {
                rooms.add(
                        Room.builder()
                                .id(Long.parseLong(roomItem.roomcode()))
                                .name(roomItem.roomtitle())
                                .description(roomItem.roomintro())
                                .price(randomPrice)
                                .image(roomItem.roomimg1())
                                .stock(randomStock)
                                .capacity(Integer.parseInt(roomItem.roommaxcount()))
                                .accommodation(Accommodation.builder().id(accommodationId).build())
                                .build()
                );
            }

            roomRepository.saveAll(rooms);
        }
    }

    public void saveAccommodationsToDBFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        List<Accommodation> accommodations = new ArrayList<>();

        try {
            JsonNode rootNode = mapper.readTree(new File("dummy/searchStay1.json"));
            JsonNode responseNode = rootNode.get("response");
            JsonNode bodyNode = responseNode.get("body");
            JsonNode itemsNode = bodyNode.get("items");
            JsonNode itemNode = itemsNode.get("item");

            int cnt = 1;
            if (itemNode.isArray()) {
                for (JsonNode item : itemNode) {
                    ObjectNode object = (ObjectNode) item;
                    long contentId = object.get("contentid").asLong();

                    Location location = Location.builder()
                            .address(object.get("addr1").asText(""))
                            .phone(object.get("tel").asText(""))
                            .areaCode(object.get("areacode").asText(""))
                            .latitude(object.get("mapy").asDouble())
                            .longitude(object.get("mapx").asDouble())
                            .build();
                    accommodations.add(
                            Accommodation.builder()
                                    .id(contentId)
                                    .name(item.get("title").asText(""))
                                    .location(location)
                                    .image(item.get("firstimage").asText(""))
                                    .description(item.get("description").asText(""))
                                    .build()
                    );

                    log.warn(cnt++ + "번째 숙소 저장 완료");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽어오는데 실패했습니다.");
        }

        accommodationRepository.saveAll(accommodations);
    }

}
