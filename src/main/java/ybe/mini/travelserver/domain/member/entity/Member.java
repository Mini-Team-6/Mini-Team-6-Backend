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
import ybe.mini.travelserver.domain.member.dto.MypageUpdateRequest;
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

    public void updateProfile(MypageUpdateRequest mypageUpdateRequest) {
        this.email = mypageUpdateRequest.email();
        this.password = mypageUpdateRequest.password();
        this.name = mypageUpdateRequest.name();
    }
}
