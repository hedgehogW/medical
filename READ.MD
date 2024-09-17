### 用户登录和管理模块

该设计包括患者、医生、管理员三种角色，所有的用户认证和权限管理都基于Spring Security，并使用JWT Token进行身份验证。数据访问通过MyBatis-Plus进行。

#### **1. 架构设计**

#### **1.1. 用户角色设计**
- **患者（patient）**：只能查看和管理个人信息、问诊记录等。
- **医生（doctor）**：可以管理分配给自己的患者和问诊记录。
- **管理员（admin）**：拥有最高权限，可以管理系统中的所有数据（包括用户、医生、患者、问诊记录等）。

#### **1.2. 认证与授权流程**
1. **注册与登录**：所有用户都通过注册接口注册，系统根据角色分配权限。
2. **JWT Token认证**：用户登录时，生成JWT Token，后续每次请求都通过JWT Token进行认证。
3. **权限控制**：Spring Security结合MyBatis-Plus在每次请求时检查用户的角色和权限，确保他们只能访问相应的数据。

#### **2. 数据库表设计**

数据库表需要为用户、角色、权限关系设计合适的结构，使用MyBatis-Plus来简化查询。

##### **2.1. 用户表 (users)**
存储用户的基本信息，包括患者、医生和管理员。

| 字段名        | 数据类型         | 说明                          |
|------------|--------------|-----------------------------|
| id         | BIGINT       | 主键，自增                       |
| username   | VARCHAR(50)  | 用户名，唯一                      |
| password   | VARCHAR(100) | 密码                          |
| role       | VARCHAR(20)  | 角色 (admin, doctor, patient) |
| name       | VARCHAR(100) | 姓名                          |
| phone      | VARCHAR(20)  | 联系电话                        |
| created_at | TIMESTAMP    | 创建时间                        |
| updated_at | TIMESTAMP    | 更新时间                        |

##### **2.2. 角色表 (roles)**
存储系统中的角色。

| 字段名     | 数据类型      | 说明                   |
|-----------|--------------|------------------------|
| id        | BIGINT       | 主键，自增              |
| role_name | VARCHAR(50)  | 角色名称                |

##### **2.3. 用户角色表 (user_roles)**
存储用户和角色之间的映射关系，一个用户可以有多个角色。

| 字段名   | 数据类型      | 说明                    |
|---------|--------------|------------------------|
| id      | BIGINT       | 主键，自增              |
| user_id | BIGINT       | 外键，关联用户表         |
| role_id | BIGINT       | 外键，关联角色表         |

##### **2.4. 权限表 (permissions)**
存储系统中的权限定义。

| 字段名          | 数据类型      | 说明                   |
|----------------|--------------|------------------------|
| id             | BIGINT       | 主键，自增              |
| permission_name| VARCHAR(100) | 权限名称                |

##### **2.5. 角色权限表 (role_permissions)**
存储角色和权限的关系，每个角色可以有多个权限。

| 字段名    | 数据类型      | 说明                    |
|----------|--------------|------------------------|
| id       | BIGINT       | 主键，自增              |
| role_id  | BIGINT       | 外键，关联角色表         |
| permission_id | BIGINT  | 外键，关联权限表         |

#### **3. API 设计**

##### **3.1. 注册接口**
- **URL**: `/api/auth/register`
- **Method**: `POST`
- **Description**: 用户注册，指定角色
- **Request Body**:
    ```json
    {
        "username": "string",
        "password": "string",
        "role": "admin/doctor/patient",
        "name": "string"
    }
    ```
- **Response**:
    ```json
    {
        "message": "User registered successfully."
    }
    ```

##### **3.2. 登录接口**
- **URL**: `/api/auth/login`
- **Method**: `POST`
- **Description**: 用户登录，生成JWT Token
- **Request Body**:
    ```json
    {
        "username": "string",
        "password": "string"
    }
    ```
- **Response**:
    ```json
    {
        "token": "jwt_token"
    }
    ```

##### **3.3. 获取用户信息**
- **URL**: `/api/auth/me`
- **Method**: `GET`
- **Headers**:
    - Authorization: `Bearer <jwt_token>`
- **Description**: 获取当前登录用户的信息
- **Response**:
    ```json
    {
        "id": 1,
        "username": "string",
        "role": "admin/doctor/patient",
        "name": "string"
    }
    ```

##### **3.4. 管理员权限接口**
- **URL**: `/api/admin/users`
- **Method**: `GET`
- **Headers**:
    - Authorization: `Bearer <jwt_token>`
- **Description**: 获取所有用户数据，仅限管理员访问
- **Response**:
    ```json
    [
        {
            "id": 1,
            "username": "doctor1",
            "role": "doctor",
            "name": "Dr. Smith"
        },
        {
            "id": 2,
            "username": "patient1",
            "role": "patient",
            "name": "John Doe"
        }
    ]
    ```

#### **4. 用户认证与权限管理**

##### **4.1. Spring Security 配置**
- 配置Spring Security，确保不同角色只能访问特定的接口。

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()  // 注册和登录接口允许所有访问
            .antMatchers("/api/admin/**").hasRole("ADMIN")  // 只有管理员可以访问
            .antMatchers("/api/doctors/**").hasRole("DOCTOR")  // 只有医生可以访问
            .antMatchers("/api/patients/**").hasRole("PATIENT")  // 只有患者可以访问
            .anyRequest().authenticated()  // 其他请求需要认证
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
```

##### **4.2. JwtAuthenticationFilter**
实现JWT Token的认证。

```java
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getJwtFromRequest(request);

        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
```

### **5. 总结**

通过Spring Security实现基于角色的认证和授权，并结合MyBatis-Plus实现数据库访问。三个角色（管理员、医生、患者）有不同的权限，管理员可以管理系统的所有数据，而医生和患者则有各自的访问范围。