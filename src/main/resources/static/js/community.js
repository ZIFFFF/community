function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (res) {
            console.log(res);
            if (res.code == 200){
                $("#comment_section").hide();
            } else {
                if (res.code == 2003){
                    var isAccepted = confirm(res.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=ecd3b2a589879dc3b88a&redirect_uri=http://localhost:8888/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", "true");
                    }
                }
            }
        }
    })
}