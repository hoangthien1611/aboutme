<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>

<title>${title }</title>

<!-- meta-tags -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Personal Resume a Responsive Web Template, Bootstrap Web Templates, Flat Web Templates, Android Compatible Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //meta-tags -->

<!-- Custom-Files -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/bootstrap.css"> <!-- Bootstrap-Core-CSS -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/style.css" type="text/css" media="all" /> <!-- Style-CSS --> 
<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/cland.css" type="text/css" media="all" /> <!-- Cland-CSS -->
<link href="<%=request.getContextPath() %>/templates/public/css/font-awesome.css" rel="stylesheet"> <!-- Font-Awesome-Icons-CSS -->	
<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/swipebox.css"> <!-- For-Gallery-Swipebox-CSS -->
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery-2.1.4.min.js"></script> <!-- Default-JavaScript-File -->
<!-- //Custom-Files -->

<!-- Web-Fonts -->
<link href="//fonts.googleapis.com/css?family=Nunito:300,400,700" rel="stylesheet">
<!-- //Web-Fonts -->
<style type="text/css">
.td_module_mx4 .entry-thumb {width: 218px;
    height: 134.86px;}
</style>
</head>
<body>

	<!-- Header -->
	  <!-- start navbar -->
	 <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
		  <div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
			  <span class="sr-only">Toggle navigation</span>
			  <span class="icon-bar"></span>
			  <span class="icon-bar"></span>
			  <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath }/">About <span>Me</span></a>
			<!-- <a class="navbar-brand" href="index.html"><img src="img/logo.png" alt="logo"></a> -->
		  </div>
		  <div id="navbar" class="navbar-collapse collapse navbar_area">          
			<ul class="nav navbar-nav navbar-right custom_nav">
			  <li><a href="${pageContext.request.contextPath }/">Trang chủ</a></li>
			  <li class="dropdown">
				<a href="javascript:void(0)">Danh mục tin <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<c:forEach var="objCat" items="${listCat }">
					<c:set var="url" value="${pageContext.request.contextPath }/${slugUtil.makeSlug(objCat.name)}/${objCat.id_cat }"></c:set>
					<li><a href="${url }">${objCat.name }</a></li>
					</c:forEach>
				</ul>
			  </li>
			  <li><a href="${pageContext.request.contextPath }/lien-he">Liên hệ</a></li>
			  <li class="nav-search-box">
			  	<form action="${pageContext.request.contextPath }/search" method="get">
				    <input type="search" name="searchText" value="${sText }" placeholder="Search">
				</form>
			  </li>
			</ul>
		  </div><!--/.nav-collapse -->
		</div>
	  </nav>
	  <!-- End navbar -->   
<!-- //Header -->