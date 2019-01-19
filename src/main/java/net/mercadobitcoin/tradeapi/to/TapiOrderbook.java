package net.mercadobitcoin.tradeapi.to;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TapiOrderbook {
	private Order[] asks;
	private Order[] bids;

	/**
	 * Constructor based on JSON response.
	 *
	 * @param jsonObject Trade API JSON response
	 */
	public TapiOrderbook(JsonObject jsonObject) {
		JsonArray asking = jsonObject.get("asks").asArray();
		asks = new Order[asking.size()];
		for (int i = 0; i < asking.size(); i++) {
			asks[i] = new Order(asking.get(i).asObject());
		}
		
		JsonArray bidding = jsonObject.get("bids").asArray();
		bids = new Order[bidding.size()];
		for (int i = 0; i < bidding.size(); i++) {
			bids[i] = new Order(bidding.get(i).asObject());
		}
	}

}
