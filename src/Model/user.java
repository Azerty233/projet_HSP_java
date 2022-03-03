package Model;

import Manager.Global;

public class user extends Global {
	
	private String email;
	private String mdp;
	
	
	public user(String email, String mdp) {
		
		email = this.email;
		mdp = this.mdp;
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	

}
