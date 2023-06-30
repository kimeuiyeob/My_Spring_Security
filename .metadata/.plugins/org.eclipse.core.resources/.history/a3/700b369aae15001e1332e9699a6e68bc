package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> Java 객체를 테이블로 매핑해주는 기술, 저장만해도 쿼리문이 실행된다.

//@DynamicInsert // DB에 insert시에 null인필드는 제외를 시켜준다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 아래 필드값을 읽어서 자동으로 테이블 생성
//@Table(name="UserTable") 이런식으로 테이블 이름도 정할수있다.
public class User {

	// id, createDate는 값을 넣지 않아도 자동으로 들어간다.

	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 넘버링 전략을 따라간다.
	private int id;

	@Column(nullable = false, length = 30, unique = true) // null이 들어가면 안되고 길이를 30이상 넘기지 못하게 한다, 중복 X
	private String username;

	@Column(nullable = false, length = 100) // 비밀번호의 길이를 크게준 이유는 암호화된 비밀번호를 DB에 저장시키기 때문이다.
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

//	@ColumnDefault("'user'") //기본 설정을 user로 할수 있지만 enum을 활용해보자
	@Enumerated(EnumType.STRING) // RoleType이라는 컬럼속성값은 없으니까 String이라고 알려주면 DB에 varchar로 들어간다.
	private RoleType role; // 역할 -> enum을 사용하는게 좋다(오타방지 및 도메인 설정) ex) admin, user, manager 등등

	@CreationTimestamp // 자바에서 현재시간을 자동으로 넣어준다.
	private Timestamp createDate;

}
