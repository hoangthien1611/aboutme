<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-pencil-square-o"></i> Chỉnh sửa người dùng</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-user"></i>Người dùng</li>
              <li><i class="fa fa-pencil-square-o"></i>Chỉnh sửa</li>
            </ol>
          </div>
        </div>
        <!-- Form validations -->
        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Chỉnh sửa người dùng
              </header>
              <c:if test="${msg != null }">
              <div class="alert alert-block alert-danger fade in">
                  <button data-dismiss="alert" class="close close-sm" type="button">
                                      <i class="icon-remove"></i>
                                  </button>
                  <strong>${msg}</strong>
                </div>
                </c:if>
              <div class="panel-body">
                <div class="form">
                  <form class="form-validate form-horizontal" id="feedback_form" method="post" action="${pageContext.request.contextPath }/admincp/user/edit/${objUser.id_user}">
                    <div class="form-group ">
                      <label for="username" class="control-label col-lg-2">Username <span class="required">*</span></label>
                      <div class="col-lg-6">
                        <input class="form-control" id="username" name="username" value="${objUser.username }" type="text" readonly />
                      </div>
                    </div>
                    <div class="form-group ">
                      <label for="fullname" class="control-label col-lg-2">Fullname <span class="required">*</span></label>
                      <div class="col-lg-6">
                        <input class="form-control" id="fullname" name="fullname" value="${objUser.fullname }" type="text" required />
                      </div>
                    </div>
                    <div class="form-group ">
                      <label for="password" class="control-label col-lg-2">Password </label>
                      <div class="col-lg-6">
                        <input class="form-control" id="password" name="password" type="password" />
                      </div>
                    </div>
                    <div class="form-group ">
                      <label for="email" class="control-label col-lg-2">E-Mail <span class="required">*</span></label>
                      <div class="col-lg-6">
                        <input class="form-control " id="email" type="email" value="${objUser.email }" name="email" required />
                      </div>
                    </div>
					<div class="form-group">
                    <label class="control-label col-lg-2" for="inputSuccess">Role</label>
                      <div class="col-lg-4">
                         <select class="form-control m-bot15" name="id_role">
							  <c:choose>
                               <c:when test="${userinfo.id_role eq 1 }">
							   <c:forEach var="objRole" items="${listRole }">
							   <option <c:if test="${objUser.id_role == objRole.id_role }">selected='selected'</c:if> value="${objRole.id_role }">${objRole.name }</option>
							   </c:forEach>
							   </c:when>
							   <c:otherwise>
							   <option  value="${objUser.id_role }">${objUser.name }</option>
							   </c:otherwise>
							   </c:choose>
							  
						  </select>
					  </div>
				    </div>

                    <div class="form-group">
                      <div class="col-lg-offset-2 col-lg-10">
                        <button class="btn btn-primary" type="submit">Sửa</button>
                        <button class="btn btn-default" type="reset">Nhập lại</button>
                      </div>
                    </div>
                  </form>
                </div>

              </div>
            </section>
          </div>
        </div>
        
        <!-- page end-->