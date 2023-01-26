package com.udacity.jwdnd.spring_security_basics.service;

import com.udacity.jwdnd.spring_security_basics.mapper.FileMapper;
import com.udacity.jwdnd.spring_security_basics.mapper.UserMapper;
import com.udacity.jwdnd.spring_security_basics.model.File;
import com.udacity.jwdnd.spring_security_basics.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileService {

    @Autowired

    private FileMapper fileMapper;
    private UserMapper userMapper;
    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public boolean saveFile(MultipartFile file, String userName) throws IOException {

        User user = this.userMapper.getUser(userName);

        Integer userId = user.getUserid();

        byte[] fileData = file.getBytes();
        String contentType = file.getContentType();
        String fileSize = String.valueOf(file.getSize());
        String fileName = file.getOriginalFilename();
        if (file.getSize() / (1024 * 1024) <= 1){
            this.fileMapper.insert(new File(null, fileName, contentType, fileSize, userId, fileData));
    }

        return true;
    }

    public List<File> getUploadedFiles(){
        return fileMapper.getAllFiles();
    }

    public boolean deleteFile(int fileId) {

        this.fileMapper.delete(fileId);

        return true;
    }

    public File getFileById(Integer fileId){
        return fileMapper.getFileById(fileId);
    }

    public Object getFileListings(Integer userId) {
        return this.fileMapper.getFileById(userId);
    }

    public boolean isFileNameAvailableForUser(String username, String filename) {

        Map<String, Object> paraMap = new HashMap<>();

        paraMap.put("username", username);
        paraMap.put("filename", filename);

        return this.fileMapper.getFileByUsernameAndFileName(paraMap).isEmpty();
    }



}