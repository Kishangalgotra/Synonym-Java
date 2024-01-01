package com.synonys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.synonys.convertor.StringAttributeConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "synonyms")
public class Synonyms implements Serializable {

    public Synonyms() {
    }

    public Synonyms(String synonyms, User user) {
        this.synonyms = synonyms;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Convert(converter = StringAttributeConverter.class)
    private String synonyms;

    @JoinColumn(name = "user")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
    private LocalDate createdOn;

    @UpdateTimestamp
    @Column(name = "modified_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
    private LocalDate modifiedOn;

    private Boolean deletedOn;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }
}
