package com.aeon.library.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_book", indexes = {
        @Index(name = "t_article_deleted_idx", columnList = "deleted"),
        @Index(name = "t_article_created_at_idx", columnList = "createdAt"),
        @Index(name = "t_article_updated_at_idx", columnList = "updatedAt")
})
@SQLRestriction("deleted = false")
@SQLDelete(sql = "UPDATE {h-schema}t_book SET deleted = true WHERE id = ?")
public class Book extends BaseEntity {
    private String title;
    private String author;
    private String ISBN;

    @Column(columnDefinition = "boolean default false")
    private boolean borrowed;

    @ManyToOne(fetch = LAZY)
    private Borrower borrower;
}
