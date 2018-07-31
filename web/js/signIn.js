$(function(){
    $("#signIn").on("click",function(){
        signIn();
    })
    $("#inputPassword").on("keydown",function(e){
        if(e.keyCode==13)
            signIn();
    })
})
function signIn(){
    var account=$("#inputAccount").val();
    var pwd=$("#inputPassword").val();
    var data={"account":account,"password":pwd,"remember":$("input[value='remember-me']").is(":checked")};
    var local="/"+$("#web-local").html()+"/signIn";
    $.ajax({
        url:local,
        type:"post",
        data:JSON.stringify(data),
        dataType:"json",
        headers:{'Content-type':'application/json;charset=utf-8'}
    }).done(function(json){
        console.log(json);
        if(json.resultCode=='USER_IS_BANNED'){
            alert(json.resultText);
        } else if(json.resultCode!="SUCCESS"){
            $(".input-hint").css("display","block");
            $(".input-hint").html(json.resultText);
        }
        else{
            window.location.href="/"+$("#web-local").html()+"/user/"+json.additionInfo;
        }
    }).fail(function(xhr,status,errorThrown){
        alert("there is something wrong with server");
    });
}