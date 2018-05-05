<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-plus-square-o"></i> Thêm danh ngôn</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-quote-left"></i>Dự án</li>
              <li><i class="fa fa-plus-square-o"></i>Thêm</li>
            </ol>
          </div>
        </div>
        <!-- Form validations -->
        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Thêm danh ngôn
              </header>
              <div class="panel-body">
                <div class="form">
                  <form class="form-validate form-horizontal" id="feedback_form" method="post" action="${pageContext.request.contextPath }/admincp/quote/add" enctype="multipart/form-data">
                    <div class="form-group ">
                      <label for="author" class="control-label col-lg-2">Tác giả <span class="required">*</span></label>
                      <div class="col-lg-6">
                        <input class="form-control" id="author" name="author" type="text" required />
                      </div>
                    </div>

					<div class="form-group">
						<label for="cname" class="control-label col-lg-2">Hình ảnh </label>
						<div class="col-lg-3">
							<input type="file" name="hinhanh">
						</div>
					</div>
                    <div class="form-group ">
                      <label for="content" class="control-label col-lg-2">Nội dung <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <textarea class="form-control " id="content" name="content" required></textarea>
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