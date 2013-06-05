<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

	<title>kiwi</title>
	<meta charset="utf-8">
	
	<!-- Global stylesheets -->
	<link href="<c:url value="/css/reset.css"/>" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/form.css"/>" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/standard.css"/>" rel="stylesheet" type="text/css">
	
	<!-- Comment/uncomment one of these files to toggle between fixed and fluid layout -->
	<!--<link href="css/960.gs.css" rel="stylesheet" type="text/css">-->
	<link href="css/960.gs.fluid.css" rel="stylesheet" type="text/css">
	
	<!-- Custom styles -->
	<link href="<c:url value="/css/simple-lists.css"/>" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/block-lists.css"/>" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/planning.css"/>" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/table.css"/>" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/calendars.css"/>" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/wizard.css"/>" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/gallery.css"/>" rel="stylesheet" type="text/css">
	
	<!-- Favicon -->
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/favicon.ico"/>">
	<link rel="icon" type="image/png" href="<c:url value="/favicon-large.png"/>">
	
	<!-- Generic libs -->
	<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.10.3/js/jquery-1.9.1.js"/>"></script>

	
	<!-- Template libs -->
	<script type="text/javascript" src="<c:url value="/js/jquery.accessibleList.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/searchField.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/standard.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.tip.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.hashchange.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.contextMenu.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.modal.js"/>"></script>
	
	<!-- Custom styles lib -->
	<script type="text/javascript" src="<c:url value="/js/list.js"/>"></script>
	
	<!-- Plugins -->
	<script  type="text/javascript" src="<c:url value="/js/jquery.dataTables.min.js"/>"></script>
	
	<!-- Charts library -->
	<!--Load the AJAX API-->
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
	<script type="text/javascript">
	
		// Load the Visualization API and the piechart package.
		google.load('visualization', '1', {'packages':['corechart']});
		
	</script>
	
	<script type="text/javascript">
		
		$(document).ready(function()
		{
			/*
			 * Example context menu
			 */
			
			// Context menu for all favorites
			$('.favorites li').bind('contextMenu', function(event, list)
			{
				var li = $(this);
				
				// Add links to the menu
				if (li.prev().length > 0)
				{
					list.push({ text: 'Move up', link:'#', icon:'up' });
				}
				if (li.next().length > 0)
				{
					list.push({ text: 'Move down', link:'#', icon:'down' });
				}
				list.push(false);	// Separator
				list.push({ text: 'Delete', link:'#', icon:'delete' });
				list.push({ text: 'Edit', link:'#', icon:'edit' });
			});
			
			// Extra options for the first one
			$('.favorites li:first').bind('contextMenu', function(event, list)
			{
				list.push(false);	// Separator
				list.push({ text: 'Settings', icon:'terminal', link:'#', subs:[
					{ text: 'General settings', link: '#', icon: 'blog' },
					{ text: 'System settings', link: '#', icon: 'server' },
					{ text: 'Website settings', link: '#', icon: 'network' }
				] });
			});
			
			/*
			 * Table sorting
			 */
			
			// A small classes setup...
			$.fn.dataTableExt.oStdClasses.sWrapper = 'no-margin last-child';
			$.fn.dataTableExt.oStdClasses.sInfo = 'message no-margin';
			$.fn.dataTableExt.oStdClasses.sLength = 'float-left';
			$.fn.dataTableExt.oStdClasses.sFilter = 'float-right';
			$.fn.dataTableExt.oStdClasses.sPaging = 'sub-hover paging_';
			$.fn.dataTableExt.oStdClasses.sPagePrevEnabled = 'control-prev';
			$.fn.dataTableExt.oStdClasses.sPagePrevDisabled = 'control-prev disabled';
			$.fn.dataTableExt.oStdClasses.sPageNextEnabled = 'control-next';
			$.fn.dataTableExt.oStdClasses.sPageNextDisabled = 'control-next disabled';
			$.fn.dataTableExt.oStdClasses.sPageFirst = 'control-first';
			$.fn.dataTableExt.oStdClasses.sPagePrevious = 'control-prev';
			$.fn.dataTableExt.oStdClasses.sPageNext = 'control-next';
			$.fn.dataTableExt.oStdClasses.sPageLast = 'control-last';
			
			// Apply to table
			$('.sortable').each(function(i)
			{
				// DataTable config
				var table = $(this),
					oTable = table.dataTable({
						/*
						 * We set specific options for each columns here. Some columns contain raw data to enable correct sorting, so we convert it for display
						 * @url http://www.datatables.net/usage/columns
						 */
						aoColumns: [
							{ bSortable: false },	// No sorting for this columns, as it only contains checkboxes
							{ sType: 'string' },
							{ bSortable: false },
							{ sType: 'numeric', bUseRendered: false, fnRender: function(obj) // Append unit and add icon
								{
									return '<small><img src="images/icons/fugue/image.png" width="16" height="16" class="picto"> '+obj.aData[obj.iDataColumn]+' Ko</small>';
								}
							},
							{ sType: 'date' },
							{ sType: 'numeric', bUseRendered: false, fnRender: function(obj) // Size is given as float for sorting, convert to format 000 x 000
								{
									return obj.aData[obj.iDataColumn].split('.').join(' x ');
								}
							},
							{ bSortable: false }	// No sorting for actions column
						],
						
						/*
						 * Set DOM structure for table controls
						 * @url http://www.datatables.net/examples/basic_init/dom.html
						 */
						sDom: '<"block-controls"<"controls-buttons"p>>rti<"block-footer clearfix"lf>',
						
						/*
						 * Callback to apply template setup
						 */
						fnDrawCallback: function()
						{
							this.parent().applyTemplateSetup();
						},
						fnInitComplete: function()
						{
							this.parent().applyTemplateSetup();
						}
					});
				
				// Sorting arrows behaviour
				table.find('thead .sort-up').click(function(event)
				{
					// Stop link behaviour
					event.preventDefault();
					
					// Find column index
					var column = $(this).closest('th'),
						columnIndex = column.parent().children().index(column.get(0));
					
					// Send command
					oTable.fnSort([[columnIndex, 'asc']]);
					
					// Prevent bubbling
					return false;
				});
				table.find('thead .sort-down').click(function(event)
				{
					// Stop link behaviour
					event.preventDefault();
					
					// Find column index
					var column = $(this).closest('th'),
						columnIndex = column.parent().children().index(column.get(0));
					
					// Send command
					oTable.fnSort([[columnIndex, 'desc']]);
					
					// Prevent bubbling
					return false;
				});
			});


		});
	</script>
	
</head>

<body>
<!-- The template uses conditional comments to add wrappers div for ie8 and ie7 - just add .ie or .ie7 prefix to your css selectors when needed -->
<!--[if lt IE 9]><div class="ie"><![endif]-->
<!--[if lt IE 8]><div class="ie7"><![endif]-->

	<!-- Main nav -->
	<nav id="main-nav">
		
		<ul class="container_12">
		
	        <li class="search"><a href="search.html" title="Wyszukaj połączenie">Wyszukj połączenie</a>
            </li>
            <li class="anulujRezerwacje"><a href="removereservation" title="Anuluj rezerwacje">Anuluj rezerwacje</a>
            </li>
			
			<c:if test="${sessionScope.user.rola eq 'administrator' }">
			<!-- ELEMENT MENU - ZARZADZANIE PRZEWOZNIKAMI -->
			<c:choose>
	        <c:when test="${pageContext.request.servletPath == '/showAirlineCompanies.jsp' or pageContext.request.servletPath == '/addAirlineCompany.jsp' or pageContext.request.servletPath == '/editAirlineCompany.jsp'}">
	        	<li class="przewoznicy current"><a href="airlinecompanies" title="Przewoźnicy">Przewoźnicy</a>
	        </c:when>
	        <c:otherwise>
	        	<li class="przewoznicy"><a href="airlinecompanies" title="Przewoźnicy">Przewoźnicy</a>
	        </c:otherwise>
	        </c:choose>
                <ul>
                	<c:choose>
	        		<c:when test="${pageContext.request.servletPath == '/showAirlineCompanies.jsp'}">
	        			<li class="current"><a href="airlinecompanies" title="Lista przewoźników">Lista przewoźników</a></li>
	        		</c:when>
	        		<c:otherwise>
	        			<li><a href="airlinecompanies" title="Lista przewoźników">Lista przewoźników</a></li>
	        		</c:otherwise>
	        		</c:choose>
                    <c:choose>
	        		<c:when test="${pageContext.request.servletPath == '/addAirlineCompany.jsp'}">
	        			<li class="current"><a href="airlinecompanies?action=addCompany" title="Dodaj przewoźnika">Dodaj przewoźnika</a></li>
	        		</c:when>
	        		<c:otherwise>
	        			<li><a href="airlinecompanies?action=addCompany" title="Dodaj przewoźnika">Dodaj przewoźnika</a></li>
	        		</c:otherwise>
	        		</c:choose>
                </ul>
            	</li>
            
            <!-- ELEMENT MENU - ZARZADZANIE LOTNISKAMI -->
			<c:choose>
	        <c:when test="${pageContext.request.servletPath == '/showAirports.jsp' or pageContext.request.servletPath == '/addAirport.jsp'}">
	        	<li class="airport current"><a href="airports" title="Lotniska">Lotniska</a>
	        </c:when>
	        <c:otherwise>
	        	<li class="airport"><a href="airports" title="Lotniska">Lotniska</a>
	        </c:otherwise>
	        </c:choose>
                <ul>
                	<c:choose>
	        		<c:when test="${pageContext.request.servletPath == '/showAirports.jsp'}">
	        			<li class="current"><a href="airports" title="Lista lotnisk">Lista lotnisk</a></li>
	        		</c:when>
	        		<c:otherwise>
	        			<li><a href="airports" title="Lista lotnisk">Lista lotnisk</a></li>
	        		</c:otherwise>
	        		</c:choose>
                    <c:choose>
	        		<c:when test="${pageContext.request.servletPath == '/addAirport.jsp'}">
	        			<li class="current"><a href="<c:url value="addAirport.jsp"/>" title="Dodaj lotnisko">Dodaj lotnisko</a></li>
	        		</c:when>
	        		<c:otherwise>
	        			<li><a href="<c:url value="addAirport.jsp"/>" title="Dodaj lotnisko">Dodaj lotnisko</a></li>
	        		</c:otherwise>
	        		</c:choose>
                </ul>
          		</li>
            <!-- KONIEC ELEMENT MENU - ZARZADZANIE LOTNISKAMI -->
            </c:if>
            <c:if test="${sessionScope.user.rola eq 'przewoznik' }">
            <!-- ELEMENT MENU - ZARZADZANIE SAMOLOTAMI -->
			<c:choose>
	        <c:when test="${pageContext.request.servletPath == '/showAirplanes.jsp' or pageContext.request.servletPath == '/addAirplane.jsp'}">
	        	<li class="airplane current"><a href="airplanes" title="Samoloty">Samoloty</a>
	        </c:when>
	        <c:otherwise>
	        	<li class="airplane"><a href="airplanes" title="Samoloty">Samoloty</a>
	        </c:otherwise>
	        </c:choose>
                <ul>
                	<c:choose>
	        		<c:when test="${pageContext.request.servletPath == '/showAirplanes.jsp'}">
	        			<li class="airplane current"><a href="airplanes" title="Lista samolotów">Lista samolotów</a></li>
	        		</c:when>
	        		<c:otherwise>
	        			<li><a href="airplanes" title="Lista samolotów">Lista samolotów</a></li>
	        		</c:otherwise>
	        		</c:choose>
                    <c:choose>
	        		<c:when test="${pageContext.request.servletPath == '/addAirplane.jsp'}">
	        			<li class="current"><a href="<c:url value="addAirplane.jsp"/>" title="Dodaj samolot">Dodaj samolot</a></li>
	        		</c:when>
	        		<c:otherwise>
	        			<li><a href="<c:url value="addAirplane.jsp"/>" title="Dodaj samolot">Dodaj samolot</a></li>
	        		</c:otherwise>
	        		</c:choose>
                </ul>
            	</li>
            <!-- KONIEC ELEMENT MENU - ZARZADZANIE SAMOLOTAMI --> 
            
            <!-- ELEMENT MENU - ZARZADZANIE LSP -->
			
	        	<li class="lsp"><a href="lsp" title="Pracownicy, loty">Pracownicy, loty</a>
            	</li>
            <!-- KONIEC ELEMENT MENU - ZARZADZANIE LSP -->
            </c:if>
            <c:if test="${sessionScope.user.rola eq 'przewoznik' or user.rola eq 'administrator' }">
            <c:choose>
            <c:when test="${pageContext.request.servletPath == '/showFlights.jsp' or pageContext.request.servletPath == '/addFlights.jsp' or pageContext.request.servletPath == '/editFlights.jsp'}">
            <li class="flights current"><a href="FlightController" title="Połączenia">Połączenia</a>
                </c:when>
                <c:otherwise>
            <li class="flights"><a href="FlightController" title="Połączenia">Połączenia</a>
                </c:otherwise>
                </c:choose>
                <ul>
                    <c:choose>
                        <c:when test="${pageContext.request.servletPath == '/showFlights.jsp'}">
                            <li class="current"><a href="FlightController" title="Lista połączeń">Lista połączeń</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="FlightController" title="Lista połączeń">Lista połączeń</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${pageContext.request.servletPath == '/addFlights.jsp'}">
                            <li class="current"><a href="FlightController?action=editorsave" title="Dodaj połączenie">Dodaj połączenie</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="FlightController?action=editorsave" title="Dodaj połączenie">Dodaj połączenie</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>

            </li>
            </c:if>
		</ul>

	</nav>
	<!-- End main nav -->
	
	<!-- Sub nav -->
	<div id="sub-nav"><div class="container_12">
	
	</div></div>
	<!-- End sub nav -->
	
	<!-- Status bar -->
	<div id="status-bar"><div class="container_12">
		<ul id="status-infos">
			<c:choose>
				<c:when test="${not empty sessionScope.user}">
					<li class="spaced">Logged as: <strong>${sessionScope.user.login}</strong></li>
					<li><a href="logout" class="button red" title="Wyloguj"><span class="smaller">WYLOGUJ</span></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="login" class="button red" title="Zaloguj"><span class="smaller">ZALOGUJ</span></a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	
	</div></div>
	<!-- End status bar -->
	
	<div id="header-shadow"></div>
	<!-- End header -->
	
	<!-- Content -->
	<article class="container_12">
