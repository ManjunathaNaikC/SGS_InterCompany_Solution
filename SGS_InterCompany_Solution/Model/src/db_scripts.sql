CREATE TABLE USER_AUTHENTICATION(
		USER_ID varchar(30) NOT NULL,
		PASSWORD varchar(30) NOT NULL,
		EMAIL_ID varchar(30) NOT NULL,
		CREATED_DATE date NULL,
		CREATED_BY varchar(30) NULL,
		UPDATED_DATE date NULL,
		UPDATED_BY varchar(30) NULL,
		ATTRIBUTE1 varchar(80) NULL,
		ATTRIBUTE2 varchar(80) NULL,
		ATTRIBUTE3 varchar(80) NULL,
		ATTRIBUTE4 varchar(80) NULL,
		ATTRIBUTE5 varchar(80) NULL,
		ATTRIBUTE6 date NULL,
		ATTRIBUTE7 date NULL,
		ATTRIBUTE8 int NULL,
		ATTRIBUTE9 int NULL,
		ATTRIBUTE10 int NULL
 CONSTRAINT PK_USER_AUTHENTICATION PRIMARY KEY CLUSTERED 
(
	USER_ID ASC
));



CREATE TABLE ROLE_DETAIL(

		ROLE_ID                 varchar(30) NOT NULL,  
		ROLE_NAME               varchar(30) NOT NULL,
		COST_IDENTIFIER         BIT  NULL,
		BUSINESS_UNIT_SETUP     BIT  NULL,
		TPA_SETUP               BIT  NULL,
		MARKUP_SETUP            BIT  NULL,
		WHT_TAX_SETUP           BIT  NULL,
		GST_SETUP               BIT  NULL,
		VAT_SETUP               BIT  NULL,
		ISD_SETUP               BIT  NULL,
		PROCESS_ICO_TRX         BIT  NULL,
		STATISTICAL_DATA        BIT  NULL,
		ICO_DASHBOARD           BIT  NULL,
		FIXED_ASSETS_TRX        BIT  NULL,
		NETTING                 BIT  NULL,
		SETTLEMENT_DASHBOARD    BIT  NULL,
		ROLE_DESCRIPTION        varchar(80)  NULL,
		CREATED_DATE            date  NULL,
		CREATED_BY              varchar(30)  NULL,
		UPDATED_DATE            date  NULL,
		UPDATED_BY              varchar(30)  NULL,
		ATTRIBUTE1              varchar(80)  NULL,
		ATTRIBUTE2              varchar(80) NULL,
		ATTRIBUTE3              varchar(80)  NULL,
		ATTRIBUTE4              varchar(80)  NULL,
		ATTRIBUTE5              varchar(80)  NULL,
		ATTRIBUTE6 				date NULL,
		ATTRIBUTE7 				date NULL,
		ATTRIBUTE8 				int NULL,
		ATTRIBUTE9 				int NULL,
		ATTRIBUTE10 			int NULL

		
 CONSTRAINT PK_ROLE_DETAIL PRIMARY KEY CLUSTERED 
(
	ROLE_ID ASC
));


CREATE TABLE USER_ROLE_MAPPING
(


USER_ID       varchar(30) NOT NULL,
ROLE_ID       varchar(30) NOT NULL,
CREATED_DATE  date        NULL,
CREATED_BY    varchar(30) NULL,
UPDATED_DATE  date        NULL,
UPDATED_BY    varchar(30) NULL,
ATTRIBUTE1    varchar(80) NULL,
ATTRIBUTE2    varchar(80) NULL,
ATTRIBUTE3    varchar(80) NULL,
ATTRIBUTE4    varchar(80) NULL,
ATTRIBUTE5    varchar(80) NULL,
ATTRIBUTE6    date NULL,
ATTRIBUTE7    date NULL,
ATTRIBUTE8    int NULL,
ATTRIBUTE9    int NULL,
ATTRIBUTE10   int NULL


CONSTRAINT PK_USER_ROLE_MAPPING PRIMARY KEY CLUSTERED 
(
	USER_ID ASC
));


CREATE TABLE BUSINESS_UNIT_DETAILS
(
		BUSINESS_UNIT_ID       varchar(30) NOT NULL,
		BUSINESS_UNIT_NAME     varchar(30) NOT NULL,
		BU_DESC   varchar(80) NULL,
		CREATED_DATE  date        NULL,
		CREATED_BY    varchar(30) NULL,
		UPDATED_DATE  date        NULL,
		UPDATED_BY    varchar(30) NULL,
		ATTRIBUTE1    varchar(80) NULL,
		ATTRIBUTE2    varchar(80) NULL,
		ATTRIBUTE3    varchar(80) NULL,
		ATTRIBUTE4    varchar(80) NULL,
		ATTRIBUTE5    varchar(80) NULL,
		ATTRIBUTE6 date NULL,
		ATTRIBUTE7 date NULL,
		ATTRIBUTE8 int NULL,
		ATTRIBUTE9 int NULL,
		ATTRIBUTE10 int NULL

CONSTRAINT PK_BUSINESS_UNIT_DETAILS PRIMARY KEY CLUSTERED 
(
	BUSINESS_UNIT_ID ASC
));




































