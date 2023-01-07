package pages;

import com.aventstack.extentreports.Status;
import org.testng.Reporter;

import base.GenericKeywords;
import pageObjects.IMDBPageObjects;
import reporter.ExtentReport;

public class IMDBPage extends GenericKeywords {

	IMDBPageObjects imdbPageObjects = new IMDBPageObjects();

	/**
	  * Loading IMDB Url
	  * @return void
	  */
	public void loadIMDBUrl() {
		loadURL(getProperty("IMDB_Url"));
		ExtentReport.log(Status.INFO,"IMDB website loaded :"+getProperty("IMDB_Url"));
	}

	/**
	  * Entering Movie Name in IMDB Search Box
	  * @param movieName
	  * @return void
	  */
	public void enterFilmNameInSearchBox(String movieName){
		enterText(imdbPageObjects.searchBox_input, movieName);
		ExtentReport.log(Status.INFO,movieName+" is searched in search box");
	}


	/**
	  * Selecting the relevant movie from the searchBoxResult
	  * Using replace method to make the search dynamic
	  * @param movieName
	  * @return void
	  */
	public void clickMovie(String movieName){
		click("xpath",imdbPageObjects.imdbMovieName_text.replace("movieNameToReplace", movieName));
		ExtentReport.log(Status.INFO,movieName+" from searchresult is clicked");
	}

	/**
	  * Getting Release Date From IMDB
	  * Since Release Date contains Country, it is removed
	  * @return String - value of release date from IMDB
	  */
	public String getReleaseDate(){
		moveToElementUsingActions(imdbPageObjects.imdbMovieReleaseDate_text);
		String date = getElementText(imdbPageObjects.imdbMovieReleaseDate_text).split(" \\(")[0];
		ExtentReport.log(Status.INFO,"Release date from IMDB is fetched as "+date);
		return date;
	}
	
	/**
	  * Getting Release Country From IMDB
	  * @return String - value of release country from IMDB
	  */
	public String getReleaseCountry(){
		String country = getElementText(imdbPageObjects.imdbMovieReleaseCountry_text);
		ExtentReport.log(Status.INFO,"Release Country from IMDB is fetched as "+country);
		return country;
	}
}
