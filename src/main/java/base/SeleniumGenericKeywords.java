package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface SeleniumGenericKeywords {
	
	public void getDriverInstance();

	public void initializeDriver();
	
	public WebDriver getDriver();
	
	public void loadURL(String url);

	public void closeBrowser();

	public WebElement getElement(By by);

	public WebElement getElement(String locatorType, String locator);
	
	public void enterText(By element,String inputValue);

	public void clickElement(By element);

	public String getElementText(By element);

	public Actions getActionsObject();

	public void moveToElementUsingActions(By element);

	public WebElement waitUntilElementPresent(By ele);
	
	public WebElement waitUntilElementClickable(By ele);

	public WebElement waitUntilElementVisibility(By ele);

	public void setWebDriverWait();
}
