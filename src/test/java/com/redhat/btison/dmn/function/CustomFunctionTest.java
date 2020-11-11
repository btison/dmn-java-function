package com.redhat.btison.dmn.function;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;

public class CustomFunctionTest {

    @Test
    public void testCustomFunction() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        DMNRuntime dmnRuntime = kieSession.getKieRuntime(DMNRuntime.class);

        String dmnModelNamespace = "https://kiegroup.org/dmn/_50029B85-DA2F-4C7A-8B75-184717DC00BF";
        String dmnModelName = "CustomFunction1";

        DMNModel dmnModel = dmnRuntime.getModel(dmnModelNamespace, dmnModelName);

        assertThat(dmnModel, notNullValue());
        assertThat(dmnModel.hasErrors(), is(false));

        DMNContext dmnContext = dmnRuntime.newContext();
        dmnContext.set("input", Integer.valueOf(12));
        dmnContext.set("divisor", Integer.valueOf(7));

        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);
        DMNDecisionResult decisionResult = dmnResult.getDecisionResultByName("Decision-1");
        assertThat(decisionResult.getEvaluationStatus(), equalTo(DMNDecisionResult.DecisionEvaluationStatus.SUCCEEDED));
        assertThat(decisionResult.getResult(), instanceOf(Boolean.class));
        assertThat((Boolean)decisionResult.getResult(), is(false));

    }




    
}
