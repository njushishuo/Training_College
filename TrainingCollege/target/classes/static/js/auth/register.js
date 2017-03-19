/**
 * Created by ss14 on 2017/3/19.
 */
function register() {

    //获取注册的对象是学生还是机构
    var role = $( "#rRole option:selected" ).val();
    var username = $("#rusername").val();
    var password = $("#rpassword").val();
    var cpassword = $("#confirm-password").val();

    if(password != cpassword){
        alert("重复输入的密码不一致");
        return;
    }

    var data ={
        role : role ,
        username: username,
        password: password
    }

    $.ajax({
        type: "POST",
        url:"/registeration",
        data: data,
        success: function(data){
            alert("注册成功");
            tranformToLogin();
        },
        traditional:true
    });
}


function tranformToLogin () {
    $("#login-form").delay(100).fadeIn(100);
    $("#register-form").fadeOut(100);
    $('#register-form-link').removeClass('active');
    $(this).addClass('active');
}