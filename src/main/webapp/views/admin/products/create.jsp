<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<form class=" row mt-5 ms-0 pe-0" action="store" method="post">
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">tên sản phẩm</label>
        <input type="text" class="form-control" name="ten">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">số lượng</label>
        <input type="number" class="form-control" name="soLuong">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">đơn giá</label>
        <input type="number" class="form-control" name="donGia">
    </div>
    <div class="mb-3 col-6">	
        <label class="form-label fw-bold">màu sắc</label>
        <input type="text" class="form-control" name="mauSac">
    </div>
    <div class="mb-3 col-6">
    	<label for="inputState" class="form-label">kích thước</label>
    <select class="form-select" name="kichThuoc">
      <option >XS</option>
      <option >S</option>
      <option selected="selected" >M</option>
      <option >L</option>
      <option >XL</option>
    </select>
    </div>
    <div class=" mb-3 mt-4 col-6">
        <label>Loại</label>
		 <select name="category">
			<c:forEach items="${ dsCategory }" var="category">
				<option value="${ category.id }">${ category.ten }</option>
			</c:forEach>
		</select>
    </div>
    <div class="mt-3">
        <button  class="btn btn-success">add</button>
        <button type="reset"  class="btn btn-primary">reset</button>
    </div>
</form>
