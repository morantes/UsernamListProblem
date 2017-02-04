package com.test.username.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.test.username.abstracts.Management;
import com.test.username.obj.BlackWord;
import com.test.username.obj.GenericItem;
import com.test.username.obj.Result;
import com.test.username.obj.User;

public class UserManagement extends Management{
	public UserManagement(String source) {
		super(source);
		// TODO Apéndice de constructor generado automáticamente
	}

	
	/***Verifies if the name meets the user validations
	 * @throws Exception ****************************************************/
	@Override
	public Boolean validateInput(StringBuffer input) throws Exception {
		return input.toString().trim().length()>=6;
	}
	
	
	/**********This method reads data from its source***************************************/
	public List<User> readAll() throws Exception{
		List<User> resultList = new ArrayList<User>();
		BufferedReader br = new BufferedReader(new FileReader(new File(source)));
	    for (String line; (line = br.readLine()) != null; ) {
	    	User item = new User(new StringBuffer(line));
	    	resultList.add(item);
	    }
	    br.close();
	    Collections.sort(resultList);
		return resultList;
	}
	
	/**********This method reads data from its source***************************************/
	public User findUser(User usr) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(new File(source)));
	    for (String line; (line = br.readLine()) != null; ) {
	    	if(usr.getName().toString().trim().equalsIgnoreCase(line.trim())){
	    		return new User(new StringBuffer(line));
	    	}
	    }
	   return null;
	}
	
	
	
	
	
	
}
