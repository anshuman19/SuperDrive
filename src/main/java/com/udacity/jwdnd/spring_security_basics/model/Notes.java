package com.udacity.jwdnd.spring_security_basics.model;

public class Notes {
    private Integer noteid;
    private String notetitle;
    private String noteDescription;
    private Integer userid;

    public Notes(Integer noteid, String noteTitle, String noteDescription, Integer userid) {
        this.noteid = noteid;
        this.notetitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userid = userid;
    }

    public Notes(String noteTitle, String noteDescription) {
        this.notetitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    public Notes() {

    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer nodeid) {
        this.noteid = nodeid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String noteTitle) {
        this.notetitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}