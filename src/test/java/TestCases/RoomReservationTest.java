package TestCases;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.LandingPage;
import PageObject.RoomDetailsPage;
import TestComponents.BaseTest;


public class RoomReservationTest extends BaseTest{
	
	@Test(dataProvider="getData")
	public void successfulRoomReservation(HashMap<String, String> input) throws InterruptedException, ParseException, IOException {
		
		LandingPage landingpage = new LandingPage(driver);
		RoomDetailsPage roomdetailspage = new RoomDetailsPage(driver);
		
		//TestStep1: Hit URL: https://automationintesting.online/ and User should be landed on "Welcome to Shady Meadows B&B" page
		String actualPageName = landingpage.checkPageName();
		String expPageName = "Welcome to Shady Meadows B&B";
		Assert.assertEquals(actualPageName, expPageName, "Page name did not match!");
		
		//TestStep2: Scroll to Check Availability section, enter 'CheckIn' and 'CheckOut' date
		
		landingpage.scrollToCheckAvailabilityElement();
		
		landingpage.goToCheckindatePicker();
		
		landingpage.datePickerAction(input.get("CheckInDay"), input.get("CheckInMonth"), input.get("CheckInYear"));
		
		landingpage.goToCheckoutdatePicker();
		
		landingpage.datePickerAction(input.get("CheckOutDay"), input.get("CheckOutMonth"), input.get("CheckOutYear"));
		
		String actualchkinDate = landingpage.actualchkinDate();
		String expectedchkinDate =landingpage.changeDateFormatToDDMMYYYY(input.get("CheckInDay"), input.get("CheckInMonth"), input.get("CheckInYear"));
		
		Assert.assertEquals(actualchkinDate, expectedchkinDate, "Date not matching!");
		
		String actualchkoutDate = landingpage.actualchkoutDate();
		String expectedchkoutDate =landingpage.changeDateFormatToDDMMYYYY(input.get("CheckOutDay"), input.get("CheckOutMonth"), input.get("CheckOutYear"));
		
		Assert.assertEquals(actualchkoutDate, expectedchkoutDate, "Date not matching!");
		
		//TestStep3: Click on 'Check Availability' button
		
		landingpage.clickCheckAvailability();
		
		landingpage.waitForRoomToApprear();
		
		landingpage.scrollToRoomCard();
		
		String actualOurRoomText = landingpage.checkRoomPanelText();
		String expOurRoomText = "Our Rooms";
		
		Assert.assertEquals(actualOurRoomText, expOurRoomText, "Text not matching!");
		
		
		//TestStep4: Click 'Book now' button of the first room appears under 'Our Rooms' section
		
		landingpage.clickToBookNow();
		
		String actualPageTitle = roomdetailspage.getPageTitle();
		String expPageTitle = "Book This Room";
		
		Assert.assertEquals(actualPageTitle,  expPageTitle, "Landed on wrong page!");
		
		
		roomdetailspage.scrollToReserveButton();
		
		Thread.sleep(5000);
		
		LocalDate fromDate = LocalDate.parse(roomdetailspage.changeDateFormatToYYYYMMDD(input.get("CheckInDay"), input.get("CheckInMonth"), input.get("CheckInYear")),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate toDate = LocalDate.parse(roomdetailspage.changeDateFormatToYYYYMMDD(input.get("CheckOutDay"), input.get("CheckOutMonth"), input.get("CheckOutYear")),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		
		long daysDiff = ChronoUnit.DAYS.between(fromDate,toDate);
		//System.out.println(daysDiff);
		
		//TestStep5: Check the 'Price Summary' section for the following: Room Charge All Night, Sum amount of Room charge all night, Total amount
		
		String expectedRoomChargeAllNightText = roomdetailspage.getChargePerNightText()+" x "+daysDiff+" nights";
		Assert.assertEquals(roomdetailspage.getRoomChargeAllNightText(), expectedRoomChargeAllNightText, "All Night Room Charge text not matching!");

		
		String expectedSumOfRoomChargeAllNightText = "£"+Integer.parseInt(roomdetailspage.trimChargePerNightText())*(int)daysDiff;
		Assert.assertEquals(roomdetailspage.getSumOfRoomChargeAllNightText(), expectedSumOfRoomChargeAllNightText, "Sum amount did not match!");
		String trimexpectedSumOfRoomChargeAllNightText = expectedSumOfRoomChargeAllNightText.replaceAll("£", "");
		
		int expectedTotal= Integer.parseInt(trimexpectedSumOfRoomChargeAllNightText)+Integer.parseInt(roomdetailspage.getCleaningfeetextamount())+Integer.parseInt(roomdetailspage.getServiceFeeTextAmount());
		String expectedTotalAmount = "£"+expectedTotal;
		Assert.assertEquals(roomdetailspage.getTotalAmountTextDisplay(), expectedTotalAmount, "Total amount did not match!");
		
		//TestStep6: Click on 'Reserve now' button
		
		roomdetailspage.clickReserveButton();
		Thread.sleep(5000);
		
		//TestStep7: Enter all the form details and click on 'Reserve now' button
		
		roomdetailspage.getReservationDetails(input.get("FirstNameForReservation"),input.get("LastNameForReservation"), input.get("EmailIDForReservation"), input.get("PhoneForReservation"));
		Thread.sleep(5000);
		
		roomdetailspage.clickFinalReserveButton();
		
		
		String expBookingconfmsg = "Your booking has been confirmed for the following dates:";
		Assert.assertEquals(roomdetailspage.getBookingConfirmMsg(), expBookingconfmsg, "Message did not match!");
		
		String expBookingconfdate = fromDate+" - "+toDate;
		Assert.assertEquals(roomdetailspage.getBookingConfirmDate(), expBookingconfdate, "Date did not match!");
		
		//TestStep8: Click on 'Return Home' button
		
		String actualHomeURL = roomdetailspage.goToReturnHome();
		String expHomeURL = "https://automationintesting.online/";
		Assert.assertEquals(actualHomeURL, expHomeURL, "Landed on wrong page!");
		
		
	}
	
	@Test(dataProvider="getData")
	public void checkAvailabilityTest_backdatedCheckInAndCheckOutDate(HashMap<String, String> input) throws InterruptedException, ParseException, IOException {
		
		LandingPage landingpage = new LandingPage(driver);
		
		//TestStep1: Hit URL: https://automationintesting.online/ and User should be landed on "Welcome to Shady Meadows B&B" page
		String actualPageName = landingpage.checkPageName();
		String expPageName = "Welcome to Shady Meadows B&B";
		Assert.assertEquals(actualPageName, expPageName, "Page name did not match!");
		
		//TestStep2: Scroll to Check Availability section, enter 'CheckIn' and 'CheckOut' date
		
		landingpage.scrollToCheckAvailabilityElement();
		
		landingpage.goToCheckindatePicker();
		
		landingpage.datePickerAction_forpastdate(input.get("BackDatedCheckInDay"), input.get("BackDatedCheckInMonth"), input.get("CheckInYear"));
		
		landingpage.goToCheckoutdatePicker();
		
		landingpage.datePickerAction_forpastdate(input.get("BackDatedCheckOutDay"), input.get("BackDatedCheckOutMonth"), input.get("CheckOutYear"));
		
		String actualchkinDate = landingpage.actualchkinDate();
		String expectedchkinDate =landingpage.changeDateFormatToDDMMYYYY(input.get("BackDatedCheckInDay"), input.get("BackDatedCheckInMonth"), input.get("CheckInYear"));
		
		Assert.assertEquals(actualchkinDate, expectedchkinDate, "Date not matching!");
		
		String actualchkoutDate = landingpage.actualchkoutDate();
		String expectedchkoutDate =landingpage.changeDateFormatToDDMMYYYY(input.get("BackDatedCheckOutDay"), input.get("BackDatedCheckOutMonth"), input.get("CheckOutYear"));
		
		Assert.assertEquals(actualchkoutDate, expectedchkoutDate, "Date not matching!");
		
		//TestStep3: Click on 'Check Availability' button
		
		landingpage.clickCheckAvailability();
		
		String actualErrorMessage = landingpage.checkRoomPanelText();
		String expErrorMessage = "Check In date and Check Out date cannot be backdated.";
		
		Assert.assertEquals(actualErrorMessage, expErrorMessage, "Text not matching!");
		
		Thread.sleep(5000);
		
		
	}
	
	@Test(dataProvider="getData")
	public void checkAvailabilityTest_CheckOutDateLesserThanCheckInDate(HashMap<String, String> input) throws InterruptedException, ParseException, IOException {
		
		LandingPage landingpage = new LandingPage(driver);
		
		//TestStep1: Hit URL: https://automationintesting.online/ and User should be landed on "Welcome to Shady Meadows B&B" page
		String actualPageName = landingpage.checkPageName();
		String expPageName = "Welcome to Shady Meadows B&B";
		Assert.assertEquals(actualPageName, expPageName, "Page name did not match!");
		
		//TestStep2: Scroll to Check Availability section,enter future 'CheckIn', enter 'CheckOut' date lesser than checkin date
		
		landingpage.scrollToCheckAvailabilityElement();
		
		landingpage.goToCheckindatePicker();
		
		landingpage.datePickerAction(input.get("CheckInDay"), input.get("CheckInMonth"), input.get("CheckInYear"));
		
		landingpage.goToCheckoutdatePicker();
		
		landingpage.datePickerAction(input.get("CheckOutDayLesserThanCheckIn"), input.get("CheckOutMonth"), input.get("CheckOutYear"));
		
		String actualchkinDate = landingpage.actualchkinDate();
		String expectedchkinDate =landingpage.changeDateFormatToDDMMYYYY(input.get("CheckInDay"), input.get("CheckInMonth"), input.get("CheckInYear"));
		
		Assert.assertEquals(actualchkinDate, expectedchkinDate, "Date not matching!");
		
		String actualchkoutDate = landingpage.actualchkoutDate();
		String expectedchkoutDate =landingpage.changeDateFormatToDDMMYYYY(input.get("CheckOutDayLesserThanCheckIn"), input.get("CheckOutMonth"), input.get("CheckOutYear"));
		
		Assert.assertEquals(actualchkoutDate, expectedchkoutDate, "Date not matching!");
		
		//TestStep3: Click on 'Check Availability' button
		
		landingpage.clickCheckAvailability();
		
		String actualErrorMessage = landingpage.checkRoomPanelText();
		String expErrorMessage = "Check Out Date cannot be lesser than Check In Date";
		
		Assert.assertEquals(actualErrorMessage, expErrorMessage, "Text not matching!");
		
		Thread.sleep(5000);
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//TestData//RoomReservationData.json");
		return new Object[][] {{data.get(0)}};

	}
	
	
}
