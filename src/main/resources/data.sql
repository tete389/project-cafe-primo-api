
--DELETE FROM employee;
--
--DELETE FROM product_form;
--
--DELETE FROM product_base;
--
--DELETE FROM option;
--
--DELETE FROM add_on;
--
--DELETE FROM category;
--
--DELETE FROM material;






insert into employee(emp_id, username, password, emp_name, phone_number )
values ('eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9', 'Admin123', 'Admin123', 'Admin', 'Admin');


insert into product_base
(prod_base_id, prod_title, is_enable, description)
values ( 'PB0000001' , 'อเมริกาโน่', true, 'none');

insert into product_base
(prod_base_id, prod_title, is_enable, description)
values ( 'PB0000002' , 'ลาเต้', true, 'none');

insert into product_base
(prod_base_id, prod_title, is_enable, description)
values ( 'PB0000003' , 'มอคค่า', true, 'none');

insert into product_base
(prod_base_id, prod_title, is_enable, description)
values ( 'PB0000004' , 'เอสเปรสโซ่', true, 'none');

insert into product_base
(prod_base_id, prod_title, is_enable, description)
values ( 'PB0000005' , 'คาปูชิโน่', true, 'none');

insert into product_base
(prod_base_id, prod_title, is_enable, description)
values ( 'PB0000006' , 'กาแฟส้ม', true, 'none');


insert into product_base
(prod_base_id, prod_title, is_enable, description)
values ( 'PB0000007' , 'เค้กส้ม', true, 'none');





insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000001' , 'ร้อน', 'none', 40.0, true, 0.0, 'none', 'PB0000001');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000002' , 'เย็น', 'none', 50.0, true, 0.0, 'none', 'PB0000001');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000003' , 'ปั่น', 'none', 60.0, true, 0.0, 'none', 'PB0000001');


insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000004' , 'ร้อน', 'none', 40.0, true, 0.0, 'none', 'PB0000002');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000005' , 'เย็น', 'none', 50.0, true, 0.0, 'none', 'PB0000002');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000006' , 'ปั่น', 'none', 60.0, true, 0.0, 'none', 'PB0000002');


insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000007' , 'ร้อน', 'none', 40.0, true, 0.0, 'none', 'PB0000003');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000008' , 'เย็น', 'none', 50.0, true, 0.0, 'none', 'PB0000003');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000009' , 'ปั่น', 'none', 60.0, true, 0.0, 'none', 'PB0000003');


insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000010' , 'ร้อน', 'none', 40.0, true, 0.0, 'none', 'PB0000004');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000011' , 'เย็น', 'none', 50.0, true, 0.0, 'none', 'PB0000004');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF00000012' , 'ปั่น', 'none', 60.0, true, 0.0, 'none', 'PB0000004');


insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000013' , 'ร้อน', 'none', 40.0, true, 0.0, 'none', 'PB0000005');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000014' , 'เย็น', 'none', 50.0, true, 0.0, 'none', 'PB0000005');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000015' , 'ปั่น', 'none', 60.0, true, 0.0, 'none', 'PB0000005');


insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000016' , 'ร้อน', 'none', 40.0, true, 0.0, 'none', 'PB0000005');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000017' , 'เย็น', 'none', 50.0, true, 0.0, 'none', 'PB0000005');

insert into product_form
(prod_form_id, prod_form, image, price, is_enable, bonus_point, description, prod_base_id)
values ( 'PF0000018' , 'ปั่น', 'none', 60.0, true, 0.0, 'none', 'PB0000005');






insert into category
(cate_id, cate_name, is_enable, is_recommend)
values ( 'C00100000' , 'เมนูแนะนำ', true, true);

insert into category
(cate_id, cate_name, is_enable, is_recommend)
values ( 'C00000002' , 'กาแฟ', true, false);

insert into category
(cate_id, cate_name, is_enable, is_recommend)
values ( 'C00000003' , 'ชา', true, false);

insert into category
(cate_id, cate_name, is_enable, is_recommend)
values ( 'C00000004' , 'นม โกโก้', true, false);

insert into category
(cate_id, cate_name, is_enable, is_recommend)
values ( 'C00000005' , 'เค้ก', true, false);

insert into category
(cate_id, cate_name, is_enable, is_recommend)
values ( 'C00000006' , 'อื่นๆ', true, false);



--insert into cate_prod
--(cate_id, prod_form_id)
--values ( 'C00000001' , 'PB0000001');
--
--insert into cate_prod
--(cate_id, prod_form_id)
--values ( 'C00000001' , 'PB0000002');
--
--insert into cate_prod
--(cate_id, prod_form_id)
--values ( 'C00000001' , 'PB0000003');
--
--insert into cate_prod
--(cate_id, prod_form_id)
--values ( 'C00000001' , 'PB0000004');
--
--insert into cate_prod
--(cate_id, prod_form_id)
--values ( 'C00000001' , 'PB0000005');
--
--insert into cate_prod
--(cate_id, prod_form_id)
--values ( 'C00000001' , 'PB0000006');









insert into add_on
(add_on_id, add_on_title, is_many_options, is_enable, description)
values ( 'Ad0000001' , 'ความหวาน', false, true, 'none');

insert into add_on
(add_on_id, add_on_title, is_many_options, is_enable, description)
values ( 'Ad0000002' , 'เพิ่มช็อตกาแฟ', false, true, 'none');

insert into add_on
(add_on_id, add_on_title, is_many_options, is_enable, description)
values ( 'Ad0000003' , 'ท็อปปิ้ง', true, true, 'none');

insert into add_on
(add_on_id, add_on_title, is_many_options, is_enable, description)
values ( 'Ad0000004' , 'เพิ่มเติม', true, true, 'none');



insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000001' , 'หวานน้อย', 0.0, true, 'Ad0000001');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000002' , 'หวานปกติ', 0.0, true, 'Ad0000001');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000003' , 'หวานมาก', 0.0, true, 'Ad0000001');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000004' , '1 ช็อต', 5.0, true, 'Ad0000002');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000005' , '2 ช็อต', 10.0, true, 'Ad0000002');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000006' , '3 ช็อต', 15.0, true, 'Ad0000002');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000007' , 'วิปครีม', 10.0, true, 'Ad0000003');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000008' , 'ไข่มุก', 5.0, true, 'Ad0000003');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000009' , 'ท็อปปิ้งบราวนี่', 5.0, true, 'Ad0000003');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000010' , 'ไซรัป', 5.0, true, 'Ad0000003');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000011' , 'ท็อปปิ้งเยลลี่', 5.0, true, 'Ad0000003');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000012' , 'ท็อปปิ้งโอรีโอ', 5.0, true, 'Ad0000003');

insert into option
(option_id, option_Name, price, is_enable, add_on_id)
values ( 'Op0000013' , '1 ช็อตนม', 5.0, true, 'Ad0000004');






--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000001' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000002' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000003' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000004' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000005' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000006' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000007' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000008' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000009' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000010' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000011' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000012' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000013' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000014' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000015' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000016' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000017' , 'Ad0000001');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000018' , 'Ad0000001');
--
--
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000001' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000002' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000003' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000004' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000005' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000006' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000007' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000008' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000009' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000010' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000011' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000012' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000013' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000014' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000015' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000016' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000017' , 'Ad0000002');
--
--insert into prod_add
--(prod_form_id, add_on_id)
--values ( 'PF0000018' , 'Ad0000002');

insert into material
(mate_id, mate_name, mate_Unit, is_enable, stock)
values ( 'M00000001' , 'น้ำตาล', 'g', true, 1000);

insert into material
(mate_id, mate_name, mate_Unit, is_enable, stock)
values ( 'M00000002' , 'ผงกาแฟ', 'g', true, 1000);

insert into material
(mate_id, mate_name, mate_Unit, is_enable, stock)
values ( 'M00000003' , 'ครีมเทียม', 'g', true, 1000);

insert into material
(mate_id, mate_name, mate_Unit, is_enable, stock)
values ( 'M00000004' , 'น้ำแข็ง', 'g', true, 1000);



--CREATE INDEX idx_status ON public.order (status);

CREATE INDEX idx_prod_base ON public.product_base (prod_title);

CREATE INDEX idx_prod_form ON public.product_form (prod_form);

CREATE INDEX idx_option_Name ON public.option (option_Name);

CREATE INDEX idx_mate_name ON public.material (mate_name);