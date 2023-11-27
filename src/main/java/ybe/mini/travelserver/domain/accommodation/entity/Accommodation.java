package ybe.mini.travelserver.domain.accommodation.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Comment("숙소")
@SuperBuilder
public class Accommodation {
    @Id
    @Comment("숙소 아이디(api 숙소키와 동일)")
    private Long id;

    @Comment("숙소 이름")
    private String name;

    @Embedded
    private Location location;

    @Comment("숙소 이미지")
    private String image;

    @Comment("숙소 설명")
    private String description;
}
