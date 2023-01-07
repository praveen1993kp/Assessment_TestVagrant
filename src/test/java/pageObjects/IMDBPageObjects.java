package pageObjects;

import org.openqa.selenium.By;

public class IMDBPageObjects {

    public By searchBox_input = By.xpath("//input[@id='suggestion-search']");
    public String imdbMovieName_text ="//div[contains(text(),'movieNameToReplace')]//parent::div//parent::a";
    public By imdbMovieReleaseDate_text =By.xpath("//a[text()='Release date']//following-sibling::div//a");
    public By imdbMovieReleaseCountry_text =By.xpath("//button[text()='Country of origin']//following-sibling::div//a");

}
