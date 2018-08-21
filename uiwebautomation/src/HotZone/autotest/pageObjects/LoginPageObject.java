package HotZone.autotest.pageObjects;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import Main.base.BasePageObject;
import Main.utilities.PropUtils;


public class LoginPageObject extends BasePageObject {
	private static final String PROP_FILE =  System.getProperty("user.dir")+"/src/HotZone/autotest/uimapping/Login.properties";
	Properties mapping = PropUtils.getProperties(PROP_FILE);
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	
	/*------------------------------------------------------------------------------------------------------------------------------*/
	
	public void input_email(String usrStr){
		clearAndTypeString(findElement(mapping.getProperty("emailfield")),usrStr);
	}
	
	public void input_email_invalid(String usrStr, String val){
		clearAndTypeString(findElement(mapping.getProperty("emailfield").replace("Email Address", val)),usrStr);
	}
	
	public void input_pin(String pwdStr){
		clearAndTypeString(findElement(mapping.getProperty("pinfield")),pwdStr);
	}
	
	public void click_btn_next(){
		clickElement(findElement(mapping.getProperty("nextbutton")));
	}
	
	public void click_btn_login(){
		clickElement(findElement(mapping.getProperty("loginbutton")));
	}
	
	public void click_pop_ok(){
		clickElement(findElement(mapping.getProperty("pop_ok")));
	}
	
	public Boolean verify_email_page_header(){	
    	return findElement(mapping.getProperty("emailtext")).getText().contains("Enter your previously registered email address.");
    }
	
	public Boolean verify_pin_page_header(){	
    	return findElement(mapping.getProperty("pintext")).getText().contains("Enter Your 6 Digit PIN");
    }
	
	public Boolean verify_pop_title_invalid_email(){	
    	return findElement(mapping.getProperty("pop_title")).getText().contains("Invalid Email");
    }
	
	public Boolean verify_pop_title_invalid_pin(){	
    	return findElement(mapping.getProperty("pop_title")).getText().contains("Alert");
    }
	
	public Boolean verify_pop_text_invalid_email(){	
    	return findElement(mapping.getProperty("pop_text")).getText().contains("We don¡¯t recognize this email address. Please enter your previously registered email address.");
    }
	
	public Boolean verify_pop_text_invalid_pin(){	
    	return findElement(mapping.getProperty("pop_text")).getText().contains("Please review your credentials. [Email/Account Name and password, or Device Access Token]");
    }
	
	public Boolean verify_login_dashboard(){
		return findElement(mapping.getProperty("dash_search_bar")).isDisplayed();
	}
	
	/*------------------------------------------------------------------------------------------------------------------------------*/
	
}
