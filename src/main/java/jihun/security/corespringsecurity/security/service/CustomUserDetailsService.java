package jihun.security.corespringsecurity.security.service;

import jihun.security.corespringsecurity.domain.Account;
import jihun.security.corespringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsService 구현
 * 보통 유저 정보를 관리하는 서비스 계층에다 UserDetailsService 인터페이스를 구현
 * 이 타입의 빈이 등록되어 있으면 Spring Boot가 만들어주는 기본 사용자가 생성되지 않음
 * 로그인 시 UserDetailsService가 가지고 있는 loadUserByUsername() 메소드 호출됨
 */
@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {//DB에서 user 정보를 가지고 오는 역할

    private final UserRepository userRepository;

    //인증 처리시 spring security가 해당 class를 참조하고 DB를 연동이 되도록 처리가 됨.(config 설정 해줘야함)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = userRepository.findByUsername(username);//Account 객체 조회

        if(account == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(account.getRole()));

        AccountContext accountContext = new AccountContext(account, roles);//userDetails 타입 반환

        return accountContext;
    }
}
