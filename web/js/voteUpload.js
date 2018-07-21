var initCount=2;
var isSingle=true;
$(function(){
    voteOptionOperation();
    setMaxSizeHint();
    setDate();
    $("#send-vote").on("click",function(){checkAndSend();});
});

function voteOptionOperation(){
    $("#delOption").popover({title:"投票选项不能低于2个！"});
    handlePopoverEvent($(".main-container input[name='title']")[0],"标题长度多于0并且少于32个字!",32);
    handlePopoverEvent($(".main-container textarea")[0],"内容长度多于0并且少于250个字!",250);
    $.each($("input[name='vote_option']"),function(index,element){
        handlePopoverEvent(element,"投票选项长度为0到16",16);
    });
    $("#addOption").on("click",function(){
        $(".vote-block").append($("<p class=\"vote_option\"><span>"+("选项"+(++initCount))+"</span>" +
            "<input class=\"form-control\" type=\"text\" name=\"vote_option\" style='margin-left: 0.5%'></p>"));
        handlePopoverEvent($("input[name='vote_option']")[initCount-1],"投票选项长度为0到16",16);
    });
    $("#delOption").on("click",function () {
        if(initCount>2){
            initCount--;
            $(".vote-block>p:last-of-type").remove();
            $("#delOption").popover("hide");
        }else{
            $("#delOption").popover("show");
            setTimeout(function(){
                $("#delOption").popover("hide");
            },3000);
        }
    });
    $("input[value='single']").on('click',function(){
        $("p#max_count").css("display","none");
        isSingle=true;
    })
    $("input[value='multi']").on('click',function(){
        $("p#max_count").css("display","block");
        isSingle=false;
    })
}
function handlePopoverEvent(element,title,maxLength){
    element=$(element);
    element.popover({
        title:title,
        placement:'bottom'
    });
    element.on("blur",function(){
        var length=$.trim(element.val()).length;
        if(length<=0||length>maxLength){
            element.popover('show');
            setTimeout(function(){
                element.popover('hide');
            },3000);
        }else
            element.popover('hide');
    });
}
function setMaxSizeHint(){
    var element=$("input[type='number']");
    element.popover({
        title:"多选最大数为2~选项总数",
        placement:'bottom'
    });
    element.on("blur",function(){
        var length=$.trim(element.val()).length;
        if(length>0){
            if(element.val()<2||element.val()>initCount){
                element.popover('show');
                setTimeout(function(){
                    element.popover('hide');
                },3000);
            }else
                element.popover('hide');
        }else{
            element.popover('show');
            setTimeout(function(){
                element.popover('hide');
            },3000);
        }
    });
}
function checkAndSend(){
    var isValid=true;
    var maxLength=[32,250,16];
    $.each($("input[type='text']"),function(index,item){
        item=$(item);
        var length=$.trim(item.val());
        if(index<maxLength.length){
            if(length>maxLength[index]||length<=0){
                item.popover('show');
                setTimeout(function(){
                    item.popover('hide');
                },3000);
                isValid=false;
            }else{
                item.popover('hide');
            }
        }else{
            if(length>maxLength[2]||length<=0){
                item.popover('show');
                setTimeout(function(){
                    item.popover('hide');
                },3000);
                isValid=false;
            }else{
                item.popover('hide');
            }
        }
    });
    var textLength=$.trim($(".main-container textarea").val()).length;
    if(textLength<=0||textLength>250){
        $(".main-container textarea").popover('show');
        setTimeout(function(){
            $(".main-container textarea").popover('hide');
        },3000);
        isValid=false;
    }
    if(!isSingle){
        var maxSize=$("input[type='number']").val();
        if(maxSize.length==0){
            $("input[type='number']").popover('show');
            setTimeout(function(){
                $("input[type='number']").popover('hide');
            },3000);
            isValid=false;
        }else{
            if(maxSize<2||maxSize>initCount){
                $("input[type='number']").popover('show');
                setTimeout(function(){
                    $("input[type='number']").popover('hide');
                },3000);
                isValid=false;
            }else
                $("input[type='number']").popover('hide');
        }
    }
    var startTime=$("input[name='start-time']");
    var endTime=$("input[name='end-time']");
    var length=$.trim(startTime.val()).length;
    if(length<=0||Date.parse(startTime.val())<Date.parse(new Date(new Date(new Date().toLocaleDateString()).getTime()).toDateString())){
        startTime.popover('show');
        setTimeout(function(){
            startTime.popover('hide');
        },3000);
        isValid=false;
    }else
        startTime.popover('hide');
    length=$.trim(endTime.val()).length;
    if(!isValid||length<=0||Date.parse(startTime.val())>Date.parse(endTime.val())){
        endTime.popover('show');
        setTimeout(function(){
            startTime.popover('hide');
        },3000);
        isValid=false;
    }else
        endTime.popover('hide');
    if(isValid)
        send();
}
function setDate(){
    var valid=true;
    var startTime=$("input[name='start-time']");
    var endTime=$("input[name='end-time']");
    startTime.popover({
        title:"开始时间不能比当前时间早",
        placement:'bottom'
    });
    endTime.popover({
        title:"结束时间不能比开始时间早",
        placement:'bottom'
    });
    startTime.on("blur",function(){
        var length=$.trim(startTime.val()).length;
        if(length<=0||Date.parse(startTime.val())<Date.parse(new Date(new Date(new Date().toLocaleDateString()).getTime()).toDateString())){
            startTime.popover('show');
            setTimeout(function(){
                startTime.popover('hide');
            },3000);
            valid=false;
        }else
            startTime.popover('hide');
    });
    endTime.on("blur",function(){
        var length=$.trim(endTime.val()).length;
        if(!valid||length<=0||Date.parse(startTime.val())>Date.parse(endTime.val())){
            endTime.popover('show');
            setTimeout(function(){
                startTime.popover('hide');
            },3000);
        }else
            endTime.popover('hide');
    });
}
function send(){
    var options=[];
    $.each($("input[name='vote_option']"),function(idx,item){
        options.push($(item).val());
    });
    var data={"topic":$.trim($("input[name='title']").val()),"content":$.trim($("textarea[name='content']").val()),
              "isMulti":$("input[name='vote_type']:checked").val()=="multi","totalVoteCount":$("input[type='number']").val(),
              "options":options,"startTime":$("input[name='start-time']").val(),"endTime":$("input[name='end-time']").val(),
              "userId":1};
    console.log(data);
    $.ajax({
        url:"/"+$("#web-local").html()+"/voteUpload/addNewTopic",
        type:"post",
        dataType:"json",
        data:JSON.stringify(data),
        headers:{'Content-type':'application/json;charset=utf-8'}
    }).done(function(json){
        if(json.code=='SUCCESS') {
            alert("发起投票成功，即将进入到对应的投票页面");
            window.location.href="/"+$("#web-local").html()+"/voteSubject/"+json.id;
        } else
            alert("出现了错误！请联系系统管理员")
    }).fail(function(xhr,status,errorThrown){
        alert("there is something wrong with server");
        isValid=false;
    });
}