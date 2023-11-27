package ybe.mini.travelserver.global.api.dto;

import ybe.mini.travelserver.global.api.dto.common.Response;

/**
 * <b>객실 정보 조회</b>
 * 추가 관광정보 상세내역을 조회한다.
 * 상세반복정보를 안내URL의 국문관광정보 상세 매뉴얼 문서를 참고하시기 바랍니다.
 */
public record RoomTourAPIResponse(Response<Item> response) {
    public record Item(
            String contentid,//콘텐츠ID
            String contenttypeid,//콘텐츠타입ID
            String roomcode,//객실코드
            String roomtitle,//객실명칭
            String roomsize1,//객실크기(평)
            String roomcount,//객실수
            String roombasecount,//기준인원
            String roommaxcount,//최대인원
            String roomoffseasonminfee1,//비수기주중최소
            String roomoffseasonminfee2,//비수기주말최소
            String roompeakseasonminfee1,//성수기주중최소
            String roompeakseasonminfee2,//성수기주말최소
            String roomintro,//객실소개
            String roombathfacility,//목욕시설여부
            String roombath,//욕조여부
            String roomhometheater,//홈시어터여부
            String roomaircondition,//에어컨여부
            String roomtv,//TV 여부
            String roompc,//PC 여부
            String roomcable,//케이블설치여부
            String roominternet,//인터넷여부
            String roomrefrigerator,//냉장고여부
            String roomtoiletries,//세면도구여부
            String roomsofa,//소파여부
            String roomcook,//취사용품여부
            String roomtable,//테이블여부
            String roomhairdryer,//드라이기여부
            String roomsize2,//객실크기(평방미터)
            String roomimg1,//객실사진1
            String roomimg1alt,//객실사진1 설명
            String cpyrhtDivCd1,//객실사진 1 저작권 유형 (Type1:제1유형(출처표시-권장), Type3:제3유형(제1유형+변경금지)
            String roomimg2,//객실사진2
            String roomimg2alt,//객실사진2 설명
            String cpyrhtDivCd2,//객실사진 2 저작권 유형 (Type1:제1유형(출처표시-권장), Type3:제3유형(제1유형+변경금지)
            String roomimg3,//객실사진3
            String roomimg3alt,//객실사진3 설명
            String cpyrhtDivCd3,//객실사진 3 저작권 유형 (Type1:제1유형(출처표시-권장), Type3:제3유형(제1유형+변경금지)
            String roomimg4,//객실사진4
            String roomimg4alt,//객실사진4 설명
            String cpyrhtDivCd4,//객실사진 4 저작권 유형 (Type1:제1유형(출처표시-권장), Type3:제3유형(제1유형+변경금지)
            String roomimg5,//객실사진5
            String roomimg5alt,//객실사진5 설명
            String cpyrhtDivCd5//객실사진 5 저작권 유형 (Type1:제1유형(출처표시-권장), Type3:제3유형(제1유형+변경금지)
    ) {
    }
}
