package ybe.mini.travelserver.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ybe.mini.travelserver.domain.cart.entity.Cart;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findALLByMemberEmail(String email);

}
