<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<h2>
	<fmt:formatDate value="${ now }" pattern="dd/MM/yyyy" />
</h2>
<c:if test="${!empty sessionScope.error }">
	<div class="alert alert-danger">${sessionScope.error }</div>
	<c:remove var="error" scope="session" />
</c:if>
<c:if test="${!empty sessionScope.message }">
	<div class="alert alert-success">${sessionScope.message }</div>
</c:if>
<c:if test="${ empty ds }">
	<p class="alert alert-warning">Không có dữ liệu</p>
</c:if>

<c:if test="${ !empty ds }">
	<table class="table">
		<thead>
			<th>tên</th>
			<th>số lượng</th>
			<th>đơn giá</th>
			<th>màu sắc</th>
			<th>kích thước</th>
			<th>loại</th>
			<th colspan="2">Thao tác</th>
		</thead>
		<tbody>
			<c:forEach items="${ ds }" var="pro">
				<tr>
					<td>${ pro.ten }</td>
					<td>${ pro.soLuong }</td>
					<td>${ pro.donGia }</td>
					<td>${ pro.mauSac }</td>
					<td>${ pro.kichThuoc }</td>
					<td>${ pro.category.ten }</td>
					<td><a class="btn btn-primary"
						href="/Assignment/product/edit?id=${pro.id}">Cập nhật</a></td>
					<td><a data-bs-toggle="modal" data-bs-target="#u${pro.id }"
						class="btn btn-danger"> Xóa </a></td>
				</tr>
<!-- Modal -->
<div class="modal fade" id="u${pro.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">are you sure !!</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        continue delete ???
      </div>
      <div class="modal-footer">
        <a type="button" class="btn btn-primary" href="/Assignment/product/delete?id=${ pro.id }">delete</a>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

			</c:forEach>
			
		</tbody>
	</table>
</c:if>
