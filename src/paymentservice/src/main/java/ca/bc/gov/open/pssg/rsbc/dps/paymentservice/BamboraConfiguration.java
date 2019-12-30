package ca.bc.gov.open.pssg.rsbc.dps.paymentservice;

import ca.bc.gov.open.pssg.rsbc.dps.paymentservice.types.BeanstreamEndpointResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BamboraConfiguration {

  @Value("${dps.crc.endpoint.approved}")
  private String approved;
  @Value("${dps.crc.endpoint.declined}")
  private String declined;
  @Value("${dps.crc.endpoint.error}")
  private String error;

  @RequestMapping(value ="/bamboraconfiguration",method = RequestMethod.GET)
  public BeanstreamEndpointResponse singlepaymenturl() {

    BeanstreamEndpointResponse xmlresp = new BeanstreamEndpointResponse(approved,declined,error,PaymentServiceConstants.PAYMENT_SERVICE_RESP_MSG_OK, PaymentServiceConstants.PAYMENT_SERVICE_SUCCESS_CD) ;
    return xmlresp;
            //"Hello from the Bambora configuration controller";
  }
}
