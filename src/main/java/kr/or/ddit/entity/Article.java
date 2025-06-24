package kr.or.ddit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


// dto는 vo의 역할을 하는 클래스를 모아둘 수 있는 영역입니다.
// 해당 클래스가 데이터를 받아 올 그릇이 됩니다.
//@NoArgsConstructor 어노테이션은 ArticleForm 클래스에 선언된 필드를 사용하지 않고 기본 생성자를 생성할 때 사용합니다
//@Getter 와 @Setter 어노테이션은 dto 클래스의 getter/setter 메서드를 만들 때 사용합니다.
//@ToString 어노테이션은 toString() 메소드를 자동 생성할 때 사용합니다.
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Entity
public class Article {
    // PK와 같은 대표값을 id로 선언합니다.
    // 대표값은 사람으로 치면 주민번호와 같습니다.
    // Article 엔티티 중에서 제목과 내용이 같을 때, 대표값 id로 다른 게시글임을 확인할
    // 수 있습니다.
    // @Id : Entity와 대표값 설정
    // @GeneratedValue : 자동 생성 기능(PK는 번호값으로 자동증가의 값으로 활용 )
    // - strategy = GenerationType.IDENTITY : 중복되지 않는 pk 번호를 만들어서 자동 생성해준다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // dto 코드를 작성할 때와 같이 title, content 필드를 선언합니다.
    // 두 필드도 db에서 인식할 수 있도록 @Column을 붙인다.
    // title 필드 선언, db 테이블의 title 열과 연결합니다.
    @Column
    private String title;
    // content 필드 선언, db테이블의 content 열과 연결됩니다.
    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null){
            this.title = article.getTitle();
        }
        if(article.content != null){
            this.content = article.getContent();
        }
    }
}
