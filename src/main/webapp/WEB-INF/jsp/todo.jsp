<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container mt-3">
    <div class="row">
        <c:if test="${not empty message}">
            <div class="alert alert-success" role="alert">
                    ${message}
            </div>
        </c:if>
        <div class="col-md-6 offset-3">
            <div class="panel panel-primary">
                <c:choose>
                    <c:when test="${todo.id==null}">
                        <div class="display-4 text-center"><b>Add Todo</b></div>
                        <br />
                    </c:when>
                    <c:otherwise>
                        <div class="display-4 text-center"><b>Edit Todo</b></div>
                        <br />
                    </c:otherwise>
                </c:choose>
                <div class="panel-body">
                    <%--@elvariable id="todo" type="long"--%>
                    <form:form method="post" modelAttribute="todo" class="form-control">
                        <form:hidden path="id" />
                        <fieldset class="form-group">
                            <form:label path="title">Title</form:label>
                            <form:input path="title" type="text" class="form-control"
                                        required="required" />
                            <form:errors path="title" cssClass="text-warning" />
                        </fieldset>
                        <fieldset class="form-group">
                            <form:label path="description">Description</form:label>
                            <form:input path="description" type="text" class="form-control"
                                        required="required" />
                            <form:errors path="description" cssClass="text-warning" />
                        </fieldset>
                        <fieldset class="form-group">
                            <form:label path="status">Status</form:label>
                            <form:select path="status"  class="form-select"
                                        required="required">
                                <form:options items="${status}"/>
                            </form:select>
                            <form:errors path="status" cssClass="text-warning" />
                        </fieldset>
                        <fieldset class="form-group">
                            <form:label path="startDate">Start Date</form:label>
                            <form:input path="startDate" type="text" placeholder="dd/mm/yyyy" class="form-control"
                                        required="required" />
                            <form:errors path="startDate" cssClass="text-warning" />
                        </fieldset>
                        <fieldset class="form-group">
                            <form:label path="targetDate">Target Date</form:label>
                            <form:input path="targetDate" type="text" placeholder="dd/mm/yyyy" class="form-control"
                                        required="required" />
                            <form:errors path="targetDate" cssClass="text-warning" />
                        </fieldset>
                        <br>
                        <button type="submit" class="btn btn-success">Save</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jspf"%>