<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container container-fluid border-bottom border-primary">
  <div
    class="d-flex align-items-center justify-content-between pt-2 pb-2 header"
  >
    <a href="#!">
      <img  src="<c:url value='/imgs/img_logo_blue_a700.svg' />" alt="Logo" />
    </a>
    <ul class="nav col-12 col-md-auto justify-content-center">
      <li>
        <a href="#" class="nav-link px-2 mx-2 link-secondary">Home</a>
      </li>
      <li><a href="#" class="nav-link px-2 mx-2 link-dark">Products</a></li>
      <li><a href="#" class="nav-link px-2 mx-2 link-dark">Blog</a></li>
      <li><a href="#" class="nav-link px-2 mx-2 link-dark">FAQ</a></li>
      <li>
        <a href="#" class="nav-link px-2 mx-2 link-dark">Contact Us</a>
      </li>
    </ul>

    <div class="col-3 text-end">
      <button type="button" class="btn position-relative me-3" data-bs-toggle="offcanvas" data-bs-target="#cartOffcanvas" aria-controls="cartOffcanvas">
        <img
                src="<c:url value='/icon/shopping-cart.svg' />"
                alt="cart"
        />
        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
          ${cart != null ? cart.size() : 0}
        </span>
      </button>
      <button type="button" class="btn btn-signIn me-2">
        Sign In / Sign Up
      </button>
    </div>
  </div>
</div>
<div class="offcanvas offcanvas-end" tabindex="-1" id="cartOffcanvas" aria-labelledby="cartOffcanvasLabel">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title" id="cartOffcanvasLabel">Shopping Cart (${cart != null ? cart.size() : 0} items)</h5>
    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
    <!-- Product Item -->
    <div class="card mb-3">
      <div class="row g-0 align-items-center">
        <div class="col-md-4">
          <img src="../Icons/productlaptop.png" class="img-fluid rounded-start" alt="Product Image">
        </div>
        <div class="col-md-8">
          <div class="card-body">
            <h6 class="card-title mb-1">MacBook Pro M2 MNEJ3 2022 LLA 13.3inch</h6>
            <p class="text-muted mb-1">Memory: 256GB</p>
            <p class="text-muted mb-1">Color: Black</p>
            <div class="d-flex justify-content-between align-items-center">
              <span>Quantity: 1</span>
              <p class="mb-0 fw-bold">$433.00</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- End Product Item -->
    <div class="card mb-3">
      <div class="row g-0 align-items-center">
        <div class="col-md-4">
          <img src="../Icons/productlaptop.png" class="img-fluid rounded-start" alt="Product Image">
        </div>
        <div class="col-md-8">
          <div class="card-body">
            <h6 class="card-title mb-1">MacBook Pro M2 MNEJ3 2022 LLA 13.3inch</h6>
            <p class="text-muted mb-1">Memory: 256GB</p>
            <p class="text-muted mb-1">Color: Black</p>
            <div class="d-flex justify-content-between align-items-center">
              <span>Quantity: 1</span>
              <p class="mb-0 fw-bold">$433.00</p>
            </div>
          </div>
        </div>
      </div>
    </div> <div class="card mb-3">
    <div class="row g-0 align-items-center">
      <div class="col-md-4">
        <img src="../Icons/productlaptop.png" class="img-fluid rounded-start" alt="Product Image">
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <h6 class="card-title mb-1">MacBook Pro M2 MNEJ3 2022 LLA 13.3inch</h6>
          <p class="text-muted mb-1">Memory: 256GB</p>
          <p class="text-muted mb-1">Color: Black</p>
          <div class="d-flex justify-content-between align-items-center">
            <span>Quantity: 1</span>
            <p class="mb-0 fw-bold">$433.00</p>
          </div>
        </div>
      </div>
    </div>
  </div> <div class="card mb-3">
    <div class="row g-0 align-items-center">
      <div class="col-md-4">
        <img src="../Icons/productlaptop.png" class="img-fluid rounded-start" alt="Product Image">
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <h6 class="card-title mb-1">MacBook Pro M2 MNEJ3 2022 LLA 13.3inch</h6>
          <p class="text-muted mb-1">Memory: 256GB</p>
          <p class="text-muted mb-1">Color: Black</p>
          <div class="d-flex justify-content-between align-items-center">
            <span>Quantity: 1</span>
            <p class="mb-0 fw-bold">$433.00</p>
          </div>
        </div>
      </div>
    </div>
  </div>
    <!-- Repeat for more products -->
    <div class="card mb-3">
      <div class="row g-0 align-items-center">
        <div class="col-md-4">
          <img src="../Icons/lpproduct.png" class="img-fluid rounded-start" alt="Product Image">
        </div>
        <div class="col-md-8">
          <div class="card-body">
            <h6 class="card-title mb-1">MacBook Pro M2 MNEJ3 2022 LLA 13.3inch</h6>
            <p class="text-muted mb-1">Memory: 256GB</p>
            <p class="text-muted mb-1">Color: Black</p>
            <div class="d-flex justify-content-between align-items-center">
              <span>Quantity: 1</span>
              <p class="mb-0 fw-bold">$433.00</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="offcanvas-footer p-3">
    <div class="d-flex justify-content-between align-items-center">
      <div>
        <p class="mb-0">Grand Total:</p>
        <h5 class="fw-bold">$543.02</h5>
      </div>
      <button class="btn btn-primary btn-lg">Proceed to Checkout</button>
    </div>
  </div>
</div>
