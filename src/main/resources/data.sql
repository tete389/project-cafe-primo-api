insert into employee
(emp_id, emp_login_id, emp_password, emp_name, emp_tel )
values ('eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9', 'Admin', 'Admin', 'Admin', 'Admin');

insert into type
(type_id, type_name, type_sale, type_status)
values ( 'T0000101' , 'เครื่องดื่ม', true, 'enable');

insert into type
(type_id, type_name, type_sale, type_status)
values ( 'T0000102' , 'เบเกอรี่', true, 'enable');

insert into type
(type_id, type_name, type_sale, type_status)
values ( 'T0000103' , 'เค้ก', true, 'enable');

insert into type
(type_id, type_name, type_sale, type_status)
values ( 'T0000110' , 'อื่นๆ', true, 'enable');



insert into category
(cate_id, cate_name, cate_sale, cate_status)
values ( 'C0000201' , 'ของทานเล่น', true, 'enable');

insert into category
(cate_id, cate_name, cate_sale, cate_status)
values ( 'C0000202' , 'เมนูร้อน', true, 'enable');

insert into category
(cate_id, cate_name, cate_sale, cate_status)
values ( 'C0000203' , 'เมนูเย็น', true, 'enable');

insert into category
(cate_id, cate_name, cate_sale, cate_status)
values ( 'C0000204' , 'เมนูปั่น', true, 'enable');

insert into category
(cate_id, cate_name, cate_sale, cate_status)
values ( 'C0000205' , 'กาแฟ', true, 'enable');

insert into category
(cate_id, cate_name, cate_sale, cate_status)
values ( 'C0000206' , 'ชา', true, 'enable');

insert into category
(cate_id, cate_name, cate_sale, cate_status)
values ( 'C0000210' , 'อื่นๆ', true, 'enable');


insert into material
(mate_id, mate_name, mate_sale, mate_stock, mate_status)
values ( 'M0000301' , 'น่ำแข็ง', true, 100, 'enable');


insert into sweetness
(sweet_id, sweet_level, sweet_sale, sweet_status)
values ( 'SWE0000401' , 'หวานปกติ', true, 'enable');

insert into sweetness
(sweet_id, sweet_level, sweet_sale, sweet_status)
values ( 'SWE0000402' , 'หวานน้อย', true, 'enable');

insert into sweetness
(sweet_id, sweet_level, sweet_sale, sweet_status)
values ( 'SWE0000403' , 'หวานมาก', true, 'enable');


insert into additional
(add_id, add_name, add_sale, add_price, add_stock, add_status)
values ( 'A0000501' , 'ไข่มุก', true, 10, 100, 'enable');
