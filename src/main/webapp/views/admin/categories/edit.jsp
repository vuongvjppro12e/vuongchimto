<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<form class="row row-cols-lg-auto g-3 align-items-center" action="/Assignment/category/update?id=${category.id }" method="post">
	<div class="col-12">
		<div>
			<label>Tên danh mục</label> <input type="text" name="ten" value="${category.ten}"/>
		</div>
	</div>

	<div class="col-12">
		<label>Người tạo</label> <select name="user_id">
			<c:forEach items="${ dsUser }" var="user">
			
				<option ${user.id == category.user.id?"selected": "" }  value="${user.id }">${ user.hoTen }</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-12">
		<button type="submit" class="btn btn-primary">Update</button>
	</div>
</form>