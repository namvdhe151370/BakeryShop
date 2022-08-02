
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<style>
    .register-form {
        width: 340px;
        margin: 50px auto;
        font-size: 15px;
    }
    .register-form form {
        margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .register-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .btn {        
        font-size: 15px;
        font-weight: bold;
    }
    .register {
        padding-top: 20px;
    }
</style>
</head>
<body>
    <div class="register-form">
        <form action="register" method="post">
            <h2 class="text-center">Register New Account</h2>    
            <div class="form-group">
                <input type="text" name="name" class="form-control" placeholder="Enter your Name" required>
            </div>
            <div class="form-group">
                <input type="text" name="email" class="form-control" placeholder="Enter your Email" required>
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="Enter Password" required>
            </div>
            <div class="form-group">
                <input type="password" name="repassword" class="form-control" placeholder="Re-enter Password" required>
            </div>
<!--            <div class="form-group">
                <input type="text" name="mobile" class="form-control" placeholder="Enter your Mobile Number" required>
            </div>-->
            <h6>
                Gender
            </h6>
            <div class="form-gender">
                <input type="radio" name="gender" class="gender-radio" value="1" required>
                <label for="gender">
                    Male
                </label>
                <input type="radio" name="gender" class="gender-radio" value="0" required>
                <label for="gender">
                    Female
                </label>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">Register</button>
            </div>
            <div class="register">
                <p class="text-center"><a href="Login.jsp">Back</a></p>
            </div>
        </form>
    </div>
</body>
</html>