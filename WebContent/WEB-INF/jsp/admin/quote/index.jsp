<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-quote-left"></i> Danh ngôn</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-quote-left"></i>Danh ngôn</li>
            </ol>
          </div>
        </div>
		
		<div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Quản lý danh ngôn
				<a class="btn btn-primary" href="${pageContext.request.contextPath }/admincp/quote/add" style="float:right"><i class="icon_plus_alt2"></i> Thêm</a>
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
                    <th> Tác giả</th>
                    <th> Nội dung</th>
                    <th> Hình ảnh</th>
                    <th class="text-center" width="15%"> Action</th>
                  </tr>
                  <c:forEach var="objQ" items="${listQuote }">
                  <c:set var="urlEdit" value="${pageContext.request.contextPath }/admincp/quote/edit/${objQ.id_quote}"></c:set>
                  <c:set var="urlDel" value="${pageContext.request.contextPath }/admincp/quote/del/${objQ.id_quote}"></c:set>
                  <tr>
                    <td>${objQ.author }</td>
                    <td>${objQ.content }</td>
                    <td><img width="100" src="${pageContext.request.contextPath }/files/${objQ.picture}" /></td>
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