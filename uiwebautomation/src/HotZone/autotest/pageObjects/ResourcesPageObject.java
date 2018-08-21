package HotZone.autotest.pageObjects;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import Main.base.BasePageObject;
import Main.utilities.PropUtils;


public class ResourcesPageObject extends BasePageObject {
	private static final String PROP_FILE =  System.getProperty("user.dir")+"/src/HotZone/autotest/uimapping/Resources.properties";
	Properties mapping = PropUtils.getProperties(PROP_FILE);
	
	public ResourcesPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	
	/*------------------------------------------------------------------------------------------------------------------------------*/
	
	public void return_dashboard(){
		navigationBack_Android();
	}
	
	public String get_resources_title(){
		return findElementByIndex(mapping.getProperty("text_view"), 0).getText();
	}
	
	public void click_back_btn(){
		clickElement(findElementByIndex(mapping.getProperty("button"), 0));
	}
	
	public void click_menu_bar(){
		clickElement(findElementByIndex(mapping.getProperty("view"), 7));
	}
	
	public void click_menu_my_profile(){
		clickElement(findElementByIndex(mapping.getProperty("linear"), 5));
	}
	
	public void click_menu_resources(){
		clickElement(findElementByIndex(mapping.getProperty("linear"), 6));
	}
	
	public void click_contact(){
		clickElement(findElementByIndex(mapping.getProperty("view"), 9));
	}
	
	public void click_add_patient(){
		clickElement(findElementByIndex(mapping.getProperty("view"), 21));
	}
	
	public void input_search_text(String text){
		clearAndTypeString(findElement(mapping.getProperty("search_text")), text);
	}
	
	public void click_search_icon(){
		clickElement(findElement(mapping.getProperty("search_icon")));
	}
	
	public Boolean verify_pop_text_invalid_pin(){	
    	return findElement(mapping.getProperty("pop_text")).getText().contains("Please review your credentials. [Email/Account Name and password, or Device Access Token]");
    }
	
	/*------------------------------------------------------------------------------------------------------------------------------*/
	
}
