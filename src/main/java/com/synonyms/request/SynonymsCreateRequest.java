package com.synonyms.request;

import java.io.Serializable;
import java.util.List;

public class SynonymsCreateRequest implements Serializable {

    private String actualKeyword;
    private List<String> synonyms;

    public String getActualKeyword() {
        return actualKeyword.trim();
    }

    public void setActualKeyword(String actualKeyword) {
        this.actualKeyword = actualKeyword;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }
}
