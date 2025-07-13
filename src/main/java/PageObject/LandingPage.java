package PageObject;


import java.time.Month;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponent.AbstractComponent;


public class LandingPage extends AbstractComponent {

	WebDriver driver;
	//JavascriptExecutor js = (JavascriptExecutor)driver;

	public LandingPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	// PageFactory
	
	@FindBy(css="h1.display-4")
	WebElement pageName;
	
	@FindBy(xpath="//button[text()='Check Availability']")
	WebElement checkAvailabilityButton;
	
	@FindBy(xpath = "//div[1]/div/div/input[@class='form-control']")
	WebElement checkinDatePicker;
	
	@FindBy(xpath = "//div[2]/div/div/input[@class='form-control']")
	WebElement checkoutDatePicker;
	
	@FindBy(xpath = "//*[@class='react-datepicker__current-month']")
	WebElement monthYearVal;
	
	@FindBy(xpath="//div[contains(@class,'g-3')]")
	WebElement checkAvailabilityRow;
	
	@FindBy(css="button.react-datepicker__navigation--next")
	WebElement nextMonthArrow;
	
	@FindBy(css="button.react-datepicker__navigation--previous")
	WebElement prevMonthArrow;
	
	@FindBy(css="h2.display-5")
	WebElement ourRoomPanel;
	
	@FindBy(css=".room-card .card-footer a")
	List<WebElement> booknowButtons;
	
	@FindBy(linkText="Contact")
	WebElement contact;
	
	@FindBy(xpath="//h3[text()='Send Us a Message']")
	WebElement sendMessage;
	
	@FindBy(css="#name")
	WebElement name;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement sendMsg_SubmitBtn;
	
	@FindBy(id="email")
	WebElement emailID;
	
	@FindBy(css="#phone")
	WebElement phoneNo;
	
	@FindBy(id="subject")
	WebElement MsgSubject;
	
	@FindBy(id="description")
	WebElement msgDescription;
	
	@FindBy(xpath="//*[@id='contact']/div/div/div/div/div/h3")
	WebElement message1;
	
	@FindBy(xpath="//*[@id='contact']/div/div/div/div/div/p[1]")
	WebElement message2;
	
	@FindBy(xpath="//*[@id='contact']/div/div/div/div/div/p[2]")
	WebElement message3;
	
	@FindBy(xpath="//*[@id='contact']/div/div/div/div/div/p[3]")
	WebElement message4;
	
	@FindBy(css=".alert-danger p")
	WebElement errorPanel;
	
	By ourRoomPanel1= By.cssSelector("h2.display-5");

	@FindBy(css=".room-card")
	WebElement room_card;
	
	public String checkPageName() {

		return pageName.getText();
	}
	
	public void goTo() {

		driver.get("https://automationintesting.online/");
	}
	
	
	public void waitForRoomToApprear() {
		
		waitForElementToApprear(ourRoomPanel1);
	}
	
	public void scrollToCheckAvailabilityElement() {
		
		scrollToElement(checkAvailabilityButton);
		
	}
	
	public void scrollToRoomCard() {
		
		scrollToElement(room_card);
		
	}
	
	public void clickCheckAvailability() {
		
		checkAvailabilityButton.click();
	}
	
	public void goToCheckindatePicker() {
		
		checkinDatePicker.click();
		
	}
	

	public void goToCheckoutdatePicker() {
		
		checkoutDatePicker.click();
		
	}
	
	public void datePickerAction(String exDay, String exMonth, String exYear) {
	
	
	String monthYearValText=monthYearVal.getText();
	
	//System.out.println(monthYearValText);
	String month = monthYearValText.split(" ")[0].trim();
	String year = monthYearValText.split(" ")[1].trim();
	//System.out.println(month);
	//System.out.println(year);
	
	
	while(!(month.equals(exMonth) && year.equals(exYear))) {
		
		nextMonthArrow.click();
		String monthYearValTextinloop = monthYearVal.getText();
		
		month = monthYearValTextinloop.split(" ")[0].trim();
		year = monthYearValTextinloop.split(" ")[1].trim();
		
	}
		
		driver.findElement(By.xpath("//div[text()='"+exDay+"']")).click();
	}
	
	public void datePickerAction_forpastdate(String exDay, String exMonth, String exYear) {
		
		
		String monthYearValText=monthYearVal.getText();
		
		System.out.println(monthYearValText);
		String month = monthYearValText.split(" ")[0].trim();
		String year = monthYearValText.split(" ")[1].trim();
		System.out.println(month);
		System.out.println(year);
		
		
		while(!(month.equals(exMonth) && year.equals(exYear))) {
			
			prevMonthArrow.click();
			String monthYearValTextinloop = monthYearVal.getText();
			System.out.println(monthYearValTextinloop);
			month = monthYearValTextinloop.split(" ")[0].trim();
			System.out.println(month);
			year = monthYearValTextinloop.split(" ")[1].trim();
			
		}
			
			driver.findElement(By.xpath("//div[text()='"+exDay+"']")).click();
		}
	
	public String checkRoomPanelText() {
		
		return ourRoomPanel.getText();
		
	}
	
	public void clickToBookNow() {
		
//		String clickBookNowBtn ="document.querySelectorAll(\".room-card .card-footer a[href]\")[0].click()";
//		js.executeScript(clickBookNowBtn);
		
		for(WebElement booknowButton : booknowButtons) {
			
			 String value = booknowButton.getDomAttribute("href"); 
			 //System.out.println(booknowButton);
			 if (value!=null) {
				 booknowButton.click();	 
			 break;
		}else if(value==null) 
			driver.close();
			 }
		
		
	}
	
	public String changeDateFormatToDDMMYYYY(String day, String month, String year) {
		
		String day1 = null;
		String monthNumber1 = null;
		
		 int monthNumber = Month.valueOf(month.toUpperCase()).getValue();
	        if(monthNumber<=9) {
	        	monthNumber1 = String.format("%02d", monthNumber);
	        	//System.out.println(monthNumber);
	        }else if(monthNumber>=10)
	        	monthNumber1=String.format("%01d", monthNumber);
	        
	        if(Integer.parseInt(day)<=9) {
	        	
	        	day1 = String.format("%02d", Integer.parseInt(day));
	        	//System.out.println(day1);
	        	
	        }else day1 = day;
	        
	       String date_DDMMYYYY = day1+"/"+monthNumber1+"/"+year;
	        
	      return date_DDMMYYYY;

}
	
	public String actualchkinDate() {
		
		return checkinDatePicker.getAttribute("value");
	}
	
	public String actualchkoutDate() {
		
		return checkoutDatePicker.getAttribute("value");
	}
	
	public void goToContact() {
		
		contact.click();
		
	}
	
	public void sendMessageMethod(String fullname, String email, String phno, String sub, String desc) {
		
		name.sendKeys(fullname);
		
		emailID.sendKeys(email);
		
		phoneNo.sendKeys(phno);
		
		MsgSubject.sendKeys(sub);
		
		msgDescription.sendKeys(desc);

		sendMsg_SubmitBtn.click();
		
	}
	
	public String validateSendMessageSection() {
		
		return sendMessage.getText();
	}
	
	public String validateMessage1() {
		
		return message1.getText();
	}
	
	public String validateMessage2() {
		
		return message2.getText();
	}
	
	public String validateMessage3() {
		
		return message3.getText();
	}
	
	public String validateMessage4() {
		
		return message4.getText();
	}
	
	public String validateErrorMessage() {
		
		return errorPanel.getText();
	}

}

