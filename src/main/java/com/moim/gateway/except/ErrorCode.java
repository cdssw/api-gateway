package com.moim.gateway.except;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ErrorCode.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description 사용자 정의 ErrorCode
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

	MAX_SIZE_OVER("S_00001", "최대 사이즈를 초과하였습니다."),
	;
	
	private final String code;
	private final String message;
}
