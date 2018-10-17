package HotZone.autotest.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import HotZone.autotest.businessComponent.BusinessComponent;
import HotZone.autotest.pageObjects.HomePageObject;
import HotZone.autotest.pageObjects.MyProfilePageObject;
import HotZone.autotest.pageObjects.ResourcesPageObject;
import Main.utilities.Utils;


public class TC_APP_Dashboard_MenuBar extends BaseTestCase{

	 @Test (priority = 1)
	 public void testUserNameOnMenuBar() {	
		 BusinessComponent.Login(driver, dataMap.getProperty("valid_email"), dataMap.getProperty("valid_pin"));
		 HomePageObject home = new HomePageObject(driver);
		 home.clickMenuBar();
		 Utils.sleep(5000);
		 Assert.assertTrue(home.getUserName().equals(dataMap.getProperty("user_name")));
		 home.returnDashboard();
	 }
	 
	 @Test (priority = 2)
	 public void testMyProfileOptionOnMenuBar() {	
		 HomePageObject home = new HomePageObject(driver);
		 home.clickMenuBar();
		 Utils.sleep(5000);
		 home.clickMenuMyProfile();
		 Utils.sleep(5000);
		 MyProfilePageObject profile = new MyProfilePageObject(driver);
		 Assert.assertTrue(profile.getProfileTitle().equals("Profile"));
		 profile.clickBackBtn();
		 Utils.sleep(5000);
		 home.returnDashboard();
	 }
	 
	 @Test (priority = 3)
	 public void testResourcesOptionOnMenuBar() {	
		 HomePageObject home = new HomePageObject(driver);
		 home.clickMenuBar();
		 Utils.sleep(5000);
		 home.clickMenuResources();
		 Utils.sleep(5000);
		 ResourcesPageObject resource = new ResourcesPageObject(driver);
		 Assert.assertTrue(resource.getResourcesTitle().equals("Resources"));
		 resource.clickBackBtn();
		 Utils.sleep(5000);
		 home.returnDashboard();
	 }
   	 
}
