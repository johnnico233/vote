$(function(){
    $("#btn-login").on("click",function(){
        var account=$.trim($(".form-group input[type='text']").val());
        var password=$.trim($(".form-group input[type='password']").val());
        var local="/"+$("#web-local").html()+"/signIn";
        var data={"account":account,"password":password};
        $.ajax({
            url:local,
            type:"post",
            data:JSON.stringify(data),
            dataType:"json",
            headers:{'Content-type':'application/json;charset=utf-8'}
        }).done(function(json){
            if(json.resultCode!="SUCCESS"){
                $(".input-hint").css("display","block");
                $(".input-hint").html(json.resultText);
            }else{
                alert("登录成功")
            }
        }).fail(function(xhr,status,errorThrown){
            alert("there is something wrong with server");
        });
    })
    $("#btn-sign").on("click",function(){
        window.location.href="/"+$("#web-local").html()+"/signUp"
    })
})