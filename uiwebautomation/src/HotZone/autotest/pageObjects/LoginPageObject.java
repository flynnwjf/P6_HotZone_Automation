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
	
	public void inputEmail(String usrStr){
		clearAndTypeString(findElement(mapping.getProperty("emailfield")),usrStr);
		hideKeyboard();
	}
	
	public void inputInvalidEmail(String usrStr, String val){
		clearAndTypeString(findElement(mapping.getProperty("emailfield").replace("Email Address", val)),usrStr);
		hideKeyboard();
	}
	
	public void inputPIN(String pwdStr){
		clearAndTypeString(findElement(mapping.getProperty("pinfield")),pwdStr);
		hideKeyboard();
	}
	
	public void clickNextBtn(){
		clickElement(findElement(mapping.getProperty("nextbutton")));
	}
	
	public void clickLoginBtn(){
		clickElement(findElement(mapping.getProperty("loginbutton")));
	}
	
	public void clickPopOK(){
		clickElement(findElement(mapping.getProperty("pop_ok")));
	}
	
	public Boolean verifyEmailPageHeader(){	
    	return findElement(mapping.getProperty("emailtext")).getText().contains("Enter your previously registered email address.");
    }
	
	public Boolean verifyPINPageHeader(){	
    	return findElement(mapping.getProperty("pintext")).getText().contains("Enter Your 6 Digit PIN");
    }
	
	public Boolean verifyPopTitleInvalidEmail(){	
    	return findElement(mapping.getProperty("pop_title")).getText().contains("Invalid Email");
    }
	
	public Boolean verifyPopTitleInvalidPIN(){	
    	return findElement(mapping.getProperty("pop_title")).getText().contains("Alert");
    }
	
	public Boolean verifyPopTextInvalidEmail(){	
    	return findElement(mapping.getProperty("pop_text")).getText().contains("We don¡¯t recognize this email address. Please enter your previously registered email address.");
    }
	
	public Boolean verifyPopTextInvalidPIN(){	
    	return findElement(mapping.getProperty("pop_text")).getText().contains("Please review your credentials. [Email/Account Name and password, or Device Access Token]");
    }
	
	public Boolean verifyLoginDashboard(){
		return findElement(mapping.getProperty("dash_search_bar")).isDisplayed();
	}
	
	/*------------------------------------------------------------------------------------------------------------------------------*/
	
}
