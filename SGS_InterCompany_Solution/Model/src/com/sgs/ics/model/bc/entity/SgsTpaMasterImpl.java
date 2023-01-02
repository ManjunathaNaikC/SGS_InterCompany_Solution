package com.sgs.ics.model.bc.entity;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;

import com.sgs.ics.model.bc.commonutils.CommonUtils;

import java.math.BigDecimal;

import java.sql.Date;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Nov 29 22:03:36 IST 2022
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SgsTpaMasterImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        TpaSeqId,
        TpaId,
        LeServiceProvider,
        LeServiceReceiver,
        PassThroughFlag,
        PassThroughBu,
        OperationUnit,
        NettingApplicatibility,
        BdCommissionApplicability,
        BdCommissionRate,
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
        PASSTHROUGHDEPTID,
        PASSTHROUGHJOBCODE,
        SgsTpaDocType1,
        SgsMarkupRateTblEO;
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
    private static final ADFLogger LOG = ADFLogger.createADFLogger(SgsTpaMasterImpl.class);


    public static final int TPASEQID = AttributesEnum.TpaSeqId.index();
    public static final int TPAID = AttributesEnum.TpaId.index();
    public static final int LESERVICEPROVIDER = AttributesEnum.LeServiceProvider.index();
    public static final int LESERVICERECEIVER = AttributesEnum.LeServiceReceiver.index();
    public static final int PASSTHROUGHFLAG = AttributesEnum.PassThroughFlag.index();
    public static final int PASSTHROUGHBU = AttributesEnum.PassThroughBu.index();
    public static final int OPERATIONUNIT = AttributesEnum.OperationUnit.index();
    public static final int NETTINGAPPLICATIBILITY = AttributesEnum.NettingApplicatibility.index();
    public static final int BDCOMMISSIONAPPLICABILITY = AttributesEnum.BdCommissionApplicability.index();
    public static final int BDCOMMISSIONRATE = AttributesEnum.BdCommissionRate.index();
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
    public static final int PASSTHROUGHDEPTID = AttributesEnum.PASSTHROUGHDEPTID.index();
    public static final int PASSTHROUGHJOBCODE = AttributesEnum.PASSTHROUGHJOBCODE.index();
    public static final int SGSTPADOCTYPE1 = AttributesEnum.SgsTpaDocType1.index();
    public static final int SGSMARKUPRATETBLEO = AttributesEnum.SgsMarkupRateTblEO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SgsTpaMasterImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.sgs.ics.model.bc.entity.SgsTpaMaster");
    }


    /**
     * Gets the attribute value for TpaSeqId, using the alias name TpaSeqId.
     * @return the value of TpaSeqId
     */
    public Integer getTpaSeqId() {
        return (Integer) getAttributeInternal(TPASEQID);
    }

    /**
     * Sets <code>value</code> as the attribute value for TpaSeqId.
     * @param value value to set the TpaSeqId
     */
    public void setTpaSeqId(Integer value) {
        setAttributeInternal(TPASEQID, value);
    }

    /**
     * Gets the attribute value for TpaId, using the alias name TpaId.
     * @return the value of TpaId
     */
    public String getTpaId() {
        return (String) getAttributeInternal(TPAID);
    }

    /**
     * Sets <code>value</code> as the attribute value for TpaId.
     * @param value value to set the TpaId
     */
    public void setTpaId(String value) {
        setAttributeInternal(TPAID, value);
    }

    /**
     * Gets the attribute value for LeServiceProvider, using the alias name LeServiceProvider.
     * @return the value of LeServiceProvider
     */
    public String getLeServiceProvider() {
        return (String) getAttributeInternal(LESERVICEPROVIDER);
    }

    /**
     * Sets <code>value</code> as the attribute value for LeServiceProvider.
     * @param value value to set the LeServiceProvider
     */
    public void setLeServiceProvider(String value) {
        setAttributeInternal(LESERVICEPROVIDER, value);
    }

    /**
     * Gets the attribute value for LeServiceReceiver, using the alias name LeServiceReceiver.
     * @return the value of LeServiceReceiver
     */
    public String getLeServiceReceiver() {
        return (String) getAttributeInternal(LESERVICERECEIVER);
    }

    /**
     * Sets <code>value</code> as the attribute value for LeServiceReceiver.
     * @param value value to set the LeServiceReceiver
     */
    public void setLeServiceReceiver(String value) {
        setAttributeInternal(LESERVICERECEIVER, value);
    }

    /**
     * Gets the attribute value for PassThroughFlag, using the alias name PassThroughFlag.
     * @return the value of PassThroughFlag
     */
    public String getPassThroughFlag() {
        return (String) getAttributeInternal(PASSTHROUGHFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for PassThroughFlag.
     * @param value value to set the PassThroughFlag
     */
    public void setPassThroughFlag(String value) {
        setAttributeInternal(PASSTHROUGHFLAG, value);
    }

    /**
     * Gets the attribute value for PassThroughBu, using the alias name PassThroughBu.
     * @return the value of PassThroughBu
     */
    public String getPassThroughBu() {
        return (String) getAttributeInternal(PASSTHROUGHBU);
    }

    /**
     * Sets <code>value</code> as the attribute value for PassThroughBu.
     * @param value value to set the PassThroughBu
     */
    public void setPassThroughBu(String value) {
        setAttributeInternal(PASSTHROUGHBU, value);
    }

    /**
     * Gets the attribute value for OperationUnit, using the alias name OperationUnit.
     * @return the value of OperationUnit
     */
    public String getOperationUnit() {
        return (String) getAttributeInternal(OPERATIONUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for OperationUnit.
     * @param value value to set the OperationUnit
     */
    public void setOperationUnit(String value) {
        setAttributeInternal(OPERATIONUNIT, value);
    }

    /**
     * Gets the attribute value for NettingApplicatibility, using the alias name NettingApplicatibility.
     * @return the value of NettingApplicatibility
     */
    public String getNettingApplicatibility() {
        return (String) getAttributeInternal(NETTINGAPPLICATIBILITY);
    }

    /**
     * Sets <code>value</code> as the attribute value for NettingApplicatibility.
     * @param value value to set the NettingApplicatibility
     */
    public void setNettingApplicatibility(String value) {
        setAttributeInternal(NETTINGAPPLICATIBILITY, value);
    }

    /**
     * Gets the attribute value for BdCommissionApplicability, using the alias name BdCommissionApplicability.
     * @return the value of BdCommissionApplicability
     */
    public String getBdCommissionApplicability() {
        return (String) getAttributeInternal(BDCOMMISSIONAPPLICABILITY);
    }

    /**
     * Sets <code>value</code> as the attribute value for BdCommissionApplicability.
     * @param value value to set the BdCommissionApplicability
     */
    public void setBdCommissionApplicability(String value) {
        setAttributeInternal(BDCOMMISSIONAPPLICABILITY, value);
    }

    /**
     * Gets the attribute value for BdCommissionRate, using the alias name BdCommissionRate.
     * @return the value of BdCommissionRate
     */
    public String getBdCommissionRate() {
        return (String) getAttributeInternal(BDCOMMISSIONRATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for BdCommissionRate.
     * @param value value to set the BdCommissionRate
     */
    public void setBdCommissionRate(String value) {
        setAttributeInternal(BDCOMMISSIONRATE, value);
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
     * Gets the attribute value for CreatedBy, using the alias name CreatedBy.
     * @return the value of CreatedBy
     */
    public String getCreatedBy() {
        return (String) getAttributeInternal(CREATEDBY);
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
     * Gets the attribute value for PASSTHROUGHDEPTID, using the alias name PASSTHROUGHDEPTID.
     * @return the value of PASSTHROUGHDEPTID
     */
    public String getPASSTHROUGHDEPTID() {
        return (String) getAttributeInternal(PASSTHROUGHDEPTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for PASSTHROUGHDEPTID.
     * @param value value to set the PASSTHROUGHDEPTID
     */
    public void setPASSTHROUGHDEPTID(String value) {
        setAttributeInternal(PASSTHROUGHDEPTID, value);
    }

    /**
     * Gets the attribute value for PASSTHROUGHJOBCODE, using the alias name PASSTHROUGHJOBCODE.
     * @return the value of PASSTHROUGHJOBCODE
     */
    public String getPASSTHROUGHJOBCODE() {
        return (String) getAttributeInternal(PASSTHROUGHJOBCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for PASSTHROUGHJOBCODE.
     * @param value value to set the PASSTHROUGHJOBCODE
     */
    public void setPASSTHROUGHJOBCODE(String value) {
        setAttributeInternal(PASSTHROUGHJOBCODE, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getSgsTpaDocType1() {
        return (RowIterator) getAttributeInternal(SGSTPADOCTYPE1);
    }


    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getSgsMarkupRateTblEO() {
        return (RowIterator) getAttributeInternal(SGSMARKUPRATETBLEO);
    }


    /**
     * @param tpaId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(String tpaId) {
        return new Key(new Object[] { tpaId });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            setTpaSeqId(am.getDBSequence1("SEQ_SGS_TPA_MASTER"));
            CommonUtils util= new CommonUtils();
             Object user= (Object)util.getSessionScopeValue("_username").toString();
             setCreatedBy(user.toString());
        } catch (Exception e) {
            LOG.severe(e);
        }
    }

    public void setCreatedBy(String value) {
        setAttributeInternal(CREATEDBY, value);
    }

    public void setUpdatedBy(String value) {
        setAttributeInternal(UPDATEDBY, value);
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        if (operation == DML_UPDATE) {
            CommonUtils util = new CommonUtils();
            Object user = (Object) util.getSessionScopeValue("_username").toString();
            setUpdatedBy(user.toString());
        }

        super.doDML(operation, e);
    }
}

