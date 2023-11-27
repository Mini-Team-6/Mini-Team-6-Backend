package ybe.mini.travelserver.global.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.entity.Location;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.global.api.dto.AccommodationTourAPIResponse;
import ybe.mini.travelserver.global.api.dto.RoomTourAPIResponse;
import ybe.mini.travelserver.global.exception.api.NoAccommodationsFromAPIException;
import ybe.mini.travelserver.global.exception.api.NoRoomsFromAPIException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourAPIService {
    public Accommodation bringAccommodation(
            String keyword,
            String code
    ) {
        var accommodationsSimpleSearchResponse = TourAPIUtils.bringAccommodation(keyword, code);

        var body = accommodationsSimpleSearchResponse.response().body();

        if (body.totalCount() == 0) {
            throw new NoAccommodationsFromAPIException();
        }

        var items = body.items().item();


        var item = items.get(0);

        long accommodationId = Long.parseLong(item.contentid());

        return generateAccommodation(
                accommodationId,
                item
        );
    }


    public List<Accommodation> bringAccommodations(
            int pageNo,
            int numOfRows,
            String keyword,
            String code
    ) {
        var accommodationsSimpleSearchResponse = TourAPIUtils.bringAccommodations(
                pageNo,
                numOfRows,
                keyword,
                code
        );

        var body = accommodationsSimpleSearchResponse.response().body();

        if (body.totalCount() == 0) {
            throw new NoAccommodationsFromAPIException();
        }
        var items = body.items().item();

        List<Accommodation> accommodations = new ArrayList<>();

        for (var item : items) {
            long accommodationId = Long.parseLong(item.contentid());

            accommodations.add(
                    generateAccommodation(
                            accommodationId,
                            item
                    )
            );
        }

        return accommodations;
    }

    private static Accommodation generateAccommodation(
            long accommodationId,
            AccommodationTourAPIResponse.Item keywordItem
    ) {
        return Accommodation.builder()
                .id(accommodationId)
                .name(keywordItem.title())
                .image(keywordItem.firstimage())
                .location(Location.builder()
                        .address(keywordItem.addr1())
                        .latitude(Double.valueOf(keywordItem.mapy()))
                        .longitude(Double.valueOf(keywordItem.mapx()))
                        .phone(keywordItem.tel())
                        .areaCode(keywordItem.areacode())
                        .build())
                .build();
    }

    public List<Room> bringRooms(long accommodationId) {
        RoomTourAPIResponse roomTourAPIResponse = TourAPIUtils.bringRoom(accommodationId);

        var body = roomTourAPIResponse.response().body();

        if (body.totalCount() == 0) {
            throw new NoRoomsFromAPIException();
        }

        var items = body.items().item();


        List<Room> rooms = new ArrayList<>();
        for (var item : items) {
            Room room = Room.builder()
                    .roomTypeId(Long.valueOf(item.roomcode()))
                    .name(item.roomtitle())
                    .description(item.roomintro())
                    .price(Integer.parseInt(item.roomoffseasonminfee1()))
                    .image(item.roomimg1())
                    .stock(Integer.parseInt(item.roomcount()))
                    .capacity(Integer.parseInt(item.roommaxcount()))
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

    public Room bringRoom(
            long accommodationId,
            long roomTypeId
    ) {
        List<Room> rooms = bringRooms(accommodationId);

        for (var room : rooms) {
            if (room.getRoomTypeId() == roomTypeId) {
                return room;
            }
        }

        throw new NoRoomsFromAPIException();
    }
}