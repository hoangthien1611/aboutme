<%@include file="/templates/taglib.jsp" %>
<!-- main-section-starts-here -->
	<!-- About-starts-here -->
	<div class="about" id="about">
		<div class="container">
			<h3 class="w3l_head">About Me</h3>
			<div class="w3l-grids-about">
				<div class="col-md-5 w3ls-ab-right">
					<div class="agile-about-right-img">
						<img src="${pageContext.request.contextPath}/templates/public/images/14660_661936803911162_6916331631373266218_n.jpg" alt="">
					</div>
				</div>
				<div class="col-md-7 w3ls-agile-left">
					<div class="wel-left animated wow fadeInLeft" data-wow-delay=".5s">
						<ul class="my-infor">
							<li>
							<span class="my-infor-sub">Họ và tên:</span> <span class="my-infor-content">Nguyễn Hoàng Thiện</span>
							</li>
							<li>
							<span class="my-infor-sub">Email:</span> <span class="my-infor-content">hoangthien1611@gmail.com</span>
							</li>
							<li>
							<span class="my-infor-sub">Số điện thoại:</span> <span class="my-infor-content">+841223512378</span>
							</li>
							<li>
							<span class="my-infor-sub">Địa chỉ:</span> <span class="my-infor-content">Liên Chiểu, Đà Nẵng</span>
							</li>
						</ul>
						<div class="clrfix"> </div>
					</div>
					
				</div>
				<div class="clrfix"> </div>
			</div>
		</div>
	</div>
	<!-- //About-ends-here -->
	
	
	<!--Skills-starts-here -->
	<div class="wthreeskills" id="skills">
		<div class="container-fluid">
			

			<div class="wthreeskills-grids">
				<div class="col-md-6 wthreeskills-grid wthreeskills-grid-2">
					<h3>MY TECHNICAL SKILLS</h3>

				</div>
				<div class="col-md-6 wthreeskills-grid wthreeskills-grid-1">

					<div class="bar_group">
						<c:forEach var="objS" items="${listSkill }">
						<div class='bar_group__bar thin' label='${objS.name }' show_values='true' tooltip='true' value='${objS.percent }'></div>
						</c:forEach>
					</div>
				</div>
				<div class="clrfix"></div>
			</div>

		</div>
	</div>
	<!-- //Skills-ends-here -->
	
	<!-- services-starts-here -->
	<div class="services" id="service"> 
		<div class="container">	 
			<h3 class="agileits-title">MY INTERESTS</h3>
			<div class="services-w3ls-row">
				<c:forEach var="objI" items="${listInterest }">
				<div class="col-md-3 services-grids">
					<div class="w3agile-servs-img">
						<div class="icon-holder">
							<c:if test="${objI.name eq 'Sports' }">
							<span class="glyphicon glyphicon-queen" aria-hidden="true"></span>
							</c:if>
							<c:if test="${objI.name eq 'Technology' }">
							<span class="fa fa-gears icon" aria-hidden="true"></span>
							</c:if>
							<c:if test="${objI.name eq 'Reading' }">
							<span class="glyphicon glyphicon-book" aria-hidden="true"></span>
							</c:if>
							<c:if test="${objI.name eq 'Music' }">
							<span class="glyphicon glyphicon-headphones" aria-hidden="true"></span>
							</c:if>
						</div>
						<h4 class="mission">${objI.name } </h4>
						<p class="description">${objI.description }</p>
					</div>
				</div>
				</c:forEach>
				<div class="clrfix"> </div>
			</div>	 			
		</div>	 			
	</div>			
	<!-- //services-ends-here -->
	
	<!-- Experience-starts-here -->
	<div id="experience" class="experience">
		<div class="container">
			<h3 class="agile-experience">Chặng Đường Tôi Đã Qua</h3>
			<c:forEach var="objW" items="${listWork }">
		    <c:set var="Wi" value="${Wi+1 }"></c:set>
			<div class="work-info"> 
				<c:if test="${Wi%2 == 1 }">
				<div class="col-md-6 work-left"> 
					<h4>${objW.period } </h4>
				</div>
				<div class="col-md-6 work-right"> 
					<h5><span class="glyphicon glyphicon-briefcase"> </span> ${objW.name }</h5>
					<p>${objW.description } </p>
				</div>
				</c:if>
				<c:if test="${Wi%2 == 0 }">
				<div class="col-md-6 work-right work-right2"> 
					<h4>${objW.period } </h4>
				</div>
				<div class="col-md-6 work-left work-left2"> 
					<h5> ${objW.name } <span class="glyphicon glyphicon-briefcase"> </span></h5>
					<p>${objW.description } </p>
				</div>
				</c:if>
				<div class="clrfix"> </div>
			</div>
			</c:forEach>
		</div>
	</div>
	<!-- //Experience-ends-here -->
	
	<!-- Portfolio-starts-here -->
	<div id="portfolio" class="portfolio">
		<div class="container">
	<span class="about-top-w3">The best of my projects </span>
		<h4 class="title-w3ls">Featured Projects</h4>
		<div class="news-bottom">
				<c:forEach var="objP" items="${listProject }">
				<c:set var="Pi" value="${Pi+1 }"></c:set>
				<div class="news-one-wthree-agile" id="pro-${objP.id_project }">
					<c:if test="${Pi%2==1 }">
					<div class="col-md-6 news-left">
						        <div class="biseller-column">
										<a class="lightbox" href="#${objP.id_project }">
											<img src="${pageContext.request.contextPath}/files/${objP.picture}" alt=""/>
										</a> 
										<div class="lightbox-target" id="${objP.id_project }">
										 <img src="${pageContext.request.contextPath}/files/${objP.picture}" alt=""/>
										 <p class="pop">${objP.preview_text }</p>
										   <a class="box-close-button" href="#pro-${objP.id_project }"> </a>
											
											<div class="clrfix"> </div>
											
										</div>
							   </div>

					</div>	
					<div class="col-md-6 news-right"> 
					<a href="#${objP.id_project }"><h4>${objP.name }</h4></a>
						<p class="para-w3-agile">${objP.preview_text }</p>	
					</div>
					</c:if>
					<c:if test="${Pi%2==0 }">
					<div class="col-md-6 news-right"> 
						<a href="#${objP.id_project }"><h4>${objP.name }</h4></a>
						<p class="para-w3-agile">${objP.preview_text }</p>	
					</div>	
					<div class="col-md-6 news-left">
						 <div class="biseller-column">
										<a class="lightbox" href="#${objP.id_project }">
											<img src="${pageContext.request.contextPath}/files/${objP.picture}" alt=""/>
										</a> 
										<div class="lightbox-target" id="${objP.id_project }">
										 <img src="${pageContext.request.contextPath}/files/${objP.picture}" alt=""/>
										 <p class="pop">${objP.preview_text }</p>
										   <a class="box-close-button" href="#pro-${objP.id_project }"> </a>
											
											<div class="clrfix"> </div>
										</div>
							</div>

					</div>						
					</c:if>	
					<div class="clrfix"> </div>	
				</div>
				</c:forEach>
				<div class="clrfix"> </div>				
			</div>	
	</div>
	</div>    
	<!-- Portfolio-ends-here -->
	
	<!-- start special quote -->
	<section id="specialQuote">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 wow bounceInLeft">
					<p>"Hãy theo đuổi đam mê, thành công sẽ theo đuổi bạn!" <span>- Three Idiots</span></p>
				</div>
			</div>
		</div>
	</section>
	<!-- End special quote -->
	
	<!-- start client testimonial -->
	<section id="testimonial">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="testimonial_area wow bounceIn">
						<div class="client_title">
							<hr>
							<h2>Danh ngôn</h2>
						</div>
						<ul class="testimon_nav">
							<c:forEach var="objQ" items="${listQuoteRandom }">
							<li>
								<div class="testimonial_content">
									<blockquote>
										<p>${objQ.content }</p>
										<small>${objQ.author }</small>
									</blockquote>
									<div class="client_img">
										<img src="${pageContext.request.contextPath}/files/${objQ.picture}" alt="img">
									</div>
								</div>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End client testimonial -->

	<!-- start featured blog area -->
	<section >
		<div class="container">
			<div class="row">
					<div >
							<h3 class="agile-contact">Bài viết</h3>
						<!-- start featured blog -->
						<div class="featured_blog">
							<div class="row">
								<c:forEach var="objN" items="${listNews }">
								<c:set var="urlNews" value="${pageContext.request.contextPath }/${slugUtil.makeSlug(objN.nameCat)}/${slugUtil.makeSlug(objN.name)}/${objN.id_news}.html"></c:set>
								<c:set var="urlCat" value="${pageContext.request.contextPath }/${slugUtil.makeSlug(objN.nameCat)}/${objN.id_cat}"></c:set>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<div class="single_featured_blog">
											<img alt="img" src="${pageContext.request.contextPath}/files/${objN.picture}">
										<h2><a href="${urlNews }">${objN.name }</a></h2>
										<div class="post_commentbox">
											<a href="${urlCat }"><i class="fa fa-tags"></i>${objN.nameCat }</a>
										</div>
										<p>${stringUtil.getPreview(objN.preview_text, 20)}</p>
										<a href="${urlNews }" class="read_more">Đọc tiếp...<i class="fa fa-long-arrow-right"></i></a>
									</div>
								</div>
								</c:forEach>
							</div>
						</div>
						<div class="clr"></div>
					</div>
					<div class="clr"></div>
			</div>
		</div>
	</section>
	<!-- End featured blog area -->
	
	<!-- advertisement -->
	<div class="brands" id="brands">
		<div class="container">
			<div class="row">
				<ul class="brands-nav">
					<c:forEach var="objAd" items="${listAd }">
					<li>
						<a href="${objAd.link }" target="_blank"><img src="${pageContext.request.contextPath }/files/${objAd.picture}" alt="" /></a>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>