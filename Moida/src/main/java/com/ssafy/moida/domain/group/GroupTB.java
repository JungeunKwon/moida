package com.ssafy.moida.domain.group;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ssafy.moida.domain.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class GroupTB extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = true, length = 30)
	private String name;
	
	@OneToMany(mappedBy = "groupTB")
	private List<AccountGroup> account = new ArrayList<>();

	@Builder
	public GroupTB(Long id, String name, List<AccountGroup> account) {
		this.id = id;
		this.name = name;
		this.account = account;
	}
	
}
