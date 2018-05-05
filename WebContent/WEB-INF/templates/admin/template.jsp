<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>

 <!--header start-->   
<tiles:insertAttribute name="header"></tiles:insertAttribute>
 <!--header end-->

    <!--sidebar start-->
    <aside>
      <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <tiles:insertAttribute name="left-bar"></tiles:insertAttribute>
        <!-- sidebar menu end-->
      </div>
    </aside>
    <!--sidebar end-->

    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <!--overview start-->
        <tiles:insertAttribute name="body"></tiles:insertAttribute>
      </section>
    <!-- Footer -->
   <tiles:insertAttribute name="footer"></tiles:insertAttribute>
    <!-- /.footer -->
  