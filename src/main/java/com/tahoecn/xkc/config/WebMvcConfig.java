package com.tahoecn.xkc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.tahoecn.xkc.interceptor.GlobalInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer,Converter<String, Date>  {

	@Value("${tahoe.application.mappingPath}")
	private String relativePath;

	@Value("${tahoe.application.physicalPath}")
	private String physicalPath;

	@Bean
	GlobalInterceptor globalInterceptor(){
		return new GlobalInterceptor();
	}

	/**
	 * 注册一个全局的拦截器
	 * @param registry
	 */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globalInterceptor()).addPathPatterns("/**").excludePathPatterns("/unauthorized","/websocket/**");;
    }

	/**
	 * 建立URL相对路径与绝对路径关系
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(relativePath).addResourceLocations("file:" + physicalPath);
	}


	@Bean
	public ObjectMapper om() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Jdk8Module());
		JavaTimeModule module = new JavaTimeModule();
		module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		objectMapper.registerModule(module);
		return objectMapper;
	}

	private static final List<String> formarts = new ArrayList();
	static{
		formarts.add("yyyy-MM");
		formarts.add("yyyy-MM-dd");
		formarts.add("yyyy-MM-dd HH");
		formarts.add("yyyy-MM-dd HH:mm");
		formarts.add("yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public Date convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		if(source.matches("^\\d{4}-\\d{1,2}$")){
			return parseDate(source, formarts.get(0));
		}else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
			return parseDate(source, formarts.get(1));
		}else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
			return parseDate(source, formarts.get(2));
		}else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
			return parseDate(source, formarts.get(3));
		}else {
			throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
		}
	}

	/**
	 * 格式化日期
	 * @param dateStr String 字符型日期
	 * @param format String 格式
	 * @return Date 日期
	 */
	public Date parseDate(String dateStr, String format) {
		Date date=null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			date = dateFormat.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}


}
