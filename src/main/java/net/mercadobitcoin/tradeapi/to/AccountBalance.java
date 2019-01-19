package net.mercadobitcoin.tradeapi.to;

import com.eclipsesource.json.JsonObject;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class AccountBalance implements Serializable {

	private static final long serialVersionUID = 6922034267613306463L;

	private Balance balance;
	private WithdrawalLimits withdrawLimits;

	/**
	 * Constructor based on JSON response.
	 * 
	 * @param jsonObject Trade API JSON response
	 */
	public AccountBalance(JsonObject jsonObject) {
		this.balance = new Balance(jsonObject.get("balance").asObject());
		this.withdrawLimits = new WithdrawalLimits(jsonObject.get("withdrawal_limits").asObject());
	}

}
