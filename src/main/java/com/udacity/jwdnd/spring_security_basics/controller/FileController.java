package com.udacity.jwdnd.spring_security_basics.controller;

import com.udacity.jwdnd.spring_security_basics.mapper.UserMapper;
import com.udacity.jwdnd.spring_security_basics.model.CredentialForm;
import com.udacity.jwdnd.spring_security_basics.model.File;
import com.udacity.jwdnd.spring_security_basics.model.FileForm;
import com.udacity.jwdnd.spring_security_basics.model.NoteForm;
import com.udacity.jwdnd.spring_security_basics.service.CredentialService;
import com.udacity.jwdnd.spring_security_basics.service.FileService;
import com.udacity.jwdnd.spring_security_basics.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Controller
@Component
@ControllerAdvice
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;


//    public FileController() {
//
//    }
//
//    public FileController(FileService fileService) {
//        this.fileService = fileService;
//    }

    private UserMapper userMapper;
    private NoteService noteService;
    private CredentialService credentialService;


//    public FileController(FileService fileService, UserMapper userMapper, NoteService noteService, CredentialService credentialService) {
//        this.fileService = fileService;
//        this.userMapper = userMapper;
//        this.noteService = noteService;
//        this.credentialService = credentialService;
//        System.out.println("Inside file controller constructor");
//    }

    @GetMapping("/displayFiles")
    public String displayFiles( )
    {

        return "Hello. Files displayed" + this.fileService.getUploadedFiles().get(0).getFilename();
    }
    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("fileUpload") MultipartFile fileUpload,
            Authentication authentication, Model model
    ) {
        //System.out.println("upload called");

        String username = (String) authentication.getPrincipal();

        if (fileUpload.isEmpty()) {
            return "redirect:/result?isSuccess=" + false + "&errorType=" + 1;
        }
        System.out.println(fileUpload.getSize());
        if (fileUpload.getSize()/(1024*1024)>1)
        {
            return "redirect:/result?isSuccess=" + false + "&errorType=" + 1;
        }
        String fileName = fileUpload.getOriginalFilename();

        if (!this.fileService.isFileNameAvailableForUser(username, fileName)) {
            return "redirect:/result?isSuccess=" + false + "&errorType=" + 1;
        }
        try {
            this.fileService.saveFile(fileUpload, username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> files = this.fileService.getUploadedFiles();
        model.addAttribute("files",files);

        //System.out.println(model.asMap().size());
        //System.out.println(files.get(0).getFilename());

        return "redirect:/result?isSuccess=" + true;
    }

    @ModelAttribute
    public void addAttributes(Model model){

        model.addAttribute("files", this.fileService.getUploadedFiles());
    }
    @GetMapping("/delete")
    public String deleteFile(
            @RequestParam(required = false, name = "fileId") Integer fileId) {
        boolean isSuccess = this.fileService.deleteFile(fileId);
        return "redirect:/result?isSuccess=" + isSuccess;
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(
            @RequestParam( name = "fileId") Integer fileId){

        File file = this.fileService.getFileById(fileId);

        String fileName = file.getFilename();
        String contentType = file.getContenttype();

        byte[] fileData = file.getFiledata();

        InputStream inputStream = new ByteArrayInputStream(fileData);

        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

}

