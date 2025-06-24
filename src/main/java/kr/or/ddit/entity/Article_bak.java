package kr.or.ddit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


// 1. 엔티티 선언
// 해당 클래스가 엔티티임을 선업합니다.
// Entity는 JPA에서 제공하는 어노테이션으로, 해당 어노테이션이 붙은 클래스를 기반으로
// db에 테이블이 생성됩니다.
// 테이블 이름은 클래스 이름과 동일하게 Article로 생성됩니다.
@Entity
public class Article_bak {
    // PK와 같은 대표값을 id로 선언합니다.
    // 대표값은 사람으로 치면 주민번호와 같습니다.
    // Article 엔티티 중에서 제목과 내용이 같을 때, 대표값 id로 다른 게시글임을 확인할
    // 수 있습니다.
    // @Id : Entity와 대표값 설정
    // @GeneratedValue : 자동 생성 기능(PK는 번호값으로 자동증가의 값으로 활용 )
    @Id
    @GeneratedValue
    private Long id;
    // dto 코드를 작성할 때와 같이 title, content 필드를 선언합니다.
    // 두 필드도 db에서 인식할 수 있도록 @Column을 붙인다.
    // title 필드 선언, db 테이블의 title 열과 연결합니다.
    @Column
    private String title;
    // content 필드 선언, db테이블의 content 열과 연결됩니다.
    @Column
    private String content;

    public Article_bak(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
