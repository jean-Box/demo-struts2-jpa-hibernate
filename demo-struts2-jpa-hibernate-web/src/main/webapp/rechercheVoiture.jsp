<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
  <title><s:text name="titre" /></title>
</head>
<body>

<h1><s:text name="titre" /></h1>

<h3><s:text name="criteres.recherche" /></h3>

<s:form id="form">

	<s:select 	 key="marque" 		name="scrudVoiture.rechercheVoiture.marque" list="scrudVoiture.marques" headerValue="" headerKey="" listKey="id" listValue="nom" />
	<s:textfield key="modele" 		name="scrudVoiture.rechercheVoiture.modele"></s:textfield>
	<s:textfield key="reference" 	name="scrudVoiture.rechercheVoiture.reference"></s:textfield>
	
	<tr>
	  <td class="tdSubmit">
		<s:submit action="rechercheVoiture_rechercher" 	key="action.rechercher" cssStyle="cursor:pointer" theme="simple" />
		<s:submit action="rechercheVoiture_creer" 		key="action.creer" 		cssStyle="cursor:pointer" theme="simple" />
	  </td>
	</tr>
</s:form>

<br/>
<s:if test="%{voitures.size > 0}">

<table border="1" class="tableau" cellspacing="0">
	<thead>
		<tr>
			<th><s:text name="marque" /></th>		
			<th><s:text name="modele" /></th>		
			<th><s:text name="reference" /></th>		
			<th><s:text name="energie" /></th>		
			<th><s:text name="nb.portes" /></th>		
			<th>&nbsp;</th>
		</tr>		
	</thead>	
	<tbody>
		<s:iterator value="voitures">
		<tr>
			<td><s:property value="marque.nom"/></td>		
			<td><s:property value="modele"/></td>		
			<td><s:property value="reference"/></td>		
			<td><s:property value="energie"/></td>		
			<td><s:property value="nombrePortes"/></td>		
			<td><s:a href="rechercheVoiture_selectionner_%{id}.action"><s:text name="action.voir"/></s:a></td>
		</tr>				
		</s:iterator>		
	</tbody>	
</table>
<br/>
</s:if>

</body>
</html>