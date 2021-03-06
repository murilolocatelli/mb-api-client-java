/**
 * under the MIT License (MIT)
 * Copyright (c) 2015 Mercado Bitcoin Servicos Digitais Ltda.
 * @see more details in /LICENSE.txt
 */

package net.mercadobitcoin.tradeapi.to;

import com.eclipsesource.json.JsonObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Operation of an order. Contains:
 *
 * <b>operationId</b>: Operation's ID.
 * <b>volume</b>: Volume dealt with by the operation.
 * <b>price</b>: Operation's unit price, in Brazilian Real.
 * <b>rate</b>: Tax's percentage applied to the operation.
 * <b>created</b>: Operation's Unix time.
 */
public class Operation implements Serializable {

	private static final long serialVersionUID = -3345636873296069825L;

	private Long operationId;
	private BigDecimal volume;
	private BigDecimal price;
	private BigDecimal rate;
	private Integer created;
	private String type;

	/**
	 * Constructor based on JSON response.
	 * 
	 * @param jsonObject Trade API JSON response
	 */
	public Operation(JsonObject jsonObject) {
		this.created = Integer.valueOf(jsonObject.get("date").toString());
		this.price = new BigDecimal(jsonObject.get("price").toString()).setScale(6, RoundingMode.FLOOR);
		this.volume = new BigDecimal(jsonObject.get("amount").toString()).setScale(6, RoundingMode.FLOOR);
		this.operationId = jsonObject.get("tid").asLong();
		this.type = jsonObject.get("type").asString();

		this.rate = null;
	}

	/**
	 * Constructor based on JSON response.
	 * 
	 * @param operationId Operation Identifier
	 * @param jsonObject Trade API JSON response
	 */
	public Operation(Long operationId, JsonObject jsonObject) {
		this.operationId = operationId;
		this.volume = new BigDecimal(jsonObject.get("volume").asString()).setScale(6, RoundingMode.FLOOR);
		this.price = new BigDecimal(jsonObject.get("price").asString()).setScale(6, RoundingMode.FLOOR);
		this.rate = new BigDecimal(jsonObject.get("rate").asString()).setScale(6, RoundingMode.FLOOR);
		this.created = Integer.valueOf(jsonObject.get("created").asString());

		this.type = null;
	}

	public Integer getDate() {
		return created;
	}

	public BigDecimal getAmount() {
		return volume;
	}

	public Long getTid() {
		return operationId;
	}

	public String getType() {
		return type;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public Integer getCreated() {
		return created;
	}

	public Long getOperationId() {
		return operationId;
	}

	@Override
	public String toString() {
		if (this.type != null) {
			return "\nOperation [date=" + created + ", price=" + price
					+ ", amount=" + volume + ", tid=" + operationId + ", type="
					+ type + "]";
		} else {
			return "Operation [operationId=" + operationId + ", volume="
					+ volume + ", price=" + price + ", rate=" + rate
					+ ", created=" + created + "]";
		}
	}

}
