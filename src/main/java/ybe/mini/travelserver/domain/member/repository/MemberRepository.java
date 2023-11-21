package ybe.mini.travelserver.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ybe.mini.travelserver.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndPassword(String email, String password);

    Optional<Member> findByEmail(String email);
}
