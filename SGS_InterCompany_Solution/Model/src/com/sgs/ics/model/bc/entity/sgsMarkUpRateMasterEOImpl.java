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
// ---    Thu Dec 29 15:13:36 IST 2022
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class sgsMarkUpRateMasterEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        MarkUpRateId,
        SpGeography,
        SpLegalEntity,
        SpBusinessUnit,
        SrGeography,
        SrLegalEntity,
        SrBusinessUnit,
        NatureOfExpenses,
        MarkUpRate,
        CreatedDate,
        CreatedBy,
        LastUpdatedDate,
        LastUpdatedBy,
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
        EFFECTIVEFROM,
        EFFECTIVETO,
        STATUS;
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
    private static final ADFLogger LOG = ADFLogger.createADFLogger(sgsMarkUpRateMasterEOImpl.class);


    public static final int MARKUPRATEID = AttributesEnum.MarkUpRateId.index();
    public static final int SPGEOGRAPHY = AttributesEnum.SpGeography.index();
    public static final int SPLEGALENTITY = AttributesEnum.SpLegalEntity.index();
    public static final int SPBUSINESSUNIT = AttributesEnum.SpBusinessUnit.index();
    public static final int SRGEOGRAPHY = AttributesEnum.SrGeography.index();
    public static final int SRLEGALENTITY = AttributesEnum.SrLegalEntity.index();
    public static final int SRBUSINESSUNIT = AttributesEnum.SrBusinessUnit.index();
    public static final int NATUREOFEXPENSES = AttributesEnum.NatureOfExpenses.index();
    public static final int MARKUPRATE = AttributesEnum.MarkUpRate.index();
    public static final int CREATEDDATE = AttributesEnum.CreatedDate.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int LASTUPDATEDDATE = AttributesEnum.LastUpdatedDate.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
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
    public static final int EFFECTIVEFROM = AttributesEnum.EFFECTIVEFROM.index();
    public static final int EFFECTIVETO = AttributesEnum.EFFECTIVETO.index();
    public static final int STATUS = AttributesEnum.STATUS.index();

    /**
     * This is the default constructor (do not remove).
     */
    public sgsMarkUpRateMasterEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.sgs.ics.model.bc.entity.sgsMarkUpRateMasterEO");
    }

    /**
     * Gets the attribute value for MarkUpRateId, using the alias name MarkUpRateId.
     * @return the value of MarkUpRateId
     */
    public Integer getMarkUpRateId() {
        return (Integer) getAttributeInternal(MARKUPRATEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for MarkUpRateId.
     * @param value value to set the MarkUpRateId
     */
    public void setMarkUpRateId(Integer value) {
        setAttributeInternal(MARKUPRATEID, value);
    }

    /**
     * Gets the attribute value for SpGeography, using the alias name SpGeography.
     * @return the value of SpGeography
     */
    public String getSpGeography() {
        return (String) getAttributeInternal(SPGEOGRAPHY);
    }

    /**
     * Sets <code>value</code> as the attribute value for SpGeography.
     * @param value value to set the SpGeography
     */
    public void setSpGeography(String value) {
        setAttributeInternal(SPGEOGRAPHY, value);
    }

    /**
     * Gets the attribute value for SpLegalEntity, using the alias name SpLegalEntity.
     * @return the value of SpLegalEntity
     */
    public String getSpLegalEntity() {
        return (String) getAttributeInternal(SPLEGALENTITY);
    }

    /**
     * Sets <code>value</code> as the attribute value for SpLegalEntity.
     * @param value value to set the SpLegalEntity
     */
    public void setSpLegalEntity(String value) {
        setAttributeInternal(SPLEGALENTITY, value);
    }

    /**
     * Gets the attribute value for SpBusinessUnit, using the alias name SpBusinessUnit.
     * @return the value of SpBusinessUnit
     */
    public String getSpBusinessUnit() {
        return (String) getAttributeInternal(SPBUSINESSUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for SpBusinessUnit.
     * @param value value to set the SpBusinessUnit
     */
    public void setSpBusinessUnit(String value) {
        setAttributeInternal(SPBUSINESSUNIT, value);
    }

    /**
     * Gets the attribute value for SrGeography, using the alias name SrGeography.
     * @return the value of SrGeography
     */
    public String getSrGeography() {
        return (String) getAttributeInternal(SRGEOGRAPHY);
    }

    /**
     * Sets <code>value</code> as the attribute value for SrGeography.
     * @param value value to set the SrGeography
     */
    public void setSrGeography(String value) {
        setAttributeInternal(SRGEOGRAPHY, value);
    }

    /**
     * Gets the attribute value for SrLegalEntity, using the alias name SrLegalEntity.
     * @return the value of SrLegalEntity
     */
    public String getSrLegalEntity() {
        return (String) getAttributeInternal(SRLEGALENTITY);
    }

    /**
     * Sets <code>value</code> as the attribute value for SrLegalEntity.
     * @param value value to set the SrLegalEntity
     */
    public void setSrLegalEntity(String value) {
        setAttributeInternal(SRLEGALENTITY, value);
    }

    /**
     * Gets the attribute value for SrBusinessUnit, using the alias name SrBusinessUnit.
     * @return the value of SrBusinessUnit
     */
    public String getSrBusinessUnit() {
        return (String) getAttributeInternal(SRBUSINESSUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for SrBusinessUnit.
     * @param value value to set the SrBusinessUnit
     */
    public void setSrBusinessUnit(String value) {
        setAttributeInternal(SRBUSINESSUNIT, value);
    }

    /**
     * Gets the attribute value for NatureOfExpenses, using the alias name NatureOfExpenses.
     * @return the value of NatureOfExpenses
     */
    public String getNatureOfExpenses() {
        return (String) getAttributeInternal(NATUREOFEXPENSES);
    }

    /**
     * Sets <code>value</code> as the attribute value for NatureOfExpenses.
     * @param value value to set the NatureOfExpenses
     */
    public void setNatureOfExpenses(String value) {
        setAttributeInternal(NATUREOFEXPENSES, value);
    }

    /**
     * Gets the attribute value for MarkUpRate, using the alias name MarkUpRate.
     * @return the value of MarkUpRate
     */
    public BigDecimal getMarkUpRate() {
        return (BigDecimal) getAttributeInternal(MARKUPRATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for MarkUpRate.
     * @param value value to set the MarkUpRate
     */
    public void setMarkUpRate(BigDecimal value) {
        setAttributeInternal(MARKUPRATE, value);
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
     * Gets the attribute value for LastUpdatedDate, using the alias name LastUpdatedDate.
     * @return the value of LastUpdatedDate
     */
    public Date getLastUpdatedDate() {
        return (Date) getAttributeInternal(LASTUPDATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdatedDate.
     * @param value value to set the LastUpdatedDate
     */
    public void setLastUpdatedDate(Date value) {
        setAttributeInternal(LASTUPDATEDDATE, value);
    }

    /**
     * Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy.
     * @return the value of LastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return (String) getAttributeInternal(LASTUPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdatedBy.
     * @param value value to set the LastUpdatedBy
     */
    public void setLastUpdatedBy(String value) {
        setAttributeInternal(LASTUPDATEDBY, value);
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
    public BigDecimal getAttribute10() {
        return (BigDecimal) getAttributeInternal(ATTRIBUTE10);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute10.
     * @param value value to set the Attribute10
     */
    public void setAttribute10(BigDecimal value) {
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
     * Gets the attribute value for EFFECTIVEFROM, using the alias name EFFECTIVEFROM.
     * @return the value of EFFECTIVEFROM
     */
    public Date getEFFECTIVEFROM() {
        return (Date) getAttributeInternal(EFFECTIVEFROM);
    }

    /**
     * Sets <code>value</code> as the attribute value for EFFECTIVEFROM.
     * @param value value to set the EFFECTIVEFROM
     */
    public void setEFFECTIVEFROM(Date value) {
        setAttributeInternal(EFFECTIVEFROM, value);
    }

    /**
     * Gets the attribute value for EFFECTIVETO, using the alias name EFFECTIVETO.
     * @return the value of EFFECTIVETO
     */
    public Date getEFFECTIVETO() {
        return (Date) getAttributeInternal(EFFECTIVETO);
    }

    /**
     * Sets <code>value</code> as the attribute value for EFFECTIVETO.
     * @param value value to set the EFFECTIVETO
     */
    public void setEFFECTIVETO(Date value) {
        setAttributeInternal(EFFECTIVETO, value);
    }

    /**
     * Gets the attribute value for STATUS, using the alias name STATUS.
     * @return the value of STATUS
     */
    public String getSTATUS() {
        return (String) getAttributeInternal(STATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for STATUS.
     * @param value value to set the STATUS
     */
    public void setSTATUS(String value) {
        setAttributeInternal(STATUS, value);
    }


    /**
     * @param markUpRateId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Integer markUpRateId) {
        return new Key(new Object[] { markUpRateId });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            setMarkUpRateId(am.getDBSequence1("SEQ_SGS_MARKUP_RATE_MASTER_TBL"));
            CommonUtils util= new CommonUtils();
               Object user= (Object)util.getSessionScopeValue("_username").toString();
               setCreatedBy(user.toString());
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
        if (operation == DML_UPDATE) {
            CommonUtils util= new CommonUtils();
            Object user= (Object)util.getSessionScopeValue("_username").toString();
            setLastUpdatedBy(user.toString());
        }
        
        super.doDML(operation, e);
    }
}

