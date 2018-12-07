create table member( 
						no				number(4) constraint member_no_pk primary key, 
						name			varchar2(20) constraint member_name_nn not null, 
						birth 			varchar2(20), 
						enroll			Date default(sysdate) 
					); 
					
create sequence member_seq start with 1 increment by 1;