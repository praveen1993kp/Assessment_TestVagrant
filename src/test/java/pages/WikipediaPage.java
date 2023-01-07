package pages;

import com.aventstack.extentreports.Status;
import org.testng.Reporter;

import base.GenericKeywords;
import pageObjects.WikipediaPageObjects;
import reporter.ExtentReport;

public class WikipediaPage extends GenericKeywords {

	WikipediaPageObjects wikipediaPageObjects = new WikipediaPageObjects();

	/**
	  * Loading Wiki Url
	  */
	public void loadWikiUrl(){
		loadURL(getProperty("Wiki_Url"));
		ExtentReport.log(Status.INFO,"Wikipedia website loaded :"+getProperty("Wiki_Url"));
	}

	/**
	  * Entering Movie Name in Wiki Search Box
	  * @param movieName - Name of the movie
	  */
	public void enterFilmNameInSearchBox(String movieName){
		enterText(wikipediaPageObjects.searchBox_input, movieName);
		ExtentReport.log(Status.INFO,movieName+" is entered in wiki search box");
	}

	/**
	  * Selecting the relevant movie from the searchBoxResult
	  * Using replace method to make the search dynamic
	  * @param movieName
	  * @return void
	  */
	public void clickMovie(String movieName){
		clickElement(wikipediaPageObjects.wikiSearchResult_text);
		ExtentReport.log(Status.INFO,movieName+" from searchresult is clicked");
	}

	/**
	  * Getting Release Date From Wiki
	  * @return String - value of release date from Wiki
	  */
	public String getReleaseDate(){
		moveToElementUsingActions(wikipediaPageObjects.wikiReleaseDate_text);
		String date = getElementText(wikipediaPageObjects.wikiReleaseDate_text);
		ExtentReport.log(Status.INFO,"Release date from wiki is fetched as "+date);
		return date;
	}

	/**
	  * Getting Release Country From Wiki
	  * @return String - value of release country from Wiki
	  */
	public String getReleaseCountry(){
		String country = getElementText(wikipediaPageObjects.wikiReleaseCountry_text);
		ExtentReport.log(Status.INFO,"Release Country from wiki is fetched as "+country );
		return country;
	}
}
