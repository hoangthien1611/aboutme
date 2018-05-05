<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
        <div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-pencil-square-o"></i> Chỉnh sửa bài viết</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-file-text"></i>Bài viết</li>
              <li><i class="fa fa-pencil-square-o"></i>Chỉnh sửa</li>
            </ol>
          </div>
        </div>
        <!-- Form validations -->
        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                Chỉnh sửa bài viết
              </header>
              <div class="panel-body">
                <div class="form">
                  <form class="form-validate form-horizontal" id="feedback_form" method="post" action="${pageContext.request.contextPath }/admincp/news/edit/${objN.id_news}" enctype="multipart/form-data">
                    <div class="form-group ">
                      <label for="name" class="control-label col-lg-2">Tên bài viết <span class="required">*</span></label>
                      <div class="col-lg-6">
                        <input class="form-control" id="name" name="name" value="${objN.name }" type="text" required />
                      </div>
                    </div>
                    <div class="form-group">
                    <label class="control-label col-lg-2" for="inputSuccess">Danh mục</label>
                      <div class="col-lg-4">
                         <select class="form-control m-bot15" name="id_cat">
							  <c:forEach var="objCat" items="${listCat }">
							  <option <c:if test="${objCat.id_cat == objN.id_cat }">selected='selected'</c:if> value="${objCat.id_cat}">${objCat.name }</option>
							  </c:forEach>
						  </select>
					  </div>
				    </div>

					<div class="form-group">
						<label for="cname" class="control-label col-lg-2">Hình ảnh </label>
						<div class="col-lg-3">
							<input type="file" name="hinhanh"> <br />
							<img width="200" src="${pageContext.request.contextPath }/files/${objN.picture}" />
						</div>
						<label for="agree" class="control-label col-lg-2 col-sm-3">Xóa ảnh </label>
						<div class="col-lg-4 col-sm-9">
                        <input type="checkbox" style="width: 20px" class="checkbox form-control" id="agree" name="delPic" />
                        </div>
					</div>
                    <div class="form-group ">
                      <label for="preview_text" class="control-label col-lg-2">Mô tả <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <textarea class="form-control " id="preview_text" name="preview_text" required>${objN.preview_text }</textarea>
                      </div>
                    </div>
                    <div class="form-group ">
                      <label for="detail_text" class="control-label col-lg-2">Chi tiết <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <textarea class="form-control " rows="5" name="detail_text" id="editor" required>${objN.detail_text }</textarea>
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
        <script>
			var editor = CKEDITOR.replace('editor');
			CKFinder.setupCKEditor(editor, '<%=request.getContextPath()%>/lib/ckfinder/');
		</script>
        <!-- page end-->