package com.upwork.rest.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -8160633134172316807L;

	private Double result;

	public Result() {

	}

	public Result(Double result) {
		this.result =  new BigDecimal(result).setScale(3,
				BigDecimal.ROUND_HALF_UP).doubleValue();;
	}

	public void setResult(Double result) {
		this.result = new BigDecimal(result).setScale(3,
				BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public Double getResult() {
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (this.result == null ? 0 : this.result.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Result other = (Result) obj;
		if (result == null) {
			if (other.result != null) {
				return false;
			}
		} else if (!result.equals(other.result)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Result [result=" + result + "]";
	}

}
