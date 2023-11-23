package ybe.mini.travelserver.global.api.dto;

import ybe.mini.travelserver.global.api.dto.common.Response;

public record AccommodationDetailResponse(Response<Item> response) {
    public record Item(
            String contentid,
            String contenttypeid,
            String goodstay,
            String benikia,
            String hanok,
            String roomcount,
            String roomtype,
            String refundregulation,
            String checkintime,
            String checkouttime,
            String chkcooking,
            String seminar,
            String sports,
            String sauna,
            String beauty,
            String beverage,
            String karaoke,
            String barbecue,
            String campfire,
            String bicycle,
            String fitness,
            String publicpc,
            String publicbath,
            String subfacility,
            String foodplace,
            String reservationurl,
            String pickup,
            String infocenterlodging,
            String parkinglodging,
            String reservationlodging,
            String scalelodging,
            String accomcountlodging
    ) {
    }
}
