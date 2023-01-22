package com.udacity.jwdnd.spring_security_basics.controller;

import com.udacity.jwdnd.spring_security_basics.mapper.UserMapper;
import com.udacity.jwdnd.spring_security_basics.model.*;
import com.udacity.jwdnd.spring_security_basics.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialsService;
    private UserMapper userMapper;

    public HomeController(NoteService noteService, FileService fileService, CredentialService credentialsService, UserMapper userMapper) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialsService = credentialsService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String home(@ModelAttribute("credential") CredentialForm credentialForm, Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userMapper.getUser(username);
        if(user != null) {
            int userId = user.getUserId();
            Notes n = new Notes();
            n.setUserid(user.getUserid());
            model.addAttribute("notes", n);
            ArrayList<File> files = new ArrayList<>();
            files.add(new File());
            model.addAttribute("files", fileService.getUploadedFiles());

            model.addAttribute("credentials", credentialsService.getUserCredentials(userId));
            return "home";
        }
        return "signup";
    }

    @GetMapping("/result")
    public String showResult(
            Authentication authentication,
            @RequestParam(required = false, name = "isSuccess") boolean isSuccess,
            @RequestParam(required = false, name = "errorType") Integer errorType,
            Model model
    ) {

        Map<String, Object> data = new HashMap<>();

        data.put("isSuccess", isSuccess);
        data.put("errorType", errorType);

        model.addAllAttributes(data);

        return "result";
    }


}
