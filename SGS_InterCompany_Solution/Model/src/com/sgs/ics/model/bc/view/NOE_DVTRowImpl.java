package com.sgs.ics.model.bc.view;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon May 22 17:25:51 IST 2023
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class NOE_DVTRowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        COSTIDENTIFICATIONNAME,
        NATUREOFEXPENSE,
        MEANING;
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
    public static final int COSTIDENTIFICATIONNAME = AttributesEnum.COSTIDENTIFICATIONNAME.index();
    public static final int NATUREOFEXPENSE = AttributesEnum.NATUREOFEXPENSE.index();
    public static final int MEANING = AttributesEnum.MEANING.index();

    /**
     * This is the default constructor (do not remove).
     */
    public NOE_DVTRowImpl() {
    }
}

