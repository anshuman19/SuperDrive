package com.udacity.jwdnd.spring_security_basics.controller;

import com.udacity.jwdnd.spring_security_basics.service.UserService;
import com.udacity.jwdnd.spring_security_basics.service.EncryptionService;
import com.udacity.jwdnd.spring_security_basics.service.CredentialService;
import com.udacity.jwdnd.spring_security_basics.mapper.UserMapper;
import com.udacity.jwdnd.spring_security_basics.model.*;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.SecureRandom;
import java.util.Base64;


@RequestMapping("/home/credentials")
@Controller
public class  CredentialController {
    private CredentialService credentialService;
    private UserMapper userMapper;

    public CredentialController(CredentialService credentialService, UserMapper userMapper) {
        this.credentialService = credentialService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public String handleAddUpdateCredentials(Authentication authentication, Credential credential){
        String loggedInUserName = (String) authentication.getPrincipal();
        User user = userMapper.getUser(loggedInUserName);
        Integer userId = user.getUserid();

        if (credential.getCredentialid() != null) {
            credentialService.editCredentials(credential);
        } else {
            credentialService.addCredentials(credential, userId);
        }

        return "redirect:/result?success";
    }

    @GetMapping("/delete")
    public String deleteCredentials(@RequestParam("id") int credentialid, Authentication authentication, RedirectAttributes redirectAttributes){
        String loggedInUserName = (String) authentication.getPrincipal();
        User user = userMapper.getUser(loggedInUserName);

        if(credentialid > 0){
            credentialService.deleteCredentials(credentialid);
            return "redirect:/result?success";
        }


        redirectAttributes.addAttribute("error", "Unable to delete the credentials.");
        return "redirect:/result?error";
    }
}
