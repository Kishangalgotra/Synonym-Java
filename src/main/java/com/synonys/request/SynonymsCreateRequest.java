package com.synonys.request;

import java.io.Serializable;

public class SynonymsCreateRequest implements Serializable {

    private String actualKeyword;
    private String synonymKeyword;

    public String getActualKeyword() {
        return actualKeyword;
    }

    public void setActualKeyword(String actualKeyword) {
        this.actualKeyword = actualKeyword;
    }

    public String getSynonymKeyword() {
        return synonymKeyword;
    }

    public void setSynonymKeyword(String synonymKeyword) {
        this.synonymKeyword = synonymKeyword;
    }
}
