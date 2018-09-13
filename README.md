# wso2-esb-5-synapse-handler

In WSO2 ESB 5.0.0, we can write a Synapse Handler and modify rquest/response content or headers and also enage sequences.
https://docs.wso2.com/display/ESB500/Writing+a+Synapse+Handler

In this sample, it executes a custom sequence in the out-flow of the responses ESB produces. 

You can find the pre-built jar file in target directory of the project. If not you can build the source from maven.

Copy the com.wso2.custom.synapse.handler-1.0.jar file into wso2esb-5.0.0/repository/components/lib

Modify the wso2esb-5.0.0/repository/conf/synapse-handlers.xml file and engage the hander as below.

<handlers>
	    <handler name="ResponseHeaderHandler" class="com.wso2.custom.synapse.handler.ResponseHeaderHandler"/>
</handlers>

Create the sample seqence wso2esb-5.0.0/repository/deployment/server/synapse-configs/default/sequences/RESPONSE_HEADER_SEQ.xml putting the following content.

<?xml version="1.0" encoding="UTF-8"?>
<sequence xmlns="http://ws.apache.org/ns/synapse" name="RESPONSE_HEADER_SEQ">
   <header name="X-Frame-Options" scope="transport" value="DENY"/>
</sequence>

Note that the sequence name RESPONSE_HEADER_SEQ is hardcoded in the source, so we need to create the sequence with the same name.

Restart the WSO2 ESB 5.0.0 and when invoking a proxy, the custom sequence should be enaged. Here, the squence is setting X-Frame-Options header to the responses.

