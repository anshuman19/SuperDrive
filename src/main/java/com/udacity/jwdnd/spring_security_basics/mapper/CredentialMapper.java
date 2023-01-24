package com.udacity.jwdnd.spring_security_basics.mapper;

import com.udacity.jwdnd.spring_security_basics.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, key, encodedpassword, userid) " +
            "VALUES(#{url}, #{username}, #{key}, #{encodedPassword}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credential getCredentialBycredentialId(Integer credentialId);

    @Select("SELECT * FROM CREDENTIALS WHERE username = #{username}")
    Credential getCredentialByCredentialByUsername(String username);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credential> getAllCredentials(Integer userid);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Boolean delete(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, key = #{key}, encodedpassword = #{encodedPassword}, username = #{newUserName} WHERE credentialId = #{credentialId}")
    void update(Integer credentialId, String newUserName, String url, String key, String encodedPassword);
}
