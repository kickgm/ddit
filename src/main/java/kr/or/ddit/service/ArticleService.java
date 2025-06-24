package kr.or.ddit.service;

import kr.or.ddit.dto.ArticleForm;
import kr.or.ddit.entity.Article;
import kr.or.ddit.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// 서비스
// 컨트롤러와 Repository 사이에서 서버의 핵심 기능(비즈니스 로직)을 처리하는 순서를 총괄한다.
// 서비스란 컨트롤러와 Repository 사이에 위치하는 계층으로, 서버의 핵심 기능을 처리하면서 Repository가
// 전달하는 데이터를 컨트롤러로 전달하는 역할도 함께 한다.
// 식당을 예로들면, 웨이터에게 주문이 들어오면 이를 전달받은 주방장이 요리를 총괄합니다.
// 재료가 필요하면 보조 요리사가 이를 가져오고, 여기서 웨이터는 컨트롤러, 주방장은 서비스, 보조 요리사는 Repository로 볼 수 있다.
// 손님(클라이언트)이 음식을 주문하면 웨이터(컨트롤러)가 이를 받아 주방장(서비스)에게 전달하고,
// 주방장(서비스)은 정해진 레시피에 따라 요리를한다.
// 요리에 필요한 재료(데이터)는 보조 요리사(Repository)가 창고에서 가져온다.
// 일반적으로 서비스 업무 처리는 트랜잭션 단위로 진행됩니다. 트랜잭션(transaction)이란 모두 성공해야
// 하는 일련의 과정을 뜻하고 쪼갤 수 없는 업무 처리의 최소 단위입니다.

// 서비스 객체 생성
// @Service 어노테이션을 선언하면 해당 클래스는 서비스를 담당한다는걸 명시한다.
// 그리고 서버가 runtime시, 객체화 되어 주입 시 활용할 수 있다.
// @Autowired 어노테이션을 활용하여 DI를 적용할 수 있는건 @Service 어노테이션이 명시되어 있을 때다.

@Slf4j
@Service
public class ArticleService {
    // Repository를 활용할 수 있도록 DI 적용(의존성 주입)
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        log.info("# service index()...!");
        // 메서드 수행 결과로 Article 묶음(리스트)을 반환하므로 반환형이 List<Article>이다.
        return articleRepository.findAll(); // db에 저장된 모든 Article을 가져와 반환한다.
    }

    public Article show(Long id) {
        log.info("# service show()...!");
        // Repository가 db에서 id로 조회한 결과를 반환하도록 return 문을 작성합니다.
        // 조회 결과 데이터가 없으면 null을 반환합니다.
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        // dto -> 엔티티로 변환한 후 article에 저장
        Article article = dto.toEntity();
        // 아래 조건을 부여하기 위한 상황
        // 등록 시, 새로운 데이터를 등록 할 때는 id를 없이 등록합니다.
        // 그런데, id를 넣고 등록 기능을 요청 시에 id와 일치하는 게시글 정보가 수정이 됩니다.
        // 이와 같은 문제를 해결하기 위해서 아래 내용이 필요합니다.
        
        // # 등록인데 수정과 같은 오류가 발생할 때
        // 등록인데 수정과 같이 id값이 파라미터로 전달된 경우, null을 반환한다.
        if(article.getId() != null){
            return  null;
        }
        // article을 db에 저장
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. dto -> 엔티티 변환하기(수정용 엔티티 생성)
        // 클라이언트에서 받은 수정 데이터가 담긴 dto를 db에서 활용할 수 있도록 엔티티로 변환해
        // article 변수에 저장한다.
        Article article = dto.toEntity();
        // 중간에 실행이 잘 되는지 확인하기 위해서 id와 article의 내용을 로그로 출력합니다.
        log.info("id : {}, artilce : {}",id,article.toString());

        // 2. 타깃 조회하기 (db에 대상 엔티티가 있는지 조회)
        // db에서 대상 엔티티를 조회해 가져온다.
        // .findById(id) 메소드를 통해서 id에 해당하는 엔티티를 가져오되 없다면 null을 반환한다.
        Article target = articleRepository.findById(id).orElse(null);
        
        // 3. 잘못된 요청 처리하기(대상 엔티티가 없거나 수정하려는 id가 잘못 됐을 경우 처리)
        // - 요청 경로로 들어온 id와 일치하는 Article 정보가 없는 경우도 에러이므로 null 체킹
        // - 요청 경로로 들어온 id와 수정 데이터로 넘어온 id로 얻어온 Article 정보의 id가 불일치 하는
        //   경우, 수정하고자 하는 데이터의 정보가 다르므로 에러
        if(target ==null || id != article.getId()){
            log.info("update-> 잘못된 요청 id : {}, article : {}",id,article.toString());
            // ResponseEntity 반환
            // ResponseEntity의 상태(Status)에는 400 또는 HttpStatus.BAD_REQUEST를 설정하고
            // 본문(body)에는 반환할 데이터가 없으므로 null을 실어 반환한다.
            return null;
        }

        // 4. 업데이트 및 정상 응답(200) 하기
        //Article updated = articleRepository.save(article);

        // 수정을 하기위해서 넘긴 수정 데이터안에는 제목 or 내용 or 제목,내용이 들어있다.
        target.patch(article);
        // 대상 엔티티가 있으면 수정 내용으로 업데이트하고 정상 응답(200)을 보낸다.
        // article 엔티티에 담긴 수정용 데이터를 db에 저장 후 updated라는 이름의 변수에 저장한다.

        // 전체 데이터 수정과 단일 데이터 수정으로 테스트를 진행
        // 이때, 전체 내용을 입력 후 수정 해보면 전체 데이터가 잘 수정되는걸 확인할 수 있습니다.
        //{
        //      "id":1,
        //      "title":"게시글 수정",
        //      "content":"게시글 내용 수정"
        //}
        // 하지만, 단일 데이터만 수정했을 때 수정되지 않은 항목은 null로 변경됩니다.
        //{
        //      "id":1,
        //      "title":"게시글 수정111",
        //}
        // 그렇기 때문에 단일 수정 시 수정하지 않은 항목은 유지하면서 나머지 항목만 수정되도록 설정이
        // 필요합니다. id를 통해 조회한 기존 데이터에 해당하는 target 정보를 수정하기 위해 던진 article
        // 데이터로 데이터가 있는 항목에 대해서만 수정 후, 수정하기 위해서 repository로 target을 던지면
        // 단일 데이터 수정이 가능합니다.

        // 기존 데이터에서 새롭게 수정된 데이터를 합쳐 놓은게 target 이므로
        Article updated = articleRepository.save(target);
        return updated;
    }


    public Article delete(Long id) {
        // 1. 대상 찾기 (db에서 대상 엔티티가 있는지 조회)
        // db에 삭제할 대상 엔티티가 있는지 조회하고 없으면 null을 반홚나다.
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리하기(대상 엔티티가 없어서 요청 자체가 잘못됐을 경우 처리)
        // 응답은 컨트롤러가 하므로 여기서는 null을 반환
        if(target == null){
            return null;
        }
        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return target;
    }
}
