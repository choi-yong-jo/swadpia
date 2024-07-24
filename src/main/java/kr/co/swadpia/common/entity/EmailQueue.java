package kr.co.swadpia.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import kr.co.swadpia.common.constant.EmailType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
public class EmailQueue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailQueueId;
    @Enumerated(EnumType.STRING)
    private EmailType type;
    private String title;
    private String body;
    private String sendTo;
    private Boolean complete;
    @CreatedDate
    @Column(name = "CREATED_AT", insertable = true, updatable = false)
    @JsonFormat(timezone = "GMT+09:00")
    protected Timestamp createdAt;


}
