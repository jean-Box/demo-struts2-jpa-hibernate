<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
  <title><s:text name="titre" /></title>
  
  <!--<script type="">
	$(document).ready(function(){
   		$("#form").validate({
		  	rules: {
    			nombrePortes: {
					required: true
    			}
  			}
		});
  
  	});

	jQuery.i18n.properties({
	    name:'messages',
	    path:'js/properties/',
	    mode:'map',
	    language:'fr',
	    callback: function() {
	    	// Surcharger les messages par ceux récupérés des fichiers properties
			jQuery.validator.messages.required 		= jQuery.i18n.prop('required');
			jQuery.validator.messages.remote 		= jQuery.i18n.prop('remote'); 
			jQuery.validator.messages.email 		= jQuery.i18n.prop('email') ;
			jQuery.validator.messages.url 			= jQuery.i18n.prop('url') ;
			jQuery.validator.messages.date 			= jQuery.i18n.prop('date');
			jQuery.validator.messages.dateISO 		= jQuery.i18n.prop('dateISO') ;
			jQuery.validator.messages.number 		= jQuery.i18n.prop('number') ;
			jQuery.validator.messages.digits 		= jQuery.i18n.prop('digits') ;
			jQuery.validator.messages.creditcard 	= jQuery.i18n.prop('creditcard') ;
			jQuery.validator.messages.equalTo 		= jQuery.i18n.prop('equalTo') ;
			jQuery.validator.messages.accept 		= jQuery.i18n.prop('accept');
    	}
	});

  </script>
--></head>
<body>

<h1><s:text name="titre" /></h1>

<s:form id="form">

	<s:select 	 key="marque" 				name="scrudVoiture.voiture.marque.id" 	list="scrudVoiture.marques" headerValue="" headerKey="" listKey="id" listValue="nom" cssClass="required" />
	<s:textfield key="modele" 				name="scrudVoiture.voiture.modele" 				cssClass="required"></s:textfield>
	<s:textfield key="reference"			name="scrudVoiture.voiture.reference" 			cssClass="required"></s:textfield>	
	<s:textfield key="nb.portes" 			name="scrudVoiture.voiture.nombrePortes" ></s:textfield>
	<s:select 	 key="energie" 				name="scrudVoiture.voiture.energie" list="energies"/>
	<s:textfield key="puissance.fiscale" 	name="scrudVoiture.voiture.puissanceFiscale" 	cssClass="digits"></s:textfield>
	
	<tr>
	  <td class="tdSubmit">
	  	<s:submit action="detailVoiture_retour" key="action.annuler" theme="simple" />
		<s:if test="%{scrudVoiture.creation == true}">
			<s:submit action="detailVoiture_creer" key="action.creer" theme="simple" />
		</s:if>
		<s:else>
			<s:submit action="detailVoiture_modifier" 	key="action.modifier"  theme="simple" />
			<s:submit action="detailVoiture_supprimer" 	key="action.supprimer" theme="simple" onclick="return confirm('Etes-vous sûr ?')" />
		</s:else>
	  </td>
	</tr>
</s:form>

</body>
</html>