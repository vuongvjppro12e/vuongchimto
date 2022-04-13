<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form class=" row mt-3 ms-0 pe-0" action="/Assignment/users/update?id=${user.id }" method="post" >
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">họ tên</label>
        <input type="text" class="form-control" name="hoTen" value="${ user.hoTen }">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">địa chỉ</label>
        <input type="text" class="form-control" name="diaChi" value="${ user.diaChi }">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">SĐT</label>
        <input type="number" class="form-control" name="sdt" value="${ user.sdt }">
    </div>
    <div class="mb-3 col-6">	
        <label class="form-label fw-bold">Email</label>
        <input type="email" class="form-control" name="email" value="${ user.email }">
    </div>
    <div class=" mb-3 mt-4 col-6">
        <label class="form-label fw-bold pe-4">Gender</label>
        <input class="form-check-input" type="radio" value="1" checked name="gioiTinh">
        <label class="form-check-label me-3">Nam</label>
        <input class="form-check-input" type="radio" value="0" name="gioiTinh">
        <label class="form-check-label">Nu</label>
    </div>
    <div class="mt-3">
        <button  class="btn btn-success">update</button>
        <button type="reset"  class="btn btn-primary">reset</button>
    </div>
</form>