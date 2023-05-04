<%@page import="com.connection.HibernateUtil"%>
<%@page import="com.dao.ProductDAO"%>
<%@page import="com.entity.*"%>
<%@page import="java.util.*"%>
<%@ page import="org.hibernate.Session"%>
<%@ page import="org.hibernate.query.Query"%>
<%@ page import="com.entity.Products"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	if (session.getAttribute("data") == null) {
		response.sendRedirect("Login.jsp");
	} else {
		Employee emp = (Employee) session.getAttribute("data");
%>

<%@page import="java.util.List"%>
<%@page import="java.sql.Blob"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management Tool </title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.4/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>
	<h1 class="text-center">Product Management Tool</h1>
	<p class="d-flex justify-content-between">
		Hi,
		<%
		out.println(emp.getName());
	%>
		<a href="logout">Logout</a>
	</p>
	<div class="card-body">
		<h5>Please enter product details to add to stock</h5>
		<form action="Dashboard" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>Title:</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td>Quantity:</td>
					<td><input type="number" name="quantity"></td>
				</tr>
				<tr>
					<td>Size:</td>
					<td><input type="text" name="size"></td>
				</tr>
				<tr>
					<td>Image:</td>
					<td><input type="file" name="image"></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><button type="submit">Add Product</button></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>S No.</th>
					<th>Title</th>
					<th>Quantity</th>
					<th>Size</th>
					<th>Image</th>
					<th>Action</th>
				</tr>



				<%
					ProductDAO productDAO = new ProductDAO(HibernateUtil.getSessionFactory());
						List<Products> list = productDAO.getAllProducts();
						int counter = 1;
						for (Products product : list) {
							String imageConvert = Base64.getEncoder().encodeToString(product.getImage());
				%>
				<tr>
					<td><%=counter%></td>
					<td><%=product.getTitle()%></td>
					<td><%=product.getQuantity()%></td>
					<td><%=product.getSize()%></td>
					<td><img alt="ProductImage"
						style="max-height: 200px; max-width: 100px; width: auto;"
						src="data:image/png;base64,<%=imageConvert%>"
						class="card-img-top m-2"></td>
					<td
						style="display: flex; justify-content: center; align-items: center; padding: 5px;">
						<form action="EditProduct.jsp" method="get" target="_blank">
							<input type="hidden" name="id" value="<%=product.getId()%>">
							<button type="submit" class="btn btn-secondary">
								<i class="bi bi-pencil-square"></i>
							</button>
						</form>

						<form action="Delete" method="post">
							<input type="hidden" name="id" value="<%=product.getId()%>">
							<button type="submit"
								onclick="if (!(confirm('Are you sure you want to delete this item?'))) return false">
								<i title="Delete Product" class="bi bi-x-circle"></i>
							</button>
						</form>
					</td>
				</tr>
				<%
					counter++;
						}
				%>

			</table>

		</div>
		<%
			String message = (String) request.getAttribute("message");
				if (message != null) {
		%>
		<script>
        	alert("<%=message%>");
		</script>
		<%
			}
		%>
	</div>
</body>
</html>
<%
	}
%>