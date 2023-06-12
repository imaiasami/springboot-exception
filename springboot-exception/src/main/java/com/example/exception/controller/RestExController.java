package com.example.exception.controller;

import org.apache.catalina.valves.ErrorReportValve;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.errorModel.ErrorResult;
import com.example.exception.errorModel.UserException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RestController
// @RestController 컨트롤러 내 모든 메소드의 응답을 @ResponseBody 로 준다.
public class RestExController {

	// @ResponseBody
	// @ResponseBody 는 응답을 HTML로 주는 것이 아니라,
	// 응답객체로 주는 것이다.
	@GetMapping("api/string")
	public String getString() {
		log.info("api/string 호출");
		return "Hello World";
	}

//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler
//	public ErrorResult illegalExHanle(IllegalArgumentException e) {
//		log.info("ex: {}", e);
//		return new ErrorResult("BAD REQUEST", e.getMessage());
//	}
//
//	@ExceptionHandler
//	public ResponseEntity<ErrorResult> userExhandle(UserException e) {
//		log.info("ex: {}", e.getMessage());
//		ErrorResult result = new ErrorResult("USER-EX", e.getMessage());
//		return new ResponseEntity<ErrorResult>(result, HttpStatus.BAD_REQUEST);
//
//	}
//
//	@ExceptionHandler
//	public ResponseEntity<ErrorResult> exHandle(Exception e) {
//		log.info("ex: {}", e.getMessage());
//		ErrorResult result = new ErrorResult("EX", e.getMessage());
//		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@GetMapping("api/members/{id}")
	public Member getMember(@PathVariable(name = "id") String id) throws Exception {
		log.info("id: {}", id);

		if (id.equals("error")) {
			throw new RuntimeException("잘못된 사용자");
		}

		if (id.equals("badreq")) {
			throw new IllegalArgumentException("잘못된 요청");
		}

		if (id.equals("user-ex")) {
			throw new UserException("사용자 정의 오류");
		}

		if (id.equals("exception")) {
			throw new Exception("기타 예외");
		}

		return new Member(id, "홍길동");
	}

	@Data
	@AllArgsConstructor
	static class Member {
		private String memberId;
		private String name;

	}
}
