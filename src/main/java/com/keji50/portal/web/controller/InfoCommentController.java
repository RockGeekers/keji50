package com.keji50.portal.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.keji50.portal.common.utils.WebUtils;
import com.keji50.portal.common.utils.constants.Constants;
import com.keji50.portal.dao.po.InfoCommentPo;
import com.keji50.portal.service.InfoCommentService;
import com.keji50.portal.service.InfoService;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/comment")
public class InfoCommentController {
	
    private static final Logger logger = LoggerFactory.getLogger(InfoCommentController.class);
    @Resource(name = "infoService")
    private InfoService infoService;
    
	@Resource(name = "infoCommentService")
	private InfoCommentService infoCommentService;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject ajaxGetComments(@PathVariable("id") int infoId, HttpServletRequest request) {
		if (infoId <= 0) {
			return WebUtils.toFailedResponse();
		}
		int authorId = 0;
		try {
			authorId = Integer.parseInt(request.getParameter(Constants.AUTHOR_ID));
		} catch (Exception e) {

		}

		try {
			List<InfoCommentPo> comments = infoCommentService.getCommentsByInfo(infoId, authorId);
			return WebUtils.toResponse(comments, request);
		} catch (Exception e) {
		    e.printStackTrace();
			return WebUtils.toFailedResponse();
		}
	}
	
	@RequestMapping(value = "/post/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject ajaxPostCommment(@PathVariable("id") int infoId,
			HttpServletRequest request) {
		if (infoId <= 0) {
			return WebUtils.toFailedResponse();
		}

		int authorId = getAuthorId(request);
		if (authorId <= 0) {
			JSONObject response = WebUtils.toFailedResponse();
			response.put(WebUtils.KEY_MESSAGE, "invalid param author_id");
			return response;
		}

		String toAuthor = StringUtils.trimWhitespace(request
				.getParameter(Constants.TO_AUTHOR));
		String content = StringUtils.trimWhitespace(request
				.getParameter(Constants.CONTENT));
		InfoCommentPo comment = new InfoCommentPo(authorId, infoId);
		comment.setToAuthor(toAuthor);
		comment.setContent(content);
		try {
		    if (!infoCommentService.saveComment(comment)) {
		        return WebUtils.toFailedResponse();
		    }
		    //点击评论,更新文章热度+1
	        try{
	            infoService.updateHotCountById(infoId);
	        }catch(Exception e){
	            logger.error("###点击评论更新文章{}热度异常,异常信息:{}",infoId,e);
	        }
		    return WebUtils.toResponse(Arrays.asList(infoCommentService.getCommentById(comment.getId())), request);
		} catch (Exception e) {
			return WebUtils.toFailedResponse();
		}
	}

	private int getAuthorId(HttpServletRequest request) {
		String authorId = request.getParameter(Constants.AUTHOR_ID);

		if (StringUtils.isEmpty(authorId)) {
			return 0;
		}
		try {
			return Integer.parseInt(authorId);
		} catch (Exception e) {
			return 0;
		}
	}
}
