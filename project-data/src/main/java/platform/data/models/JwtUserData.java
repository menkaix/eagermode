package platform.data.models;

import java.io.Serializable;

public class JwtUserData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7967856217454666214L;
	
	
	private String userName ;
	private String token ;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public JwtUserData() {
		
	}
	public JwtUserData(String pUserName, String pToken) {
		userName = pUserName ;
		token = pToken ;
	}
	

}
