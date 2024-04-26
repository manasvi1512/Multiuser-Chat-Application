/*DTO - data transfer object
 Collects data from Swing(Screen) 
 Then fills the data in DTO which is then passed to DAO
 DAO pushes the data from DTO to database
 and, then database operations are performed on this data
 */
package com.dit.chatapp.dto;

public class userDTO {
	private String userid;
	private char[] password;
	public userDTO(String userid, char[] password) {
		this.userid = userid;
		this.password = password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	
}
