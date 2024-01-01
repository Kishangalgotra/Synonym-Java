package com.synonyms.response;

import java.util.HashSet;
import java.util.List;

public class SynonymsCreationResponse {

    public String getActualKeyword() {
        return actualKeyword;
    }

    public void setActualKeyword(String actualKeyword) {
        this.actualKeyword = actualKeyword;
    }

    private String actualKeyword;
    private HashSet<String> synonyms;

    public HashSet<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(HashSet<String> synonyms) {
        this.synonyms = synonyms;
    }

}
