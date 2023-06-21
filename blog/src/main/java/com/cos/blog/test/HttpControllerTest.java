package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일) = controller

@RestController
public class HttpControllerTest {
	
	public String builderTest() {
		//DTO객체에 @builder를 사용하면 아래처럼 셋을 해줄수있다.
		Member member = Member.builder().userName("의엽").email("1234@5678").passWord("1234").build();
		System.out.println("이름 : " + member.getUserName());
		System.out.println("이메일 : " + member.getEmail());
		System.out.println("비밀번호 : " + member.getPassWord());
		return "Test 완료";
	}

	// 브라우저 요청은 get만 된다.
	// get -> sellect
	
//	@GetMapping("/http/get")
//	public String getTest(@RequestParam int id, @RequestParam String userName) {
//		return "get 요청 아이디 : " + id + " 이름 : " + userName;
//	}
	@GetMapping("/http/get")
	public String getTest(Member member) { 
		//하나씩 쿼리스트링을 받을때는 @RequestParam을 이용하는 방법도 있지만 너무 길어지기 때문에
		//DTO객체를 생성해 해당 키값이랑 필드명이랑 동일하면 그 필드이름으로 매핑되어 값을 받을수 있다.
		return "get 요청 아이디 : " + member.getId() + " 이름 : " + member.getUserName();
	}

//	====================================================================

	// post -> insert
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member member) { 
		//MIME 타입을 JSON형식으로 보내게 되면 Member객체에 그대로 매핑되서 담기게된다. => MessageConverter가 매핑시켜준다.
		return "post 요청 아이디 : " + member.getId() + " 이름 : " + member.getUserName();
	}

//	====================================================================
	// put -> update
	@PutMapping("http/put")
	public String putTest(@RequestBody Member member) {
		return "put 요청 아이디 : " + member.getId() + " 이름 : " + member.getUserName();  
	}

//	====================================================================
	// delete -> delete
	@DeleteMapping("http/delete")
	public String deleteTest(@RequestBody Member member) {
		return "delete 요청 아이디 : " + member.getId() + " 이름 : " + member.getUserName();
	}
}
