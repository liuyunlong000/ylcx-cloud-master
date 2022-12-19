package com.lcxbs.config;
import cn.hutool.core.collection.CollectionUtil;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@RefreshScope
@Component
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${knife4j.ext-config.groupName}")
	private String groupName;
	@Value("${knife4j.setting.enableHostText}")
	private String host;

	@Value("${knife4j.ext-config.contactName}")
	private String contactName;

	@Value("${knife4j.ext-config.contactUrl}")
	private String contactUrl;

	@Value("${knife4j.ext-config.contactEmail}")
	private String contactEmail;

	@Value("${knife4j.ext-config.description}")
	private String description;

	@Value("${knife4j.ext-config.apiVersion}")
	private String apiVersion;
	@Value("${knife4j.ext-config.serviceUrl}")
	private String serviceUrl;
	@Value("${security.oauth2.client.accessTokenUri}")
	private String passwordTokenUrl;

	private OpenApiExtensionResolver openApiExtensionResolver;

	@Autowired
	public SwaggerConfig(OpenApiExtensionResolver openApiExtensionResolver) {
		this.openApiExtensionResolver = openApiExtensionResolver;
	}


	@Bean(value = "defaultApi2")
	public Docket defaultApi2() {
		//schema
		List<GrantType> grantTypes=new ArrayList<>();
		//密码模式
		ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant=new ResourceOwnerPasswordCredentialsGrant(passwordTokenUrl);
		grantTypes.add(resourceOwnerPasswordCredentialsGrant);

		OAuth oAuth=new OAuthBuilder().name("Authorization").grantTypes(grantTypes).build();
		//context
		SecurityContext securityContext=SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(regex("^(?!auth).*$"))
				.build();
		//schemas
		List<SecurityScheme> securitySchemes=CollectionUtil.newArrayList(oAuth);
		//securyContext
		List<SecurityContext> securityContexts=CollectionUtil.newArrayList(securityContext);

		Docket docket=new Docket(DocumentationType.SWAGGER_2)
//				.host(this.host)
				.apiInfo(apiInfo())
				.groupName(groupName)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.lcxbs"))
				.paths(PathSelectors.any())
				.build();
//				.securityContexts(securityContexts).securitySchemes(securitySchemes);
		return docket;
	}

	private ApiInfo apiInfo() {
		Contact contact=new Contact(this.contactName,this.contactUrl,this.contactEmail);
		return new ApiInfoBuilder()
				.description(this.description)
				.termsOfServiceUrl(this.serviceUrl)
				.contact(contact)
				.version(this.apiVersion)
				.build();
	}

	List<SecurityReference> defaultAuth() {
		//scope方位
		AuthorizationScope authorizationScope = new AuthorizationScope("all", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> securityReferences=new ArrayList<>();
//		securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
		return securityReferences;
	}
}