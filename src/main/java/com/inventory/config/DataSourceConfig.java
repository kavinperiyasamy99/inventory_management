package com.inventory.config;

import com.inventory.constants.APPConstants;
import com.inventory.dto.DataSourceModel;
import com.inventory.handler.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Map;

@Configuration
@Slf4j
public class DataSourceConfig {

	@Value("${spring.profiles.active}")
	private String domain;

	@Value("${swagger.profile}")
	private String swaggerLibSwitch;
	
	@Autowired
	private Docket swaggerDocket;
	
	
	/**
	 * This method used to generate data source based upon domain.
	 * 
	 * @author kavin p
	 * @return DataSourceModel
	 * @throws Exception
	 */
	@Bean
	public synchronized DataSourceModel getDataSource() throws CustomException {
		// if domain type is dev -> read from property file. If domain type is prod,
		// read from system environment.
		log.info("domain type : " + domain);
		DataSourceModel dataSourceModel = null;
		if (domain != null && !domain.isEmpty() && domain.equalsIgnoreCase("dev")) {
			dataSourceModel = getDevelopmentDataSource();
		} else {
			dataSourceModel = getProductionDataSource();
		}
		log.info("DataSourceConfig model :: " + dataSourceModel.toString());
		log.info("Swagger Docket Profile :: "+swaggerDocket.isEnabled());
		return dataSourceModel;
	}

	/**
	 * This method used to generate the development data source.
	 *
	 * @author kavin p
	 * @return DataSourceModel
	 * @throws Exception
	 */
	private DataSourceModel getDevelopmentDataSource() throws CustomException {
		DataSourceModel dataSourceModel = new DataSourceModel();
		if(swaggerLibSwitch!=null && !swaggerLibSwitch.isEmpty() && swaggerLibSwitch.equalsIgnoreCase("false")) {
			swaggerDocket.enable(false);
		}
		return dataSourceModel;
	}

	/**
	 * This method used to get the properties from environemnt properties if
	 * platform profile is production.
	 *
	 * @author kavin p
	 * @return DataSourceModel
	 * @throws Exception
	 */
	private DataSourceModel getProductionDataSource() throws CustomException {
		DataSourceModel dataSourceModel = new DataSourceModel();
		Map<String, String> env = System.getenv();
		swaggerLibSwitch = env.get(APPConstants.SWAGGER_PROFILE);
		if(swaggerLibSwitch!=null && !swaggerLibSwitch.isEmpty() && swaggerLibSwitch.equalsIgnoreCase("false")) {
			swaggerDocket.enable(false);
		}
		return dataSourceModel;
	}

	/**
	 * This method used to get RestTemplate object and pass for api calls
	 * @author kavin p
	 * @return RestTemplate
	 */
	@Bean
    public RestTemplate getRestTemplate() {
	      return new RestTemplate();	
	}
	
}
