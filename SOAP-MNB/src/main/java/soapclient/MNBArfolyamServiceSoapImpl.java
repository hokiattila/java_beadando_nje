
package soapclient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MNBArfolyamServiceSoapImpl", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://www.mnb.hu/arfolyamok.asmx?wsdl")
public class MNBArfolyamServiceSoapImpl
    extends Service
{

    private final static URL MNBARFOLYAMSERVICESOAPIMPL_WSDL_LOCATION;
    private final static WebServiceException MNBARFOLYAMSERVICESOAPIMPL_EXCEPTION;
    private final static QName MNBARFOLYAMSERVICESOAPIMPL_QNAME = new QName("http://tempuri.org/", "MNBArfolyamServiceSoapImpl");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://www.mnb.hu/arfolyamok.asmx?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MNBARFOLYAMSERVICESOAPIMPL_WSDL_LOCATION = url;
        MNBARFOLYAMSERVICESOAPIMPL_EXCEPTION = e;
    }

    public MNBArfolyamServiceSoapImpl() {
        super(__getWsdlLocation(), MNBARFOLYAMSERVICESOAPIMPL_QNAME);
    }

    public MNBArfolyamServiceSoapImpl(WebServiceFeature... features) {
        super(__getWsdlLocation(), MNBARFOLYAMSERVICESOAPIMPL_QNAME, features);
    }

    public MNBArfolyamServiceSoapImpl(URL wsdlLocation) {
        super(wsdlLocation, MNBARFOLYAMSERVICESOAPIMPL_QNAME);
    }

    public MNBArfolyamServiceSoapImpl(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MNBARFOLYAMSERVICESOAPIMPL_QNAME, features);
    }

    public MNBArfolyamServiceSoapImpl(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MNBArfolyamServiceSoapImpl(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MNBArfolyamServiceSoap
     */
    @WebEndpoint(name = "CustomBinding_MNBArfolyamServiceSoap")
    public MNBArfolyamServiceSoap getCustomBindingMNBArfolyamServiceSoap() {
        return super.getPort(new QName("http://tempuri.org/", "CustomBinding_MNBArfolyamServiceSoap"), MNBArfolyamServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MNBArfolyamServiceSoap
     */
    @WebEndpoint(name = "CustomBinding_MNBArfolyamServiceSoap")
    public MNBArfolyamServiceSoap getCustomBindingMNBArfolyamServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "CustomBinding_MNBArfolyamServiceSoap"), MNBArfolyamServiceSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MNBARFOLYAMSERVICESOAPIMPL_EXCEPTION!= null) {
            throw MNBARFOLYAMSERVICESOAPIMPL_EXCEPTION;
        }
        return MNBARFOLYAMSERVICESOAPIMPL_WSDL_LOCATION;
    }

}
