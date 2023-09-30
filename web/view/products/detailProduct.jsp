<%-- 
    Document   : detailProduct
    Created on : Sep 30, 2023, 2:56:15 PM
    Author     : taisk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="product-img" style="float:left; width: 35%">
            <img class="default-img" src="https://th.bing.com/th/id/OIP.r1XiTgrz74owkRBP9l674QHaHa?pid=ImgDet&rs=1" alt="#">
        </div>
        <div style="font-family: cursive; font-size: larger; margin-top: 100px; padding-top: 100px">
            <ul>
                <li style="padding-top: 20px">ProductName: ${requestScope.product.productName}</li>
                <li style="padding-top: 20px">CategoryName: ${requestScope.product.category.categoryName}</li>
                <li style="padding-top: 20px">Description: ${requestScope.product.category.description}</li>
                <li style="padding-top: 20px">QuantityPerUnit: ${requestScope.product.quantityPerUnit}</li>
                <li style="padding-top: 20px">UnitsInStock: ${requestScope.product.unitsInStock}</li>
                <li style="padding-top: 20px">Price:${requestScope.product.unitPrice}</li>
            </ul>
        </div>
        <button>
            <a href="#">AddToCart</a>
        </button>
        <button>
            <a href="indexController">Back</a>
        </button>
    </body>
</html>
