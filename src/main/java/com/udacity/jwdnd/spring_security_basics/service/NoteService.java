package com.udacity.jwdnd.spring_security_basics.service;

import com.udacity.jwdnd.spring_security_basics.mapper.NotesMapper;
import com.udacity.jwdnd.spring_security_basics.mapper.UserMapper;
import com.udacity.jwdnd.spring_security_basics.model.Notes;
import org.springframework.stereotype.Service;
@Service
public class NoteService {
    private final UserMapper userMapper;
    private final NotesMapper notesMapper;

    public NoteService(NotesMapper notesMapper, UserMapper userMapper) {
        this.notesMapper = notesMapper;
        this.userMapper = userMapper;
    }

    public int addNote(Notes notes){
        int id;
        if(notes.getNoteid() == null){
            id = notesMapper.insert(notes);
        } else {
            id = notesMapper.updateNote(notes);
        }

        return id;
    }
    public Notes[] getNoteListings(Integer userid){
        return notesMapper.getNotesForUser(userid);
    }
    public Notes getNote(Integer noteId) {
        return notesMapper.getNote(noteId);
    }

    public void deleteNote(Integer noteId) {
        notesMapper.deleteNote(noteId);
    }

    public Notes getUserNote(int userid) {
        return this.notesMapper.getNote(userid);
    }
}