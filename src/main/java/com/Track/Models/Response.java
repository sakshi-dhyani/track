package com.Track.Models;

public class Response {
	
	private String error;
	private String message;
	private int code;
	private String token;
	private Object data;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Response(String error, String message, int code, String token, Object data) {
		super();
		this.error = error;
		this.message = message;
		this.code = code;
		this.token = token;
		this.data = data;
	}
	@Override
	public String toString() {
		return "Response [error=" + error + ", message=" + message + ", code=" + code + ", token=" + token + ", data="
				+ data + "]";
	}
	
	

}
