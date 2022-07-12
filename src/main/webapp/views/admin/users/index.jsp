---<%@ page language="java" contentType="text/html; charset=UTF-8"
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
	<c:remove var="message" scope="session" />
</c:if>
<c:if test="${ empty ds }">
	<p class="alert alert-warning">Không có dữ liệu</p>
</c:if>

<c:if test="${ !empty ds }">
	<table class="table">
		<thead>
			<th>họ tên</th>
			<th>Giới tính</th>
			<th>SĐT</th>
			<th>Email</th>
			<th>Địa chỉ</th>
			<th colspan="2">Thao tác</th>
		</thead>
		<tbody>
			<c:forEach items="${ ds }" var="user">
				<tr>
					<td>${ user.hoTen }</td>
					<td><c:choose>
							<c:when test="${ user.gioiTinh == 1 }">Nam</c:when>
							<c:when test="${ user.gioiTinh == 0 }">Nữ</c:when>
							<c:otherwise> - </c:otherwise>
						</c:choose></td>
					<td>${ user.sdt }</td>
					<td>${ user.email }</td>
					<td>${ user.diaChi }</td>
					<td><a class="btn btn-primary"
						href="/Assignment/users/edit?id=${ user.id }" href="">Cập nhật</a></td>
					<td><a data-bs-toggle="modal" data-bs-target="#u${user.id }"
						class="btn btn-danger"> Xóa </a></td>
				</tr>
				
				<!-- Modal -->
<div class="modal fade" id="u${user.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">are you sure !!</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        continue ???
      </div>
      <div class="modal-footer">
        <a type="button" class="btn btn-primary" href="/Assignment/users/delete?id=${ user.id }">delete</a>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
			</c:forEach>
	
		</tbody>
	</table>
</c:if>
