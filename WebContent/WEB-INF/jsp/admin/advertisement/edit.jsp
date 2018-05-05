<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-pencil-square-o"></i> Chỉnh sửa quảng cáo</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-money"></i>Quảng cáo</li>
              <li><i class="fa fa-pencil-square-o"></i>Chỉnh sửa</li>
            </ol>
          </div>
        </div>
        <!-- Form validations -->
        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Chỉnh sửa danh mục
              </header>
              <div class="panel-body">
                <div class="form">
                  <form class="form-validate form-horizontal" id="feedback_form" method="post" action="${pageContext.request.contextPath }/admincp/advertisement/edit/${objAd.id_ad}" enctype="multipart/form-data">
                    <div class="form-group ">
                      <label for="name" class="control-label col-lg-2">Tên quảng cáo <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <input class="form-control" id="name" name="name" value="${objAd.name }" type="text" required />
                      </div>
                    </div>
                    <div class="form-group ">
                      <label for="link" class="control-label col-lg-2">Link <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <input class="form-control " id="link" type="text" name="link" value="${objAd.link }" required />
                      </div>
                    </div>
                   
					<div class="form-group">
						<label for="cname" class="control-label col-lg-2">Hình ảnh </label>
						<div class="col-lg-3">
							<input type="file" name="hinhanh"> <br />
							<img width="200" src="${pageContext.request.contextPath }/files/${objAd.picture}" />
						</div>
						<label for="agree" class="control-label col-lg-2 col-sm-3">Xóa ảnh </label>
						<div class="col-lg-4 col-sm-9">
                        <input type="checkbox" style="width: 20px" class="checkbox form-control" id="agree" name="delPic" />
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