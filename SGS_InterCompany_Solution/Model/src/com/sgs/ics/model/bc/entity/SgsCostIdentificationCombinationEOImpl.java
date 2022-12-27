package com.sgs.ics.model.bc.entity;

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
// ---    Mon Dec 05 16:37:43 IST 2022
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SgsCostIdentificationCombinationEOImpl extends EntityImpl {
    private static final ADFLogger LOG = ADFLogger.createADFLogger(SgsCostIdentificationCombinationEOImpl.class);
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        CostIdentificationId,
        IcLineNo,
        TcLineNo,
        OcLineNo,
        FromBusinessUnit,
        FromOperatingUnit,
        FromJobCode,
        FromDeptId,
        FromGlAccount,
        EffectiveFrom,
        EffectiveTo,
        CreatedDate,
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
        SgsCostIdentificationRuleEO;
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
    public static final int COSTIDENTIFICATIONID = AttributesEnum.CostIdentificationId.index();
    public static final int ICLINENO = AttributesEnum.IcLineNo.index();
    public static final int TCLINENO = AttributesEnum.TcLineNo.index();
    public static final int OCLINENO = AttributesEnum.OcLineNo.index();
    public static final int FROMBUSINESSUNIT = AttributesEnum.FromBusinessUnit.index();
    public static final int FROMOPERATINGUNIT = AttributesEnum.FromOperatingUnit.index();
    public static final int FROMJOBCODE = AttributesEnum.FromJobCode.index();
    public static final int FROMDEPTID = AttributesEnum.FromDeptId.index();
    public static final int FROMGLACCOUNT = AttributesEnum.FromGlAccount.index();
    public static final int EFFECTIVEFROM = AttributesEnum.EffectiveFrom.index();
    public static final int EFFECTIVETO = AttributesEnum.EffectiveTo.index();
    public static final int CREATEDDATE = AttributesEnum.CreatedDate.index();
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
    public static final int SGSCOSTIDENTIFICATIONRULEEO = AttributesEnum.SgsCostIdentificationRuleEO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SgsCostIdentificationCombinationEOImpl() {
    }

    /**
     * Gets the attribute value for CostIdentificationId, using the alias name CostIdentificationId.
     * @return the value of CostIdentificationId
     */
    public String getCostIdentificationId() {
        return (String) getAttributeInternal(COSTIDENTIFICATIONID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CostIdentificationId.
     * @param value value to set the CostIdentificationId
     */
    public void setCostIdentificationId(String value) {
        setAttributeInternal(COSTIDENTIFICATIONID, value);
    }

    /**
     * Gets the attribute value for IcLineNo, using the alias name IcLineNo.
     * @return the value of IcLineNo
     */
    public String getIcLineNo() {
        return (String) getAttributeInternal(ICLINENO);
    }

    /**
     * Sets <code>value</code> as the attribute value for IcLineNo.
     * @param value value to set the IcLineNo
     */
    public void setIcLineNo(String value) {
        setAttributeInternal(ICLINENO, value);
    }

    /**
     * Gets the attribute value for TcLineNo, using the alias name TcLineNo.
     * @return the value of TcLineNo
     */
    public String getTcLineNo() {
        return (String) getAttributeInternal(TCLINENO);
    }

    /**
     * Sets <code>value</code> as the attribute value for TcLineNo.
     * @param value value to set the TcLineNo
     */
    public void setTcLineNo(String value) {
        setAttributeInternal(TCLINENO, value);
    }

    /**
     * Gets the attribute value for OcLineNo, using the alias name OcLineNo.
     * @return the value of OcLineNo
     */
    public String getOcLineNo() {
        return (String) getAttributeInternal(OCLINENO);
    }

    /**
     * Sets <code>value</code> as the attribute value for OcLineNo.
     * @param value value to set the OcLineNo
     */
    public void setOcLineNo(String value) {
        setAttributeInternal(OCLINENO, value);
    }

    /**
     * Gets the attribute value for FromBusinessUnit, using the alias name FromBusinessUnit.
     * @return the value of FromBusinessUnit
     */
    public String getFromBusinessUnit() {
        return (String) getAttributeInternal(FROMBUSINESSUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for FromBusinessUnit.
     * @param value value to set the FromBusinessUnit
     */
    public void setFromBusinessUnit(String value) {
        setAttributeInternal(FROMBUSINESSUNIT, value);
    }

    /**
     * Gets the attribute value for FromOperatingUnit, using the alias name FromOperatingUnit.
     * @return the value of FromOperatingUnit
     */
    public String getFromOperatingUnit() {
        return (String) getAttributeInternal(FROMOPERATINGUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for FromOperatingUnit.
     * @param value value to set the FromOperatingUnit
     */
    public void setFromOperatingUnit(String value) {
        setAttributeInternal(FROMOPERATINGUNIT, value);
    }

    /**
     * Gets the attribute value for FromJobCode, using the alias name FromJobCode.
     * @return the value of FromJobCode
     */
    public String getFromJobCode() {
        return (String) getAttributeInternal(FROMJOBCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for FromJobCode.
     * @param value value to set the FromJobCode
     */
    public void setFromJobCode(String value) {
        setAttributeInternal(FROMJOBCODE, value);
    }

    /**
     * Gets the attribute value for FromDeptId, using the alias name FromDeptId.
     * @return the value of FromDeptId
     */
    public String getFromDeptId() {
        return (String) getAttributeInternal(FROMDEPTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for FromDeptId.
     * @param value value to set the FromDeptId
     */
    public void setFromDeptId(String value) {
        setAttributeInternal(FROMDEPTID, value);
    }

    /**
     * Gets the attribute value for FromGlAccount, using the alias name FromGlAccount.
     * @return the value of FromGlAccount
     */
    public String getFromGlAccount() {
        return (String) getAttributeInternal(FROMGLACCOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for FromGlAccount.
     * @param value value to set the FromGlAccount
     */
    public void setFromGlAccount(String value) {
        setAttributeInternal(FROMGLACCOUNT, value);
    }

    /**
     * Gets the attribute value for EffectiveFrom, using the alias name EffectiveFrom.
     * @return the value of EffectiveFrom
     */
    public Date getEffectiveFrom() {
        return (Date) getAttributeInternal(EFFECTIVEFROM);
    }

    /**
     * Sets <code>value</code> as the attribute value for EffectiveFrom.
     * @param value value to set the EffectiveFrom
     */
    public void setEffectiveFrom(Date value) {
        setAttributeInternal(EFFECTIVEFROM, value);
    }

    /**
     * Gets the attribute value for EffectiveTo, using the alias name EffectiveTo.
     * @return the value of EffectiveTo
     */
    public Date getEffectiveTo() {
        return (Date) getAttributeInternal(EFFECTIVETO);
    }

    /**
     * Sets <code>value</code> as the attribute value for EffectiveTo.
     * @param value value to set the EffectiveTo
     */
    public void setEffectiveTo(Date value) {
        setAttributeInternal(EFFECTIVETO, value);
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
     * @return the associated entity SgsCostIdentificationRuleEOImpl.
     */
    public SgsCostIdentificationRuleEOImpl getSgsCostIdentificationRuleEO() {
        return (SgsCostIdentificationRuleEOImpl) getAttributeInternal(SGSCOSTIDENTIFICATIONRULEEO);
    }

    /**
     * Sets <code>value</code> as the associated entity SgsCostIdentificationRuleEOImpl.
     */
    public void setSgsCostIdentificationRuleEO(SgsCostIdentificationRuleEOImpl value) {
        setAttributeInternal(SGSCOSTIDENTIFICATIONRULEEO, value);
    }

    /**
     * @param icLineNo key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(String icLineNo) {
        return new Key(new Object[] { icLineNo });
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.sgs.ics.model.bc.entity.SgsCostIdentificationCombinationEO");
    }

//    /**
//     * Add attribute defaulting logic in this method.
//     * @param attributeList list of attribute names/values to initialize the row
//     */
//    protected void create(AttributeList attributeList) {
//        super.create(attributeList);
//    }
    
    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            //setCostIdentificationId("SGSID"+Math.random());
            setIcLineNo("IC"+am.getDBSequence("SEQ_SGS_COST_TARGET_COMBINATION"));
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
        super.doDML(operation, e);
    }
}

