package com.moim.gateway.except;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FileUploadExceptionAdvice.java
 * 
 * @author cdssw
 * @since 2020. 9. 25.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 9. 25.    cdssw            최초 생성
 * </pre>
 */
@ControllerAdvice
@ResponseBody
public class FileUploadExceptionAdvice {

	// 일반적인 Exception 처리 class
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)	
	public static class ExceptRes {
		private String timeStamp;
		private String code;
		private String message;
		private String path;
		
		@Builder
		public ExceptRes(String timeStamp, String code, String message, String path) {
			this.timeStamp = timeStamp;
			this.code = code;
			this.message = message;
			this.path = path;
		}
	}
	
	// MaxUploadSizeExceededException 처리
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ExceptRes handleNotFoundException(HttpServletRequest req, MaxUploadSizeExceededException e) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return ExceptRes.builder()
				.code(ErrorCode.MAX_SIZE_OVER.getCode())
				.message(ErrorCode.MAX_SIZE_OVER.getMessage())
				.path(req.getRequestURI())
				.timeStamp(LocalDateTime.now().format(formatter))
				.build();
	}
}
