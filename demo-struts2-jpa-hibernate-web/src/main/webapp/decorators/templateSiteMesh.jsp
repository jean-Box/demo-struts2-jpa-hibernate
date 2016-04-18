<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
  <title><decorator:title /></title>
  <link rel="stylesheet" href="css/BrightSide.css" type="text/css" />
  <link rel="stylesheet" href="css/style.css" type="text/css" />
  <script type="text/javascript" src="js/jquery-1.3.2.js"></script>
  <script type="text/javascript" src="js/jquery.validate.js"></script>	
  <script type="text/javascript" src="js/jquery.i18n.properties.js"></script>
  <decorator:head />
</head>
<body>
<!-- wrap starts here -->
<div id="wrap">

	<div id="header">
	
		<h1 id="logo"><s:text name="logo1" /><span class="green"><s:text name="logo2" /></span><span class="gray"><s:text name="logo3" /></span></h1>
		<h2 id="slogan"><s:text name="slogan" /></h2>
	
	</div>

	<!-- content-wrap starts here -->
	<div id="content-wrap"><img src="images/headerphoto.jpg" width="820" height="120" alt="headerphoto" class="no-border" />
	
		<div id="sidebar">
			<ul class="sidemenu">
				<li><a href="rechercheVoiture_afficher.action">Voiture</a></li>
			</ul>
		</div>
		
		<div id="main">
		
			<decorator:body />
			<div id="erreurs">
				<%--    block des erreurs metier  --%>
				<s:if test="messagesErreursMetier.size > 0">
					<br/>
					<h3><s:text name="erreur.titre"></s:text></h3>
					<ul><s:iterator value="messagesErreursMetier" var="err">
						<li><s:property value="#err" escape="false"/></li>
					</s:iterator></ul>
					<br/>
				</s:if>
			</div>
		
		
		
		</div>
		
			
		<!-- content-wrap ends here -->
	</div>
	
	<div id="footer">
	
		<div class="footer-left">
			<p class="align-left"><a href="http://km20.capgemini.com/community/312283"><strong><s:text name="footer.left1" /></strong></a> | <a href="http://wiki.capgemini.com/index.php/KM2:_Ing%C3%A9nierie_du_logiciel"><s:text name="footer.left2" /></a></p>
		</div>
		
		<div class="footer-right">
			<p class="align-right"><s:text name="footer.right1" /></p>
		</div>
	
	</div> 
</div>
</body>
</html>
    