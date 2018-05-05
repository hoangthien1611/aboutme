<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<ul class="sidebar-menu">
          <li>
            <a class="" href="${pageContext.request.contextPath }/admincp/index">
                          <i class="icon_house_alt"></i>
                          <span>Dashboard</span>
                      </a>
          </li>
		  <li class="">
            <a class="" href="${pageContext.request.contextPath }/admincp/user">
                          <i class="fa fa-user"></i>
                          <span>Người dùng</span>
                      </a>
          </li>
		  <li class="">
            <a class="" href="${pageContext.request.contextPath }/admincp/contact">
                          <i class="fa fa-envelope-o"></i>
                          <span>Liên lạc</span>
                      </a>
          </li>
		  <li class="sub-menu">
            <a href="javascript:;" class="">
                          <i class="fa fa-file-text"></i>
                          <span>Bài viết</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
            <ul class="sub">
              <li><a class="" href="${pageContext.request.contextPath }/admincp/news">Bài viết</a></li>
              <li><a class="" href="${pageContext.request.contextPath }/admincp/cat">Danh mục</a></li>
			  <li><a class="" href="${pageContext.request.contextPath }/admincp/comment">Bình luận</a></li>
            </ul>
          </li>
		  <li class="sub-menu">
            <a href="javascript:;" class="">
                          <i class="fa fa-qrcode"></i>
                          <span>Thông tin</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
            <ul class="sub">
              <li><a class="" href="${pageContext.request.contextPath }/admincp/work">Việc làm</a></li>
              <li><a class="" href="${pageContext.request.contextPath }/admincp/project">Dự án</a></li>
              <li><a class="" href="${pageContext.request.contextPath }/admincp/skill">Kỹ năng</a></li>
              <li><a class="" href="${pageContext.request.contextPath }/admincp/interest">Quan tâm</a></li>
              <li><a class="" href="${pageContext.request.contextPath }/admincp/network">Mạng xã hội</a></li>
            </ul>
          </li>
		  <li class="sub-menu">
            <a href="javascript:;" class="">
                          <i class="fa fa-eye"></i>
                          <span>Hiển thị</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
            <ul class="sub">
              <li><a class="" href="${pageContext.request.contextPath }/admincp/quote">Danh ngôn</a></li>
              <li><a class="" href="${pageContext.request.contextPath }/admincp/advertisement">Quảng cáo</a></li>
            </ul>
          </li>

        </ul>
<script>

	$(function() {
		var path = window.location.pathname;
	//	path = path.replace(/\/$/,"");
	//	path = decodeURIComponent(path);
		
		$(".sidebar-menu a").each(function(){
			var href = $(this).attr('href');
			if (path.substring(0, href.length) === href) {
			     $(this).closest('li').addClass('active');
			}
		});
	});
</script>