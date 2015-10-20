CREATE TABLE HRMS_COMPENSATION 
(
  COMP_ID NUMBER NOT NULL 
, EMP_ID NUMBER 
, COMP_RATIO NUMBER 
, SALARY NUMBER 
, BONUS NUMBER 
, COMMISSION NUMBER 
, DATE_EFFECTIVE DATE 
, CONSTRAINT HRMS_COMPENSATION_PK PRIMARY KEY 
  (
    COMP_ID 
  )
  ENABLE 
);

CREATE TABLE HRMS_DEPARTMENTS 
(
  DEPT_ID NUMBER NOT NULL 
, DEPT_NAME VARCHAR2(250) 
, ADDRESS VARCHAR2(250) 
, CITY VARCHAR2(100) 
, STATE VARCHAR2(100) 
, COUNTRY VARCHAR2(100) 
, POSTAL VARCHAR2(20) 
, DEPT_MGR_FIRST VARCHAR2(100) 
, DEPT_MGR_LAST VARCHAR2(100) 
, DEPT_MGR_ID NUMBER 
, LAT_COOR VARCHAR2(20) 
, LONG_COOR VARCHAR2(20) 
, CONSTRAINT HRMS_DEPARTMENTS_PK PRIMARY KEY 
  (
    DEPT_ID 
  )
  ENABLE 
);

CREATE TABLE HRMS_EMPLOYEE 
(
  EMP_ID NUMBER NOT NULL 
, FIRST_NAME VARCHAR2(255) 
, LAST_NAME VARCHAR2(255) 
, TITLE VARCHAR2(255) 
, EMAIL VARCHAR2(255) 
, PHONE VARCHAR2(255) 
, MOBILE VARCHAR2(255) 
, ADDRESS VARCHAR2(255) 
, CITY VARCHAR2(20) 
, STATE VARCHAR2(20) 
, COUNTRY VARCHAR2(20) 
, POSTAL VARCHAR2(20) 
, TWITTER VARCHAR2(255) 
, FACEBOOK VARCHAR2(255) 
, GOOGLE VARCHAR2(255) 
, LINKED_IN VARCHAR2(255) 
, COMP_RATIO NUMBER 
, SALARY NUMBER 
, BONUS NUMBER 
, COMMISSION NUMBER 
, RATING NUMBER 
, POTENTIAL VARCHAR2(255) 
, DEPT_ID NUMBER 
, DEPT_NAME VARCHAR2(255) 
, MGR_ID NUMBER 
, MGR_NAME VARCHAR2(255) 
, TEAM_AVG_RATING NUMBER 
, ACTIVE NUMBER 
, EMP_MEETINGS NUMBER 
, EMP_EVENTS VARCHAR2(20) 
, NOTIFY_APPROVED NUMBER 
, NOTIFY_WAITING NUMBER 
, NOTIFY_REJECTED NUMBER 
, GROUP_SIZE NUMBER 
, GROUP_AVG_RATING NUMBER 
, GROUP_AVG_POTENTIAL NUMBER 
, GROUP_COMP_RATIO_ABOVE NUMBER 
, GROUP_COMP_RATIO_IN NUMBER 
, GROUP_COMP_RATIO_BELOW NUMBER 
, GROUP_MEETINGS NUMBER 
, GROUP_EVENTS NUMBER 
, GROUP_TRAININGS NUMBER 
, GROUP_TIMEOFF NUMBER 
, CONSTRAINT HRMS_EMPLOYEE_PK PRIMARY KEY 
  (
    EMP_ID 
  )
  ENABLE 
);

CREATE TABLE HRMS_PERFORMANCE 
(
  PERF_ID NUMBER NOT NULL 
, EMP_ID NUMBER 
, RATING NUMBER 
, EMP_NAME VARCHAR2(255) 
, POTENTIAL NUMBER 
, DATE_EFFECTIVE DATE 
, CONSTRAINT HRMS_PERFORMANCE_PK PRIMARY KEY 
  (
    PERF_ID 
  )
  ENABLE 
);

CREATE TABLE HRMS_SKILL 
(
  SKILL_ID NUMBER NOT NULL 
, SKILL VARCHAR2(255) 
, SKILL_LEVEL NUMBER 
, CONSTRAINT HRMS_SKILL_PK PRIMARY KEY 
  (
    SKILL_ID 
  )
  ENABLE 
);