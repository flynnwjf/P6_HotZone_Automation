package HotZone.autotest.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import HotZone.autotest.pageObjects.LoginPageObject;
import Main.utilities.Utils;


public class TC_APP_ValidAndInvalidLogin extends BaseTestCase{

	 @Test (priority = 1)
	 public void testInvalidLoginWithWrongEmail() {	 	 
		  LoginPageObject login = new LoginPageObject(driver);
		  login.verify_email_page_header();
		  login.input_email(dataMap.getProperty("invalid_email"));
		  login.click_btn_next();
		  Utils.sleep(5000);
		  Assert.assertTrue(login.verify_pop_title());
		  Assert.assertTrue(login.verify_pop_text());
		  login.click_pop_ok();
	 }
	 
	 @Test (priority = 2)
	 public void testValidLogin() {	 	 
		  LoginPageObject login = new LoginPageObject(driver);
		  login.verify_email_page_header();
		  login.input_email(dataMap.getProperty("valid_email"));
		  login.click_btn_next();
		  Utils.sleep(5000);
		  login.verify_pin_page_header();
		  login.input_pin(dataMap.getProperty("valid_pin"));
	 }
   	 

}
