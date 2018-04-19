<%-- 
    Document   : student
    Created on : Mar 7, 2018, 1:09:14 PM
    Author     : GiaHieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="${pageContext.servletContext.contextPath}/">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Quản lý sinh viên</h1>
        <form:form action="student.htm" modelAttribute="student">
            <div> Mã SV </div>
            <form:input path="id"/>
            <div>Họ và tên  </div>
            <form:input path="name"/>

            <div>Điểm </div>
            <form:input path="mark"/>

            <div>Chuyên ngành </div>
            <form:select path="major" items="${major}" itemLabel="name" itemValue="id">
            </form:select>  
            <br>
            <div>
                <button name="btnInsert">Thêm</button>
                <button name="btnUpdate">Cập Nhập</button>
            </div>
        </form:form>
            <br>
        <table border="2">
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Mark</td>
                <td>Major</td>
                <td>Edit</td>
                <td>Delete</td>
            </tr>
            <c:forEach var="rows" items="${listStudent}">
                <form action="student/delete.htm">
                    <tr>
                        <td>${rows.id}</td>
                        <td>${rows.name}</td>
                        <td>${rows.mark}</td>
                        <td>${rows.major}</td>
                        <c:url var="editStudent" value="student/edit.htm">
                            <c:param name="txtId" value="${rows.id}"/>
                            <c:param name="txtName" value="${rows.name}"/>
                            <c:param name="txtMark" value="${rows.mark}"/>
                            <c:param name="txtMajor" value="${rows.major}"/>
                        </c:url>
                        <td><a href="${editStudent}">Edit</a></td>
                        <td>
                            <input type="hidden" name="txtId" value="${rows.id}"/>
                            <input type="submit" name="action" value="Delete"/>
                        </td>
                    </tr>
                </form>
            </c:forEach>    
        </table>
    </body>
</html>
