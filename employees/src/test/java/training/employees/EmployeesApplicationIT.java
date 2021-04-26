package training.employees;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class EmployeesApplicationIT {

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	EmployeesService employeesService;

	@BeforeEach
	void init() {
		RestAssuredMockMvc.requestSpecification = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON);
		RestAssuredMockMvc
				.webAppContextSetup(webApplicationContext);

		employeesService.deleteAll();
	}

	//@Test
	@RepeatedTest(30)
	void list_all_employees() {
		with()
				//.body(new CreateEmployeeCommand("Jack Doe")).
		.body("{\"name\": \"Jack Doe\"}")
				.when()
				.post("/api/employees")
				.then()
				.body("name", equalTo("Jack Doe"))
		.log();


		when()
				.get("/api/employees")
				.then()
				.body("[0].name", equalTo("Jack Doe"));
	}

}
