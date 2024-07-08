package kr.co.swadpia.config.auth;

import kr.co.swadpia.common.service.AuthService;
import kr.co.swadpia.repository.jpa.system.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

    private final AuthService authService;

    private final MemberAuthorizationFilter memberAuthorizationFilter;
    private final AdminAuthorizationFilter adminAuthorizationFilter;
    private final CustomAuthorizationManager customAuthorizationManager;

    private final MemberAuthProvider memberAuthProvider;
    private final MemberFailureHandler memberFailureHandler;
    private final MemberSuccessHandler memberSuccessHandler;

    private final AdminAuthProvider adminAuthProvider;
    private final AdminSuccessHandler adminSuccessHandler;
    private final AdminFailureHandler adminFailureHandler;

    private final MenuRepository menuRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        MemberAuthenticationFilter memberAuthenticationFilter =  new MemberAuthenticationFilter(authenticationManager());
        memberAuthenticationFilter.setFilterProcessesUrl("/api/common/login");
        memberAuthenticationFilter.setAuthenticationFailureHandler(memberFailureHandler);
        memberAuthenticationFilter.setAuthenticationSuccessHandler(memberSuccessHandler);

        AdminAuthenticationFilter adminAuthenticationFilter = new AdminAuthenticationFilter(adminAuthenticationManager());
        adminAuthenticationFilter.setFilterProcessesUrl("/api/admin/auth/login");
        adminAuthenticationFilter.setAuthenticationFailureHandler(adminFailureHandler);
        adminAuthenticationFilter.setAuthenticationSuccessHandler(adminSuccessHandler);

        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //스웨어, API role
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**", "/swagger-ui/","/v3/api-docs/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/v2/api-docs").permitAll()
                        .requestMatchers("/api/common/**").permitAll()
                        .requestMatchers("/api/test/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/v0/**").permitAll() //TODO 삭제예정
                        .requestMatchers("/api/elastic/**").permitAll()
        );

        // menu role Auth Manager
        /*http.authorizeHttpRequests(authorize -> {
            List<Menu> menus = menuRepository.findAll();
            for (Menu menu : menus) {
                if (menu.getUrl() != null && !menu.getUrl().isEmpty()) {
                    authorize.requestMatchers(menu.getUrl()).access(customAuthorizationManager);
                }
            }
        });*/

        //TODO 삭제예정
       http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/member/**").hasAnyAuthority("MEMBER")
                .requestMatchers("/api/admin/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
        );

        http.addFilter(memberAuthenticationFilter);
        http.addFilter(adminAuthenticationFilter);
        http.addFilterBefore(adminAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(memberAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return memberAuthProvider::authenticate;
    }

    public AuthenticationManager adminAuthenticationManager() {
        return adminAuthProvider::authenticate;
    }


    @Bean
    BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}