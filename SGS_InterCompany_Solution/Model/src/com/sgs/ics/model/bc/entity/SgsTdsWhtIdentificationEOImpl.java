package com.sgs.ics.model.bc.entity;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;

import java.math.BigDecimal;

import java.sql.Date;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Dec 26 10:12:45 IST 2022
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SgsTdsWhtIdentificationEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        TdsWhtIdenSeq,
        LegalEntity,
        DocumentType,
        TaxIdentificationNum,
        ValidityPeriodFrom,
        ValidityPeriodTo,
        Attachment,
        TdsWhtSeq,
        LastUpdatedBy,
        LastUpdatedDate,
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
        CREATEDBY,
        CREATEDDATE,
        SgsTdsWhtTblEO;
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
    private static final ADFLogger LOG = ADFLogger.createADFLogger(SgsTdsWhtIdentificationEOImpl.class);


    public static final int TDSWHTIDENSEQ = AttributesEnum.TdsWhtIdenSeq.index();
    public static final int LEGALENTITY = AttributesEnum.LegalEntity.index();
    public static final int DOCUMENTTYPE = AttributesEnum.DocumentType.index();
    public static final int TAXIDENTIFICATIONNUM = AttributesEnum.TaxIdentificationNum.index();
    public static final int VALIDITYPERIODFROM = AttributesEnum.ValidityPeriodFrom.index();
    public static final int VALIDITYPERIODTO = AttributesEnum.ValidityPeriodTo.index();
    public static final int ATTACHMENT = AttributesEnum.Attachment.index();
    public static final int TDSWHTSEQ = AttributesEnum.TdsWhtSeq.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDDATE = AttributesEnum.LastUpdatedDate.index();
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
    public static final int CREATEDBY = AttributesEnum.CREATEDBY.index();
    public static final int CREATEDDATE = AttributesEnum.CREATEDDATE.index();
    public static final int SGSTDSWHTTBLEO = AttributesEnum.SgsTdsWhtTblEO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SgsTdsWhtIdentificationEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.sgs.ics.model.bc.entity.SgsTdsWhtIdentificationEO");
    }

    /**
     * Gets the attribute value for TdsWhtIdenSeq, using the alias name TdsWhtIdenSeq.
     * @return the value of TdsWhtIdenSeq
     */
    public Integer getTdsWhtIdenSeq() {
        return (Integer) getAttributeInternal(TDSWHTIDENSEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for TdsWhtIdenSeq.
     * @param value value to set the TdsWhtIdenSeq
     */
    public void setTdsWhtIdenSeq(Integer value) {
        setAttributeInternal(TDSWHTIDENSEQ, value);
    }

    /**
     * Gets the attribute value for LegalEntity, using the alias name LegalEntity.
     * @return the value of LegalEntity
     */
    public String getLegalEntity() {
        return (String) getAttributeInternal(LEGALENTITY);
    }

    /**
     * Sets <code>value</code> as the attribute value for LegalEntity.
     * @param value value to set the LegalEntity
     */
    public void setLegalEntity(String value) {
        setAttributeInternal(LEGALENTITY, value);
    }

    /**
     * Gets the attribute value for DocumentType, using the alias name DocumentType.
     * @return the value of DocumentType
     */
    public String getDocumentType() {
        return (String) getAttributeInternal(DOCUMENTTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for DocumentType.
     * @param value value to set the DocumentType
     */
    public void setDocumentType(String value) {
        setAttributeInternal(DOCUMENTTYPE, value);
    }

    /**
     * Gets the attribute value for TaxIdentificationNum, using the alias name TaxIdentificationNum.
     * @return the value of TaxIdentificationNum
     */
    public String getTaxIdentificationNum() {
        return (String) getAttributeInternal(TAXIDENTIFICATIONNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for TaxIdentificationNum.
     * @param value value to set the TaxIdentificationNum
     */
    public void setTaxIdentificationNum(String value) {
        setAttributeInternal(TAXIDENTIFICATIONNUM, value);
    }

    /**
     * Gets the attribute value for ValidityPeriodFrom, using the alias name ValidityPeriodFrom.
     * @return the value of ValidityPeriodFrom
     */
    public Date getValidityPeriodFrom() {
        return (Date) getAttributeInternal(VALIDITYPERIODFROM);
    }

    /**
     * Sets <code>value</code> as the attribute value for ValidityPeriodFrom.
     * @param value value to set the ValidityPeriodFrom
     */
    public void setValidityPeriodFrom(Date value) {
        setAttributeInternal(VALIDITYPERIODFROM, value);
    }

    /**
     * Gets the attribute value for ValidityPeriodTo, using the alias name ValidityPeriodTo.
     * @return the value of ValidityPeriodTo
     */
    public Date getValidityPeriodTo() {
        return (Date) getAttributeInternal(VALIDITYPERIODTO);
    }

    /**
     * Sets <code>value</code> as the attribute value for ValidityPeriodTo.
     * @param value value to set the ValidityPeriodTo
     */
    public void setValidityPeriodTo(Date value) {
        setAttributeInternal(VALIDITYPERIODTO, value);
    }

    /**
     * Gets the attribute value for Attachment, using the alias name Attachment.
     * @return the value of Attachment
     */
    public String getAttachment() {
        return (String) getAttributeInternal(ATTACHMENT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attachment.
     * @param value value to set the Attachment
     */
    public void setAttachment(String value) {
        setAttributeInternal(ATTACHMENT, value);
    }

    /**
     * Gets the attribute value for TdsWhtSeq, using the alias name TdsWhtSeq.
     * @return the value of TdsWhtSeq
     */
    public Integer getTdsWhtSeq() {
        return (Integer) getAttributeInternal(TDSWHTSEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for TdsWhtSeq.
     * @param value value to set the TdsWhtSeq
     */
    public void setTdsWhtSeq(Integer value) {
        setAttributeInternal(TDSWHTSEQ, value);
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
     * Gets the attribute value for LastUpdatedDate, using the alias name LastUpdatedDate.
     * @return the value of LastUpdatedDate
     */
    public Date getLastUpdatedDate() {
        return (Date) getAttributeInternal(LASTUPDATEDDATE);
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
     * Gets the attribute value for CREATEDBY, using the alias name CREATEDBY.
     * @return the value of CREATEDBY
     */
    public String getCREATEDBY() {
        return (String) getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for CREATEDBY.
     * @param value value to set the CREATEDBY
     */
    public void setCREATEDBY(String value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for CREATEDDATE, using the alias name CREATEDDATE.
     * @return the value of CREATEDDATE
     */
    public Date getCREATEDDATE() {
        return (Date) getAttributeInternal(CREATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CREATEDDATE.
     * @param value value to set the CREATEDDATE
     */
    public void setCREATEDDATE(Date value) {
        setAttributeInternal(CREATEDDATE, value);
    }

    /**
     * @return the associated entity SgsTdsWhtTblEOImpl.
     */
    public SgsTdsWhtTblEOImpl getSgsTdsWhtTblEO() {
        return (SgsTdsWhtTblEOImpl) getAttributeInternal(SGSTDSWHTTBLEO);
    }

    /**
     * Sets <code>value</code> as the associated entity SgsTdsWhtTblEOImpl.
     */
    public void setSgsTdsWhtTblEO(SgsTdsWhtTblEOImpl value) {
        setAttributeInternal(SGSTDSWHTTBLEO, value);
    }


    /**
     * @param tdsWhtIdenSeq key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Integer tdsWhtIdenSeq) {
        return new Key(new Object[] { tdsWhtIdenSeq });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
           setTdsWhtIdenSeq(am.getDBSequence1("SEQ_SGS_TDS_WHT_IDENTIFICATION"));
        } catch (Exception e) {
            LOG.severe(e);
        }
    }
}
