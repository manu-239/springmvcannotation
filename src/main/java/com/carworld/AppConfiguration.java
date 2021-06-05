package com.carworld;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.carworld.dao.CarRepository;
import com.carworld.dao.EngineRepository;
import com.carworld.dao.JdbcCarRepository;
import com.carworld.dao.JdbcEngineRepository;
import com.carworld.dao.JdbcManufacturerRepository;
import com.carworld.dao.ManufacturerRepository;



@Configuration
@ComponentScan(value = {"com.carworld.controller","com.carworld.service"})
@PropertySource("classpath:/app.properties")
@EnableWebMvc //To use WebMvcConfigurer
public class AppConfiguration implements WebMvcConfigurer{


	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Value("${jdbc.url}")
	private String jdbcURL;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	
	/*INFO: @Bean method AppConfiguration.propertySourcesPlaceholderConfigurer is non-static and returns 
	an object assignable to Spring's BeanFactoryPostProcessor interface. This will result in a 
	failure to process annotations such as @Autowired, @Resource and @PostConstruct within the 
	method's declaring @Configuration class. Add the 'static' modifier to this method to avoid 
	these container lifecycle issues; see @Bean javadoc for complete details.
	Due to above issue, below method should be static*/
	@Bean
	public static PropertySourcesPlaceholderConfigurer  propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	/* <mvc:resources mapping="/resources/**" location="/resources/" />
	 * Resource mapping by implementing WebMvcConfigurer, corresponding to above
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

	@Bean
	@Scope("singleton")
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(jdbcURL);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}

	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		return new RequestMappingHandlerMapping();
	}

	@Bean
	public RequestMappingHandlerAdapter RequestMappingHandlerAdapter() {
		return new RequestMappingHandlerAdapter();
	}

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Bean(initMethod = "dbinit")
	@Scope("singleton")
	public CarRepository carRepository() {
		return new JdbcCarRepository();
	}

	@Bean
	@Scope("singleton")
	public EngineRepository engineRepository() {
		return new JdbcEngineRepository();
	}

	@Bean
	@Scope("singleton")
	public ManufacturerRepository manufacturerRepository() {
		return new JdbcManufacturerRepository();
	}

}
