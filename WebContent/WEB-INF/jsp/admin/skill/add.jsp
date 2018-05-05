<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-plus-square-o"></i> Thêm kĩ năng</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-flash"></i>Kĩ năng</li>
              <li><i class="fa fa-plus-square-o"></i>Thêm</li>
            </ol>
          </div>
        </div>
        <!-- Form validations -->
        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Thêm kĩ năng
              </header>
              <div class="panel-body">
                <div class="form">
                  <form class="form-validate form-horizontal" id="" method="post" action="${pageContext.request.contextPath }/admincp/skill/add">
                    <div class="form-group ">
                      <label for="name" class="control-label col-lg-2">Tên kĩ năng <span class="required">*</span></label>
                      <div class="col-lg-6">
                        <input class="form-control" id="name" name="name" type="text" required />
                      </div>
                    </div>
                    <div class="form-group ">
                      <label for="percent" class="control-label col-lg-2">Giá trị phần trăm <span class="required">*</span></label>
                      <div class="col-lg-6">
                        <input class="form-control" name="percent" type="text" required />
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