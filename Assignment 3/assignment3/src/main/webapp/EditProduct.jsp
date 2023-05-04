<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.connection.HibernateUtil"%>
<%@page import="com.dao.ProductDAO"%>
<%@page import="com.entity.*"%>
<%@page import="java.util.*"%>
<%
	String productId = request.getParameter("id");
	session.setAttribute("id", productId);
	if (productId == null) {
        // Handle error condition
    } else {
        ProductDAO productDAO = new ProductDAO(HibernateUtil.getSessionFactory());
        Products product = productDAO.getProductById(Integer.parseInt(productId));
        if (product == null) {
            // Handle error condition
        } else {
							
				%>
    
<form action="UpdateProduct" method="post" enctype="multipart/form-data">
   <input type="hidden" name="id" value="<%= product.getId() %>">
   <div class="form-group">
      <label for="title">Title:</label>
      <input type="text" class="form-control" id="title" name="title" value="<%= product.getTitle() %>">
   </div>
   <div class="form-group">
      <label for="quantity">Quantity:</label>
      <input type="number" class="form-control" id="quantity" name="quantity" value="<%= product.getQuantity() %>">
   </div>
   <div class="form-group">
      <label for="size">Size:</label>
      <input type="text" class="form-control" id="size" name="size" value="<%=product.getSize() %>">
   </div>
   <div class="form-group">
      <label for="image">Image:</label>
      <input type="file" class="form-control-file" id="image" name="image">
   </div>
   <button type="submit" class="btn btn-primary">Update Product</button>
</form>

  <% } 
        }%>