package kr.co.swadpia.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditingAt implements Serializable {
    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Comment("생성일시")
    @Column(name = "created_at", insertable = true, updatable = false)
    @JsonFormat(timezone = "GMT+09:00")
    protected Timestamp createdAt;

    @LastModifiedDate
    @Comment("수정일시")
    @Column(name = "updated_at", insertable = true, updatable = true)
    @JsonFormat(timezone = "GMT+09:00")
    protected Timestamp updatedAt;

    public AuditingAt() {
        createdAt = new Timestamp(new Date().getTime());
        updatedAt = new Timestamp(new Date().getTime());
    }

}
