<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/taglib.jsp" %>
	<!-- start footer -->
	  <footer id="footer">
		<div class="container">
		  <div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
			  <div class="footer_top">
				<div class="row">
				  <div class="col-lg-4 col-md-4 col-sm-4">
					<div class="single_footer_top">
					  <h2>Welcome</h2>
					  <div>
						<p>Chào bạn, tôi là Thiện.</p>
						<p>Rất vui khi bạn ghé thăm website của tôi.</p>
					  </div>
					</div>
				  </div>
				  <div class="col-lg-4 col-md-4 col-sm-4">
					<div class="single_footer_top">
					  <h2>Chuyên mục </h2>
					  <ul>
					  	<c:forEach var="objCat" items="${listCat }">
					  	<c:set var="url" value="${pageContext.request.contextPath }/${slugUtil.makeSlug(objCat.name)}/${objCat.id_cat }"></c:set>
						<li>
							<a href="${url }">${objCat.name }</a>
						</li>
						</c:forEach>
						</ul>                
					</div>
				  </div>
				  <div class="col-lg-4 col-md-4 col-sm-4">
					<div class="single_footer_top">
					  <h2>Social Links </h2>
					  <ul class="social_nav">
					  <c:forEach var="objN" items="${listNetwork }">
						<li><a href="${objN.link }" target="_blank"><i class="${objN.icon }"></i></a></li>
						</c:forEach>
					</ul>                
					</div>
				  </div>
				</div>
			  </div>
			</div>      
			<div class="col-lg-12 col-md-12 col-sm-12" style="">
			  <div class="footer_bottom">
				<div class="copyright">
				  <p>Một dự án tại VinaEnter Edu</p>
				</div>
				<div class="developer">
				  <p>hoangthien1611@gmail.com</p>
				</div>
			  </div>
			</div>
		  </div>
		</div>
	  </footer>
	  <!-- End footer -->
	<!-- //main-section-ends-here -->

	
	<!-- Custom-JavaScript-Files -->

		
		<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/bootstrap.js"></script> <!-- Necessary-JavaScript-File-For-Bootstrap --> 
	
		<!-- for-projects -->
			<!-- swipe box js -->
			<script src="<%=request.getContextPath() %>/templates/public/js/jquery.swipebox.min.js"></script> 
			<script type="text/javascript">
					jQuery(function($) {
						$(".swipebox").swipebox();
					});
			</script>
			<!-- //swipe box js --> 
			
			<script src="<%=request.getContextPath() %>/templates/public/js/jquery.adipoli.min.js" type="text/javascript"></script>
			<script type="text/javascript"> 
				$(function(){ 
					$('.row2').adipoli({
						'startEffect' : 'overlay',
						'hoverEffect' : 'sliceDown'
					}); 
				});
				
			</script>
		<!-- //for-projects -->
		
		<!-- Horizontal-Tabs-JavaScript -->
			<script src="<%=request.getContextPath() %>/templates/public/js/easyResponsiveTabs.js" type="text/javascript"></script>
			<script type="text/javascript">
				$(document).ready(function () {
					$('#horizontalTab').easyResponsiveTabs({
						type: 'default',
						width: 'auto',
						fit: true,
					});
				});
			</script>
		<!-- Horizontal-Tabs-JavaScript -->

		<!-- Stats-Number-Scroller-Animation-JavaScript -->
			<script src="<%=request.getContextPath() %>/templates/public/js/waypoints.min.js"></script> 
			<script src="<%=request.getContextPath() %>/templates/public/js/counterup.min.js"></script> 
			<script>
				jQuery(document).ready(function( $ ) {
					$('.counter').counterUp({
						delay: 10,
						time: 1000,
					});
				});
			</script>
		<!-- //Stats-Number-Scroller-Animation-JavaScript -->

		<!-- Progressive-Bars-JavaScript -->
			<script src="<%=request.getContextPath() %>/templates/public/js/bars.js"></script>
		<!-- //Progressive-Bars-JavaScript -->

		<!-- for-experience -->
			<!-- Show-More-JavaScript -->
			<script>
				$(document).ready(function () {
					size_li = $("#myList li").size();
					x=1;
					$('#myList li:lt('+x+')').show();
					$('#loadMore').click(function () {
						x= (x+1 <= size_li) ? x+1 : size_li;
						$('#myList li:lt('+x+')').show();
					});
					$('#showLess').click(function () {
						x=(x-1<0) ? 1 : x-1;
						$('#myList li').not(':lt('+x+')').hide();
					});
				});
			</script>
			<!-- //Show-More-JavaScript -->
		<!-- //for-experience -->
	
		<!-- start-smoth-scrolling -->
			<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/move-top.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/easing.js"></script>
			<script type="text/javascript">
				jQuery(document).ready(function($) {
					$(".scroll").click(function(event){		
						event.preventDefault();
						$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
					});
				});
			</script>
		<!-- start-smoth-scrolling -->
		<!-- here stars scrolling icon -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
			*/
								
			$().UItoTop({ easingType: 'easeOutQuart' });
								
			});
	</script>
<!-- //here ends scrolling icon -->

	
<!-- //Custom-JavaScript-Files -->

</body>
</html>
