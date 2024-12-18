package almosaferTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class almosaferProject {

	WebDriver driver = new ChromeDriver();

	String website = "https://www.almosafer.com/en";

	Random rand = new Random();

	@BeforeTest

	public void setUp() {

		driver.manage().window().maximize();

		driver.get(website);

		driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div/button[1]")).click();

	}

	@Test(priority = 1)
	public void languageEn() {

		String ActualLaguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpectedLanguage = "en";

		Assert.assertEquals(ActualLaguage, ExpectedLanguage);

	}

	@Test(priority = 2)
	public void currencySar() {

		String AcutalCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();

		String ExpectedCurrency = "SAR";

		Assert.assertEquals(AcutalCurrency, ExpectedCurrency);

	}

	@Test(priority = 3)
	public void contactNumber() {

		String AcutalNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();

		String ExpectedNumber = "+966554400000";

		Assert.assertEquals(AcutalNumber, ExpectedNumber);

	}

	@Test(priority = 4)
	public void qitaf() {

		WebElement TheFooter = driver.findElement(By.tagName("footer"));

		boolean AcutalLogo = TheFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();

		boolean ExpectedLogo = true;

		Assert.assertEquals(AcutalLogo, ExpectedLogo);
	}

	@Test(priority = 5)
	public void hotelsNotSelected() {

		WebElement selectPerson = driver.findElement(By.id("uncontrolled-tab-example-tabpane-hotels"));

		boolean Actual = selectPerson.isDisplayed();

		boolean Expected = false;

		Assert.assertEquals(Actual, Expected);

	}

	@Test(priority = 6)
	public void flightDeparture() {
		int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
		String ExpectedDate = String.format("%02d", tomorrow);

		String AcutalDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();

		Assert.assertEquals(AcutalDate, ExpectedDate);
	}

	@Test(priority = 7)
	public void flightReturn() {
		int Aftertomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
		String ExpectRutern = String.format("%02d", Aftertomorrow);

		String AcutalRutern = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();

		Assert.assertEquals(AcutalRutern, ExpectRutern);
	}

	@Test(priority = 8)
	public void randomLanguage() throws InterruptedException {

		String[] myWebsites = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

		String[] arabicCitys = { "دبي", "جدة" };
		String[] englishCitys = { "Dubai", "Jeddah", "Riyadh" };

		int arabicRand = rand.nextInt(arabicCitys.length);
		int englishRand = rand.nextInt(englishCitys.length);

		int randWebsite = rand.nextInt(myWebsites.length);

		driver.get(myWebsites[randWebsite]);

		WebElement hotelButton = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelButton.click();

		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		if ("ar".equals(actualLanguage)) {

			WebElement cityInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input "));
			cityInput.sendKeys(arabicCitys[arabicRand]);

			String expectedLanguage = "ar";
			Assert.assertEquals(actualLanguage, expectedLanguage);

		} else {

			WebElement cityInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input "));
			cityInput.sendKeys(englishCitys[englishRand]);

			String expectedLanguage = "en";
			Assert.assertEquals(actualLanguage, expectedLanguage);
		}
		
		Thread.sleep(2000);
		
		WebElement firstCity = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		firstCity.findElements(By.tagName("li")).get(1).click();
	
		
	    WebElement selectVedtor = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		
		Select select = new Select(selectVedtor);
		
		int randomVestorNumber =  rand.nextInt(2);
		
		select.selectByIndex(randomVestorNumber);
	
		WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		
		searchButton.click();
	}
	
	@Test(priority = 9)
	public void loadPage () throws InterruptedException {
		
		Thread.sleep(20000);
		
		WebElement result = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
		
	boolean AcutalResult = 	result.getText().contains("مكان") || result.getText().contains("found")  ;
	
	boolean ExpectedRuselt = true ;
	
	Assert.assertEquals(AcutalResult, ExpectedRuselt);
		
		
	}
	
	@Test(priority = 10 )
	public void lowestPrice() throws InterruptedException {
		
		Thread.sleep(15000);
		
	WebElement lowPriceButton =	driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
	lowPriceButton.click();      
	
	
	List<WebElement> prices = driver.findElements(By.cssSelector(".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muiltr-18vmb2l"));

	if(driver.getCurrentUrl().contains("en")) {
		
		int lowestPrice =  Integer.parseInt(prices.get(1).getText().replace("SAR ",""));
		
		int highestPrice = Integer.parseInt(prices.get(prices.size()-1).getText().replace("SAR ",""));
		
		boolean Acutual = lowestPrice<highestPrice;
		boolean Expected = true ;
		
		Assert.assertEquals(Acutual, Expected);
	}
	else {
	int lowestPrice =  Integer.parseInt(prices.get(1).getText().replace("ر.س.‏ ",""));
		
		int highestPrice = Integer.parseInt(prices.get(prices.size()-1).getText().replace("ر.س.‏ ",""));
		
		boolean Acutual = lowestPrice<highestPrice;
		boolean Expected = true ;
		
		Assert.assertEquals(Acutual, Expected);
		
	}
			
	}
	
	
	
	
	
	
	
	
	
	

}
