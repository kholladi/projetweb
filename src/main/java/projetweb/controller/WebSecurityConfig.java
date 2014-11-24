package projetweb.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import projetweb.model.Employes;
import projetweb.repository.EmployesRepository;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 @Override
	protected void configure(HttpSecurity http) throws Exception {
		 
	
		http.authorizeRequests().antMatchers("/","/accueil","/accueilemploi","/historique","/caisse","/loginemploi","/index","/cai","/deletefacture","/editfacture","/facture").permitAll()
				.anyRequest().authenticated();
		http.csrf().disable();

		http.formLogin().loginPage("/login").usernameParameter("login")
				.passwordParameter("pwd").permitAll().and().logout()
				.logoutUrl("/bye").logoutSuccessUrl("/accueil");
	}


	@Configuration
	protected static class AuthenticationConfiguration extends
			GlobalAuthenticationConfigurerAdapter {
		
		private EmployesRepository EmployesRep;
		ArrayList<Employes>listEm=new ArrayList<Employes>();
		

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("imad").password("web").roles("ADMIN");
			
		}

	}
}
