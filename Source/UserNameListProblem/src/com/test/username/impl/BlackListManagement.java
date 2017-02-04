package com.test.username.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.test.username.abstracts.Management;
import com.test.username.obj.BlackWord;
import com.test.username.obj.GenericItem;
import com.test.username.obj.Result;
import com.test.username.obj.User;

public class BlackListManagement extends Management{
	public BlackListManagement(String source) {
		super(source);
	}

	
	/***Could verify if the black list word is able to be added (NO need to implement anything in this case)
	 * @throws Exception ****************************************************/
	@Override
	public Boolean validateInput(StringBuffer input) throws Exception {
		return true;
	}
	
	
	/**********This method reads data from its source***************************************/
	public List<BlackWord> readAll() throws Exception{
		List<BlackWord> resultList = new ArrayList<BlackWord>();
		BufferedReader br = new BufferedReader(new FileReader(new File(source)));
	    for (String line; (line = br.readLine()) != null; ) {
	    	BlackWord item = new BlackWord(new StringBuffer(line));
	    	resultList.add(item);
	    }
	    br.close();
	    Collections.sort(resultList);
		return resultList;
	}
	
	public void add(StringBuffer item) throws IOException{
		String newline = System.getProperty("line.separator");
		FileWriter fStream = new FileWriter(source, true);
		fStream.append(item);
		fStream.append(newline);
        fStream.flush();
        fStream.close();
	}


	public BlackWord readUntilFind(StringBuffer stringBuffer) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(source)));
		
	    for (String line; (line = br.readLine()) != null; ) {
	    	if(line.trim().equalsIgnoreCase(stringBuffer.toString())){
	    		return new BlackWord(new StringBuffer(line));
	    	}
	    }
	    br.close();
		return null;
	}
	
}
