package ybe.mini.travelserver.global.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.entity.Location;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.global.api.dto.DetailInfoResponse;
import ybe.mini.travelserver.global.api.dto.DetailIntroResponse;
import ybe.mini.travelserver.global.api.dto.SearchKeywordResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourAPIService {

    public Accommodation bringAccommodation(
            long accommodationId,
            String keyword
    ) {
        var accommodationDetailResponse = TourAPIUtils.bringAccommodationDetail(accommodationId);
        var accommodationsSimpleSearchResponse = TourAPIUtils.bringAccommodations(keyword);

        var detailItems = accommodationDetailResponse.response().body().items().item();
        var keywordItems = accommodationsSimpleSearchResponse.response().body().items().item();

        if (keywordItems.isEmpty() || detailItems.isEmpty()) {
            throw new IllegalArgumentException("숙소 정보가 없습니다.");
        }

        var keywordItem = keywordItems.get(0);
        var detailItem = detailItems.get(0);

        return generateAccommodation(
                accommodationId,
                keywordItem,
                detailItem
        );
    }

    public Accommodation bringAccommodation(String keyword) {
        var accommodationsSimpleSearchResponse = TourAPIUtils.bringAccommodations(keyword);

        var keywordItems = accommodationsSimpleSearchResponse.response().body().items().item();

        if (keywordItems.isEmpty()) {
            throw new IllegalArgumentException("숙소 정보가 없습니다.");
        }
        var keywordItem = keywordItems.get(0);

        long accommodationId = Long.parseLong(keywordItem.contentid());

        var accommodationDetailResponse = TourAPIUtils.bringAccommodationDetail(accommodationId);
        var detailItems = accommodationDetailResponse.response().body().items().item();
        var detailItem = detailItems.get(0);

        return generateAccommodation(
                accommodationId,
                keywordItem,
                detailItem
        );
    }

    public List<Room> bringRooms(long accommodationId) {
        DetailInfoResponse detailInfoResponse = TourAPIUtils.bringRoom(accommodationId);

        var roomItems = detailInfoResponse.response().body().items().item();

        if (roomItems.isEmpty()) {
            throw new IllegalArgumentException("객실 정보가 없습니다.");
        }

        List<Room> rooms = new ArrayList<>();
        for (var roomItem : roomItems) {
            Room room = Room.builder()
                    .roomTypeId(Long.valueOf(roomItem.roomcode()))
                    .name(roomItem.roomtitle())
                    .description(roomItem.roomintro())
                    .price(Integer.parseInt(roomItem.roomoffseasonminfee1()))
                    .image(roomItem.roomimg1())
                    .stock(Integer.parseInt(roomItem.roomcount()))
                    .capacity(Integer.parseInt(roomItem.roommaxcount()))
                    .accommodation(
                            Accommodation.builder()
                                    .id(accommodationId)
                                    .build()
                    )
                    .build();

            rooms.add(room);
        }

        return rooms;
    }

    public List<Accommodation> bringAccommodationsForSearch(
            int pageNo,
            int numOfRows,
            String keyword,
            String areaCode
    ) {
        var accommodationsSimpleSearchResponse = TourAPIUtils.bringAccommodations(
                pageNo,
                numOfRows,
                keyword,
                areaCode
        );

        var searchedItems = accommodationsSimpleSearchResponse.response().body().items().item();

        if (searchedItems.isEmpty()) {
            throw new IllegalArgumentException("숙소 정보가 없습니다.");
        }

        List<Accommodation> accommodations = new ArrayList<>();

        for (var item : searchedItems) {
            long accommodationId = Long.parseLong(item.contentid());

            accommodations.add(
                    generateAccommodation(
                            accommodationId,
                            item,
                            null
                    )
            );
        }

        return accommodations;
    }

    private static Accommodation generateAccommodation(
            long accommodationId,
            SearchKeywordResponse.Item keywordItem,
            DetailIntroResponse.Item detailItem
    ) {
        String subfacility = Optional.ofNullable(detailItem)
                .map(DetailIntroResponse.Item::subfacility)
                .orElse("");

        return Accommodation.builder()
                .id(accommodationId)
                .name(keywordItem.title())
                .image(keywordItem.firstimage())
                .description(subfacility)
                .location(Location.builder()
                        .address(keywordItem.addr1())
                        .latitude(Double.valueOf(keywordItem.mapy()))
                        .longitude(Double.valueOf(keywordItem.mapx()))
                        .phone(keywordItem.tel())
                        .areaCode(keywordItem.areacode())
                        .build())
                .build();
    }
}