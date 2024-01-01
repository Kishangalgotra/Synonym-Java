package com.synonyms.model;


import java.io.Serializable;
import java.math.BigDecimal;
import com.synonyms.enums.UserRole;


public class UserModel implements Serializable {

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    private String emailId;

    private UserRole role;

}
