package com.example.sprint.jwt.user_principal;

import com.example.sprint.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountPrincipal implements UserDetails {
    private Integer id;
    private String name;
    private String email;
    @JsonIgnore
    private String encryptPassword;
    private String avatar;
    private Collection<? extends GrantedAuthority> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public static AccountPrincipal build(Account account) {// build account trên hệ thống
        List<GrantedAuthority> authorities = account.getRoleSet().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new AccountPrincipal(
                account.getId(),
                account.getName(),
                account.getEmail(),
                account.getEncryptPassword(),
                account.getAvatar(),
                authorities
        );
    }

    @Override
    public String getPassword() {
        return encryptPassword;
    }

    @Override
    public String getUsername() {
        return email;
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
}
