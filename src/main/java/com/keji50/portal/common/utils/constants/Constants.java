package com.keji50.portal.common.utils.constants;

import com.keji50.portal.common.spring.CustomizedPropertyPlaceholderConfigurer;

/**
 * 常量集合类
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月8日 下午4:34:47
 *
 * @see
 */
public class Constants {

    private Constants() {

    }

    /**
     * 用户默认头像
     */
    public final static String DEFAULT_ACCOUNT_IMAGE = CustomizedPropertyPlaceholderConfigurer.getContextProperty("account.default.image");
    
    public final static String INFO_CATEGORY_ID = "info_category_id";
    
    public final static String INFO_ID = "info_id";
    
    public final static String INFO_OFFSET = "offset";
    
    public final static String AUTHOR_ID = "author_id";
    
    public final static String TO_AUTHOR = "to_author";
    
    public final static String CONTENT = "content";
    
    public final static String COMMAND = "d";
    
    public final static String VALIDATE_TYPE_PHONE = "0";
    
    public final static String VALIDATE_TYPE_EMAIL = "1";
    
    public final static String SESSION_LOGIN_USER = "loginUser";
    
    // 以下为返回给页面的参数
    
    // 用户默认头像
    public final static String RESPONSE_DEFAULT_ACCOUNT_IMAGE = "default_account_image";
    
    // 页面index
    public final static String RESPONSE_INDEX = "index";
    
    // 页面标题
    public final static String RESPONSE_TITLE = "title";
    
    // 文章
    public final static String RESPONSE_INFO = "info";
    
    // 作者
    public final static String RESPONSE_AUTHOR = "author";
    
    // 文章列表
    public final static String RESPONSE_INFOS = "infos";
    
    // 文章总数目
    public final static String RESPONSE_INFOS_TOTAL= "infosTotal";
    
    // 用户最近发表的文章列表
    public final static String RESPONSE_INFOS_NEARBY = "infosNearby";

    // 热门文章列表
    public final static String RESPONSE_INFOS_HOT = "infosHot";
    
    // 首页动态栏推荐文章列表
    public final static String RESPONSE_INFOS_SUGGEST = "infosSuggest";

    // 文章栏目列表
    public final static String RESPONSE_INFO_CATEGORIES = "categories";
    
    

    
}
