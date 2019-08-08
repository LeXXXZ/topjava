<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/topjava.common.js" defer></script>
<script type="text/javascript" src="resources/js/topjava.meals.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-md-center"><spring:message code="meal.title"/></h3>
        <form id="filter" class="card border-dark">
            <div class="form-row">
                <div class="form-group col-lg">
                    <div class="col-md">
                        <lable class="col-form-label-lg"><spring:message code="meal.startDate"/>:</lable>
                        <input class="form-control" type="date" name="startDate" value="${param.startDate}">
                    </div>
                    <div class="col-md">
                        <lable class="col-form-label-lg"><spring:message code="meal.endDate"/>:</lable>
                        <input class="form-control" type="date" name="endDate" value="${param.endDate}">
                    </div>
                </div>
                <div class="form-group col-lg">
                    <div class="col-md">
                        <lable class="col-form-label-lg"><spring:message code="meal.startTime"/>:</lable>
                        <input class="form-control" type="time" name="startTime" value="${param.startTime}">
                    </div>
                    <div class="col-md">
                        <lable class="col-form-label-lg"><spring:message code="meal.endTime"/>:</lable>
                        <input class="form-control" type="time" name="endTime" value="${param.endTime}">
                    </div>
                </div>
            </div>
            <div class="card-footer">
                <button class="btn btn-primary" onclick="filter()"><spring:message code="meal.filter"/></button>
                <button class="btn btn-secondary" onclick="resetInput()"><spring:message code="common.reset"/></button>
            </div>
        </form>
        <hr>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="meal.add"/>
        </button>
        <hr>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="meal.dateTime"/></th>
                <th><spring:message code="meal.description"/></th>
                <th><spring:message code="meal.calories"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
                <tr data-mealExcess="${meal.excess}">
                    <td>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a><span class="fa fa-pencil"></span></a></td>
                    <td><a onclick="deleteRow(${meal.id})"><span class="fa fa-remove"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="meal.add"/></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="datetime" class="col-form-label"><spring:message code="meal.dateTime"/></label>
                        <input type="datetime-local" class="form-control" id="datetime" name="datetime"
                               placeholder="<spring:message code="meal.dateTime"/>">
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message
                                code="meal.description"/></label>
                        <input type="text" class="form-control" id="description" name="description"
                               placeholder="<spring:message code="meal.description"/>">
                    </div>

                    <div class="form-group">
                        <label for="calories" class="col-form-label"><spring:message code="meal.calories"/></label>
                        <input type="number" class="form-control" id="calories" name="calories"
                               placeholder="1000">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>