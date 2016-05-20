package edu.oakland.portlet.service;

import org.springframework.stereotype.Service;

import javax.portlet.PortletRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import java.io.IOException;

//Spring annotations.
@Service
public class PreferenceService{

  //A public method that is called from the EditController. It takes a PortletRequest,
  //and a string that will either be null or 'on' (which comes from the checkbox)
  public void savePreferences(PortletRequest request, String value)
  throws ReadOnlyException, ValidatorException, IOException{

    //Changes the showPidm element in portlet.xml
    PortletPreferences preferences = request.getPreferences();
    preferences.setValue("showPidm", value);
    preferences.store();
  }

  //This Boolean method returcd ns a true or false value depending on whether or not
  //the pidm should be shown. This method is used in the MainController.
  public Boolean showPidmPreference(PortletRequest request){
    PortletPreferences preferences = request.getPreferences();
    String value = preferences.getValue("showPidm", null);
    if (value != null){
      return true;
    }else{
      return false;
    }
  }
}
