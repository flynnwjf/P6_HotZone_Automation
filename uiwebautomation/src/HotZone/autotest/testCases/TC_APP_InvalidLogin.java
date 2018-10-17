package HotZone.autotest.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import HotZone.autotest.pageObjects.LoginPageObject;
import Main.utilities.Utils;


public class TC_APP_InvalidLogin extends BaseTestCase{

	/*
	 * Log in the app with invalid Email
	 * Check if the error message will pop up
	 */
	 @Test (priority = 1)
	 public void testInvalidLoginWithWrongEmail() {	 	 
		  LoginPageObject login = new LoginPageObject(driver);
		  login.verifyEmailPageHeader();
		  login.inputEmail(dataMap.getProperty("invalid_email"));
		  login.clickNextBtn();
		  Utils.sleep(5000);
		  Assert.assertTrue(login.verifyPopTitleInvalidEmail());
		  Assert.assertTrue(login.verifyPopTextInvalidEmail());
		  login.clickPopOK();
	 }
	 
	/*
	 * Log in the app with valid Email and invalid PIN
	 * Check if the error message will pop up
	 */
	 @Test (priority = 2)
	 public void testInvalidLoginWithWrongPIN() {	 	 
		  LoginPageObject login = new LoginPageObject(driver);
		  login.verifyEmailPageHeader();
		  login.inputInvalidEmail(dataMap.getProperty("valid_email"), dataMap.getProperty("invalid_email"));
		  login.clickNextBtn();
		  Utils.sleep(5000);
		  login.verifyPINPageHeader();
		  login.inputPIN(dataMap.getProperty("invalid_pin"));
		  Utils.sleep(5000);
		  Assert.assertTrue(login.verifyPopTitleInvalidPIN());
		  Assert.assertTrue(login.verifyPopTextInvalidPIN());
		  login.clickPopOK();
	 }
   	 
}
