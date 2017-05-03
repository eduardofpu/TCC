package org.condominio.sj.backand.z.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public enum ExceptionConstants {

	PARAMETER_VALUE_EXCEPTION(HttpStatus.BAD_REQUEST, 4001, "Erro 4001 . Valor do parametro não pode ser nullo."),

	SECURITY_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "Error 5000. Erro gerado por problemas de segurança, localizar um administrador."),

	SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, 5001, "Este Email,Telefone,CPF OU RG ja existe no sistema!! ");//[%s]

	private final HttpStatus httpStatus;

	private final Integer serverCode;

	private final String message;

	private static final Map<Integer, ExceptionConstants> internalCodeMap;

	static {
		internalCodeMap = new HashMap<Integer, ExceptionConstants>();

		for (ExceptionConstants value : values()) {
			internalCodeMap.put(value.serverCode, value);
		}
	}

	private ExceptionConstants(HttpStatus httpStatus, Integer serverCode, String message) {
		this.httpStatus = httpStatus;
		this.serverCode = serverCode;
		this.message = message;
	}

	public Integer getServerCode() {
		return serverCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public ExceptionConstants parser(int code) {
		return internalCodeMap.get(code);
	}

}
