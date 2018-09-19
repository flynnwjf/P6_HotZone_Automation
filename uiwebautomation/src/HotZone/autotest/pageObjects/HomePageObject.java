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
	
	public void return_dashboard(){
		navigationBack_Android();
	}
	
	public void click_menu_bar(){
		clickElement(findElementByIndex(mapping.getProperty("view"), 7));
	}
	
	public String get_user_name(){
		int count = countOfElementsByClassName(mapping.getProperty("text_view"));
		return findElementByIndex(mapping.getProperty("text_view"), (count-3)).getText();
	}
	
	public void click_menu_my_profile(){
		int count = countOfElementsByClassName(mapping.getProperty("linear"));
		clickElement(findElementByIndex(mapping.getProperty("linear"), (count-2)));
	}
	
	public void click_menu_resources(){
		int count = countOfElementsByClassName(mapping.getProperty("linear"));
		clickElement(findElementByIndex(mapping.getProperty("linear"), (count-1)));
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
