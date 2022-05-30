package com.raferlyan.words.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

/**
 * @author raferlyan
 * @date 2022/5/30 08:36
 **/
@Getter
@Setter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "WORDS")
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "WORD")
    private String words;

    @Column(name = "TRANSLATION")
    private String translation;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "DISPLAY_ORDER")
    private int displayOrder;

    @Column(name = "IS_DELETED",columnDefinition = "SMALLINT")
    private Boolean isDeleted;

    @Column(name = "DELETED_AT")
    private Date deletedAt;
}
