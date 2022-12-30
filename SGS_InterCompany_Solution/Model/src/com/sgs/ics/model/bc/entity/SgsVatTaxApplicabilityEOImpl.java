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
// ---    Mon Dec 26 11:20:35 IST 2022
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SgsVatTaxApplicabilityEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        VatSeq,
        VatTaxApplSeq,
        CostGroupingCode,
        ChargeabilityToTax,
        ItmServCat,
        RateOfTax,
        ExemptSuppy,
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
        UPDATEDBY,
        UPDATEDDATE,
        SgsVatTblEO;
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
    private static final ADFLogger LOG = ADFLogger.createADFLogger(SgsVatTaxApplicabilityEOImpl.class);


    public static final int VATSEQ = AttributesEnum.VatSeq.index();
    public static final int VATTAXAPPLSEQ = AttributesEnum.VatTaxApplSeq.index();
    public static final int COSTGROUPINGCODE = AttributesEnum.CostGroupingCode.index();
    public static final int CHARGEABILITYTOTAX = AttributesEnum.ChargeabilityToTax.index();
    public static final int ITMSERVCAT = AttributesEnum.ItmServCat.index();
    public static final int RATEOFTAX = AttributesEnum.RateOfTax.index();
    public static final int EXEMPTSUPPY = AttributesEnum.ExemptSuppy.index();
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
    public static final int UPDATEDBY = AttributesEnum.UPDATEDBY.index();
    public static final int UPDATEDDATE = AttributesEnum.UPDATEDDATE.index();
    public static final int SGSVATTBLEO = AttributesEnum.SgsVatTblEO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SgsVatTaxApplicabilityEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.sgs.ics.model.bc.entity.SgsVatTaxApplicabilityEO");
    }

    /**
     * Gets the attribute value for VatSeq, using the alias name VatSeq.
     * @return the value of VatSeq
     */
    public Integer getVatSeq() {
        return (Integer) getAttributeInternal(VATSEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for VatSeq.
     * @param value value to set the VatSeq
     */
    public void setVatSeq(Integer value) {
        setAttributeInternal(VATSEQ, value);
    }

    /**
     * Gets the attribute value for VatTaxApplSeq, using the alias name VatTaxApplSeq.
     * @return the value of VatTaxApplSeq
     */
    public Integer getVatTaxApplSeq() {
        return (Integer) getAttributeInternal(VATTAXAPPLSEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for VatTaxApplSeq.
     * @param value value to set the VatTaxApplSeq
     */
    public void setVatTaxApplSeq(Integer value) {
        setAttributeInternal(VATTAXAPPLSEQ, value);
    }

    /**
     * Gets the attribute value for CostGroupingCode, using the alias name CostGroupingCode.
     * @return the value of CostGroupingCode
     */
    public String getCostGroupingCode() {
        return (String) getAttributeInternal(COSTGROUPINGCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CostGroupingCode.
     * @param value value to set the CostGroupingCode
     */
    public void setCostGroupingCode(String value) {
        setAttributeInternal(COSTGROUPINGCODE, value);
    }

    /**
     * Gets the attribute value for ChargeabilityToTax, using the alias name ChargeabilityToTax.
     * @return the value of ChargeabilityToTax
     */
    public String getChargeabilityToTax() {
        return (String) getAttributeInternal(CHARGEABILITYTOTAX);
    }

    /**
     * Sets <code>value</code> as the attribute value for ChargeabilityToTax.
     * @param value value to set the ChargeabilityToTax
     */
    public void setChargeabilityToTax(String value) {
        setAttributeInternal(CHARGEABILITYTOTAX, value);
    }

    /**
     * Gets the attribute value for ItmServCat, using the alias name ItmServCat.
     * @return the value of ItmServCat
     */
    public String getItmServCat() {
        return (String) getAttributeInternal(ITMSERVCAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for ItmServCat.
     * @param value value to set the ItmServCat
     */
    public void setItmServCat(String value) {
        setAttributeInternal(ITMSERVCAT, value);
    }

    /**
     * Gets the attribute value for RateOfTax, using the alias name RateOfTax.
     * @return the value of RateOfTax
     */
    public String getRateOfTax() {
        return (String) getAttributeInternal(RATEOFTAX);
    }

    /**
     * Sets <code>value</code> as the attribute value for RateOfTax.
     * @param value value to set the RateOfTax
     */
    public void setRateOfTax(String value) {
        setAttributeInternal(RATEOFTAX, value);
    }

    /**
     * Gets the attribute value for ExemptSuppy, using the alias name ExemptSuppy.
     * @return the value of ExemptSuppy
     */
    public String getExemptSuppy() {
        return (String) getAttributeInternal(EXEMPTSUPPY);
    }

    /**
     * Sets <code>value</code> as the attribute value for ExemptSuppy.
     * @param value value to set the ExemptSuppy
     */
    public void setExemptSuppy(String value) {
        setAttributeInternal(EXEMPTSUPPY, value);
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
     * Gets the attribute value for UPDATEDBY, using the alias name UPDATEDBY.
     * @return the value of UPDATEDBY
     */
    public String getUPDATEDBY() {
        return (String) getAttributeInternal(UPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for UPDATEDBY.
     * @param value value to set the UPDATEDBY
     */
    public void setUPDATEDBY(String value) {
        setAttributeInternal(UPDATEDBY, value);
    }

    /**
     * Gets the attribute value for UPDATEDDATE, using the alias name UPDATEDDATE.
     * @return the value of UPDATEDDATE
     */
    public Date getUPDATEDDATE() {
        return (Date) getAttributeInternal(UPDATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for UPDATEDDATE.
     * @param value value to set the UPDATEDDATE
     */
    public void setUPDATEDDATE(Date value) {
        setAttributeInternal(UPDATEDDATE, value);
    }

    /**
     * @return the associated entity SgsVatTblEOImpl.
     */
    public SgsVatTblEOImpl getSgsVatTblEO() {
        return (SgsVatTblEOImpl) getAttributeInternal(SGSVATTBLEO);
    }

    /**
     * Sets <code>value</code> as the associated entity SgsVatTblEOImpl.
     */
    public void setSgsVatTblEO(SgsVatTblEOImpl value) {
        setAttributeInternal(SGSVATTBLEO, value);
    }


    /**
     * @param vatTaxApplSeq key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Integer vatTaxApplSeq) {
        return new Key(new Object[] { vatTaxApplSeq });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            setVatTaxApplSeq(am.getDBSequence1("SEQ_SGS_VAT_TAX_APPLICABILITY"));
        } catch (Exception e) {
            LOG.severe(e);
        }
    }
}

