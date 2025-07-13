package PageObject;


import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;


public class RoomDetailsPage extends AbstractComponent {

	WebDriver driver;

	public RoomDetailsPage(WebDriver d) {

		super(d);
		this.driver = d;
		PageFactory.initElements(d, this);
	}

	// PageFactory
	
	@FindBy(id="doReservation")
	WebElement reserveButton;
	
	@FindBy(css=".card-body .d-flex span")
	WebElement chargePerNight;
	
	@FindBy(css=".card-body .d-flex.justify-content-between>span")
	WebElement roomChargeAllNight;
	
	@FindBy(css=".card-body .d-flex.justify-content-between>span+span")
	WebElement sumOfRoomChargeAllNight;
	
	@FindBy(xpath="//*[@id='root-container']/div/div[2]/div/div[2]/div/div/form/div[2]/div/div[2]/span[2]")
	WebElement cleaningfee;
	
	@FindBy(xpath="//*[@id='root-container']/div/div[2]/div/div[2]/div/div/form/div[2]/div/div[3]/span[2]")
	WebElement servicefee;
	
	@FindBy(css=".card-body .d-flex.justify-content-between.fw-bold span+span")
	WebElement totalAmount;
	
	@FindBy(css="input.form-control.room-firstname")
	WebElement firstNameforReservation;
	
	@FindBy(css="input.form-control.room-lastname")
	WebElement lastNameforReservation;
	
	@FindBy(css="input.form-control.room-email")
	WebElement emailIDforReservation;
	
	@FindBy(css="input.form-control.room-phone")
	WebElement phoneforReservation;
	
	@FindBy(xpath="//button[text()='Reserve Now']")
	WebElement finalReserveButton;
	
	@FindBy(css=".booking-card p")
	WebElement bookingconfirmMsg;
	
	@FindBy(css=".booking-card p+p")
	WebElement bookingconfirmDate;
	
	@FindBy(xpath="//a[text()='Return home']")
	WebElement returnHomeBtn;
	
	@FindBy(xpath="//h2[text()='Book This Room']")
	WebElement pageTitle;
	
	public void scrollToReserveButton() {
		
		scrollToElement(reserveButton);
		
	}
	
	public String getPageTitle() {
		
		return pageTitle.getText();
		
	}
	
	public void clickReserveButton() {
		
		reserveButton.click();
	}
	
	
	public String getChargePerNightText() {
	
		
		String chargePerNightText = chargePerNight.getText();
		System.out.println(chargePerNightText);
		
		return chargePerNightText;
		
	}
	
	public String getRoomChargeAllNightText() {
	
		
		String roomChargeAllNightText = roomChargeAllNight.getText();
		System.out.println(roomChargeAllNightText);
		
		return roomChargeAllNightText;
		
	}
	
	public String getSumOfRoomChargeAllNightText() {
	
		
		String sumOfRoomChargeAllNightText = sumOfRoomChargeAllNight.getText();
		System.out.println(sumOfRoomChargeAllNightText);
		
		return sumOfRoomChargeAllNightText;
		
	}
	
	public String getCleaningfeetextamount() {
	
		
		String cleaningFeeTextAmount = cleaningfee.getText();
		String trimcleaningfeetextamount = cleaningFeeTextAmount.replaceAll("£", "");
		System.out.println(cleaningFeeTextAmount+" "+trimcleaningfeetextamount);
		
		return trimcleaningfeetextamount;
		
	}
	
	public String getServiceFeeTextAmount() {
	
		
		String serviceFeeTextAmount = servicefee.getText();
		String trimservicefeetextamount = serviceFeeTextAmount.replaceAll("£", "");
		System.out.println(serviceFeeTextAmount+" "+trimservicefeetextamount);
		
		return trimservicefeetextamount;
		
	}
	
	public String getTotalAmountTextDisplay() {
	
		
		String totalAmountTextDisplay = totalAmount.getText();
		System.out.println(totalAmountTextDisplay);
		
		return totalAmountTextDisplay;
		
	}
	
	public String trimChargePerNightText() {
	
		
		String trimchargePerNightText = getChargePerNightText().replaceAll("£", "");
		System.out.println(trimchargePerNightText);
		
		return trimchargePerNightText;
		
	}
	
	public void getReservationDetails(String fname, String lname, String emailid, String phnum) {
	
		firstNameforReservation.sendKeys(fname);
		lastNameforReservation.sendKeys(lname);
		emailIDforReservation.sendKeys(emailid);
		phoneforReservation.sendKeys(phnum);
		
	}
	

	public void clickFinalReserveButton() {
		
	 finalReserveButton.click();
	 
	}
		
	public String getBookingConfirmMsg() {
	
		
		String bookingconfmsg = bookingconfirmMsg.getText();
		
		return bookingconfmsg;
		
	}
	
	public String getBookingConfirmDate() {
	
		
		String bookingconfdate = bookingconfirmDate.getText();
		
		return bookingconfdate;
		
	}
	
	public String goToReturnHome() {
		
		returnHomeBtn.click();
		return driver.getCurrentUrl();
	}

	
	public String changeDateFormatToYYYYMMDD(String day, String month, String year) {
		
		String day1 = null;
		String monthNumber1 = null;
		
		 int monthNumber = Month.valueOf(month.toUpperCase()).getValue();
	        if(monthNumber<=9) {
	        	monthNumber1 = String.format("%02d", monthNumber);
	        	//System.out.println(monthNumber1);
	        }else if(monthNumber>=10)
	        	monthNumber1=String.format("%01d", monthNumber);
	        
	        if(Integer.parseInt(day)<=9) {
	        	
	        	day1 = String.format("%02d", Integer.parseInt(day));
	        	//System.out.println(day1);
	        	
	        }else day1 = day;
	        
	       String date_DDMMYYYY = year+"-"+monthNumber1+"-"+day1;
	        
	      return date_DDMMYYYY;

}
}

