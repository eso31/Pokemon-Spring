<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
  <head>
    <title>Pokemon</title>
  </head>
    <h4>Pokemon</h4> <a href="/pokesearch">Search</a>

    <table>
      <th>Name</th>
      <th>Type 1</th>
      <th>Type 2</th>
      <th>Image</th>
      <th></th>
        <tr>
          <td>${pokemon.name}</td>
          <td>${pokemon.type1}</td>
          <td>${pokemon.type2}</td>
          <td><img SRC="${pokemon.imageUrl}"</td>
          <td>
            <form action="/add" method="post" class="m6">
               <input type="hidden" name="pokemonId" value="${pokemon.pokemonId}">
               <input type="hidden" name="name" value="${pokemon.name}">
               <input type="hidden" name="type1" value="${pokemon.type1}">
               <input type="hidden" name="type2" value="${pokemon.type2}">
               <input type="hidden" name="imageUrl" value="${pokemon.imageUrl}">
               <input class="btn waves-effect waves-light" type="submit" value="Add pokemon">
             </form>
          </td>
        </tr>
    </table>



</html>

<!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/css/materialize.min.css">
<!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/js/materialize.min.js"></script>