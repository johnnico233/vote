var initCount=2;
$(function(){
    voteOptionOperation();
});

function voteOptionOperation(){
    $("#addOption").on("click",function(){
        $(".vote-block").append($("<p class=\"vote_option\"><span>"+("选项"+(++initCount))+"</span>" +
            "<input class=\"form-control\" type=\"text\" name=\"vote_option\"></p>"))
    });
    $("#delOption").on("click",function () {
        if(initCount>2){
            initCount--;
            $(".vote-block>p:last-of-type").remove();
        }
    });
}