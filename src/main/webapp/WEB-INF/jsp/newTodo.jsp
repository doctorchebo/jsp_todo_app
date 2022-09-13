<%@ include file="../common/header.jsp"%>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="panel panel-primary">
                <div class="panel-heading"><b>Add Todo</b></div>
                <div class="panel-body">
                    <%--@elvariable id="todo" type="uuid"--%>
                    <form:form method="post" modelAttribute="todo">
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
                            <form:select path="status"  class="form-control"
                                        required="required" items="${status}">
                            </form:select>
                            <form:errors path="status" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="targetDate">Target Date</form:label>
                            <form:input path="targetDate" type="text" class="form-control"
                                        required="required" />
                            <form:errors path="targetDate" cssClass="text-warning" />
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp"%>