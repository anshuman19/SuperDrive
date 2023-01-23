package com.udacity.jwdnd.spring_security_basics.model;

public class CredentialForm {

    private String credentialId;
    private String password;
    private String url;
    private String username;

    public CredentialForm(String credentialId, String password, String url, String username) {
        this.credentialId = credentialId;
        this.password = password;
        this.url = url;
        this.username = username;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
