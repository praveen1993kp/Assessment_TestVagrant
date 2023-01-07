package testcases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.aventstack.extentreports.Status;
import org.testng.annotations.*;
import reporter.ExtentReport;
import testExecutionEngine.ExecutionEngine;
import org.testng.Assert;

import pages.IMDBPage;
import pages.WikipediaPage;

public class ValidateMovieDetailsTest extends ExecutionEngine {
	IMDBPage imdbPage = new IMDBPage();
	WikipediaPage wikipediaPage = new WikipediaPage();
	static ExtentReport extentReport = new ExtentReport();

	@BeforeMethod
	public void createNode(Object[] testData){
		extentReport.createNode("Validation Movie Details for :"+String.valueOf(testData[0]));
	}


	@Test(description = "Validate Movie Release Date And Country", dataProvider = "movieList")
	public void validateMovieDetails(String movieName) throws ParseException {
		imdbPage.loadIMDBUrl();
		imdbPage.enterFilmNameInSearchBox(movieName);
		imdbPage.clickMovie(movieName);
		Date releaseDate_IMDB = new SimpleDateFormat("MMMM dd, yyyy").parse(imdbPage.getReleaseDate());
		String releaseCountry_IMDB = imdbPage.getReleaseCountry();

		wikipediaPage.loadWikiUrl();
		wikipediaPage.enterFilmNameInSearchBox(movieName);
		wikipediaPage.clickMovie(movieName);

		Assert.assertEquals(releaseDate_IMDB,  new SimpleDateFormat("dd MMMM yyyy").parse(wikipediaPage.getReleaseDate()));
		ExtentReport.log(Status.INFO, "Assertion successful for movie release date");
		Assert.assertEquals(releaseCountry_IMDB, wikipediaPage.getReleaseCountry());
		ExtentReport.log(Status.INFO, "Assertion successful for movie release country");
		ExtentReport.log(Status.PASS, "Validation is Successful for "+movieName);
	}

	@DataProvider(parallel = true)
	public Object[][] movieList(){
		String[] movieList = imdbPage.getProperty("Movie_Name").split(";;;");
		Object[][] data = new Object[movieList.length][1];

		for(int i =0; i < movieList.length;i++)
			data[i][0] = movieList[i];

		return data;
	}


}
