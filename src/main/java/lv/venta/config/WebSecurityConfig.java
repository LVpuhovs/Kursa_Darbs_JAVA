package lv.venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService testUser() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails u1Details =
                        User
                        .builder()
                        .username("admin")
                        .password(encoder.encode("123456"))
                        .authorities("ADMIN")
                        .build();
        UserDetails u2Details =
                User
                        .builder()
                        .username("markuss")
                        .password(encoder.encode("25062024"))
                        .authorities("USER")
                        .build();


        UserDetails u3Details =
                User
                        .builder()
                        .username("janis")
                        .password(encoder.encode("098765"))
                        .authorities("USER")
                        .build();

        return new InMemoryUserDetailsManager(u1Details, u2Details, u3Details);
    }

    @Bean
    public SecurityFilterChain configureEndpoints(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/driver/all").permitAll()
                        .requestMatchers("/team/all").permitAll()
                        .requestMatchers("/standings/all").permitAll()
                        .requestMatchers("/standings/driver/all").permitAll()
                        .requestMatchers("/standings/team/all").permitAll()
                        .requestMatchers("/driver/create").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/driver/update/**").hasAuthority("ADMIN")
                        .requestMatchers("/driver/delete/**").hasAuthority("ADMIN")
                        .requestMatchers("/team/add").hasAuthority("ADMIN")
                        .requestMatchers("/team/update/**").hasAuthority("ADMIN")
                        .requestMatchers("/team/delete/**").hasAuthority("ADMIN")
                        .requestMatchers("/standings/all").permitAll()
                        .requestMatchers("/standings/driver/update/race/**").hasAuthority("ADMIN")
                        .requestMatchers("/standings/team/update/race/**").hasAuthority("ADMIN")
                        .requestMatchers("/standings/team/all").permitAll()
                        .requestMatchers("/h2-console/**").hasAuthority("ADMIN")
                        .requestMatchers("styles.css").permitAll()
                        .requestMatchers("driver/create.css").permitAll()
                        .requestMatchers("/error").permitAll()
                );

        http.formLogin(form -> form.permitAll());

        http.csrf(csrf-> csrf.disable());
        http.headers(frame-> frame.frameOptions(option->option.disable()));
        return http.build();
    }

}
