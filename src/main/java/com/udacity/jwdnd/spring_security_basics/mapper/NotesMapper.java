package com.udacity.jwdnd.spring_security_basics.mapper;

import com.udacity.jwdnd.spring_security_basics.model.File;
import com.udacity.jwdnd.spring_security_basics.model.Notes;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NotesMapper {
    @Select("SELECT *  FROM NOTES WHERE userid = #{userid}")
    Notes[] getNotesForUser(Integer userid);

    @Select("SELECT * FROM NOTES")
    Notes[] getNoteListings();

    @Select("SELECT *  FROM NOTES WHERE noteid = #{noteid}")
    Notes getNotes(Integer notesid);
    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    Notes getNote(Integer noteid);

    //System.out.println("inside note mapper");
    //@Insert("INSERT INTO NOTES (notetitle = #{notetitle}, notedescription =  #{notedescription}, userid = #{userid}")
    @Insert("insert into NOTES (" +
            "notetitle, " +
            "notedescription, " +
            "userid) VALUES (" +
            "#{notetitle}, " +
            "#{noteDescription}, " +
            "#{userid} )")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Notes notes);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    void deleteNote(Integer noteid);


    @Update("UPDATE NOTES " +
            " SET notetitle = #{notetitle}, notedescription = #{notedescription} " +
            " WHERE noteid = #{noteid}")
    int updateNote(Notes notes);

}