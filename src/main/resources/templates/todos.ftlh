<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Todos</title>
</head>
<body>
    <table border="1">
        <thead>
            <tr>
                <td>ID</td>
                <td>Title</td>
                <td>Category</td>
            </tr>
        </thead>
        <tbody>
            <#list todos as todo>
                <tr>
                    <td>${todo.getId()}</td>
                    <td>${todo.getTitle()}</td>
                    <td>${todo.getCategory()}</td>
                </tr>
            </#list>
        </tbody>
    </table>
</body>
</html>