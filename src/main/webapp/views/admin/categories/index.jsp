<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:if test="${ empty ds }">
	<p class="alert alert-warning">Không có dữ liệu</p>
</c:if>

<c:if test="${ !empty ds }">
	<table class="table">
		<thead>
			<th>Tên danh mục</th>
			<th>Người tạo</th>
			<th colspan="2">Thao tác</th>
		</thead>
		<tbody>
			<c:forEach items="${ds}" var="category">
				<tr>
					<td>${category.ten}</td>
					<td>${category.user.hoTen}</td>
					<td><a class="btn btn-primary"
						href="/Assignment/category/edit?id=${ category.id }">Cập nhật</a>
					</td>
					<td><a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#u${category.id }"
						href="/Assignment/category/delete?id=${ category.id }">Xoá</a></td>
				</tr>

				<!-- Modal -->
				<div class="modal fade" id="u${category.id }" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">are you sure
									!!</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">continue delete ???</div>
							<div class="modal-footer">
								<a type="button" class="btn btn-primary"
									href="/Assignment/category/delete?id=${ category.id }">delete</a>
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</tbody>
	</table>
</c:if>