package com.udacity.jwdnd.spring_security_basics.model;

public class Credential {

    private Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userid;
    //private String unencodedpassword;
    private String encodedPassword;

    public Credential(Integer credentialId, String url, String username, String key, String encodedPassword,
                      Integer userid) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        //this.password = password;
        this.encodedPassword = encodedPassword;
        this.userid = userid;
        //this.unencodedpassword = unencodedPassword;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }





    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

//    public String getUnencodedpassword() {
//        return unencodedpassword;
//    }
    public String getEncodedPassword() {
        return encodedPassword;
    }

//    public void setUnencodedpassword(String unencodedPassword) {
//        this.unencodedpassword = unencodedPassword;
//    }
    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
}