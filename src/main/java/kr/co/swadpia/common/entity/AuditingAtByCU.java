package kr.co.swadpia.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingAtByCU extends AuditingAt {
    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(updatable = false)
    @Comment("생성자ID")
    protected Long crateEmployeeId;

    @LastModifiedBy
    @Column(insertable = true, updatable = true)
    @Comment("수정자ID")
    protected Long modifyEmployeeId;
}
