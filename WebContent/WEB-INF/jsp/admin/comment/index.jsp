<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-comments-o"></i> Bình luận</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-comments-o"></i>Bình luận</li>
            </ol>
          </div>
        </div>
		
		<div class="row">
          <div class="col-lg-12">
            <section class="panel">
            <form action="${pageContext.request.contextPath }/admincp/comment/delall" method="post">
              <header class="panel-heading">
                Quản lý bình luận
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
                    <th>Id</th>
                    <th>Họ tên</th>
                    <th>Email</th>
                    <th>Nội dung</th>
                    <th>Tên bài viết</th>
                    <th>Ngày tạo</th>
                    <th class="text-center">Status</th>
                    <th>Id_parent</th>
                    <th class="text-center">Action</th>
                  </tr>
                  <c:forEach var="objC" items="${listComment }">
                  <c:set var="urlDel" value="${pageContext.request.contextPath }/admincp/comment/del/${objC.id_comment }"></c:set>
                  <tr>
                  	<td><input type="checkbox" class="checkbox" name="id_comment" value="${objC.id_comment }" /></td>
                    <td>${objC.id_comment }</td>
                    <td>${objC.fullname }</td>
                    <td>${objC.email }</td>
                    <td>${objC.content }</td>
                    <td>${objC.name }</td>
                    <td>${objC.date_create }</td>
                    <td class="text-center change-${objC.id_comment }">
                    	<c:choose>
                       		<c:when test="${objC.status == 0 }">
                       			<a href="javascript:void(0)" onclick="changeActive(${objC.id_comment}, 1)">
								<img src="${pageContext.request.contextPath }/templates/admin/img/disactive.png" width="20px"></a>
                       		</c:when>
                       		<c:otherwise>
                       			<a href="javascript:void(0)" onclick="changeActive(${objC.id_comment}, 0)">
								<img src="${pageContext.request.contextPath }/templates/admin/img/active.png" width="20px"></a>
                       		</c:otherwise>
                       	</c:choose>
                    </td>
                    <td class="text-center">${objC.id_parent }</td>
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
                         <li><a href="${pageContext.request.contextPath }/admincp/comment/${currentPage - 1}">«</a></li>
                         </c:if>
                     	<c:forEach begin="${pageStart }" end="${pageEnd }" var="page">
                         	<li <c:if test="${page == currentPage }">class="active"</c:if>><a href="${pageContext.request.contextPath }/admincp/comment/${page}">${page }</a></li>
                         </c:forEach>
                         <c:if test="${currentPage < sumPage }">
                         <li><a href="${pageContext.request.contextPath }/admincp/comment/${currentPage + 1}">»</a></li>
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
					
					function changeActive(id, gt){
						
					    $.ajax({
					      url: "${pageContext.request.contextPath}/admincp/comment",
					      type: 'POST',
					      cache: false,
					      data: {
					        agt : gt,
					        aid: id
					      },
					
					      success: function(data){
					        $(".change-"+id).html(data); 
					      },
					      error: function (){
					        alert('Có lỗi xảy ra trong quá trình xử lý!');
					      }
					    }); 
					  }
				
				</script>