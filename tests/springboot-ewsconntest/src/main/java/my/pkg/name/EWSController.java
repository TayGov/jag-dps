package my.pkg.name;

import java.net.URI;

import microsoft.exchange.webservices.data.core.service.folder.Folder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.search.LogicalOperator;
import microsoft.exchange.webservices.data.core.enumeration.search.SortDirection;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;

@RestController
public class EWSController {

	public EWSController(ExchangeService exchangeService) {
		this.exchangeService = exchangeService;
	}

	private ExchangeService exchangeService;


/**
 *
 * Outlook client. 
 * 
 * Demonstrates a filtered fetch of items from your inbox 
 * 	
 * @return
 * @throws Exception
 */
@RequestMapping("/getMessageCount")
public String getMessageCount() throws Exception {

	  Folder inbox = Folder.bind(exchangeService, WellKnownFolderName.Inbox);
	  FindItemsResults<Item> findResults = findItems(exchangeService);
	  return Integer.toString(findResults.getTotalCount()) + " Messages found in you Inbox";
	  
  }

  private FindItemsResults<Item> findItems(ExchangeService service) throws Exception {
		ItemView view = new ItemView(10);
		view.getOrderBy().add(ItemSchema.DateTimeReceived, SortDirection.Ascending);
		view.setPropertySet(new PropertySet(BasePropertySet.IdOnly, ItemSchema.Subject, ItemSchema.DateTimeReceived));

		FindItemsResults<Item> findResults =
	    	service.findItems(WellKnownFolderName.Inbox,
	        	new SearchFilter.SearchFilterCollection(
					LogicalOperator.Or, new SearchFilter.ContainsSubstring(ItemSchema.Subject, "EWS"),
				new SearchFilter.ContainsSubstring(ItemSchema.Subject, "API")), view);

	    //MOOOOOOST IMPORTANT: load items properties, before
	    service.loadPropertiesForItems(findResults, PropertySet.FirstClassProperties);
		System.out.println("Total number of items found: " + findResults.getTotalCount());
		
		return findResults; 
	}
  
}