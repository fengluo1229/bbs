$(
    function checkLogin(){
        
        if(sessionStorage.getItem("user")!=null){
            var user = JSON.parse(sessionStorage.getItem("user"));
            $("#loginLabel").text(user.userName);
            $("#loginLabel").attr("href","profile.html")
            $("#regLabel").hide();
        }else{
            $("#dropdown").hide();
        }
    }
)
// $($.ajax({
//     url:"http://127.0.0.1:8080/admin/topArticle",
//     type:"get",
//     dataType:"json",
//     success:function(data){
//     }
// }));
{/* function articleTemplet(articleId,articleTitle,author,sort,label,postTime,lastReplyTime,lastReplyAuthor,replyNum){

} */}