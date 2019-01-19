package net.mercadobitcoin.tradeapi.to;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Getter
@ToString
public class Coin implements Serializable {

	private static final long serialVersionUID = 6850038424494851016L;

	private BigDecimal available;
	private BigDecimal total;
	private Integer amountOpenOrders;

	/**
	 * Constructor based on JSON response.
	 *
	 * @param jsonObject Trade API JSON response
	 */
	public Coin(JsonObject jsonObject) {
		this.available = new BigDecimal(jsonObject.get("available").asString()).setScale(6, RoundingMode.FLOOR);
		this.total = new BigDecimal(jsonObject.get("total").asString()).setScale(6, RoundingMode.FLOOR);

		this.amountOpenOrders = Optional.ofNullable(jsonObject.get("amount_open_orders"))
				.map(JsonValue::asInt)
				.orElse(null);
	}

}
