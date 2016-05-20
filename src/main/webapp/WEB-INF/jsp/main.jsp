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

<%-- Portlet Url's --%>
<h2><spring:message code="portlet.hello"/> ${ fn:escapeXml(displayName) }!</h2>

<%-- Conditional Statement determines if the PIDM line should be displayed or not --%>
<c:if test="${displayPidm != null}">
  <p><spring:message code="portlet.pidm"/> ${ fn:escapeXml(displayPidm) }</p>
</c:if>

<p><spring:message code="portlet.emailAddress"/> ${ fn:escapeXml(emailAddress) }</p>
