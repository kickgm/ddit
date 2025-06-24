//package kr.or.ddit.dto;
//
//import kr.or.ddit.entity.Article;
//import lombok.NoArgsConstructor;
//
//
//public class ArticleForm_bak {
//    private long id;
//    private String title;
//    private String content;
//
//    public ArticleForm_bak() {}
//
//
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
//
//    public Article toEntity() {
//        return new Article(null, title, content);
//    }
//}
