package ybe.mini.travelserver.domain.room.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SuperBuilder
@Comment("객실")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("객실 방 호수")
    private Long id;

    @Comment("객실 타입 id(api 객실 타입 키 동일)")
    private Long roomTypeId;

    @Comment("객실 이름")
    private String name;

    @Comment("객실 설명")
    private String description;

    @Comment("객실 가격")
    private Integer price;

    @Comment("객실 이미지")
    private String image;

    @Comment("객실 수(api 값)")
    private Integer stock;

    @Comment("객실 수용 인원")
    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    @Comment("숙박 시설 번호(FK)")
    private Accommodation accommodation;
}
