package com.example.exception.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServletExController {
	// HTTP 상태 코드

	// 400번대 에러 : 클라이언트가 잘못 요청
	// 500번대 에러 : 서버에서 처리과정에 오류가 발생

	// 예외 처리의 흐름 : 컨트롤러에서 예외가 발생 ->
	// 인터셉터 -> 서블릿 -> 필터 -> WAS

	// 오류 발생 처리 방법
	// 1. throw Exception
	// 2. response.sendError(HTTP 상태보드, 오류 메시지)

	// 스프링 부트 오류 페이지 처리 우선순위
	// 1. 뷰 템플릿
	// resources/templates/error/500.html
	// resources/templates/error/5xx.html

	// 2. 정적 리소스
	// resources/static/error/500.html
	// resources/static/error/5xx.html

	// 3. 대상이 없을 경우
	// resources/templates/error.html

	@GetMapping("error-ex")
	public void errorEx() {
		throw new RuntimeException("예외 발생");
	}

	@GetMapping("error-404")
	public void error404(HttpServletResponse response) throws IOException {
		response.sendError(404, "404 오류 발생");
	}

	@GetMapping("error-403")
	public void error403(HttpServletResponse response) throws IOException {
		response.sendError(403, "403 오류 발생");
	}

	@GetMapping("error-500")
	public void error500(HttpServletResponse response) throws IOException {
		response.sendError(500, "500 오류 발생");
	}

}
