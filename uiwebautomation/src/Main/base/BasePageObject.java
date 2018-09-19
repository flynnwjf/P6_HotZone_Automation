package Main.base;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Main.utilities.Utils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;


public class BasePageObject {

	protected Logger logger = Logger.getLogger(this.getClass());
	protected WebDriver driver;
	
	/*Android - Navigate Back*/
	public void navigationBack_Android() {
		AndroidDriver<WebElement> driver = (AndroidDriver<WebElement>) this.driver;
		driver.pressKeyCode(AndroidKeyCode.BACK);
	}
	
	/*Find Element*/	
	public WebElement findElement(String locator){
		String by = locator.split("->")[0];
		String path = locator.split("->")[1];
		logger.info("Find element: " + path);
		if (by.equals("id")){
			return findElementById(path);
		}
		else if (by.equals("xpath")){
			return findElementByXpath(path);
		}
		else {
			return findElementByCss(path);
		}
	}
	
	public WebElement findElementById(String id){
		logger.info("find element by id: " + id);
		try{
			return driver.findElement(By.id(id));
		}catch(Exception e){
			logger.info("Find element exception: " + e.getMessage());
			return null;
		}
	}
	
	public WebElement findElementByXpath(String xpath){
		logger.info("find element by xpath: " + xpath);
		try{
			return driver.findElement(By.xpath(xpath));
		}catch(Exception e){
			logger.info("Find element exception: " + e.getMessage());
			return null;
		}
	}
	
	public WebElement findElementByCss(String cssStr){
		logger.info("find element by css: " + cssStr);
		try{
		   return driver.findElement(By.cssSelector(cssStr));
		}catch(Exception e){
		   logger.info("Find element exception: " + e.getMessage());
		   return null;
		}
	}
	
	public WebElement findElementByIndex(String name, int index){
		logger.info("find elements by class name: " + name);
		try{
			return driver.findElements(By.className(name)).get(index);
		}catch(Exception e){
			logger.info("Find elements exception: " + e.getMessage());
			return null;
		}
	}
	
	public int countOfElementsByClassName(String name){
		return driver.findElements(By.className(name)).size();
	}
	
	/*Release Element*/	
	public void releaseElement(WebElement ele){
		Actions ac = new Actions(driver);
		ac.release(ele);
	}
	
	public boolean isElementDisplay(WebElement ele) {
        boolean isdisplay = false;
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try{
             wait.until(ExpectedConditions.visibilityOf(ele));           
             isdisplay = ele.isDisplayed(); 
             logger.info("Verify if current element " + ele.getTagName() + " is displayed on page: " + isdisplay);
           } 
        catch (Exception e) {
             logger.info("Sorry, this element is not displayed on page, throw: " + e.getMessage());
           }
        return isdisplay;
    }
	
	public void getFocus(WebElement ele) {
		Actions ac = new Actions(driver);
		ac.contextClick(ele).perform();
		ele.sendKeys(Keys.ESCAPE);
	}
	
	public void clearAndTypeString(WebElement ele, String text) {
        logger.info("Typing " + text + " into element " + ele.getTagName());
        ele.clear();
        ele.sendKeys(text);
    }
	 
	public void clickElement(WebElement ele) {
        logger.info("Click element " + ele.getText());   
        ele.click();
    }
	 
	public void clickElementWithFocus(WebElement ele) {
	    logger.info("Click element " + ele.getText() + " with focus");
	    getFocus(ele);
	    ele.click();
	}
	 
	public void doubleClickElement(WebElement ele) {
	        logger.info("Double click element " + ele.getText()); 
	        Actions ac = new Actions(driver); 
		   	ac.doubleClick(ele).perform();
	}

	public void mouseOver(WebElement ele){
		logger.info("Mouse over on element " + ele.getTagName());
	   	Actions ac = new Actions(driver); 
	   	ac.moveToElement(ele).clickAndHold().perform();
	}
	 
	public void rightMenu(WebElement ele){
		logger.info("Right context menu on element " + ele.getTagName());
	    Actions ac = new Actions(driver); 
		ac.contextClick(ele).perform();
	}
	
	public void sendKeys(Keys key){
		logger.info("Send key: " + key);
		Actions ac = new Actions(driver);
		ac.sendKeys(key).perform();
		Utils.sleep(2000);
	}
	 
	public String getCssValue(WebElement ele,String value){
		 logger.info("Css value on element is: " + ele.getTagName());
		 return ele.getCssValue(value);
	}
	 
	public String switchToFrame(String fName){
	   logger.info("Switch to frame: "+ fName);
	   String strMainHandler = driver.getWindowHandle();
	   driver.switchTo().frame(fName);
	   return strMainHandler;
	}
	 
	public String switchToWindow(String wName){
	   logger.info("Switch to window: "+ wName + "from main page");
	   String strMainHandler = driver.getWindowHandle();
	   driver.switchTo().window(wName);
	   return strMainHandler;
	}
	 
	 //get cookie
	 /*public Set getCookieValue(){
		logger.info("retrieve cookies");
	    return driver.manage().getCookies();
	 }*/	 
	
	/*Delete All Cookies*/	
	public void deleteAllCookies(){
		logger.info("Try to delete all cookies");
		driver.manage().deleteAllCookies();
	}
	 
	 public Object executeJS(String script) {
         logger.info("Run the javascript from page, the javascript is: " + script);
         JavascriptExecutor je = (JavascriptExecutor) driver;
         return je.executeScript(script);
     }
	 
     public void executeJS(String script,WebElement e) {
         logger.info("Run the javascript from page, the javascript is: " + script);
         JavascriptExecutor je = (JavascriptExecutor) driver;
         je.executeScript(script,e);
     }
     
     public void refreshPage(){
         logger.info("Refresh the page");
         Actions actions = new Actions(driver);
         actions.sendKeys(Keys.F5).perform();
     }
     
 	public void refresh() {
		driver.navigate().refresh();
	}
     
     public void scrollToView(WebElement e){
    	 logger.info("Scroll the view to the defined position");
         //Helper.getFocus(driver, e);
         //executeJS("window.scrollTo(0," + e.getLocation().y + ")");
         executeJS("arguments[0].scrollIntoView(true);", e);
     }
     
     public void scrollToTop(){
    	 logger.info("Scroll the view to the top");
    	 executeJS("var q=document.documentElement.scrollTop=0");
     }
     
     public void Checkboxed(WebElement e) {
         if (!(e.isSelected())) {
             logger.info("Check the checkbox, the element " + e.getTagName() + e.getText() + ", was unselected before");
             e.click();
             logger.info("Check the checkbox, the element " + e.getTagName() + e.getText() + ", is selected now");
         } else {
             logger.info("Check the checkbox, the element " + e.getTagName() + e.getText() + ", had been selected by default");
         }
     }
     
     public void unCheckbox(WebElement e) {
         if (e.isSelected()) {
             logger.info("Check the checkbox, the element " + e.getTagName() + e.getText() + ", was selected before");
             e.click();
             logger.info("Check the checkbox, the element " + e.getTagName() + e.getText() + ", is unselected now");
         } else {
             logger.info("Check the checkbox, the element " + e.getTagName() + e.getText() + ", had been unselected by default");
         }
     }

     public void selectDropDownByValue(WebElement e,String value) {
    	 logger.info("Try to select item "+ value + " from dropdown: " + e.getTagName());
         Select sele = new Select(e);
         sele.selectByValue(value);
     }
     
     public void selectDropDownByIndex(WebElement e,int index) {
    	 logger.info("Try to select item "+ index + " from dropdown: " + e.getTagName());
         Select sele = new Select(e);
         sele.selectByIndex(index);
     }
     
     public void selectDropDownByVisableText(WebElement e,String text) {
    	 logger.info("Try to select item "+ text + " from dropdown: " + e.getTagName());
         Select sele = new Select(e);
         sele.selectByVisibleText(text);
     }
     
     public void selectDropDownByPartialValue(WebElement e,String name) {
    	 logger.info("Try to select item "+ name + " from dropdown: " + e.getTagName());
         Select sele = new Select(e);
         List<WebElement> optList = sele.getOptions();
         for(WebElement opt: optList){
        	 if(opt.getText().contains(name)){
        		 opt.click();
        		 break;
        	 }
         }
     }
     
     public void selectDropDownByText(String strXpath, String text) {
    	 WebElement ulObj = driver.findElement(By.xpath(strXpath));
    	 List<WebElement> optList= ulObj.findElements(By.tagName("li")); 
         for(WebElement opt: optList){
        	 if(opt.findElement(By.tagName("a")).getText().equals(text)){
        		 logger.info("Try to select item "+ text + " from dropdown: " + opt.getTagName());
        		 opt.click();
        		 break;
        	 }
         }
     }
     
     public void selectDropDownByIndex(String strXpath, String attr, String num) {
    	 WebElement ulObj = driver.findElement(By.xpath(strXpath));
    	 List<WebElement> optList= ulObj.findElements(By.tagName("li")); 
         for(WebElement opt: optList){
        	 if(opt.getAttribute(attr).equals(num)){
        		 logger.info("Try to select item (index) "+ num + " from dropdown: " + opt.getTagName());
        		 opt.findElement(By.tagName("a")).click();
        		 break;
        	 }
         }
     }
     
     public String getSelectedValue(WebElement e) {
    	 logger.info("Find selected item from dropdown: " + e.getText());
    	 String result = "";
         Select sele = new Select(e);
         List<WebElement> optList=sele.getOptions();
         for(WebElement opt: optList){
        	 if(opt.getAttribute("selected")!=null){
        		 result = opt.getText();
        		 break;
        	 }
         }
         return result;
     }
     
     public void dragAndDrop(WebElement e,int x,int y) {
    	 logger.info("Try to drag " + e.getTagName() + " X: " + x + " Y: " + y);
    	 Actions actions = new Actions(driver);
    	 actions.dragAndDropBy(e, x, y).build().perform();
     }
     
     public void dragAndDropByMove(WebElement from,WebElement to) {
    	 logger.info("Try to drag "+ from.getTagName() + " to: " + to.getTagName());
    	 Actions actions = new Actions(driver);
    	 actions.clickAndHold(from).moveToElement(to).release().perform();
//    	 actions.clickAndHold(from).moveToElement(to).release(to).build();
//    	 actions.perform();
     }
     
     
     public void holdAndMoveMouse(WebElement ele,int xOffset,int yOffset) {
    	 logger.info("Try to drag "+ ele.getText());
//    	 int x = ele.getLocation().getX();
//    	 int y = ele.getLocation().getY();
    	 Actions actions = new Actions(driver);
    	 actions.moveToElement(ele, 100, 100).clickAndHold().moveByOffset(xOffset, yOffset).release().build();
    	 actions.perform();
     }
     
     public void moveMouse(WebElement ele) {
    	 logger.info("Try to drag "+ ele.getText());
    	 Actions actions = new Actions(driver);
//    	 int x = ele.getLocation().getX();
//    	 int y = ele.getLocation().getY();
    	 actions.moveToElement(ele, 100, 100).release().build();
    	 actions.perform();   	 
     }
     
 	public Boolean isObjectExist(String xpathStr) {
		try {
			driver.findElement(By.xpath(xpathStr));
			return true;
		} 
		catch (NoSuchElementException e) {
			return false;
		}
	}
 	
} 
