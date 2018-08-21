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
		  login.verify_email_page_header();
		  login.input_email(dataMap.getProperty("invalid_email"));
		  login.click_btn_next();
		  Utils.sleep(5000);
		  Assert.assertTrue(login.verify_pop_title_invalid_email());
		  Assert.assertTrue(login.verify_pop_text_invalid_email());
		  login.click_pop_ok();
	 }
	 
	/*
	 * Log in the app with valid Email and invalid PIN
	 * Check if the error message will pop up
	 */
	 @Test (priority = 2)
	 public void testInvalidLoginWithWrongPIN() {	 	 
		  LoginPageObject login = new LoginPageObject(driver);
		  login.verify_email_page_header();
		  login.input_email_invalid(dataMap.getProperty("valid_email"), dataMap.getProperty("invalid_email"));
		  login.click_btn_next();
		  Utils.sleep(5000);
		  login.verify_pin_page_header();
		  login.input_pin(dataMap.getProperty("invalid_pin"));
		  Utils.sleep(5000);
		  Assert.assertTrue(login.verify_pop_title_invalid_pin());
		  Assert.assertTrue(login.verify_pop_text_invalid_pin());
		  login.click_pop_ok();
	 }
   	 
}
