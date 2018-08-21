package HotZone.autotest.businessComponent;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import HotZone.autotest.pageObjects.LoginPageObject;
import Main.utilities.Utils;

public class BusinessComponent {
	
	static Logger logger = Logger.getLogger(BusinessComponent.class);
	
	public static void Login(WebDriver driver, String email, String pin){	
		  LoginPageObject login = new LoginPageObject(driver);
		  login.input_email(email);
		  login.click_btn_next();
		  Utils.sleep(5000);
		  login.input_pin(pin);
		  Utils.sleep(15000);
	}	
	
	public static void Logout(WebDriver driver){	

	}

}
