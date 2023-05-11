package com.tyss.optimize.config.aspect;

import com.tyss.optimize.config.SpringBeans;

import java.util.HashMap;
import java.util.Map;

public enum RequestHeaderEnum {

    USER_ID("userId") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setUserId(value);
        }
    },
    CLIENT_TOPIC("clientTopic") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setClientTopic(value);
        }
    },
    PROJECT_ID("projectId") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setProjectId(value);
        }
    }, FOLDER_ID("folderId") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setFolderId(value);
        }
    }, PROJECT_TYPE("projectType") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setProjectType(value);
        }
    },
    PLATFORM("platform") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setPlatform(value);
        }
    }, CLIENT_IP("clientIP") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setClientIP(value);
        }
    }, ELEMENT_ID("elementId") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setElementId(value);
        }
    },
    PAGE_ID("pageId") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setPageId(value);
        }
    }, PAGE_NAME("pageName") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setPageName(value);
        }
    }, RECORD_ELEMENT_IDS("recordElementIds") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setRecordElementIds(value);
        }
    },
    PERSONAL_TOKEN("personalToken") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setPersonalToken(value);
        }
    }, LICENSE_ID("licenseId") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setLicenseId(value);
        }
    }, USER_EMAIL("userEmail") {
        @Override
        public void setRequestHeader(String value) {
            SpringBeans.getReqHeaderBean().setUserEmail(value);
        }
    };

    final private String label;

    RequestHeaderEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public abstract void setRequestHeader(String value);

    private static final Map<String, RequestHeaderEnum> requestHeaderCache = new HashMap<>();

    static {
        for (RequestHeaderEnum e: values()) {
            requestHeaderCache.put(e.label, e);
        }
    }
    public static RequestHeaderEnum labelOf(String label) {
        return requestHeaderCache.get(label);
    }
}
