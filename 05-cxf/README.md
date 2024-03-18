Example SOAP message with valid auth:

```xml
<soapenv:Envelope 
	xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://soap.patriktrefil.com/"
	xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
    <soapenv:Header>
    <wsse:Security>
      <wsse:UsernameToken>
        <wsse:Username>Alice</wsse:Username>
        <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">ecilA</wsse:Password>
      </wsse:UsernameToken>
     </wsse:Security>
  </soapenv:Header>
   <soapenv:Body>
      <soap:createInvoice>
         <!--Optional:-->
         <arg0>A</arg0>
         <!--Optional:-->
         <arg1>B</arg1>
         <arg2>1.0</arg2>
      </soap:createInvoice>
   </soapenv:Body>
</soapenv:Envelope>
```