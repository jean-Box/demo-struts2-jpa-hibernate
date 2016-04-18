<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title><s:text name="erreur.titre"/></title>
    </head>
    
    <body class="body">
      		
  		<%--  en cas d'erreur technique --%>
		<s:if test="erreurTechnique">
			<h1><s:text name="erreur.technique.inattendue"></s:text></h1>
			<input type="button" onclick="document.location.href='index.html'" value="Accueil">
			<br>
			<br>
			<pre><s:property value="throwable.print" /></pre>
		</s:if>
	
		

    </body>
</html>