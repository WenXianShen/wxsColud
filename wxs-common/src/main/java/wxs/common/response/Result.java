package wxs.common.response;

public  class Result {
	//0=登录失效，1=请求成功，2=业务异常，3=系统异常
	/**
	 * 登录失效
	 */
	public static AppResult invalid(){
		return new AppResult(0, "登录失效~", null);
	}
	/**
	 * 请求非法
	 */
	public static AppResult illegal(){
		return new AppResult(2, "请求非法~", null);
	}
	/**
	 * 参数不完整
	 */
	public static AppResult incomplete(){
		return new AppResult(2, "参数不完整~", null);
	}
	/**
	 * 系统出错
	 */
	public static AppResult error(String message){
		return new AppResult(3, message, null);
	}
	/**
	 * 对象找不到
	 */
	public static AppResult notFound(){
		return new AppResult(2, "对象找不到~", null);
	}
	public static AppResult failed(String message){
		return new AppResult(2, message, null);
	}
	public static AppResult failed(String message,Object result){
		return new AppResult(2, message, null);
	}
	public static AppResult success(Object result){
		return new AppResult(1, "", result);
	}
	public static AppResult success(String message,Object result){
		return new AppResult(1, message, result);
	}
	/**
	 * 创建成功
	 */
	public static AppResult successCreate(Object result){
		return new AppResult(1, "创建成功~", result);
	}
	/**
	 * 更新成功
	 */
	public static AppResult successUpdate(Object result){
		return new AppResult(1, "更新成功~", result);
	}
	/**
	 * 删除成功
	 */
	public static AppResult successRemove(Object result){
		return new AppResult(1, "删除成功~", result);
	}
}
