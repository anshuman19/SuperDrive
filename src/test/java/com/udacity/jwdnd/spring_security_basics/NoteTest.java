package com.udacity.jwdnd.spring_security_basics;

import com.udacity.jwdnd.spring_security_basics.model.Notes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteTest extends SpringSecurityBasicsApplicationTests {
    /**
     * Test that edits an existing note and verifies that the changes are displayed.
     */
    @Test
    public void testDelete() {
        String noteTitle = "To do";
        String noteDescription = "Complete first note";
        Homepage homepage = signUpAndLogin();
        createNote(noteTitle, noteDescription, homepage);
        homepage.navToNotesTab();
        homepage = new Homepage(driver);
        Assertions.assertFalse(homepage.noNotes(driver));
        deleteNote(homepage);
        Assertions.assertTrue(homepage.noNotes(driver));
    }

    private void deleteNote(Homepage homePage) {
        homePage.deleteNote();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
    }

    /**
     * Test that creates a note, and verifies it is displayed.
     */
    @Test
    public void testCreateAndDisplay() {
        String noteTitle = "To do";
        String noteDescription = "Complete first note";
        Homepage homePage = signUpAndLogin();
        createNote(noteTitle, noteDescription, homePage);
        homePage.navToNotesTab();
        homePage = new Homepage(driver);
        Notes note = homePage.getFirstNote();
        Assertions.assertEquals(noteTitle, note.getNotetitle());
        Assertions.assertEquals(noteDescription, note.getNoteDescription());
        deleteNote(homePage);
        homePage.logout();
    }

    /**
     * Test that edits an existing note and verifies that the changes are displayed.
     */
    @Test
    public void testModify() {
        String noteTitle = "To do";
        String noteDescription = "Complete first note";
        Homepage homePage = signUpAndLogin();
        createNote(noteTitle, noteDescription, homePage);
        homePage.navToNotesTab();
        homePage = new Homepage(driver);
        homePage.editNote();
        String modifiedNoteTitle = "Modified Note";
        homePage.modifyNoteTitle(modifiedNoteTitle);
        String modifiedNoteDescription = "my modified note.";
        homePage.modifyNoteDescription(modifiedNoteDescription);
        homePage.saveNoteChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToNotesTab();
        Notes note = homePage.getFirstNote();
        Assertions.assertEquals(modifiedNoteTitle, note.getNotetitle());
        Assertions.assertEquals(modifiedNoteDescription, note.getNoteDescription());
    }

    private void createNote(String noteTitle, String noteDescription, Homepage homePage) {
        homePage.navToNotesTab();
        homePage.addNewNote();
        homePage.setNoteTitle(noteTitle);
        homePage.setNoteDescription(noteDescription);
        homePage.saveNoteChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToNotesTab();
    }
}