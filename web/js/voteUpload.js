var initCount=2;
$(function(){
    voteOptionOperation();
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
            "<input class=\"form-control\" type=\"text\" name=\"vote_option\"></p>"));
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
    })
    $("input[value='multi']").on('click',function(){
        $("p#max_count").css("display","block");
    })
}
function handlePopoverEvent(element,title,maxLength){
    element=$(element);
    console.log(element);
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
function checkAndSend(){

}