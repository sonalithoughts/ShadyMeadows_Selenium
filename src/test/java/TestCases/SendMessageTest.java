package TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.LandingPage;
import TestComponents.BaseTest;

public class SendMessageTest extends BaseTest{
	
	@Test(dataProvider="getData")
	public void SendMessage_Successful(HashMap<String, String> input) throws InterruptedException {
		
		LandingPage landingpage = new LandingPage(driver);
		
		//TestStep1: Hit URL: https://automationintesting.online/ and User should be landed on "Welcome to Shady Meadows B&B" page
		
		String actualPageName = landingpage.checkPageName();
		String expPageName = "Welcome to Shady Meadows B&B";
		Assert.assertEquals(actualPageName, expPageName, "Page wording did not match!");
		
		//TestStep2: Click on 'Contact' link at the top right navigation bar
		
		landingpage.goToContact();
		Thread.sleep(5000);
		
		String actualText = landingpage.validateSendMessageSection();
		String expText = "Send Us a Message";
		Assert.assertEquals(actualText, expText, "Landed on wrong section!");
		
		//TestStep3: Provide valid details and then click on 'Submit' button
		
		landingpage.sendMessageMethod(input.get("Name"), input.get("Email"), input.get("PhoneNum"), input.get("Subject"), input.get("MessageDescription"));
		
		Thread.sleep(5000);
		
		
		String actualmsg1 = landingpage.validateMessage1();
		String expectedmsg1 = "Thanks for getting in touch "+input.get("Name")+"!";
		Assert.assertEquals(actualmsg1,expectedmsg1, "Message did not match!");
		
		String actualmsg2 = landingpage.validateMessage2();
		String expectedmsg2 = "We'll get back to you about";
		Assert.assertEquals(actualmsg2,expectedmsg2, "Message did not match!");
		
		String actualmsg3 = landingpage.validateMessage3();
		String expectedmsg3 = input.get("Subject");
		Assert.assertEquals(actualmsg3,expectedmsg3, "Message did not match!");
		
		String actualmsg4 = landingpage.validateMessage4();
		String expectedmsg4 = "as soon as possible.";
		Assert.assertEquals(actualmsg4,expectedmsg4, "Message did not match!");
		
		
	}
	
	@Test(dataProvider="getData")
	public void SendMessage_Unsuccessful_BlankName(HashMap<String, String> input) throws InterruptedException {
		
		LandingPage landingpage = new LandingPage(driver);
		
		//TestStep1: Hit URL: https://automationintesting.online/ and User should be landed on "Welcome to Shady Meadows B&B" page
		
		String actualPageName = landingpage.checkPageName();
		String expPageName = "Welcome to Shady Meadows B&B";
		Assert.assertEquals(actualPageName, expPageName, "Page wording did not match!");
		
		//TestStep2: Click on 'Contact' link at the top right navigation bar
		
		landingpage.goToContact();
		Thread.sleep(5000);
		
		String actualText = landingpage.validateSendMessageSection();
		String expText = "Send Us a Message";
		Assert.assertEquals(actualText, expText, "Landed on wrong section!");
		
		//TestStep3: Keep the "Name" field blank
		
		landingpage.sendMessageMethod(input.get("BlankName"), input.get("Email"), input.get("PhoneNum"), input.get("Subject"), input.get("MessageDescription"));
		
		Thread.sleep(5000);
		
		
		String actualMsg_BlankName = landingpage.validateErrorMessage();
		String expMsg_BlankName = "Name may not be blank";
		Assert.assertEquals(actualMsg_BlankName, expMsg_BlankName, "Error message did not match!");
		
		
	}
	
	@Test(dataProvider="getData")
	public void SendMessage_Unsuccessful_NameContainsSpecialChar(HashMap<String, String> input) throws InterruptedException {
		
		LandingPage landingpage = new LandingPage(driver);
		
		//TestStep1: Hit URL: https://automationintesting.online/ and User should be landed on "Welcome to Shady Meadows B&B" page
		
		String actualPageName = landingpage.checkPageName();
		String expPageName = "Welcome to Shady Meadows B&B";
		Assert.assertEquals(actualPageName, expPageName, "Page wording did not match!");
		
		//TestStep2: Click on 'Contact' link at the top right navigation bar
		
		landingpage.goToContact();
		Thread.sleep(5000);
		
		String actualText = landingpage.validateSendMessageSection();
		String expText = "Send Us a Message";
		Assert.assertEquals(actualText, expText, "Landed on wrong section!");
		
		//TestStep3: Provide special characters in Name field
		
		landingpage.sendMessageMethod(input.get("NameWithSpecialChar"), input.get("Email"), input.get("PhoneNum"), input.get("Subject"), input.get("MessageDescription"));
		
		Thread.sleep(5000);
		
		String actualMsg_BlankName = landingpage.validateErrorMessage();
		String expMsg_BlankName = "Name cannot have special character";
		Assert.assertEquals(actualMsg_BlankName, expMsg_BlankName, "Error message did not match!");
		
		
	}
	
	@Test(dataProvider="getData")
	public void SendMessage_Unsuccessful_WrongEmailFormat(HashMap<String, String> input) throws InterruptedException {
		
		LandingPage landingpage = new LandingPage(driver);
		
		//TestStep1: Hit URL: https://automationintesting.online/ and User should be landed on "Welcome to Shady Meadows B&B" page
		
		String actualPageName = landingpage.checkPageName();
		String expPageName = "Welcome to Shady Meadows B&B";
		Assert.assertEquals(actualPageName, expPageName, "Page wording did not match!");
		
		//TestStep2: Click on 'Contact' link at the top right navigation bar
		
		landingpage.goToContact();
		Thread.sleep(5000);
		
		String actualText = landingpage.validateSendMessageSection();
		String expText = "Send Us a Message";
		Assert.assertEquals(actualText, expText, "Landed on wrong section!");
		
		//TestStep3: Provide wrong email format in 'Email' field (For eg, test.gmail.com)
		
		landingpage.sendMessageMethod(input.get("Name"), input.get("WrongEmailFormat"), input.get("PhoneNum"), input.get("Subject"), input.get("MessageDescription"));
		
		Thread.sleep(5000);
		
		
		String actualMsg_BlankName = landingpage.validateErrorMessage();
		String expMsg_BlankName = "must be a well-formed email address";
		Assert.assertEquals(actualMsg_BlankName, expMsg_BlankName, "Error message did not match!");
		
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//TestData//SendMessageData.json");
		return new Object[][] {{data.get(0)}};

	}
}

