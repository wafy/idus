package com.homework.idus.core.user;

import com.homework.idus.web.v1.user.signup.SignupRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 30)
    private String nickname;
    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false,length = 20)
    private String mobilePhoneNo;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 10)
    private Gender gender;


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    private User(String name,
                String nickname,
                String password,
                String mobilePhoneNo,
                String email,
                Gender gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.mobilePhoneNo = mobilePhoneNo;
        this.email = email;
        this.gender = gender;
    }

    public static User of(String name,
                          String nickname,
                          String password,
                          String mobilePhoneNo,
                          String email,
                          Gender gender) {
        return new User(name, nickname, password, mobilePhoneNo, email, gender);
    }

    public static User of(SignupRequest request) {
        return User.of(request.getName(),
                request.getNickname(),
                request.getPassword(),
                request.getMobilePhoneNo(),
                request.getEmail(),
                request.getGender());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isMatchesPassword(String val1) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(val1, this.password);
    }
}
