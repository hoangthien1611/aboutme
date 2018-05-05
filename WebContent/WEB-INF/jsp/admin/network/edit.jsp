<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-pencil-square-o"></i> Chỉnh sửa mạng xã hội</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-globe"></i>Mạng xã hội</li>
              <li><i class="fa fa-pencil-square-o"></i>Chỉnh sửa</li>
            </ol>
          </div>
        </div>
        <!-- Form validations -->
        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Chỉnh sửa mạng xã hội
              </header>
              <div class="panel-body">
                <div class="form">
                  <form class="form-validate form-horizontal" id="" method="post" action="${pageContext.request.contextPath }/admincp/network/edit/${objN.id_net}">
                    <div class="form-group ">
                      <label for="name" class="control-label col-lg-2">Tên  <span class="required">*</span></label>
                      <div class="col-lg-6">
                        <input class="form-control" id="name" name="name" value="${objN.name }" type="text" required />
                      </div>
                    </div>
                    <div class="form-group ">
                      <label for="icon" class="control-label col-lg-2">Class icon </label>
                      <div class="col-lg-6">
                        <input class="form-control" name="icon" value="${objN.icon}" type="text" />
                      </div>
                    </div>
                    <div class="form-group ">
                      <label for="link" class="control-label col-lg-2">Link <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <input class="form-control" name="link" value="${objN.link}" type="text" required />
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