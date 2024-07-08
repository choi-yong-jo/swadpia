package kr.co.swadpia.config.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
			.requestFactory(this::clientHttpRequestFactory)
			.setConnectTimeout(Duration.ofMillis(5000))
			.setReadTimeout(Duration.ofMillis(5000))
			.errorHandler(new RestTemplateResponseErrorHandler())
			.additionalInterceptors(new RestTemplateLoggingInterceptor())
			.build();
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(5000);
		factory.setReadTimeout(5000);
		return factory;
	}
}
