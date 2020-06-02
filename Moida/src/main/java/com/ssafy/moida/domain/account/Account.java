package com.ssafy.moida.domain.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.moida.domain.common.BaseEntity;
import com.ssafy.moida.domain.etrash.Etrash;
import com.ssafy.moida.domain.group.AccountGroup;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Account extends BaseEntity
implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 30)
	private String email;
	
	@Column(nullable = false, length = 100)
	private String username;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //쓰기 시에만 deserialize
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 30)
	private int gender;
	
	@Lob
	@Column(nullable = true, name="profile_Img")
	private String profileImg;
	
	@Column(nullable = false, length = 100)
	private String phone;
	
	@Column(nullable = false, length = 30, unique = true)
	private String nickname;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles = new ArrayList<>();
	
	@OneToMany(mappedBy = "account")
	private List<AccountGroup> groupList = new ArrayList<>();
	
	public void updateAccountInfo(String password, String phone,  String nickname, String profileImg) {
		this.password = password;
		this.phone = phone;
		this.nickname = nickname;
		this.profileImg = profileImg;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Builder
	public Account(String email, String username, String password, int gender, String profileImg, String phone,
			String nickname, List<String> roles) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.profileImg = profileImg;
		this.phone = phone;
		this.nickname = nickname;
		this.roles = roles;
	}
}
