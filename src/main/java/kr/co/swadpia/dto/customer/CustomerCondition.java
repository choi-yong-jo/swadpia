package kr.co.swadpia.dto.customer;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@ParameterObject
public class CustomerCondition {

    @Schema(description = "검색어")
    String keyword;

    @Schema(description = "시작일")
    String start;

    @Schema(description = "종료일")
    String end;

    @Schema(description = "페이지", example = "0")
    Integer page;

    @Schema(description = "페이지 사이즈", example = "10")
    Integer size;

    @Schema(description = "정렬", example = "DESC")
    String sort;

    @Schema(description = "정렬기준", hidden = false, example = "customerNo")
    String sortBy;

    public Pageable getPageable() {
        return PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(sort), sortBy));
    }

}
