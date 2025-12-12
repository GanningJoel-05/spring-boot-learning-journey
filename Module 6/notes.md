# **Module 6.3 - Connect to PostgreSQL Database (Spring Boot)**

---

## 💡 1️⃣ What is PostgreSQL?

PostgreSQL (often called “Postgres”) is a **powerful, open-source, production-grade relational database**.

It is used by companies like Apple, Netflix, Spotify, Instagram, and many global enterprises.

### ⭐ Why developers love Postgres:

- Reliable & stable
- Full ACID compliance
- Supports advanced SQL
- Supports JSON features
- Highly scalable
- Great with Spring Boot + JPA

### Simple Definition:

> PostgreSQL = A modern, reliable, enterprise database used for real production applications.
> 

---

# ⭐ 2️⃣ Why Use PostgreSQL in Spring Boot?

| Feature | Benefit |
| --- | --- |
| Robust | Handles millions of records safely |
| Scalable | Great for large applications |
| Free & Open Source | No license cost |
| Plays well with JPA/Hibernate | Auto mapping between objects & tables |
| Supports advanced data types | JSONB, arrays, UUIDs |

Spring Boot has **first-class support** for PostgreSQL with auto-configuration.

---

# ⚙️ 3️⃣ Step-by-Step Setup of PostgreSQL in Spring Boot

This is the exact sequence you’ll use both in real projects and interviews 👇

---

# 🧩 **Step 1 — Install PostgreSQL**

Download from:

👉 https://www.postgresql.org/download/

During installation:

- Username → `postgres` (default)
- Password → *you choose*
- Install pgAdmin (GUI)

We’ll use pgAdmin to create databases & inspect tables.

---

# 🧩 **Step 2 — Create a Database in PostgreSQL**

Open **pgAdmin** →

Right-click **Databases** →

**Create → Database**

Example name:

```
studentdb
```

Done ✔

---

# 🧩 **Step 3 — Add PostgreSQL Dependency in Spring Boot**

In `pom.xml`:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

Spring Boot auto-detects it and prepares the DataSource.

(Thanks to Auto Configuration)

---

# 🧩 **Step 4 — Configure PostgreSQL in application.properties**

```
spring.datasource.url=jdbc:postgresql://localhost:5432/studentdb
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Explanation:

- `5432` → default PostgreSQL port
- `studentdb` → DB we created
- `ddl-auto=update` → auto-create/update tables
- `show-sql=true` → show SQL in logs

---

# 🧩 **Step 5 — Create Entity Class**

```java
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    // getters and setters
}
```

Hibernate will create a `student` table in PostgreSQL automatically.

---

# 🧩 **Step 6 — Create Repository**

```java
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
```

You now have **CRUD methods ready** without writing SQL:

- save()
- findAll()
- findById()
- delete()
- count()

---

# 🧩 **Step 7 — Service Layer**

```java
@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public Student addStudent(Student student) {
        return repo.save(student);
    }

    public List<Student> getAll() {
        return repo.findAll();
    }
}
```

---

# 🧩 **Step 8 — Controller Layer**

```java
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping
    public List<Student> getStudents() {
        return service.getAll();
    }
}
```

---

# 🧪 **Step 9 — Test the API**

### POST (Create):

```
POST http://localhost:8080/students
Body:
{
  "name": "Joel",
  "age": 21
}
```

### GET (Read):

```
GET http://localhost:8080/students
```

---

# 🗄️ **Step 10 — Verify Data in PostgreSQL**

Open pgAdmin →

Open database →

Tables → `student` →

Right-click → **View Data**

You’ll see the row inserted from your API 🎉🚀

---

# 🎯 **Interview-Ready Summary**

> To connect Spring Boot with PostgreSQL, we add the PostgreSQL driver dependency and configure datasource properties in application.properties.
> 
> 
> Spring Boot auto-configures the connection based on the presence of the driver, and creates tables using JPA/Hibernate.
> 
> CRUD operations are handled using the Repository Layer, without writing SQL manually.
> 

---

# 🌸 Real-Life Analogy

Think of PostgreSQL as a **permanent digital notebook**:

- H2 = rough sheet (erased every restart)
- PostgreSQL = actual notebook where your important notes stay

Your Spring Boot app writes into this notebook through JPA.