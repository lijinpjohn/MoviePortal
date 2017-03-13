# MoviePortal
Simple web based application for browsing and renting movies

Assumptions
===========
*assumes that the user data and movie details are already present in the database
*registered user will have value 'REG' in the 'user_type'
*regular user will have value 'NOR' in the 'user_type'
*DB used is mysql

Endpoints
===========
browse by genre
------------------
<server>:<port>/moviesonline/genre/<genre>
browse by year
------------------
<server>:<port>/moviesonline/year/{year}
browse rented list
------------------
<server>:<port>/moviesonline/myrent/<email>
to rent a movie
------------------
<server>:<port>/moviesonline/rent  

Notes
===========
*Please refer the comments added along with the code
*global exception handler(com.ms.controller.ExceptionControlrAdvice) is used for exceptions other than custom exceptions
*test cases are not written for all classes. Only covered important classes

DDL
=========================================================================================
CREATE TABLE USERS(
	EMAIL VARCHAR(50) PRIMARY KEY,
	PASSWORD VARCHAR(50) NOT NULL,
	FNAME VARCHAR(50) NOT NULL,
	LNAME  VARCHAR(50),
	USER_TYPE VARCHAR(3) NOT NULL,
	CARD_NO VARCHAR(20)
);

CREATE TABLE MOVIES(
	MOVIE_ID NUMBER(20) PRIMARY KEY,
	TITLE VARCHAR(100) NOT NULL,
	GENRE VARCHAR(50) NOT NULL,
	YEAR VARCHAR(4),
	PRICE NUMBER(10) NOT NULL,
);

CREATE INDEX GENRE_INDEX ON MOVIES(GENRE);
CREATE INDEX YEAR_INDEX ON MOVIES(YEAR);

CREATE TABLE RENTS(
	RENT_ID NUMBER(50) PRIMARY KEY,
	MOVIE_ID NUMBER(20) NOT NULL,
	EMAIL VARCHAR(50) NOT NULL,
	AMOUNT_PAID NUMBER(10) NOT NULL,
	RENT_DATE DATE NOT NULL,
	EXP_DATE DATE NOT NULL,
	CONSTRAINT FK_MOV_ID FOREIGN KEY (MOVIE_ID) REFERENCES MOVIES(MOVIE_ID),
	CONSTRAINT FK_USR_EMAIL FOREIGN KEY (EMAIL) REFERENCES USERS(EMAIL),
);
=========================================================================================
