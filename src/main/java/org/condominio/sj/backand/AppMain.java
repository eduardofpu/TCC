package org.condominio.sj.backand;

import org.condominio.sj.backand.z.utils.AppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class AppMain extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AppContext.class, args);
	}



	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(AppMain.class);
	}


}
