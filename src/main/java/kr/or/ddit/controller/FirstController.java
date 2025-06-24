package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//1. 어노테이션(annotation) 이란?
// 소스 코드에 추가해 사용하는 메타 데이터의 일종입니다.
// 메타 데이터는 프로그램에서 처리해야 할 데이터가 아니라 컴파일 및 실행 과정에서 코드를 어떻게 처리해야할지
// 알려주는 추가 정보입니다.
// 자바에서는 어노테이션 작성 시, 앞에 '@'기호를 붙여 사용합니다.

// 컨트롤러 선언과 동시에 자동으로 임포트
// 컨트롤러 선언 :해당파일이 컨트롤러임을 선언합니다.
@Controller
public class FirstController {

    // niceToMeetYou() 메소드 작성
    // URL 요청 접수 : 클라이언트로부터 '/hi'라는 요청을 받아 접수
    // '/hi'라는 요청을 받음과 동시에 niceToMeetYou() 메소드 수행
    // Model : 뷰 템플릿 페이지에서 사용할 변수를 등록하기 위해 모델 객체를 매개변수로 가져옵니다.
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        // 컨트롤러 내 메소드는 요청 하나를 받을 목적지와도 같습니다.
        // 해당 목적지는 브라우저에 입력된 URL과 맵핑되는 request를 가지고 있고, 응답으로 나갈 정보는
        // 결과 페이지를 만들기 위한 페이지 정보입니다.
        // 응답으로 설정된 'greetings'는 templates 폴더 내 작성된.mustache 확장자를 가진 파일들 중,
        // greetings로 시작하는 파일을 찾습니다. 찾은 파일을 이용하여 응답 결과물을 만들어 브라우저에
        // 응답으로 내보냅니다

        // Model 이라는 데이터 전달자를 이용해 'username'이라는 key로 '홍길동'이라는 데이터를 설정
        model.addAttribute("username","홍길동");
        // greetings.mustache 파일 변환
        // greetings 문자열을 입력하게 되면 해당 문자열을 서버가 알아서 templates 디렉토리 내에서
        // 같은 이름의 mustache 파일을 찾아 웹 브라우저로 전송합니다. (템플릿 엔진으로 mustache를 활용
        // 하고 있기 때문에 우선적으로 페이지를 찾는 확장자는 mustache가 된다)
        return "greetings";
    }

    // URL 요청 접수
    // 1. 컨트롤러는 @Controller 내부에 @GetMapping 어노테이션을 통해 클라이언트의 요청을 받는다.
    //     이때, 해당 요청은 Get방식의 Method 방식으로 들어온 요청 이기 때문에 Get방식의 요청을 받기
    //     위한 어노테이션 @GetMapping을 활용합니다.
    // '/bye' 요청을 처리합니다.
    @GetMapping("/bye")
    public String seeYouNext(Model model){
        // 등록할 변수명과 변수값을 적어줍니다.
        // Model 이라는 데이터 전달자를 통해 등록된 데이터는 해당 메소드의 응답 결과인 goodbye.mustache
        // 의 {{nickname}}자리의 변수 값으로 값을 받을 수 있다.
        model.addAttribute("nickname","도적");
        // 변환값은 요청에 따라 보여 줄 뷰 템플릿 페이지를 적습니다.
        return "goodbye";
    }

}
