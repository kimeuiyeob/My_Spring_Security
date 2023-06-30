package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//우선 시큐리티 기본 세션 동작과 세션 시큐리티 세션 생성에 대해서 간략하게 정리해본다.
//시큐리티 컨피그 파일 -> 함수를 훑어보면 내가 지정한 url말고는 못들어가게 막는걸 볼수있다.
//그리고 로그인이 요청시 아이디와 비밀번호를 받아올수있는데 authentication(인증) 필드가 중간에 가로채
//usernamepasswordauthenticationtoken에 담는다.
//그리고 authenticationManager한테 토큰을 던진다. authenticationManager는 해당 아이디로 아이디가 있는지
//확인하고 해당 아이디가 있으면 authentication객체를 생성하고 해당 객체를 시큐리티 세션 컨텍스트에 담는다.
//이렇게 시큐리티 세션이 생성되는것이다.

@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	// 스프링이 로그인 요청을 가로챌때, username, password 변수 2개를 가로채는데
	// password 부분처리는 알아서 처리 한다.
	// username이 DB에 있는지 없는지만 확인해주면 된다. -> 확인을 아래 함수에서 한다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByusername(username)
				.orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을수 없습니다. : " + username));
		return new PrincipalDetail(principal); // 시큐리티의 세션에 유저 정보가 저장이된다. -> UserDetails 타입으로 저장된다.
		// 그래서 하나의 클래스를 만들어서 implements UserDetails을 한뒤 오버라이드 해서 저렇게 한거다. 시큐리티는
		// UserDetails타입으로 저장해야하기 때문!!
	}

}
