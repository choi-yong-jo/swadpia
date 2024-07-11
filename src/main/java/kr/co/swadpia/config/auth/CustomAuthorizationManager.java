package kr.co.swadpia.config.auth;

import jakarta.annotation.PostConstruct;
import kr.co.swadpia.common.entity.Menu;
import kr.co.swadpia.repository.jpa.system.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * spring security 권한 추가 manager
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final MenuRepository menuRepository;

    private final Map<AntPathRequestMatcher, List<String>> requestMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        loadMenuRoles();
    }

    public void reloadMenuRoles() {
        loadMenuRoles();
    }

    private void loadMenuRoles() {
        /*List<Menu> menus = menuRepository.findAll();
        for (Menu menu : menus) {
            addMenuRoles(menu);
        }*/
    }

    private void addMenuRoles(Menu menu) {

        //customAuthorizationManager.reloadMenuRoles(); 호출하면 권한 리로드됌
        /*log.info("CustomAuthorizationManager 실행");
        if (menu.getUrl() != null && !menu.getUrl().isEmpty()) {
            List<String> roles = menu.getMenuGroupAuthorities().stream()
                .map(MenuGroupAuthority::getMenuGroupAuthorityName).toList();

            if (!roles.isEmpty()) {
                requestMap.put(new AntPathRequestMatcher(menu.getUrl()), roles);
            }
        }

        // 하위 메뉴 재귀
        for (Menu child : menu.getChildren()) {
            addMenuRoles(child);
        }*/
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        String requestUrl = object.getRequest().getRequestURI();
        for (Map.Entry<AntPathRequestMatcher, List<String>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(object.getRequest())) {
                for (String role : entry.getValue()) {
                    if (authentication.get().getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role))) {
                        return new AuthorizationDecision(true);
                    }
                }
            }
        }
        throw new AccessDeniedException("Access Denied");
    }
}