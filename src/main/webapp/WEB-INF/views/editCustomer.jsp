<%@ include file="header.jsp" %>
<div class="container-fluid">

  <div class="row">
  	<div class="col-md-6">
  	</div>
  	<div class="col-md-6">
  			  <h2>Edit Customer: ${customer.customerName }</h2>
			<form class="form-horizontal"  action="<%=request.getContextPath()%>/customer/editCustomer.action" method="post" role="form">
				 <input type="hidden" value="${customer.customerId }"	 name="customerId" id="customerId">
				<div class="form-group">
					<label for="title">Customer Name</label>
					<div class="input-group">
						<span class="input-group-addon"> <span	class="glyphicon glyphicon-cog"></span></span>
						 <input type="text" value="${customer.customerName }"	class="form-control" name="name" id="name"
							placeholder="Enter Name" required>
					</div>
				</div>
				<div class="form-group">
					<label for="desc">Country</label>
					<div class="input-group">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-pencil"></span></span> <input type="text"
							class="form-control" name="country" value="${customer.country }"
							placeholder="Enter Country" required>
					</div>
				</div>
					<button type="submit" class="btn btn-primary">Update</button>
			</form>
  	
  	</div>
 </div>
</div>