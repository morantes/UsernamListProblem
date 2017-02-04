package com.test.username.util;

import java.util.List;

import com.test.username.obj.BlackWord;
import com.test.username.obj.User;

public class Util {
	public static User findLocalItem(StringBuffer name, List<User> userList) {
		for (User usr: userList ) {
	    	if(usr.toString().trim().equalsIgnoreCase(name.toString())){
	    		return usr;
	    	}
	    }
		return null;
	}
	public static BlackWord findLocalBlackWord(StringBuffer name, List<BlackWord> blacklist) {
		
		for (BlackWord item: blacklist ) {
	    	if(item.toString().trim().equalsIgnoreCase(name.toString())){
	    		return item;
	    	}
	    }
		return null;
	}
}
