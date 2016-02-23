package com.wish.wishMVC.base;

public class Result extends BaseBean{
	private static final long serialVersionUID = 1L;
	private int isOK;//1为正常，0为异常
	private Object data;

	public Result() {
		this.isOK = 1;
	}

	public Result(int isOK, Object data) {
		this.isOK = isOK;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getIsOK() {
		return isOK;
	}

	public void setIsOK(int isOK) {
		this.isOK = isOK;
	}
}
