package paging.study.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import paging.study.service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    // 아래 경로는 허용
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/member/login", "/", "/member/denied").permitAll()
                    .antMatchers("/admin").hasAuthority("SUPER_ADMIN")
                    .antMatchers("/user").hasAuthority("MEMBER")
                    .anyRequest().authenticated()
                // login
                .and()
                    .formLogin()
                    .loginPage("/member/login")
                    .loginProcessingUrl("/member/loginProc")
                    .defaultSuccessUrl("/admin/main")
                    .successHandler(successHandler())
                    .usernameParameter("userId")
                    .passwordParameter("userPassword")
                // logout
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logoutPrc"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                    .exceptionHandling().accessDeniedPage("/member/denied").
                and().csrf().disable();
    }

    /**
     *
     * 로그인 인증 처리
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandles();
    }
}
