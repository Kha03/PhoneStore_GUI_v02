<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>PAYMENT RESULT</title>

    <!-- Favicon -->
    <link rel="apple-touch-icon" sizes="57x57" href="<c:url value='/favicon/apple-icon-57x57.png'/>">
    <link rel="apple-touch-icon" sizes="60x60" href="<c:url value='/favicon/apple-icon-60x60.png'/>">
    <link rel="apple-touch-icon" sizes="72x72" href="<c:url value='/favicon/apple-icon-72x72.png'/>">
    <link rel="apple-touch-icon" sizes="76x76" href="<c:url value='/favicon/apple-icon-76x76.png'/>">
    <link rel="icon" type="image/png" sizes="192x192" href="<c:url value='/favicon/android-icon-192x192.png'/>">
    <link rel="icon" type="image/png" sizes="32x32" href="<c:url value='/favicon/favicon-32x32.png'/>">
    <meta name="theme-color" content="#ffffff">

    <!-- Bootstrap CSS -->
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet"/>
</head>
<body>
<div class="container my-5">
    <div class="text-center mb-4">
        <h3 class="text-primary">PAYMENT RESULT</h3>
    </div>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <tbody>
            <tr>
                <th scope="row">Transaction ID:</th>
                <td><%= request.getParameter("vnp_TxnRef") %></td>
            </tr>
            <tr>
                <th scope="row">Amount:</th>
                <td><%= request.getParameter("vnp_Amount") %></td>
            </tr>
            <tr>
                <th scope="row">Order Description:</th>
                <td><%= request.getParameter("vnp_OrderInfo") %></td>
            </tr>
            <tr>
                <th scope="row">Payment Response Code:</th>
                <td><%= request.getParameter("vnp_ResponseCode") %></td>
            </tr>
            <tr>
                <th scope="row">VNPAY Transaction No:</th>
                <td><%= request.getParameter("vnp_TransactionNo") %></td>
            </tr>
            <tr>
                <th scope="row">Bank Code:</th>
                <td><%= request.getParameter("vnp_BankCode") %></td>
            </tr>
            <tr>
                <th scope="row">Payment Date:</th>
                <td><%= request.getParameter("vnp_PayDate") %></td>
            </tr>
            <tr>
                <th scope="row">Transaction Status:</th>
                <td>
                    <%
                        String responseCode = request.getParameter("vnp_ResponseCode");
                        if ("00".equals(responseCode)) {
                            out.print("<span class='text-success font-weight-bold'>Success</span>");
                        } else {
                            out.print("<span class='text-danger font-weight-bold'>Failed</span>");
                        }
                    %>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <!-- Button to go back to home page -->
    <div class="text-center mt-4">
        <a href="<c:url value='/' />" class="btn btn-primary btn-lg">Back to Home</a>
    </div>

    <footer class="footer mt-5 text-center text-muted">
        <p>&copy; VNPAY 2024</p>
    </footer>
</div>

<!-- Bootstrap JS -->
<script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>
</body>
</html>
