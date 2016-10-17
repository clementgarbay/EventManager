<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="application" %>

<div class="col-sm-12">
    <div class="row">

        <c:forEach items="${events}" var="event">
            <div class="col-sm-4">
                <a href="<app:getUrl pathId="EVENTS"/>${event.id}">
                    <div class="card card-stats">
                        <div class="card-header" data-background-color="purple">
                            <i class="fa fa-home"></i>
                        </div>
                        <div class="card-content">
                            <h3 class="title">${event.title}</h3>
                            <p class="category">${fn:replace(event.description, fn:substring(event.description, 240, fn:length(event.description)), '...')}</p>
                        </div>
                        <div class="card-footer">
                            <div class="stats">
                                <i class="fa fa-calendar"></i> <fmt:formatDate pattern="'le' dd/MM/yyyy 'à' h'h'" value="${event.date}" />
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>

    </div>
</div>