<%@ page  import="org.gradletest.web.ServiceUser" %>

Check the console for HELLO WORLD

<% 
	new ServiceUser().doLog("HELLO WORLD");
%>