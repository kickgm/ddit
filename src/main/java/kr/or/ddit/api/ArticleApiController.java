package kr.or.ddit.api;

import kr.or.ddit.dto.ArticleForm;
import kr.or.ddit.entity.Article;
import kr.or.ddit.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    
    /*
        Article 데이터를 CRUD하기 위한 REST API 주소 설계
        - 조회 요청
            > /api/articles 또는 /api/articles/{id}
            > GET 메서드로 Article 목록 전체 또는 단일 Article을 조회합니다.
            
        - 생성 요청
            > /api/articles
            > POST 메서드로 새로운 Article을 생성해 목록에 저장합니다.
            
         - 수정 요청
            > /api/articles/{id}
            > PATCH 메서드로 특정 Article의 내용을 수정합니다.
            
         - 삭제 요청
            > /api/articles/{id}
            > DELETE 메서드로 특정 Article을 삭제합니다.
     */
    @Autowired
    private ArticleService articleService;

    // GET 방식
    // 게시글 목록 요청
    @GetMapping("/api/articles")
    public List<Article> index(){
        // 서비스를 통해 데이터를 가져온다. 그리고 가져온 데이터 그대로 응답으로 전달
        return articleService.index();
    }
    // 게시글 상세조회 요청
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable("id") Long id){
        return articleService.show(id);
    }

    // POST
    // 게시글 등록 요청
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);

        return  (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    // 게시글 수정 요청
    @PatchMapping("/api/articles/{id}")
    public  ResponseEntity<Article> update(@PathVariable("id")Long id,@RequestBody ArticleForm dto){
        // 서비스를 통해 게시글 수정
        Article updated = articleService.update(id,dto);

        // 수정 되면 정상, 실패하면 오류 응답
        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 게시글 삭제 요청
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable("id") Long id){
        Article deleted = articleService.delete(id);

        // 삭제 결과에 따른 응답 처리
        // NO_CONTENT : 상태코드 204
        // BAD_REQUEST : 상태코드 400
        return  (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
