<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Policy Page</title>

	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
		.error {
            	color: red;
	</style>
</head>
<body>
<h1>
    Add a Policy
</h1>
<table align="right">
    <tr>
        <td><a href = "<c:url value='/login'/>">Login</a>
        </td>

        <td><a href="<c:url value='/registration'/>">Register</a>
        </td>
    </tr>
</table>
<c:url var="addAction" value="/policy/add" ></c:url>

<form:form action="${addAction}" commandName="policy">
<table>
	<c:if test="${!empty policy.id}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="40"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="price">
				<spring:message text="Price"/>
			</form:label>
		</td>
		<td>
			<form:input path="price" />
			<span>
			    <form:errors path="price" cssClass="error" />
			</span>
		</td>

	</tr>
	<tr>
		<td>
			<form:label path="type.id">
				<spring:message text="Type"/>
			</form:label>
		</td>
		<td>
            <form:select path="type.id">
                <form:option value="" label="" />
                <form:options items="${typeList}" itemValue="id" itemLabel="name" />
            </form:select>
            <span>
                <form:errors path="type.id" cssClass="error" />
            </span>
         </td>
	</tr>
  	<tr>
		<td>
			<form:label path="active">
				<spring:message text="Active"/>
			</form:label>
		</td>
		<td>
            <form:checkbox path="active" />
         <td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty policy.id}">
				<input type="submit"
					value="<spring:message text="Edit Policy"/>" />
			</c:if>
			<c:if test="${empty policy.id}">
				<input type="submit"
					value="<spring:message text="Add Policy"/>" />
			</c:if>
		</td>
	</tr>
</table>
</form:form>
<c:url var="findAction" value="/policy/find" ></c:url>

<form:form action="${findAction}" commandName="policy">
<table>
    <tr>
        <th width="80">Policy Price</th>
        <th width="80">Policy Type</th>
        <th width="100">Policy Active</th>
    </tr>
	<tr>
		<td>
			<input type="text" name="pricef" />
		</td>
		<td>
            <form:select path="typef">
                <form:option value="" label="" />
                <form:options items="${typeList}" itemValue="id" itemLabel="name" />
            </form:select>
         </td>
        <td>
             <form:checkbox path="activef" />
          <td>
		<td colspan="2">
            <input type="submit"
                value="<spring:message text="Find Policy"/>" disabled="true" />
		</td>
	</tr>
</table>
</form:form>
<br>
<h3>Policys List</h3>
<c:if test="${!empty listPolicys}">
	<table class="tg">
	<tr>
		<th width="120">Policy ID</th>
		<th width="120">Policy Price</th>
		<th width="120">Policy Type</th>
        <th width="120">Policy Active</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listPolicys}" var="policy">
		<tr>
			<td>${policy.id}</td>
			<td>${policy.price}</td>
			<td>${policy.type.name}</td>
            <td>${policy.active}</td>
			<td><a href="<c:url value='/edit/${policy.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${policy.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
<br>

   <c:if test="${paginationResult.totalPages > 1}">
       <div class="page-navigator">
          <c:forEach items="${paginationResult.navigationPages}" var = "page">
              <c:if test="${page != -1 }">
                <a href="policys?page=${page}" class="nav-item">${page}</a>
              </c:if>
              <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
              </c:if>
          </c:forEach>

       </div>
   </c:if>
</body>
</html>