package pageObjects;

import org.openqa.selenium.By;

public class WikipediaPageObjects {

    public By searchBox_input = By.id("searchInput");
    public By wikiSearchResult_text =By.xpath("//p[contains(text(),'film')]//preceding-sibling::h3");
    public By wikiReleaseDate_text =By.xpath("//div[text()='Release date']//parent::th//following-sibling::td//div//li");
    public By wikiReleaseCountry_text =By.xpath("//th[text()='Country']//following-sibling::td");
}