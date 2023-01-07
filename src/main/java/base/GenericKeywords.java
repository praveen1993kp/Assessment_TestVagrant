package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class GenericKeywords implements SeleniumGenericKeywords, NonSeleniumGenericKeywords {

	private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
	public static Properties prop;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/**
	 * Taking Screenshot Utility - Configurable in Single Step
	 * @return - int
	 * @throws IOException
	 */
	public int takeSnap() throws IOException {
		int ranNum = (int) (Math.random()*999999+1000000);

		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File target = new File("./snaps/img"+ranNum+".png"); //img434234.png
		FileUtils.copyFile(source, target);

		return ranNum;
	}

	/**
	 *This method will launch the browser based on the browser arguments
	 * @Params - browser name (chrome, firefox)
	 * @throws IOException
	 */
	public void getDriverInstance(){
		String browser = getProperty("browser");
		switch (browser){
			case "chrome":
				driver.set(new ChromeDriver());
				break;
			case "firefox":
				driver.set(new FirefoxDriver());
				break;
		}
	}

	/**
	 *This method will initialize driver based on browser params, maximize the window
	 * and set the wait for 15 seconds for element location
	 * @Params - browser name (chrome, firefox)
	 * @throws - NoSuchElementExeception
	 */
	public void initializeDriver() {
		getDriverInstance();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait( Duration.ofSeconds(15));
	}

	/**
	 *This is a getter method to use the initialized browser driver
	 * @return - WebDriver object
	 */
	public WebDriver getDriver() {
		return driver.get();
	}


	/**
	 * This method load the new URL in the browser window and wait until the entire page load get complete
	 * @param url - URL to be loaded
	 */
	public void loadURL(String url) {
		getDriver().get(url);
	}

	/**
	 * This method will close the current browser window, and the driver session get expired
	 */
	public void closeBrowser() {
		getDriver().quit();
	}

	/**
	 *Using By class mechanism this method wait for 5 seconds for the element to present to be located with in the document
	 * @param by - reference for By
	 * @return - identified web element locator
	 */
	public WebElement getElement(By by){
		return waitUntilElementPresent(by);
	}

	/**
	 *This method is used to identify the element by using different locator type and locators by invoking get Element method
	 * @param locator - locator values
	 * @param locatorType - LocatorTypes
	 * @return - identified webElement
	 */
	public WebElement getElement(String locatorType, String locator){
		switch (locatorType){
			case "xpath":
				return getElement(By.xpath(locator));
			case "id":
				return getElement(By.id(locator));
			case "css":
				return getElement(By.cssSelector(locator));
			default:
				throw new RuntimeException("Invalid Locator type");
		}
	}

	/**
	 *This method is used enter give text into the object based on the locator and wait for 5 seconds until Presents of the web element
	 * @param  element - find and match the elements of web page
	 * @param inputValue - the text which enter into the object
	 */
	public void enterText(By element,String inputValue) {
		waitUntilElementPresent(element).sendKeys(inputValue);
	}

	/**
	 * This method is used click on the given object based on the locator and locator Type
	 * @param locator - locator values
	 * @param locatorType - LocatorTypes
	 */
	public void click(String locatorType,String locator){
		getElement(locatorType,locator).click();
	}

	/**
	 *  This method is used click on the given object based on the locator
	 *  and wait for 5 seconds until the web Element to be clickable
	 *  if failed to click in normal click action then catch will perform the Actions class click
	 *  and finally handle the javaScript executor to click the element directly in DOM
	 *  @param element - find and match the elements of web page
	 *
	 */
	public void clickElement(By element) {
		try{
			waitUntilElementClickable(element).click();
		}catch (ElementClickInterceptedException e){
			try{
				new Actions(getDriver()).click(waitUntilElementClickable(element));
			}catch (ElementClickInterceptedException e1) {
				((JavascriptExecutor)getDriver()).executeScript("arguments[0].click();", element);
			}
		}
	}

	/**
	 *This method used to get the text and wait for 5 seconds for element to be visible
	 * and get the text for the specific element text
	 * @param - element - find and match the elements of web page
	 * @param - expected - expected value
	 * @throws - WebDriverException
	 */
	public String getElementText(By element) {
		return waitUntilElementVisibility(element).getText();
	}

	/**
	 *This getter method will give a new Actions class object for keyboard or mouse action
	 * @return - actions class object
	 * @throws  - Exceptions
	 */
	public Actions getActionsObject() {
		return new Actions(getDriver());
	}

	/**
	 *This method wait for element to be visible and get the text for the specific element text
	 * @param - element - find and match the elements of web page
	 * @param - expected - expected value
	 * @throws - WebDriverException
	 */
	public void moveToElementUsingActions(By element) {
		waitUntilElementVisibility(element);
		getActionsObject().moveToElement(getElement(element)).build();
	}

	/**
	 *method will wait for web Element is present on DOM of the page
	 * @param - ele - find and match the elements of web page
	 * @throws - NoSuchElementException, ElementNotFoundExecption
	 */
	public WebElement waitUntilElementPresent(By ele){
		return wait.get().until(ExpectedConditions.presenceOfElementLocated(ele));
	}

	/**
	 *method will wait for web Element is clickable on DOM of the page
	 * @param - ele - find and match the elements of web page
	 * @throws - NoSuchElementException, ElementNotFoundExecption
	 */
	public WebElement waitUntilElementClickable(By ele){
		return wait.get().until(ExpectedConditions.elementToBeClickable(ele));
	}

	/**
	 *method will wait for web Element is visible in DOM of the page
	 * @param - ele - find and match the elements of web page
	 * @throws - NoSuchElementException, ElementNotFoundExecption
	 */
	public WebElement waitUntilElementVisibility(By ele){
		return wait.get().until(ExpectedConditions.visibilityOfElementLocated(ele));
	}

	/**
	 * Setter method to set the wait duration in seconds
	 * @param - getDriver - webdriver object
	 * @param - Duration in seconds
	 * @throws - NotFoundException
	 */
	public void setWebDriverWait() {
		wait.set(new WebDriverWait(getDriver(),Duration.ofSeconds(10)));
	}

	@Override
	public String getProperty(String propertyName) {
		return prop.getProperty(propertyName);
	}
}
