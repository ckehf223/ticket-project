package project.member;

import java.io.Serializable;

public class Admin implements Serializable{

	private String adminID;
	private int adminPW;
	
	public Admin() {
		
	}
	public Admin(String adminID, int adminPW) {
		this.adminID = adminID;
		this.adminPW = adminPW;
	}
	public String getAdminID() {
		return adminID;
	}
	public int getAdminPW() {
		return adminPW;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public void setAdminPW(int adminPW) {
		this.adminPW = adminPW;
	}

}
