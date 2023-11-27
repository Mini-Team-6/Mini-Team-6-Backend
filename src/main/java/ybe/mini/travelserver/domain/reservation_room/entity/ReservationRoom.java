package ybe.mini.travelserver.domain.reservation_room.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.format.annotation.DateTimeFormat;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.room.entity.Room;

import java.time.LocalDateTime;

import static ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus.PAYED;
import static ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus.RESERVED;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @Comment("객실 번호")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    @Comment("예약 번호 FK")
    private Reservation reservation;

    @Comment("객실 체크인")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkIn;

    @Comment("객실 체크아웃")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkOut;

    @Enumerated(EnumType.STRING)
    @Comment("에약 상태")
    private ReservationRoomStatus status;

    @Comment("숙박 인원")
    private Integer guestNumber;

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void updateStatusToPayed() {
        status = PAYED;
    }

    public static ReservationRoom createReservationRoom(
            Room room, LocalDateTime checkIn, LocalDateTime checkOut, Integer guestNumber
    ) {
        return ReservationRoom.builder()
                .room(room)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .status(RESERVED)
                .guestNumber(guestNumber)
                .build();

    }

}
