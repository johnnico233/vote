$(function(){
    voteOperationEvent();
})
function voteOperationEvent(){
    $("input[name='vote_option']").on("click",function(){
        console.log("click");
        $("#confirm-vote").removeAttr("disabled");
    })
}