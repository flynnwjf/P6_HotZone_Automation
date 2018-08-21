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
		 home.click_menu_bar();
		 Utils.sleep(5000);
		 Assert.assertTrue(home.get_user_name().equals(dataMap.getProperty("user_name")));
		 home.return_dashboard();
	 }
	 
	 @Test (priority = 2)
	 public void testMyProfileOptionOnMenuBar() {	
		 HomePageObject home = new HomePageObject(driver);
		 home.click_menu_bar();
		 Utils.sleep(5000);
		 home.click_menu_my_profile();
		 Utils.sleep(5000);
		 MyProfilePageObject profile = new MyProfilePageObject(driver);
		 Assert.assertTrue(profile.get_profile_title().equals("Profile"));
		 profile.click_back_btn();
		 Utils.sleep(5000);
		 home.return_dashboard();
	 }
	 
	 @Test (priority = 3)
	 public void testResourcesOptionOnMenuBar() {	
		 HomePageObject home = new HomePageObject(driver);
		 home.click_menu_bar();
		 Utils.sleep(5000);
		 home.click_menu_resources();
		 Utils.sleep(5000);
		 ResourcesPageObject resource = new ResourcesPageObject(driver);
		 Assert.assertTrue(resource.get_resources_title().equals("Resources"));
		 resource.click_back_btn();
		 Utils.sleep(5000);
		 home.return_dashboard();
	 }
   	 
}
