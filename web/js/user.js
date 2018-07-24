var userId=parseInt($("#user_id_recorder").html());
$("#user_id_recorder").remove();
var filename=null;
$(function(){
    setLeftBlockAnimation();
    setOverview();
    setUserInfo();
});

function setLeftBlockAnimation(){
    setSubBlockAnimation($("#user-info-block"));
    setSubBlockAnimation($("#vote-info-block"));
    setSubBlockAnimation($("#message-info-block"));
}

function setSubBlockAnimation(element){
    var shown=true;
    var div=element.find("div");
    var height=div.css("height");
    console.log(height);
    element.find("p").on("click",function(){
        var operation=shown?"-=":"+=";
        if(!shown)
            div.css("display","block");
        div.animate({
            height:operation+height,
        },500,function(){
            if(shown)
                div.css("display","none");
            shown=!shown;
        });

    });
}

function setOverview(){
    if($("#overview-lastlogin")!=undefined){
        translateTime($("#overview-lastlogin"));
        $.each($(".overview-block>.overview-follow tr td:nth-of-type(n+3)"),function(idx,ele){
            translateTime($(ele))
        })
    }
}

function setUserInfo(){
    if($("#user-info-show")!=undefined){
        translateTime($("#user-info-show  >div >div p:nth-of-type(3) span:last-of-type"));
        translateTime($("#user-info-show  >div:nth-of-type(2) p:nth-of-type(4) span:last-of-type"));
        $("#user-info-edit input[name='birth']").val(
            $("#user-info-show  >div:nth-of-type(2) p:nth-of-type(4) span:last-of-type").html());
        $("#btn-edit-user-info").on("click",function(){
            $("#user-info-show").css("display","none");
            $("#user-info-edit").css("display","block");
        });
        //上传用户资料
        $("#btn-edit-user-info-fin").on("click",function(){
            var data={userId:userId,username:$("input[name='username']").val(),sex:$("input[name='sex']:checked").val(),
                      email:$("input[name='email']").val(),phone:$("input[name='phone']").val(),
                      birth:$("input[name='birth']").val(),whatsUp:$("textarea[name='whatsUp']").val(),avatar:filename};
            var url="/"+$("#web-local").html()+"/user/"+userId+"/updateUserInfo";
            sendData2Server(url,data,function(json){
                console.log(json);
            });
            // $("#user-info-show").css("display","block");
            // $("#user-info-edit").css("display","none");
        })
        //上传图片
        $("#send-user-photo").on("click",function(){
            var file=$("#user-photo-file");
            sendUserPhoto(file,"/"+$("#web-local").html()+"/user/"+userId+"/updateUserInfo");
        })
    }
}

function translateTime(ele){
    var text=ele.html();
    var date=new Date(text);
    var dateFormat=date.getFullYear()+"-"+(date.getMonth()>=10?"":"0")+date.getMonth()+"-"+
        (date.getDay()>=10?"":"0")+date.getDay();
    ele.html(dateFormat);
}

function sendUserPhoto(file,url){
    var formData=new FormData();
    formData.append("file",file[0].files[0]);
    $.ajax({
        url:url,
        data:formData,
        processData: false,
        contentType: false,
        dataType:"json",
        type:"post"
    }).done(function(json){
        console.log(json);
        if(json.resultCode=='SUCCESS'){
            $("#user-info-edit >div >div:last-of-type p:nth-of-type(n+2)").css("display","none");
            $("#user-info-edit >div >div:last-of-type p:first-of-type").css("display","block");
            filename=json.additionInfo;
        }else{
            $("#user-info-edit >div >div:last-of-type p:last-of-type").css("display","block");
            $("#user-info-edit >div >div:last-of-type p:nth-of-type(2)").css("display","none");
            $("#user-info-edit >div >div:last-of-type p:first-of-type").css("display","none");
            $("#user-info-edit >div >div:last-of-type p:last-of-type").html(json.resultText);
        }
    }).fail(function(xhr,status,errorThrown){
        alert("there is something wrong with server");
        isValid=false;
    });
}

function sendData2Server(url,data,func){
    $.ajax({
        data:JSON.stringify(data),
        url:url,
        type:"POST",
        dataType:"JSON"
    }).done(func).fail(function(xhr,status,errorThrown){
        alert("there is something wrong with server");
        isValid=false;
    });
}