<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-envelope-o"></i> Liên lạc</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-envelope-o"></i>Liên lạc</li>
            </ol>
          </div>
        </div>
		
		<div class="row">
          <div class="col-lg-12">
            <section class="panel">
            <form action="${pageContext.request.contextPath }/admincp/contact/delall" method="post">
              <header class="panel-heading">
                Quản lý liên lạc
				<button type="submit" class="btn btn-danger" style="float:right; display:none" id="deleteAll" onclick="return confirm('Bạn có chắc muốn xóa không?')"><i class="fa fa-trash-o"></i> Xóa</button>
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
                  	<th><input type="checkbox" id="checkAll"/></th>
                    <th>Họ tên</th>
                    <th>Email</th>
                    <th>Số điện thoại</th>
                    <th>Nội dung</th>
                    <th>Ngày tạo</th>
                    <th class="text-center">Action</th>
                  </tr>
                  <c:forEach var="objCont" items="${listContact }">
                  <c:set var="urlDel" value="${pageContext.request.contextPath }/admincp/contact/del/${objCont.id_contact }"></c:set>
                  <tr>
                  	<td><input type="checkbox" class="checkbox" name="id_contact" value="${objCont.id_contact }" /></td>
                    <td>${objCont.fullname }</td>
                    <td>${objCont.email }</td>
                    <td>${objCont.phone }</td>
                    <td>${objCont.content }</td>
                    <td>${objCont.date_create }</td>
                    <td class="text-center">
                      <div class="btn-group">
                        <a class="btn btn-danger" href="${urlDel }" title="Xóa" onclick="return confirm('Bạn có chắc muốn xóa không?')"><i class="fa fa-trash-o"></i></a>
                      </div>
                    </td>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
              <div class="text-center">
                  <ul class="pagination">
                    
                    <c:if test="${currentPage > 1}">
                         <li><a href="${pageContext.request.contextPath }/admincp/contact/${currentPage - 1}">«</a></li>
                         </c:if>
                     	<c:forEach begin="${pageStart }" end="${pageEnd }" var="page">
                         	<li <c:if test="${page == currentPage }">class="active"</c:if>><a href="${pageContext.request.contextPath }/admincp/contact/${page}">${page }</a></li>
                         </c:forEach>
                         <c:if test="${currentPage < sumPage }">
                         <li><a href="${pageContext.request.contextPath }/admincp/contact/${currentPage + 1}">»</a></li>
                     </c:if>
                  </ul>
                </div>
                </form>
            </section>
          </div>
        </div>
        
        <script type="text/javascript">
					$(document).ready(function() {
						$("#checkAll").change(function(){   
							$("input:checkbox").prop('checked', $(this).prop("checked"));
							if($(this).prop("checked")) {
							    $('#deleteAll').show();
							  } else {
							    $('#deleteAll').hide();
							  }
						});
					});
			
					$(document).ready(function() {
						$('.checkbox').change(function(){ 
							if(false == $(this).prop("checked")){ 
								$("#checkAll").prop('checked', false); 
							}
							if ($('.checkbox:checked').length == $('.checkbox').length ){
								$("#checkAll").prop('checked', true);
							}
						});
					});
					
					$(document).ready(function() {
						$('.checkbox').change(function(){
							if ( $('.checkbox:checked').length > 0) {
					            $("#deleteAll").show();
					        } else {
					            $("#deleteAll").hide();
					        }
							});
					});
				
				</script>