package com.pp.android.auto;


import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class AndroidWebElements {

	String BTNalreadyHaveAnAccount_id;
	String BTNlogin_id;
	String BTNfinishTour_id;
	String BTNcontinue_id;
	String BTNupload_name;
	String BTNsearch_name;
	String BTNrefresh_name;
	String BTNnewFolder_name;
	String BTNsort_name;
	String BTNdelete_name;
	String BTNcapturePhoto_id;
	String BTNcapturePhoto_LG_id;
	String BTNcaptureVideo_id;
	String BTNcaptureDone_id;
	String BTNretakeCapture_id;
	String BTNcancelCapture_id;
	String BTNdeleteConfirm_id;
	String BTNcancelDelete_id;
	String BTNdismissNewFolder_id;
	String BTNcreateNewFolder_id;
	String BTNopenLSM_xpth;
	String BTNlsm_ID;
	String BTNcontinue_Name;
	String BTNok_Name;
	String BTNmoreOptions_ClassName;
	String BTNsingOut_Name;
	String BTNrestoreSnapshot_Name;
	String BTNcreateSnapshot_Name;
	String BTNdismiss_Name;
	String BTNyes_Name;
	String BTNstart_Name;
	String BTNphone_Name;
	String BTNcancelGalleryApp_ID;
	String BTNmoreOptions_Name;
	String BTNselectItem_Name;
	String BTNselectAll_Name;
	String BTNcancel_Name;
	String BTNcapturePhoto_GooglePhone_Name;
	String BTNcreateAccount_Name;
	String BTNupgrade_Name;
		
	String TEXTFIELDgeneral_ID;
	String TEXTFIELDemail_id;
	String TEXTFIELDpassword_id;
	String SearchFolderFromFolder_Name;
	String GalleryApp_Name;
	
	String OPTIONcaptureNewPhoto_name;
	String OPTIONcaptureNewVideo_name;
	String OPTIONlocalPhoto_name;
	String OPTIONlocalVideo;
	String OPTIONmusicPlayer_name;
	String OPTIONanyFile_name;
	String OPTIONsortAZ_name;
	String OPTIONsortZA_name;
	String OPTIONsortNewestFirst_name;
	String OPTIONsortOldestFirst_name;
	String OPTIONsortSmallestFirst_name;
	String OPTIONsortBiggestFirst_name;
	String OPTIONsignOut_Name;
	
	
	
	String LIST1_xpth;
	String LIST2_xpth;
	String LIST3_xpth;
	String LIST4_xpth;
	String LIST5_xpth;
	String LIST6_xpth;
	String ListSecondaryText_ID;
	String scrollDown;
	String scrollUp;
	
	//STRINGS
	String userUnlimited_name;
	String userLimited_name;
	String password;
	String NeverLoseAPhoto;
	String CATEGORIES;
	String FileExplorer_Name;
	String Settings_Name;
	String 	Backup_Name;
	String ImageNotAvailable_ID;
	String ImageNotAvailable_Name;
	String FullScreen_ID;
	String BTNhome_ID;
	String TransferPhonesSimply_name;
	String UnlimitedProtection_Name;
	String BTNskip_ID;
	String TourUpgradeTextInfo_Name;
	String BTNtourUpgradeNow_ID;
	String BillingUrl_Name;
	String TransferPhonesSimplyFullText_name;
	String NoFilesFound_Name;
	String SnapshotTakenSuccessfully_Name;
	String NoFiles_Name;
	String RestoreCompletedSuccessfully_Name;
	String SearchFolderFromRoot_Name;
	String SearchImageFromRoot_Name;
	String SearchImageFromFolder_Name;
	String SearchSongInSong_Name;
	String SearchSongInArtists_Name;
	String SearchSongInAlbums_Name;
	String SearchSongInGenres_Name;
	String Loading_Name;
	String MusicPlayer_Name;
	String Artists_Name;
	String Albums_name;
	String Genres_Name;

	
	
	//Checkbox
	String CheckBoxselectAll_Name;
	
	//Icons
	String IconLeftUpperBack_ID;
	String IconNumbrOfSharedContacts_ID;
	String IconAddShareUser_ID;
	String IconReviewDoneForImageGooglePhone_Name;
	String IconIagreeToTermsOfService_Name;
	
	//General Info
	String ConnectionAIRPLANE_MODE;
	String ConnectionWIFI;
	String CoonectionDATA;
	String ConnectionALL;
	
	

	
	public AndroidWebElements(String langXml, String xmlPath ) throws ParserConfigurationException, SAXException, IOException, InterruptedException{
		this.BTNalreadyHaveAnAccount_id = XmlHandel.readAndroidXml("BTNalreadyHaveAnAccount_id", langXml, xmlPath);
		this.BTNlogin_id = XmlHandel.readAndroidXml("BTNlogin_id", langXml, xmlPath);
		this.BTNfinishTour_id = XmlHandel.readAndroidXml("BTNfinishTour_id", langXml, xmlPath);
		this.BTNcontinue_id = XmlHandel.readAndroidXml("BTNcontinue_id", langXml, xmlPath);
		this.BTNupload_name = XmlHandel.readAndroidXml("BTNupload_name", langXml, xmlPath);
		this.BTNsearch_name = XmlHandel.readAndroidXml("BTNsearch_name", langXml, xmlPath);
		this.BTNrefresh_name = XmlHandel.readAndroidXml("BTNrefresh_name", langXml, xmlPath);
		this.BTNnewFolder_name = XmlHandel.readAndroidXml("BTNnewFolder_name", langXml, xmlPath);
		this.BTNsort_name = XmlHandel.readAndroidXml("BTNsort_name", langXml, xmlPath);
		this.BTNdelete_name = XmlHandel.readAndroidXml("BTNdelete_name", langXml, xmlPath);
		this.BTNcapturePhoto_id = XmlHandel.readAndroidXml("BTNcapturePhoto_id", langXml, xmlPath);
		this.BTNcaptureVideo_id = XmlHandel.readAndroidXml("BTNcaptureVideo_id", langXml, xmlPath);
		this.BTNcaptureDone_id = XmlHandel.readAndroidXml("BTNcaptureDone_id", langXml, xmlPath);
		this.BTNretakeCapture_id = XmlHandel.readAndroidXml("BTNretakeCapture_id", langXml, xmlPath);
		this.BTNcancelCapture_id = XmlHandel.readAndroidXml("BTNcancelCapture_id", langXml, xmlPath);
		this.BTNdeleteConfirm_id = XmlHandel.readAndroidXml("BTNdeleteConfirm_id", langXml, xmlPath);
		this.BTNcancelDelete_id = XmlHandel.readAndroidXml("BTNcancelDelete_id", langXml, xmlPath);
		this.BTNdismissNewFolder_id = XmlHandel.readAndroidXml("BTNdismissNewFolder_id", langXml, xmlPath);
		this.BTNcreateNewFolder_id = XmlHandel.readAndroidXml("BTNcreateNewFolder_id", langXml, xmlPath);
		this.BTNopenLSM_xpth = XmlHandel.readAndroidXml("BTNopenLSM_xpth", langXml, xmlPath);
		this.TEXTFIELDgeneral_ID = XmlHandel.readAndroidXml("TEXTFIELDgeneral_ID", langXml, xmlPath);
		this.BTNlsm_ID = XmlHandel.readAndroidXml("BTNlsm_ID", langXml, xmlPath);
		this.BTNcontinue_Name = XmlHandel.readAndroidXml("BTNcontinue_Name", langXml, xmlPath);
		this.BTNcapturePhoto_LG_id = XmlHandel.readAndroidXml("BTNcapturePhoto_LG_id", langXml, xmlPath);
		this.BTNok_Name = XmlHandel.readAndroidXml("BTNok_Name", langXml, xmlPath);
		this.BTNmoreOptions_ClassName = XmlHandel.readAndroidXml("BTNmoreOptions_ClassName", langXml, xmlPath);
		this.BTNhome_ID = XmlHandel.readAndroidXml("BTNhome_ID", langXml, xmlPath);
		this.BTNskip_ID = XmlHandel.readAndroidXml("BTNskip_ID", langXml, xmlPath);
		this.BTNsingOut_Name = XmlHandel.readAndroidXml("BTNsingOut_Name", langXml, xmlPath);
		this.BTNrestoreSnapshot_Name = XmlHandel.readAndroidXml("BTNrestoreSnapshot_Name", langXml, xmlPath);
		this.BTNcreateSnapshot_Name = XmlHandel.readAndroidXml("BTNcreateSnapshot_Name", langXml, xmlPath);
		this.BTNdismiss_Name = XmlHandel.readAndroidXml("BTNdismiss_Name", langXml, xmlPath);
		this.BTNyes_Name = XmlHandel.readAndroidXml("BTNyes_Name", langXml, xmlPath);
		this.BTNstart_Name = XmlHandel.readAndroidXml("BTNstart_Name", langXml, xmlPath);
		this.BTNphone_Name = XmlHandel.readAndroidXml("BTNphone_Name", langXml, xmlPath);
		this.BTNcancelGalleryApp_ID = XmlHandel.readAndroidXml("BTNcancelGalleryApp_ID", langXml, xmlPath);
		this.BTNmoreOptions_Name = XmlHandel.readAndroidXml("BTNmoreOptions_Name", langXml, xmlPath);
		this.BTNselectItem_Name = XmlHandel.readAndroidXml("BTNselectItem_Name", langXml, xmlPath);
		this.BTNselectAll_Name = XmlHandel.readAndroidXml("BTNselectAll_Name", langXml, xmlPath);
		this.BTNcancel_Name = XmlHandel.readAndroidXml("BTNcancel_Name", langXml, xmlPath);
		this.BTNcapturePhoto_GooglePhone_Name = XmlHandel.readAndroidXml("BTNcapturePhoto_GooglePhone_Name", langXml, xmlPath);
		this.BTNcreateAccount_Name = XmlHandel.readAndroidXml("BTNcreateAccount_Name", langXml, xmlPath);
		this.BTNupgrade_Name = XmlHandel.readAndroidXml("BTNupgrade_Name", langXml, xmlPath);

		

		this.TEXTFIELDemail_id = XmlHandel.readAndroidXml("TEXTFIELDemail_id", langXml, xmlPath);
		this.TEXTFIELDpassword_id = XmlHandel.readAndroidXml("TEXTFIELDpassword_id", langXml, xmlPath);
		this.OPTIONcaptureNewPhoto_name = XmlHandel.readAndroidXml("OPTIONcaptureNewPhoto_name", langXml, xmlPath);
		this.OPTIONcaptureNewVideo_name = XmlHandel.readAndroidXml("OPTIONcaptureNewVideo_name", langXml, xmlPath);
		this.OPTIONlocalPhoto_name = XmlHandel.readAndroidXml("OPTIONlocalPhoto_name", langXml, xmlPath);
		this.OPTIONlocalVideo = XmlHandel.readAndroidXml("OPTIONlocalVideo", langXml, xmlPath);
		this.OPTIONmusicPlayer_name = XmlHandel.readAndroidXml("OPTIONmusicPlayer_name", langXml, xmlPath);
		this.OPTIONanyFile_name = XmlHandel.readAndroidXml("OPTIONanyFile_name", langXml, xmlPath);
		this.OPTIONsortAZ_name = XmlHandel.readAndroidXml("OPTIONsortAZ_name", langXml, xmlPath);
		this.OPTIONsortZA_name = XmlHandel.readAndroidXml("OPTIONsortZA_name", langXml, xmlPath);
		this.OPTIONsortNewestFirst_name = XmlHandel.readAndroidXml("OPTIONsortNewestFirst_name", langXml, xmlPath);
		this.OPTIONsortOldestFirst_name = XmlHandel.readAndroidXml("OPTIONsortOldestFirst_name", langXml, xmlPath);
		this.OPTIONsortSmallestFirst_name = XmlHandel.readAndroidXml("OPTIONsortSmallestFirst_name", langXml, xmlPath);
		this.OPTIONsortBiggestFirst_name = XmlHandel.readAndroidXml("OPTIONsortBiggestFirst_name", langXml, xmlPath);
		this.OPTIONsignOut_Name = XmlHandel.readAndroidXml("OPTIONsignOut_Name", langXml, xmlPath);

		
		
		
		this.LIST1_xpth = XmlHandel.readAndroidXml("LIST1_xpth", langXml, xmlPath);
		this.LIST2_xpth = XmlHandel.readAndroidXml("LIST2_xpth", langXml, xmlPath);
		this.LIST3_xpth = XmlHandel.readAndroidXml("LIST3_xpth", langXml, xmlPath);
		this.LIST4_xpth = XmlHandel.readAndroidXml("LIST4_xpth", langXml, xmlPath);
		this.LIST5_xpth = XmlHandel.readAndroidXml("LIST5_xpth", langXml, xmlPath);
		this.LIST6_xpth = XmlHandel.readAndroidXml("LIST6_xpth", langXml, xmlPath);
		this.ListSecondaryText_ID = XmlHandel.readAndroidXml("ListSecondaryText_ID", langXml, xmlPath);

		this.CheckBoxselectAll_Name = XmlHandel.readAndroidXml("CheckBoxselectAll_Name", langXml, xmlPath);

		
		this.userUnlimited_name = XmlHandel.readAndroidXml("userUnlimited_name", langXml, xmlPath);
		this.userLimited_name = XmlHandel.readAndroidXml("userLimited_name", langXml, xmlPath);
		this.password = XmlHandel.readAndroidXml("password", langXml, xmlPath);
		this.NeverLoseAPhoto = XmlHandel.readAndroidXml("NeverLoseAPhoto", langXml, xmlPath);
		this.CATEGORIES = XmlHandel.readAndroidXml("CATEGORIES", langXml, xmlPath);
		this.FileExplorer_Name = XmlHandel.readAndroidXml("FileExplorer_Name", langXml, xmlPath);
		this.Settings_Name = XmlHandel.readAndroidXml("Settings_Name", langXml, xmlPath);
		this.Backup_Name = XmlHandel.readAndroidXml("Backup_Name", langXml, xmlPath);
		this.ImageNotAvailable_ID = XmlHandel.readAndroidXml("ImageNotAvailable_ID", langXml, xmlPath);
		this.ImageNotAvailable_Name = XmlHandel.readAndroidXml("ImageNotAvailable_Name", langXml, xmlPath);
		this.FullScreen_ID = XmlHandel.readAndroidXml("FullScreen_ID", langXml, xmlPath);
		this.scrollDown = XmlHandel.readAndroidXml("scrollDown", langXml, xmlPath);
		this.scrollUp = XmlHandel.readAndroidXml("scrollUp", langXml, xmlPath);
		this.TransferPhonesSimply_name = XmlHandel.readAndroidXml("TransferPhonesSimply_name", langXml, xmlPath);
		this.UnlimitedProtection_Name = XmlHandel.readAndroidXml("UnlimitedProtection_Name", langXml, xmlPath);
		this.TourUpgradeTextInfo_Name = XmlHandel.readAndroidXml("TourUpgradeTextInfo_Name", langXml, xmlPath);
		this.BTNtourUpgradeNow_ID = XmlHandel.readAndroidXml("BTNtourUpgradeNow_ID", langXml, xmlPath);
		this.BillingUrl_Name = XmlHandel.readAndroidXml("BillingUrl_Name", langXml, xmlPath);		
		this.TransferPhonesSimplyFullText_name = XmlHandel.readAndroidXml("TransferPhonesSimplyFullText_name", langXml, xmlPath);
		this.NoFilesFound_Name = XmlHandel.readAndroidXml("NoFilesFound_Name", langXml, xmlPath);
		this.SnapshotTakenSuccessfully_Name = XmlHandel.readAndroidXml("SnapshotTakenSuccessfully_Name", langXml, xmlPath);
		this.NoFiles_Name = XmlHandel.readAndroidXml("NoFiles_Name", langXml, xmlPath);
		this.GalleryApp_Name = XmlHandel.readAndroidXml("GalleryApp_Name", langXml, xmlPath);
		this.RestoreCompletedSuccessfully_Name = XmlHandel.readAndroidXml("RestoreCompletedSuccessfully_Name", langXml, xmlPath);
		this.SearchFolderFromRoot_Name = XmlHandel.readAndroidXml("SearchFolderFromRoot_Name", langXml, xmlPath);
		this.SearchImageFromRoot_Name = XmlHandel.readAndroidXml("SearchImageFromRoot_Name", langXml, xmlPath);
		this.SearchFolderFromFolder_Name = XmlHandel.readAndroidXml("SearchFolderFromFolder_Name", langXml, xmlPath);
		this.SearchImageFromFolder_Name = XmlHandel.readAndroidXml("SearchImageFromFolder_Name", langXml, xmlPath);
		this.SearchSongInSong_Name = XmlHandel.readAndroidXml("SearchSongInSong_Name", langXml, xmlPath);
		this.SearchSongInArtists_Name = XmlHandel.readAndroidXml("SearchSongInArtists_Name", langXml, xmlPath);
		this.SearchSongInAlbums_Name = XmlHandel.readAndroidXml("SearchSongInAlbums_Name", langXml, xmlPath);
		this.SearchSongInGenres_Name = XmlHandel.readAndroidXml("SearchSongInGenres_Name", langXml, xmlPath);
		this.Loading_Name = XmlHandel.readAndroidXml("Loading_Name", langXml, xmlPath);
		this.MusicPlayer_Name = XmlHandel.readAndroidXml("MusicPlayer_Name", langXml, xmlPath);
		this.Artists_Name = XmlHandel.readAndroidXml("Artists_Name", langXml, xmlPath);
		this.Albums_name = XmlHandel.readAndroidXml("Albums_name", langXml, xmlPath);
		this.Genres_Name = XmlHandel.readAndroidXml("Genres_Name", langXml, xmlPath);
		
		
		this.IconLeftUpperBack_ID = XmlHandel.readAndroidXml("IconLeftUpperBack_ID", langXml, xmlPath);
		this.IconNumbrOfSharedContacts_ID = XmlHandel.readAndroidXml("IconNumbrOfSharedContacts_ID", langXml, xmlPath);
		this.IconAddShareUser_ID = XmlHandel.readAndroidXml("IconAddShareUser_ID", langXml, xmlPath);
		this.IconReviewDoneForImageGooglePhone_Name = XmlHandel.readAndroidXml("IconReviewDoneForImageGooglePhone_Name", langXml, xmlPath);
		this.IconIagreeToTermsOfService_Name = XmlHandel.readAndroidXml("IconIagreeToTermsOfService_Name", langXml, xmlPath);

		
		
		this.ConnectionAIRPLANE_MODE = XmlHandel.readAndroidXml("ConnectionAIRPLANE_MODE", langXml, xmlPath);
		this.ConnectionWIFI = XmlHandel.readAndroidXml("ConnectionWIFI", langXml, xmlPath);
		this.CoonectionDATA = XmlHandel.readAndroidXml("CoonectionDATA", langXml, xmlPath);
		this.ConnectionALL = XmlHandel.readAndroidXml("ConnectionALL", langXml, xmlPath);

	
		
		

	}

}
