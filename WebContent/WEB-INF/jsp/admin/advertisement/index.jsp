<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-money"></i> Quảng cáo</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-money"></i>Quảng cáo</li>
            </ol>
          </div>
        </div>
		
		<div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Quản lý quảng cáo
				<a class="btn btn-primary" href="${pageContext.request.contextPath }/admincp/advertisement/add" style="float:right"><i class="icon_plus_alt2"></i> Thêm</a>
              </header>
              <table class="table table-striped table-advance table-hover">
                <tbody>
                  <tr>
                    <th> Tên quảng cáo</th>
                    <th> Link</th>
                    <th> Hình ảnh</th>
                    <th class="text-center"> Action</th>
                  </tr>
                  <c:forEach var="objAd" items="${listAd }">
                  <c:set var="urlEdit" value="${pageContext.request.contextPath }/admincp/advertisement/edit/${objAd.id_ad}"></c:set>
                  <c:set var="urlDel" value="${pageContext.request.contextPath }/admincp/advertisement/del/${objAd.id_ad}"></c:set>
                  <tr>
                    <td>${objAd.name }</td>
                    <td>${objAd.link }</td>
                    <td><img width="100" src="${pageContext.request.contextPath }/files/${objAd.picture }" /></td>
                    <td class="text-center">
                      <div class="btn-group">
                        <a class="btn btn-success" href="${urlEdit }" title="Chỉnh sửa"><i class="fa fa-pencil-square-o"></i></a>
                        <a class="btn btn-danger" href="${urlDel }" title="Xóa" onclick="return confirm('Bạn có chắc muốn xóa?')"><i class="fa fa-trash-o"></i></a>
                      </div>
                    </td>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
            </section>
          </div>
        </div>