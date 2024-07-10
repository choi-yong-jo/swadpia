package kr.co.swadpia.test.document;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Slf4j
@Document(indexName = "employee")
public class EmployeeDocument {

	@Schema(description = "ID(추가시에는 자동생성)",
		defaultValue = "1",
		name = "id",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	@Id
	private String id;

	@Schema(description = "사번",
		defaultValue = "SW000001",
		name = "employeeNo",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	//사번
	private String employeeNo;

	@Schema(description = "이메일",
		defaultValue = "swadpia@swadpia.co.kr",
		name = "email",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	//이메일
	private String email;

	@Schema(description = "성명(한글)",
		defaultValue = "김성원",
		name = "nameKr",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	//성명(한글)
	private String nameKr;

	@Schema(description = "휴대폰번호",
		defaultValue = "010-7777-7777",
		name = "mobile",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	//휴대폰번호
	private String mobile;

	@Schema(description = "직급코드",
		defaultValue = "01",
		name = "positionCode",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	//직급코드
	private String positionCode;

	@Schema(description = "사업장",
		defaultValue = "충무로",
		name = "workplaceCode",
		type = "String",
		requiredMode = Schema.RequiredMode.AUTO)
	//사업장
	private String workplaceCode;

	@Schema(description = "근무형태 (정규직or비정규직)",
		defaultValue = "정규직",
		name = "employeeCode",
		type = "String")
	//근무형태 (정규직or비정규직)
	private String employeeCode;

}
