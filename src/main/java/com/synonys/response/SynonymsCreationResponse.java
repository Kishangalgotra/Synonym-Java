package com.synonys.response;

import java.util.List;

public class SynonymsCreationResponse {

    public String getActualKeyword() {
        return actualKeyword;
    }

    public void setActualKeyword(String actualKeyword) {
        this.actualKeyword = actualKeyword;
    }

    private String actualKeyword;
    private List<String> synonyms;

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

}
