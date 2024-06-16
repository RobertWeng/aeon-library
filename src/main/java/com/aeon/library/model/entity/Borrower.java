package com.aeon.library.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_borrower", indexes = {
        @Index(name = "t_borrower_deleted_idx", columnList = "deleted"),
        @Index(name = "t_borrower_created_at_idx", columnList = "createdAt"),
        @Index(name = "t_borrower_updated_at_idx", columnList = "updatedAt")
})
@SQLRestriction("deleted = false")
@SQLDelete(sql = "UPDATE {h-schema}t_user SET deleted = true WHERE id = ?")
public class Borrower extends BaseEntity {
    private String email;
    private String name;
}
