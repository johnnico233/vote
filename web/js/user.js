var userId=parseInt($("#user_id_recorder").html());
var filename;
var isValid=[true,true,true,true,true];
$("#user_id_recorder").remove();
$(function(){
    setLeftBlockAnimation();
    setOverview();
    setUserInfo();
    setFollowList();
    setUserManageList();
    setUserDustbin();
    setMyVote();
    setVoteManage();
    setVoteDustbin();
    setMyMessage();
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
    if($("#overview-lastlogin").length>0){
        translateTime($("#overview-lastlogin"));
        $.each($(".overview-block>.overview-follow tr td:nth-of-type(n+3)"),function(idx,ele){
            translateTime($(ele))
        })
    }
}

function setUserInfo(){
    if($("#userInfo-block").length>0){
        filename=$("#user-info-show > div >img").attr("src");
        filename=filename.substring(filename.lastIndexOf("/")+1);
        console.log(filename);
        translateTime($("#user-info-show  >div >div p:nth-of-type(3) span:last-of-type"));
        translateTime($("#user-info-show  >div:nth-of-type(2) p:nth-of-type(4) span:last-of-type"));
        $("#user-info-edit input[name='birth']").val(
            $("#user-info-show  >div:nth-of-type(2) p:nth-of-type(4) span:last-of-type").html());
        $("#btn-edit-user-info").on("click",function(){
            $("#user-info-show").css("display","none");
            $("#user-info-edit").css("display","block");
        });
        $("#user-info-edit input[name='username']").on("blur",function(){
            var name=$(this).val();
            if($.trim(name).length>=4){
                var data={"username":name,"userId":userId};
                sendData2Server("/"+$("#web-local").html()+"/user/checkNameExistExceptMe",data,function(json){
                    if(json.resultCode!='SUCCESS'){
                        $("p.error-hint").eq(0).html(json.resultText);
                        $("p.error-hint").eq(0).css("display","block");
                        isValid[0]=false;
                    }else{
                        $("p.error-hint").eq(0).css("display","none");
                        isValid[0]=true;
                    }
                })
            }else{
                $("p.error-hint").eq(0).css("display","block");
                isValid[0]=false;
            }
        });
        $("#user-info-edit input[name='email']").on("blur",function(){
            var email=$.trim($(this).val());
            var pattern=/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
            var result=pattern.test(email);
            if(result){
                var data={"email":email,"userId":userId};
                sendData2Server("/"+$("#web-local").html()+"/user/checkEmailExistExceptMe",data,function(json){
                    console.log(json);
                    if(json.resultCode!='SUCCESS'){
                        $("p.error-hint").eq(1).html(json.resultText);
                        $("p.error-hint").eq(1).css("display","block");
                        isValid[1]=false;
                    }else{
                        $("p.error-hint").eq(1).css("display","none");
                        isValid[1]=true;
                    }
                })
            }else{
                $("p.error-hint").eq(1).css("display","block");
                isValid[1]=false;
            }
        });
        $("#user-info-edit input[name='phone']").on("blur",function(){
            var phone=$(this).val();
            if($.trim(phone).length>0&&$.trim(phone).length<=13){
                var data={"phone":$.trim(phone),"userId":userId};
                sendData2Server("/"+$("#web-local").html()+"/user/checkPhoneExistExceptMe",data,function(json){
                    if(json.resultCode!='SUCCESS'){
                        $("p.error-hint").eq(2).html(json.resultText);
                        $("p.error-hint").eq(2).css("display","block");
                        isValid[2]=false;
                    }else{
                        $("p.error-hint").eq(2).css("display","none");
                        isValid[2]=true;
                    }
                })
            }else{
                $("p.error-hint").eq(2).css("display","block");
                isValid[2]=false;
            }
        });
        $("#user-info-edit input[name='birth']").on("blur",function(){
            var text=$(this).val();
            if($.trim(text)==""){
                $("p.error-hint").eq(3).css("display","block");
                isValid[3]=false;
            }else{
                $("p.error-hint").eq(3).css("display","none");
                isValid[3]=true;
            }
        });
        $("#user-info-edit textarea[name='whatsUp']").on("blur",function(){
            var text=$(this).val();
            if($.trim(text).length>32){
                $("p.error-hint").eq(4).css("display","block");
                isValid[4]=false;
            }else{
                $("p.error-hint").eq(4).css("display","none");
                isValid[4]=true;
            }
        });
        //上传用户资料
        $("#btn-edit-user-info-fin").on("click",function(){
            var sendable=true;
            $.each(isValid,function(idx,ele){
                if(ele!=true)
                    sendable=false;
            })
            console.log(isValid);
            if(sendable){
                var uid=$("#manage-modify-user-id").length>0?$("#manage-modify-user-id").html():userId;
                var data={userId:uid,username:$("input[name='username']").val(),sex:$("input[name='sex']:checked").val(),
                    email:$("input[name='email']").val(),phone:$("input[name='phone']").val(),
                    birth:$("input[name='birth']").val(),whatsUp:$("textarea[name='whatsUp']").val(),avatar:filename};
                var url="/"+$("#web-local").html()+"/user/"+userId+"/updateUserInfo";
                sendData2Server("/"+$("#web-local").html()+"/user/checkInfoExceptMe",data,function(json){
                    if(json.resultCode=='SUCCESS'){
                        sendData2Server(url,data,function(json){
                            console.log(json);
                            alert(json.resultText);
                            if(json.resultCode=="SUCCESS"){
                                if($("#manage-return-page").length>0){
                                    window.location.href="/"+$("#web-local").html()+$("#manage-return-page").html();
                                }else{
                                    window.location.reload();
                                }
                            }
                        });
                    }else if(json.resultCode=='USERNAME_EXIST'){
                        isValid[0]=false;
                        $("p.error-hint").eq(0).html(json.resultText);
                        $("p.error-hint").eq(0).css("display","block");
                    }else if(json.resultCode=="EMAIL_EXIST"){
                        isValid[1]=false;
                        $("p.error-hint").eq(1).html(json.resultText);
                        $("p.error-hint").eq(1).css("display","block");
                    }else{
                        isValid[2]=false;
                        $("p.error-hint").eq(2).html(json.resultText);
                        $("p.error-hint").eq(2).css("display","block");
                    }
                })
            }
        })

        //上传图片
        $("#send-user-photo").on("click",function(){
            var file=$("#user-photo-file");
            sendUserPhoto(file,"/"+$("#web-local").html()+"/user/"+userId+"/uploadFile");
        })
    }
}

//关注列表
function setFollowList(){
    if($("#followList > table").length>0){
        $.each($("#followList tr:nth-of-type(n+2) td:nth-of-type(3)"),function(idx,elem){
            translateTime($(elem));
        });
        $.each($("#followList tr:nth-of-type(n+2) td:nth-of-type(4)"),function(idx,elem){
            translateTime($(elem));
        });
        //跳转按钮
        var url="/"+$("#web-local").html()+"/user/"+userId+"/followList";
        var preBtn=$("#followList .skip-block button:first-of-type");
        var nextBtn=$("#followList .skip-block button:last-of-type");
        var idx=parseInt($("#followList .skip-block span:first-of-type").html());
        var total=parseInt($("#followList .skip-block span:last-of-type").html());
        var input=$("#followList .skip-block input[type='number']");
        var inputBtn=$("#followList .skip-block button:nth-of-type(2)");
        setSKipButton(preBtn,nextBtn,input,inputBtn,idx,total,url);
        $.each($("#followList tr:nth-of-type(n+2)"),function(idx,elem){
            var username=$(elem).find("td:nth-of-type(2)>a").html();
            var button=$(elem).find("button");
            button.on("click",function(){
                var url='/'+$("#web-local").html()+"/user/follow/delete";
                var data={'followUserName':username,'userId':userId};
                sendData2Server(url,data,function(json){
                    if(json.resultCode=='SUCCESS'){
                        alert("取消关注成功");
                        window.location='/'+$("#web-local").html()+"/user/"+userId+"/followList";
                    }else{
                        alert("服务器出现错误,无法取消关注,即将刷新页面");
                    }
                })
            })
        })
    }
}
//用户管理列表
function setUserManageList(){
    if($(".user-manager").length>0){
        $.each($(".user-manager tr:nth-of-type(n+2) >td:nth-of-type(4)"),function(idx,elem){
            translateTime($(elem));
        });
        var url="/"+$("#web-local").html()+"/user/manage";
        var preBtn=$(".user-manager .skip-block button:first-of-type");
        var nextBtn=$(".user-manager .skip-block button:last-of-type");
        var idx=parseInt($(".user-manager .skip-block span:first-of-type").html());
        var total=parseInt($(".user-manager .skip-block span:last-of-type").html());
        var input=$(".user-manager .skip-block input[type='number']");
        var inputBtn=$(".user-manager .skip-block button:nth-of-type(2)");
        setSKipButton(preBtn,nextBtn,input,inputBtn,idx,total,url);
        $.each($(".user-manager tr:nth-of-type(n+2)"),function(idx,elem){
            var account=$(elem).find("td:nth-of-type(2)>a").html();
            var del=$(elem).find("td:nth-of-type(6)>button");
            var modify=$(elem).find("td:nth-of-type(5)>button");
            var id=$(elem).find(".user-id-hide").html();
            del.on("click",function(){
                var url='/'+$("#web-local").html()+"/user/manage/ban";
                var data={"account":account};
                sendData2Server(url,data,function(json){
                    if(json.resultCode=='SUCCESS'){
                        alert("封停账户成功");
                        window.location.reload();
                    }else{
                        alert("服务器出现错误,无法封停用户,即将刷新页面");
                    }
                })
            })
            modify.on("click",function(){
                window.location.href="/"+$("#web-local").html()+"/user/manage/modify?uid="+id;
            })
        })
    }
}
//用户回收箱设置
function setUserDustbin(){
    if($("#dustbinUser").length>0){
        $.each( $("#dustbinUser tr:nth-of-type(n+2)"),function(idx,elem){
            var time=$(elem).find("td:nth-of-type(4)");
            var btn=$(elem).find("button");
            var id=$(elem).find("td:last-of-type").html();
            translateTimeWithConcrete($(time));
            btn.on("click",function () {
                var data={"userId":id};
                var url="/"+$("#web-local").html()+"/user/dustbinUser/recover";
                sendData2Server(url,data,function(json){
                    if(json.resultCode=='SUCCESS'){
                        alert(json.resultText);
                        window.location.reload();
                    }
                });
            })
        });
        var url="/"+$("#web-local").html()+"/user/dustbinUser";
        var preBtn=$(".skip-block button:first-of-type");
        var nextBtn=$(".skip-block button:last-of-type");
        var idx=parseInt($("#dustbinUser .skip-block span:first-of-type").html());
        var total=parseInt($("#dustbinUser .skip-block span:last-of-type").html());
        var input=$(".skip-block input[type='number']");
        var inputBtn=$(".skip-block button:nth-of-type(2)");
        setSKipButton(preBtn,nextBtn,input,inputBtn,idx,total,url);

    }
}
//我的投票设置
function setMyVote(){
    if($(".user-message").length>0){
        $.each($(".user-message tr:nth-of-type(n+2)"),function(idx,elem){
            var time=$(elem).find("td:nth-of-type(3)");
            translateTimeWithConcrete($(time));
            var modifyBtn=$(elem).find("td:nth-of-type(4) button");
            var vopticId=$(elem).find("td:nth-of-type(6)").html();
            var messageId=$(elem).find("td:nth-of-type(7)").html();
            modifyBtn.on("click",function(){
                window.location.href="/"+$("#web-local").html()+"/user/voteModify?mes="+messageId+"&topic="+vopticId+"#message-input";
            })
        });
        var url="/"+$("#web-local").html()+"/user/myVotes";
        var preBtn=$(".skip-block button:first-of-type");
        var nextBtn=$(".skip-block button:last-of-type");
        var idx=parseInt($(".user-message .skip-block span:first-of-type").html());
        var total=parseInt($(".user-message .skip-block span:last-of-type").html());
        var input=$(".skip-block input[type='number']");
        var inputBtn=$(".skip-block button:nth-of-type(2)");
        setSKipButton(preBtn,nextBtn,input,inputBtn,idx,total,url);
    }
}
//我的留言设置
function setMyMessage(){
    if($(".user-message").length>0){
        var url="/"+$("#web-local").html()+"/user/myVoteMessage";
        var preBtn=$(".skip-block button:first-of-type");
        var nextBtn=$(".skip-block button:last-of-type");
        var idx=parseInt($(".user-message .skip-block span:first-of-type").html());
        var total=parseInt($(".user-message .skip-block span:last-of-type").html());
        var input=$(".skip-block input[type='number']");
        var inputBtn=$(".skip-block button:nth-of-type(2)");
        setSKipButton(preBtn,nextBtn,input,inputBtn,idx,total,url);
    }
}


//投票管理设置
function setVoteManage(){
    if($(".vote-manager").length>0){
        $.each($(".vote-manager tr:nth-of-type(n+2)"),function(idx,elem){
            var time=$(elem).find("td:nth-of-type(4)");
            translateTimeWithConcrete(time);
            var delBtn=$(elem).find("td:last-of-type button");
            var modifyBtn=$(elem).find("td:nth-last-of-type(2) button");
            var voteId=$(elem).find("td:first-of-type").html();
            voteId=voteId.substring(1);
            delBtn.on("click",function(){
                var url='/'+$("#web-local").html()+'/user/voteManage/ban';
                var data={"id":voteId};
                sendData2Server(url,data,function(json){
                    alert(json.resultText);
                    if(json.resultCode=="SUCCESS"){
                        window.location.href='/'+$("#web-local").html()+'/user/voteManage'
                    }
                })
            })
            modifyBtn.on("click",function(){
                var url='/'+$("#web-local").html()+'/user/voteManage/modify/'+voteId;
                window.location.href=url;
            })
        });
        var url="/"+$("#web-local").html()+"/user/voteManage";
        var preBtn=$(".skip-block button:first-of-type");
        var nextBtn=$(".skip-block button:last-of-type");
        var idx=parseInt($(".vote-manager .skip-block span:first-of-type").html());
        var total=parseInt($(".vote-manager .skip-block span:last-of-type").html());
        var input=$(".skip-block input[type='number']");
        var inputBtn=$(".skip-block button:nth-of-type(2)");
        setSKipButton(preBtn,nextBtn,input,inputBtn,idx,total,url);

    }
}
//投票回收站设置
function setVoteDustbin(){
    if($("#dustbinVote").length>0){
        $.each($("#dustbinVote tr:nth-of-type(n+2)"),function(idx,elem){
            var time=$(elem).find("td:nth-of-type(3)");
            var recoverBtn=$(elem).find("td:nth-of-type(4) button");
            var voteId=$(elem).find("td:first-of-type").html();
            voteId=voteId.substring(1);
            var banId=$(elem).find("td:last-of-type").html();
            translateTimeWithConcrete(time);
            recoverBtn.on("click",function(){
                var url="/"+$("#web-local").html()+"/user/dustbinVote/recover";
                var data={"banId":banId,"voteId":voteId};
                sendData2Server(url,data,function(json){
                    alert(json.resultText);
                    if(json.resultCode=="SUCCESS")
                        window.location.href="/"+$("#web-local").html()+"/user/dustbinVote";
                })
            })
        });
        var url="/"+$("#web-local").html()+"/user/dustbinVote";
        var preBtn=$(".skip-block button:first-of-type");
        var nextBtn=$(".skip-block button:last-of-type");
        var idx=parseInt($("#dustbinVote .skip-block span:first-of-type").html());
        var total=parseInt($("#dustbinVote .skip-block span:last-of-type").html());
        var input=$(".skip-block input[type='number']");
        var inputBtn=$(".skip-block button:nth-of-type(2)");
        setSKipButton(preBtn,nextBtn,input,inputBtn,idx,total,url);
    }
}

function translateTime(ele){
    var text=ele.html();
    if(text!=""){
        var date=new Date(text+"GMT+0800");
        var dateFormat=date.getFullYear()+"-"+(date.getMonth()>=9?"":"0")+(date.getMonth()+1)+"-"+
            (date.getDate()>=10?"":"0")+date.getDate();
        ele.html(dateFormat);
    }else{
        ele.html("无");
    }
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
            $("#user-info-edit >div >div:last-of-type img").attr("src",
                "/"+$("#web-local").html()+"/avatar/tmp/"+filename);
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
        dataType:"JSON",
        headers:{'Content-type':'application/json;charset=utf-8'}
    }).done(func).fail(function(xhr,status,errorThrown){
        alert("there is something wrong with server");
        isValid=false;
    });
}
//跳转按钮设置,直接共用
function setSKipButton(preBtn,nextBtn,indexInput,indexBtn,index,total,url){
    if(index<=1)
        preBtn.attr("disabled",true);
    else
        preBtn.on("click",function(){
            window.location.href=url+"?idx="+(index-1);
        });
    if(index>=total)
        nextBtn.attr("disabled",true);
    else
        nextBtn.on("click",function(){
            window.location.href=url+"?idx="+(index+1);
        });
    indexBtn.on("click",function(){
        skipEvent(indexInput.val(),total,url);
    });
    indexInput.on("keydown",function(e){
        if(e.keyCode==13)
            skipEvent(indexInput.val(),total,url);
    })
}
function skipEvent(idx,total,url){
    if(idx<=0)
        window.location.href=url+"?idx=1";
    else if(idx>total)
        window.location.href=url+"?idx="+total;
    else
        window.location.href=url+"?idx="+idx;
}
function translateTimeWithConcrete(ele){
    var text=ele.html();
    if(text!=""){
        var date=new Date(text+"GMT+0800");
        var dateFormat=date.getFullYear()+"-"+(date.getMonth()>=9?"":"0")+(date.getMonth()+1)+"-"+
            (date.getDate()>=10?"":"0")+date.getUTCDate()+" "+(date.getHours()>=10?"":"0")+date.getHours()+":"+
            (date.getUTCMinutes()>=10?"":"0")+date.getUTCMinutes()+":"+(date.getUTCSeconds()>=10?"":"0")
            +date.getUTCSeconds();
        ele.html(dateFormat);
    }else{
        ele.html("无");
    }
}