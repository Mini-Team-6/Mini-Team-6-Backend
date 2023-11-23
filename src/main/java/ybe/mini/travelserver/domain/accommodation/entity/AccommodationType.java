package ybe.mini.travelserver.domain.accommodation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccommodationType {
    TOURIST_HOTEL("관광호텔"),
    CONDOMINIUM("콘도미니엄"),
    YOUTH_HOSTEL("유스호스텔"),
    PENSION("펜션"),
    MOTEL("모텔"),
    MINBAK("민박"),
    GUESTHOUSE("게스트하우스"),
    HOMESTAY("홈스테이"),
    SERVICED_RESIDENCE("서비스드레지던스"),
    HANOK("한옥");

    private final String name;
}
