package jihun.security.corespringsecurity.service.impl;

import jakarta.transaction.Transactional;
import jihun.security.corespringsecurity.domain.AccountDto;
import jihun.security.corespringsecurity.repository.UserRepository;
import jihun.security.corespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(AccountDto account) {
        userRepository.save(account.toEntity());
    }
}
