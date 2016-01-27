/**
 *Copyright (c) 2016, ShangHai HOWBUY INVESTMENT MANAGEMENT Co., Ltd.
 *All right reserved.
 *
 *THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF HOWBUY INVESTMENT
 *MANAGEMENT CO., LTD.  THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 *TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 *WITHOUT THE PRIOR WRITTEN PERMISSION OF HOWBUY INVESTMENT MANAGEMENT
 * CO., LTD.
 */

package com.keji50.portal.web.controller;

import com.keji50.portal.dao.po.EsActivityPo;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创业活动Contorller
 * 
 * @author shiwen.shi
 * @date 2016-1-26 下午1:44:42
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/activity")
public class EsActivityController {
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public List<EsActivityPo> list() {
        return null;
    }

}
