package com.tyss.optimize.config;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Data
public class ReqHeader {
    String projectId;
    String folderId;
    String projectType;
    String platform;
    String clientIP;
    String clientInfo;
    String elementId;
    String pageId;
    String pageName;
    String recordElementIds;
    String personalToken;
    String licenseId;
    String userEmail;
    String clientTopic; //used inside kafka consumer & producer
    String userId; //used inside kafka consumer & producer
}