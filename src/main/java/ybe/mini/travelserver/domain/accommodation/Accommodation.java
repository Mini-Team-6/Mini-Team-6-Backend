package ybe.mini.travelserver.domain.accommodation;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Comment("숙소")
public class Accommodation {
    @Id
    @Comment("숙소 아이디(api 숙소키와 동일)")
    private Long id;

    @Comment("숙소 카테고리")
    private AccommodationType accommodationType;

    @Comment("숙소 이름")
    private String name;

    @Embedded
    private Location location;

    @Comment("숙소 이미지")
    private String image;

    @Comment("숙소 설명")
    private String description;
}
