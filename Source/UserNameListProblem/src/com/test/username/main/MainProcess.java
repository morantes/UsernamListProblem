package com.test.username.main;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import com.test.username.exception.BlackListedUserException;
import com.test.username.exception.InvalidUserLenghtException;
import com.test.username.impl.BlackListManagement;
import com.test.username.impl.UserManagement;
import com.test.username.obj.BlackWord;
import com.test.username.obj.Result;
import com.test.username.obj.User;
import com.test.username.util.Util;

public class MainProcess {
	static final String  URLBlackListSource = "blackList.txt";
	static final String  URLUserNameSource="users.txt";
	public static void main(String args[]){
		
		try {
			String param = args[0];
			String[] params = param.split("\\=");
			if(params[0].trim().equalsIgnoreCase("checkNames")){
				String[] names=params[1].split("\\,");
				for(String username: names){
					try{
						System.out.println("\n\n\n\n\nValidating username: "+username);
						checkNames(username);
					}
					catch (InvalidUserLenghtException e) {
						 System.out.println("The username it's not valid.");
						 final StringWriter sw = new StringWriter();
					     final PrintWriter pw = new PrintWriter(sw, true);
					     e.printStackTrace(pw);
					     System.out.println(sw.getBuffer().toString());
					}
					catch (BlackListedUserException e) {
						 System.out.println("The username contains restricted words.");
						 System.out.println("\n\n\n\n\n");
						 final StringWriter sw = new StringWriter();
					     final PrintWriter pw = new PrintWriter(sw, true);
					     e.printStackTrace(pw);
					     System.out.println(sw.getBuffer().toString());
					}
				}
				
				
			}
			else{
				if(params[0].trim().equalsIgnoreCase("blacklist")){
					addBlackList(params[1]);
				}
				else{
					throw new Exception("Bad input.");
				}
			}
		}
		
		catch (Exception e) {
			 System.out.println("An error has been produced.");
			 System.out.println("Usage (checknames): java -jar UserName.jar checkNames=myName1,myName2,myName3...");
			 System.out.println("Usage (add blackList item): java -jar UserName.jar blackList=blackListItem");
			 System.out.println("\n\n\n\n\n");
			 final StringWriter sw = new StringWriter();
		     final PrintWriter pw = new PrintWriter(sw, true);
		     e.printStackTrace(pw);
		     System.out.println(sw.getBuffer().toString());
		}
	}
	
	public static void checkNames(String name) throws Exception{
		UserManagement usrMngm = new UserManagement(URLUserNameSource);
		BlackListManagement blMngm = new BlackListManagement(URLBlackListSource);
		
		List<BlackWord> blackList = blMngm.readAll(); //get sorted blacklist
		List<User> userList = usrMngm.readAll(); //get sorted users list
		User usr = new User(new StringBuffer(name));
		Result result = usrMngm.verifyUser(usr.getName(),blackList,userList,usrMngm.findUser(usr));
		System.out.println("Result: "+result.getValid());
		if(!result.getValid()){
			System.out.println("Username it's not valid.");
			int count=1;
			for(User usrResult : result.getResultList()){
				System.out.println("Suggested user "+count+" : {"+usrResult.getName()+"}");
				count++;
			}
		}
		else{
			System.out.println("It's a valid username.");
		}
	}
	
	public static void addBlackList(String name) throws Exception{
		BlackListManagement blMngm = new BlackListManagement(URLBlackListSource);
		if(blMngm.readUntilFind(new StringBuffer(name))==null){
			blMngm.add(new StringBuffer(name));
			System.out.println("{"+name+"} successfully added to Blacklist");
		}
		else{
			throw new Exception ("Blacklist word {"+name+"} already exists");
		}
	}
	
}
