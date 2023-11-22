package ybe.mini.travelserver.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import ybe.mini.travelserver.global.entity.BaseTimeEntity;

import java.io.Serializable;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Comment("회원")
public class Member extends BaseTimeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원 아이디")
    private Long id;

    @Comment("회원 이메일")
    private String email;

    @Comment("회원 비밀번호")
    private String password;

    @Comment("회원 이름")
    private String name;

    public Member updateProfile(
            String email,
            String password,
            String name
    ) {
        if (!email.isBlank()) {
            this.email = email;
        }
        if (!name.isBlank()) {
            this.name = name;
        }
        if (!password.isBlank()) {
            this.password = password;
        }
        return this;
    }
}
