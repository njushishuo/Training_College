/**
 * Created by ss14 on 2017/3/17.
 */



function clickLogin (e) {
    $("#login-form").delay(100).fadeIn(100);
    $("#register-form").fadeOut(100);
    $('#register-form-link').removeClass('active');
    $(this).addClass('active');
    e.preventDefault();
}


function clickRegister (e) {
    $("#register-form").delay(100).fadeIn(100);
    $("#login-form").fadeOut(100);
    $('#login-form-link').removeClass('active');
    $(this).addClass('active');
    e.preventDefault();
}

$(function() {

    $('#login-form-link').click(clickLogin );
    $('#register-form-link').click(clickRegister);

});



