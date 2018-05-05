<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-user"></i> Người dùng</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-user"></i>Người dùng</li>
            </ol>
          </div>
        </div>
		
		<div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Quản lý người dùng
				<a class="btn btn-primary" href="${pageContext.request.contextPath }/admincp/user/add" style="float:right"><i class="icon_plus_alt2"></i> Thêm</a>
              </header>
              <c:if test="${msg.state == 1 }">
              	<div class="alert alert-success fade in">
                  <button data-dismiss="alert" class="close close-sm" type="button">
                                      <i class="icon-remove"></i>
                                  </button>
                  <strong>${msg.msg }</strong>
                </div>
              </c:if>
              <c:if test="${msg.state == 0 }">
              	<div class="alert alert-block alert-danger fade in">
                  <button data-dismiss="alert" class="close close-sm" type="button">
                                      <i class="icon-remove"></i>
                                  </button>
                  <strong>${msg.msg }</strong>
                </div>
              </c:if>
              <table class="table table-striped table-advance table-hover">
                <tbody>
                  <tr>
                    <th> Username</th>
                    <th> Fullname</th>
                    <th> Email</th>
                    <th> Role</th>
                    <th class="text-center"> Action</th>
                  </tr>
                  <c:forEach var="objU" items="${listUser}">
                   <c:set var="urlEdit" value="${pageContext.request.contextPath }/admincp/user/edit/${objU.id_user}"></c:set>
                   <c:set var="urlDel" value="${pageContext.request.contextPath }/admincp/user/del/${objU.id_user}"></c:set>
                  <tr>
                    <td>${objU.username }</td>
                    <td>${objU.fullname }</td>
                    <td>${objU.email }</td>
                    <td>${objU.name }</td>
                    <td class="text-center">
                      <div class="btn-group">
                      <c:if test="${(objU.id_user eq userinfo.id_user) or (userinfo.id_role eq 1)}">
                        <a class="btn btn-success" href="${urlEdit }" title="Chỉnh sửa"><i class="fa fa-pencil-square-o"></i></a>
                      </c:if>
                      <c:if test="${(objU.name ne 'ADMIN') and (userinfo.id_role eq 1) }">
                        <a class="btn btn-danger" href="${urlDel }" title="Xóa" onclick="return confirm('Bạn có chắc muốn xóa không?')"><i class="fa fa-trash-o"></i></a>
                      </c:if>
                      </div>
                    </td>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
            </section>
          </div>
        </div>