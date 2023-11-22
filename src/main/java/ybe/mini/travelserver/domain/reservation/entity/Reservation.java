package ybe.mini.travelserver.domain.reservation.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus;
import ybe.mini.travelserver.global.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

import static ybe.mini.travelserver.domain.reservation.entity.ReservationStatus.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("회원 번호 FK")
    private Member member;

    @OneToMany(
            mappedBy = "reservation",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<ReservationRoom> reservationRooms = new ArrayList<>();

    @Comment("예약 상태")
    private ReservationStatus status;

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public void addReservationRoom(ReservationRoom reservationRoom) {
        reservationRooms.add(reservationRoom);
        reservationRoom.setReservation(this);
    }

    public void updateStatusToPaySuccess() {
        status = PAYED_SUCCESS;
    }

    public static Reservation createReservation(
            Member member,
            List<ReservationRoom> reservationRooms
    ) {

        Reservation reservation = Reservation.builder()
                .member(member)
                .status(PAYED_BEFORE)
                .build();
        reservationRooms.forEach(reservation::addReservationRoom);

        return reservation;
    }

    public void cancelReservation() {
        if(status.equals(PAYED_BEFORE)) {
            this.setStatus(CANCELED);
        } else if(status.equals(PAYED_SUCCESS)) {
            this.setStatus(REFUND);
        }
        reservationRooms.forEach(room -> room.setStatus(ReservationRoomStatus.CANCELED));
    }

    public Integer getTotalPrice() {
        return reservationRooms.stream().mapToInt(room -> room.getRoom().getPrice()).sum();
    }

}
