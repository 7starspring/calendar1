drop table calendar CASCADE CONSTRAINTS;

CREATE TABLE calendar (
	sno number(14) NOT NULL, /* serial number = yyyyMMdd(8) + schedule_seq(6) */
	gno number(14) NOT NULL, /* group number */
	title VARCHAR2(100) NOT NULL,
	content VARCHAR2(2000),
	startdate char(12) NOT NULL, /* yyyy MM dd hh mm */
	enddate char(12) NOT NULL,
	allday char(1) NOT NULL, /* true=1 or false=0 */
	url VARCHAR2(100),
	userid number(7) NOT NULL, /* user ID */
	className varchar2(15), /*e.g: eventColor312*/
	color char(23) /*#ffffff:#ffffff:#ffffff*/
);

CREATE UNIQUE INDEX calendar_sno_idx
	ON calendar (
		sno desc
	);

ALTER TABLE calendar
	ADD
		CONSTRAINT calendar_sno_pk
		PRIMARY KEY (
			sno
		);
		
--select * from user_constraints where table_name in upper('calendar');
		
drop SEQUENCE calendar_seq;

CREATE SEQUENCE calendar_seq
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 999999
	NOCACHE
	CYCLE;

--Test Data
insert into calendar values(
'20131127' || calendar_seq.NEXTVAL,'20131127' || calendar_seq.NEXTVAL,
'title','content','201311270700','201311281500','1',
'https://www.google.com',1004,'eventColor1',
'#77ffff:#ff77ff:#ffff77');

commit;

select * from calendar;
