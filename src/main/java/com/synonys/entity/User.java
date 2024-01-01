package com.synonys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    private String password;

    @Column(name = "created_on")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
    private LocalDate createdOn;

    @Column(name = "modified_on")
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
    private LocalDate modifiedOn;

    private Boolean deletedOn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Synonyms.class)
    List<Synonyms> synonymsList;

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
