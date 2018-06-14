$($.ajax({
    url:"http:127.0.0.1:8080/admin/reply",
    type:"get",
    dataType:"json",
    success:function(data){
        var trs = "<table class=\"table table-striped table-bordered\"><tr><th>回复Id</th><th>回复内容</th><th>回复文章id</th><th>回复者</th><th>回复时间</th><th>操作</th>";
        $.each(data.data,function(n,value){
            trs+="<tr><td>"+value.replyId+"</td><td>"+value.message+"</td><td>"+value.articleId+"</td><td>"+value.author+"</td><td><button class=\"btn btn-success\" onclick='recoverReply(\""+value.replyId+"\")'>恢复回复</button></button></td></tr>";
        });
        trs+="</table>"
        $("#result").append(trs);
    }
}));
$($.ajax({
    url:"http:127.0.0.1:8080/admin/reply/pageNum",
    type:"get",
    success:function(data){
        var pageNum=data.data;
        var buttons="";
        for(var i=1;i<=pageNum;i++){
        buttons+="<button type=\"button\" class=\"btn btn-defaoult\" onclick='changePage("+i+")'>"+i+"</button>"
        }
        $("#page").append(buttons);
    }
}));
function recoverReply(replyleId){
    $.ajax({
        url:"http:127.0.0.1:8080/admin/reply/recover",
        type:"post",
        data:{
            replyId:replyId
        },
        datatype:"json",
        success:function(data){
            alert(data.message);
            location.reload();
        }
    })
};
function changePage(page){
    $.ajax({
        url:"http:127.0.0.1:8080/admin/reply",
    type:"get",
        data:{
            page:page
        },
        dataType:"json",
        success:function(data){
            var trs = "<table class=\"table table-striped table-bordered\"><tr><th>回复Id</th><th>回复内容</th><th>回复文章id</th><th>回复者</th><th>回复时间</th><th>操作</th>";
            $.each(data.data,function(n,value){
                trs+="<tr><td>"+value.replyId+"</td><td>"+value.message+"</td><td>"+value.articleId+"</td><td>"+value.author+"</td><td><button class=\"btn btn-success\" onclick='recoverReply(\""+value.replyId+"\")'>恢复回复</button></button></td></tr>";
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