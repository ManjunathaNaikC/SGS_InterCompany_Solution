package com.sgs.ics.model.bc.entity;

import com.sgs.ics.model.bc.commonutils.CommonUtils;
import com.sgs.ics.model.bc.am.SGSAppModuleImpl;

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
// ---    Tue Dec 13 11:51:00 IST 2022
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SgsStatisticalDataEOImpl extends EntityImpl {
    private static final ADFLogger LOG = ADFLogger.createADFLogger(SgsStatisticalDataEOImpl.class);

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
        Attribute1,
        Attribute2,
        Attribute3,
        Attribute4,
        Attribute5,
        Attribute6,
        Attribute7,
        Attribute8,
        Attribute9,
        Attribute10,
        Attribute11,
        Attribute12,
        Attribute13,
        Attribute14,
        Attribute15,
        CREATEDDATE,
        GLACCOUNT,
        InputProvider,
        NATUREOFEXPENSE,
        FROMBU,
        FROMDEPTID,
        FROMJOBCODE,
        FROMOU,
        ADDTEXPENSECAT,
        CONCATEID,
        EMPGRADE,
        STATGEOGRAPHY,
        APPROVESTATUS,
        TRANSACTIONPERIOD,
        EMAILATTACHMENT,
        STATISTICALDATA,
        DOCATTM,
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
    public static final int ATTRIBUTE1 = AttributesEnum.Attribute1.index();
    public static final int ATTRIBUTE2 = AttributesEnum.Attribute2.index();
    public static final int ATTRIBUTE3 = AttributesEnum.Attribute3.index();
    public static final int ATTRIBUTE4 = AttributesEnum.Attribute4.index();
    public static final int ATTRIBUTE5 = AttributesEnum.Attribute5.index();
    public static final int ATTRIBUTE6 = AttributesEnum.Attribute6.index();
    public static final int ATTRIBUTE7 = AttributesEnum.Attribute7.index();
    public static final int ATTRIBUTE8 = AttributesEnum.Attribute8.index();
    public static final int ATTRIBUTE9 = AttributesEnum.Attribute9.index();
    public static final int ATTRIBUTE10 = AttributesEnum.Attribute10.index();
    public static final int ATTRIBUTE11 = AttributesEnum.Attribute11.index();
    public static final int ATTRIBUTE12 = AttributesEnum.Attribute12.index();
    public static final int ATTRIBUTE13 = AttributesEnum.Attribute13.index();
    public static final int ATTRIBUTE14 = AttributesEnum.Attribute14.index();
    public static final int ATTRIBUTE15 = AttributesEnum.Attribute15.index();
    public static final int CREATEDDATE = AttributesEnum.CREATEDDATE.index();
    public static final int GLACCOUNT = AttributesEnum.GLACCOUNT.index();
    public static final int INPUTPROVIDER = AttributesEnum.InputProvider.index();
    public static final int NATUREOFEXPENSE = AttributesEnum.NATUREOFEXPENSE.index();
    public static final int FROMBU = AttributesEnum.FROMBU.index();
    public static final int FROMDEPTID = AttributesEnum.FROMDEPTID.index();
    public static final int FROMJOBCODE = AttributesEnum.FROMJOBCODE.index();
    public static final int FROMOU = AttributesEnum.FROMOU.index();
    public static final int ADDTEXPENSECAT = AttributesEnum.ADDTEXPENSECAT.index();
    public static final int CONCATEID = AttributesEnum.CONCATEID.index();
    public static final int EMPGRADE = AttributesEnum.EMPGRADE.index();
    public static final int STATGEOGRAPHY = AttributesEnum.STATGEOGRAPHY.index();
    public static final int APPROVESTATUS = AttributesEnum.APPROVESTATUS.index();
    public static final int TRANSACTIONPERIOD = AttributesEnum.TRANSACTIONPERIOD.index();
    public static final int EMAILATTACHMENT = AttributesEnum.EMAILATTACHMENT.index();
    public static final int STATISTICALDATA = AttributesEnum.STATISTICALDATA.index();
    public static final int DOCATTM = AttributesEnum.DOCATTM.index();
    public static final int SLOC = AttributesEnum.SLOC.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SgsStatisticalDataEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.sgs.ics.model.bc.entity.SgsStatisticalDataEO");
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

    public void setCREATEDDATE(Date value) {
        setAttributeInternal(CREATEDDATE, value);
    }

    public void setUpdatedDate(Date value) {
        setAttributeInternal(UPDATEDDATE, value);
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
     * Gets the attribute value for Attribute1, using the alias name Attribute1.
     * @return the value of Attribute1
     */
    public String getAttribute1() {
        return (String) getAttributeInternal(ATTRIBUTE1);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute1.
     * @param value value to set the Attribute1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**
     * Gets the attribute value for Attribute2, using the alias name Attribute2.
     * @return the value of Attribute2
     */
    public String getAttribute2() {
        return (String) getAttributeInternal(ATTRIBUTE2);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute2.
     * @param value value to set the Attribute2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**
     * Gets the attribute value for Attribute3, using the alias name Attribute3.
     * @return the value of Attribute3
     */
    public String getAttribute3() {
        return (String) getAttributeInternal(ATTRIBUTE3);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute3.
     * @param value value to set the Attribute3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**
     * Gets the attribute value for Attribute4, using the alias name Attribute4.
     * @return the value of Attribute4
     */
    public String getAttribute4() {
        return (String) getAttributeInternal(ATTRIBUTE4);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute4.
     * @param value value to set the Attribute4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**
     * Gets the attribute value for Attribute5, using the alias name Attribute5.
     * @return the value of Attribute5
     */
    public String getAttribute5() {
        return (String) getAttributeInternal(ATTRIBUTE5);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute5.
     * @param value value to set the Attribute5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**
     * Gets the attribute value for Attribute6, using the alias name Attribute6.
     * @return the value of Attribute6
     */
    public Date getAttribute6() {
        return (Date) getAttributeInternal(ATTRIBUTE6);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute6.
     * @param value value to set the Attribute6
     */
    public void setAttribute6(Date value) {
        setAttributeInternal(ATTRIBUTE6, value);
    }

    /**
     * Gets the attribute value for Attribute7, using the alias name Attribute7.
     * @return the value of Attribute7
     */
    public Date getAttribute7() {
        return (Date) getAttributeInternal(ATTRIBUTE7);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute7.
     * @param value value to set the Attribute7
     */
    public void setAttribute7(Date value) {
        setAttributeInternal(ATTRIBUTE7, value);
    }

    /**
     * Gets the attribute value for Attribute8, using the alias name Attribute8.
     * @return the value of Attribute8
     */
    public Date getAttribute8() {
        return (Date) getAttributeInternal(ATTRIBUTE8);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute8.
     * @param value value to set the Attribute8
     */
    public void setAttribute8(Date value) {
        setAttributeInternal(ATTRIBUTE8, value);
    }

    /**
     * Gets the attribute value for Attribute9, using the alias name Attribute9.
     * @return the value of Attribute9
     */
    public Date getAttribute9() {
        return (Date) getAttributeInternal(ATTRIBUTE9);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute9.
     * @param value value to set the Attribute9
     */
    public void setAttribute9(Date value) {
        setAttributeInternal(ATTRIBUTE9, value);
    }

    /**
     * Gets the attribute value for Attribute10, using the alias name Attribute10.
     * @return the value of Attribute10
     */
    public Date getAttribute10() {
        return (Date) getAttributeInternal(ATTRIBUTE10);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute10.
     * @param value value to set the Attribute10
     */
    public void setAttribute10(Date value) {
        setAttributeInternal(ATTRIBUTE10, value);
    }

    /**
     * Gets the attribute value for Attribute11, using the alias name Attribute11.
     * @return the value of Attribute11
     */
    public BigDecimal getAttribute11() {
        return (BigDecimal) getAttributeInternal(ATTRIBUTE11);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute11.
     * @param value value to set the Attribute11
     */
    public void setAttribute11(BigDecimal value) {
        setAttributeInternal(ATTRIBUTE11, value);
    }

    /**
     * Gets the attribute value for Attribute12, using the alias name Attribute12.
     * @return the value of Attribute12
     */
    public BigDecimal getAttribute12() {
        return (BigDecimal) getAttributeInternal(ATTRIBUTE12);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute12.
     * @param value value to set the Attribute12
     */
    public void setAttribute12(BigDecimal value) {
        setAttributeInternal(ATTRIBUTE12, value);
    }

    /**
     * Gets the attribute value for Attribute13, using the alias name Attribute13.
     * @return the value of Attribute13
     */
    public BigDecimal getAttribute13() {
        return (BigDecimal) getAttributeInternal(ATTRIBUTE13);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute13.
     * @param value value to set the Attribute13
     */
    public void setAttribute13(BigDecimal value) {
        setAttributeInternal(ATTRIBUTE13, value);
    }

    /**
     * Gets the attribute value for Attribute14, using the alias name Attribute14.
     * @return the value of Attribute14
     */
    public BigDecimal getAttribute14() {
        return (BigDecimal) getAttributeInternal(ATTRIBUTE14);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute14.
     * @param value value to set the Attribute14
     */
    public void setAttribute14(BigDecimal value) {
        setAttributeInternal(ATTRIBUTE14, value);
    }

    /**
     * Gets the attribute value for Attribute15, using the alias name Attribute15.
     * @return the value of Attribute15
     */
    public BigDecimal getAttribute15() {
        return (BigDecimal) getAttributeInternal(ATTRIBUTE15);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute15.
     * @param value value to set the Attribute15
     */
    public void setAttribute15(BigDecimal value) {
        setAttributeInternal(ATTRIBUTE15, value);
    }

    /**
     * Gets the attribute value for CREATEDDATE, using the alias name CREATEDDATE.
     * @return the value of CREATEDDATE
     */
    public Date getCREATEDDATE() {
        return (Date) getAttributeInternal(CREATEDDATE);
    }

    //    public void setCREATEDDATE(Date value) {
    //        setAttributeInternal(CREATEDDATE, value);
    //    }


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
     * Gets the attribute value for InputProvider, using the alias name InputProvider.
     * @return the value of InputProvider
     */
    public String getInputProvider() {
        return (String) getAttributeInternal(INPUTPROVIDER);
    }

    /**
     * Sets <code>value</code> as the attribute value for InputProvider.
     * @param value value to set the InputProvider
     */
    public void setInputProvider(String value) {
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
     * Gets the attribute value for APPROVESTATUS, using the alias name APPROVESTATUS.
     * @return the value of APPROVESTATUS
     */
    public String getAPPROVESTATUS() {
        return (String) getAttributeInternal(APPROVESTATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for APPROVESTATUS.
     * @param value value to set the APPROVESTATUS
     */
    public void setAPPROVESTATUS(String value) {
        setAttributeInternal(APPROVESTATUS, value);
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
     * Gets the attribute value for EMAILATTACHMENT, using the alias name EMAILATTACHMENT.
     * @return the value of EMAILATTACHMENT
     */
    public String getEMAILATTACHMENT() {
        return (String) getAttributeInternal(EMAILATTACHMENT);
    }

    /**
     * Sets <code>value</code> as the attribute value for EMAILATTACHMENT.
     * @param value value to set the EMAILATTACHMENT
     */
    public void setEMAILATTACHMENT(String value) {
        setAttributeInternal(EMAILATTACHMENT, value);
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
     * Gets the attribute value for DOCATTM, using the alias name DOCATTM.
     * @return the value of DOCATTM
     */
    public String getDOCATTM() {
        return (String) getAttributeInternal(DOCATTM);
    }

    /**
     * Sets <code>value</code> as the attribute value for DOCATTM.
     * @param value value to set the DOCATTM
     */
    public void setDOCATTM(String value) {
        setAttributeInternal(DOCATTM, value);
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

    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            setStatisticalDataId(am.getDBSequence1("SEQ_SGS_STATISTICAL_DATA_TBL"));
            CommonUtils util = new CommonUtils();
            Object user = (Object) util.getSessionScopeValue("_username").toString();
            setCreatedBy(user.toString());
            setCREATEDDATE(new java.sql.Date(new java.util.Date().getTime()));
            String natureOfExpense = "";
            String inputProvider = "";
            String addExpenseQuilfier = "";
            if (getNATUREOFEXPENSE() != null) {
                natureOfExpense = getNATUREOFEXPENSE().toUpperCase().replaceAll("\\s", "");
            }
            if (getInputProvider() != null) {
                inputProvider = getInputProvider().toUpperCase().replaceAll("\\s", "");
            }
            if (getADDTEXPENSECAT() != null) {
                addExpenseQuilfier = getADDTEXPENSECAT().toUpperCase().replaceAll("\\s", "");
            }
            setCONCATEID(natureOfExpense + inputProvider + addExpenseQuilfier);
        } catch (Exception e) {
            LOG.severe(e);
        }
    }

    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        String natureOfExpense = "";
        String inputProvider = "";
        String addExpenseQuilfier = "";
        if (operation == DML_UPDATE) {
            CommonUtils util = new CommonUtils();
            Object user = (Object) util.getSessionScopeValue("_username").toString();
            setUpdatedBy(user.toString());
            setUpdatedDate(new java.sql.Date(new java.util.Date().getTime()));

            if (getNATUREOFEXPENSE() != null) {
                natureOfExpense = getNATUREOFEXPENSE().toUpperCase().replaceAll("\\s", "");
            }
            if (getInputProvider() != null) {
                inputProvider = getInputProvider().toUpperCase().replaceAll("\\s", "");
            }
            if (getADDTEXPENSECAT() != null) {
                addExpenseQuilfier = getADDTEXPENSECAT().toUpperCase().replaceAll("\\s", "");
            } else {
                addExpenseQuilfier = natureOfExpense;
            }
            setCONCATEID(natureOfExpense + inputProvider + addExpenseQuilfier);
        } else if (operation == DML_INSERT) {

            if (getNATUREOFEXPENSE() != null) {
                natureOfExpense = getNATUREOFEXPENSE().toUpperCase().replaceAll("\\s", "");
            }
            if (getInputProvider() != null) {
                inputProvider = getInputProvider().toUpperCase().replaceAll("\\s", "");
            }
            if (getADDTEXPENSECAT() != null) {
                addExpenseQuilfier = getADDTEXPENSECAT().toUpperCase().replaceAll("\\s", "");
            } else {
                addExpenseQuilfier = natureOfExpense;
            }
            setCONCATEID(natureOfExpense + inputProvider + addExpenseQuilfier);
        }

        super.doDML(operation, e);
    }

}

