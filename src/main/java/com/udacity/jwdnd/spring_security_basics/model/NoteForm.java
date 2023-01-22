package com.udacity.jwdnd.spring_security_basics.model;

import org.springframework.web.multipart.MultipartFile;

public class NoteForm {
    private String title;
    private String description;

    public Integer noteId;

    public Integer getnoteId() {
        return noteId;
    }

    public void setnoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}