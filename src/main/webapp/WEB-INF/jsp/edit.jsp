<%--

    Licensed to Apereo under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Apereo licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

<%-- An actionURL linking the button and the updatePreferences method --%>
<portlet:actionURL var="updatePref">
  <portlet:param name="action" value="updatePreferences"/>
</portlet:actionURL>

<h1>Preferences</h1>
<form method="POST" action="${ updatePref }" role="form">
      <%-- Creates a class 'checkbox' and one checkbox. More can easily be added --%>
      <div class="exampledetails-checkbox">
          <%-- type determines what to display. Name is how we reference the object in java
               There is a single line conditional statement seeing if checkBoxChecker is true
               or false. If it is true, the box will be checked. Else, it won't be. --%>
          <input type="checkbox" name="chkboxOne" ${checkBoxChecker == true ? "checked" : ""} />
          Show PIDM
      </div>
  <button type="submit" id="exampledetails-btn-submit" name="submit" class="btn btn-default">Save Preference</button>
</form>

<portlet:renderURL var="viewUrl" portletMode="view"/>
