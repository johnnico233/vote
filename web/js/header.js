$(function(){
    $("#btn-login").on("click",function(){
        login();
    })
    $("#login-password").on("keydown",function(e){
        if(e.keyCode==13)
            login();
    })
    $("#btn-sign").on("click",function(){
        window.location.href="/"+$("#web-local").html()+"/signUp"
    })
    if($("#login-btn").length==0){
        $("#search-btn").css("margin-top","4px");
    }
    $("#search-btn").on("click",function(){
       var searchContent=$("#search-vote").val();
       searchContent=$.trim(searchContent);
       if(searchContent.length>0){
           window.location.href="/"+$("#web-local").html()+"/?title="+searchContent;
       }
    });
})
function login(){
    var account=$.trim($(".form-group input[type='text']").val());
    var password=$.trim($(".form-group input[type='password']").val());
    var local="/"+$("#web-local").html()+"/signIn";
    var data={"account":account,"password":password,"remember":$("input[name='remember']").is(":checked")};
    $.ajax({
        url:local,
        type:"post",
        data:JSON.stringify(data),
        dataType:"json",
        headers:{'Content-type':'application/json;charset=utf-8'}
    }).done(function(json){
        if(json.resultCode=='USER_IS_BANNED'){
            alert(json.resultText);
        }else if(json.resultCode!="SUCCESS"){
            $(".input-hint").css("display","block");
            $(".input-hint").html(json.resultText);
        }else{
            alert("登录成功");
            window.location.reload();
        }
    }).fail(function(xhr,status,errorThrown){
        alert("there is something wrong with server");
    });
}