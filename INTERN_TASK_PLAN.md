# 🎓 Smart Mess & Canteen Management System — 30-Day Intern Task Plan

> **Tech Stack:** Spring Boot 4.x · Hibernate/JPA · MySQL · Angular 18+  
> **Base Package:** `com.smartmess.smart_mess_management`  
> **Frontend Path:** `Frontend/smart-mess-frontend/src/app/`  
> **Backend Path:** `Backend/smart-mess-management/src/main/java/com/smartmess/smart_mess_management/`

---

## 📋 How to Read This Plan

Each task entry contains:
- **What to do** — clear description of the task
- **Files to work on** — exact file paths
- **What to implement** — specific fields, methods, annotations
- **Definition of Done** — how you know it's complete

---

## 🗓️ WEEK 1 — Setup, Orientation & Core Entities

---

### ✅ Day 1 — Project Setup & Environment

**Goal:** Get both backend and frontend running locally.

#### Task 1.1 — Backend Setup
**Files:** `Backend/smart-mess-management/`
- Import the Maven project into IntelliJ IDEA or Eclipse
- Open `src/main/resources/application.properties` (create if missing) and add:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smart_mess_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.port=8080
```
- Create the database `smart_mess_db` in MySQL
- Run `SmartMessManagementApplication.java` and confirm it starts without errors

**Definition of Done:** Spring Boot app starts on port 8080, connects to MySQL

---

#### Task 1.2 — Frontend Setup
**Files:** `Frontend/smart-mess-frontend/`
- Open terminal in this folder
- Run `npm install` (already done if node_modules exists)
- Run `ng serve` and open `http://localhost:4200`
- Open `src/app/app.routes.ts` and review existing route stubs
- Open `src/app/app.config.ts` and confirm `HttpClient` is provided

**Definition of Done:** Angular app loads at `http://localhost:4200` without errors

---

#### Task 1.3 — Understand the Project Structure
Read and understand these files:
- All files in `entity/` — these are your database tables
- All files in `Services/` — these are service interfaces
- All files in `Controllers/` — these are REST API endpoints
- All files in `models/models/` (Angular) — these are TypeScript interfaces
- All files in `services/services/` (Angular) — these call the backend APIs

**Definition of Done:** Write a short note (comment in a README or text file) explaining what each module does

---

### ✅ Day 2 — Database Design & Entity Setup (Admin + Student)

**Goal:** Implement the `Admin` and `Student` JPA entities with all fields and relationships.

#### Task 2.1 — Admin Entity
**File:** `entity/Admin.java`

Implement the class with these fields and annotations:
```java
@Entity
@Table(name = "admins")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Admin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Email @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    private String password;

    private String phone;
    private String role; // e.g., "SUPER_ADMIN", "MESS_ADMIN"

    @CreationTimestamp
    private LocalDateTime createdAt;
}
```

**File:** `repository/AdminRepository.java`
```java
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
```

**File:** `Services/AdminService.java`
```java
public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin getAdminById(Long id);
    List<Admin> getAllAdmins();
    Admin updateAdmin(Long id, Admin admin);
    void deleteAdmin(Long id);
    Optional<Admin> findByEmail(String email);
}
```

---

#### Task 2.2 — Student Entity
**File:** `entity/Student.java`
```java
@Entity
@Table(name = "students")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    private String phone;
    private String rollNumber;
    private String department;
    private String hostelName;
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private StudentStatus status; // ACTIVE, INACTIVE

    @CreationTimestamp
    private LocalDateTime createdAt;
}
```

**File:** `repository/StudentRepository.java`
```java
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByRollNumber(String rollNumber);
    List<Student> findByStatus(StudentStatus status);
}
```

**File:** `Services/StudentService.java`
```java
public interface StudentService {
    Student createStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    Optional<Student> findByEmail(String email);
}
```

**Definition of Done:** Run the app — `admins` and `students` tables created in MySQL automatically

---

### ✅ Day 3 — Mess & Canteen Entities

**Goal:** Implement `Mess` and `Canteen` entities.

#### Task 3.1 — Mess Entity
**File:** `entity/Mess.java`
```java
@Entity
@Table(name = "messes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Mess {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String location;
    private String contactNumber;
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private MessType type; // VEG, NON_VEG, BOTH

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
```

**File:** `repository/MessRepository.java`
```java
public interface MessRepository extends JpaRepository<Mess, Long> {
    List<Mess> findByType(MessType type);
    Optional<Mess> findByName(String name);
}
```

**File:** `Services/MessService.java`
```java
public interface MessService {
    Mess createMess(Mess mess);
    Mess getMessById(Long id);
    List<Mess> getAllMesses();
    Mess updateMess(Long id, Mess mess);
    void deleteMess(Long id);
}
```

---

#### Task 3.2 — Canteen Entity
**File:** `entity/Canteen.java`
```java
@Entity
@Table(name = "canteens")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Canteen {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String location;
    private String openingTime;
    private String closingTime;
    private String contactNumber;
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
```

**File:** `repository/CanteenRepository.java`
```java
public interface CanteenRepository extends JpaRepository<Canteen, Long> {
    List<Canteen> findByIsActive(Boolean isActive);
}
```

**Definition of Done:** `messes` and `canteens` tables created in MySQL

---

### ✅ Day 4 — Menu, Meal & Food Item Entities

**Goal:** Implement food-related entities.

#### Task 4.1 — Meal Entity
**File:** `entity/Meal.java`
```java
@Entity
@Table(name = "meals")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Meal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name; // Breakfast, Lunch, Dinner, Snacks

    private String description;
    private String serveTime; // e.g., "07:00 - 09:00"

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;
}
```

#### Task 4.2 — Menu Entity
**File:** `entity/Menu.java`
```java
@Entity
@Table(name = "menus")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Menu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate menuDate;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    private String items; // comma-separated food items for simplicity

    @CreationTimestamp
    private LocalDateTime createdAt;
}
```

#### Task 4.3 — FoodItem Entity
**File:** `entity/FoodItem.java`
```java
@Entity
@Table(name = "food_items")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FoodItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private FoodCategory category; // VEG, NON_VEG, BEVERAGE, SNACK

    private Boolean isAvailable = true;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "canteen_id")
    private Canteen canteen;
}
```

**Definition of Done:** `meals`, `menus`, `food_items` tables created in MySQL

---

### ✅ Day 5 — Order, Payment & Subscription Entities

**Goal:** Implement transaction-related entities.

#### Task 5.1 — FoodOrder & FoodOrderItem
**File:** `entity/FoodOrder.java`
```java
@Entity
@Table(name = "food_orders")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FoodOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "canteen_id")
    private Canteen canteen;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, CONFIRMED, READY, DELIVERED, CANCELLED

    private BigDecimal totalAmount;

    @CreationTimestamp
    private LocalDateTime orderedAt;

    @OneToMany(mappedBy = "foodOrder", cascade = CascadeType.ALL)
    private List<FoodOrderItem> orderItems;
}
```

**File:** `entity/FoodOrderItem.java`
```java
@Entity
@Table(name = "food_order_items")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FoodOrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private FoodOrder foodOrder;

    @ManyToOne
    @JoinColumn(name = "food_item_id")
    private FoodItem foodItem;

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subTotal;
}
```

#### Task 5.2 — Payment Entity
**File:** `entity/Payment.java`
```java
@Entity
@Table(name = "payments")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentType type; // MESS_FEE, CANTEEN_ORDER, SUBSCRIPTION

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // PENDING, COMPLETED, FAILED, REFUNDED

    private String transactionId;
    private String paymentMethod; // CASH, UPI, CARD, ONLINE

    @CreationTimestamp
    private LocalDateTime paidAt;
}
```

#### Task 5.3 — Subscription Entity
**File:** `entity/Subscription.java`
```java
@Entity
@Table(name = "subscriptions")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Subscription {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private SubscriptionPlan plan; // MONTHLY, QUARTERLY, YEARLY

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status; // ACTIVE, EXPIRED, CANCELLED

    private BigDecimal amount;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
```

**Definition of Done:** All tables created. Verify in MySQL Workbench or CLI.

---

### ✅ Day 6 — Attendance, Feedback, Complaint & Notice Entities

#### Task 6.1 — Attendance Entity
**File:** `entity/Attendance.java`
```java
@Entity
@Table(name = "attendance")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Attendance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    private LocalDate attendanceDate;
    private Boolean isPresent;

    @CreationTimestamp
    private LocalDateTime markedAt;
}
```

#### Task 6.2 — Feedback Entity
**File:** `entity/Feedback.java`
```java
@Entity
@Table(name = "feedbacks")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Feedback {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;

    @Min(1) @Max(5)
    private Integer rating;

    @Column(length = 1000)
    private String comment;

    @Enumerated(EnumType.STRING)
    private FeedbackCategory category; // FOOD_QUALITY, CLEANLINESS, SERVICE, TIMING

    @CreationTimestamp
    private LocalDateTime createdAt;
}
```

#### Task 6.3 — Complaint Entity
**File:** `entity/Complaint.java`
```java
@Entity
@Table(name = "complaints")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Complaint {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @NotBlank
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status; // OPEN, IN_PROGRESS, RESOLVED, CLOSED

    @Enumerated(EnumType.STRING)
    private ComplaintCategory category; // FOOD, SERVICE, HYGIENE, BILLING, OTHER

    private String resolution;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime resolvedAt;
}
```

#### Task 6.4 — Notice Entity
**File:** `entity/Notice.java`
```java
@Entity
@Table(name = "notices")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(length = 5000)
    private String content;

    @Enumerated(EnumType.STRING)
    private NoticeType type; // GENERAL, URGENT, MAINTENANCE, HOLIDAY

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    private LocalDate validUntil;
    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
```

**Definition of Done:** All remaining tables created. Full database schema complete.

---

### ✅ Day 7 — DTOs & Application Properties

**Goal:** Implement all DTOs and create enums.

#### Task 7.1 — Create Enums
Create a new package `enums/` and add these enum files:
- `StudentStatus.java` → `ACTIVE, INACTIVE`
- `MessType.java` → `VEG, NON_VEG, BOTH`
- `OrderStatus.java` → `PENDING, CONFIRMED, READY, DELIVERED, CANCELLED`
- `PaymentType.java` → `MESS_FEE, CANTEEN_ORDER, SUBSCRIPTION`
- `PaymentStatus.java` → `PENDING, COMPLETED, FAILED, REFUNDED`
- `SubscriptionPlan.java` → `MONTHLY, QUARTERLY, YEARLY`
- `SubscriptionStatus.java` → `ACTIVE, EXPIRED, CANCELLED`
- `FoodCategory.java` → `VEG, NON_VEG, BEVERAGE, SNACK`
- `ComplaintStatus.java` → `OPEN, IN_PROGRESS, RESOLVED, CLOSED`
- `ComplaintCategory.java` → `FOOD, SERVICE, HYGIENE, BILLING, OTHER`
- `FeedbackCategory.java` → `FOOD_QUALITY, CLEANLINESS, SERVICE, TIMING`
- `NoticeType.java` → `GENERAL, URGENT, MAINTENANCE, HOLIDAY`

#### Task 7.2 — Implement DTOs
**Files:** `dto/StudentDTO.java`, `dto/MessDTO.java`, etc.

Each DTO should mirror the entity but WITHOUT sensitive fields (no passwords) and with validation annotations. Example:

**File:** `dto/StudentDTO.java`
```java
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StudentDTO {
    private Long id;
    @NotBlank private String name;
    @Email @NotBlank private String email;
    private String phone;
    private String rollNumber;
    private String department;
    private String hostelName;
    private String roomNumber;
    private StudentStatus status;
}
```

Do the same pattern for: `AdminDTO`, `MessDTO`, `CanteenDTO`, `MealDTO`, `MenuDTO`, `FoodItemDTO`, `FoodOrderDTO`, `FoodOrderItemDTO`, `PaymentDTO`, `SubscriptionDTO`, `AttendanceDTO`, `FeedbackDTO`, `ComplaintDTO`, `NoticeDTO`

**Definition of Done:** All DTOs compile without errors, no entity references leak passwords

---

## 🗓️ WEEK 2 — Service Implementation (Backend)

---

### ✅ Day 8 — Admin & Student Service Implementation

**Goal:** Implement `AdminServiceImpl` and `StudentServiceImpl`.

#### Task 8.1 — AdminServiceImpl
**File:** `serviceimpl/AdminServiceImpl.java`
```java
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Admin createAdmin(Admin admin) {
        // TODO: hash password before saving (use BCrypt)
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {
        Admin existing = getAdminById(id);
        existing.setName(admin.getName());
        existing.setPhone(admin.getPhone());
        existing.setRole(admin.getRole());
        return adminRepository.save(existing);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
```

#### Task 8.2 — StudentServiceImpl
**File:** `serviceimpl/StudentServiceImpl.java`

Implement all methods from `StudentService` interface following the same pattern as `AdminServiceImpl`.

**Definition of Done:** Both service classes compile and inject repository correctly

---

### ✅ Day 9 — Mess & Canteen Service Implementation

**Files to implement:**
- `serviceimpl/MessServiceImpl.java` — implement `MessService`
- `serviceimpl/CanteenServiceImpl.java` — implement `CanteenService`

Each must include:
- `@Service @RequiredArgsConstructor` annotations
- Inject the corresponding Repository
- Implement all CRUD methods
- Throw `RuntimeException("X not found")` when entity is missing

Also implement remaining repository files:
- `repository/MessRepository.java` → extend `JpaRepository<Mess, Long>`
- `repository/CanteenRepository.java` → extend `JpaRepository<Canteen, Long>`

**Definition of Done:** All service impls compile with no errors

---

### ✅ Day 10 — Menu, Meal & FoodItem Service Implementation

**Files to implement:**
- `serviceimpl/MealServiceImpl.java`
- `serviceimpl/MenuServiceImpl.java`
- `serviceimpl/FoodItemServiceImpl.java`

For `MenuService`, add a useful method:
```java
List<Menu> getMenuByMessAndDate(Long messId, LocalDate date);
```

For `FoodItemService`, add:
```java
List<FoodItem> getAvailableItemsByCanteen(Long canteenId);
```

Implement these in the corresponding `ServiceImpl` and `Repository` files.

**Definition of Done:** Food-related services compile and are ready to be wired into controllers

---

### ✅ Day 11 — Order & Payment Service Implementation

**Files to implement:**
- `serviceimpl/FoodOrderServiceImpl.java`
- `serviceimpl/FoodOrderItemServiceImpl.java`
- `serviceimpl/PaymentServiceImpl.java`

For `FoodOrderServiceImpl`, add business logic:
```java
// When creating an order, calculate totalAmount from order items
public FoodOrder createOrder(FoodOrder order) {
    BigDecimal total = order.getOrderItems().stream()
        .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    order.setTotalAmount(total);
    order.setStatus(OrderStatus.PENDING);
    return foodOrderRepository.save(order);
}
```

Also add:
```java
List<FoodOrder> getOrdersByStudent(Long studentId);
List<FoodOrder> getOrdersByStatus(OrderStatus status);
FoodOrder updateOrderStatus(Long orderId, OrderStatus status);
```

**Definition of Done:** Order total is auto-calculated, status can be updated

---

### ✅ Day 12 — Subscription & Attendance Service Implementation

**Files to implement:**
- `serviceimpl/SubscriptionServiceImpl.java`
- `serviceimpl/AttendanceServiceImpl.java`

For `SubscriptionService`, add:
```java
List<Subscription> getActiveSubscriptionsByStudent(Long studentId);
boolean hasActiveSubscription(Long studentId, Long messId);
```

For `AttendanceService`, add:
```java
List<Attendance> getAttendanceByStudentAndDateRange(Long studentId, LocalDate from, LocalDate to);
long countPresentByStudentAndMonth(Long studentId, int month, int year);
```

**Definition of Done:** Attendance can be queried by date range

---

### ✅ Day 13 — Feedback, Complaint & Notice Service Implementation

**Files to implement:**
- `serviceimpl/FeedbackServiceImpl.java`
- `serviceimpl/ComplaintServiceImpl.java`

Create new (missing):
- `Services/NoticeService.java`
- `serviceimpl/NoticeServiceImpl.java`
- `repository/NoticeRepository.java`

For `ComplaintService`, add:
```java
List<Complaint> getComplaintsByStatus(ComplaintStatus status);
Complaint resolveComplaint(Long complaintId, String resolution);
```

For `NoticeService`, add:
```java
List<Notice> getActiveNotices();
List<Notice> getNoticesByType(NoticeType type);
```

**Definition of Done:** All 15 service implementations are complete

---

### ✅ Day 14 — REST Controllers (Admin, Student, Mess)

**Goal:** Implement the first 3 REST controllers.

#### Task 14.1 — AdminController
**File:** `Controllers/AdminController.java`
```java
@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<Admin> create(@Valid @RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createAdmin(admin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAll() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> update(@PathVariable Long id, @Valid @RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.updateAdmin(id, admin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
```

#### Task 14.2 — StudentController & MessController
**Files:** `Controllers/StudentController.java`, `Controllers/MessController.java`

Follow the exact same pattern as `AdminController` with:
- `@RestController @RequestMapping("/api/students")`
- `@RestController @RequestMapping("/api/messes")`

**Definition of Done:** Test with Postman — POST/GET/PUT/DELETE working for all 3

---

### ✅ Day 15 — REST Controllers (Canteen, Menu, Meal, FoodItem)

**Files to implement:**
- `Controllers/CanteenController.java` → `@RequestMapping("/api/canteens")`
- `Controllers/MealController.java` → `@RequestMapping("/api/meals")`
- `Controllers/MenuController.java` → `@RequestMapping("/api/menus")`
- `Controllers/FoodItemController.java` → `@RequestMapping("/api/food-items")`

For `FoodItemController`, add an extra endpoint:
```java
@GetMapping("/canteen/{canteenId}/available")
public ResponseEntity<List<FoodItem>> getAvailableByCanteen(@PathVariable Long canteenId) {
    return ResponseEntity.ok(foodItemService.getAvailableItemsByCanteen(canteenId));
}
```

For `MenuController`, add:
```java
@GetMapping("/mess/{messId}/date/{date}")
public ResponseEntity<List<Menu>> getByMessAndDate(
    @PathVariable Long messId,
    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return ResponseEntity.ok(menuService.getMenuByMessAndDate(messId, date));
}
```

**Definition of Done:** All endpoints respond correctly in Postman

---

## 🗓️ WEEK 3 — Remaining Controllers + Angular Setup

---

### ✅ Day 16 — Order, Payment & Subscription Controllers

**Files:**
- `Controllers/FoodOrderController.java` → `@RequestMapping("/api/orders")`
- `Controllers/FoodOrderItemController.java` → `@RequestMapping("/api/order-items")`
- `Controllers/PaymentController.java` → `@RequestMapping("/api/payments")`
- `Controllers/SubscriptionController.java` → `@RequestMapping("/api/subscriptions")`

For `FoodOrderController`, add:
```java
@PutMapping("/{id}/status")
public ResponseEntity<FoodOrder> updateStatus(
    @PathVariable Long id,
    @RequestParam OrderStatus status) {
    return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
}

@GetMapping("/student/{studentId}")
public ResponseEntity<List<FoodOrder>> getByStudent(@PathVariable Long studentId) {
    return ResponseEntity.ok(orderService.getOrdersByStudent(studentId));
}
```

**Definition of Done:** Full order lifecycle can be tested via Postman

---

### ✅ Day 17 — Attendance, Feedback, Complaint & Notice Controllers

**Files:**
- `Controllers/AttendanceController.java` → `@RequestMapping("/api/attendance")`
- `Controllers/FeedbackController.java` → `@RequestMapping("/api/feedbacks")`
- `Controllers/ComplaintController.java` → `@RequestMapping("/api/complaints")`
- `Controllers/NoticeController.java` → `@RequestMapping("/api/notices")`

For `ComplaintController`, add:
```java
@PutMapping("/{id}/resolve")
public ResponseEntity<Complaint> resolve(
    @PathVariable Long id,
    @RequestParam String resolution) {
    return ResponseEntity.ok(complaintService.resolveComplaint(id, resolution));
}
```

For `NoticeController`, add:
```java
@GetMapping("/active")
public ResponseEntity<List<Notice>> getActiveNotices() {
    return ResponseEntity.ok(noticeService.getActiveNotices());
}
```

**Definition of Done:** All 15 controllers are implemented and tested

---

### ✅ Day 18 — Global Exception Handler + CORS Config

**Goal:** Add proper error handling and allow Angular to call the backend.

#### Task 18.1 — Global Exception Handler
Create new file: `exception/GlobalExceptionHandler.java`
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
```

#### Task 18.2 — CORS Configuration
Create new file: `config/CorsConfig.java`
```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*");
            }
        };
    }
}
```

**Definition of Done:** Angular can call Spring Boot APIs without CORS errors

---

### ✅ Day 19 — Angular Models & Services Setup

**Goal:** Wire up all Angular TypeScript models and HTTP services.

#### Task 19.1 — Update Angular Models
**Files:** `src/app/models/models/*.model.ts`

Update each model to match the backend entities. Example:

**File:** `models/models/student.model.ts`
```typescript
export interface Student {
  id?: number;
  name: string;
  email: string;
  phone?: string;
  rollNumber?: string;
  department?: string;
  hostelName?: string;
  roomNumber?: string;
  status?: 'ACTIVE' | 'INACTIVE';
  createdAt?: string;
}
```

Do the same for: `mess.model.ts`, `canteen.model.ts`, `meal.model.ts`, `menu.model.ts`, `food-item.model.ts`, `food-order.model.ts`, `payment.model.ts`, `subscription.model.ts`, `attendance.model.ts`, `feedback.model.ts`, `complaint.model.ts`, `notice.model.ts`

---

#### Task 19.2 — Update Angular Services
**Files:** `src/app/services/services/*.ts`

Each service should inject `HttpClient` and call the backend. Example:

**File:** `services/services/student.ts`
```typescript
@Injectable({ providedIn: 'root' })
export class StudentService {
  private apiUrl = 'http://localhost:8080/api/students';

  constructor(private http: HttpClient) {}

  getAll() { return this.http.get<Student[]>(this.apiUrl); }
  getById(id: number) { return this.http.get<Student>(`${this.apiUrl}/${id}`); }
  create(student: Student) { return this.http.post<Student>(this.apiUrl, student); }
  update(id: number, student: Student) { return this.http.put<Student>(`${this.apiUrl}/${id}`, student); }
  delete(id: number) { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
}
```

Do the same for all 13 remaining services.

**Definition of Done:** All services compile, `HttpClient` is available via `app.config.ts`

---

### ✅ Day 20 — Angular Routing & Shared Components

**Goal:** Set up navigation and shared UI components.

#### Task 20.1 — Configure Routes
**File:** `src/app/app.routes.ts`
```typescript
export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'students', component: StudentListComponent },
  { path: 'students/add', component: StudentAddComponent },
  { path: 'students/edit/:id', component: StudentEditComponent },
  { path: 'messes', component: MessListComponent },
  { path: 'canteens', component: CanteenListComponent },
  { path: 'menus', component: MenuListComponent },
  { path: 'food-items', component: FoodItemListComponent },
  { path: 'orders', component: FoodOrderListComponent },
  { path: 'payments', component: PaymentListComponent },
  { path: 'subscriptions', component: SubscriptionListComponent },
  { path: 'attendance', component: AttendanceListComponent },
  { path: 'feedbacks', component: FeedbackListComponent },
  { path: 'complaints', component: ComplaintListComponent },
  { path: 'notices', component: NoticeListComponent },
];
```

#### Task 20.2 — Shared Components (Navbar & Sidebar)
**Files:**
- `shared/shared/navbar/` — add navigation links
- `shared/shared/sidebar/` — add sidebar with module links
- `shared/shared/footer/` — add basic footer
- `shared/shared/loading/` — add a spinner component

**Definition of Done:** Navigating to `/students` shows the student list page

---

## 🗓️ WEEK 4 — Angular UI Implementation

---

### ✅ Day 21 — Student Module (Angular)

**Goal:** Implement full CRUD UI for Students.

#### Task 21.1 — StudentListComponent
**Files:**
- `components/components/student/student-list/student-list.ts`
- `components/components/student/student-list/student-list.html`

```typescript
// student-list.ts
export class StudentListComponent implements OnInit {
  students: Student[] = [];

  constructor(private studentService: StudentService, private router: Router) {}

  ngOnInit() {
    this.studentService.getAll().subscribe(data => this.students = data);
  }

  deleteStudent(id: number) {
    if (confirm('Are you sure?')) {
      this.studentService.delete(id).subscribe(() => this.ngOnInit());
    }
  }

  editStudent(id: number) {
    this.router.navigate(['/students/edit', id]);
  }
}
```

```html
<!-- student-list.html -->
<div class="container mt-4">
  <div class="d-flex justify-content-between mb-3">
    <h2>Students</h2>
    <a routerLink="/students/add" class="btn btn-primary">+ Add Student</a>
  </div>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>ID</th><th>Name</th><th>Email</th><th>Roll No</th><th>Department</th><th>Status</th><th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <tr *ngFor="let s of students">
        <td>{{s.id}}</td><td>{{s.name}}</td><td>{{s.email}}</td>
        <td>{{s.rollNumber}}</td><td>{{s.department}}</td><td>{{s.status}}</td>
        <td>
          <button class="btn btn-sm btn-warning me-1" (click)="editStudent(s.id!)">Edit</button>
          <button class="btn btn-sm btn-danger" (click)="deleteStudent(s.id!)">Delete</button>
        </td>
      </tr>
    </tbody>
  </table>
</div>
```

#### Task 21.2 — StudentAddComponent & StudentEditComponent
**Files:**
- `components/components/student/student-add/student-add.ts` + `.html`
- `components/components/student/student-edit/student-edit.ts` + `.html`

Both use a Reactive Form with fields: `name, email, password, phone, rollNumber, department, hostelName, roomNumber`

The Edit component loads existing data using the `id` from the route params.

**Definition of Done:** Can add, view, edit, and delete a student from the browser

---

### ✅ Day 22 — Mess & Canteen Module (Angular)

**Goal:** Implement CRUD UI for Mess and Canteen.

**Files to implement:**
- `components/components/mess/` — add `mess-list`, `mess-add`, `mess-edit` folders with `.ts` and `.html` files
- `components/components/canteen/` — same structure

Follow the exact same pattern used in Day 21 for Students. The mess form fields are: `name, location, contactNumber, capacity, type (dropdown: VEG/NON_VEG/BOTH)`

The canteen form fields are: `name, location, openingTime, closingTime, contactNumber, isActive`

**Definition of Done:** Mess and Canteen are fully manageable from the UI

---

### ✅ Day 23 — Menu & Food Item Module (Angular)

**Files to implement:**
- `components/components/menu/` — list + add + edit
- `components/components/food-item/` — list + add + edit

For Menu, the add form has: `menuDate (date picker), dayOfWeek, mess (dropdown), meal (dropdown), items`

For FoodItem, the add form has: `name, description, price, category (dropdown), isAvailable, canteen (dropdown)`

**Special:** The dropdowns for `mess`, `meal`, and `canteen` should load data from the API using the corresponding Angular services.

**Definition of Done:** Can create a weekly menu and add food items to a canteen

---

### ✅ Day 24 — Food Order Module (Angular)

**Files:**
- `components/components/food-order/food-order-list/` — list with status filter
- `components/components/food-order/food-order-add/` — place new order form

The order list should show: Order ID, Student, Canteen, Total Amount, Status, Date

Add a **status update button** that calls the `PUT /api/orders/{id}/status` endpoint with a dropdown (PENDING → CONFIRMED → READY → DELIVERED).

**Definition of Done:** Admin can view all orders and update their status

---

### ✅ Day 25 — Payment & Subscription Module (Angular)

**Files:**
- `components/components/payment/` — list payments, filter by student or status
- `components/components/subscription/` — list subscriptions, show active badge

Payment list columns: Student, Amount, Type, Status, Transaction ID, Date

Subscription list columns: Student, Mess, Plan, Start Date, End Date, Status (with colored badge)

**Definition of Done:** Payments and subscriptions are visible in the UI

---

### ✅ Day 26 — Attendance Module (Angular)

**Files:**
- `components/components/attendance/` — list + mark attendance form

The mark attendance form has:
- Student dropdown
- Meal dropdown
- Date picker
- Present/Absent toggle

The list view shows attendance records with date range filter.

**Definition of Done:** Attendance can be marked and viewed with date range filtering

---

### ✅ Day 27 — Feedback & Complaint Module (Angular)

#### Feedback Component
**Files:** `components/components/feedback/`
- List view with student name, mess, rating (show stars), comment, category
- Add form: student dropdown, mess dropdown, rating (1-5), comment, category

#### Complaint Component
**Files:** `components/components/complaint/`
- List view with status badges (color-coded: OPEN=red, IN_PROGRESS=orange, RESOLVED=green)
- Add form: title, description, category
- Resolve button that opens a text input for resolution

**Definition of Done:** Feedback and complaints are manageable with proper status visualization

---

### ✅ Day 28 — Notice Module & Dashboard (Angular)

#### Notice Component
**Files:** `components/components/notice/`
- List view showing active notices with type badges
- Add form: title, content, type, validUntil date
- Toggle to activate/deactivate notice

#### Dashboard Component
**Files:** `components/components/dashboard/`
Display summary cards showing:
- Total Students
- Total Active Subscriptions
- Total Pending Orders
- Total Open Complaints
- Recent Notices (last 3)

Each card calls the appropriate API to get count data.

**Definition of Done:** Dashboard loads with real data from all APIs

---

### ✅ Day 29 — Auth Guard, Interceptor & Login Page

**Goal:** Add basic login protection to the app.

#### Task 29.1 — Login Component
**Files:** `components/components/login/`

Simple login form with email and password fields. On submit, call a `/api/admins/login` endpoint (create this in `AdminController`):
```java
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
    return adminService.findByEmail(credentials.get("email"))
        .filter(a -> a.getPassword().equals(credentials.get("password")))
        .map(a -> ResponseEntity.ok(Map.of("message", "Login successful", "adminId", a.getId())))
        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
}
```

Store `adminId` in `localStorage` on success and redirect to dashboard.

#### Task 29.2 — Auth Guard
**File:** `guards/guards/auth-guard.ts`
```typescript
export const authGuard: CanActivateFn = () => {
  const adminId = localStorage.getItem('adminId');
  if (adminId) return true;
  inject(Router).navigate(['/login']);
  return false;
};
```

Apply this guard to all routes except `/login` in `app.routes.ts`.

#### Task 29.3 — HTTP Interceptor
**File:** `interceptors/interceptors/auth-interceptor.ts`
```typescript
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Add headers if needed in future (e.g., JWT tokens)
  return next(req);
};
```

**Definition of Done:** Unauthenticated users are redirected to login page

---

### ✅ Day 30 — Final Testing, Bug Fixes & Documentation

**Goal:** Test the entire application end-to-end and document the project.

#### Task 30.1 — End-to-End Testing Checklist
Test these complete flows:
- [ ] Register a student → assign to a mess → create a subscription
- [ ] View today's menu for a mess
- [ ] Place a food order from canteen → update status to DELIVERED → payment created
- [ ] Mark attendance for a student
- [ ] Student submits feedback for a mess
- [ ] Student submits a complaint → admin resolves it
- [ ] Admin posts a notice → visible on dashboard

#### Task 30.2 — Fix Issues
Document any bugs found during testing in a `BUGS.md` file with format:
```
Issue: [description]
Steps to Reproduce: [steps]
Expected: [behavior]
Actual: [behavior]
Status: FIXED / PENDING
```

#### Task 30.3 — Update README.md
**File:** `README.md` (project root)

Add:
- Project overview
- Tech stack
- How to run backend
- How to run frontend
- API endpoints list (table with Method, URL, Description)
- Database schema diagram (can be a simple text table)

**Definition of Done:** A new developer can clone the repo and run the app by reading only the README

---

## 📊 Summary Table

| Day | Module | Layer |
|-----|--------|-------|
| 1 | Setup | Environment |
| 2 | Admin + Student | Entity + Repository + Service Interface |
| 3 | Mess + Canteen | Entity + Repository + Service Interface |
| 4 | Meal + Menu + FoodItem | Entity + Repository + Service Interface |
| 5 | Order + Payment + Subscription | Entity + Repository + Service Interface |
| 6 | Attendance + Feedback + Complaint + Notice | Entity + Repository + Service Interface |
| 7 | All Modules | DTOs + Enums |
| 8 | Admin + Student | ServiceImpl |
| 9 | Mess + Canteen | ServiceImpl |
| 10 | Meal + Menu + FoodItem | ServiceImpl |
| 11 | Order + Payment | ServiceImpl |
| 12 | Subscription + Attendance | ServiceImpl |
| 13 | Feedback + Complaint + Notice | ServiceImpl |
| 14 | Admin + Student + Mess | REST Controllers |
| 15 | Canteen + Meal + Menu + FoodItem | REST Controllers |
| 16 | Order + Payment + Subscription | REST Controllers |
| 17 | Attendance + Feedback + Complaint + Notice | REST Controllers |
| 18 | - | Exception Handler + CORS Config |
| 19 | All Modules | Angular Models + Services |
| 20 | - | Angular Routing + Shared Components |
| 21 | Student | Angular CRUD UI |
| 22 | Mess + Canteen | Angular CRUD UI |
| 23 | Menu + FoodItem | Angular CRUD UI |
| 24 | FoodOrder | Angular CRUD UI |
| 25 | Payment + Subscription | Angular UI |
| 26 | Attendance | Angular UI |
| 27 | Feedback + Complaint | Angular UI |
| 28 | Notice + Dashboard | Angular UI |
| 29 | Auth | Login + Guard + Interceptor |
| 30 | - | Testing + Bug Fix + Documentation |

---

## 🔧 Important Notes for Intern

1. **Always run the backend first**, then the frontend
2. **Test every API in Postman** before moving to Angular
3. **Commit your work daily** with a meaningful message like `Day 8: Implemented AdminServiceImpl and StudentServiceImpl`
4. If stuck for more than 30 minutes, create a GitHub Issue describing the problem
5. **Never commit** `application.properties` with real database credentials — use `application.properties.example`
6. Use `@Valid` on all controller method parameters that accept request bodies
7. Use `@Transactional` on service methods that do multiple database operations

---

## 📁 Package Structure (Backend)

```
com.smartmess.smart_mess_management
├── config/           ← CorsConfig, etc.
├── Controllers/      ← REST Controllers
├── dto/              ← Data Transfer Objects
├── entity/           ← JPA Entities
├── enums/            ← Enum classes
├── exception/        ← GlobalExceptionHandler
├── repository/       ← JPA Repositories
├── Services/         ← Service Interfaces
└── serviceimpl/      ← Service Implementations
```

## 📁 Component Structure (Frontend)

```
src/app/
├── components/components/
│   ├── attendance/
│   ├── canteen/
│   ├── complaint/
│   ├── dashboard/
│   ├── feedback/
│   ├── food-item/
│   ├── food-order/
│   ├── login/
│   ├── meal/
│   ├── menu/
│   ├── mess/
│   ├── notice/
│   ├── payment/
│   ├── student/
│   └── subscription/
├── guards/guards/    ← Auth Guard
├── interceptors/     ← HTTP Interceptor
├── models/models/    ← TypeScript Interfaces
├── services/services/← HTTP Services
└── shared/shared/    ← Navbar, Sidebar, Footer
```
