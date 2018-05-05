<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
		<div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-bullseye"></i> Mạng xã hội</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-globe"></i>Mạng xã hội</li>
            </ol>
          </div>
        </div>
		
		<div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Quản lý mạng xã hội
                <a class="btn btn-primary" href="${pageContext.request.contextPath }/admincp/network/add" style="float:right"><i class="icon_plus_alt2"></i> Thêm</a>
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
                    <th>Tên </th>
                    <th>Icon </th>
                    <th>Link </th>
                    <th class="text-center">Hành động</th>
                  </tr>
                  <c:forEach var="objN" items="${listNetwork }">
                  <c:set var="urlEdit" value="${pageContext.request.contextPath }/admincp/network/edit/${objN.id_net }"></c:set>
                  <c:set var="urlDel" value="${pageContext.request.contextPath }/admincp/network/del/${objN.id_net }"></c:set>
                  <tr>
                    <td>${objN.name }</td>
                    <td><i class="${objN.icon }"></i></td>
                    <td>${objN.link }</td>
                    <td class="text-center">
                      <div class="btn-group">
                        <a class="btn btn-success" href="${urlEdit }" title="Chỉnh sửa"><i class="fa fa-pencil-square-o"></i></a>
                      	<a class="btn btn-danger" href="${urlDel }" title="Xóa" onclick="return confirm('Bạn có chắc muốn xóa không?')"><i class="fa fa-trash-o"></i></a>
                      </div>
                    </td>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
            </section>
          </div>
        </div>