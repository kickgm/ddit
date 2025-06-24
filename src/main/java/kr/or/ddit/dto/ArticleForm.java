package kr.or.ddit.dto;

import kr.or.ddit.entity.Article;
import lombok.*;

// dto는 vo의 역할을 하는 클래스를 모아둘 수 있는 영역입니다.
// 해당 클래스가 데이터를 받아 올 그릇이 됩니다. 드디어!ㅇ

// @NoArgsConstructor 어노테이션은 ArticleForm 클래스에 선언된 필드를 사용하지 않고 기본 생성자를 생성할 때 사용합니다.
// @Getter와 @Setter 어노테이션은 dto 클래스의 getter/setter 메서드를 만들 때 사용합니다.
// @ToString 어노테이션은 toString() 메소드 자동 생성할 때 사용합니다.lksadjflkwjdlfk;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
