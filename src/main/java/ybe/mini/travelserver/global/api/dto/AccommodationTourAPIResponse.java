package ybe.mini.travelserver.global.api.dto;

import ybe.mini.travelserver.global.api.dto.common.Response;

/**
 * <b>숙박 간단 정보 키워드 검색</b>
 * 키워드로 검색을하며 전체별 타입정보별 목록을 조회한다
 */
public record AccommodationTourAPIResponse(Response<Item> response) {
    public record Item(
            String addr1,
            String addr2,
            String areacode,
            String booktour,
            String cat1,
            String cat2,
            String cat3,
            String contentid,
            String contenttypeid,
            String createdtime,
            String firstimage,
            String firstimage2,
            String cpyrhtDivCd,
            String mapx,
            String mapy,
            String mlevel,
            String modifiedtime,
            String sigungucode,
            String tel,
            String title // 숙소명
    ) {
    }
}
