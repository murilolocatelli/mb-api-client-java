package net.mercadobitcoin.tradeapi.to;

import com.eclipsesource.json.JsonObject;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class WithdrawalLimits implements Serializable {

    private static final long serialVersionUID = -6917707045530120778L;

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
	public WithdrawalLimits(JsonObject jsonObject) {
        this.brl = new Coin(jsonObject.get("brl").asObject());
        this.btc = new Coin(jsonObject.get("btc").asObject());
        this.ltc = new Coin(jsonObject.get("ltc").asObject());
        this.bch = new Coin(jsonObject.get("bch").asObject());
        this.xrp = new Coin(jsonObject.get("xrp").asObject());
	}

}
