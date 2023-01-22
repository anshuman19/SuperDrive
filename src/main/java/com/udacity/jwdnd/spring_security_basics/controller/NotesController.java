package com.udacity.jwdnd.spring_security_basics.controller;

import com.udacity.jwdnd.spring_security_basics.service.NoteService;
import com.udacity.jwdnd.spring_security_basics.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import com.udacity.jwdnd.spring_security_basics.model.Notes;

@Controller
@RequestMapping("/notes")
public class NotesController {
    private NoteService noteService;
    private UserService userService;

    public NotesController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/save")
    public String addNote(Authentication authentication, @ModelAttribute("notes") Notes notes, Model model){
        System.out.println("Inside Note controller");
        System.out.println(notes.getNoteid());
        System.out.println(notes.getNotetitle());
        System.out.println(notes.getNoteDescription());
        System.out.println(notes.getUserid());


        Boolean isSuccess = noteService.addNote(notes) > 0;
        return "redirect:/result?isSuccess=" + isSuccess;
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam("id") int id){
        Boolean isSuccess = id > 0;

        if(isSuccess){
            noteService.deleteNote(id);
        }

        return "redirect:/result?isSuccess=" + isSuccess;
    }

}