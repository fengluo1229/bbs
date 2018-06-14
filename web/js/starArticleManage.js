$($.ajax({
    url:"http:127.0.0.1:8080/admin/starArticle",
    type:"get",
    dataType:"json",
    success:function(data){
        var trs = "<table class=\"table table-striped table-bordered\"><tr><th>文章Id</th><th>文章标题</th><th>文章作者</th><th>操作</th>";
        $.each(data.data,function(n,value){
            trs+="<tr><td>"+value.articleId+"</td><td>"+value.articleTitle+"</td><td>"+value.author+"</td><td><button class=\"btn btn-info\" onclick='cancelStar(\""+value.articleId+"\")'>取消加精</button></td></tr>";
        });
        trs+="</table>"
        $("#result").append(trs);
    }
}));
$($.ajax({
    url:"http:127.0.0.1:8080/admin/starArticle/pageNum",
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
function cancelStar(articleId){
    $.ajax({
        url:"http:127.0.0.1:8080/admin/article/cancelStar",
        type:"post",
        data:{
            articleId:articleId
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
        url:"http:127.0.0.1:8080/admin/starArticle",
        type:"get",
        data:{
            page:page
        },
        dataType:"json",
        success:function(data){
            var trs = "<table class=\"table table-striped table-bordered\"><tr><th>文章Id</th><th>文章标题</th><th>文章作者</th><th>操作</th>";
            $.each(data.data,function(n,value){
            trs+="<tr><td>"+value.articleId+"</td><td>"+value.articleTitle+"</td><td>"+value.author+"</td><td><button class=\"btn btn-info\" onclick='cancelStar(\""+value.articleId+"\")'>取消加精</button></td></tr>";
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