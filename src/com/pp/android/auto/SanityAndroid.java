package com.pp.android.auto;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.xml.sax.SAXException;

public class SanityAndroid {
	
	String currentDateFolder;
	String webElementXmlLang;
	String	webElementXmlPath;
	String StartServerPath;
	String StopServerPath;
	AndroidWebElements androidData;
	public AndroidDriver driver;
	AndroidGenericMethods genMeth = new AndroidGenericMethods();
	

	@BeforeSuite(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		
		//Set the tests configuration
		StartServerPath = genMeth.getValueFromPropFile("StartServerPath");
		StopServerPath = genMeth.getValueFromPropFile("StopServerPath");
		webElementXmlPath = genMeth.getValueFromPropFile("webElementXmlPath");
		webElementXmlLang = genMeth.getValueFromPropFile("webElementXmlLang");
		
		androidData= new AndroidWebElements(webElementXmlLang, webElementXmlPath);
		driver = genMeth.setCapabilitiesAndroid(genMeth);

		genMeth.cleanLoginAndroid(driver, genMeth, androidData, androidData.userUnlimited_name); 

	}
//
//	@BeforeMethod (alwaysRun = true)
//	public void checkHomeScreen() throws InterruptedException, IOException, ParserConfigurationException, SAXException{
//	// Check if the client still logged in before each test (if not login to the client)
//		
//		By byCat = By.xpath("//android.widget.ListView[1]/android.widget.TextView[1]");
//		String cat = "CATEGORIES";
//		boolean loggedin= genMeth.checkIsTextPresentNative(driver ,cat, byCat);
//		if (loggedin != true){
//			driver = genMeth.loginNativeAndroid();
//			
//		}
//	}
//
//	@Test (enabled = false , testName="Sanity Tests",  description = "Test the Login with Android" , groups= {"Sanity Native Android"})	
//	public void testLogin() throws ParserConfigurationException, SAXException, IOException, InterruptedException{	
//		
//		androidElementData androidData = genMeth.androidElementInit();
////		  	genData strInf = genMeth.genericDataInit();
//		genMeth.clickId(driver, genMeth, androidData.BTNalreadyHaveAnAccount_id);
//		//genMeth.findByIdAndSend(driver, genMeth, "com.pogoplug.android:id/email", "meny@cloudengines.com");
//		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDemail_id, "meny@cloudengines.com");
//	   // genMeth.findByIdAndSend(driver, genMeth, "com.pogoplug.android:id/password", "1");
//		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDpassword_id, "1");
//	    //genMeth.idAndClick(driver, genMeth, "com.pogoplug.android:id/loginBtn");
//		genMeth.clickId(driver, genMeth, androidData.BTNlogin_id);
//
//
////		genMeth.isTextPresent("Never Lose a Photo", By.xpath("//android.support.v4.view.ViewPager[1]/android.widget.TextView[1]"));
//	// Make sure that the intro display (that way the swipe will be done at the right time)
//		By by = By.id("com.pogoplug.android:id/protect_computer");
//		String text = "Never Lose a Photo";
//		genMeth.isTextPresentNative(driver , text, by);
//		
//		
//	// Navigate through the intro
//		driver.swipe(1031, 1150, 53, 1150, 500);
//		//driver.executeScript("mobile: swipe", new HashMap<String, Double>() {{ put("touchCount", (double) 1); put("startX", (double) 1031); put("startY", (double) 1150); put("endX", (double) 53); put("endY", (double) 1159); put("duration", 0.5); }});
//		genMeth.clickId(driver, genMeth, androidData.BTNfinishTour_id);
//		genMeth.clickId(driver, genMeth, androidData.BTNcontinue_id);
//	  
//	// Make sure that the Login was successful By verifying that the "CATEGORIES" display in the *LSM (Left Side Menu)
//	    By by1 = By.xpath("//android.widget.ListView[1]/android.widget.TextView[1]");
//		String text1 = "CATEGORIES";
//		genMeth.isTextPresentNative(driver ,text1, by1);
//
//	// Close the left side menu	
//		genMeth.clickName(driver, genMeth, "Files, Navigate up");
//			
//	}
//
	@Test (enabled = true , description = "Test the Create folder with Android" , groups= {"Sanity Android"}  /*dependsOnMethods={"testLogin"}*/)	
	public void testCreatefolder() throws ParserConfigurationException, SAXException, IOException, InterruptedException{
		
		
		String currentDate = genMeth.currentTime();
		genMeth.clickName(driver, genMeth, androidData.FileExplorer_Name);	
//		Create a new folder 
		genMeth.clickName(driver, genMeth,  androidData.BTNnewFolder_name);
		
//	 	Dismiss the create folder window		
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDgeneral_ID, currentDate);
		genMeth.clickId(driver, genMeth, androidData.BTNdismissNewFolder_id);

//	 	make sure that the folder wasn't created
		genMeth.isElementInvisibleNative(driver, By.name(currentDate));
		
//		Now Press the create folder
		genMeth.clickName(driver, genMeth, androidData.BTNnewFolder_name);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDgeneral_ID, currentDate);
		genMeth.clickId(driver, genMeth, androidData.BTNcreateNewFolder_id);
		
//		Check if the folder was created successfully 
		genMeth.isElementVisibleNative(driver, By.name(currentDate));
		
	// sort the list in order to place the image in the first position
		genMeth.clickName(driver, genMeth,androidData.BTNsort_name);
		genMeth.clickName(driver,genMeth, androidData.OPTIONsortOldestFirst_name);

	// long press the folder (choosing the folder for deletion by swipe long duration- need to figure out how to do it by proper long press code)
		Thread.sleep(1000);
		//add long press instead of the swip
		TouchAction action = new TouchAction(driver); 
	
		WebElement el = genMeth.returnName(driver, genMeth, currentDate);
		action.longPress(el).waitAction(2000).release().perform();
		//driver.swipe(280, 900, 320, 900, 2000);
		genMeth.clickName(driver, genMeth, androidData.BTNdelete_name);
		
	// Cancel the delete & make sure that the folder wasn't deleted
		genMeth.clickId(driver, genMeth, androidData.BTNcancelDelete_id);
		genMeth.isElementVisibleNative(driver, By.name(currentDate));
		
	// now delete the folder & make sure it was deleted properly
		action.longPress(el).waitAction(2000).release().perform();
		//driver.swipe(280, 900, 320, 900, 2000);
		genMeth.clickName(driver, genMeth, androidData.BTNdelete_name);;
		genMeth.clickId(driver, genMeth, androidData.BTNdeleteConfirm_id);
		genMeth.isElementInvisibleNative(driver, By.name(currentDate));
		
	// go Start test start screen (LSM left side menu)
		genMeth.clickXpth(driver, genMeth, androidData.BTNopenLSM_xpth);
		
		  }
//
//	@Test (enabled = true ,testName="Sanity Tests", description = "Test the Upload utility with Android" , groups= {"Sanity Native Android"})	
//	public void testUploadImage() throws ParserConfigurationException, SAXException, IOException, InterruptedException{
//		
//		androidElementData androidData = genMeth.androidElementInit();
//	// open pogoplug cloud & press
//		genMeth.clickXpth(driver, genMeth, "//android.widget.ListView[1]/android.widget.LinearLayout[3]");
//		Thread.sleep(1000);
//		genMeth.clickXpth(driver, genMeth, androidData.LIST1_xpth);
//		
//	// Capture an image
//		genMeth.clickName(driver, genMeth, androidData.BTNupload_name);
//		genMeth.clickName(driver, genMeth, androidData.OPTIONcaptureNewPhoto_name);
//		Thread.sleep(2000);
//		genMeth.clickId(driver, genMeth, androidData.BTNcapturePhoto_id);
//		genMeth.clickId(driver, genMeth, androidData.BTNcaptureDone_id);
//		Thread.sleep(1000);
//		genMeth.clickName(driver, genMeth, androidData.BTNrefresh_name);
//		
//	// sort the list in order to place the image in the first position
//		genMeth.clickName(driver, genMeth,androidData.BTNsort_name);
//		genMeth.clickName(driver, genMeth, androidData.OPTIONsortOldestFirst_name);
//		genMeth.clickName(driver, genMeth, androidData.BTNrefresh_name);
//		
//	// Check if the image display in the list
//		genMeth.isElementVisibleNative(By.xpath(androidData.LIST5_xpth), driver);
//		
//		WebElement uploadedImage = genMeth.returnXpth(driver, genMeth, "//android.view.View[1]/android.widget.ListView[1]/android.widget.LinearLayout[5]/android.widget.TextView[2]");
//		Thread.sleep(1000);
//		String lastUpload = uploadedImage.getText();
//		String currentUpload= "None";
//		Thread.sleep(1000);
//		
//	// Add an if that will verify that the Upload has finished (compare the KB - once not changing it probably has finished or stuck)	
//		if (lastUpload != currentUpload ){
//			genMeth.clickName(driver, genMeth, androidData.BTNrefresh_name);
//			lastUpload = uploadedImage.getText();
//			Thread.sleep(5000);
//			genMeth.clickName(driver, genMeth, androidData.BTNrefresh_name);
//		//	lastUpload = currentUpload;
//			currentUpload = uploadedImage.getText();
//			
//		}
//		
//	// Open the image & make sure that it displays 
//		genMeth.clickXpth(driver, genMeth, androidData.LIST5_xpth);
//		
//	// Make sure that the "Image not available" text doesn't displayed
//		String imageNotAvailable = "Image not available";
//		By by4 = By.id("com.pogoplug.android:id/thumbnail_not_found_text_view");
//		genMeth.isElementInvisibleTextNative(by4, imageNotAvailable, driver);
//		Thread.sleep(1000);	
//		genMeth.clickXpth(driver, genMeth, "//android.view.View[1]");
//	// Delete the image
//		driver.swipe(300, 900, 300, 900, 2000);
//		Thread.sleep(5000);
//		genMeth.clickName(driver, genMeth, androidData.BTNdelete_name);
//		genMeth.clickId(driver, genMeth, androidData.BTNdeleteConfirm_id);
//
//	// Check that the image was deleted
//		genMeth.isElementInvisibleNative(By.xpath(androidData.LIST5_xpth), driver);
//		
//		}
//
//	@AfterSuite (alwaysRun=true)
//
//		public void sendMail() throws Exception {
//			driver.quit();	
//			SendResults sr = new SendResults("elicherni444@gmail.com", "meny@cloudengines.com", "TestNG results", "Test Results");
//			sr.sendTestNGResult();
//		
//		}
//
//
//
//	@AfterMethod
//	public void tearDown() {
//		//count ++;
//
//	}

	}



