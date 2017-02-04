package com.test.username.obj;

import java.util.List;

public class Result {
	public Boolean valid = false;
	public Boolean getValid() {
		return valid;
	}
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	public List<User> getResultList() {
		return resultList;
	}
	public void setResultList(List<User> resultList) {
		this.resultList = resultList;
	}
	public List<User> resultList;
}
