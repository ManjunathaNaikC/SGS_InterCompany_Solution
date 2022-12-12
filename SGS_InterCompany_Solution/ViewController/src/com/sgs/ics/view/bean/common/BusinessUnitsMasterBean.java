package com.sgs.ics.view.bean.common;



import oracle.adf.model.BindingContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class BusinessUnitsMasterBean {
    public BusinessUnitsMasterBean() {
    }

    public String deleteBUMaster() {
        BindingContainer bindings = getBindings();
               OperationBinding operationBinding = bindings.getOperationBinding("Delete");
               Object result = operationBinding.execute();
               OperationBinding deleteOb = bindings.getOperationBinding("Commit");
               deleteOb.execute();
               if (!operationBinding.getErrors().isEmpty()) {
                   return null;
               }
               return null;
               }
               
           public BindingContainer getBindings() {
               return BindingContext.getCurrent().getCurrentBindingsEntry();
           }
    
}
