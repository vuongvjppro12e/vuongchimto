<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IT16304</title>
<link rel="stylesheet"
	href="/Assignment/css/bootstrap.min.css" />
</head>
	<nav class="navbar navbar-expand-lg navbar-light bg-light mx-5 ">
  <div class="container-fluid">
    <a class="navbar-brand" href="/Assignment/HomeServlet">FPT-TECH</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            USER
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/Assignment/users/index">List User</a></li>
            <li><a class="dropdown-item" href="/Assignment/users/create">Add user</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            PRODUCT
          </a>
          
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/Assignment/product/index">List product</a></li>
            <li><a class="dropdown-item" href="/Assignment/product/create">Add product</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            CATEGORY
          </a>
          
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/Assignment/category/index">List category</a></li>
            <li><a class="dropdown-item" href="/Assignment/category/create">Add category</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            ACCOUNT
          </a>
          
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/Assignment/dangXuat">đăng xuất</a></li>
            <li><a class="dropdown-item" href="/Assignment/login">đăng nhập</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
      </ul>
      <div class="me-5">using: <a class="text-dark"> ${sessionScope.user != null ? user.hoTen:"none" }</a></div>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
<body >
	<div class="container">
	<jsp:include page="${views}"/>
	</div>
</body>

<footer class="bg-light text-center text-lg-start mt-4">
  
  <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
    © 2022 Copyright:
    <a class="text-dark" href="https://www.facebook.com/VTV.vjpPro/"> VUONGVT-PH15053</a>
  </div>
  
</footer >
	<script src="/Assignment/js/jquery.min.js"></script>
	<script src="/Assignment/js/popper.min.js"></script>
	<script src="/Assignment/js/bootstrap.min.js"></script>
</html>