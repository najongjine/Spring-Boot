package com.biz.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

//JPA를 사용하기위한 DB 설정
@Entity
@Table(name="tbl_users",schema="spBoot")
public class UserVO {
	/*
	 * Generator값을 auto로 설정하면
	 * JPA가 임의로 table을 생성하고 sequence를 만들어서 주입하도록 설정이 됨.
	 * DBMS에 관계없이 자동생성 칼럼의 값을 주입하는 방식
	 * 
	 * 오라클처럼 autoincrement가 없는 DBMS
	 * @GeneratedValue(strategy=GenerationType.SEQUENCE)
	 * 
	 * DBMS 관계없이 사용하는 방법: auto 설정하면 table 속성과 같게 작동된다
	 * @GeneratedValue(strategy=GenerationType.TABLE)
	 * 
	 * auto incremant 기능이 있는 DBMS
	 * @GeneratedValue(strategy=GenerationType.IDENTITY)
	 * 
	 * MySql을 사용할때는 auto옵션보다는 identity를 사용하자
	 */
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	private String username;
	private String password;
	private String email;
	private String phone;
	private String address;
}
