<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-plus-square-o"></i> Thêm quảng cáo</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-money"></i>Quảng cáo</li>
              <li><i class="fa fa-plus-square-o"></i>Thêm</li>
            </ol>
          </div>
        </div>
        <!-- Form validations -->
        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Thêm danh mục
              </header>
              <div class="panel-body">
                <div class="form">
                  <form class="form-validate form-horizontal" id="feedback_form" method="post" action="${pageContext.request.contextPath }/admincp/advertisement/add" enctype="multipart/form-data">
                    <div class="form-group ">
                      <label for="name" class="control-label col-lg-2">Tên quảng cáo <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <input class="form-control" id="name" name="name" type="text" required />
                      </div>
                    </div>
                    <div class="form-group ">
                      <label for="link" class="control-label col-lg-2">Link <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <input class="form-control " id="link" type="text" name="link" required />
                      </div>
                    </div>
                   
					<div class="form-group">
						<label for="cname" class="control-label col-lg-2">Hình ảnh </label>
						<div class="col-lg-3">
							<input type="file" name="hinhanh">
						</div>
					</div>
					
                    <div class="form-group">
                      <div class="col-lg-offset-2 col-lg-10">
                        <button class="btn btn-primary" type="submit">Thêm</button>
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