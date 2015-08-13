package com.rxv5.platform.result;

/**
 * @author Rixu
 * 
 * 代表成功或失败的Bean，用于Controller的返回值
 */
public final class SuccessOrFailure {

	/**
	 * 成功实例
	 */
	public static final SuccessOrFailure SUCCESS = new SuccessOrFailure();

	/**
	 * 失败实例
	 */
	public static final SuccessOrFailure FAILURE = new SuccessOrFailure(false);

	private boolean success;

	private String message;

	/**
	 * 创建一个成功实例
	 */
	private SuccessOrFailure() {
		this.success = true;
	}

	/**
	 * 
	 */
	private SuccessOrFailure(boolean isSuccess) {
		this.success = isSuccess;
	}

	/**
	 * 创建一个失败实例
	 * 
	 * @param message 失败信息 失败信息不能为空白（null和空白字符串）
	 */
	public SuccessOrFailure(String message) {
		if (message == null || message.length() <= 0) {
			throw new RuntimeException("不能以空白失败信息创建一个“失败”实例");
		}

		this.success = false;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 设置失败消息。当失败消息为非空白（null和空白字符串）时success会同时被设置为false，否则为true
	 * 
	 * @param message 失败消息 没有失败消息应该用null表示
	 */
	public void setmessage(String message) {
		this.success = (message == null || message.length() <= 0) ? true : false;
		this.message = message;
	}

	/**
	 * 设置成功与否。当设置为成功时，message被清除；当设置为失败时，message被设置为“未知错误”
	 * 
	 * @param success 是否成功 成功时为true，失败时为false
	 */
	public void setSuccess(boolean success) {
		this.success = success;

		if (success) {
			this.message = null;
		} else {
			this.message = "未知错误";
		}
	}

}
