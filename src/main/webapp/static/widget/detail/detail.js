define("widget/detail/detail",["require","exports","module","components/jquery/jquery","components/template/template"],function(t,a,e){{var n=t("components/jquery/jquery"),o=t("components/template/template");e.exports=function(t){var a={init:function(){this.commentTemplate='<% for(var i = 0,len = data.data.length; i<len ;i++){%>\n<% var comment = data.data[i],path = data.contextpath ; %>\n<div class="comment cf comment_details" data-comment-id="<%=comment.id %>">\n    <div class="avatar left">\n        <a href="javascript:void(0)"><img alt="科技50用户<%=comment.author.nickname %>" src="<%=comment.author.image %>" raw_iden="<%=comment.id %>" class="before-fade-in"></a>\n    </div>\n    <div class="comment-wrapper">\n        <div class="postmeta"><a class="user_info_name" href="javascript:void(0)"><%=comment.author.nickname %></a>&nbsp;•&nbsp;\n            <abbr class="timeago" title="<%=comment.createDate %>"><%=comment.createDate %></abbr>\n        </div>\n        <div class="commemt-main">\n        	<p <% if(comment.state != \'c\') { %>class="pending"<% } %>>\n        	<%  if(comment.state == \'c\') { %>\n        	<span title="该评论正在审核中, 仅对本人可见" class="badge badge-warning">审核中</span>\n        	<% } \n        		if (comment.toAuthor) {\n        	%>\n        	<span class="replay-tips">回复<%=comment.toAuthor %>：</span>\n        	<% } %>\n            <%=comment.content %>\n            </p>\n        </div>\n        <div class="opts"></div>\n        <a class="pull-right" data-author="<%=comment.author.nickname%>" href="javascript:;"> 回复</a></div>\n</div>\n<%}%>   ',this.addEvent()},addEvent:function(){var a=this;console.log(t.author_id),a.getList(t.commentUrl,{author_id:t.author_id},"get",function(t){if(0==t.code&&t.data.length){n("#commentTotalCount,#commentFormCount").html(t.data.length);var e=o.parse(a.commentTemplate,{data:t});n("#J_comments").html(e)}}),n("#J_comments").delegate(".pull-right","click",function(){var t=n(this).attr("data-author");n("#post").attr("data-author",t),n(".J_toAuthor").text(t),n(".reply_message").show()}),n("#J_userInfo .ladda-button").click(function(){var e=n("#post").val();e&&a.postComment(t.postUrl,{author_id:t.author_id,to_author:n("#post").attr("data-author"),content:e},function(t){if(0==t.code){n("#post").val(""),n(".J_delAuthor").click();var e=o.parse(a.commentTemplate,{data:t});n("#J_comments").prepend(e)}})}),n(".J_delAuthor").click(function(){n(".J_toAuthor").text(""),n(".reply_message").hide(),n("#post").attr("data-author","")})},getList:function(t,a,e,o){n.ajax({type:e,url:t,data:a,success:o}).complete(function(){})},postComment:function(t,a,e){n.ajax({type:"post",url:t,data:a,success:e}).complete(function(){})}};a.init()}}});