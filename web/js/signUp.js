var isValid=true;
$(function(){
    addTimeCheck();
    clickHandler();
})
function addTimeCheck(){
    var yearSelect=$("#year");
    //让时间从1900添加到2018
    for(var i=1900;i<=2018;i++){
        var option=$("<option value='"+i+"'>"+i+"</option>");
        yearSelect.append(option);
    }
    var monthSelect=$("#month");
    for(var i=1;i<=12;i++){
        var option=$("<option value='"+i+"'>"+i+"</option>");
        monthSelect.append(option);
    }
    var dayList=getDayList();
    buildDaySelect(dayList[3]);
    yearSelect.on("change",function(){
        var year=yearSelect.val();
        if($("#month").val()==2){
            if(((year%4==0&&year%100!=0)||year%400==0)){
                buildDaySelect(dayList[1]);
            }else{
                buildDaySelect(dayList[0]);
            }
        }
    });
    monthSelect.on("change",function(){
        var daysArray=[[1,3,5,7,8,10,12],[4,6,9,11]];
        if(monthSelect.val()==2){
            var year=$("#year").val();
            if(((year%4==0&&year%100!=0)||year%400==0)){
                buildDaySelect(dayList[1]);
            }else{
                buildDaySelect(dayList[0]);
            }
        }
        $.each(daysArray[0],function(index,item){
            if(item==monthSelect.val())
                buildDaySelect(dayList[3]);
        });
        $.each(daysArray[1],function(index,item){
            if(item==monthSelect.val())
                buildDaySelect(dayList[2]);
        });
    });
}
function getDayList(){
    var dayList=new Array();
    var totalDayList=[28,29,30,31];
    for(var i=0;i<4;i++){
        var tempList=new Array();
        for(var j=1;j<=totalDayList[i];j++){
            tempList.push($("<option value='"+j+"'>"+j+"</option>"));
        }
        dayList.push(tempList);
    }
    return dayList;
}
function buildDaySelect(dayList){
    var daySelect=$("#day");
    if(daySelect.find("option").length!=0){
        $.each(daySelect.find("option"),function(index,item){
            $(item).detach();
        });
    }
    for(var i=0;i<dayList.length;i++)
        daySelect.append(dayList);
}
function clickHandler(){
    //账号输入时进行ajax异步访问服务器账号是否存在
    $("#account").on("blur",function(){
        var account=$("#account").val();
        console.log(account);
        var length=$.trim(account).length;
        if(length>=6&&length<=16)
            sendSth2Server("/"+$("#web-local").html()+"/signUp/accountCheck",{"account":$.trim(account)},$("p:first-of-type >span"),
                "目前该账户可用","账号已经被注册");
        else{
            isValid=false;
            $("p:first-of-type >span").html("账号长度不符合");
            $("p:first-of-type >span").css("visibility","visible");
            $("p:first-of-type >span").css("color","red");
        }
    });
    $("#password").on("blur",function(){
        var length=$("#password").val().length;
        var hint=$("p:nth-of-type(2) >span");
        hint.css("visibility","visible");
        if(length<8||length>16){
            hint.css("color","red");
            if(length>16)
                hint.html("密码长度过长");
            else
                hint.html("密码长度过短")
            isValid=false;
        }else{
            hint.css("color","green");
            hint.html("密码符合要求");
            isValid=true;
        }
    });
    $("#re-password").on("blur",function(){
        var hint=$("p:nth-of-type(3) >span");
        hint.css("visibility","visible");
        if($("#password").val()==$("#re-password").val()){
            hint.css("color","green");
            hint.html("密码一致");
            $("#re-password").css("margin-right","9%");
            isValid=true;
        }else{
            hint.css("color","red");
            hint.html("密码不一致");
            $("#re-password").css("margin-right","7%");
            isValid=true;
        }
    });
    $("#username").on("blur",function(){
        var username=$("#username").val();
        var length=$.trim(username).length;
        var hint=$("p:nth-of-type(4) >span");
        hint.css("visibility","visible");
        if(length<4||length>16){
            hint.css("color","red");
            if(length>16)
                hint.html("昵称长度过长");
            else
                hint.html("昵称长度过短")
            isValid=false;
        }else{
            sendSth2Server("/"+$("#web-local").html()+"/signUp/checkUserName",{"username":$.trim(username)},
                hint, "昵称目前可用","昵称已经存在");
        }
    });
    $("#phone").on("blur",function(){
        var phone=$("#phone").val();
        var hint=$("p:nth-of-type(6) >span");
        var length=$.trim(phone).length;
        hint.css("visibility","visible");
        if(length>0){
            $("#phone").css("margin-right","4%");
            sendSth2Server("/"+$("#web-local").html()+"/signUp/checkUserPhone",{"phone":$.trim(phone)},
                hint, "当前号码可以使用","电话号码已被注册");
        }
        else{
            $("#phone").css("margin-right","6%");
            hint.css("color","red");
            hint.html("请输入电话号码");
        }
    });
    $("#email").on("blur",function(){
        var email=$("#email").val();
        var hint=$("p:nth-of-type(7) >span");
        hint.css("visibility","visible");
        var parttern=/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
        var result=parttern.test(email);
        if(result){
            $("#email").css("margin-right","5%");
            sendSth2Server("/"+$("#web-local").html()+"/signUp/checkUserEmail",{"email":$.trim(email)},
                hint, "该邮箱可使用","邮箱已经存在");
        }else{
            hint.css("color","red");
            hint.html("请输入合法邮箱");
            $("#email").css("margin-right","7%");
        }
    });
    $(".sign-up-btn").on("click",function(){
        sendSignUpInfo2Server();
    })
}
function sendSignUpInfo2Server(){
    var data={"account":$("#account").val(),"password":$("#password").val(),"username":$("#username").val(),
              "birth":$("#year").val()+"-"+$("#month").val()+"-"+$("#day").val(),"phone":$("#phone").val(),
        "email":$("#email").val(),"sex":$("input[type=radio]:checked").val(),"type":2};
    $.ajax({
        url:"/"+$("#web-local").html()+"/signUp/addNewUser",
        type:"post",
        dataType:"json",
        data:JSON.stringify(data),
        headers:{'Content-type':'application/json;charset=utf-8'}
    }).done(function(json){
        var hint={'ACCOUNT_EXIST':1,'USERNAME_EXIST':4,
            'PHONE_EXIST':6,"EMAIL_EXIST":7};
        var location={1:"账号已经被注册",4:"昵称已经存在",6:"电话号码已被注册",7:"邮箱已经存在"};
        console.log(json);
        if(json.code=="SUCCESS")
            alert("注册成功");
        else{
            var pos=hint[json.code];
            var hint=$("p").eq(pos).find("span");
            hint.css("color","red");
            hint.html(location[pos]);
        }
    }).fail(function(xhr,status,errorThrown){
        alert("there is something wrong with server");
        isValid=false;
    });
}
function sendSth2Server(url,data,errorHint,successWord,errorWord){
    console.log(errorHint);
    $.ajax({
        url:url,
        type:"post",
        dataType:"json",
        data:JSON.stringify(data),
        headers:{'Content-type':'application/json;charset=utf-8'}
    }).done(function(json){
        errorHint.css("visibility","visible")
        if(json.result==false){
            errorHint.html(errorWord);
            errorHint.css("color","red");
            isValid=false;
        }
        else{
            errorHint.html(successWord);
            errorHint.css("color","green");
            isValid=true;
        }
    }).fail(function(xhr,status,errorThrown){
        alert("there is something wrong with server");
        isValid=false;
    });
}