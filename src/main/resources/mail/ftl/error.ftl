<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Email</title>
	<META http-equiv=Content-Type content=text/html;charset=utf-8>
</head>
<body style="MARGIN:20px 0px;BACKGROUND-COLOR: #FFFFFF;">
<!-- TABELLA DEL TEMPLATE -->
<table cellPadding="0" cellSpacing="0" border="0" align="center" width="100%" style="HEIGHT: 100%;width:90%;border:2px solid ${normal};">
	<tr>
		<td style="WIDTH: 100%; HEIGHT: 1%;">
			<!-- START HEADER-->
          	<table cellSpacing=0 cellPadding=0 width="100%" border="0">
            	<TBODY>
            		<tr>
		            	<td width="1%" height="96" rowspan="2" style="background-color:${dark}"><IMG src="cid:logo" border=0></td>
		            	<td style="background-color:${normal}; FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #f6cd33; FONT-FAMILY: Verdana, Arial, Sans-Serif; text-align:right; padding-right:20px;" height="72px" width="99%">&nbsp;</td>
           			</tr>
           			<tr>
              			<td style="background-color:${dark};FONT-SIZE: 8px;" width="99%">&nbsp;</td>
           			</tr>
           		</TBODY>
          	</table>      
		</td>
	</tr>
	<tr>	
		<td style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; VERTICAL-ALIGN: top; WIDTH: 100%; PADDING-TOP: 10px; TEXT-ALIGN: center; background-color:#ffffff; FONT-FAMILY: Verdana, Arial, Sans-Serif; font-size:14px;">		  
		 	<!-- inizio corpo della mail -->  
			<table cellSpacing="0" cellPadding="0" width="98%" border="0" align="center">
				<tr>
					<td align="left">  
			      		Gentile utente,<BR>  
					  	l&#39;esecuzione del job '${jobname}' compiuta il ${start?datetime} non &egrave; terminata con successo.
					 </td>
		  		</tr>
		  		<tr>
		  			<td align="left">
	  					${exception}
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td style="WIDTH: 1%; HEIGHT: 1%">
			<!-- footer -->
			<table cellSpacing="0" cellPadding="0" width="100%" border="0">
				<tr>
					<td style="text-align:left; VERTICAL-ALIGN:bottom; background-color:#ffffff;" width="1%"><IMG alt="" src="cid:left"></TD>
				    <td style="FONT-WEIGHT: normal; FONT-SIZE: 14px; COLOR: #000000; FONT-FAMILY: Verdana, Arial, Sans-Serif; text-align:center; background-color:#ffffff;" align="center"  width="48%">&nbsp;</td>
				    <td style="FONT-WEIGHT: normal; FONT-SIZE: 14px; COLOR: #000000; FONT-FAMILY: Verdana, Arial, Sans-Serif; text-align:center; background-color:#ffffff;"  width="2%" align="center">
             			<div style="WIDTH: 400px; padding: 10; background-color: #F4F4F4;  border: 1px solid #EEEEEE; FONT-FAMILY: Verdana, Arial, Sans-Serif; FONT-SIZE: 10px; margin-bottom: 5px;">This email is generated by an automated system, please do not respond.<div>
            		</td>
            		<td style="FONT-WEIGHT: normal; FONT-SIZE: 14px; COLOR: #000000; FONT-FAMILY: Verdana, Arial, Sans-Serif; text-align:center; background-color:#ffffff;" align="center"  width="48%">&nbsp;</td>
				    <td style="text-align:right; VERTICAL-ALIGN:bottom; background-color:#ffffff;" width="1%"><IMG alt="" src="cid:right"></td>
				</tr>
				<tr>
				    <td height="24px" style="background-color:${dark};  font-weight: normal; font-size: 11px; color: #ffffff; font-family: verdana, arial, Sans-Serif; text-align:center;" colspan="5">${nameApplication}</td>
				</tr>
			</table>    
		</td>
	</tr>
</table>
<!-- FINE TABELLA DEL TEMPLATE -->
</body>
</html>



