package br.com.softcube.expensemanagement.configuration;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.softcube.expensemanagement.services.security.ExpenseAuthenticationService;

@Configuration
@EnableWebSecurity
public class ExpenseManagementSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ExpenseAuthenticationService expenseAuthenticationService;
	
	//Esse método realiza as configurações de autenticação do usuário (mecanismo de autenticação com token e etc)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(expenseAuthenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Esse método realiza as configurações de autorização do usuário (quais URLs são públicas, quais são privadas e etc)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/expense").permitAll()
			.antMatchers(HttpMethod.GET, "/expense/*").permitAll()
			.anyRequest().authenticated()
			.and().formLogin();
	}
	
	//Esse método realiza as configurações de autorização de recursos estáticos (CSS, JS, Images e etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
	}

}
*/