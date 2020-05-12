package com.ssafy.moida.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 30)
	private String email;
	
	@Column(nullable = false, length = 100)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 30)
	private int gender;
	
	@Column(nullable = true, name="profile_Img")
	private String profileImg;
	
	@Column(nullable = false, length = 100)
	private String phone;
	
	@Column(nullable = false, length = 30, unique = true)
	private String nickname;
	
	public void updateAccountInfo(String password, String phone,  String nickname, String profileImg) {
		this.password = password;
		this.phone = phone;
		this.nickname = nickname;
		this.profileImg = profileImg;
	}
	
	@Builder
	public Account(Long id, String email, String username, String password, int gender, String profileImg, String phone,
			String nickname) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.profileImg = profileImg;
		this.phone = phone;
		this.nickname = nickname;
	}
}
