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
package edu.oakland.portlet.mvc.portlet;

import java.io.IOException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import javax.portlet.PortletModeException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.ModelAndView;
import edu.oakland.portlet.service.PreferenceService;

//Spring annotations. @Controller is a special form of @Component
//When @Controller is annotated, it must also be declared in applicationContext.xml
@Controller
@RequestMapping("EDIT")
public class EditController {

    @Autowired
    PreferenceService pref;

    //Logger is used for catalina.out. Println statements also work.
    protected final Log logger = LogFactory.getLog(getClass());

    @RenderMapping
    public ModelAndView showMainView(
            final RenderRequest request, final RenderResponse response) {

        // creates your ModelAndView object and sets the edit view
        final ModelAndView mav = new ModelAndView("edit");

        //Creates an object that can be passed to the jsp that will either be
        //'on' or null. Used to set the checkbox as checked or unchecked whenever
        //a user switches to the edit view.
        mav.addObject("checkBoxChecker", pref.showPidmPreference(request));
        return mav;
    }

    //This action is found in the edit.jsp file
    //See spring documentation for requestmapping help
    @RequestMapping(params="action=updatePreferences")
    public void updatePreferences(ActionRequest request, ActionResponse response)
     throws PortletModeException {


      try {
          //Calls a function from the PreferenceService class, and passes request
          //and a string value obtained from the checkbox.
          //It will either be 'on' or null.
          pref.savePreferences(request, request.getParameter("chkboxOne"));
      } catch(ReadOnlyException|ValidatorException|IOException ex) {
          logger.error("Exception thrown: " + ex.getMessage());
      } catch(Exception e) {
          logger.error("Exception thrown: " + e.getMessage());
      }

      response.setPortletMode(PortletMode.VIEW);
    }
}
