package HotZone.autotest.testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import HotZone.autotest.businessComponent.BusinessComponent;
import HotZone.autotest.pageObjects.HomePageObject;
import Main.utilities.Utils;


public class TC_APP_Dashboard_ExpandAllPatients extends BaseTestCase{

	 @Test (priority = 1)
	 public void testViewEachPatientsDetail() {	
		 BusinessComponent.Login(driver, dataMap.getProperty("valid_email"), dataMap.getProperty("valid_pin"));
		 HomePageObject home = new HomePageObject(driver);
		 int count = home.getCountOfPatients();
		 List<WebElement> optList = home.getListOfPatients();
		 for(int i=0; i<count; i++){
			 home.clickSpecificPatient(optList, i);
			 Utils.sleep(5000);
			 home.returnDashboard();
			 Utils.sleep(5000);
		 }
	 }
   	 
}
