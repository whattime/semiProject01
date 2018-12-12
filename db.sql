create table member( 
						no				number(4) constraint member_no_pk primary key, 
						name			varchar2(20) constraint member_name_nn not null, 
						birth 			varchar2(20),
						gender		 	varchar2(20),
						enroll			Date default(sysdate)
					); 
					
create sequence member_seq start with 1 increment by 1;

select * from member;

insert into member(no,name, birth ) values(member_seq.nextVal, 'james', '840827');
insert into member(no,name) values(0,'admin');

alter table member add (photo BLOB);

delete from member;
drop table member purge;
drop sequence member_seq;
