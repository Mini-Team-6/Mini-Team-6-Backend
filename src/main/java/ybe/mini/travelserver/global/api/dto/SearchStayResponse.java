package ybe.mini.travelserver.global.api.dto;

import ybe.mini.travelserver.global.api.dto.common.Response;

/**
 * <b>숙박 간단 정보 목록</b>
 * 숙박정보 검색목록을 조회한다. 컨텐츠 타입이 ‘숙박’일 경우에만 유효하다.
 */
public record SearchStayResponse(Response<Item> response) {
    public record Item(
            String addr1,//전체 주소
            String addr2,//부분 주소
            String areacode,//지역코드
            String benikia,//베니키아
            String cat1,
            String cat2,
            String cat3,
            String contentid,//숙박 ID
            String contenttypeid,//시설 ID (숙박 32)
            String createdtime,//등록일
            String firstimage,//원본 이미지
            String firstimage2,//썸네일
            String cpyrhtDivCd,//저작권자
            String goodstay,//굿스테이
            String hanok,//한옥
            String mapx,//경도
            String mapy,//위도
            String mlevel,//맵레벨
            String modifiedtime,//수정일
            String tel,//전화번호
            String title,//숙소명
            String booktour,//북투어
            String sigungucode//시군구 코드
    ) {
    }
}
