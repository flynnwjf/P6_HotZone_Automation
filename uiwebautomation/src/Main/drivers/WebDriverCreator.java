package Main.drivers;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import Main.utilities.XmlParse;
import Main.utilities.XmlParseHandler;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class WebDriverCreator{

	static XmlParseHandler xmlConfigUtils = XmlParse.getConfigDocInstance(System.getProperty("user.dir")+"/src/HotZone/resource/config.xml");

	public static WebDriver getWebDriver(String driverStr) throws Exception {
		if (driverStr.equalsIgnoreCase("IE")) {
			return getIEDriver();
		} else if (driverStr.equalsIgnoreCase("Chrome")) {
			return getChromeDriver();
		} else if (driverStr.equalsIgnoreCase("Safari")) {
			return getSafariDriver();
		} else if (driverStr.equalsIgnoreCase("Firefox")) {
			return getFirefoxDriver();
		} else if (driverStr.equalsIgnoreCase("iOS_WEB")) {
			return getIOSWapDriver(driverStr);
		} else if (driverStr.equalsIgnoreCase("Android_WEB")) {
			return getAndroidWapDriver(driverStr);
		} else if (driverStr.equalsIgnoreCase("iOS_APP")) {
			return getiOSAppDriver(driverStr);
		} else if (driverStr.equalsIgnoreCase("Android_APP")) {
			return getAndroidAppDriver(driverStr);
		} else {
			return getFirefoxDriver();
		}
	}

	/*Remote*/
//	private static WebDriver getChromeDriver() throws MalformedURLException {
//		DesiredCapabilities capability = DesiredCapabilities.chrome();
//		return new RemoteWebDriver(new URL("http://build01.nextpt.com:4444/wd/hub"),capability);
//	}

	/*Remote*/
//	private static WebDriver getFirefoxDriver() throws MalformedURLException {
//		DesiredCapabilities capability = DesiredCapabilities.firefox();
//		return new RemoteWebDriver(new URL("http://build01.nextpt.com:4444/wd/hub"),capability);
//	}

	/*Remote*/
//	private static WebDriver getIEDriver() throws MalformedURLException {
//		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
//		return new RemoteWebDriver(new URL("http://build01.nextpt.com:4444/wd/hub"),capability);
//	}
	
	private static WebDriver getChromeDriver() throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/Main/drivers/chromedriver.exe");
		capability = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		return new ChromeDriver(capability);
	}
	
	private static WebDriver getFirefoxDriver() throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/Main/drivers/geckodriver.exe");
		return new FirefoxDriver(capability);
	}
	
	private static WebDriver getIEDriver() throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/Main/drivers/IEDriverServer.exe");
        capability = DesiredCapabilities.internetExplorer();
        capability.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
        capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        return new InternetExplorerDriver(capability);
	}
	
	public static WebDriver getSafariDriver() {
		DesiredCapabilities safariCapabilities = DesiredCapabilities.safari();
		SafariOptions options = new SafariOptions();
		safariCapabilities.setCapability(SafariOptions.CAPABILITY, options);
		return new SafariDriver(safariCapabilities);
	}

	private static WebDriver getAndroidWapDriver(String env) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, xmlConfigUtils.getChildNodeValue(env, "browserName"));
		capabilities.setCapability("platformName", xmlConfigUtils.getChildNodeValue(env, "platformName"));
		capabilities.setCapability("deviceName", xmlConfigUtils.getChildNodeValue(env, "deviceName"));
		capabilities.setCapability("platformVersion", xmlConfigUtils.getChildNodeValue(env, "platformVersion"));
		capabilities.setCapability("newCommandTimeout", "300");
		return new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	private static WebDriver getIOSWapDriver(String env) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", xmlConfigUtils.getChildNodeValue(env, "deviceName"));
		capabilities.setCapability("platformName", xmlConfigUtils.getChildNodeValue(env, "platformName"));
		capabilities.setCapability("platformVersion", xmlConfigUtils.getChildNodeValue(env, "platformVersion"));
		capabilities.setCapability("browserName", xmlConfigUtils.getChildNodeValue(env, "browserName"));
		return new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	
	private static WebDriver getAndroidAppDriver(String env) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", System.getProperty("user.dir") + xmlConfigUtils.getChildNodeValue(env, "filePath"));
		capabilities.setCapability("udid", xmlConfigUtils.getChildNodeValue(env, "deviceID"));
		capabilities.setCapability("platformName", xmlConfigUtils.getChildNodeValue(env, "platformName"));
		capabilities.setCapability("deviceName", xmlConfigUtils.getChildNodeValue(env, "deviceName"));
		capabilities.setCapability("platformVersion", xmlConfigUtils.getChildNodeValue(env, "platformVersion"));
		capabilities.setCapability("appPackage", "com.ppdgsk.hotzone");
		capabilities.setCapability("appActivity", "md51384854994f54f5416fc33864cf2ee5a.SplashActivity");
		capabilities.setCapability("newCommandTimeout", "300");
		return new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	
	private static WebDriver getiOSAppDriver(String env) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", System.getProperty("user.dir") + xmlConfigUtils.getChildNodeValue(env, "filePath"));
		capabilities.setCapability("udid", xmlConfigUtils.getChildNodeValue(env, "deviceID"));
		capabilities.setCapability("platformName", xmlConfigUtils.getChildNodeValue(env, "platformName"));
		capabilities.setCapability("deviceName", xmlConfigUtils.getChildNodeValue(env, "deviceName"));
		capabilities.setCapability("platformVersion", xmlConfigUtils.getChildNodeValue(env, "platformVersion"));
		capabilities.setCapability("appPackage", "com.ppdgsk.hotzone");
		capabilities.setCapability("appActivity", "md51384854994f54f5416fc33864cf2ee5a.SplashActivity");
		capabilities.setCapability("newCommandTimeout", "300");
		return new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

}
