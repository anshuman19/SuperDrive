package com.udacity.jwdnd.spring_security_basics.service;

import com.udacity.jwdnd.spring_security_basics.mapper.CredentialMapper;
import com.udacity.jwdnd.spring_security_basics.mapper.UserMapper;
import com.udacity.jwdnd.spring_security_basics.model.Credential;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;
    private UserMapper userMapper;


    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService, UserMapper userMapper) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.userMapper = userMapper;
    }

    public void addCredential(String url, String username, String credentialUserName,
                              String key, String encryptedPassword) {
        Integer userid = userMapper.getUser(username).getUserid();
        Credential credential = new Credential(0, url, credentialUserName, key, encryptedPassword, userid);
        credentialMapper.insert(credential);
    }

    public Credential getCredential(Integer credentialId){
        return credentialMapper.getCredentialBycredentialId(credentialId);
    }

    public List<Credential> getAllCredentials(int userid) {

        List<Credential> credentialList= credentialMapper.getAllCredentials(userid);
        //System.out.println("get all credential function");
        //System.out.println(userid);
        //System.out.println(credentialList);
        for (Credential credential : credentialList) {
            //System.out.println("credentialId: "+ credential.getCredentialId());
            //System.out.println("url: "+credential.getUrl());
            //System.out.println("key: "+credential.getKey());
            //System.out.println("password: " + credential.getEncodedPassword());
            //credential.setUnencodedpassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
            credential.setPassword(encryptionService.decryptValue(credential.getEncodedPassword(),credential.getKey()));
        }

        return credentialList;
    }

    public Boolean deleteCredentials (Integer credentialId){
        return credentialMapper.delete(credentialId);
    }

    public void update(Integer credentialId, String newUsername, String newUrl, String key, String encryptedPassword){
        credentialMapper.update(credentialId, newUsername, newUrl, key, encryptedPassword);
    }
}
