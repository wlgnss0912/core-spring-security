package jihun.security.corespringsecurity.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    private String username;
    private String password;
    private String email;
    private String age;
    private String role;

    public Account toEntity() {
        return Account.builder()
                .username(username)
                .password(password)
                .email(email)
                .age(age)
                .role(role)
                .build();
    }

}
