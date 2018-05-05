 <%@include file="/templates/taglib.jsp" %>
                   <div class="clearfix content">
                    <div class="content_title">
                        <h2>Tìm kiếm</h2>
                    </div>
                    <div class="form-search">
                    <form action="${pageContext.request.contextPath }/search" method="get">
                  		<select name="searchCat" style="width: 170px;float:left; height: 27px;padding: 5px; margin-top: 18px;">
                  		   <option value="0">Danh mục</option>
						   <c:forEach var="objCat" items="${listCat }">
						   <option <c:if test="${objCat.id_cat == idCat }">selected='selected'</c:if> value="${objCat.id_cat }">${objCat.name }</option>
						   </c:forEach>
						</select>
                           <input type="text" name="searchText" value="${sText }" placeholder="Search..." >
                       	   <input type="submit" value="Tìm kiếm" />
                     </form>
                    </div>
                    
					<c:forEach var="objN" items="${listNews }">
					<c:set var="url" value="${pageContext.request.contextPath }/${slugUtil.makeSlug(objN.nameCat)}/${slugUtil.makeSlug(objN.name)}/${objN.id_news}.html"></c:set>
                    <div class="clearfix single_content">
                        <div class="clearfix post_date floatleft">
                            <div class="date">
                                <h3><fmt:formatDate pattern="dd" value="${objN.date_create }" type="date" /></h3>
                                <p>Tháng <fmt:formatDate pattern="MM" value="${objN.date_create}" type="date" /></p>
                            </div>
                        </div>
                        <div class="clearfix post_detail">
                            <h2><a href="${url }">${objN.name } </a></h2>
                            
                            <div class="clearfix post_excerpt">
                                <a href="${url }"><img src="${pageContext.request.contextPath }/files/${objN.picture}" alt="" /></a>
                                <div class="clearfix post-meta">
                                <p><span><i class="fa fa-user"></i> ${objN.username }</span> <span><i class="fa fa-eye"></i> ${objN.count_views }</span></p>
                            	</div>
                                <p>${stringUtil.getPreview(objN.preview_text, 30) }</p>
                            	<a class="doc-them" href="${url }">Đọc thêm</a>
                            </div>
                        </div>
                    </div>
					</c:forEach>
                </div>

                <div class="pagination">
                    <nav>
                        <ul>
                            <c:if test="${currentPage > 1}">
							<li><a href="${pageContext.request.contextPath }/search?searchCat=${idCat}&searchText=${sText}&page=${currentPage - 1}"> <<</a></li>
							</c:if>
							<c:forEach begin="${pageStart }" end="${pageEnd }" var="i">
								<c:set var="urlSeo" value="${pageContext.request.contextPath }/search?searchCat=${idCat}&searchText=${sText}&page=${i}"></c:set>
								<li ><a href="${urlSeo}" <c:if test="${i == currentPage }">class="active"</c:if>>${i }</a></li>
							</c:forEach>
							<c:if test="${currentPage < sumPage }">
							<li><a href="${pageContext.request.contextPath }/search?searchCat=${idCat}&searchText=${sText}&page=${currentPage - 1}">>></a></li>
							</c:if>
                        </ul>
                    </nav>
                </div>