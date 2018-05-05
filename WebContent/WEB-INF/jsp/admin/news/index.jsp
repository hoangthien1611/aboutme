<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-file-text"></i> Bài viết</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-file-text"></i>Bài viết</li>
            </ol>
          </div>
        </div>
		
		<div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Quản lý bài viết
				<a class="btn btn-primary" href="${pageContext.request.contextPath }/admincp/news/add" style="float:right"><i class="icon_plus_alt2"></i> Thêm</a>
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
                    <th> Tên bài viết</th>
                    <th> Hình ảnh</th>
                    <th> Danh mục</th>
                    <th> Người đăng</th>
                    <th> Ngày tạo</th>
                    <th> Số lượt xem</th>
                    <th class="text-center"> Hành động</th>
                  </tr>
                  <c:forEach var="objN" items="${listNews }">
                  <c:set var="urlEdit" value="${pageContext.request.contextPath }/admincp/news/edit/${objN.id_news}"></c:set>
                  <c:set var="urlDel" value="${pageContext.request.contextPath }/admincp/news/del/${objN.id_news}"></c:set>
                  <tr>
                    <td>${objN.name }</td>
                    <td class="text-center"><img width="100" src="${pageContext.request.contextPath }/files/${objN.picture}" /></td>
                    <td>${objN.nameCat }</td>
                    <td>${objN.username }</td>
                    <td>${objN.date_create }</td>
                    <td>${objN.count_views }</td>
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
              <div class="text-center">
                  <ul class="pagination">
                    
                    <c:if test="${currentPage > 1}">
                         <li><a href="${pageContext.request.contextPath }/admincp/news/${currentPage - 1}">«</a></li>
                         </c:if>
                     	<c:forEach begin="${pageStart }" end="${pageEnd }" var="page">
                         	<li <c:if test="${page == currentPage }">class="active"</c:if>><a href="${pageContext.request.contextPath }/admincp/news/${page}">${page }</a></li>
                         </c:forEach>
                         <c:if test="${currentPage < sumPage }">
                         <li><a href="${pageContext.request.contextPath }/admincp/news/${currentPage + 1}">»</a></li>
                     </c:if>
                  </ul>
                </div>
            </section>
          </div>
        </div>