package ybe.mini.travelserver.domain.accommodation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationAndRoomResponse;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.service.RoomService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final RoomService roomService;

    @Transactional(readOnly = true)
    public List<AccommodationGetResponse> bringAccommodations(String keyword, String areaCode) {
        List<Accommodation> accommodations;

        if (keyword != null || areaCode != null) {
            if (keyword != null && areaCode != null) {
                accommodations = accommodationRepository.findByNameContainingAndLocationAreaCode(keyword, areaCode);
            } else if (keyword != null) {
                accommodations = accommodationRepository.findByNameContaining(keyword);
            } else {
                accommodations = accommodationRepository.findByLocationAreaCode(areaCode);
            }
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

