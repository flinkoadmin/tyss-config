package com.tyss.optimize.config.auth;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.common.util.Privilege;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.Objects;

public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(
            Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)){
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();

        return hasPrivilege(auth, targetType, permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(
            Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, targetType.toUpperCase(),
                permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {

        Principal principal = (Principal) auth.getPrincipal();
        KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
        AccessToken token = kp.getKeycloakSecurityContext().getToken();
        System.out.println("Inside hasPrivilege() accessToken: "+token);

        Map<String, Object> otherClaims = token.getOtherClaims();
        System.out.println("Inside hasPrivilege() otherClaims: "+otherClaims);

        String privilege = null;
        if(otherClaims.containsKey("currentPrivilege")){
            privilege = (String) otherClaims.get("currentPrivilege");
        }
        System.out.println("Inside hasPrivilege() privilege: "+privilege);

        if(Privilege.SUPER_ADMIN.equals(privilege)) {
            return true;
        }
        for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
            String grantAuth = grantedAuth.getAuthority();
            if (Objects.nonNull(grantAuth) && grantAuth.startsWith(targetType)) {

                String grant = grantAuth.substring(grantAuth.indexOf(":")+1, grantAuth.length());
                switch (grant) {
                    case CommonConstants.FULL_ACCESS:
                    case CommonConstants.WRITE:
                        return true;
                    case CommonConstants.NO_ACCESS:
                        return false;
                    case CommonConstants.VIEW:
                        return (permission.equals(CommonConstants.WRITE)) ? false : true;
                    default:
                        return false;
                }
            }
        }
        return false;
    }
}
