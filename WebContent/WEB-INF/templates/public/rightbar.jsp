<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
				<div class="clearfix sidebar">
                    <div class="clearfix single_sidebar">
                        <div class="popular_post">
                            <div class="sidebar_title">
                                <h3><span>Bài viết mới</span></h3>
                            </div>
                            <ul>
                            	<c:forEach var="objN" items="${listItemNews }">
                            	<c:set var="url" value="${pageContext.request.contextPath }/${slugUtil.makeSlug(objN.nameCat)}/${slugUtil.makeSlug(objN.name)}/${objN.id_news}.html"></c:set>
                                <li><a href="${url }">${objN.name } </a></li>
                            	</c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="clearfix single_sidebar">
                        <div class="popular_post">
                            <div class="sidebar_title">
                                <h3><span>Bài đọc nhiều</span></h3>
                            </div>
                            <ul>
                            	<c:forEach var="objN" items="${listNewsMostViewed }">
                            	<c:set var="url" value="${pageContext.request.contextPath }/${slugUtil.makeSlug(objN.nameCat)}/${slugUtil.makeSlug(objN.name)}/${objN.id_news}.html"></c:set>
                                <li><a href="${url }">${objN.name } </a></li>
                            	</c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="clearfix single_sidebar category_items">
                        <div class="sidebar_title">
							<h3><span>Danh mục</span></h3>
						</div>
                        <ul>
                        	<c:forEach var="objC" items="${listCatRight }">
                        	<c:set var="url" value="${pageContext.request.contextPath }/${slugUtil.makeSlug(objC.name)}/${objC.id_cat}"></c:set>
                            <li class="cat-item"><a href="${url }">${objC.name }</a>(${objC.count_news })</li>
                        	</c:forEach>
                        </ul>
                    </div>
                </div>