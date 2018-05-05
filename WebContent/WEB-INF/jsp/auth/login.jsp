<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
<%@include file="/templates/taglib.jsp" %>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
  <meta name="author" content="GeeksLabs">
  <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
  <link rel="shortcut icon" href="img/favicon.png">

  <title>Login</title>

  <!-- Bootstrap CSS -->
  <link href="<%=request.getContextPath() %>/templates/admin/css/bootstrap.min.css" rel="stylesheet">
  <!-- bootstrap theme -->
  <link href="<%=request.getContextPath() %>/templates/admin/css/bootstrap-theme.css" rel="stylesheet">
  <!--external css-->
  <!-- font icon -->
  <link href="<%=request.getContextPath() %>/templates/admin/css/elegant-icons-style.css" rel="stylesheet" />
  <link href="<%=request.getContextPath() %>/templates/admin/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles -->
  <link href="<%=request.getContextPath() %>/templates/admin/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath() %>/templates/admin/css/style-responsive.css" rel="stylesheet" />

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-img3-body">

  <div class="container">

    <form class="login-form" action="${pageContext.request.contextPath }/login" method="post">
      <div class="login-wrap">
        <p class="login-img"><i class="icon_lock_alt"></i></p>
        <c:if test="${param.error != null }">
       		<p style="color:red; font-weight:bold">Tên đăng nhập hoặc mật khẩu sai!</p>
        </c:if>
        <c:if test="${param.msg != null }">
       		<p style="color:green;font-weight:bold">Bạn đã đăng xuất thành công!</p>
        </c:if>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_profile"></i></span>
          <input type="text" name="username" class="form-control" placeholder="Username" autofocus>
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_key_alt"></i></span>
          <input type="password" name="password" class="form-control" placeholder="Password">
        </div>
        <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
                <span class="pull-right"> <a href="#"> Forgot Password?</a></span>
            </label>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
      </div>
    </form>
    <div class="text-right">
      <div class="credits">
          Thực hiện bởi <a href="#">Nguyễn Hoàng Thiện</a>
        </div>
    </div>
  </div>


</body>

</html>
