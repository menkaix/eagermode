package platform.main;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import dashboard.data.entities.Actor;
import dashboard.data.entities.Feature;
import dashboard.data.entities.FeatureType;
import dashboard.data.entities.Project;
import dashboard.data.entities.UserStory;

@CrossOrigin
@SpringBootApplication(scanBasePackages = {				
		
		"platform.config",
		"platform.services.jwt",
		"platform.services.structure",
		"platform.controller",
		
		
		"dashboard.services",		
		"dashboard.controllers",
		"dashboard.controller.utilities",
		"dashboard.controller.entities",
		
		"dashboard.constraints",
		"dashboard.agents"
		
		
		})
@EntityScan(basePackages={
		"dashboard.data.entities",
		"platform.data.entities"})
@EnableJpaRepositories(basePackages = {
		"platform.data.repositories",
		"dashboard.data.repositories"
})
@EnableScheduling
public class AccessingDataMysqlApplication extends SpringBootServletInitializer implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}
	
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.getServletRegistration(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
                .setInitParameter("dispatchOptionsRequest", "true");
    }
	
	@Override
    public void configureRepositoryRestConfiguration(
      RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Project.class);
        config.exposeIdsFor(Actor.class);
        config.exposeIdsFor(UserStory.class);
        config.exposeIdsFor(Feature.class);
        config.exposeIdsFor(FeatureType.class);
        
    }
	
	
}
