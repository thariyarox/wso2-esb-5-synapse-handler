package com.wso2.custom.synapse.handler;

import org.apache.synapse.AbstractSynapseHandler;
import org.apache.synapse.MessageContext;
import org.apache.synapse.config.SynapseConfiguration;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.base.SequenceMediator;

import java.util.Map;

public class ResponseHeaderHandler extends AbstractSynapseHandler {

    private final static String RESPONSE_HEADER_SEQ = "RESPONSE_HEADER_SEQ";

    public boolean handleRequestInFlow(MessageContext messageContext) {
        return true;
    }

    public boolean handleRequestOutFlow(MessageContext messageContext) {
        return true;
    }

    public boolean handleResponseInFlow(MessageContext messageContext) {
        return true;
    }

    public boolean handleResponseOutFlow(MessageContext synCtx) {

        /*
        // If the response headers should be set from code, use this code block
        Axis2MessageContext messageContext = (Axis2MessageContext) synCtx;
        org.apache.axis2.context.MessageContext axis2MessageCtx =
                messageContext.getAxis2MessageContext();
        Object headers = axis2MessageCtx.getProperty(
                org.apache.axis2.context.MessageContext.TRANSPORT_HEADERS);

        if (headers != null && headers instanceof Map) {
            Map headersMap = (Map) headers;
            headersMap.put("X-Frame-Options", "DENY");

        }
        */
        
        // Execute a sequence named RESPONSE_HEADER_SEQ in the response outflow

        SynapseConfiguration synapseConfiguration = synCtx.getConfiguration();
        SequenceMediator sequence = (SequenceMediator) synapseConfiguration.getSequence(RESPONSE_HEADER_SEQ);
        if (sequence != null) {
            sequence.mediate(synCtx);
        }

        return true;

    }
}
