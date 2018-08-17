<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
  <head>
    <title>Pokemon Team</title>
  </head>
    <h4>My Pokemon Team</h4> <a href="/pokesearch">Search</a>

    <table>
      <th>Id</th>
      <th>Name</th>
      <th>Type 1</th>
      <th>Type 2</th>
      <th>Image</th>
      <th></th>
      <c:forEach items="${pokemonList}" var="p">
        <tr>
          <td>${p.pokemonId}</td>
          <td>${p.name}</td>
          <td>${p.type1}</td>
          <td>${p.type2}</td>
          <td><img SRC="${p.imageUrl}"</td>
          <td>
            <form action="/remove" method="post" class="m6">
               <input type="hidden" name="id" value="${p.id}">
               <input class="btn waves-effect waves-light" type="submit" value="Remove pokemon">
             </form>
          </td>
        </tr>
      </c:forEach>
    </table>

</html>

<!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/css/materialize.min.css">
<!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/js/materialize.min.js"></script>