package wxs.common.response;

import java.io.Serializable;
import java.util.Date;


public class  AppResult implements Serializable{
	private static final long serialVersionUID = -7780480031663752427L;
	private int status;//0=登录失效，1=请求成功，2=业务异常，3=系统异常
	private String message;
	private Object result;
	private long time = new Date().getTime();
	public AppResult() {
		
	}
	public AppResult(int status,String message,Object result) {
		this.status = status;
		this.message = message;
		this.result = result;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
