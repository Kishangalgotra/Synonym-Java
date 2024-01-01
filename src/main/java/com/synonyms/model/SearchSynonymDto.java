package com.synonyms.model;

public class SearchSynonymDto {

    private String synonym;
    private String synonymId;

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getSynonymId() {
        return synonymId;
    }

    public void setSynonymId(String synonymId) {
        this.synonymId = synonymId;
    }

    public SearchSynonymDto(String synonym, String synonymId) {
        this.synonym = synonym;
        this.synonymId = synonymId;
    }
}
