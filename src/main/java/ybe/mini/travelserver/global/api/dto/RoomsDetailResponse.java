package ybe.mini.travelserver.global.api.dto;

import ybe.mini.travelserver.global.api.dto.common.Response;

public record RoomsDetailResponse(Response<Item> response) {
    public record Item(
            String contentid,
            String contenttypeid,
            String roomcode,
            String roomtitle,
            String roomsize1,
            String roomcount,
            String roombasecount,
            String roommaxcount,
            String roomoffseasonminfee1,
            String roomoffseasonminfee2,
            String roompeakseasonminfee1,
            String roompeakseasonminfee2,
            String roomintro,
            String roombathfacility,
            String roombath,
            String roomhometheater,
            String roomaircondition,
            String roomtv,
            String roompc,
            String roomcable,
            String roominternet,
            String roomrefrigerator,
            String roomtoiletries,
            String roomsofa,
            String roomcook,
            String roomtable,
            String roomhairdryer,
            String roomsize2,
            String roomimg1,
            String roomimg1alt,
            String cpyrhtDivCd1,
            String roomimg2,
            String roomimg2alt,
            String cpyrhtDivCd2,
            String roomimg3,
            String roomimg3alt,
            String cpyrhtDivCd3,
            String roomimg4,
            String roomimg4alt,
            String cpyrhtDivCd4,
            String roomimg5,
            String roomimg5alt,
            String cpyrhtDivCd5
    ) {
    }
}
