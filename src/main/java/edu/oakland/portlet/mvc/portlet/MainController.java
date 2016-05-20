/**
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
//Package name
package edu.oakland.portlet.mvc.portlet;

//Required import statements for portlets
import java.util.Map;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletPreferences;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.ModelAndView;
import edu.oakland.portlet.service.PreferenceService;

//Spring annotations. @Controller is a special form of @Component
//When @Controller is annotated, it must also be declared in applicationContext.xml
@Controller
@RequestMapping("VIEW")

public class MainController {

  @Autowired
  PreferenceService preference;
    //Logger is used for catalina.out. Println statements also work.
    protected final Log logger = LogFactory.getLog(getClass());


    //This is called whenever the main view is loaded
    @RenderMapping
    public ModelAndView showMainView(
        final RenderRequest request, final RenderResponse response) {

        // creates your ModelAndView object and sets the main view
        final ModelAndView mav = new ModelAndView("main");
        if(logger.isDebugEnabled()) {
            logger.debug("Using view name main");
        }

        //Get the USER_INFO from portlet.xml,
        //which gets the data from personDirectoryContext.xml
        @SuppressWarnings("unchecked")
        final Map<String,String> userInfo = (Map<String,String>) request.
                getAttribute(PortletRequest.USER_INFO);

        //Gets the preferences stored in portlet.xml
        PortletPreferences preferences = request.getPreferences();
        //Gets the value of the specific portlet 'showPidm'
        String myPref = preferences.getValue("showPidm", null);

        //Adds your name to the portlet
        mav.addObject("displayName", userInfo.get("displayName"));

        //Checks (with PreferenceService) if the portlet should display
        //the pidm, or there is a preference saying otherwise
        //If there is, it will not be displayed.
        if (preference.showPidmPreference(request) == true){
          mav.addObject("displayPidm", userInfo.get("pidm"));
        }

        //Adds your email address to the portlet
        mav.addObject("emailAddress", userInfo.get("mail"));
        return mav;
    }
}
