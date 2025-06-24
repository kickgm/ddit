//package kr.or.ddit.controller;
//
//import kr.or.ddit.dto.ArticleForm;
//import kr.or.ddit.entity.Article;
//import kr.or.ddit.repository.ArticleRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Slf4j
//@Controller
//public class ArticleController_bak {
//
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    @GetMapping("/articles/new")
//    public String newArticleForm(){
//        return "articles/new";
//    }
//
//    @PostMapping("/articles/create")
//    public String createArticle(ArticleForm form){
//        log.info("createArticle->form :" + form.toString());
//
//        Article article = form.toEntity();
//        log.info("createArticle->form: " + article.toString());
//
//        Article saved = articleRepository.save(article);
//        log.info("createArticle->article: " + saved.toString());
//
//        return "/articles";
//    }
//}
