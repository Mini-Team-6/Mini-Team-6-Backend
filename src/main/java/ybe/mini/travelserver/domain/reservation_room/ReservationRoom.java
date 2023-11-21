package ybe.mini.travelserver.domain.reservation_room;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.format.annotation.DateTimeFormat;
import ybe.mini.travelserver.domain.reservation.Reservation;
import ybe.mini.travelserver.domain.room.Room;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Comment("예약 객실")
public class ReservationRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("예약 번호")
    private Long id;

    @OneToOne
    @JoinColumn(name = "room_id")
    @Comment("객실 번호")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    @Comment("예약 번호 FK")
    private Reservation reservation;

    @Comment("객실 체크인")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkIn;

    @Comment("객실 체크아웃")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkOut;

    @Comment("에약 상태")
    private ReservationRoomStatus reservationRoomStatus;

    @Comment("숙박 인원")
    private Integer guestNumber;
}
