package kr.co.swadpia.config.auditor;

import kr.co.swadpia.admin.dto.AdminSessionDTO;
import kr.co.swadpia.common.dto.SessionDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class AuditorAware implements org.springframework.data.domain.AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        if(authentication.getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ADMIN"))) {
            AdminSessionDTO adminSessionDTO = (AdminSessionDTO) authentication.getPrincipal();
            return Optional.of(adminSessionDTO.getAdminId());

        } else if (authentication.getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("MEMBER"))) {
            SessionDTO sessionDTO = (SessionDTO) authentication.getPrincipal();
            return Optional.of(sessionDTO.getMemberSeq());
        }else{

            return Optional.empty();
        }
    }
}
