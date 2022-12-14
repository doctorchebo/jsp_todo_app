<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container mt-3">
    <c:if test="${not empty message}">
        <div class="alert alert-success" role="alert">
                ${message}
        </div>
    </c:if>
    <%--@elvariable id="todo" type="long"--%>
    <form:form action="/TodoList/search" modelAttribute="todo" method="post" class="search-form w-25">
        <div class="d-flex">
            <form:input path="title" class="form-control me-2" placeholder="search by title"/><br/>
            <ul id="list-group" class="list-group"></ul>
            <button type="submit" class="btn btn-info"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>
    </form:form>
    <div>
        <a type="button" class="btn btn-primary btn-md" href="new">Add Todo</a>

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
                    <th width="15%">Title</th>
                    <th width="20%">Description</th>
                    <th width="10%">Status</th>
                    <th width="10%">Start Date</th>
                    <th width="10%">Target Date</th>
                    <th width="10%">Update</th>
                    <th width="10%">Delete</th>
                    <th width="5%">Set Done</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.title}</td>
                        <td>${todo.description}</td>
                        <td>${todo.status}</td>
                        <td><fmt:formatDate value="${todo.startDate}"
                                            pattern="dd/MM/yyyy" /></td>
                        <td><fmt:formatDate value="${todo.targetDate}"
                                            pattern="dd/MM/yyyy" /></td>
                        <td><a type="button" class="btn btn-success"
                               href="edit?id=${todo.id}">Update</a>
                            </td>
                        <td><a type="button" class="btn btn-warning"
                               href="delete?id=${todo.id}">Delete</a></td>
                        <c:choose>
                            <c:when test="${todo.status=='PENDING'}">
                                <td class="text-center">
                                    <a type="button" class="pending btn btn-light"
                                                           href="complete?id=${todo.id}"><i class="fa fa-check-circle" aria-hidden="true"></i>
                                    </a>
                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="pagination">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
            <c:choose>
                <c:when test="${page==0}">
                    <li class="page-item disabled">
                        <a class="page-link" href="" tabindex="-1"><i class="fa fa-arrow-left"></i></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="/TodoList/?page=${page-1}" tabindex="-1"><i class="fa fa-arrow-left"></i></a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${pageSize >= 5 && totalCount>5}">
                    <li class="page-item">
                        <a class="page-link" href="/TodoList/?page=${page+1}"><i class="fa fa-arrow-right"></i></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link" href="/TodoList/?page=${page+1}"><i class="fa fa-arrow-right"></i></a>
                    </li>
                </c:otherwise>
            </c:choose>
            </ul>
        </nav>
    </div>
</div>
<%@ include file="../common/footer.jspf"%>