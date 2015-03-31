package com.pp.android.auto;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.xml.sax.SAXException;

import com.applitools.eyes.Eyes;

  public class SanityAndroid {
	
	String currentDateFolder;
	String webElementXmlLang;
	String webElementXmlPath;
	String StartServerPath;
	String StopServerPath;
	AndroidWebElements droidData;
	public AndroidDriver driver;
	AndroidGenericMethods genMeth = new AndroidGenericMethods();
	
	public SanityAndroid() {
		// TODO Auto-generated constructor stub
	}

	@BeforeSuite(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		
        // This is your api key, make sure you use it in all your tests.
		
		//Set the tests configuration
		StartServerPath = genMeth.getValueFromPropFile("StartServerPath");
		StopServerPath = genMeth.getValueFromPropFile("StopServerPath");
		webElementXmlPath = genMeth.getValueFromPropFile("webElementXmlPath");
		webElementXmlLang = genMeth.getValueFromPropFile("webElementXmlLang");
		
		droidData= new AndroidWebElements(webElementXmlLang, webElementXmlPath);
		driver = genMeth.setCapabilitiesAndroid(genMeth);
		genMeth.cleanLoginAndroid(genMeth,droidData, droidData.userUnlimited_name); 
		
	}

	@BeforeMethod (alwaysRun = true)
	public void checkHomeScreen() throws InterruptedException, IOException, ParserConfigurationException, SAXException{

//		genMeth.setWifiOn();

		// Check if the client still logged in & in StartUp screen before each test
		if (driver == null) {
			try {
				driver.removeApp(genMeth.getValueFromPropFile("appPackage"));
				driver.quit();
			} catch (Exception e) {
				// swallow if fails
			}
			driver = genMeth.setCapabilitiesAndroid(genMeth);
			genMeth.cleanLoginAndroid( genMeth,droidData, droidData.userUnlimited_name );
		}

		else {
			boolean StartUpScreenDisplay = genMeth.checkIsElementVisible( By.name(droidData.Settings_Name));

			if (StartUpScreenDisplay != true) {

				try {
					driver.removeApp(genMeth.getValueFromPropFile("appPackage"));
					driver.quit();
				} catch (Exception e) {
					// swallow if fails
				}

				driver = genMeth.setCapabilitiesAndroid(genMeth);
				genMeth.cleanLoginAndroid( genMeth, droidData, droidData.userUnlimited_name);

			}

		}

	}
	
	@AfterMethod(enabled = true, dependsOnMethods = { "connectionLost" })
	public void enabledWifi() {

		genMeth.setWifiOn();
	}

	@Test (enabled = true ,testName = "createfolder1", retryAnalyzer = Retry.class, description = "Test the Create folder with Android" ,
			groups= {"Sanity Android"}  /*dependsOnMethods={"testLogin"}*/)	
	public void createfolder() throws ParserConfigurationException, SAXException, IOException, InterruptedException{
		
		String currentDate = genMeth.currentTime();
		genMeth.clickName( genMeth, droidData.FileExplorer_Name);	

		//check if "Create folder" exist & if not create it
		
		Boolean isCreateFolder = genMeth.checkIsElementVisible(By.name("Create folder"));
		if (isCreateFolder != true){
			genMeth.clickName( genMeth, droidData.BTNnewFolder_name);
			genMeth.sendId(  genMeth, droidData.TEXTFIELDgeneral_ID, "Create folder");
			genMeth.clickId(  genMeth, droidData.BTNcreateNewFolder_id);
		
			
//			Check if the folder was created successfully 
			genMeth.isElementVisible( By.name("Create folder"));

		}
		
//		Create a new folder 
		genMeth.clickName(genMeth, "Create folder");
		
		//Check if empty & if not delete all folders
		
		
		//check if No files found display
		Boolean isEmpty = genMeth.checkIsElementVisible(By.name(droidData.NoFilesFound_Name));
		while (isEmpty != true){
			//delete first folder
			genMeth.longPressElement(driver, genMeth, By.id("com.pogoplug.android:id/list_item"));
			genMeth.clickName(genMeth, droidData.BTNdelete_name);
			genMeth.clickId(  genMeth, droidData.BTNdeleteConfirm_id);
			genMeth.clickName(genMeth, droidData.BTNmoreOptions_Name);
			genMeth.clickName(genMeth, droidData.BTNrefresh_name);
			isEmpty = genMeth.checkIsElementVisible(By.name(droidData.NoFilesFound_Name));

		}
		
		
//		genMeth.clickName( genMeth, droidData.BTNnewFolder_name);
		genMeth.clickName(genMeth, droidData.BTNmoreOptions_Name);
		genMeth.clickName(genMeth, "New Folder");
		
//	 	Dismiss the create folder window		
		genMeth.sendId(  genMeth, droidData.TEXTFIELDgeneral_ID, currentDate);
		genMeth.clickId(  genMeth, droidData.BTNdismissNewFolder_id);

//	 	make sure that the folder wasn't created
		genMeth.isElementInvisible(  By.name(currentDate));
		
//		Now Press the create folder
		genMeth.clickName(genMeth, droidData.BTNmoreOptions_Name);
		genMeth.clickName(genMeth, "New Folder");

//		genMeth.clickName( genMeth, droidData.BTNnewFolder_name);
		genMeth.sendId(  genMeth, droidData.TEXTFIELDgeneral_ID, currentDate);
		genMeth.clickId(  genMeth, droidData.BTNcreateNewFolder_id);
		
//		Check if the folder was created successfully 
		genMeth.isElementVisible( By.name(currentDate));
		
	// sort the list in order to place the image in the first position
		genMeth.clickName(genMeth, droidData.BTNmoreOptions_Name);
		genMeth.clickName( genMeth,droidData.BTNsort_name);
		genMeth.clickName(genMeth, droidData.OPTIONsortOldestFirst_name);

	// long press the folder for deletion
		genMeth.longPressElement(driver, genMeth, By.name(currentDate));
		genMeth.clickName( genMeth, droidData.BTNdelete_name);
		
	// Cancel the delete & make sure that the folder wasn't deleted
		genMeth.clickId(  genMeth, droidData.BTNcancelDelete_id);
		genMeth.isElementVisible( By.name(currentDate));
		
	// now delete the folder & make sure it was deleted properly
		genMeth.longPressElement(driver, genMeth, By.name(currentDate));
		genMeth.clickName( genMeth, droidData.BTNdelete_name);;
		genMeth.clickId(  genMeth, droidData.BTNdeleteConfirm_id);
		genMeth.isElementInvisible(  By.name(currentDate));
		
	// Go to strtup page (LSM left side menu)
		genMeth.clickId(  genMeth, droidData.BTNlsm_ID);
		genMeth.isTextPresentAndroid(driver, By.name(droidData.CATEGORIES), droidData.CATEGORIES);

		
		  }

	
	@Test (enabled = true , retryAnalyzer = Retry.class, testName="Sanity Tests", description = "Test the Upload utility with Android" ,
			groups= {"Sanity Android"})	
	public void uploadImage() throws ParserConfigurationException, SAXException, IOException, InterruptedException{
		
	// open pogoplug cloud & press
		genMeth.clickId(  genMeth, droidData.BTNlsm_ID);
		genMeth.clickName( genMeth, "upload from existing test");
		boolean isEmpty = genMeth.checkIsElementVisible(By.name(droidData.NoFilesFound_Name));
		if ( isEmpty != true){
			
			genMeth.longPressElement(driver, genMeth, By.id(droidData.ListSecondaryText_ID));
			genMeth.clickName( genMeth, droidData.BTNdelete_name);
			genMeth.clickId(  genMeth, droidData.BTNdeleteConfirm_id);
			genMeth.clickName(genMeth, droidData.BTNmoreOptions_Name);
			genMeth.clickName(genMeth, droidData.BTNrefresh_name);
			isEmpty = genMeth.checkIsElementVisible(By.name(droidData.NoFilesFound_Name));
		}
		
		
		//Make sure that the folder is empty
		
	// Capture an image
		genMeth.clickName( genMeth, droidData.BTNupload_name);
		genMeth.clickName( genMeth, droidData.OPTIONcaptureNewPhoto_name);
		Thread.sleep(2000);
		
//		genMeth.clickId(  genMeth, droidData.BTNcapturePhoto_LG_id);
		genMeth.clickName(genMeth, droidData.BTNcapturePhoto_GooglePhone_Name);
		genMeth.clickName(genMeth, droidData.IconReviewDoneForImageGooglePhone_Name);

//		genMeth.clickName( genMeth, droidData.BTNok_Name);
		Thread.sleep(2000);
		genMeth.clickClassName(driver, genMeth, droidData.BTNmoreOptions_ClassName);
		genMeth.clickName( genMeth, droidData.BTNrefresh_name);
		
	// Check if the image display in the list
		WebElement uploadedImage = genMeth.returnId(driver, genMeth, "com.pogoplug.android:id/text_secondary");
		String lastUpload = uploadedImage.getText();
		String currentUpload= "None";
		Thread.sleep(1000);
		
	// Add an if that will verify that the Upload has finished (compare the KB - once not changing it probably has finished or stuck)	
		if (lastUpload != currentUpload ){
			genMeth.clickClassName(driver, genMeth, droidData.BTNmoreOptions_ClassName);
			genMeth.clickName( genMeth, droidData.BTNrefresh_name);
			lastUpload = uploadedImage.getText();
			Thread.sleep(5000);
			genMeth.clickClassName(driver, genMeth, droidData.BTNmoreOptions_ClassName);
			genMeth.clickName( genMeth, droidData.BTNrefresh_name);
			currentUpload = uploadedImage.getText();
			
		}
		
	// Open the image & make sure that it displays 
		genMeth.clickName( genMeth, currentUpload);
	// Make sure that the "Image not available" text doesn't displayed
		Thread.sleep(3000);
		genMeth.isElementInvisibleText( By.name(droidData.ImageNotAvailable_Name), droidData.ImageNotAvailable_Name);
		genMeth.takeScreenShotPositive( genMeth, "testUploadImage");
		Thread.sleep(1000);	
	//	genMeth.clickXpth(driver, genMeth, "//android.view.View[1]");
		genMeth.clickId(  genMeth, droidData.FullScreen_ID);
		genMeth.clickId(  genMeth, droidData.BTNhome_ID);
	// Delete the image
		genMeth.longPressElement(driver, genMeth, By.id(droidData.ListSecondaryText_ID));
//		Thread.sleep(5000);
		genMeth.clickName( genMeth, droidData.BTNdelete_name);
		genMeth.clickId(  genMeth, droidData.BTNdeleteConfirm_id);
	// Check that the image was deleted
		genMeth.isElementInvisible(  By.id(droidData.FullScreen_ID));
		genMeth.clickClassName(driver, genMeth, droidData.BTNmoreOptions_ClassName);
		genMeth.clickName( genMeth, droidData.BTNrefresh_name);
		genMeth.isElementVisible(By.name(droidData.NoFilesFound_Name));
		//Back to start page
		genMeth.pressBackButton();
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		genMeth.isTextPresentAndroid(driver, By.name(droidData.CATEGORIES), droidData.CATEGORIES);

		
		}
	
	
	@Test(enabled = true, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Test TOUR for New accounts and for upgrade accounts",
			groups = { "Sanity Android" })
	public void tour() throws Exception, Throwable {

		
		 // =============================================================== 
		 //        Tour for Free Account
		 //===============================================================
		 
		genMeth.killAppAndroid(driver);
		driver = genMeth.setCapabilitiesAndroid(genMeth);
		genMeth.clickId(  genMeth, droidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(  genMeth, droidData.TEXTFIELDemail_id, droidData.userLimited_name);
		genMeth.sendId(  genMeth, droidData.TEXTFIELDpassword_id, droidData.password);
		genMeth.tapId( genMeth, droidData.BTNlogin_id);
		Thread.sleep(3000);
		
		// check Never loose a photo
		genMeth.isElementVisible( By.name(droidData.NeverLoseAPhoto));
		genMeth.takeScreenShotPositive( genMeth, droidData.NeverLoseAPhoto );
		driver.swipe(600, 800, 50, 800, 600);
		
		//Check Transfer phones simply
		genMeth.isElementVisible( By.name(droidData.TransferPhonesSimply_name));
		genMeth.takeScreenShotPositive( genMeth, droidData.TransferPhonesSimply_name );
		driver.swipe(600, 800, 50, 800, 600);
		// Check Unlimited protection
		genMeth.isElementVisible( By.name(droidData.UnlimitedProtection_Name));
		genMeth.takeScreenShotPositive( genMeth,  droidData.UnlimitedProtection_Name );
		
		// SKIP 
		genMeth.clickId(  genMeth, droidData.BTNskip_ID);
		
		// Check Backup
		genMeth.isElementVisible( By.name(droidData.Backup_Name));
		genMeth.takeScreenShotPositive( genMeth, droidData.Backup_Name );
		
		// press the Continue button 
		genMeth.clickId(  genMeth, droidData.BTNfinishTour_id);	
		genMeth.isTextPresentAndroid(driver, By.name(droidData.CATEGORIES), droidData.CATEGORIES);
		Thread.sleep(1000);
		
		//Verify that LOGOUT & RELOGIN will not prompt the tour
//		genMeth.clickXpth(genMeth, "//android.widget.RelativeLayout[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[7]");
		genMeth.tapXpth( genMeth, "//android.widget.RelativeLayout[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[7]");
		driver.scrollToExact(droidData.OPTIONsignOut_Name);
		genMeth.clickName( genMeth, droidData.OPTIONsignOut_Name);
		genMeth.clickName( genMeth, droidData.BTNok_Name);
		genMeth.clickId(  genMeth, droidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(  genMeth, droidData.TEXTFIELDemail_id, droidData.userLimited_name);
		genMeth.sendId(  genMeth, droidData.TEXTFIELDpassword_id, droidData.password);
		genMeth.tapId( genMeth, droidData.BTNlogin_id);
		genMeth.isTextPresentAndroid(driver, By.name(droidData.CATEGORIES), droidData.CATEGORIES);
		
		 
		// same test only with Upgrade now at pogoplug.com
		genMeth.killAppAndroid(driver);
		driver = genMeth.setCapabilitiesAndroid(genMeth);
		genMeth.clickId(  genMeth, droidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(  genMeth, droidData.TEXTFIELDemail_id,droidData.userLimited_name);
		genMeth.sendId(  genMeth, droidData.TEXTFIELDpassword_id,droidData.password);
		genMeth.tapId( genMeth, droidData.BTNlogin_id);

		// check Never loose a photo
		genMeth.isElementVisible( By.name(droidData.NeverLoseAPhoto));
		driver.swipe(600, 800, 50, 800, 600);

		// Check Transfer phones simply
		genMeth.isElementVisible(By.name(droidData.TransferPhonesSimply_name));
		driver.swipe(600, 800, 50, 800, 600);
		// Check Unlimited protection
		genMeth.isElementVisible(By.name(droidData.UnlimitedProtection_Name));
		genMeth.takeScreenShotPositive( genMeth, droidData.UnlimitedProtection_Name + "_Tour");

		// UPGRADE now at pogoplug.com
		genMeth.clickId(  genMeth, droidData.BTNtourUpgradeNow_ID);
		genMeth.isElementVisible( By.name(droidData.BillingUrl_Name));
		genMeth.takeScreenShotPositive( genMeth,  "_Tour_Billing_Page");
		
		
		
	// =====================================================
	//     Tour for Unlimited Account
	// =====================================================
		 
		
		genMeth.killAppAndroid(driver);
		driver = genMeth.setCapabilitiesAndroid(genMeth);
		genMeth.clickId(  genMeth, droidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(  genMeth, droidData.TEXTFIELDemail_id, droidData.userUnlimited_name);
		genMeth.sendId(  genMeth, droidData.TEXTFIELDpassword_id, droidData.password);
		genMeth.tapId( genMeth, droidData.BTNlogin_id);
		
		// check Never loose a photo
		genMeth.isElementVisible( By.name(droidData.NeverLoseAPhoto));
		driver.swipe(600, 800, 50, 800, 600);
		
		//Check Transfer phones simply
		genMeth.isElementVisible( By.name(droidData.TransferPhonesSimply_name));
		genMeth.takeScreenShotPositive( genMeth, droidData.TransferPhonesSimply_name + "_Tour_Finish");
		genMeth.clickId(  genMeth, "com.pogoplug.android:id/tour_indicator_placehoder");
		
		// Check Backup
		genMeth.isElementVisible( By.name(droidData.Backup_Name));
		
		// press the Continue button 
		genMeth.clickId(  genMeth, droidData.BTNfinishTour_id);	
		genMeth.isTextPresentAndroid(driver, By.name(droidData.CATEGORIES), droidData.CATEGORIES);
		Thread.sleep(1000);
		// logout & relogin will not prompt the tour
		genMeth.tapXpth( genMeth, "//android.widget.RelativeLayout[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[7]");
		driver.scrollToExact(droidData.OPTIONsignOut_Name);
		genMeth.clickName( genMeth, droidData.OPTIONsignOut_Name);
		genMeth.clickName( genMeth, droidData.BTNok_Name);
		genMeth.clickId(  genMeth, droidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(  genMeth, droidData.TEXTFIELDemail_id, droidData.userUnlimited_name);
		genMeth.sendId(  genMeth, droidData.TEXTFIELDpassword_id, droidData.password);
		genMeth.tapId( genMeth, droidData.BTNlogin_id);
		genMeth.isTextPresentAndroid(driver, By.name(droidData.CATEGORIES), droidData.CATEGORIES);
		
	}
	
	@Test(enabled = true, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Sign up- Create new user (Negetive positive test), Privacy Policy, TRUSTe",
			groups = { "Sanity Android" })
	public void createNewUser() throws Exception, Throwable {
		String userRand = genMeth.randomString();
		
		genMeth.signOutFromStartupAndroid(genMeth, droidData);
		
		//Attempt to Create a new user with bad format
		genMeth.clickName(genMeth, droidData.BTNcreateAccount_Name);
		genMeth.sendId( genMeth, droidData.TEXTFIELDemail_id, userRand );
		
		Thread.sleep(1000);
		genMeth.sendId( genMeth, droidData.TEXTFIELDpassword_id, droidData.password);
		genMeth.clickName(genMeth, droidData.IconIagreeToTermsOfService_Name);
		genMeth.clickName(genMeth, droidData.BTNcreateAccount_Name);
		
		genMeth.sendId( genMeth, droidData.TEXTFIELDemail_id, userRand + "@test.com");
		Thread.sleep(1000);
		genMeth.sendId( genMeth, droidData.TEXTFIELDpassword_id, droidData.password);
		genMeth.clickName(genMeth, droidData.BTNcreateAccount_Name);
		Thread.sleep(1000);
		genMeth.clickName(genMeth, droidData.BTNcontinue_Name);
		genMeth.isElementVisible(By.name( droidData.BTNupgrade_Name));
		genMeth.takeScreenShotPositive(genMeth, droidData.BTNupgrade_Name + "New Account");

		
	}
	
	@Test (enabled = true, retryAnalyzer = Retry.class, testName = "Terms of service & TrusTe", description = "Test the TOS & TrusTe Links" ,
			groups = {"Sanity Android"})
	public void termsOfServiceAndTruste () throws InterruptedException, IOException, ParserConfigurationException, SAXException{
		
		String currentTime = genMeth.currentTime();
		String currentDate = genMeth.currentDate();
		
		genMeth.signOutFromStartupAndroid(genMeth, droidData);
		genMeth.clickName(genMeth, droidData.BTNcreateAccount_Name);
		genMeth.clickName(genMeth, droidData.TermsofService_Name);
		Thread.sleep(2000);
		genMeth.isElementVisible(By.name(droidData.TermsofServiceLink_Name));
		genMeth.takeScreenShotPositive(genMeth, "terms of service link");
		genMeth.pressBackButton();
		
		genMeth.clickId(genMeth, droidData.IconTrusT_Id);
		genMeth.isElementVisible(By.name(droidData.TrustLink_Name));
		genMeth.takeScreenShotPositive(genMeth, "/" + currentDate + currentTime + "TrusT link");
		genMeth.pressBackButton();

		genMeth.clickName(genMeth, droidData.PrivacyPolicy_Name);
		genMeth.isElementVisible(By.name( droidData.PrivacyPolicyLink_Name));
		genMeth.takeScreenShotPositive(genMeth, "privacy policy link");
		
		
	}
	
	@SuppressWarnings("static-access")
	@Test(enabled = true,  testName = "Sanity Tests", description = "login with bad/missing credentials , forgot password (negative & positive)",
			groups = { "Sanity Android1" })
	public void badCredentials() throws Exception, Throwable {
		
		String currentTime = genMeth.currentTime();

		Eyes eyes = new Eyes();
		eyes.setApiKey("Hbh6716cKDCgn8a9bMAREPM105nbW109PQe0993So5GwFpNM110");
		eyes.open(driver, "pp", "test1");
		eyes.checkWindow("Initial screen");
		eyes.close();
					
		
		genMeth.signOutFromStartupAndroid(genMeth, droidData);
		genMeth.clickId( genMeth, droidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId( genMeth, droidData.TEXTFIELDemail_id, currentTime);
		genMeth.sendId( genMeth, droidData.TEXTFIELDpassword_id, droidData.password);
		genMeth.clickId( genMeth, droidData.BTNlogin_id);
		Thread.sleep(3000);
		genMeth.takeScreenShotPositive( genMeth, "_Bad_login");
		genMeth.sendId( genMeth, droidData.TEXTFIELDemail_id, droidData.userUnlimited_name);
		genMeth.sendId( genMeth, droidData.TEXTFIELDpassword_id, droidData.password);
		genMeth.clickId( genMeth, droidData.BTNlogin_id);	
		genMeth.isElementVisible(By.name(droidData.CATEGORIES));
		


		// Login with bad credentials
	

		// Forgot your password Negative (attempt to restore password with a non existing email)
	
		
		// Forgot your password Positive (attempt to restore password with an existing email)
	
	}
	
	@Test(enabled = true, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Search functionality & filter",
			groups = { "Sanity Android" })
	public void search() throws Exception, Throwable {
		
		String currentDate = genMeth.currentDate();
		
		//Search Folder in ROOT
		String Random = genMeth.randomString();	
		WebElement search = genMeth.returnId(driver, genMeth, "com.pogoplug.android:id/main" );
		search.click();
		genMeth.sendId(genMeth, "android:id/search_view", droidData.SearchFolderFromRoot_Name);
		genMeth.pressEnter();
		genMeth.isElementVisible( By.name(droidData.SearchFolderFromRoot_Name));
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.tapId(genMeth, droidData.BTNlsm_ID);
		
		// Search Image in ROOT
		Thread.sleep(2000);
		search.click();
		genMeth.sendId(genMeth, "android:id/search_view", droidData.SearchImageFromRoot_Name);
		genMeth.pressEnter();
		genMeth.isElementVisible( By.name(droidData.SearchImageFromRoot_Name));
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(2000);

		// Search Empty results in ROOT
		search.click();
		genMeth.sendId(genMeth, "android:id/search_view", Random);
		genMeth.clickId( genMeth, "android:id/search_close_btn");
		genMeth.isElementVisible( By.id("android:id/search_src_text"));
		genMeth.takeScreenShotPositive( genMeth, "/" + currentDate +  "Delete_TextBox_Via_X");
		Thread.sleep(2000);

		genMeth.sendId(genMeth, "android:id/search_view", Random);
		genMeth.pressEnter();
		genMeth.isElementVisible( By.name(droidData.NoFilesFound_Name));
		genMeth.takeScreenShotPositive( genMeth, "Search" + droidData.NoFilesFound_Name);
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);
		genMeth.tapId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);

		// Search FOLDER in FOLDER
		genMeth.clickName( genMeth, droidData.BTNsearch_name);
		genMeth.sendId(genMeth, "android:id/search_src_text", droidData.SearchFolderFromFolder_Name);
		genMeth.pressEnter();
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.isElementInvisible( By.name(droidData.NoFilesFound_Name));
		genMeth.isElementVisible( By.name(droidData.SearchFolderFromFolder_Name));
		genMeth.tapId(genMeth, droidData.BTNlsm_ID);

		// Search Image in FOLDER 
		genMeth.clickName( genMeth, droidData.BTNsearch_name);
		genMeth.sendId(genMeth, "android:id/search_src_text", droidData.SearchImageFromFolder_Name);
		
		genMeth.pressEnter();
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.isElementInvisible( By.name(droidData.NoFilesFound_Name));
		genMeth.isElementVisible( By.name(droidData.SearchImageFromFolder_Name));
		genMeth.takeScreenShotPositive(genMeth,  droidData.SearchImageFromFolder_Name);
		genMeth.tapId(genMeth, droidData.BTNlsm_ID);

		// Search Empty results in FOLDER
		genMeth.clickName( genMeth, droidData.BTNsearch_name);
		genMeth.sendId(genMeth, "android:id/search_src_text", Random);
		genMeth.pressEnter();
		genMeth.isElementVisible( By.name(droidData.NoFilesFound_Name));
		
		//Verify that the "x" deletes the text in the search
		genMeth.clickName( genMeth, droidData.BTNsearch_name);
		genMeth.clickId( genMeth, "android:id/search_close_btn" );
		genMeth.sendId(genMeth, "android:id/search_src_text", currentDate);
		genMeth.isElementVisible(By.name(currentDate));
		//genMeth.clickName(genMeth,"Clear query"); //   android:id/search_close_btn
		genMeth.clickId( genMeth, "android:id/search_close_btn" );
		genMeth.isElementInvisible( By.name(currentDate));
		
		//Search in Songs A song
		genMeth.clickId(genMeth, "android:id/closeButton");
		genMeth.tapId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);
		genMeth.tapId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);
		genMeth.clickName(genMeth, droidData.MusicPlayer_Name);
		genMeth.clickName(genMeth, droidData.BTNsearch_name);
		genMeth.sendId(genMeth, "android:id/search_src_text", droidData.SearchSongInSong_Name);
		genMeth.pressEnter();
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.isElementVisible(By.name(droidData.SearchSongInSong_Name));
		genMeth.isElementInvisible(By.name(droidData.NoFilesFound_Name));
		
		//Search in songs empty results
		genMeth.clickName(genMeth, droidData.BTNsearch_name);
		genMeth.sendId(genMeth, "android:id/search_src_text", Random);
		genMeth.pressEnter();
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.isElementVisible(By.name(droidData.NoFilesFound_Name));

		//Search in Artists song
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);
		genMeth.pressBackButton();
		Thread.sleep(1000);
		genMeth.clickName(genMeth, droidData.Artists_Name);
		genMeth.clickName(genMeth, droidData.BTNsearch_name);
		genMeth.sendId(genMeth, "android:id/search_src_text" , droidData.SearchSongInArtists_Name);
		genMeth.pressEnter();
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.isElementVisible(By.name(droidData.SearchSongInArtists_Name));
		genMeth.isElementInvisible(By.name(droidData.NoFilesFound_Name));
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);

		//Search in Artists empty results
		genMeth.clickName(genMeth, droidData.BTNsearch_name);
		genMeth.sendId(genMeth, "android:id/search_src_text" , Random);
		genMeth.pressEnter();
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.isElementVisible(By.name(droidData.NoFilesFound_Name));
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);
//		genMeth.clickId(genMeth, droidData.BTNlsm_ID);

		
		//Search in Albums song
		genMeth.clickName(genMeth, droidData.Albums_name);
		genMeth.clickName(genMeth, droidData.BTNsearch_name);
		genMeth.sendId(genMeth, "android:id/search_src_text" , droidData.SearchSongInAlbums_Name);
		genMeth.pressEnter();
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.isElementVisible(By.name(droidData.SearchSongInAlbums_Name));
		genMeth.isElementInvisible(By.name(droidData.NoFilesFound_Name));
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);
		
		//Search in Albums empty results
		genMeth.clickName(genMeth, droidData.BTNsearch_name);
		genMeth.sendId(genMeth, "android:id/search_src_text" , Random);
		genMeth.pressEnter();
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.isElementVisible(By.name(droidData.NoFilesFound_Name));
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);

		//Search in Genres song 
		genMeth.clickName(genMeth, droidData.Genres_Name);
		genMeth.clickName(genMeth, droidData.BTNsearch_name);
		genMeth.sendId(genMeth, "android:id/search_src_text" , droidData.SearchSongInGenres_Name);
		genMeth.pressEnter();
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.isElementVisible(By.name(droidData.SearchSongInGenres_Name));
		genMeth.isElementInvisible(By.name(droidData.NoFilesFound_Name));
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);
		
		//Search in Genres empty results
		genMeth.clickName(genMeth, droidData.BTNsearch_name);
		genMeth.sendId(genMeth, "android:id/search_src_text" , Random);
		genMeth.pressEnter();
		genMeth.isElementInvisible(By.name(droidData.Loading_Name));
		genMeth.isElementVisible(By.name(droidData.NoFilesFound_Name));
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		Thread.sleep(1000);
		
		//search for a contact
		genMeth.clickName(genMeth, droidData.FileExplorer_Name);
		//open a random folder in order to be able to press the add user
		genMeth.clickName(genMeth, "Favorites");
		genMeth.clickName(genMeth, "Add Users");
//		genMeth.clickId(genMeth, droidData.IconNumbrOfSharedContacts_ID);
		genMeth.clickId(genMeth, droidData.IconAddShareUser_ID);
		driver.findElementById("android:id/search_src_text").sendKeys("MenyTheb");
		genMeth.isElementVisible(By.name("Menythebest"));
		
		//Back to start up screen
		genMeth.pressBackButton();
		genMeth.pressBackButton();
		genMeth.clickName(genMeth, droidData.BTNcancel_Name);
		genMeth.tapId(genMeth, droidData.BTNlsm_ID);
		genMeth.isTextPresentAndroid(driver, By.name(droidData.CATEGORIES), droidData.CATEGORIES);
	
	}
	
	@Test(enabled = true, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Settings: create & restore a snapshot",
			groups = { "Sanity Android" })
	public void createRestoreSnapShot() throws Exception, Throwable {
		
		
		genMeth.clickName( genMeth, droidData.Settings_Name);
		driver.scrollToExact(droidData.BTNrestoreSnapshot_Name);
		genMeth.clickName( genMeth, droidData.BTNrestoreSnapshot_Name);
		
		//Make sure That no previous snapshot exist (if so delete them)
		Boolean isEmpty = genMeth.fastCheckIsElementVisible( By.name(droidData.NoFiles_Name));
		if (isEmpty !=true){
			genMeth.clickName( genMeth, droidData.BTNdelete_name);
			genMeth.clickName( genMeth, droidData.BTNdelete_name);
			genMeth.clickName( genMeth, droidData.BTNrestoreSnapshot_Name);
		}
				
		//Create snapshot (precondition is to make sure that at least one image video & contact is available!!!)
		genMeth.clickId(  genMeth, droidData.BTNhome_ID);
		genMeth.clickName( genMeth, droidData.BTNcreateSnapshot_Name);
		Thread.sleep(1000);
		genMeth.takeScreenShotPositive( genMeth, "_CreatingSnapshotScreen");
		genMeth.waitForElementToBeVisible(driver, By.name(droidData.SnapshotTakenSuccessfully_Name), 3);
		genMeth.clickName( genMeth, droidData.BTNdismiss_Name);
		
		// delete all the images from camera roll
		genMeth.pressHomeButton();
		genMeth.clickName( genMeth, droidData.GalleryApp_Name);
		//genMeth.clickId(genMeth, "com.android.gallery3d:id/gl_root_view");

		//Boolean isGalleryEmpty = genMeth.fastCheckIsElementVisible( By.name(droidData.NoFiles_Name));
		Boolean isGalleryEmpty = genMeth.fastCheckIsElementVisible( By.name("Camera"));
		if (isGalleryEmpty != true){
			//Samsung Nexus
			genMeth.clickId(genMeth, "com.android.gallery3d:id/gl_root_view");
			genMeth.clickName(genMeth, droidData.BTNmoreOptions_Name);
			genMeth.clickName(genMeth, droidData.BTNselectItem_Name);
			genMeth.clickName(genMeth, "0 selected");
			genMeth.clickName(genMeth, "Select all");
			genMeth.clickName( genMeth, droidData.BTNdelete_name);
			genMeth.clickName( genMeth, droidData.BTNok_Name);

			/*
			 * LG
			driver.tap(1, 140, 280, 500);
			genMeth.clickName( genMeth, droidData.BTNdelete_name);
			genMeth.clickName( genMeth, droidData.CheckBoxselectAll_Name);
			genMeth.clickName( genMeth, droidData.BTNdelete_name);
			genMeth.clickName( genMeth, droidData.BTNyes_Name);
			*/
		}
		genMeth.pressBackButton();

		try {
			driver.runAppInBackground(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		genMeth.clickId(genMeth, droidData.IconLeftUpperBack_ID);
		genMeth.clickName(genMeth, droidData.Settings_Name);
		driver.scrollToExact(droidData.BTNrestoreSnapshot_Name);
		genMeth.clickName( genMeth, droidData.BTNrestoreSnapshot_Name);
		
		// Make sure a new entry was made
		genMeth.isElementInvisible(  By.name(droidData.NoFilesFound_Name));
		Boolean isAutoExist = genMeth.checkIsElementVisible(By.id(droidData.IconNumbrOfSharedContacts_ID));
		if (isAutoExist){
			
			genMeth.clickXpth(driver, genMeth, "//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]");
			
		}
		else{
		genMeth.clickId(genMeth, "com.pogoplug.android:id/list_item_image");
		}
		
		genMeth.clickName( genMeth, droidData.BTNstart_Name);
		/*
		 * LG
		genMeth.clickName( genMeth, droidData.BTNphone_Name);
		genMeth.waitForElementToBeVisible(driver, By.name("Set up my profile"), 2);
		genMeth.pressBackButton();
		*/
		genMeth.waitForElementToBeVisible(driver, By.name(droidData.RestoreCompletedSuccessfully_Name), 2);
		genMeth.clickName( genMeth, droidData.BTNdismiss_Name);
		
		//Now go verify that the images/videos were restored to the device successfully
		genMeth.pressHomeButton();
		genMeth.clickName( genMeth, droidData.GalleryApp_Name);
		genMeth.clickId(genMeth, "com.android.gallery3d:id/gl_root_view");
		genMeth.clickName(genMeth, droidData.BTNmoreOptions_Name);
		genMeth.clickName(genMeth, droidData.BTNselectItem_Name);
		genMeth.clickName(genMeth, "0 selected");
		genMeth.clickName(genMeth, droidData.BTNselectAll_Name);
		genMeth.isElementVisible(By.name("3 selected"));
		genMeth.takeScreenShotPositive(genMeth, "restored snapshot to gallery");
		genMeth.pressBackButton();		
		genMeth.pressBackButton();
		genMeth.pressBackButton();
		
		/*
		driver.tap(1, 140, 280, 500);
		genMeth.clickName( genMeth, droidData.BTNdelete_name);
		genMeth.clickName( genMeth, droidData.CheckBoxselectAll_Name);
		genMeth.isElementVisible( By.name("2 selected"));
		
		genMeth.clickId(  genMeth, droidData.BTNcancelGalleryApp_ID);
		genMeth.clickName( genMeth, "Camera, Navigate up");		
		driver.runAppInBackground(1);
		genMeth.clickId(  genMeth, droidData.IconLeftUpperBack_ID);
		genMeth.clickId(  genMeth, droidData.IconLeftUpperBack_ID);
		*/
		
		}
	
	
	@Test (enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Settings: Save Login",
			groups = { "Sanity Android1" })
			
	public void settingsKeepMeSignedIn() throws Exception, Throwable {

		// Keep me signed in = true
		genMeth.clickName(genMeth, droidData.Settings_Name);
//		genMeth.takeScreenShotPositive(genMeth, "keep me signed in is checked");
		
		try {
			driver.launchApp();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		genMeth.isElementVisible(By.name("Pogoplug Cloud"));
		
		
		// Keep me signed in = False
		genMeth.clickId(genMeth, droidData.IconLeftUpperBack_ID);
		genMeth.clickName(genMeth, droidData.Settings_Name);
		genMeth.clickName(genMeth, "Keep me signed in");
		
		try {
			driver.close();
			driver.launchApp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		genMeth.isElementVisible(By.id(droidData.BTNalreadyHaveAnAccount_id));

	}

	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Settings: Backup Enable/disable without upload in the background",
			groups = { "Regression Droid" })
	public void settingsBackupEnableDisable() throws Exception, Throwable {

		// Disable the Backup from tour
		

		// "Enable" the backup form LSM (Left Side Menu) & cancel it
		

		// Enable the Backup form LSM (Left Side Menu)
	

		// Enable the backup from settings (first enable it & then disable it from backup screen)
		

	}
	
	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Settings: Backup Enable/disable *with upload in the background",
			groups = { "Regression iOS" })// , dependsOnMethods={"successTest"})
	public void settingsBackupEnableDisableDuringUpload() throws Exception,Throwable {

		// login with new account & enable/disable the backup from Tour/Settings/LSM/Photo Gallery
		//iosData = genMeth.iOSelementInit(langEng);
	

		// Verify that the go unlimited tour text is displayed
	
		
		//Disable the backup from TOUR
		
		
		// verify that the backup is Disabled in LSM
		
				
		//Enable backup form LSM
		
		
		// verify that the backup is running
		
		//Disable the backup from SETTINGS
	
		//verify that the backup was disabled in LSM
	
		
		//Enable backup from TIMELINE 
		
		
		// check that the backup disabled notification isn't displayed
		
		
		// Disable the Backup form settings
	
		
		//Enable backup from VIDEOS 
		

		// check that the backup disabled notification isn't displayed
	
		
		// Disable the Backup form settings
	
		
		// Enable backup from ALBUMS
	

		// check that the backup disabled notification isn't displayed
		

		// Check that the backup has finished at ALBUMS,VIDEOS,PHOTO GALLERY & LSM
		
		
		// verify that the images were uploaded to the cloud
		
		
		// Make sure that the "Image not available" text doesn't displayed
		
	}

	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = " Backup running in background -> make sure process keeps alive and completes its queue even in background AND if taking new shots they are automatically backed up",
			groups = { "Sanity Android" })
	public void backupInBackground() throws Exception, Throwable {

		//webElementsIos iosData = genMeth.iOSelementInit(langEng);
		
		//Login with new account (*backup will initiate)
		
		
		// verify that the backup is Enable & running
		

		//Go to background/Sleep & wait 60 seconds
		driver.lockScreen(60);
		
		//Bring App back to foreground & make sure that backup has finished successfully
		
		
		//Verify that the backup is completed
		
		
		// Make sure that a random image is open successfully
	

		// Make sure that the "Image not available" text doesn't displayed
		


	}

	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Switching from Foreground to Background and vice versa use cases",
			groups = { "Sanity Android" })
	public void foregroundBackgroundSwitch() throws Exception, Throwable {

		//Take the app to background & foreground x times
		
		
		//Take the app to sleep/lock  x times
	

	}
	
		@Test(enabled = false , retryAnalyzer = Retry.class, testName = "Sanity Tests" , description = " Add remove files from favorites" ,
				groups = { "Sanity Android"} )
	public void Favorites() throws InterruptedException, IOException, ParserConfigurationException, SAXException{
			// open favorites & make sure that it is empty
		
			
			//positive empty favorites screenshot
		
			
			//Add image/video/song to favorites
		
			
			//Make sure that it is displayed in favorites
	
				
			// remove the files from favorites & make sure that the empty screen display
			
			
			//Now remove from favorites from Toolbar 
			
			
			
			//Add image/video/song to favorites
		
			
			//Make sure that it is displayed in favorites
		
			
			// go back to startup page
		
			
			
		}
		
	@Test(enabled = false, retryAnalyzer = Retry.class, testName = "Sanity Tests", description = "Adding & removing team folders",
			groups = { "Sanity Android" })
	public void addRemoveTeamFolders() throws Exception, Throwable {
		
		//webElementsIos iosData = genMeth.iOSelementInit(langEng);

		//Share user with team folder 
	
		
		// login with the SHARED user & make sure that the team folder was added & can be open
	
		
		//Cancel remove share
	
		
		//Remove share folder from the SHARED user

		//genMeth.clickName( iosData.BTNremoveShare_Name);

		
		//Make sure that the shared folder was removed
		
		
		// login with the SHARING user & make sure that the folder isn't shared anymore
		
				
		//Remove shared folder from the SHARING user
	
		
		//Share user with team folder & then remove share

		
		//Make sure that the folder isn't shared under the SHARED account
	
		
	}
	@Test(enabled = false, retryAnalyzer = Retry.class,  testName = "connection lost handling", description = "Checking how the app owrks while connection is lost & back again",
			dependsOnGroups = {"Sanity*"}, groups={ "Sanity Android"})
			
	public void connectionLost() throws InterruptedException, IOException, ParserConfigurationException, SAXException{
		
		genMeth.setWifiOn();
		
		// check app while connection is lost & return during login
		genMeth.signOutFromStartupAndroid(genMeth, droidData);
		
		genMeth.setAirplainMode();
		genMeth.clickId(  genMeth, droidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId( genMeth, droidData.TEXTFIELDemail_id, droidData.userUnlimited_name);
		Thread.sleep(1000);
		genMeth.sendId( genMeth, droidData.TEXTFIELDpassword_id, droidData.password);
		genMeth.clickId( genMeth, droidData.BTNlogin_id);
		genMeth.takeScreenShotPositive(genMeth, "login_without_connection");
		genMeth.isElementVisible(By.id(droidData.TEXTFIELDemail_id));
		
		
		// check app while connection is lost & post login
		genMeth.setWifiOn();
		genMeth.sendId( genMeth, droidData.TEXTFIELDemail_id, droidData.userUnlimited_name);
		genMeth.sendId( genMeth, droidData.TEXTFIELDpassword_id, droidData.password);
		genMeth.clickId( genMeth, droidData.BTNlogin_id);
		Thread.sleep(5000);
		
		genMeth.setAirplainMode();
		genMeth.clickName(genMeth, droidData.FileExplorer_Name);
		genMeth.clickName( genMeth, droidData.BTNrefresh_name);
		genMeth.isElementVisible(By.name("No internet connection"));
		genMeth.takeScreenShotPositive(genMeth, "No internet connection");
		
		genMeth.setWifiOn();
		genMeth.clickName(genMeth, droidData.BTNrefresh_name);
		genMeth.isElementInvisible(By.name("No internet connection"));
//		driver.scrollToExact("upload from existing test");
		genMeth.clickName(genMeth, "upload from existing test");
		
		// check app while connection is lost & back during backup/upload
		genMeth.clickName( genMeth, droidData.BTNupload_name);
		genMeth.clickName( genMeth, droidData.OPTIONcaptureNewPhoto_name);
		genMeth.clickName(genMeth, droidData.BTNcapturePhoto_GooglePhone_Name);
		genMeth.clickName(genMeth, droidData.BTNcapturePhoto_GooglePhone_Name);
		genMeth.clickName(genMeth, droidData.IconReviewDoneForImageGooglePhone_Name);
		
		//Now kill the connection
		genMeth.setAirplainMode();
		genMeth.clickClassName(driver, genMeth, droidData.BTNmoreOptions_ClassName);
		genMeth.clickName( genMeth, droidData.BTNrefresh_name);
		
		//Open connection & make sure that upload has finished successfully 
		genMeth.setWifiOn();
		genMeth.clickClassName(driver, genMeth, droidData.BTNmoreOptions_ClassName);
		genMeth.clickName( genMeth, droidData.BTNrefresh_name);
		
	// Check if the image display in the list
		WebElement uploadedImage = genMeth.returnId(driver, genMeth, "com.pogoplug.android:id/text_secondary");
		String lastUpload = uploadedImage.getText();
		String currentUpload= "None";
		Thread.sleep(1000);
		
	// Add an if that will verify that the Upload has finished (compare the KB - once not changing it probably has finished or stuck)	
		if (lastUpload != currentUpload ){
			genMeth.clickClassName(driver, genMeth, droidData.BTNmoreOptions_ClassName);
			genMeth.clickName( genMeth, droidData.BTNrefresh_name);
			lastUpload = uploadedImage.getText();
			Thread.sleep(5000);
			genMeth.clickClassName(driver, genMeth, droidData.BTNmoreOptions_ClassName);
			genMeth.clickName( genMeth, droidData.BTNrefresh_name);
			currentUpload = uploadedImage.getText();
			
		}
		
	// Open the image & make sure that it displays 
		genMeth.clickName( genMeth, currentUpload);
		
	// Make sure that the "Image not available" text doesn't displayed
		Thread.sleep(3000);
		genMeth.isElementInvisibleText( By.name(droidData.ImageNotAvailable_Name), droidData.ImageNotAvailable_Name);
		genMeth.takeScreenShotPositive( genMeth, "testUploadImage");
		Thread.sleep(1000);	
		genMeth.clickId(  genMeth, droidData.FullScreen_ID);
		genMeth.clickId(  genMeth, droidData.BTNhome_ID);
		
	// Delete the image
		genMeth.longPressElement(driver, genMeth, By.id(droidData.ListSecondaryText_ID));
		genMeth.clickName( genMeth, droidData.BTNdelete_name);
		genMeth.clickId(  genMeth, droidData.BTNdeleteConfirm_id);
		
	// Check that the image was deleted
		genMeth.isElementInvisible(  By.id(droidData.FullScreen_ID));
		genMeth.clickClassName(driver, genMeth, droidData.BTNmoreOptions_ClassName);
		genMeth.clickName( genMeth, droidData.BTNrefresh_name);
		genMeth.isElementVisible(By.name(droidData.NoFilesFound_Name));
		
		//Back to start page
		genMeth.pressBackButton();
		genMeth.clickId(genMeth, droidData.BTNlsm_ID);
		genMeth.isTextPresentAndroid(driver, By.name(droidData.CATEGORIES), droidData.CATEGORIES);


		
		// check app while connection is lost & back post refresh
		
	} 
	
	
	
	@Test(enabled = false, retryAnalyzer = Retry.class,  testName = "connection lost handling",
			description = "Checking how the app owrks while connection is lost & back again",
			groups={ "Sanity Android1"} )
	public void temp() throws IOException, ParserConfigurationException, SAXException, InterruptedException{
		
		
		genMeth.isTextPresentAndroid(driver, By.name(droidData.CATEGORIES), droidData.CATEGORIES);

		
		
		
	}
	
	@AfterSuite (alwaysRun=true)

		public void tearDown() throws Exception {
//			driver.removeApp("com.pogoplug.android");
		
			try {
				genMeth.setWifiOn();
				driver.quit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			SendResults sr = new SendResults("elicherni444@gmail.com", "meny@cloudengines.com", "TestNG results", "Test Results");
			sr.sendTestNGResult();
		
		}




	}



