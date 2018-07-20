package com.dong.demoboot.entity;

import java.util.Date;

/**
 * user日志entity
 *
 * @author: dongzhihua
 * @time: 2018/6/22 12:25:43
 */
public class UserLog {
	private String userName;
	private String action;
	private Date actionTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
}
