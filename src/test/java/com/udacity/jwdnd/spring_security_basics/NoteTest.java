//package com.udacity.jwdnd.spring_security_basics;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class NoteTests extends SpringSecurityBasicsApplicationTests {
//    /**
//     * Test that edits an existing note and verifies that the changes are displayed.
//     */
//    @Test
//    public void testDelete() {
//        String noteTitle = "My Note";
//        String noteDescription = "This is my note.";
//        Homepage homepage = signUpAndLogin();
//        createNote(noteTitle, noteDescription, homepage);
//        homepage.navToNotesTab();
//        homepage = new Homepage(driver);
//        Assertions.assertFalse(homepage.noNotes(driver));
//        deleteNote(homepage);
//        Assertions.assertTrue(homepage.noNotes(driver));
//    }
//
//    private void deleteNote(Homepage homePage) {
//        homePage.deleteNote();
//        ResultPage resultPage = new ResultPage(driver);
//        resultPage.clickOk();
//    }
//
//    /**
//     * Test that creates a note, and verifies it is displayed.
//     */
//    @Test
//    public void testCreateAndDisplay() {
//        String noteTitle = "My Note";
//        String noteDescription = "This is my note.";
//        Homepage homePage = signUpAndLogin();
//        createNote(noteTitle, noteDescription, homePage);
//        homePage.navToNotesTab();
//        homePage = new Homepage(driver);
//        Notes note = homePage.getFirstNote();
//        Assertions.assertEquals(noteTitle, note.getNoteTitle());
//        Assertions.assertEquals(noteDescription, note.getNoteDescription());
//        deleteNote(homePage);
//        homePage.logout();
//    }
//
//    /**
//     * Test that edits an existing note and verifies that the changes are displayed.
//     */
//    @Test
//    public void testModify() {
//        String noteTitle = "My Note";
//        String noteDescription = "This is my note.";
//        Homepage homePage = signUpAndLogin();
//        createNote(noteTitle, noteDescription, homePage);
//        homePage.navToNotesTab();
//        homePage = new Homepage(driver);
//        homePage.editNote();
//        String modifiedNoteTitle = "My Modified Note";
//        homePage.modifyNoteTitle(modifiedNoteTitle);
//        String modifiedNoteDescription = "This is my modified note.";
//        homePage.modifyNoteDescription(modifiedNoteDescription);
//        homePage.saveNoteChanges();
//        ResultPage resultPage = new ResultPage(driver);
//        resultPage.clickOk();
//        homePage.navToNotesTab();
//        Note note = homePage.getFirstNote();
//        Assertions.assertEquals(modifiedNoteTitle, note.getNoteTitle());
//        Assertions.assertEquals(modifiedNoteDescription, note.getNoteDescription());
//    }
//
//    private void createNote(String noteTitle, String noteDescription, Homepage homePage) {
//        homePage.navToNotesTab();
//        homePage.addNewNote();
//        homePage.setNoteTitle(noteTitle);
//        homePage.setNoteDescription(noteDescription);
//        homePage.saveNoteChanges();
//        ResultPage resultPage = new ResultPage(driver);
//        resultPage.clickOk();
//        homePage.navToNotesTab();
//    }
//}