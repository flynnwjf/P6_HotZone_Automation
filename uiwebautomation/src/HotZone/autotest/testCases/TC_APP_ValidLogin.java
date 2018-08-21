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
		  login.verify_email_page_header();
		  login.input_email(dataMap.getProperty("valid_email"));
		  login.click_btn_next();
		  Utils.sleep(5000);
		  login.verify_pin_page_header();
		  login.input_pin(dataMap.getProperty("valid_pin"));
		  Utils.sleep(15000);
		  Assert.assertTrue(login.verify_login_dashboard());
	 }
   	 
}
