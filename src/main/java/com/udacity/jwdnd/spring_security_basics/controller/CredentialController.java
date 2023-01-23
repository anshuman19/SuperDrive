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
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

@RequestMapping("/credentials")
@Controller
public class CredentialController {

    private Logger logger = LoggerFactory.getLogger(CredentialController.class);

    private final CredentialService credentialService;
    private final EncryptionService encryptionService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, EncryptionService encryptionService, UserService userService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    @PostMapping("submit-credential")
    public String submitCredentials(
            @ModelAttribute("newCredential") CredentialForm newCredential,
            Authentication authentication,
            Model model) {

        String username = (String) authentication.getName();
        String newUrl = newCredential.getUrl();
        String credentialIdStr = newCredential.getCredentialId();
        String password = newCredential.getPassword();
        System.out.println("submitCredential");
        System.out.println(username);
        System.out.println(newUrl);
        System.out.println(credentialIdStr);
        System.out.println(password);
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);


        if(credentialIdStr.isEmpty()){
            credentialService.addCredential(newUrl, username, newCredential.getUsername(), encodedKey,password, encryptedPassword);
        } else {
            Credential credentialExists = getCredential(Integer.parseInt(credentialIdStr));
            credentialService.update(credentialExists.getCredentialId(), newCredential.getUsername(), newUrl, encodedKey, encryptedPassword);
        }


        return "redirect:/result";

    }

    @GetMapping(value = "/view-credential/{credentialId}")
    public Credential getCredential(@PathVariable Integer credentialId){
        return credentialService.getCredential(credentialId);
    }



    @GetMapping(value = "/delete-credential/{credentialId}")
    public String deleteCredentials(@ModelAttribute("credentialStore") CredentialStore credentialStore,
                                    @RequestParam(required = false, name = "credentialId") Integer credentialId,
                                    Authentication authentication,
                                    Model model){

        Boolean isSuccess = credentialService.deleteCredentials(credentialId);

        return "redirect:/result?isSuccess=" + isSuccess;
    }
}
