package com.myproject.board.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp //생성시 시간을 만들어주는
    @Column(updatable = false) //수정시에는 관여 x
    private LocalDateTime createdTime;

    @UpdateTimestamp //업데이트 발생시 시간을 만들어주는
    @Column(insertable = false) //입력시에는 관여 x
    private LocalDateTime updatedTime;
}
