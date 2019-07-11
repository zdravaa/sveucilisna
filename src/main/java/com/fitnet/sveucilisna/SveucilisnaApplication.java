package com.fitnet.sveucilisna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SveucilisnaApplication extends SpringBootServletInitializer {

	/*@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/v1/players*//*");
		registrationBean.addUrlPatterns("/api/v1/teams*//*");

		return registrationBean;
	}*/

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SveucilisnaApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SveucilisnaApplication.class, args);
	}
}
