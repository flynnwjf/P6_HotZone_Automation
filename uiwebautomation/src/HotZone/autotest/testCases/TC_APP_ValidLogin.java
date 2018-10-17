package HotZone.autotest.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import HotZone.autotest.pageObjects.LoginPageObject;
import Main.utilities.Utils;


public class TC_APP_ValidLogin extends BaseTestCase{

	/*
	 * Log in the app with valid Email and PIN
	 * Check if user can go to Dashboard
	 */
	
	 @Test (priority = 1)
	 public void testValidLogin() {	 	 
		  LoginPageObject login = new LoginPageObject(driver);
		  login.verifyEmailPageHeader();
		  login.inputEmail(dataMap.getProperty("valid_email"));
		  login.clickNextBtn();
		  Utils.sleep(5000);
		  login.verifyPINPageHeader();
		  login.inputPIN(dataMap.getProperty("valid_pin"));
		  Utils.sleep(15000);
		  Assert.assertTrue(login.verifyLoginDashboard());
	 }
   	 
}
