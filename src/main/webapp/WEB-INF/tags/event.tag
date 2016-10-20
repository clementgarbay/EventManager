<%@ taglib prefix="app" uri="application" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="event" type="fr.eventmanager.entity.Event" required="true" description="L'évènement à afficher" rtexprvalue="true" %>

<div class="col-sm-4">
    <a href="<app:getUrl pathId="EVENTS"/>${event.id}">
        <div class="card card-stats">
            <div class="card-header" data-background-color="purple">
                <i class="material-icons">store</i>
            </div>
            <div class="card-content">
                <h3 class="title">${event.title}</h3>
                <p class="category">${fn:replace(event.description, fn:substring(event.description, 240, fn:length(event.description)), '...')}</p>
            </div>
            <div class="card-footer">
                <div class="stats">
                    <i class="material-icons">date_range</i> <fmt:formatDate pattern="'le' dd/MM/yyyy 'à' h'h'" value="${event.date}" />
                </div>
            </div>
        </div>
    </a>
</div>