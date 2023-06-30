package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 아래 필드값을 읽어서 자동으로 테이블 생성
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터 사용할때 (longtext)
	private String content; // 썸머노트 사용 -> <html>태그가 섞여서 디자인된다.

	@ColumnDefault("0") //조회수
	private int count;

	// ORM에서 테이블 연관관계를 맺기 위해서는 이렇게 사용한다.
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = one ->한명의 유저는 여러개의 게시글을 쓸수 있다.
	@JoinColumn(name = "userId") // 즉 userId 컬럼을 생성해 User테이블의 pk id랑 연관관계를 맺게된다.
	private User user; // DB는 객체를 저장할수 없다. FK, 자바는 객체를 저장할수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //mappedBy -> 연관관계의 주인이 아니다. (DB 컬럼 생성 X)
	//즉 board를 sellect할때 join문을 통해서 reply 값을 가져오기 위함이다.
	@JsonIgnoreProperties({"board"}) //<= board를 무시한다
	//EAGER전략은 해당 board를 조회할때 reply의 값까지 다 가져오는건데 reply안에는 또 Eager전략으로 board가있다.
	//이렇게 되면 서로 무한참조를 해서 값을 계속 가져오는 문제가 발생해서 @JsonIgnoreProperties({"board"}) 어노테이션으로
	//reply를 가져올때 안에있는 board값은 빼고 가져오게 하면서 무한참조를 막는것이다.
	@OrderBy("id desc")
	private List<Reply> reply;

	@CreationTimestamp // 데이터가 추가 또는 수정될때 자동으로 시간이 들어간다.
	private Timestamp createDate;

}
