package com.lin.ynnzbigdata.controller;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

abstract class GeneralController {
    protected HttpSession session;

    public void setSession(HttpSession session) {
        this.session = session;
    }

    //跳转界面的方法
    public ModelAndView forwardOrRedirect(String url) {
        return null == session.getAttribute("SESSION_PRINCIPAL") ? //
                new ModelAndView("redirect:/_") : new ModelAndView(url);
    }

    //定义ajax返回界面的结果
    protected String jsonResultTemplate(Object resultContent) {
        return "{\"result\" : " + resultContent + " }";
        
    }


}
