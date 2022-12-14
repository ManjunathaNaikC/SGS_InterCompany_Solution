package com.sgs.ics.view;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

public class ValueChangeListener {
    public ValueChangeListener() {
    }

    public void onChangeOfNatureOfExpense(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
       
        System.out.println("lookupCode :: "+valueChangeEvent.getNewValue());
        String newLokkupCode =(String)valueChangeEvent.getNewValue();
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("NatureOfExpenseLookupVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        String lookupValue = (String) row.getAttribute("LOOKUPCODE");
        System.out.println("lookupCode :: "+lookupValue);
        ViewObjectImpl costIdentificationVO =
            (ViewObjectImpl) getDCIteratorBindings("SgsCostIdentificationRuleVOIterator").getViewObject();
//        costIdentificationVO.getViewCriteria("NatureOfExpenseVC");
//        costIdentificationVO.setNamedWhereClause("bNatureOfExpense", lookupValue);
        costIdentificationVO.setWhereClause("NATURE_OF_EXPENSE = '"+newLokkupCode+"'");
        costIdentificationVO.executeQuery();

    }

    private DCIteratorBinding getDCIteratorBindings(String iterName) {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings.findIteratorBinding(iterName);

    }
}
