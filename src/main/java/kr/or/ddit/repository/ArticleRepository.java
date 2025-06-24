package kr.or.ddit.repository;

import kr.or.ddit.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
;
// dto를 활용해 entity로 변환한 데이터를 레포지터리를 통해 관리하기 위해서 CrudRepository 인터페이스를 가용
// CrudRepository<Article, Long>
// Article : 관리 대상 엔티티의 클래스 타입 (현 애플리케이션에서는 Article이 해당됩니다.)
// Long : 관리 대상 엔티티의 대표값 타입 (현 애플리케이션에서는 대표값에 해당하는 id)
// id의 타입은 Long 이므로 Long을 입력합니다.
public interface ArticleRepository extends CrudRepository<Article,Long> {
    // ArticleRepository 객체는 CrudRepository가 제공하는 기능을 별도 정의 없이 그대로 사용할 수 있습니다.
    // db에 데이터를 생성, 조회, 수정, 삭제하는 기본 동작을 추가 코드로 구현할 필요없이 CrudRepository에서 상속받아 사용할 수 있습니다.
    
    // 기존 Iterable<Article> 타입을 ArrayList 수정
    @Override
    ArrayList<Article> findAll();
}
