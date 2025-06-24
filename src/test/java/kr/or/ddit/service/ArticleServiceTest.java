package kr.or.ddit.service;

import kr.or.ddit.dto.ArticleForm;
import kr.or.ddit.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest 어노테이션은 해당 클래스를 스프링 부트와 연동해 테스트할 수 있도록 설정
// 해당 어노테이션이 있으면 테스트 코드에서 스프링 부트가 관리하는 다양한 객체를 주입받을 수 있습니다.
@SpringBootTest
class ArticleServiceTest {

    // # 테스트
    // 테스트는 프로그램의 품질을 검증하는 것으로, 의도대로 프로그램이 잘 동작하는지 확인하는 과정입니다.
    // 테스트 초창기에는 사람이 직접 요청을 보내고 응답을 받아 일일이 확인하는 방식으로 진행하였습니다.
    // 하지만 이제는 테스트 도구를 이용해 반복적인 검증 절차를 자동화할 수 있습니다.
    //
    // # 테스트 코드의 작성 단계
    // 1. 예상 데이터 작성
    // 2. 실제 데이터 작성
    // 3. 예상 데이터와 실제 데이터를 비교해 검증

    // # 테스트 결과 처리
    // 테스트를 통과하면 지속적인 리팩토링으로 코드를 개선할 수 있고, 통과하지 못하면 디버깅을 통해 잘못된 부분을
    // 수정할 수 있습니다.
    //
     // 테스트 코드는 다양한 경우를 대비해서 작성하는데, 이를 테스트 케이스라고 합니다.
    // 테스트 케이스는 성공할 경우 뿐만 아니라 실패할 경우도 고려해야 하는데 다양한 상황을 예상해 세부적으로
    // 작성해야합니다. 테스트를 통한 코드 검증과 리팩토링은 개발자의 기본 소양이고 이를 기반으로 한 개발 방법론인
    // 테스트 주도 개발 또는 개발의 핵심 패러다임으로 자리 잡고 있습니다.
    // 테스트 주도 개발 (TDD, Test Driven Development)이란 일단 테스트 코드를 만든 후 이를 통과하는 최소한의
    // 코드부터 시작해 점진적으로 코드를 개선 및 확장해 나가는 개발 방식입니다.
    //
    // # 테스트를 진행하기 위한 메소드 종류
    // - assertEquals(expected, actual) : 두 값이 같은지 검증
    //     ex) assertEquals(100, 105);
    //  - assertNotEquals(expected, actual) : 두 값이 같지 않을때 검증
    //      ex) assertNotEquals(10,105);
    //  - assertTrue(condition) : 조건이 true 일 때 통과
    //      ex) assertTrue(3 >2);
    //  - assertFalse(condition) : 조건이 false 일 때 통과
    //      ex) assertFalse(3 < 2);
    //  - assertNull(object) : 객체가 null 일 때 통과
    //     ex) String str = null;
    //          assertNull(str);
    //  - assertNotNull(object) : 객체가 null이 아닐 때 통과
    //  - assertArrayEquals(expectedArray, actualArray) : 배열의 모든 요소가 동일한지 확인
    //      ex ) int[] arr1 = {1,2,3};
    //           int[] arr2 = {1,2,3};
    //          assertArrayEquals(arr1, arr2);
    //  - assertThrows(expectedException.class, executable) : 특정 예외가 발생할 때 통과
    //  - assertDoesNotThrow(executable) : 예외가 발생하지 않아야 통과
    //  - assertTimeout(duration, executable) : 실행 시간이 특정 시간 내에 완료됐을 때 통과
    //  - assertAll(executables...) : 여러개의 assert 종류를 한번에 실행하고 실패한 assert를 확인할 수 있다.

    // ArticleService 객체 주입(DI)
    @Autowired
    private  ArticleService articleService;

    @Test
    void index() {
        // 테스트 상황
        // - index() 메서드를 호출 했을 때, Article 객체의 정보 3건이 목록으로 출력된다.
        // - id의 값은 자동 증가된 값으로 설정된다.

        // 1. 예상 데이터
        // 예상 데이터는 data.sql에 작성된 쿼리 만큼 데이터가 생성되어 들어올테니까 총 3개의 index를 가진
        // 리스트 데이터가 들어옵니다.
        // 예상 데이터 객체로 저장
        Article a = new Article(1L,"개똥이의하루","즐거운여행");
        Article b = new Article(2L,"철수의하루","바닷가여행");
        Article c = new Article(3L,"은혜의하루","개울물여행");

        // Arrays.asList() 메서드로 합친 정적 리스트를 새 ArrayList로 만들어 expected에 저장 합니다.
        // Arrays.asList() 메소드는 입력된 배열 또는 2개 이상의 동일한 타입의 데이터를 정적 리스트로 만들어
        // 반환합니다. 그렇게 때문에 고정적인 크기이고 add(), remove()와 같은 함수를 사용할 수 없다.
        List<Article> expected = new ArrayList<>(Arrays.asList(a,b,c));

        // 2. 실제 데이터
        // 실제 데이터는 articleService.index() 메소드를 호출해 그 결과를 List<Article> 타입의
        // articles에 받아옵니다.
        // 모든 게시글을 조회 요청하고 그 결과로 반환되는 게시글의 묶음을 받아 옵니다.
        List<Article> articles = articleService.index();

        // 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success(){
        // 테스트 상황
        // - show() 메소드를 호출 했을 때, id 1번에 해당하는 게시글 정보가 출력된다.

        Long id = 1L;
        // 1. 예상 데이터
        Article expected = new Article(id,"개똥이의하루","즐거운여행");

        // 2. 실제 데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void show_success2(){
        // 테스트 상황
        // - show() 메서드를 호출 했을 때, 없는 id의 값에 해당하는 게시글 정보가 출력된다.(null)

        Long id = -1L;
        // 1. 예상 데이터
        // 예상 데이터는 존재하지 않는 id인 -1을 조회한다고 가정
        // 해당 경우 db에서 조회되는 내용이 없어 null을 반환할 것이므로 expected 객체에 null을 저장한다.
        Article expected = null;

        // 2. 실제 데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검증
        // 실제 데이터와 예상 데이터의 값 null은 toString() 메소드를 호출할 수 없으므로 첫번째와 두번째 모두
        // toString()을 뺀 데이터로 비교를 사용한다.
        assertEquals(expected,article);

    }
    
    @Test
    void show_failed(){
        // 테스트 상황
        // - show() 메소드를 호출 했을 때, id 1번 게시글 정보와 제목, 내용이 다른 예상 데이터를 비교한다.
        
        Long id = 1L;
        // 1. 예상 데이터
        // - 기존 등록되어 있는 첫번째 게시글 정보의 제목을 다르게 설정
        Article expected = new Article(id, "개똥이의하루","ddsf");

        // 2.  실제 데이터
        Article article =articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void create_success(){
        // 테스트 상황
        // - create() 메서드를 호출 했을 때, 새로운 게시글 정보 등록
        // - 등록 후, 등록된 데이터와 등록 후 예상 데이터가 일치하는지 확인

        // 1. 예상 데이터(등록과 일치 여부를 조회할 때 사용)
        // title, content 값 임의 등록
        String title = "개똥이의 여행4";
        String content = "즐거운 여행4";

        // 예상 데이터는 사용자가 새 게시물을 생성한 상황을 가정해 작성한다.
        // 그런데 id는 db에서 자동으로 생성하므로 작성할 필요가 없다.
        // 예상 데이터의 id, title, content를 저장하는데 id는 필드를 따로 선언하지 않았으므로
        // 자동으로 생성될 4L을 작성합니다.
        Article expected = new Article(4L, title, content);

        // 2. 실제 데이터
        ArticleForm dto = new ArticleForm(null, title, content);
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void create_failed(){
        // 테스트 상황
        // - 등록 기능인데 수정 이벤트를 요청했을 때
        // - id를 포함한 데이터로 create() 메서드를 호출 했을 때, 게시글 정보 등록
        // - 등록 후, 등록된 데이터와 등록 후 예상 데이터가 일치하는지 확인
        // 1. 예상 데이터(등록과 일치 여부를 조회할 때 사용)
        // id, title, content 값 임의 등록
        Long id = 4L;
        String title = "개똥이의 여행4";
        String content = "즐거운 여행4";
        // 위 3가지 항목으로 만든 Article 정보는 id를 포함된 데이터이나, 존재하지 않는 4번째 게시글 정보로
        // 등록 기능이 아닌 수정 기능을 요청했기 때문에 없는 데이터 null이 반환됩니다.
        // 그렇게 때문에 예상 데이터는 null
        Article expected = null;

        // 2. 실제 데이터
        ArticleForm dto = new ArticleForm(null, title, content);
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        // 예상 데이터와 실제 데이터를 비교
        assertEquals(expected,article);
    }

}