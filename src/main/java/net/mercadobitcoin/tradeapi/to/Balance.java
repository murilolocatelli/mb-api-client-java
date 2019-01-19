package net.mercadobitcoin.tradeapi.to;

import com.eclipsesource.json.JsonObject;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class Balance implements Serializable {

	private static final long serialVersionUID = 8949937495301765392L;

	private Coin brl;
	private Coin btc;
	private Coin ltc;
	private Coin bch;
	private Coin xrp;

	/**
	 * Constructor based on JSON response.
	 *
	 * @param jsonObject Trade API JSON response
	 */
	public Balance(JsonObject jsonObject) {
		this.brl = new Coin(jsonObject.get("brl").asObject());
		this.btc = new Coin(jsonObject.get("btc").asObject());
		this.ltc = new Coin(jsonObject.get("ltc").asObject());
		this.bch = new Coin(jsonObject.get("bch").asObject());
		this.xrp = new Coin(jsonObject.get("xrp").asObject());
	}

}
