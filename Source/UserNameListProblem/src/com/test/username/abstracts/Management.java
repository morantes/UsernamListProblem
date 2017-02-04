package com.test.username.abstracts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.test.username.exception.BlackListedUserException;
import com.test.username.exception.InvalidUserLenghtException;
import com.test.username.obj.BlackWord;
import com.test.username.obj.GenericItem;
import com.test.username.obj.Result;
import com.test.username.obj.User;
import com.test.username.util.Util;

public abstract class  Management{
	public String source ;
	public boolean ascendent;
	public int topResults;
	public int getTopResults() {
		return topResults;
	}

	public void setTopResults(int topResults) {
		this.topResults = topResults;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isAscendent() {
		return ascendent;
	}

	public void setAscendent(boolean ascendent) {
		this.ascendent = ascendent;
	}

	public Management(String source){
		this.source=source;
	}
	
	
	
		
	
	/**********This abstract method validates the input, whatever its nature is*************/
	public abstract Boolean validateInput(StringBuffer input) throws Exception;
	
	
	
	/**********This method adds a new item to its source
	 * @param userList 
	 * @param blackList 
	 * @param user *************************************/
	public  Result verifyUser(StringBuffer item, List<BlackWord> blackList, List<User> userList,User foundUser) throws Exception{
		List<User> feebBackNames = new ArrayList<User>();
		Result result = new Result();
		if(validateInput(item)){
			for(BlackWord restrictedWord : blackList){
                if(item.toString().contains(restrictedWord.getName().toString())){
                    throw new BlackListedUserException("The username matches a prohibited word {"+restrictedWord.getName().toString()+"}");
                }
            }
			User pivotUsr,pivotUsr2;
			 if(foundUser != null){
				 	int rNumber;
	                StringBuffer name=new StringBuffer();
	                Random random = new Random();
	                Boolean validation;
	                int i=0;
	                while((feebBackNames.size() < 14) && (i < 3)){
	                	feebBackNames.clear();
	                    for(int j = 0; j < 14; j++){
	                    	validation = true;
	                        rNumber = random.nextInt(100)+1; //Generating random number
	                        name = new StringBuffer(item.toString()+rNumber);
	                        pivotUsr=Util.findLocalItem(name,userList); //Looking for suggested name in Original Username list
	                        pivotUsr2=Util.findLocalItem(name,feebBackNames); //Looking for suggested name in Suggested Username List
	                        if(pivotUsr2==null){ //Suggested name it's not in actual Suggested Username List
	                            if(pivotUsr == null){ //Suggested it's not in Original Username List
	                                for(BlackWord restrictedWord : blackList){
	                                    if(name.toString().contains(restrictedWord.getName().toString())){
	                                    	validation = false; //suggested name its in blacklist
	                                    	break; //We don't want to iterate the whole List
	                                    }
	                                }                            
	                            }
	                            if(validation){
	                            	feebBackNames.add(new User(name));
	                            }
	                        }
	                    }
	                    i++;
	                }

	                result.setValid(Boolean.FALSE);
	                result.setResultList(feebBackNames);
	            }else{
	                result.setValid(Boolean.TRUE);
	                result.setResultList(feebBackNames);
	            }
		}
		else{
			throw new InvalidUserLenghtException("User name must be at least 6 characters");
		}
		return result;
	}
	
	

	/**********This method removes an existing to its source**********************************/
	public void remove(StringBuffer item){
		
	}
	
	
	
	
}
