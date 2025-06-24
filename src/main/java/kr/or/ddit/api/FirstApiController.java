package kr.or.ddit.api;

import kr.or.ddit.dto.ArticleForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// REST API용 컨트롤러
// @RestController 어노테이션은 @Controller와 @ResponseBody 어노테이션을 포함하고 있는 어노테이션입니다.
// 요청을 받기 위해 해당 클래스는 컨트롤러임을 명시하고 받은 요청을 응답으로 내보낼 시, 페이지 경로가 아닌 데이터가 응답으로 나갈 수
// 있도록 @ResponseBody 어노테이션을 활용할 수 있도록 @RestController 어노테이션을 명시합니다.
@RestController
public class FirstApiController {

    /*
        웹 서비스를 사용하는 클라이언트에는 웹 브라우저만 있는 것이 아닙니다.
        스마트폰, 스마트워치, 태블릿 ,CCTV, ESS 시스템, GIS 기반의 시스템 등등 각종 센서 등이 모두 클라이언트입니다.
        서버는 이러한 모든 클라이언트 요청에 응답해야 합니다. 웹 브라우저뿐만 아니라 어떤 기기가와도 기기에 맞는 뷰 페이지
        (view1, view2,...)를 응답해야 하죠.
        그런데 이런 기기들은 앞으로 끝없이 나올텐데 그때마다 서버가 일일이 대응하기란 쉽지 않습니다. 이런 조건을 해결하기 위한
        방법은 바로 REST API를 사용하는 방법입니다. REST API(Representational State Transfer API)는 서버의 자원을
        클라이언트에 구애받지 않고 사용할 수 있게 하는 설계 방식입니다.
        REST API 방식에서는 HTTP 요청에 대한 응답으로 서버의 자원을 반환합니다. 서버에서 보내는 응답이 특정 기기에 종속되지
        않도록 모든 기기에서 통용될 수 있는 데이터를 반환합니다.
        REST API에서 서버는 클라이언트의 요청에 대한 응답으로 화면(view)이 아닌 데이터(data)를 전송합니다.
        이때, 사용하는 응답 데이터는 JSON(JavaScript Object Notation)입니다. 과거에는 응답 데이터로 XML을 많이 사용했지만,
        최근에는 JSON을 보편적으로 많이 쓰고 있습니다.

        REST API를 개발하다 보면 HTTP Method 방식 설정이 필요합니다. 이때, GET,POST,PUT,PATCH,DELETE 등 다양한 Method 방식을
        선택하는데, Method 방식 중 PUT과 PATCH는 차이점이 존재합니다.

        # PUT과 PATCH의 차이점
        - PUT : 기존 데이터를 전부 새 내용으로 변경합니다. 만약 기존 데이터가 없다면 새로 생성
        - PATCH : 기존 데이터 중에서 일부만 새 내용으로 변경합니다.

        REST API를 통해 요청을 보내고 처리 여부에 따라 성공 또는 실패의 응답 결과를 받습니다.
        이때, 응답으로 내보낼 수 있는 여러 상태코드가 존재합니다.

        # HTTP 상태 코드
        - 1XX (정보) : 요청이 수신돼 처리중입니다.
        - 2XX (성공) : 요청이 정상적으로 처리됐습니다.
        - 3XX (리다이렉션 메시지) : 요청을 완료하려면 추가 행동이 필요합니다.
        - 4XX (클라이언트 요청 오류) : 클라이언트의 요청이 잘못돼 서버가 요청을 수행할 수 없습니다.
        - 5XX (서버 응답 오류) : 서버 내부에 에러가 발생해 클라이언트 요청에 대해 적절히 수행하지 못했습니다.
    */

    @GetMapping("/api/hello")
    public String hello(){
        return "hello world!";
    }

    @GetMapping("/api/data/list")
    public List<String> getListData(){
        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("홍길동");
        dataList.add("유재석");
        dataList.add("메뚜기");
        return  dataList;
    }

    @GetMapping("/api/data/articleList")
    public List<ArticleForm> getArticleList(){
        ArrayList<ArticleForm> dataList = new ArrayList<>();
        ArticleForm articleData1 = new ArticleForm();
        articleData1.setId(1L);
        articleData1.setTitle("제목1");
        articleData1.setContent("내용01");

        ArticleForm articleData2 = new ArticleForm();
        articleData2.setId(2L);
        articleData2.setTitle("제목2");
        articleData2.setContent("내용02");

        dataList.add(articleData1);
        dataList.add(articleData2);

        return  dataList;
    }

}
