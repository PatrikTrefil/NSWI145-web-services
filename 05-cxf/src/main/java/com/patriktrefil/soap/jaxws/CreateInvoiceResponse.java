
package com.patriktrefil.soap.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.6.3
 * Mon Mar 18 15:32:32 CET 2024
 * Generated source version: 3.6.3
 */

@XmlRootElement(name = "createInvoiceResponse", namespace = "http://soap.patriktrefil.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createInvoiceResponse", namespace = "http://soap.patriktrefil.com/")

public class CreateInvoiceResponse {

    @XmlElement(name = "return")
    private boolean _return;

    public boolean getReturn() {
        return this._return;
    }

    public void setReturn(boolean new_return)  {
        this._return = new_return;
    }

}
