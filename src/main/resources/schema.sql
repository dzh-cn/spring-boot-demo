CREATE  TABLE USER
( id INT AUTO_INCREMENT PRIMARY KEY,
  USERNAME VARCHAR(40),
  NAME VARCHAR (20),
  age INT (3),
  balance DECIMAL (10,2)
);
create table fj_execute_result(
 result_id int AUTO_INCREMENT primary key ,
 task_count int null,
 once_millis int null,
 total_millis int null,
 fj_total_millis int null,
 fj_thread_count int null,
 scale decimal(5,2) null,
 created_date datetime NULL,
 modified_date datetime NULL
);