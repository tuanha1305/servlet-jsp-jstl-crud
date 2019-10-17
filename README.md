# Simple CRUD App using Servlet
## Step 1: Create database & table
database: crud_servlet_jsp
```sql
create database crud_servlet_jsp
```
table: students
```sql
CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
```
## Step 2: Change your url database in StudentServlet.class
Change here:
```java
public void init() {
   dao = new DAO("jdbc:mysql://localhost/crud_servlet_jsp", "root", "");
}
```
Read tutorial [Servlet JSP JSTL JDBC Create Read Update Delete (CRUD) - Quản lý sinh viên](http://127.0.0.1:4000/2019/10/02/servlet-jsp-jstl-jdbc-example.html)
Thanks for watching !
