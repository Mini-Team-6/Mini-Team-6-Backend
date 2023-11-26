package ybe.mini.travelserver.domain.accommodation.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Embeddable
@SuperBuilder
public class Location {
    @Comment("숙박 시설 주소")
    private String address;

    @Comment("숙박 시설 전화번호")
    private String phone;

    @Comment("숙박 시설 지역 코드")
    private String areaCode;

    @Comment("숙박 시설 위도")
    private Double latitude;

    @Comment("숙박 시설 경도")
    private Double longitude;
}
