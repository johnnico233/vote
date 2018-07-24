$(function(){
    setLeftBlockAnimation();
    setOverview();
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

function translateTime(ele){
    var text=ele.html();
    var date=new Date(text);
    var dateFormat=date.getFullYear()+"-"+(date.getMonth()>=10?"":"0")+date.getMonth()+"-"+
        (date.getDay()>=10?"":"0")+date.getDay();
    ele.html(dateFormat);
}