package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.SecurityConfig;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	// 세션 객체를 여기서 주입시켜서 가져올수도 있고 파라미터로 받을수도 있다.
//	@Autowired
//	private HttpSession session;

	// =====================================================
	// 회원가입
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		int result = userService.save(user); // 1이면 성공, -1이면 실패
		// HttpStatus.OK -> 200 정상적인 통신이 완료되었다는 뜻이다.
		ResponseDto<Integer> resp = new ResponseDto<>(HttpStatus.OK.value(), result);
		return resp;
	}

	// =====================================================
	// 로그인
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		// principal 접근 주체
//		User principal = userService.login(user);
//
//		if (principal != null) {
//			session.setAttribute("principal", principal); // 세션 생성
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}

	// =====================================================
}
