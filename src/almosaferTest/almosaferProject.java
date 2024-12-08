package almosaferTest;

import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
	public void randomLanguage() {

		String[] myWebsites = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

		int randWebsite = rand.nextInt(myWebsites.length);

		driver.get(myWebsites[randWebsite]);

		String ActualLaguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		if (ActualLaguage == "ar") {

			String ExpectedLanguage = "ar";
			Assert.assertEquals(ActualLaguage, ExpectedLanguage);
		} else {

			String ExpectedLanguage = "en";

			Assert.assertEquals(ActualLaguage, ExpectedLanguage);

		}

	}

}
