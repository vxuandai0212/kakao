package com.kakao.auth.security.oauth2.user;

import java.util.Map;

public class TwitterOAuth2UserInfo extends OAuth2UserInfo {

    public TwitterOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        if(attributes.containsKey("data")) {
            Map<String, Object> dataObj = (Map<String, Object>) attributes.get("data");
            if(dataObj.containsKey("id")) {
                return (String) dataObj.get("id");
            }
        }
        return null;
    }

    @Override
    public String getName() {
        if(attributes.containsKey("data")) {
            Map<String, Object> dataObj = (Map<String, Object>) attributes.get("data");
            if(dataObj.containsKey("name")) {
                return (String) dataObj.get("name");
            }
        }
        return null;
    }

    @Override
    public String getEmail() {
        if(attributes.containsKey("data")) {
            Map<String, Object> dataObj = (Map<String, Object>) attributes.get("data");
            if(dataObj.containsKey("email")) {
                return (String) dataObj.get("email");
            }
        }
        return null;
    }

    @Override
    public String getUsername() {
        if(attributes.containsKey("data")) {
            Map<String, Object> dataObj = (Map<String, Object>) attributes.get("data");
            if(dataObj.containsKey("username")) {
                return (String) dataObj.get("username");
            }
        }
        return null;
    }

    @Override
    public String getImageUrl() {
        if(attributes.containsKey("data")) {
            Map<String, Object> dataObj = (Map<String, Object>) attributes.get("data");
            if(dataObj.containsKey("profile_image_url")) {
                return (String) dataObj.get("profile_image_url");
            }
        }
        return null;
    }
}