package ybe.mini.travelserver.domain.cart.dummy;

import ybe.mini.travelserver.domain.cart.entity.Cart;
import ybe.mini.travelserver.domain.member.dummy.DummyMember;
import ybe.mini.travelserver.domain.room.DummyObjectForRoom;

import java.time.LocalDate;

public interface DummyCart extends DummyMember, DummyObjectForRoom {
    default Cart dummyCart() {
        return Cart.builder()
                .id(1L)
                .member(dummyMember())
                .checkIn(LocalDate.of(2023, 12, 29))
                .checkOut(LocalDate.of(2023, 12, 30))
                .room(dummyRoom(dummyAccommodation()))
                .guestNumber(2)
                .build();
    }

}
