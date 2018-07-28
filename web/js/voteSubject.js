$(function(){
    voteOperationEvent();
    initialize();
    voteUpload();
    uploadMessage();
})
function voteOperationEvent(){
    $("input[name='vote_option']").on("click",function(){
        $("#confirm-vote").removeAttr("disabled");
    })
}
function initialize(){
    var messageCount=parseInt($("#messageCount").html());
    var step=parseInt($("#step").html());
    console.log(messageCount+"\t"+step);
    var total=parseInt(((messageCount-1)/step))+1;
    $("#nextIndex").html(total);
    var index=parseInt($("#idx").html());
    var topicId=parseInt($("#topicId").html());
    console.log(index);
    if(index<=1){
        console.log("index<=1");
        $("#preBtn").attr("disabled",true);
    }else
        $("#preBtn").on("click",function(){
            window.location.href="/"+$("#web-local").html()+"/voteSubject/"+topicId+"?idx="+(parseInt(index-1));
        });
    if(index>=total){
        console.log("index>=total");
        $("#nextBtn").attr("disabled",true);
    }else
        $("#nextBtn").on("click",function(){
            window.location.href="/"+$("#web-local").html()+"/voteSubject/"+topicId+"?idx="+(parseInt(index+1));
        });
    $(".skip-block > .btn-success").on("click",function(){
        skipEvent(total,topicId);
    })
    $("#index").on("keydown",function(e){
        if(e.keyCode==13)
            skipEvent(total,topicId);
    })

}
function skipEvent(total,topicId){
    var idx=$("#index").val();
    if(idx<=0)
        window.location.href="/"+$("#web-local").html()+"/voteSubject/"+topicId+"?idx=1";
    else if(idx>total)
        window.location.href="/"+$("#web-local").html()+"/voteSubject/"+topicId+"?idx="+total;
    else
        window.location.href="/"+$("#web-local").html()+"/voteSubject/"+topicId+"?idx="+idx;
}
//需要修改userId
function voteUpload(){
    var voteType=$(".vote_type:nth-of-type(2)").html();
    var maxVoteCount=parseInt($("#vote_max_count").html());
    if(voteType=='多选'){
        $("#confirm-vote").popover({
            title:"投票超过限制"
        })
    }
    $("#confirm-vote").on("click",function(){
        if(voteType=='多选')
            $("#confirm-vote").popover('hide');
        var data=[];
        var topicId=$("#topicId").html();
        $.each($("input[name='vote_option']:checked"),function(idx,element){
            var itemId=$(element).val();
            data.push(itemId);
        });
        var url="/"+$("#web-local").html()+"/voteSubject/sendVote";
        data={userId:2,voteOptions:data,topicId:parseInt(topicId)};
        sendData2Server(url,data,true,null)
    });
    var clickCount=0;
    if(voteType=='多选') {
        $("input[name='vote_option']").on("click", function () {
            clickCount = $("input[name='vote_option']:checked").length;
            if (clickCount > maxVoteCount) {
                $("#confirm-vote").popover('show');
                setTimeout(function () {
                    $("#confirm-vote").popover('hide');
                }, 2500);
                $("#confirm-vote").attr("disabled",true);
            }else
                $("#confirm-vote").attr("disabled",false);
        });
    };
}
//发表留言事件触发,需要修改userId
function uploadMessage(){
    var userId=-1;
    if($("#userID").length>0)
        userId=$("#userID").html();
    var url="/"+$("#web-local").html()+"/voteSubject/sendVoteMessage";
    var skipUrl="/"+$("#web-local").html()+"/voteSubject/"+$("#topicId").html();
    if($("#message-id").length>0){
        url="/"+$("#web-local").html()+"/voteSubject/updateVoteMessage";
        if($("#returnPage").length>0)
            skipUrl="/"+$("#web-local").html()+"/user/voteMessageManage";
        else
            skipUrl="/"+$("#web-local").html()+"/user/myVoteMessage";
    }
    $(".send-message >textarea").on(
        {"keydown":function(){
            setSendButtonValid();
        },"blur":function(){
            setSendButtonValid();
        }
        });
    $("#send-message-btn").on("click",function(){
        console.log("userId");
        var content=$.trim($(".send-message >textarea").val());
        var data={"content":content,"voteId":$("#topicId").html(),"userId":userId};
        if($("#message-id").length>0)
            data['id']=parseInt($("#message-id").html());
        console.log(data);
        sendData2Server(url,data,false,skipUrl);
    })
    $(".send-message >textarea").on("keydown",function(e){
        if(e.ctrlKey&&e.keyCode==13){
            var content=$.trim($(".send-message >textarea").val());
            var data={"content":content,"voteId":$("#topicId").html(),"userId":userId};
            if($("#message-id").length>0)
                data['id']=parseInt($("#message-id").html());
            sendData2Server(url,data,false,skipUrl);
        }
    })
}
//发表留言的按钮设置
function setSendButtonValid(){
    var content=$.trim($(".send-message >textarea").val());
    if(content.length<=0||content.length>=250){
        console.log(content);
        $("#send-message-btn").attr("disabled",true);
    }else
        $("#send-message-btn").attr("disabled",false);
}
function sendData2Server(url,data,isReload,skipUrl){
    $.ajax({
        url:url,
        type:"post",
        dataType:"json",
        data:JSON.stringify(data),
        headers:{'Content-type':'application/json;charset=utf-8'}
    }).done(function(json){
        console.log(json);
        if(isReload){
            if(json.resultCode=='SUCCESS') {
                alert(json.resultText);
                window.location.reload();
            }
        }
        else{
            if(json.resultCode!=undefined)
                if(json.resultCode=='SUCCESS'){
                alert(json.resultText);
                window.location.href=skipUrl;
            }
            if(json.result.resultCode=='SUCCESS'){
                alert(json.result.resultText);
                window.location.href=skipUrl+"?idx="+json.idx;
            }

        }
    }).fail(function(xhr,status,errorThrown){
        alert("there is something wrong with server");
        isValid=false;
    });
}
