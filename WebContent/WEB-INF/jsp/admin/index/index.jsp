		<div class="row">
          <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-laptop"></i> Dashboard</h3>
            <ol class="breadcrumb">
              <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath }/admincp/index">Home</a></li>
              <li><i class="fa fa-laptop"></i>Dashboard</li>
            </ol>
          </div>
        </div>

        <div class="row">
          <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
            <div class="info-box blue-bg">
              <i class="fa fa-user"></i>
              <div class="count"><a href="${pageContext.request.contextPath }/admincp/user">${countUser }</a></div>
              <div class="title">Users</div>
            </div>
            <!--/.info-box-->
          </div>
          <!--/.col-->

          <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
            <div class="info-box brown-bg">
              <i class="fa fa-list"></i>
              <div class="count"><a href="${pageContext.request.contextPath }/admincp/cat">${countCat }</a></div>
              <div class="title">Danh mục</div>
            </div>
            <!--/.info-box-->
          </div>
          <!--/.col-->

          <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
            <div class="info-box dark-bg">
              <i class="fa fa-file-text"></i>
              <div class="count"><a href="${pageContext.request.contextPath }/admincp/news">${countNews }</a></div>
              <div class="title">Bài viết</div>
            </div>
            <!--/.info-box-->
          </div>
          <!--/.col-->

          <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
            <div class="info-box green-bg">
              <i class="fa fa-envelope-o"></i>
              <div class="count"><a href="${pageContext.request.contextPath }/admincp/contact">${countContact }</a></div>
              <div class="title">Hộp thư đến</div>
            </div>
            <!--/.info-box-->
          </div>
          <!--/.col-->

        </div>
        <!--/.row-->