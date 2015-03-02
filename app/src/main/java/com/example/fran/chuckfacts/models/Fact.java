package com.example.fran.chuckfacts.models;

import java.io.Serializable;

/**
 * Created by Fran on 06/02/2015.
 */
public class Fact implements Serializable {

    private long codi;
    private String title;
    private String content;
    private String gif;

    /**
     * Constructor
     */
    public Fact() {
    }

    /**
     * Constructor amb paràmetres
     *
     * @param codi
     * @param title
     * @param content
     * @param gif
     */
    public Fact(long codi, String title, String content, String gif) {
        this.codi = codi;
        this.title = title;
        this.content = content;
        this.gif = gif;
    }

    /* Getter i Setters */
    public long getCodi() {
        return this.codi;
    }

    public void setCodi(long codi) {
        this.codi = codi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGif() {
        return gif;
    }

    public void setSetGif(String gif) {
        this.gif = gif;
    }
}
