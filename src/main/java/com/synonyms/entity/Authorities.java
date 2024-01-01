package com.synonyms.entity;

import com.synonyms.enums.Authority;
import com.synonyms.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {

    private static final long serialVersionUID = -6349439417937226252L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "role")
    private String userRole;

    @NotNull
    @Column(name = "authority")
    private String authority;

    public Long getAuthorityId() {
        return id;
    }

    public void setAuthorityId(Long authorityId) {
        this.id = authorityId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Authorities() {
    }

    public Authorities(String userRole, Authority authority) {
        this.userRole = userRole;
        this.authority = authority.name();
    }
}