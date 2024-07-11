package kr.co.swadpia.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingAtBy extends AuditingAt {
    private static final long serialVersionUID = 1L;

    @LastModifiedBy
    @Column(name = "member_id", insertable = true, updatable = true)
    protected Long memberId;
}
