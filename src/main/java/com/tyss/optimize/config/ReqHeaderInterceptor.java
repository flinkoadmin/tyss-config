package com.tyss.optimize.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class ReqHeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String projectId = request.getHeader("projectId");
        String folderId = request.getHeader("folderId");
        String projectType = request.getHeader("projectType");
        String platform = request.getHeader("platform");
        String clientIP = request.getHeader("X-Forwarded-For");
        String elementId = request.getHeader("elementId");
        String pageId = request.getHeader("pageId");
        String pageName = request.getHeader("pageName");
        String recordElementIds = request.getHeader("recordElementIds");
        String personalToken = request.getHeader("personalToken");
        String licenseId = request.getHeader("licenseId");
        String userEmail = request.getHeader("userEmail");

        SpringBeans.getReqHeaderBean().setProjectId(projectId);
        SpringBeans.getReqHeaderBean().setFolderId(folderId);
        SpringBeans.getReqHeaderBean().setProjectType(projectType);
        SpringBeans.getReqHeaderBean().setPlatform(platform);
        SpringBeans.getReqHeaderBean().setClientIP(clientIP);
        SpringBeans.getReqHeaderBean().setElementId(elementId);
        SpringBeans.getReqHeaderBean().setPageId(pageId);
        SpringBeans.getReqHeaderBean().setPageName(pageName);
        SpringBeans.getReqHeaderBean().setRecordElementIds(recordElementIds);
        SpringBeans.getReqHeaderBean().setPersonalToken(personalToken);
        SpringBeans.getReqHeaderBean().setLicenseId(licenseId);
        SpringBeans.getReqHeaderBean().setUserEmail(userEmail);
        log.info(" preHandle projectId = {}, licenseId = {}, projectType = {}, folderId={}, " +
                        "platform={}, clientIP={}, elementId={}, pageId={}, " +
                        "pageName={}, recordElementIds={} ,personalToken={}, userEmail={}"
                , projectId, licenseId, projectType, folderId, platform,
                clientIP, elementId, pageId, pageName, recordElementIds, personalToken, userEmail);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {

    }


}