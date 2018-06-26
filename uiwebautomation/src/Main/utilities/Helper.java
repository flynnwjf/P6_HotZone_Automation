/**
 * 
 */
package Main.utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Helper {

	public static void getFocus(WebDriver driver, WebElement ele) {
		Actions ac = new Actions(driver);
		ac.contextClick(ele).perform();
		ele.sendKeys(Keys.ESCAPE);
	}
	
	public static void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

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

	public static boolean waitForElementClickable(WebDriver driver, String xpathStr, long secs) {
		boolean pagDisp = false;
		WebDriverWait wait = new WebDriverWait(driver, secs);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathStr)));
			pagDisp = true;
		} 
		catch (TimeoutException te) {
			Assert.fail("Cannot find element : " + xpathStr);
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

	public static Boolean isSubObjectExist(WebDriver driver, WebElement parentObj, String xpathStr) {
		try {
			parentObj.findElement(By.xpath(xpathStr));
			return true;
		} 
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public static Boolean waitForElementNotPresent(WebDriver driver, String cssStr, long secs) {
		boolean elementDisp = false;
		WebDriverWait wait = new WebDriverWait(driver, secs);
		try {
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (!driver.findElement(By.cssSelector(cssStr)).isDisplayed());
				}
			});
			elementDisp = true;
		} 
		catch (TimeoutException te) {
			Assert.fail("Can find element : " + cssStr);
		}
		return elementDisp;
	}

	public static Boolean waitForElementPresent(WebDriver driver, String cssStr) {
		boolean elementDisp = false;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			wait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.cssSelector(cssStr));
				}
			});
			elementDisp = true;
		} 
		catch (TimeoutException te) {
			Assert.fail("Cannot find element : " + cssStr);
		}
		return elementDisp;
	}
	
	public static Boolean waitForElementDisplayedByCss(WebDriver driver, String xpath, long secs) {
		boolean elementDisp = false;
		WebDriverWait wait = new WebDriverWait(driver, secs);
		try {
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return d.findElement(By.xpath(xpath)).isDisplayed();
				}
			});
			elementDisp = true;
		} 
		catch (TimeoutException te) {
			Assert.fail("Cannot find element : " + xpath);
		}
		return elementDisp;
	}
	
	public static Boolean waitForElementValue(WebDriver driver, String actlVal, String exptVal,long secs) {
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
			Assert.fail("Element value: "+ actlVal + " is not equal to : " + exptVal);
		}
		return elementValue;
	}	

	public static Boolean waitForElementPresentByXpath(RemoteWebDriver driver, String xpathstr) {
		boolean elementDisp = false;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			wait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath(xpathstr));
				}
			});
			elementDisp = true;
		} 
		catch (TimeoutException te) {
			Assert.fail("Cannot find element : " + xpathstr);
		}
		return elementDisp;
	}

	public void waitForPageToLoad(long mills, WebDriver driver, String selector) {
		boolean isPresent = false;
		for(int count = 0;count<3;count++){
			try {
				Thread.sleep(mills);
				isPresent = driver.findElement(By.cssSelector(selector)).isDisplayed();
			} 
			catch (Exception e) {
				continue;
			}
			if(isPresent){
				break;
			}
		} 
	}

}
