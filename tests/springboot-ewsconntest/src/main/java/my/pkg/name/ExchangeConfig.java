package my.pkg.name;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class ExchangeConfig {

    @Bean
    public ExchangeService exchangeService() throws Exception {

        ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
        service.setUrl(new URI("https://wsgw.dev.jag.gov.bc.ca/dps/bcgov/ews/services/Exchange.asmx"));
        service.setCredentials(new WebCredentials("email", "password"));
        service.autodiscoverUrl("Alex.Joyeux@gov.bc.ca", new EWSAutodiscoverAPI.RedirectionUrlCallback());
        service.setTraceEnabled(true);
        return service;
    }

}
