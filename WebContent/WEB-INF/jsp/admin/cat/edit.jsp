<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-pencil-square-o"></i> Chỉnh sửa danh mục</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-folder-open-o"></i>Danh mục</li>
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
                  <form class="form-validate form-horizontal" id="" method="post" action="${pageContext.request.contextPath }/admincp/cat/edit/${cat.id_cat}">
                    <div class="form-group ">
                      <label for="name" class="control-label col-lg-2">Nhập tên danh mục <span class="required">*</span></label>
                      <div class="col-lg-6">
                        <input class="form-control" value="${cat.name }" id="name" name="name" minlength="5" maxlength="35" type="text" required />
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