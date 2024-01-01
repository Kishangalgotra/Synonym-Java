package com.synonyms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.synonyms.enums.UserRole;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {

    public User() {
    }

    public User(String emailId, String uniqueId) {
        this.emailId = emailId;
        this.password = uniqueId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @CreationTimestamp
    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
    private LocalDate createdOn;

    @UpdateTimestamp
    @Column(name = "modified_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
    private LocalDate modifiedOn;

    private Boolean deleted;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUniqueId() {
        return password;
    }

    public void setUniqueId(String uniqueId) {
        this.password = uniqueId;
    }
}
