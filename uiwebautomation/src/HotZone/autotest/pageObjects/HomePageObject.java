package HotZone.autotest.pageObjects;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import Main.base.BasePageObject;
import Main.utilities.PropUtils;


public class HomePageObject extends BasePageObject {
	private static final String PROP_FILE =  System.getProperty("user.dir")+"/src/HotZone/autotest/uimapping/Home.properties";
	Properties mapping = PropUtils.getProperties(PROP_FILE);
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	
	/*------------------------------------------------------------------------------------------------------------------------------*/
	
	public void returnDashboard(){
		navigationBack_Android();
	}
	
	public void clickMenuBar(){
		clickElement(findElementByIndex(mapping.getProperty("view"), 7));
	}
	
	public String getUserName(){
		int count = countOfElementsByClassName(mapping.getProperty("text_view"));
		return findElementByIndex(mapping.getProperty("text_view"), (count-3)).getText();
	}
	
	public void clickMenuMyProfile(){
		int count = countOfElementsByClassName(mapping.getProperty("linear"));
		clickElement(findElementByIndex(mapping.getProperty("linear"), (count-2)));
	}
	
	public void clickMenuResources(){
		int count = countOfElementsByClassName(mapping.getProperty("linear"));
		clickElement(findElementByIndex(mapping.getProperty("linear"), (count-1)));
	}
	
	public void clickContact(){
		clickElement(findElementByIndex(mapping.getProperty("view"), 9));
	}
	
	public void clickAddPatient(){
		clickElement(findElementByIndex(mapping.getProperty("view"), 21));
	}
	
	public void inputSearchText(String text){
		clearAndTypeString(findElement(mapping.getProperty("search_text")), text);
	}
	
	public void clickSearchIcon(){
		clickElement(findElement(mapping.getProperty("search_icon")));
	}
	
	public Boolean verifyPopTextInvalidPIN(){	
    	return findElement(mapping.getProperty("pop_text")).getText().contains("Please review your credentials. [Email/Account Name and password, or Device Access Token]");
    }
	
	/*------------------------------------------------------------------------------------------------------------------------------*/
	
}
