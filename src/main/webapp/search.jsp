<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
  <head>
    <title>Pokeapi</title>
  </head>
    <h4>Search pokemon id</h4>

    <div class="row">
        <div class="col l4">
            <form action="/pokesearch" method="post" class="m6">
                Pokemon Id: <input type="text" name="id">
                <input class="btn waves-effect waves-light" type="submit" value="Submit">
             </form>
         </div>
    </div>

</html>

<!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/css/materialize.min.css">
<!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/js/materialize.min.js"></script>