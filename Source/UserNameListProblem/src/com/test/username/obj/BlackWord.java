package com.test.username.obj;



public class BlackWord implements GenericItem{
	public BlackWord(StringBuffer name){
		this.name=name;
	}

	private StringBuffer name;

	public StringBuffer getName() {
		return name;
	}

	public void setName(StringBuffer name) {
		this.name = name;
	}


	@Override
	public int compareTo(Object obj) {
		BlackWord user = (BlackWord)obj;
		if(BOOLEAN_ALPHABETICAL_RESULT){
			return this.name.toString().toUpperCase().compareTo(user.getName().toString().toUpperCase());
		}
		else{
			return user.getName().toString().toUpperCase().compareTo(this.name.toString().toUpperCase());
		}
	}
	
}
