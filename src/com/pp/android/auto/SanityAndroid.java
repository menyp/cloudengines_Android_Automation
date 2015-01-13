package com.pp.android.auto;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.mobile.NetworkConnection;
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

	@BeforeMethod (alwaysRun = true)
	public void checkHomeScreen() throws InterruptedException, IOException, ParserConfigurationException, SAXException{

		// Check if the client still logged in & in StartUp screen before each test
		if (driver == null) {
			try {
				driver.removeApp(genMeth.getValueFromPropFile("appPackage"));
				driver.quit();
			} catch (Exception e) {
				// swallow if fails
			}
			driver = genMeth.setCapabilitiesAndroid(genMeth);
			genMeth.cleanLoginAndroid(driver, genMeth, androidData, androidData.userUnlimited_name );
		}

		else {
			boolean StartUpScreenDisplay = genMeth.checkIsElementVisibleNative( driver , By.name(androidData.Settings_Name));

			if (StartUpScreenDisplay != true) {

				try {
					driver.removeApp(genMeth.getValueFromPropFile("appPackage"));
					driver.quit();
				} catch (Exception e) {
					// swallow if fails
				}

				driver = genMeth.setCapabilitiesAndroid(genMeth);
				genMeth.cleanLoginAndroid(driver, genMeth, androidData, androidData.userUnlimited_name);

			}

		}

	}
	

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
		genMeth.isElementVisible(driver, By.name(currentDate));
		
	// sort the list in order to place the image in the first position
		genMeth.clickName(driver, genMeth,androidData.BTNsort_name);
		genMeth.clickName(driver,genMeth, androidData.OPTIONsortOldestFirst_name);

	// long press the folder (choosing the folder for deletion by swipe long duration- need to figure out how to do it by proper long press code)
		genMeth.longPressElement(driver, genMeth, By.name(currentDate));
		genMeth.clickName(driver, genMeth, androidData.BTNdelete_name);
		
	// Cancel the delete & make sure that the folder wasn't deleted
		genMeth.clickId(driver, genMeth, androidData.BTNcancelDelete_id);
		genMeth.isElementVisible(driver, By.name(currentDate));
		
	// now delete the folder & make sure it was deleted properly
		genMeth.longPressElement(driver, genMeth, By.name(currentDate));
		genMeth.clickName(driver, genMeth, androidData.BTNdelete_name);;
		genMeth.clickId(driver, genMeth, androidData.BTNdeleteConfirm_id);
		genMeth.isElementInvisibleNative(driver, By.name(currentDate));
		
	// Go to strtup page (LSM left side menu)
		genMeth.clickId(driver, genMeth, androidData.BTNlsm_ID);
		
		  }

	
	@Test (enabled = true ,testName="Sanity Tests", description = "Test the Upload utility with Android" , groups= {"Sanity Android"})	
	public void testUploadImage() throws ParserConfigurationException, SAXException, IOException, InterruptedException{
		
	// open pogoplug cloud & press
		genMeth.clickId(driver, genMeth, androidData.BTNlsm_ID);
		genMeth.clickName(driver, genMeth, "upload from existing test");
	// Capture an image
		genMeth.clickName(driver, genMeth, androidData.BTNupload_name);
		genMeth.clickName(driver, genMeth, androidData.OPTIONcaptureNewPhoto_name);
		Thread.sleep(2000);
		genMeth.clickId(driver, genMeth, androidData.BTNcapturePhoto_LG_id);
		genMeth.clickName(driver, genMeth, androidData.BTNok_Name);
		Thread.sleep(2000);
		genMeth.clickClassName(driver, genMeth, androidData.BTNmoreOptions_ClassName);
		genMeth.clickName(driver, genMeth, androidData.BTNrefresh_name);
		
	// Check if the image display in the list
		WebElement uploadedImage = genMeth.returnId(driver, genMeth, "com.pogoplug.android:id/text_secondary");
		String lastUpload = uploadedImage.getText();
		String currentUpload= "None";
		Thread.sleep(1000);
		
	// Add an if that will verify that the Upload has finished (compare the KB - once not changing it probably has finished or stuck)	
		if (lastUpload != currentUpload ){
			genMeth.clickClassName(driver, genMeth, androidData.BTNmoreOptions_ClassName);
			genMeth.clickName(driver, genMeth, androidData.BTNrefresh_name);
			lastUpload = uploadedImage.getText();
			Thread.sleep(5000);
			genMeth.clickClassName(driver, genMeth, androidData.BTNmoreOptions_ClassName);
			genMeth.clickName(driver, genMeth, androidData.BTNrefresh_name);
			currentUpload = uploadedImage.getText();
			
		}
		
	// Open the image & make sure that it displays 
		genMeth.clickName(driver, genMeth, currentUpload);
	// Make sure that the "Image not available" text doesn't displayed
		Thread.sleep(3000);
		genMeth.isElementInvisibleText(driver, By.name(androidData.ImageNotAvailable_Name), androidData.ImageNotAvailable_Name);
		genMeth.takeScreenShotPositive(driver, genMeth, "testUploadImage_"+currentUpload);
		Thread.sleep(1000);	
	//	genMeth.clickXpth(driver, genMeth, "//android.view.View[1]");
		genMeth.clickId(driver, genMeth, androidData.FullScreen_ID);
		genMeth.clickId(driver, genMeth, androidData.BTNhome_ID);
	// Delete the image
		genMeth.longPressElement(driver, genMeth, By.id(androidData.ListSecondaryText_ID));
		Thread.sleep(5000);
		genMeth.clickName(driver, genMeth, androidData.BTNdelete_name);
		genMeth.clickId(driver, genMeth, androidData.BTNdeleteConfirm_id);
	// Check that the image was deleted
		genMeth.isElementInvisibleNative(driver, By.id(androidData.FullScreen_ID));
		
		}
	
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Test TOUR for New accounts and for upgrade accounts",
			groups = { "Sanity Android" })
	public void testTour() throws Exception, Throwable {

		
		 // =============================================================== 
		 //        Tour for Free Account
		 //===============================================================
		 String curentTime = genMeth.currentTime();
		 
		genMeth.killAppAndroid(driver);
		driver = genMeth.setCapabilitiesAndroid(genMeth);
		genMeth.clickId(driver, genMeth, androidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDemail_id, androidData.userLimited_name);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDpassword_id, androidData.password);
		genMeth.tapId(driver, genMeth, androidData.BTNlogin_id);
		Thread.sleep(3000);
		
		// check Never loose a photo
		genMeth.isElementVisible(driver, By.name(androidData.NeverLoseAPhoto));
		genMeth.takeScreenShotPositive(driver, genMeth, curentTime + androidData.NeverLoseAPhoto );
		driver.swipe(600, 800, 50, 800, 600);
		
		//Check Transfer phones simply
		genMeth.isElementVisible(driver, By.name(androidData.TransferPhonesSimply_name));
		genMeth.takeScreenShotPositive(driver, genMeth, curentTime + androidData.TransferPhonesSimply_name );
		driver.swipe(600, 800, 50, 800, 600);
		// Check Unlimited protection
		genMeth.isElementVisible(driver, By.name(androidData.UnlimitedProtection_Name));
		genMeth.takeScreenShotPositive(driver, genMeth, curentTime + androidData.UnlimitedProtection_Name );
		
		// SKIP 
		genMeth.clickId(driver, genMeth, androidData.BTNskip_ID);
		
		// Check Backup
		genMeth.isElementVisible(driver, By.name(androidData.Backup_Name));
		genMeth.takeScreenShotPositive(driver, genMeth, curentTime + androidData.Backup_Name );
		
		// press the Continue button 
		genMeth.clickId(driver, genMeth, androidData.BTNfinishTour_id);	
		genMeth.isTextPresentAndroid(driver, By.name(androidData.CATEGORIES), androidData.CATEGORIES);
		
		//Verify that LOGOUT & RELOGIN will not prompt the tour
		genMeth.clickXpth(driver, genMeth, "//android.widget.RelativeLayout[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[7]");
		genMeth.clickName(driver, genMeth, androidData.OPTIONsignOut_Name);
		genMeth.clickName(driver, genMeth, androidData.BTNok_Name);
		genMeth.clickId(driver, genMeth, androidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDemail_id, androidData.userLimited_name);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDpassword_id, androidData.password);
		genMeth.tapId(driver, genMeth, androidData.BTNlogin_id);
		genMeth.isTextPresentAndroid(driver, By.name(androidData.CATEGORIES), androidData.CATEGORIES);
		
		 
		// same test only with Upgrade now at pogoplug.com
		genMeth.killAppAndroid(driver);
		driver = genMeth.setCapabilitiesAndroid(genMeth);
		genMeth.clickId(driver, genMeth, androidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDemail_id,androidData.userLimited_name);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDpassword_id,androidData.password);
		genMeth.tapId(driver, genMeth, androidData.BTNlogin_id);

		// check Never loose a photo
		genMeth.isElementVisible(driver, By.name(androidData.NeverLoseAPhoto));
		driver.swipe(600, 800, 50, 800, 600);

		// Check Transfer phones simply
		genMeth.isElementVisible(driver,By.name(androidData.TransferPhonesSimply_name));
		driver.swipe(600, 800, 50, 800, 600);
		// Check Unlimited protection
		genMeth.isElementVisible(driver,By.name(androidData.UnlimitedProtection_Name));
		genMeth.takeScreenShotPositive(driver, genMeth, curentTime + "_Tour");

		// UPGRADE now at pogoplug.com
		genMeth.clickId(driver, genMeth, androidData.BTNtourUpgradeNow_ID);
		genMeth.isElementVisible(driver, By.name(androidData.BillingUrl_Name));
		genMeth.takeScreenShotPositive(driver, genMeth, curentTime + "_Tour_Billing_Page");
		
		
		
	// =====================================================
	//     Tour for Unlimited Account
	// =====================================================
		 
		
		genMeth.killAppAndroid(driver);
		driver = genMeth.setCapabilitiesAndroid(genMeth);
		genMeth.clickId(driver, genMeth, androidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDemail_id, androidData.userUnlimited_name);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDpassword_id, androidData.password);
		genMeth.tapId(driver, genMeth, androidData.BTNlogin_id);
		
		// check Never loose a photo
		genMeth.isElementVisible(driver, By.name(androidData.NeverLoseAPhoto));
		driver.swipe(600, 800, 50, 800, 600);
		
		//Check Transfer phones simply
		genMeth.isElementVisible(driver, By.name(androidData.TransferPhonesSimply_name));
		genMeth.takeScreenShotPositive(driver, genMeth, curentTime + "_Tour_Finish");
		genMeth.clickId(driver, genMeth, "com.pogoplug.android:id/tour_indicator_placehoder");
		
		// Check Backup
		genMeth.isElementVisible(driver, By.name(androidData.Backup_Name));
		
		// press the Continue button 
		genMeth.clickId(driver, genMeth, androidData.BTNfinishTour_id);	
		genMeth.isTextPresentAndroid(driver, By.name(androidData.CATEGORIES), androidData.CATEGORIES);
		
		// logout & relogin will not prompt the tour
		genMeth.clickXpth(driver, genMeth, "//android.widget.RelativeLayout[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[7]");
		genMeth.clickName(driver, genMeth, androidData.OPTIONsignOut_Name);
		genMeth.clickName(driver, genMeth, androidData.BTNok_Name);
		genMeth.clickId(driver, genMeth, androidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDemail_id, androidData.userUnlimited_name);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDpassword_id, androidData.password);
		genMeth.tapId(driver, genMeth, androidData.BTNlogin_id);
		genMeth.isTextPresentAndroid(driver, By.name(androidData.CATEGORIES), androidData.CATEGORIES);
		
	}
	
	@Test(enabled = false, testName = "Sanity Tests", description = "Sign up- Create new user (Negetive positive test), Privacy Policy, TRUSTe",
			groups = { "Sanity Android" })
	public void createNewUser() throws Exception, Throwable {
//		genMeth.clickName(driver, genMeth, name);
		
		
	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "login with bad/missing credentials , forgot password (negative & positive)",
			groups = { "Sanity Android" })
	public void badCredentials() throws Exception, Throwable {
		String currentTime = genMeth.currentTime();
		genMeth.signOutFromStartupAndroid(genMeth, driver, androidData);
		genMeth.clickId(driver,  genMeth, androidData.BTNalreadyHaveAnAccount_id);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDemail_id, currentTime);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDpassword_id, androidData.password);
		genMeth.clickId(driver, genMeth, androidData.BTNlogin_id);
		Thread.sleep(3000);
		genMeth.takeScreenShotPositive(driver, genMeth, currentTime + "_Bad_login");
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDemail_id, androidData.userUnlimited_name);
		genMeth.sendId(driver, genMeth, androidData.TEXTFIELDpassword_id, androidData.password);
		genMeth.clickId(driver, genMeth, androidData.BTNlogin_id);		
		genMeth.isTextPresentAndroid(driver, By.name(androidData.CATEGORIES), androidData.CATEGORIES);


		// Login with bad credentials
	

		// Forgot your password Negative (attempt to restore password with a non existing email)
	
		
		// Forgot your password Positive (attempt to restore password with an existing email)
	
	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Search functionality & filter",
			groups = { "Sanity Android" })
	public void search() throws Exception, Throwable {
		
		String Random = genMeth.randomString();	

		//Search in ROOT for a folder
	

		// Search in ROOT for an image
		

		// Search in ROOT for empty results
		
		
		// Search in FOLDER for a folder
		

		// Search in FOLDER for an image


		// Search in FOLDER for empty results
	
		
		// Search in POGOPLUG for a folder

		
		// Search in POGOPLUG for an image
	
		// Search in POGOPLUG for empty results
		
		
		//   Music Player  
		
		
		//Search in Songs A song	
	

		//Search in songs empty results
		

		//Search in Artists song
		
		
		//Search in Artists empty results
		
		
		//Search in Albums song
		
		
		//Search in Albums empty results
	
		
		//Search in Genres song 
		
		//Search in Genres empty results
	
		//search in add users screen
		
		
		//search for a contact
	
		
		//no results search using clear & cancel 
	
		
		//Back to start up screen
		
	}
	
	@Test(enabled = true, testName = "Sanity Tests", description = "Settings: Passcode",
			groups = { "Sanity Android1" })
	public void createSnapShot() throws Exception, Throwable {
		
		Thread.sleep(1000);
		//driver.closeApp();
		//driver.launchApp();
		genMeth.clickName(driver, genMeth, androidData.Settings_Name);
		driver.scrollToExact("Restore Snapshot");
		genMeth.clickName(driver, genMeth, "Restore Snapshot");
		genMeth.clickName(driver, genMeth, androidData.BTNdelete_name);
		
		
		genMeth.clickName(driver, genMeth, "Gallery");
		

		// Make sure that there are no snap shot
		
		//Create a snapshot
		
		// Make sure a new entry was made
		
		// delete all the images from camera roll
		
		// Restore snapshot
		
		//Make sure all images were restored successfully 
		

		
		

		
		// Go to startup screen
		
		


	}
	
	@Test(enabled = false, testName = "Sanity Tests", description = "Settings: Save Login",
			groups = { "Sanity Android" })
	public void settingsSaveLoginSanity() throws Exception, Throwable {

		// Save Login = true
		
		
		// Save Login = false
		

	}

	@Test(enabled = false, testName = "Sanity Tests", description = "Settings: Backup Enable/disable without upload in the background",
			groups = { "Regression iOS" })
	public void settingsBackupEnableDisable() throws Exception, Throwable {

		// Disable the Backup
		

		// "Enable" the backup form LSM (Left Side Menu) & cancel it
		

		// Enable the Backup form LSM (Left Side Menu)
	

		// Enable the backup from settings (first enable it & then disable it from backup screen)
		

	}
	
	@Test(enabled = false, testName = "Sanity Tests", description = "Settings: Backup Enable/disable *with upload in the background",
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

	@Test(enabled = false, testName = "Sanity Tests", description = " Backup running in background -> make sure process keeps alive and completes its queue even in background AND if taking new shots they are automatically backed up",
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

	@Test(enabled = false, testName = "Sanity Tests", description = "Switching from Foreground to Background and vice versa use cases",
			groups = { "Sanity Android" })
	public void foregroundBackgroundSwitch() throws Exception, Throwable {

		//Take the app to background & foreground x times
		
		
		//Take the app to sleep/lock  x times
	

	}
	
		@Test(enabled = false , testName = "Sanity Tests" , description = " Add remove files from favorites" ,
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
		
	@Test(enabled = false, testName = "Sanity Tests", description = "Adding & removing team folders",
			groups = { "Sanity Android" })
	public void addRemoveTeamFolders() throws Exception, Throwable {
		
		//webElementsIos iosData = genMeth.iOSelementInit(langEng);

		//Share user with team folder 
	
		
		// login with the SHARED user & make sure that the team folder was added & can be open
	
		
		//Cancel remove share
	
		
		//Remove share folder from the SHARED user

		//genMeth.clickName(driver, iosData.BTNremoveShare_Name);

		
		//Make sure that the shared folder was removed
		
		
		// login with the SHARING user & make sure that the folder isn't shared anymore
		
				
		//Remove shared folder from the SHARING user
	
		
		//Share user with team folder & then remove share

		
		//Make sure that the folder isn't shared under the SHARED account
	
		
	}
	@Test(enabled = false, testName = "connection lost handling", description = "Checking how the app owrks while connection is lost & back again" )
	public void connectionLost(){
		
		// check app while connection is lost & back during login
		 
		 
		// check app while connection is lost & back during backup/upload
		
		
		
	} 
	
	
//
	@AfterSuite (alwaysRun=true)

		public void sendMail() throws Exception {
//			driver.removeApp("com.pogoplug.android");
		
			try {
				driver.quit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			//SendResults sr = new SendResults("elicherni444@gmail.com", "meny@cloudengines.com", "TestNG results", "Test Results");
			//sr.sendTestNGResult();
		
		}




	}



