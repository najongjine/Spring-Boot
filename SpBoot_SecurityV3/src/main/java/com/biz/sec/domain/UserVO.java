package com.biz.sec.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "tbl_users")
public class UserVO implements UserDetails{
	/**
	 *  UserDetails 를 상속받고
	 *  private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private Collection<? extends GrantedAuthority> authorities;
	넣어주면 custom VO 와 Spring sec 속성이 서로 연동이 됨
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * jpa의 entity를 선언할때 id칼럼은 반드시 classType으로 선언하자.
	 * 그렇지 않으면 jpa의 자동 method가 작동된 않는다.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",columnDefinition = "bigint")
	private Long id;
	
	@Column(unique = true,length = 64,columnDefinition = "varchar(64)")
	private String username;
	private String password;
	
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	@Transient // DB에 칼럼으로 생성하지 말라
	private Collection<? extends GrantedAuthority> authorities;
	
	private String email;
	private String phone;
	private String address;
	
	/*
	 * JPA에서 1:N 의 관계를 설정하는 부분
	 * fetchType Lazy 두 테이블을 join 했을때 master table의 데이터를 select 한 후
	 * 바로 slave table을 slect 하지 않고 slave table의 데이터가 필요한 시점에
	 * select를 수행하도록 하는 지연 옵션
	 */
	//cascade = {CascadeType.ALL}
	@OneToMany(mappedBy = "userVO",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	private Set<UserRole> userRoles;
}
