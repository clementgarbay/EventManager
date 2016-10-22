<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" uri="application" %>

<div class="col-sm-12">
    <div class="row">
        <c:forEach items="${events}" var="event">
            <app:event event="${event}" />
        </c:forEach>
    </div>
</div>