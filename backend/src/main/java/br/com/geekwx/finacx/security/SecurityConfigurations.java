package br.com.geekwx.finacx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.geekwx.finacx.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private AutenticacaoService autenticationService;
	
	@Autowired
	private TokenService tokenService;

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
//	@Override
//	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManager();
//	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	

	
	//Configuracoes de autenticacao
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
			.userDetailsService(autenticationService).passwordEncoder(new BCryptPasswordEncoder());
		}
		
		
		
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception 
//		{
//		    auth.parentAuthenticationManager(authenticationManagerBean());
//		        .userDetailsService(customUserDetailsService);
//		}
		
		
		/*
		 * 
		 * .antMatchers(HttpMethod.POST, "/admin/**").access("hasAuthority('ADMIN')")
.antMatchers(HttpMethod.GET, "/admin/**").access("hasAuthority('ADMIN')")
.antMatchers(HttpMethod.PUT, "/admin/**").access("hasAuthority('ADMIN')")
.antMatchers(HttpMethod.POST, "/admin/**").permitAll()
			.antMatchers(HttpMethod.POST, "/admin/usuario/**").permitAll()
			.antMatchers(HttpMethod.POST, "/usuario/**").permitAll()
			.antMatchers(HttpMethod.GET, "/admin/**").permitAll()
			.antMatchers(HttpMethod.GET, "/usuario/**").permitAll()
			.antMatchers(HttpMethod.PUT, "/admin/**").permitAll()
			.antMatchers(HttpMethod.DELETE, "/admin/**").permitAll()
		 * 
		 * */
		
		//Configuracoes de autorizacao
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			.antMatchers(HttpMethod.POST, "/admin/**").access("hasAuthority('ADMIN')")
			.antMatchers(HttpMethod.GET, "/admin/**").access("hasAuthority('ADMIN')")
			.antMatchers(HttpMethod.PUT, "/admin/**").access("hasAuthority('ADMIN')")
			.antMatchers(HttpMethod.DELETE, "/admin/**").access("hasAuthority('ADMIN')")
				.anyRequest().authenticated()
				.and().cors()
				.and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
		}
		

		//Configuracoes de recursos estaticos(js, css, imagens, etc.)
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
		}
		
		
//		public static void main(String[] args) {
//			
//			String password = "admin"; 
//			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
//			String encodedPassword = passwordEncoder.encode(password);
//			System.out.print(encodedPassword);
//			
//		}
//		
}
