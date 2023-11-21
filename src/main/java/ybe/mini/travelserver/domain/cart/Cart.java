package ybe.mini.travelserver.domain.cart;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.format.annotation.DateTimeFormat;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.room.Room;
import ybe.mini.travelserver.global.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Comment("장바구니")
public class Cart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("장바구니 아이디")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("회원 번호")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @Comment("방 번호")
    private Room room;

    @Comment("객실 체크인")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkIn;

    @Comment("객실 체크아웃")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkOut;

    @Comment("숙박 인원")
    private Integer guestNumber;
}
