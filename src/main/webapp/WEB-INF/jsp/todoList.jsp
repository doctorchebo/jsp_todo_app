<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container mt-3">
    <c:if test="${not empty message}">
        <div class="alert alert-success" role="alert">
                ${message}
        </div>
    </c:if>
    <div>
        <a type="button" class="btn btn-primary btn-md" href="TodoList/new">Add Todo</a>
    </div>
    <br>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>List of Todos</h3>
        </div>
        <div class="panel-body">
            <table class="table">
                <thead class="table-dark">
                <tr>
                    <th width="20%">Title</th>
                    <th width="20%">Description</th>
                    <th width="10%">Status</th>
                    <th width="20%">Target Date</th>
                    <th width="15%">Update</th>
                    <th width="15%">Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.title}</td>
                        <td>${todo.description}</td>
                        <td>${todo.status}</td>
                        <td><fmt:formatDate value="${todo.targetDate}"
                                            pattern="dd/MM/yyyy" /></td>
                        <td><a type="button" class="btn btn-success"
                               href="TodoList/edit?id=${todo.id}">Update</a>
                            </td>
                        <td><a type="button" class="btn btn-warning"
                               href="TodoList/delete?id=${todo.id}">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
<%@ include file="../common/footer.jspf"%>