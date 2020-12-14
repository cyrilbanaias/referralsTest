package com.niji.utils;

import cucumber.api.java.en.When;

import javax.xml.soap.*;

public class SOAPClientSAAJ {

    @When("test du WS via SOAP")
    public static void testSOAP() throws Exception{
        String soapEndpointUrl = "http://integ-iop.niji.fr:7001/services/evciV1";

        callSoapWebService(soapEndpointUrl);
    }

    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "evc";
        String myNamespaceURI = "https://api-iop.gireve.com/schemas/EVCIDataDownloadV1/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

            /*
            Constructed SOAP Request Message:
            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:myNamespace="http://www.webserviceX.NET">
                <SOAP-ENV:Header/>
                <SOAP-ENV:Body>
                    <myNamespace:GetInfoByCity>
                        <myNamespace:USCity>New York</myNamespace:USCity>
                    </myNamespace:GetInfoByCity>
                </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("eMIP_ToIOP_SetEVSEAvailabilityStatusRequest", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("transactionId", myNamespace);
        soapBodyElem1.addTextNode("MOCHA_TRANSACTION_ID");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("partnerIdType", myNamespace);
        soapBodyElem2.addTextNode("eMI3");
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("partnerId", myNamespace);
        soapBodyElem3.addTextNode("FR*JBU");
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("operatorIdType", myNamespace);
        soapBodyElem4.addTextNode("eMI3");
        SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("operatorId", myNamespace);
        soapBodyElem5.addTextNode("FR*JBO");
        SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("EVSEIdType", myNamespace);
        soapBodyElem6.addTextNode("eMI3");
        SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("EVSEId", myNamespace);
        soapBodyElem7.addTextNode("FR*JBO*2927*2323");
        SOAPElement soapBodyElem8 = soapBodyElem.addChildElement("statusEventDate", myNamespace);
        soapBodyElem8.addTextNode("2015-06-07T13:14:27.600Z");
        SOAPElement soapBodyElem9 = soapBodyElem.addChildElement("availabilityStatus", myNamespace);
        soapBodyElem9.addTextNode("0");
        SOAPElement soapBodyElem10 = soapBodyElem.addChildElement("availabilityStatusUntil", myNamespace);
        soapBodyElem10.addTextNode("2015-06-07T13:14:27.600Z");
        SOAPElement soapBodyElem11 = soapBodyElem.addChildElement("availabilityStatusComment", myNamespace);
        soapBodyElem11.addTextNode("this is a comment");
    }

    private static void callSoapWebService(String soapEndpointUrl) throws Exception {
        //try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), soapEndpointUrl);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            soapConnection.close();
        /*} catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }*/
    }

    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("X-Forwarded-For", "192.168.2.2");
        headers.addHeader("X-Client-DN", "d0e3b433-8f0f-2c09-5658-6a176df25fa2");

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }

}