package ybe.mini.travelserver.domain.reservation.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.format.annotation.DateTimeFormat;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus;
import ybe.mini.travelserver.global.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Comment("예약")
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("예약 번호")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @Comment("회원 번호 FK")
    private Member member;

    @Comment("객실 체크인")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkIn;

    @Comment("객실 체크아웃")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkOut;

    @Comment("예약 상태")
    private ReservationRoomStatus reservationRoomStatus;

    @Comment("숙박 인원")
    private Integer guestNumber;

}
