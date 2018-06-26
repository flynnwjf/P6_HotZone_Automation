package HotZone.autotest.testCases;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import Main.drivers.WebDriverCreator;
import Main.utilities.PropUtils;
import Main.utilities.Utils;
import Main.utilities.XmlParse;
import Main.utilities.XmlParseHandler;

public class BaseTestCase {
	
	static final String CONFIG = System.getProperty("user.dir")+"/src/HotZone/resource/config.xml";
	static final String MsgData = System.getProperty("user.dir") + "/src/HotZone/testdata/message.properties";
	static final String TestData = System.getProperty("user.dir") + "/src/HotZone/testdata/testdata.properties";
	String screenshotPath = "/src/HotZone/screenshot/" + Utils.getCurrentDate();
	
	protected XmlParseHandler xmlConfigUtils = XmlParse.getConfigDocInstance(CONFIG);
	Properties dataMap = PropUtils.getProperties(TestData);
	Properties msgMap = PropUtils.getProperties(MsgData);
	
	Logger logger = Logger.getLogger(this.getClass());
	protected WebDriver driver;
	
	
	/*Debug*/
	@BeforeTest
	public void setup() throws Exception {		
		logger.info("###### start setup driver environment ######");		
		String envStr = xmlConfigUtils.getNodeValue("env");
		if (envStr.equalsIgnoreCase("desktop")) {
			envStr = xmlConfigUtils.getChildNodeValue(envStr, "browserName");
			driver = WebDriverCreator.getWebDriver(envStr);		
			if (driver != null) {
				driver.get(xmlConfigUtils.getNodeValue("url"));
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    		driver.manage().window().maximize();
			}
		}
		else if (envStr.contains("_web")){
			driver = WebDriverCreator.getWebDriver(envStr);		
			if (driver != null) {
				driver.get(xmlConfigUtils.getNodeValue("url"));
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
		}
		else {
			driver = WebDriverCreator.getWebDriver(envStr);		
		}
		Thread.sleep(5000);
	}

	/*Remote*/
//	@BeforeTest
//	@Parameters({"env","browserType"})
//	public void setup(String env, String browser) throws Exception {	
//		logger.info("###### start setup driver environment ######");	
//		if (env.equalsIgnoreCase("desktop")) {
//			driver = WebDriverCreator.getWebDriver(browser);		
//			if (driver != null) {
//				driver.get(xmlConfigUtils.getNodeValue("url"));
//				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	    		driver.manage().window().maximize();
//			}
//		}
//		else if (env.contains("_web")){
//			driver = WebDriverCreator.getWebDriver(env);		
//			if (driver != null) {
//				driver.get(xmlConfigUtils.getNodeValue("url"));
//				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//			}
//		}
//		else {
//			driver = WebDriverCreator.getWebDriver(env);		
//		}
//		Thread.sleep(5000);
//	}

	@AfterTest
	public void teardown() {
		driver.quit();
		logger.info("###### end teardown driver environment ######");
	}
	
}
