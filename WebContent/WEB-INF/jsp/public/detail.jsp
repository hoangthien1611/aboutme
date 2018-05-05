<%@include file="/templates/taglib.jsp" %>
			<div class="clearfix content">

                    <h1>${objN.name } </h1>
                    <div class="clearfix post-meta">
                        <p><span><i class="fa fa-clock-o"></i> <fmt:formatDate pattern="hh:mm:ss dd/MM/yyyy" value="${objN.date_create }" type="date" /></span> <span><i class="fa fa-user"></i> ${objN.username }</span> <span><i class="fa fa-eye"></i> ${objN.count_views }</span></p>
                    </div>

                    <div class="vnecontent">
                        <p>${objN.detail_text }</p>
                    </div>

                </div>

                <div class="more_themes">
                    <h2>Bài viết liên quan <i class="fa fa-thumbs-o-up"></i></h2>
                    <div class="more_themes_container">
                    	<c:forEach var="objR" items="${listRelated }">
                    	<c:set var="url" value="${pageContext.request.contextPath }/${slugUtil.makeSlug(objR.nameCat)}/${slugUtil.makeSlug(objR.name)}/${objR.id_news}.html"></c:set>
                        <div class="single_more_themes floatleft">
                            <img src="${pageContext.request.contextPath }/files/${objR.picture}" alt="" />
                            <a href="${url }">
                                <h2>${objR.name }</h2>
                            </a>
                        </div>
                        </c:forEach>
						<div class="clr"></div>
                    </div>
                </div>
                
                <div class="comment">
                	<h3><span>Bình luận</span></h3>
                	<c:forEach var="objC" items="${listComment }">
                	<c:if test="${objC.id_parent == 0 }">
                	<div class="strator">
						<div class="strator-left">
							<img src="<%=request.getContextPath() %>/templates/public/images/co.png" class="img-responsive" alt="">
						</div>
						<div class="strator-right">
							<h5>${objC.fullname }</h5>
							<span><fmt:formatDate pattern="hh:mm:ss dd/MM/yyyy" value="${objC.date_create }" type="date" /></span>
							<p class="sin">${objC.content }</p>
							<div class="reply">
								<a href="javscript:void(0)" class="reply" onclick="showRep(${objC.id_comment})">Trả lời</a>
							</div>
						</div>
						<div class="clr"></div>
			     	</div>
					<c:forEach var="objSub" items="${listComment}">
					<c:if test="${objSub.id_parent == objC.id_comment }">	
					<div class="strator1">
							<div class="strator-left">
								<img src="<%=request.getContextPath() %>/templates/public/images/co.png" class="img-responsive" alt="">
							</div>
							<div class="strator-right">
								<h5>${objSub.fullname }</h5> 	
								<span><fmt:formatDate pattern="hh:mm:ss dd/MM/yyyy" value="${objSub.date_create }" type="date" /></span>
								<p class="sin">${objSub.content }</p>
								<div class="reply">
									<a href="javscript:void(0)" class="reply" onclick="showRep(${objC.id_comment})">Trả lời</a>
								</div>
							</div>
						<div class="clr"></div>
					</div>
					<div class="clr"></div>
					</c:if>
					</c:forEach>
					<div id="success-${objC.id_comment}"></div>
					<div class="comment-box rep-box form-${objC.id_comment }">
						<form  method="post" action="javascript:void(0)" class="formrep" id="form-${objC.id_comment }" onsubmit="return doComment(${objC.id_comment})">
							 <input type="text" id="name-${objC.id_comment}" name="name" class="textbox" value="" placeholder="Name" required>
							 <input type="text" id="email-${objC.id_comment}" name="email" class="textbox" value="" placeholder="Email" required>
							 <textarea value="Message:" id="message-${objC.id_comment}" name="message" placeholder="Ý kiến của bạn" required></textarea>
							 <div class="smt1">
							 	
								<input type="submit" value="Trả lời">
								<input onclick="hideRep(${objC.id_comment})" type="reset" value="ĐÓNG">
							 </div>
						 </form>
					</div>
					<div class="clr"></div>
					</c:if>
					</c:forEach>
					<div id="success-0"></div>
					<div class="comment-box">
								<form method="post" action="javascript:void(0)" id="form-0" onsubmit="doComment(0)">
								 <input type="text" id="name-0" name="name" class="textbox" value="" placeholder="Name" required>
								 <input type="text" id="email-0" name="email" class="textbox" value="" placeholder="Email" required>
								 <textarea value="Message:" id="message-0" name="message" placeholder="Ý kiến của bạn" required></textarea>
								 <div class="smt1">
								 	
									<input type="submit" value="Gửi">
								 </div>
							   </form>
							</div>
                </div>
                
                <script>
						function showRep(id) {
							$(".form-"+id).show();
						}
						function hideRep(id){
					        $(".form-"+id).hide();
					    }
						function doComment(id) {
							var aname = $('#name-'+id).val();
							var aemail = $('#email-'+id).val();
							var amessage = $('#message-'+id).val();
							var aidNews = ${objN.id_news};
							var aidParent = id;
							$.ajax({
								url: '${pageContext.request.contextPath}/detail',
								type: 'POST',
								cache: false,
								data: {
									fullname: aname,
									email: aemail,
									content: amessage,
									id_news: aidNews,
									id_parent: aidParent
									},
								success: function(data){
					               $('#success-'+id).html(data);
									
					               $('#form-'+id).trigger("reset");
								},
								error: function (){
					                alert('Có lỗi xảy ra!');
					                $('#form-'+id).trigger("reset");
								}
							});
						}
						$(function (){
							
							$(document).on('click', ".close", function() {
							       $('.alert-success').fadeOut(1000);
							       $('.alert-danger').fadeOut(1000);
							      
							 });
							});
					</script>