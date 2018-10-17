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
	
	public void returnDashboard(){
		navigationBack_Android();
	}
	
	public String getResourcesTitle(){
		return findElementByIndex(mapping.getProperty("text_view"), 0).getText();
	}
	
	public void clickBackBtn(){
		clickElement(findElementByIndex(mapping.getProperty("button"), 0));
	}
	
	public Boolean verifyX(){	
    	return findElement(mapping.getProperty("pop_text")).getText().contains("Please review your credentials. [Email/Account Name and password, or Device Access Token]");
    }
	
	/*------------------------------------------------------------------------------------------------------------------------------*/
	
}
