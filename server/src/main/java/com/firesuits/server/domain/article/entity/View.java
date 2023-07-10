package com.firesuits.server.domain.article.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "view")
@Entity
public class View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "view_id", updatable = false)
    private Long viewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "view_count")
    private Integer viewCount;

    public View(Article article, Integer viewCount) {
        this.article = article;
        this.viewCount = viewCount;
    }
}
