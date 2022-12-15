
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

CREATE TABLE [dbo].[SGS_COST_IDENTIFICATION_RULE](
	[COST_IDENTIFICATION_ID] [varchar](80) NOT NULL,
	[COST_IDENTIFICATION_NAME] [varchar](80) NOT NULL,
	[NATURE_OF_EXPENSE] [varchar](80) NULL,
	[COST_GROUP] [varchar](80) NULL,
	[INV_JOURNAL_GRP_CODE] [varchar](80) NULL,
	[DATA_SOURCE] [varchar](80) NOT NULL,
	[TRUE_UP_DOWN_APPLICABILITY] [varchar](80) NULL,
	[TRUE_UP_DOWN_FREQUENCY] [varchar](80) NULL,
	[ACCOUNTING_TREATMENT] [varchar](80) NULL,
	[SERVICE_CATEGORY] [varchar](80) NULL,
	[ALLOCATION_BASIS] [varchar](80) NULL,
	[INPUT_PROVIDER] [varchar](80) NULL,
	[EFFECTIVE_FROM] [date] NULL,
	[EFFECTIVE_TO] [date] NULL,
	[CREATED_DATE] [date] NULL,
	[CREATED_BY] [varchar](80) NULL,
	[UPDATED_DATE] [date] NULL,
	[UPDATED_BY] [varchar](80) NULL,
	[ATTRIBUTE1] [varchar](80) NULL,
	[ATTRIBUTE2] [varchar](80) NULL,
	[ATTRIBUTE3] [varchar](80) NULL,
	[ATTRIBUTE4] [varchar](80) NULL,
	[ATTRIBUTE5] [varchar](80) NULL,
	[ATTRIBUTE6] [date] NULL,
	[ATTRIBUTE7] [date] NULL,
	[ATTRIBUTE8] [date] NULL,
	[ATTRIBUTE9] [date] NULL,
	[ATTRIBUTE10] [date] NULL,
	[ATTRIBUTE11] [numeric](10, 5) NULL,
	[ATTRIBUTE12] [numeric](10, 5) NULL,
	[ATTRIBUTE13] [numeric](10, 5) NULL,
	[ATTRIBUTE14] [numeric](10, 5) NULL,
	[ATTRIBUTE15] [numeric](10, 5) NULL,
 CONSTRAINT [PK_SGS_COST_IDENTIFICATION_RULE] PRIMARY KEY CLUSTERED 
(
	[COST_IDENTIFICATION_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE SEQUENCE dbo.SEQ_SGS_COST_IDENTIFICATION_RULE AS 
INT START WITH 1
INCREMENT BY 1;
GO

ALTER TABLE [dbo].[SGS_COST_IDENTIFICATION_RULE] ADD  CONSTRAINT [seq_SGS_COST_IDENTIFICATION_RULEID]  DEFAULT (format(NEXT VALUE FOR [dbo].[SEQ_SGS_COST_IDENTIFICATION_RULE],'CIR00#')) FOR [COST_IDENTIFICATION_ID]
GO

Sample insert query: 
INSERT INTO SGS_COST_IDENTIFICATION_RULE(COST_IDENTIFICATION_NAME,NATURE_OF_EXPENSE,COST_GROUP,INV_JOURNAL_GRP_CODE,DATA_SOURCE,ACCOUNTING_TREATMENT,INPUT_PROVIDER,ALLOCATION_BASIS,EFFECTIVE_FROM,EFFECTIVE_TO,CREATED_DATE,CREATED_BY)
VALUES ('1234_ADOBE','LICENCE CHARGES','LICENCE CHARGES','','STANDARD RATES','REIMBURSMENT','PRASANNA','HEAD COUNT','12-11-2022','12-11-2022','12-11-2022','Manjunath')

------------------------------------------------- End SGS_COST_IDENTIFICATION_RULE -----------------------------------

--------------------------------------------------- Table SGS_COST_IDENTIFICATION_COMBINATION-------------------------
CREATE TABLE [dbo].[SGS_COST_IDENTIFICATION_COMBINATION](
	[COST_IDENTIFICATION_ID] [varchar](80) NOT NULL,
	[IC_LINE_NO] [varchar](30) NOT NULL,
	[TC_LINE_NO] [varchar](30) NULL,
	[OC_LINE_NO] [varchar](30) NULL,
	[FROM_BUSINESS_UNIT] [varchar](80) NULL,
	[FROM_OPERATING_UNIT] [varchar](80) NULL,
	[FROM_JOB_CODE] [varchar](80) NULL,
	[FROM_DEPT_ID] [varchar](80) NULL,
	[FROM_GL_ACCOUNT] [varchar](80) NULL,
	[EFFECTIVE_FROM] [date] NULL,
	[EFFECTIVE_TO] [date] NULL,
	[CREATED_DATE] [date] NOT NULL,
	[CREATED_BY] [varchar](80) NOT NULL,
	[UPDATED_DATE] [date] NULL,
	[UPDATED_BY] [varchar](80) NULL,
	[ATTRIBUTE1] [varchar](80) NULL,
	[ATTRIBUTE2] [varchar](80) NULL,
	[ATTRIBUTE3] [varchar](80) NULL,
	[ATTRIBUTE4] [varchar](80) NULL,
	[ATTRIBUTE5] [varchar](80) NULL,
	[ATTRIBUTE6] [date] NULL,
	[ATTRIBUTE7] [date] NULL,
	[ATTRIBUTE8] [date] NULL,
	[ATTRIBUTE9] [date] NULL,
	[ATTRIBUTE10] [date] NULL,
	[ATTRIBUTE11] [numeric](10, 5) NULL,
	[ATTRIBUTE12] [numeric](10, 5) NULL,
	[ATTRIBUTE13] [numeric](10, 5) NULL,
	[ATTRIBUTE14] [numeric](10, 5) NULL,
	[ATTRIBUTE15] [numeric](10, 5) NULL,
 CONSTRAINT [PK_SGS_COST_IDENTIFICATION_COMBINATION] PRIMARY KEY CLUSTERED 
(
	[IC_LINE_NO] ASC
))

ALTER TABLE [dbo].[SGS_COST_IDENTIFICATION_COMBINATION]
   ADD CONSTRAINT FK_SGS_COST_IDENTIFICATION_COMBINATION FOREIGN KEY (COST_IDENTIFICATION_ID)
      REFERENCES [dbo].[SGS_COST_IDENTIFICATION_RULE] (COST_IDENTIFICATION_ID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
;

CREATE SEQUENCE dbo.SEQ_SGS_COST_IDENTIFICATION_COMB AS 
INT START WITH 1
INCREMENT BY 1;
GO

ALTER TABLE [dbo].[SGS_COST_IDENTIFICATION_COMBINATION] ADD  CONSTRAINT [seq_SGS_COST_IDENTIFICATION_COMBOID]  DEFAULT (format(NEXT VALUE FOR [dbo].[SEQ_SGS_COST_IDENTIFICATION_COMB],'IC00#')) FOR [IC_LINE_NO]
GO


-------------------------------------------- End SGS_COST_IDENTIFICATION_COMBINATION-------------------------------

--------------------------------------------------- Table SGS_COST_TARGET_COMBINATION-------------------------
CREATE TABLE [dbo].[SGS_COST_TARGET_COMBINATION](
	[COST_IDENTIFICATION_ID] [varchar](80) NOT NULL,
	[TC_LINE_NO] [varchar](30) NOT NULL,
	[IC_LINE_NO] [varchar](30) NULL,
	[OC_LINE_NO] [varchar](30) NULL,
	[TARGET_BUSINESS_UNIT] [varchar](80) NULL,
	[TARGET_OPERATING_UNIT] [varchar](80) NOT NULL,
	[TARGET_JOB_CODE] [varchar](80) NULL,
	[TARGET_GL_ACCOUNT] [varchar](80) NULL,
	[FROM_GL_ACCOUNT] [varchar](80) NULL,
	[EFFECTIVE_FROM] [date] NULL,
	[EFFECTIVE_TO] [date] NULL,
	[CREATED_DATE] [date] NULL,
	[CREATED_BY] [varchar](80) NULL,
	[UPDATED_DATE] [date] NULL,
	[UPDATED_BY] [varchar](80) NULL,
	[ATTRIBUTE1] [varchar](80) NULL,
	[ATTRIBUTE2] [varchar](80) NULL,
	[ATTRIBUTE3] [varchar](80) NULL,
	[ATTRIBUTE4] [varchar](80) NULL,
	[ATTRIBUTE5] [varchar](80) NULL,
	[ATTRIBUTE6] [date] NULL,
	[ATTRIBUTE7] [date] NULL,
	[ATTRIBUTE8] [date] NULL,
	[ATTRIBUTE9] [date] NULL,
	[ATTRIBUTE10] [date] NULL,
	[ATTRIBUTE11] [numeric](10, 5) NULL,
	[ATTRIBUTE12] [numeric](10, 5) NULL,
	[ATTRIBUTE13] [numeric](10, 5) NULL,
	[ATTRIBUTE14] [numeric](10, 5) NULL,
	[ATTRIBUTE15] [numeric](10, 5) NULL,
 CONSTRAINT [PK_SGS_COST_TARGET_COMBINATION] PRIMARY KEY CLUSTERED 
(
	[TC_LINE_NO] ASC
))

ALTER TABLE [dbo].[SGS_COST_TARGET_COMBINATION]
   ADD CONSTRAINT FK_SGS_COST_TARGET_COMBINATION FOREIGN KEY (COST_IDENTIFICATION_ID)
      REFERENCES [dbo].[SGS_COST_IDENTIFICATION_RULE] (COST_IDENTIFICATION_ID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
;

CREATE SEQUENCE dbo.SEQ_SGS_COST_TARGET_COMBINATION AS 
INT START WITH 1
INCREMENT BY 1;
GO

ALTER TABLE [dbo].[SGS_COST_TARGET_COMBINATION] ADD  CONSTRAINT [seq_SEQ_SGS_COST_TARGET_COMBINATIONID]  DEFAULT (format(NEXT VALUE FOR [dbo].[SEQ_SGS_COST_TARGET_COMBINATION],'TC00#')) FOR [TC_LINE_NO]
GO

------------------------------------ End SGS_COST_TARGET_COMBINATION-----------------------------------------


--------------------------------- Table SGS_COST_OFFSET_COMBINATION--------------------------------------------
CREATE TABLE [dbo].[SGS_COST_OFFSET_COMBINATION](
	[COST_IDENTIFICATION_ID] [varchar](80) NOT NULL,
	[OC_LINE_NO] [varchar](30) NOT NULL,
	[TC_LINE_NO] [varchar](30) NULL,
	[IC_LINE_NO] [varchar](30) NULL,
	[OFFSET_BUSINESS_UNIT] [varchar](80) NULL,
	[OFFSET_OPERATING_UNIT] [varchar](80) NOT NULL,
	[OFFSET_JOB_CODE] [varchar](80) NULL,
	[OFFSET_GL_ACCOUNT] [varchar](80) NULL,
	[FROM_GL_ACCOUNT] [varchar](80) NULL,
	[EFFECTIVE_FROM] [date] NULL,
	[EFFECTIVE_TO] [date] NULL,
	[CREATED_DATE] [date] NOT NULL,
	[CREATED_BY] [varchar](80) NOT NULL,
	[UPDATED_DATE] [date] NULL,
	[UPDATED_BY] [varchar](80) NULL,
	[ATTRIBUTE1] [varchar](80) NULL,
	[ATTRIBUTE2] [varchar](80) NULL,
	[ATTRIBUTE3] [varchar](80) NULL,
	[ATTRIBUTE4] [varchar](80) NULL,
	[ATTRIBUTE5] [varchar](80) NULL,
	[ATTRIBUTE6] [date] NULL,
	[ATTRIBUTE7] [date] NULL,
	[ATTRIBUTE8] [date] NULL,
	[ATTRIBUTE9] [date] NULL,
	[ATTRIBUTE10] [date] NULL,
	[ATTRIBUTE11] [numeric](10, 5) NULL,
	[ATTRIBUTE12] [numeric](10, 5) NULL,
	[ATTRIBUTE13] [numeric](10, 5) NULL,
	[ATTRIBUTE14] [numeric](10, 5) NULL,
	[ATTRIBUTE15] [numeric](10, 5) NULL,
 CONSTRAINT [PK_SGS_COST_OFFSET_COMBINATION] PRIMARY KEY CLUSTERED 
(
	[OC_LINE_NO] ASC
))

ALTER TABLE [dbo].[SGS_COST_OFFSET_COMBINATION]
   ADD CONSTRAINT FK_SGS_COST_OFFSET_COMBINATION FOREIGN KEY (COST_IDENTIFICATION_ID)
      REFERENCES [dbo].[SGS_COST_IDENTIFICATION_RULE] (COST_IDENTIFICATION_ID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
;

CREATE SEQUENCE dbo.SEQ_SGS_COST_OFFSET_COMBINATION AS 
INT START WITH 1
INCREMENT BY 1;
GO

ALTER TABLE [dbo].[SGS_COST_OFFSET_COMBINATION] ADD  CONSTRAINT [seq_SGS_COST_OFFSET_COMBINATIONID]  DEFAULT (format(NEXT VALUE FOR [dbo].[SEQ_SGS_COST_OFFSET_COMBINATION],'OC00#')) FOR [OC_LINE_NO]
GO

---------------------------- Lookup Table---------------------
CREATE TABLE [dbo].[SGS_LOOKUP_TABLE](
	[LOOKUP_CODE] [int] NOT NULL,
	[MEANING] [varchar](80) NULL,
	[LOOKUP_TYPE] [varchar](250) NULL,
	[ENABLED] [varchar](80) NULL,
	[DISPLAY_SEQUENCE] int NULL,
	[CREATED_DATE] [date] NULL,
	[CREATED_BY] [varchar](30) NULL,
	[UPDATED_DATE] [date] NULL,
	[UPDATED_BY] [varchar](30) NULL,
 CONSTRAINT [PK_SGS_LOOKUP_TABLE] PRIMARY KEY CLUSTERED 
(
	[LOOKUP_CODE] ASC
))

--------------------------End of LookUp Table --------------------

CREATE SEQUENCE dbo.SEQ_SGS_COST_IDEN_COMBINATION AS
INT START WITH 1
INCREMENT BY 1;
GO



------------------------SetupRules BU Master Table------------------
CREATE TABLE SGS_BUSINESS_UNIT_MASTER(
    [BU_SEQ] [int] NULL,
	[BUSSINESS_UNIT_ID] [varchar](80) NOT NULL,
	[BUSSINESS_UNIT_NAME] [varchar](80) NULL,
	[LEGAL_ENTITY] [varchar](80) NULL,
	[BUSSINESS_UNIT_GEOGRAPHY] [varchar](80) NULL,
	[BUSSINESS_UNIT_ZONE] [varchar](80) NULL,
	[BUSINESS_CONTROLLERS] [varchar](80) NULL,
	[BUSSINESS_UNIT_STATUS] [varchar](80) NULL,
	[ALTERNATE_BU] varchar(80) NULL,
	[FUNCTIONAL_CURRENCY] [varchar](80) NULL,
	[SUB_LEDGER_APPLICABILITY] [varchar](80) NULL,
	[ICO_SUPPLIER_NAME] [varchar](80) NULL,
	[ICO_CUSTOMER_NAME] [varchar](80) NULL,
	[OPERATING_UNIT] [varchar](80) NULL,
	[GST_REG_NUM] [varchar](80) NULL,
	[COLLECTION_BU] [varchar](80) NULL,
    [PAYING_BU] [varchar](80) NULL,
    [DEPARTMENT_ID] [varchar](80) NULL,
	[EFFECTIVE_START_DATE] date NULL,
	[EFFECTIVE_END_DATE] date NULL,
	[CREATED_DATE] [date] NOT NULL,
	[CREATED_BY] [varchar](80) NOT NULL,
	[UPDATED_DATE] date NULL,
	[UPDATED_BY] [varchar](80) NULL,
	[ATTRIBUTE1] [varchar](80) NULL,
	[ATTRIBUTE2] [varchar](80) NULL,
	[ATTRIBUTE3] [varchar](80) NULL,
	[ATTRIBUTE4] [varchar](80) NULL,
	[ATTRIBUTE5] [varchar](80) NULL,
	[ATTRIBUTE6] [date] NULL,
	[ATTRIBUTE7] [date] NULL,
	[ATTRIBUTE8] [date] NULL,
	[ATTRIBUTE9] [date] NULL,
	[ATTRIBUTE10] [date] NULL,
	[ATTRIBUTE11] [numeric](10, 5) NULL,
	[ATTRIBUTE12] [numeric](10, 5) NULL,
	[ATTRIBUTE13] [numeric](10, 5) NULL,
	[ATTRIBUTE14] [numeric](10, 5) NULL,
	[ATTRIBUTE15] [numeric](10, 5) NULL,
 CONSTRAINT PK_SGS_BUSINESS_UNIT_MASTER PRIMARY KEY CLUSTERED 
(
	BUSSINESS_UNIT_ID ASC
));


CREATE SEQUENCE dbo.SEQ_SGS_BUSINESS_UNIT_MASTER AS 
INT START WITH 1
INCREMENT BY 1;
GO
----------------------------------End Setup Rules BU Master Table--------------


----------------------------------13-12-2022 Raja Script--------------

CREATE TABLE [dbo].[SGS_STATISTICAL_DATA_TBL]( 
              [STATISTICAL_DATA_ID] [int] NOT NULL,                      
              [STATISTICAL_DATA_CATEGORY] [varchar](80) NOT NULL,                                             
              [TO_BUSINESS_UNIT] [varchar](80) NULL,                                        
              [TO_JOB_CODE] [varchar](80) NULL,                                  
              [TO_OPERATING_UNIT] [varchar](80) NULL,                                    
              [TO_DEPARTMENT_ID]  [varchar](80) NULL,                     
              [STATISTICAL_DATA] [varchar](80) NULL,                                           
              [UNIT_OF_MEASURE]  [varchar](80) NULL,                    
              [COST_GROUP] [varchar](80) NULL,                        
              [CURRENCY]  [varchar](80) NULL,                                              
              [EMPLOYEE_ID] [varchar](80) NULL, 
              [TARGET_AMOUNT] [numeric](20, 5) NULL,                            
              [REJECTED_REASON] [varchar](80) NULL, 
			  [REJECTION_COMMENTS] [varchar](100) NULL, 
			  [VALIDITY_FROM] [Date] NULL, 
			  [VALIDITY_TILL] [Date] NULL, 
              [CREATED_BY] [varchar](80) NULL,                                      
   
			  [UPDATED_DATE] [date] NULL, 			  
              [UPDATED_BY] [varchar](80) NULL,                      
              [ATTRIBUTE1] [varchar](80) NULL,                      
              [ATTRIBUTE2] [varchar](80) NULL,                      
              [ATTRIBUTE3] [varchar](80) NULL,                      
              [ATTRIBUTE4] [varchar](80) NULL,                      
              [ATTRIBUTE5] [varchar](80) NULL,                      
              [ATTRIBUTE6] [date] NULL,
              [ATTRIBUTE7] [date] NULL,
              [ATTRIBUTE8] [date] NULL,
              [ATTRIBUTE9] [date] NULL,
              [ATTRIBUTE10] [date] NULL,
              [ATTRIBUTE11] [numeric](20, 5) NULL,
              [ATTRIBUTE12] [numeric](20, 5) NULL,
              [ATTRIBUTE13] [numeric](20, 5) NULL,
              [ATTRIBUTE14] [numeric](10, 5) NULL,
              [ATTRIBUTE15] [numeric](10, 5) NULL,
CONSTRAINT [PK_SGS_STATISTICAL_DATA_TBL] PRIMARY KEY CLUSTERED 
(
              [STATISTICAL_DATA_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO





CREATE SEQUENCE dbo.SEQ_SGS_STATISTICAL_DATA_TBL AS 
INT START WITH 1
INCREMENT BY 1;
GO


----------------------------------14-12-2022 Raja Script--------------

ALTER TABLE SGS_TPA_MASTER ALTER COLUMN "PASS_THROUGH_FLAG" VARCHAR(80);






----------------------------------14-12-2022 Kiran Script--------------
CREATE TABLE [dbo].[SGS_STANDARD_RATE_SETUP](

              [STANDARD_RATE_SEQ] [int] NOT NULL,                     

              [STANDARD_RATE_ID] [varchar](80) NOT NULL,                                            

              [EXPENSE_TYPE] [varchar](80) NULL,                                       

              [COST_IDENTIFIER] [varchar](80) NULL,                                  

              [SUB_EXPENSE_TYPE] [varchar](80) NULL,                                   

              [SR_GEOGRAPHY]  [varchar](80) NULL,                    

              [EMPLOYEE_GRADE_LEVEL] [varchar](80) NULL,                                           

              [STANDARD_COST]  [numeric](20, 5) NULL,                   

              [NON_PERSONNEL_COST] [numeric](20, 5) NULL,                       

              [DEPT_COST_CENTER]  [varchar](80) NULL,            

              [CURRENCY]  [varchar](80) NULL,                               

              [UNIT_OF_MEASURE] [varchar](80) NULL,                        

              [EMPLOYEE_ID] [varchar](80) NULL,                                           

              [PROGRAM_NAME] [varchar](80) NULL,                                          

                                             [EFFECTIVE_END_DATE] [date] NULL,

              [EFFECTIVE_START_DATE] [date] NULL,

              [CREATED_BY] [varchar](80) NULL,

              [CREATED_DATE] [date] NULL,                                   

              [UPDATED_DATE] [date] NULL,                                  

              [UPDATED_BY] [varchar](80) NULL,                     

              [ATTRIBUTE1] [varchar](80) NULL,                     

              [ATTRIBUTE2] [varchar](80) NULL,                      

              [ATTRIBUTE3] [varchar](80) NULL,                     

              [ATTRIBUTE4] [varchar](80) NULL,                     

              [ATTRIBUTE5] [varchar](80) NULL,                     

              [ATTRIBUTE6] [date] NULL,

              [ATTRIBUTE7] [date] NULL,

              [ATTRIBUTE8] [date] NULL,

              [ATTRIBUTE9] [date] NULL,

              [ATTRIBUTE10] [date] NULL,

              [ATTRIBUTE11] [numeric](20, 5) NULL,

              [ATTRIBUTE12] [numeric](20, 5) NULL,

              [ATTRIBUTE13] [numeric](20, 5) NULL,

              [ATTRIBUTE14] [numeric](10, 5) NULL,

              [ATTRIBUTE15] [numeric](10, 5) NULL,

CONSTRAINT [PK_SGS_STANDARD_RATE_SETUP] PRIMARY KEY CLUSTERED

(

              [STANDARD_RATE_SEQ] ASC

)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]

) ON [PRIMARY]

GO

 

CREATE SEQUENCE dbo.SEQ_STANDARD_RATE_SETUP AS

INT START WITH 1

INCREMENT BY 1;

GO




























