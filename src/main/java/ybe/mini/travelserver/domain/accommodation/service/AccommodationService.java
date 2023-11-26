package ybe.mini.travelserver.domain.accommodation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationAndRoomResponse;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.service.RoomService;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final RoomService roomService;
    private final TourAPIService tourAPIService;

    public List<AccommodationGetResponse> bringAccommodationsFromAPI(
            int pageNo, int numOfRows,
            String keyword, String areaCode
    ) {
        List<Accommodation> accommodations =
                tourAPIService.bringAccommodationsForSearch(pageNo, numOfRows, keyword, areaCode);

        return getResponseList(accommodations);
    }

    public AccommodationAndRoomResponse bringAccommodationAndRoomsFromAPI(
            Long accommodationId,
            String keyword
    ) {
        Accommodation accommodation = tourAPIService.bringAccommodation(accommodationId, keyword);
        AccommodationGetResponse accommodationGetResponse = AccommodationGetResponse.fromEntity(accommodation);
        List<Room> rooms = tourAPIService.bringRooms(accommodationId);
        List<RoomGetResponse> roomGetResponseList =
                rooms.stream()
                        .map(RoomGetResponse::fromEntity)
                        .toList();
        return AccommodationAndRoomResponse.fromEntity(accommodationGetResponse, roomGetResponseList);
    }


    @Transactional(readOnly = true)
    public List<AccommodationGetResponse> bringAccommodations(String keyword, String areaCode) {
        List<Accommodation> accommodations;

        if (!Objects.isNull(keyword) && !Objects.isNull(areaCode)) {
            accommodations = accommodationRepository.findByNameContainingAndLocationAreaCode(keyword, areaCode);
        } else if (!Objects.isNull(keyword)) {
            accommodations = accommodationRepository.findByNameContaining(keyword);
        } else if (!Objects.isNull(areaCode)) {
            accommodations = accommodationRepository.findByLocationAreaCode(areaCode);
        } else {
            accommodations = accommodationRepository.findAll();
        }

        return getResponseList(accommodations);
    }

    @Transactional(readOnly = true)
    public AccommodationAndRoomResponse bringAccommodationAndRooms(Long accommodationId) {
        Accommodation accommodation = bringAccommodation(accommodationId);
        AccommodationGetResponse accommodationGetResponse = AccommodationGetResponse.fromEntity(accommodation);
        List<RoomGetResponse> roomGetResponseList = roomService.bringRooms(accommodationId);
        return AccommodationAndRoomResponse.fromEntity(accommodationGetResponse, roomGetResponseList);
    }


    private Accommodation bringAccommodation(Long accommodationId) {
        return accommodationRepository
                .findById(accommodationId).orElseThrow(RuntimeException::new);
    }

    private static List<AccommodationGetResponse> getResponseList(List<Accommodation> accommodations) {
        return accommodations.stream()
                .map(AccommodationGetResponse::fromEntity)
                .toList();
    }

}

