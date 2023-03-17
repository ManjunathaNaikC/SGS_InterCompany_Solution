package com.sgs.ics.model.bc.entity;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;

import com.sgs.ics.model.bc.commonutils.CommonUtils;

import java.math.BigDecimal;

import java.sql.Date;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jan 03 13:32:22 IST 2023
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class sgsStatisticalDataTempEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        StatisticalDataId,
        StatisticalDataCategory,
        ToBusinessUnit,
        ToJobCode,
        ToOperatingUnit,
        ToDepartmentId,
        UnitOfMeasure,
        CostGroup,
        Currency,
        EmployeeId,
        TargetAmount,
        RejectedReason,
        RejectionComments,
        ValidityFrom,
        ValidityTill,
        CreatedBy,
        UpdatedDate,
        UpdatedBy,
        CreatedDate,
        ADDTEXPENSECAT,
        EMPGRADE,
        FROMBU,
        FROMDEPTID,
        FROMJOBCODE,
        FROMOU,
        GLACCOUNT,
        INPUTPROVIDER,
        NATUREOFEXPENSE,
        STATGEOGRAPHY,
        CONCATEID,
        TRANSACTIONPERIOD,
        STATISTICALDATA,
        SLOC;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        protected int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        protected static final int firstIndex() {
            return firstIndex;
        }

        protected static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        protected static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    private static final ADFLogger LOG = ADFLogger.createADFLogger(sgsStatisticalDataTempEOImpl.class);


    public static final int STATISTICALDATAID = AttributesEnum.StatisticalDataId.index();
    public static final int STATISTICALDATACATEGORY = AttributesEnum.StatisticalDataCategory.index();
    public static final int TOBUSINESSUNIT = AttributesEnum.ToBusinessUnit.index();
    public static final int TOJOBCODE = AttributesEnum.ToJobCode.index();
    public static final int TOOPERATINGUNIT = AttributesEnum.ToOperatingUnit.index();
    public static final int TODEPARTMENTID = AttributesEnum.ToDepartmentId.index();
    public static final int UNITOFMEASURE = AttributesEnum.UnitOfMeasure.index();
    public static final int COSTGROUP = AttributesEnum.CostGroup.index();
    public static final int CURRENCY = AttributesEnum.Currency.index();
    public static final int EMPLOYEEID = AttributesEnum.EmployeeId.index();
    public static final int TARGETAMOUNT = AttributesEnum.TargetAmount.index();
    public static final int REJECTEDREASON = AttributesEnum.RejectedReason.index();
    public static final int REJECTIONCOMMENTS = AttributesEnum.RejectionComments.index();
    public static final int VALIDITYFROM = AttributesEnum.ValidityFrom.index();
    public static final int VALIDITYTILL = AttributesEnum.ValidityTill.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int UPDATEDDATE = AttributesEnum.UpdatedDate.index();
    public static final int UPDATEDBY = AttributesEnum.UpdatedBy.index();
    public static final int CREATEDDATE = AttributesEnum.CreatedDate.index();
    public static final int ADDTEXPENSECAT = AttributesEnum.ADDTEXPENSECAT.index();
    public static final int EMPGRADE = AttributesEnum.EMPGRADE.index();
    public static final int FROMBU = AttributesEnum.FROMBU.index();
    public static final int FROMDEPTID = AttributesEnum.FROMDEPTID.index();
    public static final int FROMJOBCODE = AttributesEnum.FROMJOBCODE.index();
    public static final int FROMOU = AttributesEnum.FROMOU.index();
    public static final int GLACCOUNT = AttributesEnum.GLACCOUNT.index();
    public static final int INPUTPROVIDER = AttributesEnum.INPUTPROVIDER.index();
    public static final int NATUREOFEXPENSE = AttributesEnum.NATUREOFEXPENSE.index();
    public static final int STATGEOGRAPHY = AttributesEnum.STATGEOGRAPHY.index();
    public static final int CONCATEID = AttributesEnum.CONCATEID.index();
    public static final int TRANSACTIONPERIOD = AttributesEnum.TRANSACTIONPERIOD.index();
    public static final int STATISTICALDATA = AttributesEnum.STATISTICALDATA.index();
    public static final int SLOC = AttributesEnum.SLOC.index();

    /**
     * This is the default constructor (do not remove).
     */
    public sgsStatisticalDataTempEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.sgs.ics.model.bc.entity.sgsStatisticalDataTempEO");
    }


    /**
     * Gets the attribute value for StatisticalDataId, using the alias name StatisticalDataId.
     * @return the value of StatisticalDataId
     */
    public Integer getStatisticalDataId() {
        return (Integer) getAttributeInternal(STATISTICALDATAID);
    }

    /**
     * Sets <code>value</code> as the attribute value for StatisticalDataId.
     * @param value value to set the StatisticalDataId
     */
    public void setStatisticalDataId(Integer value) {
        setAttributeInternal(STATISTICALDATAID, value);
    }

    /**
     * Gets the attribute value for StatisticalDataCategory, using the alias name StatisticalDataCategory.
     * @return the value of StatisticalDataCategory
     */
    public String getStatisticalDataCategory() {
        return (String) getAttributeInternal(STATISTICALDATACATEGORY);
    }

    /**
     * Sets <code>value</code> as the attribute value for StatisticalDataCategory.
     * @param value value to set the StatisticalDataCategory
     */
    public void setStatisticalDataCategory(String value) {
        setAttributeInternal(STATISTICALDATACATEGORY, value);
    }

    /**
     * Gets the attribute value for ToBusinessUnit, using the alias name ToBusinessUnit.
     * @return the value of ToBusinessUnit
     */
    public String getToBusinessUnit() {
        return (String) getAttributeInternal(TOBUSINESSUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for ToBusinessUnit.
     * @param value value to set the ToBusinessUnit
     */
    public void setToBusinessUnit(String value) {
        setAttributeInternal(TOBUSINESSUNIT, value);
    }

    /**
     * Gets the attribute value for ToJobCode, using the alias name ToJobCode.
     * @return the value of ToJobCode
     */
    public String getToJobCode() {
        return (String) getAttributeInternal(TOJOBCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ToJobCode.
     * @param value value to set the ToJobCode
     */
    public void setToJobCode(String value) {
        setAttributeInternal(TOJOBCODE, value);
    }

    /**
     * Gets the attribute value for ToOperatingUnit, using the alias name ToOperatingUnit.
     * @return the value of ToOperatingUnit
     */
    public String getToOperatingUnit() {
        return (String) getAttributeInternal(TOOPERATINGUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for ToOperatingUnit.
     * @param value value to set the ToOperatingUnit
     */
    public void setToOperatingUnit(String value) {
        setAttributeInternal(TOOPERATINGUNIT, value);
    }

    /**
     * Gets the attribute value for ToDepartmentId, using the alias name ToDepartmentId.
     * @return the value of ToDepartmentId
     */
    public String getToDepartmentId() {
        return (String) getAttributeInternal(TODEPARTMENTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ToDepartmentId.
     * @param value value to set the ToDepartmentId
     */
    public void setToDepartmentId(String value) {
        setAttributeInternal(TODEPARTMENTID, value);
    }


    /**
     * Gets the attribute value for UnitOfMeasure, using the alias name UnitOfMeasure.
     * @return the value of UnitOfMeasure
     */
    public String getUnitOfMeasure() {
        return (String) getAttributeInternal(UNITOFMEASURE);
    }

    /**
     * Sets <code>value</code> as the attribute value for UnitOfMeasure.
     * @param value value to set the UnitOfMeasure
     */
    public void setUnitOfMeasure(String value) {
        setAttributeInternal(UNITOFMEASURE, value);
    }

    /**
     * Gets the attribute value for CostGroup, using the alias name CostGroup.
     * @return the value of CostGroup
     */
    public String getCostGroup() {
        return (String) getAttributeInternal(COSTGROUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for CostGroup.
     * @param value value to set the CostGroup
     */
    public void setCostGroup(String value) {
        setAttributeInternal(COSTGROUP, value);
    }

    /**
     * Gets the attribute value for Currency, using the alias name Currency.
     * @return the value of Currency
     */
    public String getCurrency() {
        return (String) getAttributeInternal(CURRENCY);
    }

    /**
     * Sets <code>value</code> as the attribute value for Currency.
     * @param value value to set the Currency
     */
    public void setCurrency(String value) {
        setAttributeInternal(CURRENCY, value);
    }

    /**
     * Gets the attribute value for EmployeeId, using the alias name EmployeeId.
     * @return the value of EmployeeId
     */
    public String getEmployeeId() {
        return (String) getAttributeInternal(EMPLOYEEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for EmployeeId.
     * @param value value to set the EmployeeId
     */
    public void setEmployeeId(String value) {
        setAttributeInternal(EMPLOYEEID, value);
    }

    /**
     * Gets the attribute value for TargetAmount, using the alias name TargetAmount.
     * @return the value of TargetAmount
     */
    public BigDecimal getTargetAmount() {
        return (BigDecimal) getAttributeInternal(TARGETAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for TargetAmount.
     * @param value value to set the TargetAmount
     */
    public void setTargetAmount(BigDecimal value) {
        setAttributeInternal(TARGETAMOUNT, value);
    }

    /**
     * Gets the attribute value for RejectedReason, using the alias name RejectedReason.
     * @return the value of RejectedReason
     */
    public String getRejectedReason() {
        return (String) getAttributeInternal(REJECTEDREASON);
    }

    /**
     * Sets <code>value</code> as the attribute value for RejectedReason.
     * @param value value to set the RejectedReason
     */
    public void setRejectedReason(String value) {
        setAttributeInternal(REJECTEDREASON, value);
    }

    /**
     * Gets the attribute value for RejectionComments, using the alias name RejectionComments.
     * @return the value of RejectionComments
     */
    public String getRejectionComments() {
        return (String) getAttributeInternal(REJECTIONCOMMENTS);
    }

    /**
     * Sets <code>value</code> as the attribute value for RejectionComments.
     * @param value value to set the RejectionComments
     */
    public void setRejectionComments(String value) {
        setAttributeInternal(REJECTIONCOMMENTS, value);
    }

    /**
     * Gets the attribute value for ValidityFrom, using the alias name ValidityFrom.
     * @return the value of ValidityFrom
     */
    public Date getValidityFrom() {
        return (Date) getAttributeInternal(VALIDITYFROM);
    }

    /**
     * Sets <code>value</code> as the attribute value for ValidityFrom.
     * @param value value to set the ValidityFrom
     */
    public void setValidityFrom(Date value) {
        setAttributeInternal(VALIDITYFROM, value);
    }

    /**
     * Gets the attribute value for ValidityTill, using the alias name ValidityTill.
     * @return the value of ValidityTill
     */
    public Date getValidityTill() {
        return (Date) getAttributeInternal(VALIDITYTILL);
    }

    /**
     * Sets <code>value</code> as the attribute value for ValidityTill.
     * @param value value to set the ValidityTill
     */
    public void setValidityTill(Date value) {
        setAttributeInternal(VALIDITYTILL, value);
    }

    /**
     * Gets the attribute value for CreatedBy, using the alias name CreatedBy.
     * @return the value of CreatedBy
     */
    public String getCreatedBy() {
        return (String) getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreatedBy.
     * @param value value to set the CreatedBy
     */
    public void setCreatedBy(String value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for UpdatedDate, using the alias name UpdatedDate.
     * @return the value of UpdatedDate
     */
    public Date getUpdatedDate() {
        return (Date) getAttributeInternal(UPDATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for UpdatedDate.
     * @param value value to set the UpdatedDate
     */
    public void setUpdatedDate(Date value) {
        setAttributeInternal(UPDATEDDATE, value);
    }

    /**
     * Gets the attribute value for UpdatedBy, using the alias name UpdatedBy.
     * @return the value of UpdatedBy
     */
    public String getUpdatedBy() {
        return (String) getAttributeInternal(UPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for UpdatedBy.
     * @param value value to set the UpdatedBy
     */
    public void setUpdatedBy(String value) {
        setAttributeInternal(UPDATEDBY, value);
    }

    /**
     * Gets the attribute value for CreatedDate, using the alias name CreatedDate.
     * @return the value of CreatedDate
     */
    public Date getCreatedDate() {
        return (Date) getAttributeInternal(CREATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreatedDate.
     * @param value value to set the CreatedDate
     */
    public void setCreatedDate(Date value) {
        setAttributeInternal(CREATEDDATE, value);
    }

    /**
     * Gets the attribute value for ADDTEXPENSECAT, using the alias name ADDTEXPENSECAT.
     * @return the value of ADDTEXPENSECAT
     */
    public String getADDTEXPENSECAT() {
        return (String) getAttributeInternal(ADDTEXPENSECAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for ADDTEXPENSECAT.
     * @param value value to set the ADDTEXPENSECAT
     */
    public void setADDTEXPENSECAT(String value) {
        setAttributeInternal(ADDTEXPENSECAT, value);
    }

    /**
     * Gets the attribute value for EMPGRADE, using the alias name EMPGRADE.
     * @return the value of EMPGRADE
     */
    public Integer getEMPGRADE() {
        return (Integer) getAttributeInternal(EMPGRADE);
    }

    /**
     * Sets <code>value</code> as the attribute value for EMPGRADE.
     * @param value value to set the EMPGRADE
     */
    public void setEMPGRADE(Integer value) {
        setAttributeInternal(EMPGRADE, value);
    }

    /**
     * Gets the attribute value for FROMBU, using the alias name FROMBU.
     * @return the value of FROMBU
     */
    public String getFROMBU() {
        return (String) getAttributeInternal(FROMBU);
    }

    /**
     * Sets <code>value</code> as the attribute value for FROMBU.
     * @param value value to set the FROMBU
     */
    public void setFROMBU(String value) {
        setAttributeInternal(FROMBU, value);
    }

    /**
     * Gets the attribute value for FROMDEPTID, using the alias name FROMDEPTID.
     * @return the value of FROMDEPTID
     */
    public String getFROMDEPTID() {
        return (String) getAttributeInternal(FROMDEPTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for FROMDEPTID.
     * @param value value to set the FROMDEPTID
     */
    public void setFROMDEPTID(String value) {
        setAttributeInternal(FROMDEPTID, value);
    }

    /**
     * Gets the attribute value for FROMJOBCODE, using the alias name FROMJOBCODE.
     * @return the value of FROMJOBCODE
     */
    public String getFROMJOBCODE() {
        return (String) getAttributeInternal(FROMJOBCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for FROMJOBCODE.
     * @param value value to set the FROMJOBCODE
     */
    public void setFROMJOBCODE(String value) {
        setAttributeInternal(FROMJOBCODE, value);
    }

    /**
     * Gets the attribute value for FROMOU, using the alias name FROMOU.
     * @return the value of FROMOU
     */
    public String getFROMOU() {
        return (String) getAttributeInternal(FROMOU);
    }

    /**
     * Sets <code>value</code> as the attribute value for FROMOU.
     * @param value value to set the FROMOU
     */
    public void setFROMOU(String value) {
        setAttributeInternal(FROMOU, value);
    }

    /**
     * Gets the attribute value for GLACCOUNT, using the alias name GLACCOUNT.
     * @return the value of GLACCOUNT
     */
    public String getGLACCOUNT() {
        return (String) getAttributeInternal(GLACCOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for GLACCOUNT.
     * @param value value to set the GLACCOUNT
     */
    public void setGLACCOUNT(String value) {
        setAttributeInternal(GLACCOUNT, value);
    }

    /**
     * Gets the attribute value for INPUTPROVIDER, using the alias name INPUTPROVIDER.
     * @return the value of INPUTPROVIDER
     */
    public String getINPUTPROVIDER() {
        return (String) getAttributeInternal(INPUTPROVIDER);
    }

    /**
     * Sets <code>value</code> as the attribute value for INPUTPROVIDER.
     * @param value value to set the INPUTPROVIDER
     */
    public void setINPUTPROVIDER(String value) {
        setAttributeInternal(INPUTPROVIDER, value);
    }

    /**
     * Gets the attribute value for NATUREOFEXPENSE, using the alias name NATUREOFEXPENSE.
     * @return the value of NATUREOFEXPENSE
     */
    public String getNATUREOFEXPENSE() {
        return (String) getAttributeInternal(NATUREOFEXPENSE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NATUREOFEXPENSE.
     * @param value value to set the NATUREOFEXPENSE
     */
    public void setNATUREOFEXPENSE(String value) {
        setAttributeInternal(NATUREOFEXPENSE, value);
    }

    /**
     * Gets the attribute value for STATGEOGRAPHY, using the alias name STATGEOGRAPHY.
     * @return the value of STATGEOGRAPHY
     */
    public String getSTATGEOGRAPHY() {
        return (String) getAttributeInternal(STATGEOGRAPHY);
    }

    /**
     * Sets <code>value</code> as the attribute value for STATGEOGRAPHY.
     * @param value value to set the STATGEOGRAPHY
     */
    public void setSTATGEOGRAPHY(String value) {
        setAttributeInternal(STATGEOGRAPHY, value);
    }


    /**
     * Gets the attribute value for CONCATEID, using the alias name CONCATEID.
     * @return the value of CONCATEID
     */
    public String getCONCATEID() {
        return (String) getAttributeInternal(CONCATEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CONCATEID.
     * @param value value to set the CONCATEID
     */
    public void setCONCATEID(String value) {
        setAttributeInternal(CONCATEID, value);
    }

    /**
     * Gets the attribute value for TRANSACTIONPERIOD, using the alias name TRANSACTIONPERIOD.
     * @return the value of TRANSACTIONPERIOD
     */
    public Date getTRANSACTIONPERIOD() {
        return (Date) getAttributeInternal(TRANSACTIONPERIOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for TRANSACTIONPERIOD.
     * @param value value to set the TRANSACTIONPERIOD
     */
    public void setTRANSACTIONPERIOD(Date value) {
        setAttributeInternal(TRANSACTIONPERIOD, value);
    }


    /**
     * Gets the attribute value for STATISTICALDATA, using the alias name STATISTICALDATA.
     * @return the value of STATISTICALDATA
     */
    public BigDecimal getSTATISTICALDATA() {
        return (BigDecimal) getAttributeInternal(STATISTICALDATA);
    }

    /**
     * Sets <code>value</code> as the attribute value for STATISTICALDATA.
     * @param value value to set the STATISTICALDATA
     */
    public void setSTATISTICALDATA(BigDecimal value) {
        setAttributeInternal(STATISTICALDATA, value);
    }

    /**
     * Gets the attribute value for SLOC, using the alias name SLOC.
     * @return the value of SLOC
     */
    public String getSLOC() {
        return (String) getAttributeInternal(SLOC);
    }

    /**
     * Sets <code>value</code> as the attribute value for SLOC.
     * @param value value to set the SLOC
     */
    public void setSLOC(String value) {
        setAttributeInternal(SLOC, value);
    }

    /**
     * @param statisticalDataId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Integer statisticalDataId) {
        return new Key(new Object[] { statisticalDataId });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            setStatisticalDataId(am.getDBSequence1("SEQ_SGS_STATISTICAL_DATA_TBL"));
            CommonUtils util = new CommonUtils();
            Object user = (Object) util.getSessionScopeValue("_username").toString();
            setCreatedBy(user.toString());
            setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
        } catch (Exception e) {
            LOG.severe(e);
        }
    }
    
    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        if (operation == DML_INSERT) {
//            CommonUtils util= new CommonUtils();
//            Object user= (Object)util.getSessionScopeValue("_username").toString();
//            setUpdatedBy(user.toString());
//            setUpdatedDate(new java.sql.Date(new java.util.Date().getTime()));
            String natureOfExpense="";
            String inputProvider="";
            String addExpenseQuilfier="";
            if(getNATUREOFEXPENSE() != null){
                natureOfExpense = getNATUREOFEXPENSE().toUpperCase().replaceAll("\\s", "");
            }
            if(getINPUTPROVIDER() != null){
                inputProvider = getINPUTPROVIDER().toUpperCase().replaceAll("\\s", "");
            }
            if(getADDTEXPENSECAT() != null){
                addExpenseQuilfier = getADDTEXPENSECAT().toUpperCase().replaceAll("\\s", "");
            }    
            setCONCATEID(natureOfExpense+inputProvider+addExpenseQuilfier);
        }
        
        super.doDML(operation, e);
    }

    
    
}

