$(function(){
    initialize();
});

function initialize(){
    var url="/"+$("#web-local").html()+"/";
    var preBtn=$(".skip-block button:first-of-type");
    var nextBtn=$(".skip-block button:last-of-type");
    var idx=parseInt($(".skip-block span:first-of-type").html());
    var total=parseInt($(".skip-block span:last-of-type").html());
    var input=$(".skip-block input[type='number']");
    var inputBtn=$(".skip-block button:nth-of-type(2)");
    setSKipButton(preBtn,nextBtn,input,inputBtn,idx,total,url);
    $.each($("tr"),function(idx,elem){
        var timeBlock=$(elem).find(" p:last-of-type");
        var startTime=$(timeBlock).find("span:first-of-type");
        var endTime=$(timeBlock).find("span:nth-of-type(2)");
        translateTimeWithConcrete(startTime);
        translateTimeWithConcrete(endTime);
        startTime.html("开始时间:"+startTime.html());
        endTime.html("结束时间:"+endTime.html());
        var id=$(timeBlock).find("span:last-of-type").html();
        $(elem).on("click",function(){
            window.location.href="/"+$("#web-local").html()+"/voteSubject/"+id;
        });
    });
    $("h1 button").on("click",function(){
        window.location.href="/"+$("#web-local").html()+"/voteUpload";
    })
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


function skipEvent(idx,total,url){
    var addInfo=($("#searchContent").length>0?"&title="+$("#searchContent").html():"");
    if(idx<=0)
        window.location.href=url+"?idx=1"+addInfo;
    else if(idx>total)
        window.location.href=url+"?idx="+total+addInfo;
    else
        window.location.href=url+"?idx="+idx+addInfo;
}

//跳转按钮设置,直接共用
function setSKipButton(preBtn,nextBtn,indexInput,indexBtn,index,total,url){
    var addInfo=($("#searchContent").length>0?"&title="+$("#searchContent").html().substring(5):"");
    if(index<=1)
        preBtn.attr("disabled",true);
    else
        preBtn.on("click",function(){
            window.location.href=url+"?idx="+(index-1)+addInfo;
        });
    if(index>=total)
        nextBtn.attr("disabled",true);
    else
        nextBtn.on("click",function(){
            window.location.href=url+"?idx="+(index+1)+addInfo;
        });
    indexBtn.on("click",function(){
        skipEvent(indexInput.val(),total,url);
    });
    indexInput.on("keydown",function(e){
        if(e.keyCode==13)
            skipEvent(indexInput.val(),total,url);
    })
}