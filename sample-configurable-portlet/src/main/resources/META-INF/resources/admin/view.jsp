<%@include file="../init.jsp" %>

<liferay-portlet:actionURL name="editSettings" var="editSettingsUrl" />

<aui:form name="adminFm" method="post" action="<%= editSettingsUrl %>" >

	<aui:input name="name" type="text" value="${configurable.name()}" />
	
	<aui:button type="submit" value="save" name="sbmtFm" />

</aui:form>