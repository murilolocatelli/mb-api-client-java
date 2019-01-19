/**
 * under the MIT License (MIT)
 * Copyright (c) 2015 Mercado Bitcoin Servicos Digitais Ltda.
 * @see more details in /LICENSE.txt
 */

package net.mercadobitcoin.tradeapi.to;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.eclipsesource.json.JsonValue;
import net.mercadobitcoin.util.EnumValue;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

/**
 * Order information.
 */
public class Order extends TapiBase {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The Coin Pairs that a operation can deal with.
	 */
	public enum CoinPair implements EnumValue {
		BRLBTC("BRLBTC"),
		BRLLTC("BRLLTC"),
		BRLBCH("BRLBCH"),
		BRLXRP("BRLXRP");

		private final String value;

		private CoinPair(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * Define the Status of an Order (Active, Canceled or Completed).
	 */
	public enum OrderStatus implements EnumValue {
		ACTIVE("active"),
		CANCELED("canceled"),
		COMPLETED("completed");
		private final String value;

		private OrderStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public static final String MINIMUM_VOLUME = "0.01";
	public static final BigDecimal BITCOIN_24H_WITHDRAWAL_LIMIT = new BigDecimal(25);
	public static final int BITCOIN_DEPOSIT_CONFIRMATIONS = 6;
	
	public static final BigDecimal LITECOIN_24H_WITHDRAWAL_LIMIT = new BigDecimal(25);
	public static final int LITECOIN_DEPOSIT_CONFIRMATIONS = 15;
	
	private CoinPair coin_pair;
	private Integer type;
	private BigDecimal quantity;
	private String limit_price;
	
	private Long orderId;
	private Integer status;
	private Integer created;
	private List<Operation> operations;
	
	private Boolean flagSmall = false;

    public Order(CoinPair pair, Long orderId) {
        this.coin_pair = pair;
        this.orderId = orderId;
    }
	
	/**
	 * Constructor. Request a new Order with the specified parameters.
	 * @param pair The pair of coins of to be exchanged.
	 * @param volume The amount to be exchanged.
	 * @param price The price the exchange should be dealt.
	 */
	public Order(CoinPair pair, BigDecimal volume, String price) {
		this.limit_price = price;
		this.quantity = volume;
		this.coin_pair = pair;

		this.flagSmall = true;
	}

	/**
	 * Constructor. Response from the Trade API, sent to the User.
	 */
	public Order(JsonObject jsonObject) {
		this.orderId = jsonObject.get("order_id").asLong();

		this.coin_pair = Optional.ofNullable(jsonObject.get("coin_pair"))
				.map(t -> CoinPair.valueOf(t.asString().toUpperCase())).orElse(null);

		this.type = Optional.ofNullable(jsonObject.get("order_type"))
				.map(JsonValue::asInt).orElse(null);

		this.quantity = new BigDecimal(jsonObject.get("quantity").asString()).setScale(6, RoundingMode.FLOOR);
		this.limit_price = jsonObject.get("limit_price").asString();

		this.status = Optional.ofNullable(jsonObject.get("status"))
				.map(JsonValue::asInt).orElse(null);

		// TODO: fazer parse de isOwner

		//this.created = Integer.valueOf(jsonObject.get("created").asString());
		
		this.operations = new ArrayList<Operation>();

		// TODO: fazer parse de operations

//		for (String operationId: jsonObject.get("operations").asObject().names()) {
//			if (operationId.matches("-?\\d+(\\.\\d+)?")) {
//				operations.add(new Operation(
//								Long.valueOf(operationId),
//								jsonObject.get("operations").asObject().get(operationId).asObject() ));
//			}
//		}
	}
	
	/**
	 * Constructor. Response from the API, used by Orderbook.
	 */
	public Order(JsonArray jsonArray, CoinPair pair, Integer type) {
		this.limit_price = jsonArray.get(0).toString();
		this.quantity = new BigDecimal(jsonArray.get(1).toString()).setScale(6, RoundingMode.FLOOR);
		this.coin_pair = pair;
		this.type = type;
		
		this.flagSmall = true;
	}
	
	public CoinPair getCoin_pair() {
		return coin_pair;
	}

	public Integer getType() {
		return type;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public String getLimit_price() {
		return limit_price;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getCreated() {
		return created;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	@Override
	public String toString() {
		if (this.flagSmall == true) {
			return "\nOrder [pair=" + coin_pair + ", type=" + type + ", volume=" + quantity
					+ ", price=" + limit_price + "]";
		} else {
			return "Order [pair=" + coin_pair + ", type=" + type + ", volume=" + quantity
					+ ", price=" + limit_price + ", orderId=" + orderId + ", status="
					+ status + ", created=" + created + ", operations="
					+ operations + "]";
		}
	}
	
}