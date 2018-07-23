$(function(){
    setLeftBlockAnimation();
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