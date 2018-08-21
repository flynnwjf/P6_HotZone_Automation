package Main.utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Helper {
	

	public static void screenShot(WebDriver driver, String picName, String path) {
		String dir_name = System.getProperty("user.dir")+ File.separator + path;
		if (!(new File(dir_name).isDirectory())) {
			new File(dir_name).mkdir();
		}
		try {
			File source_file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source_file, new File(dir_name + File.separator
					+ picName + "_" + Utils.formatDateString("yyyyMMdd-HHmm") + ".png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean waitForObjectDisplay(WebDriver driver, WebElement ele, long secs) {
		boolean objDisp = false;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			wait.until (new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (ele.isDisplayed());
				}
			});
			objDisp = true;
		} 
		catch (TimeoutException te) {
			Assert.fail("Cannot find this web element in the page:" + ele.getTagName());
		}
		return objDisp;
	}

	public static boolean waitForObjectPresent(WebDriver driver, WebElement ele, long secs) {
		boolean objDisp = false;
		WebDriverWait wait = new WebDriverWait(driver, secs);
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			objDisp = true;
		} 
		catch (TimeoutException te) {
			Assert.fail("Cannot find this web element in the page: " + ele.getTagName() + " " + ele.getText());
		}
		return objDisp;
	}

	public static boolean waitForPageLoadByTitle(WebDriver driver, String pageTitle) {
		boolean pagDisp = false;
		WebDriverWait wait = new WebDriverWait(driver, 40);
		try {
			wait.until(ExpectedConditions.titleContains(pageTitle));
			pagDisp = true;
		} 
		catch (TimeoutException te) {
			Assert.fail("Cannot load page : " + pageTitle);
		}
		return pagDisp;
	}
	
	public static boolean waitForObjectClickable(WebDriver driver, WebElement ele, long secs) {
		boolean pagDisp = false;
		WebDriverWait wait = new WebDriverWait(driver, secs);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			pagDisp = true;
		} 
		catch (TimeoutException te) {
			Assert.fail("Cannot find element : " + ele.getTagName());
		}
		return pagDisp;
	}

	public static Boolean waitForObjectValue(WebDriver driver, String actlVal, String exptVal, long secs) {
		boolean elementValue = false;
		WebDriverWait wait = new WebDriverWait(driver, secs);
		try {
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return actlVal.trim().equals(exptVal.trim());
				}
			});
			elementValue = true;
		} 
		catch (TimeoutException te) {
			Assert.fail("Object value: " + actlVal + " is not equal to : " + exptVal);
		}
		return elementValue;
	}	

}
