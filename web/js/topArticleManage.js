$($.ajax({
    url:"http://127.0.0.1:8080/admin/topArticle",
    type:"get",
    datatype:"json",
    success:function(data){
        var trs = "<table class=\"table table-striped table-bordered\"><tr><th>文章Id</th><th>文章标题</th><th>文章作者</th><th>操作</th>";
        $.each(data.data,function(n,value){
            trs+="<tr><td>"+value.articleId+"</td><td>"+value.articleTitle+"</td><td>"+value.author+"</td><td><button class=\"btn btn-info\" onclick='cancelTop(\""+value.articleId+"\")'>取消置顶</button></td></tr>";
        });
        trs+="</table>"
        $("#result").append(trs);
    }
}));
function cancelTop(articleId){
    $.ajax({
        url:"http://127.0.0.1:8080/admin/article/cancelTop",
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
}