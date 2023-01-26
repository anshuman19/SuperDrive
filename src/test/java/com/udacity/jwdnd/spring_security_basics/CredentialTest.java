package com.udacity.jwdnd.spring_security_basics;

import com.udacity.jwdnd.spring_security_basics.model.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialTest extends SpringSecurityBasicsApplicationTests {

    public static final String BEATLES_URL = "https://www.thebeatles.com/";
    public static final String MCCARTNEY_USERNAME = "rishi";
    public static final String MCCARTNEY_PASSWORD = "rishi01";
    public static final String RINGO_URL = "http://www.ringostarr.com/";
    public static final String RINGO_USERNAME = "amit";
    public static final String RINGO_PASSWORD = "amit01";

    /**
     * Test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed
     * password is encrypted.
     */
    @Test
    public void testCredentialCreation() {
        Homepage homePage = signUpAndLogin();
        createAndVerifyCredential(BEATLES_URL, MCCARTNEY_USERNAME, MCCARTNEY_PASSWORD, homePage);
        homePage.deleteCredential();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.logout();
    }

    private void createAndVerifyCredential(String url, String username, String password, Homepage homePage) {
        createCredential(url, username, password, homePage);
        homePage.navToCredentialsTab();
        Credential credential = homePage.getFirstCredential();
        Assertions.assertEquals(url, credential.getUrl());
        Assertions.assertEquals(username, credential.getUsername());
        Assertions.assertNotEquals(password, credential.getPassword());
    }

    private void createCredential(String url, String username, String password, Homepage homePage) {
        homePage.navToCredentialsTab();
        homePage.addNewCredential();
        setCredentialFields(url, username, password, homePage);
        homePage.saveCredentialChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToCredentialsTab();
    }

    private void setCredentialFields(String url, String username, String password, Homepage homePage) {
        homePage.setCredentialUrl(url);
        homePage.setCredentialUsername(username);
        homePage.setCredentialPassword(password);
    }

    /**
     * Test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the
     * credentials, and verifies that the changes are displayed.
     */
    @Test
    public void testCredentialModification() {
        Homepage homePage = signUpAndLogin();
        createAndVerifyCredential(BEATLES_URL, MCCARTNEY_USERNAME, MCCARTNEY_PASSWORD, homePage);
        Credential originalCredential = homePage.getFirstCredential();
        String firstEncryptedPassword = originalCredential.getPassword();
        homePage.editCredential();
        String newUrl = RINGO_URL;
        String newCredentialUsername = RINGO_USERNAME;
        String newPassword = RINGO_PASSWORD;
        setCredentialFields(newUrl, newCredentialUsername, newPassword, homePage);
        homePage.saveCredentialChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToCredentialsTab();
        Credential modifiedCredential = homePage.getFirstCredential();
        Assertions.assertEquals(newUrl, modifiedCredential.getUrl());
        Assertions.assertEquals(newCredentialUsername, modifiedCredential.getUsername());
        String modifiedCredentialPassword = modifiedCredential.getPassword();
        Assertions.assertNotEquals(newPassword, modifiedCredentialPassword);
        Assertions.assertNotEquals(firstEncryptedPassword, modifiedCredentialPassword);
        homePage.deleteCredential();
        resultPage.clickOk();
        homePage.logout();
    }

    /**
     * Test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
     */
    @Test
    public void testDeletion() {
        Homepage homePage = signUpAndLogin();
        createCredential(BEATLES_URL, MCCARTNEY_USERNAME, MCCARTNEY_PASSWORD, homePage);
        createCredential(RINGO_URL, RINGO_USERNAME, RINGO_PASSWORD, homePage);
        createCredential("http://www.johnlennon.com/", "aman01", "aman09", homePage);
        Assertions.assertFalse(homePage.noCredentials(driver));
        homePage.deleteCredential();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToCredentialsTab();
        homePage.deleteCredential();
        resultPage.clickOk();
        homePage.navToCredentialsTab();
        homePage.deleteCredential();
        resultPage.clickOk();
        homePage.navToCredentialsTab();
        Assertions.assertTrue(homePage.noCredentials(driver));
        homePage.logout();
    }
}