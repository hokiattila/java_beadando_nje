
package soapclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetExchangeRatesResponseBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetExchangeRatesResponseBody"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GetExchangeRatesResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetExchangeRatesResponseBody", propOrder = {
    "getExchangeRatesResult"
})
public class GetExchangeRatesResponseBody {

    @XmlElementRef(name = "GetExchangeRatesResult", namespace = "http://www.mnb.hu/webservices/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> getExchangeRatesResult;

    /**
     * Gets the value of the getExchangeRatesResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGetExchangeRatesResult() {
        return getExchangeRatesResult;
    }

    /**
     * Sets the value of the getExchangeRatesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGetExchangeRatesResult(JAXBElement<String> value) {
        this.getExchangeRatesResult = value;
    }

}
