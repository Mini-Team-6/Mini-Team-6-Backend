package ybe.mini.travelserver.global.api.dto;

import ybe.mini.travelserver.global.api.dto.common.Response;

public record AccommodationSimplePagenationResponse(Response<Item> response) {
    public record Item(
            String addr1,
            String addr2,
            String areacode,
            String benikia,
            String cat1,
            String cat2,
            String cat3,
            String contentid,
            String contenttypeid,
            String createdtime,
            String firstimage,
            String firstimage2,
            String cpyrhtDivCd,
            String goodstay,
            String hanok,
            String mapx,
            String mapy,
            String mlevel,
            String modifiedtime,
            String tel,
            String title,
            String booktour,
            String sigungucode
    ) {
    }
}
