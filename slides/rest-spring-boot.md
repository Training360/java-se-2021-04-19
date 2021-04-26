
class: inverse, center, middle

# Microservice alkalmazás felépítése Spring Boot keretrendszerrel Docker környezetben

---

class: inverse, center, middle

# REST webszolgáltatások GET művelet

---

## RESTful webszolgáltatások tulajdonságai

* Roy Fielding: Architectural Styles and the Design of Network-based <br /> Software Architectures, 2000
* Representational state transfer
* Alkalmazás erőforrások gyűjteménye, <br /> melyeken CRUD műveleteket lehet végezni
* HTTP protokoll erőteljes használata: <br /> URI, metódusok, státuszkódok
* JSON formátum használata
* Egyszerűség, skálázhatóság, platformfüggetlenség
* [Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)

---

## Annotációk

* `@RestController`, mintha minden metóduson `@ResponseBody` annotáció
    * Alapértelmezetten JSON formátumba
* `@RequestMapping` annotation helyett `@GetMapping`, `@PostMapping`, stb.

---

## Controller

```java
@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<EmployeeDto> listEmployees() {
        return employeesService.listEmployees();
    }
}
```

---

## Architektúra

![Architektúra](images/rest-architecture.png)

---

## Lombok

* Boilerplate kódok generálására, pl. getter/setter, konstruktor, `toString()`, <br />equals/hashcode, logger, stb.
* Annotation processor
* IntelliJ IDEA támogatás: plugin és _Enable annotation processing_
* `@Data` annotáció
    * `@ToString`, `@EqualsAndHashCode`, `@Getter` minden attribútumon, <br />`@Setter` nem final attribútumon és `@RequiredArgsConstructor`
* `@NoArgsConstructor`

```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
</dependency>
```

---

## Példa a Lombok használatára

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private long id;

    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
```

---

## ModelMapper

* Object mapping
* Hasonló struktúrájú osztályú példányok konvertálására <br />(pl. entitások és DTO-k között)
* Reflection alapú, intelligens alapértékekkel
* Fluent mapping API speciális esetek kezelésére


```xml
<dependency>
  <groupId>org.modelmapper</groupId>
  <artifactId>modelmapper</artifactId>
  <version>${modelmapper.version}</version>
</dependency>
```

---

## Példa a ModelMapper <br /> használatára

```java
@Bean
public ModelMapper modelMapper() {
  return new ModelMapper();
}
```

```java
Employee employee = // load
EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);

List<Employee> employees = // load
java.lang.reflect.Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
List<EmployeeDto> dtos = modelMapper.map(employees, targetListType);
```

---

class: inverse, center, middle

# GET műveletek paraméterezése

---

## URL paraméterek kezelése

* `@RequestParam` annotációval
* Kötelező, kivéve a `required = "false"` attribútum megadásakor
* Automatikus típuskonverzió


```java
public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
       return employeesService.listEmployees(prefix);
}
```

Elérhető a `/api/employees?prefix=Jack` címen

---

## URL részletek kezelése

* Osztályon lévő `@RequestMapping` és `@GetMapping` összeadódik

```java
@GetMapping("/{id}")
public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
    return employeesService.findEmployeeById(id);
}
```

Elérhető a `/api/employees/1` címen


---

## 12Factor hivatkozás: <br /> Stateless processes

* Csak egy kérés időtartamáig van állapot
  * Nem baj, ha elveszik
  * Nem kell cluster-ezni
  * Kevesebb erőforrás
  * Nincs párhuzamossági probléma
* Kérések közötti állapot: backing services
* Cache: inkább backing service, ne nőjön szignifikánsan <br /> az alkalmazás memóriaigénye
* Shared nothing: egy update csak egy node hatóköre

---

## Konkurrencia

* Ha állapotmentesen dolgozunk, nem okoz problémát
* Horizontális skálázás
* Backing service szintre kerül

---
class: inverse, center, middle

# REST webszolgáltatások POST és DELETE művelet

---

## POST és PUT művelet

* PUT idempotens

---

## Controller POST művelettel

* `@RequestBody` annotáció - deszerializáció, alapból JSON-ből Jacksonnel

```java
@PostMapping
public EmployeeDto createEmployee(
    @RequestBody CreateEmployeeCommand command) {
  return employeesService.createEmployee(command);
}

@PutMapping("/{id}")
public EmployeeDto updateEmployee(
    @PathVariable("id") long id,
    @RequestBody UpdateEmployeeCommand command) {
  return
    employeesService.updateEmployee(id, command);
}
```

---

## Controller DELETE művelettel

```java
@DeleteMapping("/{id}")
public void deleteEmployee(@PathVariable("id") long id) {
    employeesService.deleteEmployee(id);
}
```

---

class: inverse, center, middle

# Státuszkódok és hibakezelés

---

## Státuszkód állítása controller metódusból

* `ResponseEntity` visszatérési típus: státuszkód, header, body, stb.

```java
@GetMapping("/{id}")
public ResponseEntity findEmployeeById(@PathVariable("id") long id) {
    try {
        return ResponseEntity.ok(employeesService.findEmployeeById(id));
    }
    catch (IllegalArgumentException iae) {
        return ResponseEntity.notFound().build();
    }
}
```

---

## 201 - CREATED státuszkód

```java
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public EmployeeDto createEmployee(
    @RequestBody CreateEmployeeCommand command) {
  return employeesService.createEmployee(command);
}
```

---

## 204 - NO CONTENT státuszkód

```java
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteEmployee(@PathVariable("id") long id) {
    employeesService.deleteEmployee(id);
}
```

---

## Alapértelmezett válasz hiba

* Status code 500

```json
{
  "timestamp": 1596570258672,
  "status": 500,
  "error": "Internal Server Error",
  "message": "",
  "path": "/api/employees/3"
}

```

---

## Hibakezelés

* Servlet szabvány szerint `web.xml` állományban
* Exceptionre tehető `@ResponseStatus` annotáció
* Globálisan `ExceptionResolver` osztályokkal
* `@ExceptionHandler` annotációval ellátott metódus a controllerben
* `@ControllerAdvice` annotációval ellátott globális `@ExceptionHandler` <br /> annotációval ellátott metódus

---

## ExceptionHandler

```java
@ExceptionHandler(IllegalArgumentException.class)
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public void handleNotFound() {
    System.out.println("Employee not found");
}
```

---

## RFC 7807

* Problem Details for HTTP APIs
* `application/problem+json` mime-type

```json
{
    "type": "employees/invalid-json-request",
    "title": "JSON error",
    "status": 400,
    "detail": "JSON parse error: Unexpected character..."
}
```

---

## RFC 7807 mezők

* `type`: URI, mely azonosítja a hiba típusát
* `title`: ember által olvasható üzenet
* `status`: http státuszkód
* `detail`: részletek, ember által olvasható
* `instance`: URI, mely azonosítja a hibát, <br />és később is elérhető (pl. valamilyen log hivatkozás)
* Egyedi saját mezők definiálhatók

---

## org.zalando:problem

```xml
<dependency>
  <groupId>org.zalando</groupId>
  <artifactId>problem</artifactId>
  <version>${problem.version}</version>
</dependency>
<dependency>
  <groupId>org.zalando</groupId>
  <artifactId>jackson-datatype-problem</artifactId>
  <version>${problem.version}</version>
</dependency>
```

---

## A problem használata

```java
@ExceptionHandler({IllegalArgumentException.class})
public ResponseEntity<Problem> handleNotFound(IllegalArgumentException  e) {
    Problem problem = Problem.builder()
            .withType(URI.create("employees/employee-not-found"))
            .withTitle("Not found")
            .withStatus(Status.NOT_FOUND)
            .withDetail(e.getMessage())
            .build();

    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
    .contentType(MediaType.APPLICATION_PROBLEM_JSON)
      .body(problem);
}
```

```java
@Bean
public ObjectMapper objectMapper() {
  return new ObjectMapper()
  .findAndRegisterModules();
}
```

---

class: inverse, center, middle

# Integrációs tesztelés

---

## Web réteg tesztelése

* Elindítható csak a Spring MVC réteg: <br />`@SpringBootTest` helyett `@WebMvcTest` annotáció használata
* Service réteg mockolható Mockitoval, `@MockBean` annotációval
* `MockMvc` injektálható
    * Kérések összeállítására (path variable, paraméterek, header, stb.)
    * Válasz ellenőrzésére (státuszkód, header, tartalom)
    * Válasz naplózására
    * Válasz akár Stringként, JSON dokumentumként <br />(jsonPath)
* Nem indít valódi konténert, a Servlet API-t mockolja
* JSON szerializáció

---

## Web réteg tesztelése példa

.small-code-14[
```java
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
```

```java
@Test
void testListEmployees() throws Exception {
    when(employeesService.listEmployees(any())).thenReturn(List.of(
            new EmployeeDto(1L, "John Doe"),
            new EmployeeDto(2L, "Jane Doe")
    ));

    mockMvc.perform(get("/api/employees"))
      .andDo(print())
            .andExpect(status().isOk())        
            .andExpect(jsonPath("$[0].name", equalTo("John Doe")));
}
```
]

---

## Teljes alkalmazás tesztelése <br /> konténer nélkül

* `@SpringBootTest` és `@AutoConfigureMockMvc` annotáció

```java
@Test
void testListEmployees() throws Exception {
  mockMvc.perform(get("/api/employees"))
    .andExpect(status().isOk())
    .andDo(print())
    .andExpect(
      jsonPath("$[0].name", equalTo("John Doe")));
}
```

---

## Teljes alkalmazás tesztelése <br /> konténerrel

* `RANDOM_PORT`
* Port `@LocalServerPort` annotációval injektálható
* Injektálható `TestRestTemplate` - url és port előre beállítva
* JSON szerializáció és deszerializáció

```java
@SpringBootTest(webEnvironment =
    SpringBootTest.WebEnvironment.RANDOM_PORT)
```

---

## Teljes alkalmazás tesztelése <br /> konténerrel példa

```java
@Test
void testListEmployees() {
  List<EmployeeDto> employees = 
    restTemplate.exchange("/api/employees",
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<List<EmployeeDto>>(){})
    .getBody();
  
  assertThat(employees)
          .extracting(EmployeeDto::getName)
          .containsExactly("John Doe", "Jane Doe");
}
```

---

class: inverse, center, middle

# Swagger UI

---

## Swagger UI

* API dokumentáció generálására
* Az API ki is próbálható
* OpenAPI Specification (eredetileg Swagger Specification)
  * RESTful webszolgáltatások leírására
  * Kód és dokumentáció generálás
  * Programozási nyelv független
  * JSON/YAML formátumú
  * JSON Scheman alapul
* Keretrendszer független
* Annotációkkal személyre szabható

---

## springdoc-openapi projekt

* Swagger UI automatikus elindítása a `/swagger-ui.html` címen
* OpenAPI elérhetőség a `/v3/api-docs` címen (vagy `/v3/api-docs.yaml`)

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-ui</artifactId>
  <version>${springdoc-openapi-ui.version}</version>
</dependency>
```

---

## Globális testreszabás

```java
@Bean
public OpenAPI customOpenAPI() {
  return new OpenAPI()
  .info(new Info()
  .title("Employees API")
  .version("1.0.0")
  .description("Operations with employees"));
}
```

---

## Séma testreszabás

* Figyelembe veszi a Bean Validation annotációkat

```java
public class CreateEmployeeCommand {

    @NotNull
    @Schema(description="name of the employee", example = "John Doe")
    private String name;
}
```

---

## Osztály és metódus szint

* Figyelembe veszi a Spring MVC annotációkat

```java
@RestController
@RequestMapping("/api/employees")
@Tag( name = "Operations on employees")
public class EmployeesController {

  @GetMapping("/{id}")
  @Operation(summary = "Find employee by id",
    description = "Find employee by id.")
  @ApiResponse(responseCode = "404",
    description = "Employee not found")
  public EmployeeDto findEmployeeById(
      @Parameter(description = "Id of the employee",
        example = "12")
      @PathVariable("id") long id) {
    // ...
  }

}
```

---

## 12Factor hivatkozás: API first

* Contract first alapjain
* Laza csatolás
* Webes és mobil GUI és az üzleti logika is ide tartozik
* Dokumentálva és tesztelve legyen
* [API Blueprint](https://apiblueprint.org/): Markdown alapú formátum API dokumentálására

---

class: inverse, center, middle

# Tesztelés REST Assured használatával

---

## REST Assured

* Keretrendszer független eszköz REST API tesztelésére
* Dinamikus nyelvek egyszerűségét próbálja hozni Java nyelven
* JSON, mint szöveg, vagy objektum mapping (Jackson, Gson, JAXB, stb.)
* Megadható
  * Path, parameter, header, cookie, content-type, stb.
* Sokszínű assertek
* Támogatja a különböző autentikációs módokat

---

## REST Assured - Assert

* XML tartalomra XmlPath, GPath (Groovy-ból, hasonló az XPath-hoz)
* DTD és XSD validáció
* JSON tartalomra JSONPath-szal
* JSON Schema validáció
* Header, status, cookie, content-type
* Response time


---

## Függőségek - JsonPath és XmlPath

```xml
<dependency>
  <groupId>io.rest-assured</groupId>
  <artifactId>json-path</artifactId>
  <version>${rest-assured.version}</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>io.rest-assured</groupId>
  <artifactId>xml-path</artifactId>
  <version>${rest-assured.version}</version>
  <scope>test</scope>
</dependency>
```

Csak JSON használata esetén is kell mindkét függőség

---

## Függőségek

* Un. RestAssuredMockMvc API

```xml
<dependency>
  <groupId>io.rest-assured</groupId>
  <artifactId>rest-assured</artifactId>
  <version>${rest-assured.version}</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>io.rest-assured</groupId>
  <artifactId>spring-mock-mvc</artifactId>
  <version>${rest-assured.version}</version>
  <scope>test</scope>
</dependency>
```

---

## Inicializálás

```java
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.equalTo;
```

```java
@Autowired
WebApplicationContext webApplicationContext;

@BeforeEach
void init() {
  RestAssuredMockMvc.requestSpecification = given()
    .contentType(ContentType.JSON)
    .accept(ContentType.JSON);

  RestAssuredMockMvc
    .webAppContextSetup(webApplicationContext);
}
```

---

## Teszteset

```java
@Test
void testCreateEmployeeThenListEmployees() {
    with().body(new CreateEmployeeCommand("Jack Doe")).
    when()
      .post("/api/employees")
      .then()
      .body("name", equalTo("Jack Doe"));

    when()
      .get("/api/employees")
      .then()
      .body("[0].name", equalTo("Jack Doe"));
}
```

---

class: inverse, center, middle

# REST Assured séma validáció

---

## JSON Schema

```json
{
  "$schema": "https://json-schema.org/draft/2019-09/schema",
  "title": "Employees",
  "type": "array",
  "items": [
    {
      "title": "EmployeeDto",
      "type": "object",
      "required": ["name", "id"],
      "properties": {
        "id": {
          "type": "integer",
          "description": "id of the employee",
          "format": "int64",
          "example": 12
        },
        "name": {
          "type": "string",
          "description": "name of the employee",
          "example": "John Doe"
}}}]}
```
---

## Függőség

```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>json-schema-validator</artifactId>
    <scope>test</scope>
</dependency>
```

---

## JSON Schema validáció

```java
when()
    .get("/api/employees")
    .then()
    .body(matchesJsonSchemaInClasspath("employee-dto-schema.json"));
```

---

class: inverse, center, middle

# Content negotiation

---

## Content negotiation

* Mechanizmus, mely lehetővé teszi a kliens számára, <br />hogy az erőforrás megjelenítési
  formái közül válasszon, pl.
    * JSON vagy XML (`Accept` fejléc és Mime Type)
    * GIF vagy JPEG
    * Nyelv (`Accept-Language` fejléc alapján)

---

## Content negotiation Spring Boot támogatás

* Controllerben

```java
@RequestMapping(value = "/api/employees",
  produces = {MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE})
```

* `pom.xml`-ben

```xml
<dependency>
  <groupId>org.glassfish.jaxb</groupId>
  <artifactId>jaxb-runtime</artifactId>
</dependency>
```

* Dto-ban `@XmlRootElement`

---

## Lista esetén

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesDto {

    @XmlElement(name = "employee")
    private List<EmployeeDto> employees;
}
```

---

class: inverse, center, middle

# Validáció

---

## Validáció

* Bean Validation 2.0 (JSR 380) támogatás
* Ne réteghez legyen kötve, hanem az adatot hordozó beanhez
* Attribútumokra annotáció
* Beépített annotációk
* Saját annotáció implementálható
* Megadható metódus paraméterekre és visszatérési értékre is

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

## Beépített annotációk 1.

* `@AssertFalse`, `@AssertTrue`
* `@Null`, `@NotNull`
* `@Size`
* `@Max`, `@Min`, `@Positive`, <br />`@PositiveOrZero`, `@Negative`, `@NegativeOrZero`
* `@DecimalMax`, `@DecimalMin`
* `@Digits`

---

## Beépített annotációk 2.


* `@Future`, `@Past`, <br />`@PastOrPresent`, `@FutureOrPresent`
* `@Pattern`
* `@Email`
* `@NotEmpty`, `@NotBlank`

---

## Validáció controlleren

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeCommand {

    @NotNull(message = "Name can not be null")
    private String name;
}
```

```java
@PostMapping
public EmployeeDto createEmployee(
    @Valid @RequestBody CreateEmployeeCommand command) {
  return employeesService.createEmployee(command);
}
```

---

## Válasz

```json
{
  "timestamp": 1596570707472,
  "status": 400,
  "error": "Bad Request",
  "message": "",
  "path": "/api/employees"
}
```

---

## problem használatával

.small-code-14[
```java
@ExceptionHandler({MethodArgumentNotValidException.class})
public ResponseEntity<Problem> handleValidationError(MethodArgumentNotValidException e) {
    List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
            .map((FieldError fe) -> new Violation(fe.getField(), fe.getDefaultMessage()))
            .collect(Collectors.toList());

    Problem problem = Problem.builder()
            .withType(URI.create("employees/validation-error"))
            .withTitle("Validation error")
            .withStatus(Status.BAD_REQUEST)
            .withDetail(e.getMessage())
            .with("violations", violations)
            .build();

    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .contentType(MediaType.APPLICATION_PROBLEM_JSON)
      .body(problem);
}
```
]

---

## Violation osztály

```java
@Data
@AllArgsConstructor
public class Violation {

    private String field;

    private String defaultMessage;
}
```

---

## Kapott válasz

```json
{
  "type": "employees/validation-error",
  "title": "Validation error",
  "status": 400,
  "detail": "Validation failed for argument [0] in public ...",
  "violations": [
    {
      "field": "name",
      "message": "Name can not be null"
    }
  ]
}
```

---

class: inverse, center, middle

# Saját validáció készítése

---

## Saját annotáció

```java
@Constraint(validatedBy = NameValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface Name {

    String message() default "Invalid name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int maxLength() default 50;

}
```

---

## Validator osztály

```java
public class NameValidator implements ConstraintValidator<Name, String> {

  private int maxLength;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null &&
      !value.isBlank() &&
      value.length() > 2 && 
      value.length() <= maxLength && 
      Character.isUpperCase(value.charAt(0));
  }

  @Override
  public void initialize(Name constraintAnnotation) {
      maxLength = constraintAnnotation.maxLength();
  }
}
```