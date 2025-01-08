package com.recr.bot.recrbot.model.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.recr.bot.recrbot.model.entity.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "system_users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "username")
    private String username;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;
    @Column(name = "password")
    private String password;
    @Builder.Default
    @Column(name = "is_account_non_expired")
    boolean isAccountNonExpired = true;
    @Builder.Default
    @Column(name = "is_account_non_locked")
    boolean isAccountNonLocked = true;
    @Builder.Default
    @Column(name = "is_credentials_non_expired")
    boolean isCredentialsNonExpired = true;
    @Column(name = "mfa")
    @Builder.Default
    private boolean mfa = false;
    @Column(name = "totp_code")
    private String totpCode;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

}
