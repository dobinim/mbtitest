package com.kim.mbtitest.dto;

public class MbtiUserDto {
	
	private String mName;
	private String mPw;
	private String mNickname;
	private String mbti;
	private String auth;
	
	
	public MbtiUserDto() {
		super();

	}


	public MbtiUserDto(String mName, String mPw, String mNickname, String mbti, String auth) {
		super();
		this.mName = mName;
		this.mPw = mPw;
		this.mNickname = mNickname;
		this.mbti = mbti;
		this.auth = auth;
	}


	public MbtiUserDto(String mName, String mPw, String mNickname, String mbti) {
		super();
		this.mName = mName;
		this.mPw = mPw;
		this.mNickname = mNickname;
		this.mbti = mbti;
	}


	// getter, setter
	public String getmName() {
		return mName;
	}


	public void setmName(String mName) {
		this.mName = mName;
	}


	public String getmPw() {
		return mPw;
	}


	public void setmPw(String mPw) {
		this.mPw = mPw;
	}


	public String getmNickname() {
		return mNickname;
	}


	public void setmNickname(String mNickname) {
		this.mNickname = mNickname;
	}


	public String getMbti() {
		return mbti;
	}


	public void setMbti(String mbti) {
		this.mbti = mbti;
	}


	public String getAuth() {
		return auth;
	}


	public void setAuth(String auth) {
		this.auth = auth;
	}




}
