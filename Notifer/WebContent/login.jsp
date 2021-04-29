


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="basic.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <title>Notifer</title>
  <style>
  .card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.5);
  max-width: 300px;
  height: 300px;
  margin: 0 auto;
  margin-top: 20px;
  text-align: center;
  font-family: arial;
  padding-bottom: 20px;
}
.rem{
  margin-top: 180px;
}
.title{
  margin-top: 10px;
  font-style: italic;
  font-size: 18px;
}
#usr{
  border-color: rgb(192,192,192);
  max-width: 350px;
  margin: auto;
}

  </style>
</head>
<body>
  <h1 align='center' class="rem">RemindMe</h1>
  <div class="card bg-light text-dark">
    <h1 class="title">Login</h1>
    <div class="card-body">
      <form action ="LoginServlet" method="post">
      <div class="form-group">
        <input type="text" placeholder="email" class="form-control" autocomplete="on" id="usr" name="username" required>
      </div>
      <div class="form-group">
        <input type="password" placeholder="password" class="form-control" id="usr" name="password" required>
      </div>
      <button type="submit" class="btn btn-primary">Login</button><br>
    </form>
    
    Not a member?<a href="<%=request.getContextPath()%>/Registration.jsp">Sign up</a>
  </div>
  </div>
</body>
</html>