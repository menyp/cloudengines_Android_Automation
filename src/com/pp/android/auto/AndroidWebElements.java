package com.pp.android.auto;


import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class AndroidWebElements {
	//GenericMethods genMeth = new GenericMethods();

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
	String BTNcaptureVideo_id;
	String BTNcaptureDone_id;
	String BTNretakeCapture_id;
	String BTNcancelCapture_id;
	String BTNdeleteConfirm_id;
	String BTNcancelDelete_id;
	String BTNdismissNewFolder_id;
	String BTNcreateNewFolder_id;
	String BTNopenLSM_xpth;
	
	String TEXTFIELDemail_id;
	String TEXTFIELDpassword_id;
	
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
	
	String LIST1_xpth;
	String LIST2_xpth;
	String LIST3_xpth;
	String LIST4_xpth;
	String LIST5_xpth;
	String LIST6_xpth;
	
	String userUnlimited_name;
	
	public AndroidWebElements(String langXml, String xmlPath ) throws ParserConfigurationException, SAXException, IOException{
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
		
		
		this.LIST1_xpth = XmlHandel.readAndroidXml("LIST1_xpth", langXml, xmlPath);
		this.LIST2_xpth = XmlHandel.readAndroidXml("LIST2_xpth", langXml, xmlPath);
		this.LIST3_xpth = XmlHandel.readAndroidXml("LIST3_xpth", langXml, xmlPath);
		this.LIST4_xpth = XmlHandel.readAndroidXml("LIST4_xpth", langXml, xmlPath);
		this.LIST5_xpth = XmlHandel.readAndroidXml("LIST5_xpth", langXml, xmlPath);
		this.LIST6_xpth = XmlHandel.readAndroidXml("LIST6_xpth", langXml, xmlPath);
		
		this.userUnlimited_name = XmlHandel.readAndroidXml("userUnlimited_name", langXml, xmlPath);

		
	}

}
