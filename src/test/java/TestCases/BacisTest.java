package TestCases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BacisTest {
	
	public static void main(String[] args) throws InterruptedException, ParseException {
		
		LocalDate ld = LocalDate.parse("2025-08-11",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		int day = ld.getDayOfYear();
		Month mmonth = ld.getMonth();
		int yyear = ld.getYear();
		System.out.println(mmonth);
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://automationintesting.online/");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'g-3')]")));
		Thread.sleep(5000);
		
		WebElement checkAvailabilityButton = driver.findElement(By.xpath("//button[text()='Check Availability']"));
		WebElement fromDatePicker= driver.findElement(By.xpath("//div[1]/div/div/input[@class='form-control']"));
		Actions a = new Actions(driver);
		
		a.scrollToElement(checkAvailabilityButton).build().perform();
		fromDatePicker.click();
		
		String monthYearVal = driver.findElement(By.xpath("//*[@class='react-datepicker__current-month']")).getText();
		
		System.out.println(monthYearVal);
		String month = monthYearVal.split(" ")[0].trim();
		String year = monthYearVal.split(" ")[1].trim();
		System.out.println(month);
		System.out.println(year);
		
		
		while(!(month.equalsIgnoreCase("August") && year.equals("2025"))) {
			
			driver.findElement(By.xpath("//button[@aria-label='Next Month']")).click();
			Thread.sleep(2000);
			monthYearVal = driver.findElement(By.xpath("//*[@class='react-datepicker__current-month']")).getText();
			
			
			month = monthYearVal.split(" ")[0].trim();
			year = monthYearVal.split(" ")[1].trim();
			
			driver.findElement(By.xpath("//div[text()='8']")).click();
		}
		
//		String actualfromdpDate = driver.findElement(By.xpath("//div[1]/div/div/input[@class='form-control']")).getAttribute("value");
//		String expectedfromdpdate = "08/08/2025";
//		
//		Assert.assertEquals(actualfromdpDate, expectedfromdpdate, "Date not matching!");
		
		WebElement toDatePicker= driver.findElement(By.xpath("//div[2]/div/div/input[@class='form-control']"));
		toDatePicker.click();
		
		String tomonthYearVal = driver.findElement(By.xpath("//*[@class='react-datepicker__current-month']")).getText();
		
		System.out.println(tomonthYearVal);
		String tomonth = tomonthYearVal.split(" ")[0].trim();
		String toyear = tomonthYearVal.split(" ")[1].trim();
		System.out.println(tomonth);
		System.out.println(toyear);
		
		
		while(!(tomonth.equalsIgnoreCase("August") && toyear.equals("2025"))) {
			
			driver.findElement(By.xpath("//button[@aria-label='Next Month']")).click();
			Thread.sleep(2000);
			tomonthYearVal = driver.findElement(By.xpath("//*[@class='react-datepicker__current-month']")).getText();
			
			
			tomonth = tomonthYearVal.split(" ")[0].trim();
			toyear = tomonthYearVal.split(" ")[1].trim();
			
			driver.findElement(By.xpath("//div[text()='11']")).click();
		}
		
		
		
		checkAvailabilityButton.click();
		
		
		WebElement booknowBtn= driver.findElement(By.xpath("//a[contains(@href,'/reservation/')]"));
		
		
		Actions b = new Actions(driver);
		
		b.scrollToElement(booknowBtn).build().perform();
		booknowBtn.click();
		
		
		Thread.sleep(5000);
		
//		String currentURL = driver.getCurrentUrl();
//		String fromDate = "2025-08-08";
//		String toDate = "2025-08-11";
//		String actualURL = "https://automationintesting.online/reservation/1?checkin="+fromDate+"&checkout="+toDate;
//		
//		Assert.assertEquals(currentURL,actualURL, "URL not matching!");
		
		
		
		WebElement reserveButton = driver.findElement(By.id("doReservation"));
		Actions c = new Actions(driver);
		
		c.scrollToElement(reserveButton).build().perform();
		
		LocalDate fromDate1 = LocalDate.parse("2025-08-08",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate toDate1 = LocalDate.parse("2025-08-11",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		long daysDiff = ChronoUnit.DAYS.between(fromDate1,toDate1);
		System.out.println(daysDiff);
		
		WebElement chargePerNight = driver.findElement(By.cssSelector(".card-body .d-flex span"));
		String chargePerNightText = chargePerNight.getText();
		System.out.println(chargePerNightText);
		String trimchargePerNightText = chargePerNightText.replaceAll("£", "");
		System.out.println(trimchargePerNightText);
		
		WebElement roomChargeAllNight = driver.findElement(By.cssSelector(".card-body .d-flex.justify-content-between>span"));
		String actualRoomChargeAllNightText = roomChargeAllNight.getText();
		String expectedRoomChargeAllNightText = chargePerNightText+" x "+daysDiff+" nights";
		Assert.assertEquals(actualRoomChargeAllNightText, expectedRoomChargeAllNightText, "All Night Room Charge text not matching!");

		
		WebElement sumOfRoomChargeAllNight = driver.findElement(By.cssSelector(".card-body .d-flex.justify-content-between>span+span"));
		String actualSumOfRoomChargeAllNightText = sumOfRoomChargeAllNight.getText();
		String expectedSumOfRoomChargeAllNightText = "£"+Integer.parseInt(trimchargePerNightText)*(int)daysDiff;
		Assert.assertEquals(actualSumOfRoomChargeAllNightText, expectedSumOfRoomChargeAllNightText, "Sum amount did not match!");
		String trimexpectedSumOfRoomChargeAllNightText = expectedSumOfRoomChargeAllNightText.replaceAll("£", "");
		
		WebElement cleaningfee=driver.findElement(By.xpath("//*[@id='root-container']/div/div[2]/div/div[2]/div/div/form/div[2]/div/div[2]/span[2]"));
		String cleaningfeetextamount = cleaningfee.getText();
		String trimcleaningfeetextamount = cleaningfeetextamount.replaceAll("£", "");

		WebElement servicefee = driver.findElement(By.xpath("//*[@id='root-container']/div/div[2]/div/div[2]/div/div/form/div[2]/div/div[3]/span[2]"));
		String servicefeetextamount = servicefee.getText();
		String trimservicefeetextamount = servicefeetextamount.replaceAll("£", "");
		
		WebElement totalAmount = driver.findElement(By.cssSelector(".card-body .d-flex.justify-content-between.fw-bold span+span"));
		
		String totalAmountTextDisplay = totalAmount.getText();
		int expectedTotal= Integer.parseInt(trimexpectedSumOfRoomChargeAllNightText)+Integer.parseInt(trimcleaningfeetextamount)+Integer.parseInt(trimservicefeetextamount);
		String expectedTotalAmount = "£"+expectedTotal;
		Assert.assertEquals(totalAmountTextDisplay, expectedTotalAmount, "Total amount did not match!");
		
		reserveButton.click();
		
		WebElement firstName = driver.findElement(By.cssSelector("input.form-control.room-firstname"));
		firstName.sendKeys("Sonali");
		
		WebElement lastName = driver.findElement(By.cssSelector("input.form-control.room-lastname"));
		lastName.sendKeys("Dose");
		
		WebElement emailIDforReservation = driver.findElement(By.cssSelector("input.form-control.room-email"));
		emailIDforReservation.sendKeys("Abcd@gmail.com");
		
		WebElement phoneforReservation = driver.findElement(By.cssSelector("input.form-control.room-phone"));
		phoneforReservation.sendKeys("12345678900");
		
		WebElement reserveNowButton = driver.findElement(By.xpath("//button[text()='Reserve Now']"));
		reserveNowButton.click();
		
		
		
		
	}

}
