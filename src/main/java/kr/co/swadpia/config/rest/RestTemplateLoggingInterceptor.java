package kr.co.swadpia.config.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		logRequestDetails(request);
		ClientHttpResponse response = execution.execute(request, body);
		logResponseDetails(response);
		return response;
	}

	private void logRequestDetails(HttpRequest request) {
		log.info("RestTemplate Req URI,Method,Headers: {}, {}, {}", request.getURI(),request.getMethod(),request.getHeaders());
	}

	private void logResponseDetails(ClientHttpResponse response) throws IOException {
		log.info("RestTemplate Res Status code,text: {}, {}", response.getStatusCode(),response.getStatusText());
	}
}