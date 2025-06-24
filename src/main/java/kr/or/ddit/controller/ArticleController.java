package kr.or.ddit.controller;

import kr.or.ddit.dto.ArticleForm;
import kr.or.ddit.entity.Article;
import kr.or.ddit.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

// 컨트롤러 선언 : 해당 파일이 컨트롤러임을 선언합니다.(@Controller)
// Lombok의 log를 출력할 때 사용할 .info, .debug와 같은 메서드를 활용할 때 @Slf4j 어노테이션을 활용합니다.
@Slf4j
@Controller
public class ArticleController {

    // articleRepository 객체 선언
    // 스프링부트가 미리 생성해 놓은 Repository 객체 주입(Dependency Injection)
    // @AutoWired 어노테이션은 스프링 부트에서 제공하는 어노테이션으로 이를 컨트롤러의 필드에 붙이면 스프링 부트가 만들어 놓은 객체를
    // 가져와 객체 주입을 해준다
    // 이를 의존성 주입(DI; Dependency Injection) 이라고 한다.
    @Autowired
    private ArticleRepository articleRepository;

    // URL 요청 접수
    // 뷰(등록페이지) 페이지를 보여주기 위해 newArticleForm() 메서드를 추가
    @GetMapping("/articles/new")
    public String newArticleForm(){
        // 반환값 작성 : 뷰 페이지의 이름을 적습니다.
        // articles 디렉토리를 만들고, new.mustache 뷰 페이지를 추가합니다.
        return "articles/new";
    }
    // URL 요청 접수
    // @GetMapping 대신 @PostMapping 어노테이션을 사용합니다.
    // 뷰 페이지에서 폼 데이터를 POST 방식으로 전송했으므로 컨트롤러에서 받을 때도 @PostMapping으로 받는다.
    // 이때, 괄호 안에는 받는 URL 주소를 넣는다.
    // new.mustache 에서 form 태그에 action 속성에 맵핑된 '/articles/create'로 설정했기 때문에 맵핑될 수 있다.
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        // ArticleForm 타입의 form 객체를 매개변수로 선언합니다.
        // 폼에서 전송한 데이터가 dto에 잘 담겼는지 확인하기 위해서 출력문을 추가함
        
        // 로깅을 사용하기 위해 클래스 명 위에 @Slf4j 어노테이션을 사용합니다.
        // dto에 입력한 데이터가 정상적으로 바인딩되어 넘어오기 위해서는 mustache 파일에 작성된 form 태그 내에
        // 입력 태그들의 name과 ArticleForm 클래스 내 필드명이 같아야만 값이 정상적으로 바인딩 되어 돌어올 수 있습니다.
        log.info("createArticle->form :" + form.toString());

        // JPA를 이용해 엔티티를 db에 저장하기
        // 1. dto를 엔티티로 변환
        Article article = form.toEntity();
        // dto가 엔티티로 잘 변환되었는지 확인함.
        log.info("createArticle->form: " + article.toString());
        
        // 2. 레퍼지터리로 엔티티를 db에 저장
        // entity로 변환된 article 데이터를 저장 후 saved 객체에 반환
        Article saved = articleRepository.save(article);
        // article이 db에 잘 저장되는지 확인
        log.info("createArticle->article: " + saved.toString());
        
        // createAricle->form : ArticleForm{title='제목입니다01',content='내용입니다01'}
        // createArticle->article : Article{id=null, title='제목입니다01', content='내용입니다01'}
        // createArticle->article : Article{id=1, title='제목입니다01', content='내용입니다01'}
        // entity로 변환된 Article에 id가 null인걸 확인할 수 있고, 데이터가 저장된 후의 Aricle에 id가 자동 증가도니 값으로 1이 된걸
        // 확인할 수 있습니다. 결론적으로는 입력 폼을 통해서 전달받은 데이터를 dto에 담고 해당 데이터를 기반으로 entity를 구성한 후,
        // Repository의 save() 메소드를 이용해 db에 데이터를 저장합니다.
        // 저장한 후에 반환된 Article 객체의 데이터를 확인한 결과 id가 1로 자동증가 된 객체를 확인할 수 있습니다.
        
        // 리다이렉트를 작성할 위치
        // 리다이렉트는 클라이언트의 요청을 받아 새로운 URL 주소로 재요청하라고 지시하는 것
        // 형식 :: return "/articles/:URL 주소";
        return "redirect:/articles/" + saved.getId();
    }
    // 데이터 조회 요청 접수
    @GetMapping("/articles/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        //@PathVariable() 어노테이션은 URL에 포함되어 있는 즉, 요청 경로에 포함되어 돌아오는 경로 파라미터의 값을 가져올 때 사용합니다.
        // 이때, '{}'에 설정된 파라미터 키 값이 해당 메소드의 파라미터 변수와 동일해야함 데이터를 바인딩 할 수 있습니다.
        // 스프링 부트에서는 @PathVariable 어노테이션에 꼭 -optionParameter 설정을 해주어야만 한다.(그렇지 않으면 에러 발생)
        // - [java.lang.Long] not specified, and parameter name information not available via reflection...
        // - @PathVariable("id") Long id와 같이 작성해야함
        log.info("요청 경로에 포함된 파라미터 id : " + id);

        // 1. id를 조회해 데이터 가져오기
        // findById()는 JPA의 CrudRepository가 제공하는 메서드로, 특정 엔티티의 id값을 기준으로 데이터를 찾아 Optional 타입으로 반환한다.
        // id와 일치하는 데이터를 찾아, Optional 타입으로 반환,
        // Article articleEntity = articleRepository.findById(id);          // 에러 (X) :: Optional 반환 타입과 일치 X
        // Optional<Article> articleEntity = articleRepository.findById(id) // 정상 (O) :: Optional 반환 타입으로 설정
        // 우리가 활용할 데이터의 규칙은 Article 객체를 반환받은 후, 상세보기 페이지로 Model에 담아 전달하고자 한다.
        // 그렇기 때문에, Optional 타입을 활용하여 넘기지 않고 Article과 같은 타입으로 반환해 넘길 수 있도록 정의합니다.
        // [orElse 적용]
        // orElse(null) : id 값으로 데이터를 찾을 때 해당 id와 일치하는 게시글 데이터가 없으면 null을 반환하고,
        //                  값이 있으면 articleEntity 변수에 Article 객체 데이터를 넣어 반환한다.
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        // 'article'이라는 키로 value인 articleEntity 객체를 추가한다.
        model.addAttribute("article",articleEntity);

        // 3. 뷰 페이지 반환하기
        // 뷰 페이지는 articles라는 디렉터리 안에 show라는 파일이 있다는 의미입니다.
        return "articles/show";
    }

    // 데이터 목록 요청 접수
    @GetMapping("/articles")
    public  String index(Model model){  // index() 메소드의 매개변수로 Model 객체를 받아옴(목록 데이터 전달 위함)
        // 태스팅이란 형변환이라고도 하며 데이터 타입을 변환하는 것을 말합니다.
        // 자바에서 상속 관계가 있는 특정 객체는 상황에 따라 더 넓은 범위로 해석될 수도 있고, 때때로 좁은 범위로 해석될 수도이 있습니다.
        // 이때, 넓은 범위로 해석하는 것을 업캐스팅(Upcasting), 좁은 점위로 해석하는 것을 다운 캐스팅(DownCasting)이라고 합니다.
        // 예를 들어 고양이을 생물로 해석했다면 업캐스팅이고, 생물에서 다시 동물로 해석했다면 다운캐스팅입니다.

        // 1. 모든 데이터 가져오기
        // findAll() 메소드는 원래 반환 타입이 Iterable<> 이지만, ArrayList로 재정의 했기 때문에, 다운 캐스팅입니다.
        // Iterable<Article? articleEntity = articleRepository.findAll(); 은 업캐스팅으로 데이터 변환
        // Interable(I) <- Collection(I) <- List(I) <- ArrayList(C)
        ArrayList<Article> articleEntityList =  articleRepository.findAll();

        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);

        // 3 뷰 페이지 설정하기
        // articles 디렉토리 안에 index.mustach 파일이 뷰 페이지로 설정
        return "articles/index";
    }

    // 데이터 수정 화면 요청 접수
    // URL 주소에 있는 id를 받아오는 것이므로 데이터 타입 앞에 @PathVariable 어노테이션을 추가한다.
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){
        // 1. db에서 수정할 데이터 가져오기
        // db에서 데이터를 가져올 때는 Repository를 이용합니다.
        // 만약 데이터를 찾지 못하면 null을 반환하고, 데이터를 찾았다면 Article 타입의 articleEntity로 작성합니다.
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        // articleEntity를 article로 등록
        model.addAttribute("article",articleEntity);

        return "articles/edit";
    }

    // URL 요청 접수
    // 매개변수로 dto 받아오기
    // 수정의 기능이라면 HTTP 메서드 방식의 PATCH를 사용해야 하지만, form 태그가 지원하는 메소드 방식은
    // GET,POST가 전부임. (RESTFul 방식이라면 다양한 Method 방식을 지원하여 기능 개발이 가능)
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        // 웹 서비스에 사용하는 프로토콜(대표 메서드)
        // POST : 데이터 생성 요청 (insert)
        // GET : 데이터 조회 요청(select)
        // PATCH(PUT) : 데이터 수정 요청(update)
        // DELETE : 데이터 삭제 요청(delete)
        log.info("update->form: " + form.toString());

        // 1. dto를 엔티티로 변환
        // dto(form)를 엔티티(articleEntity)로 변환
        Article articleEntity = form.toEntity();
        log.info("update->articleEntity : " + articleEntity);

        // 2. 엔티티를 db에 저장
        // 데이터를 새로 생성하는 것이 아니라 수정하려는 것이다.
        // db에서 id와 일치하는 게시글 정보를 Repository의 findById() 메소드를 이용해 가져와 Article 타입의 객체로 저장
        // 데이터가 없다면 null을 반환한다.
        // 2-1 db에서 기존 데이터를 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2 기존 데이터 값을 갱신하기
        // 기존 데이터가 존재한다면, 수정할 수 있는 데이터가 존재하게 되는 경우
        if(target != null){
            articleRepository.save(articleEntity);  // 엔티티를 db에 저장(갱신)
        }


        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

    // URL 요청 접수
    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra){
        log.info("삭제 요청 들어옴...!");

        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 대상 엔티티 삭제하기
        // 삭제할 대상이 있는지 확인
        if(target != null){
            // delete() 메소드로 대상 삭제
            articleRepository.delete(target);

            // 일회성 메시지를 전달하기 위한 설정
            // RedirectAttributes 객체는 객체명에서도 힌트를 얻을 수 있듯이 페이지 이동방식이 리다이렉트 인 경우 일회성 데이터를
            // 전달할 수 있습니다.
            ra.addFlashAttribute("msg","삭제가 완료 되었습니다.");
        }
        
        // 3. 결과 페이지로 리다이렉트 하기
        return "redirect:/articles";
    }

}
