package com.orangehrmlive;

import com.team6.base.CommonAPI;
import com.team6.pages.orangehrmlive.*;
import com.team6.utility.Utility;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.Properties;

public class AdminTest extends CommonAPI {
    Properties prop = Utility.loadProperties();
    String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
    String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));
    String invalidUsername = Utility.decode(prop.getProperty("orangeHRM.invalidUserName"));
    String invalidPassword = Utility.decode(prop.getProperty("orangeHRM.invalidPassword"));

    @BeforeMethod
    @Override
    public void setUp(@Optional("false") String useCloudEnv, @Optional("browserstack") String envName, @Optional("windows") String os,
                      @Optional("10") String osVersion, @Optional("chrome") String browserName, @Optional("110") String browserVersion,
                      @Optional("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login") String url) throws MalformedURLException {
        super.setUp(useCloudEnv, envName, os, osVersion, browserName, browserVersion, url);
    }

    @Test
    public void verifyResetButton() {
        LoginPage lp = new LoginPage(getDriver());
        AdminPage aP = new AdminPage(getDriver());

        lp.enteringUserNamePassWord();
        lp.clickOnLoginBtn();
        aP.clickOnAdmin();
        aP.enterUsernameAndEmployeeName("Jon", "Doe");
        aP.clickResetButton();
        Assert.assertTrue(aP.verifyTextBoxEmpty());

    }

    @Test
    public void verifySearchWithValidUserDetails() {
        LoginPage lp = new LoginPage(getDriver());
        AdminPage aP = new AdminPage(getDriver());

        lp.enterUsername(validUsername);
        lp.enterPassword(validPassword);
        lp.clickOnLoginBtn();
        aP.clickOnAdmin();

        aP.enterUsernameAndEmployeeName("admin");
        // Assuming you have a search button in AdminPage, click it after entering the details:
        aP.clickSearchButton();


        // Add an assertion to check if the search results contain the expected data.
        // This can be done by checking if a specific element is displayed in the search results.
    }
}