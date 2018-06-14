$($.ajax({
    url:"http:127.0.0.1:8080/admin/user",
    type:"get",
    dataType:"json",
    success:function(data){
        var trs = "<table class=\"table  table-striped table-bordered\"><tr><th>用户名</th><th>禁言时间</th><th>操作</th></tr>";
        $.each(data.data,function(n,value){
            trs+="<tr><td>"+value.userName+"</td><td>"+value.banTime+"</td><td><button class=\"btn btn-danger\" onclick='cancelBan(\""+value.userName+"\")'>解除禁言</button></td></tr>";
        });
        trs+="</table>"
        $("#result").append(trs);
    },
    error:function(){
        alert("error");
    }
}));
$($.ajax({
url:"http:127.0.0.1:8080/admin/user/pageNum",
type:"get",
dataType:"json",
success:function(data){
var pageNum=data.data;
var buttons="";
for(var i=1;i<=pageNum;i++){
    buttons+="<button type=\"button\" class=\"btn btn-defaoult\" onclick='changePage("+i+")'>"+i+"</button>"
}
$("#page").append(buttons);
}
}));
function cancelBan(userName){
$.ajax({
url:"http:127.0.0.1:8080/admin/user/cancel",
type:"post",
data:{
    userName:userName
},
dataType:"json",
success:function(data){
    alert(data.message);
    location.reload();
}
})
};
function banUser(){
$.ajax({
url:"http:127.0.0.1:8080/admin/user/ban",
type:"post",
data:{
    userName:$("#userName").val(),
    banTime:$("input[name='options']:checked").val()
},
dataType:"json",
success:function(data){
    alert(data.message);
    location.reload();
}
})
};
function changePage(page){
$.ajax({
    url:"http:127.0.0.1:8080/admin/user",
    type:"get",
    data:{
        page:page
    },
    dataType:"json",
    success:function(data){
        var trs = "<table class=\"table  table-striped table-bordered\"><tr><th>用户名</th><th>禁言时间</th><th>操作</th></tr>";
        $.each(data.data,function(n,value){
            trs+="<tr><td>"+value.userName+"</td><td>"+value.banTime+"</td><td><button class=\"btn btn-danger\" onclick='cancelBan(\""+value.userName+"\")'>解除禁言</button></td>";
        });
        trs+="</table>"
        $("#result").empty();
        $("#result").append(trs);
    },
    error:function(){
        alert("error");
    }
})
};