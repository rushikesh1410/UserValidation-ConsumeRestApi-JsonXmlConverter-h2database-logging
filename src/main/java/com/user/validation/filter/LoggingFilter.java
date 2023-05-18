package com.user.validation.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class LoggingFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);

		Long startTime = System.currentTimeMillis();
		filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);

		LocalDateTime date_and_time = LocalDateTime.now();

		Long timeTaken = System.currentTimeMillis() - startTime;

		String requestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(),
				request.getCharacterEncoding());
		String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(),
				response.getCharacterEncoding());


		LOGGER.info("_____________Welcome to loggs______________");
		LOGGER.info("filter logs :DATE= {}", date_and_time);
		LOGGER.info("filter logs :METHOD = {}", request.getMethod());
		LOGGER.info("filter logs :REQUESTURI = {}", request.getRequestURI());
		LOGGER.info("filter logs :RESPONSE CODE= {}", response.getStatus());
		LOGGER.info("filter logs :STARTTIME ={}", startTime);
		LOGGER.info("filter logs :TIMETAKEN = {}", timeTaken);
		LOGGER.info("filter logs :REQUEST BODY ={}", requestBody);
		LOGGER.info("filter logs :RESPONSE BODY ={}", responseBody);

		contentCachingResponseWrapper.copyBodyToResponse();

	}

	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
		try {
			return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
