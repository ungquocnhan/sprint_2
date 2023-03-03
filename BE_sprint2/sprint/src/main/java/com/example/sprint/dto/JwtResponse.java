package com.example.sprint.dto;

import com.example.sprint.model.Customer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;

@Getter
@Setter
public class JwtResponse {
    private Integer id;
    private String token;
    private String type = "Bearer";
    private String name;
    private String avatar;
    private String email;
    private Optional<GetIdCustomer> idCustomer;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse() {
    }

    public JwtResponse(Integer id, String token, String type, String name, String avatar, String email, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(String token, String name, Collection<? extends GrantedAuthority> authorities, Integer id, String email, String avatar, Optional<GetIdCustomer> idCustomer) {
        this.token = token;
        this.name = name;
        this.roles = authorities;
        this.id = id;
        this.email = email;
        this.avatar = avatar;
        this.idCustomer = idCustomer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
