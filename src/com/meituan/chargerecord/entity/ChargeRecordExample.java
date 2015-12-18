package com.meituan.chargerecord.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeRecordExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	public ChargeRecordExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andRecord_idIsNull() {
			addCriterion("record_id is null");
			return (Criteria) this;
		}

		public Criteria andRecord_idIsNotNull() {
			addCriterion("record_id is not null");
			return (Criteria) this;
		}

		public Criteria andRecord_idEqualTo(Integer value) {
			addCriterion("record_id =", value, "record_id");
			return (Criteria) this;
		}

		public Criteria andRecord_idNotEqualTo(Integer value) {
			addCriterion("record_id <>", value, "record_id");
			return (Criteria) this;
		}

		public Criteria andRecord_idGreaterThan(Integer value) {
			addCriterion("record_id >", value, "record_id");
			return (Criteria) this;
		}

		public Criteria andRecord_idGreaterThanOrEqualTo(Integer value) {
			addCriterion("record_id >=", value, "record_id");
			return (Criteria) this;
		}

		public Criteria andRecord_idLessThan(Integer value) {
			addCriterion("record_id <", value, "record_id");
			return (Criteria) this;
		}

		public Criteria andRecord_idLessThanOrEqualTo(Integer value) {
			addCriterion("record_id <=", value, "record_id");
			return (Criteria) this;
		}

		public Criteria andRecord_idIn(List<Integer> values) {
			addCriterion("record_id in", values, "record_id");
			return (Criteria) this;
		}

		public Criteria andRecord_idNotIn(List<Integer> values) {
			addCriterion("record_id not in", values, "record_id");
			return (Criteria) this;
		}

		public Criteria andRecord_idBetween(Integer value1, Integer value2) {
			addCriterion("record_id between", value1, value2, "record_id");
			return (Criteria) this;
		}

		public Criteria andRecord_idNotBetween(Integer value1, Integer value2) {
			addCriterion("record_id not between", value1, value2, "record_id");
			return (Criteria) this;
		}

		public Criteria andApp_idIsNull() {
			addCriterion("app_id is null");
			return (Criteria) this;
		}

		public Criteria andApp_idIsNotNull() {
			addCriterion("app_id is not null");
			return (Criteria) this;
		}

		public Criteria andApp_idEqualTo(String value) {
			addCriterion("app_id =", value, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_idNotEqualTo(String value) {
			addCriterion("app_id <>", value, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_idGreaterThan(String value) {
			addCriterion("app_id >", value, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_idGreaterThanOrEqualTo(String value) {
			addCriterion("app_id >=", value, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_idLessThan(String value) {
			addCriterion("app_id <", value, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_idLessThanOrEqualTo(String value) {
			addCriterion("app_id <=", value, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_idLike(String value) {
			addCriterion("app_id like", value, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_idNotLike(String value) {
			addCriterion("app_id not like", value, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_idIn(List<String> values) {
			addCriterion("app_id in", values, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_idNotIn(List<String> values) {
			addCriterion("app_id not in", values, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_idBetween(String value1, String value2) {
			addCriterion("app_id between", value1, value2, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_idNotBetween(String value1, String value2) {
			addCriterion("app_id not between", value1, value2, "app_id");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeIsNull() {
			addCriterion("app_poi_code is null");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeIsNotNull() {
			addCriterion("app_poi_code is not null");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeEqualTo(String value) {
			addCriterion("app_poi_code =", value, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeNotEqualTo(String value) {
			addCriterion("app_poi_code <>", value, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeGreaterThan(String value) {
			addCriterion("app_poi_code >", value, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeGreaterThanOrEqualTo(String value) {
			addCriterion("app_poi_code >=", value, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeLessThan(String value) {
			addCriterion("app_poi_code <", value, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeLessThanOrEqualTo(String value) {
			addCriterion("app_poi_code <=", value, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeLike(String value) {
			addCriterion("app_poi_code like", value, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeNotLike(String value) {
			addCriterion("app_poi_code not like", value, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeIn(List<String> values) {
			addCriterion("app_poi_code in", values, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeNotIn(List<String> values) {
			addCriterion("app_poi_code not in", values, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeBetween(String value1, String value2) {
			addCriterion("app_poi_code between", value1, value2, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andApp_poi_codeNotBetween(String value1, String value2) {
			addCriterion("app_poi_code not between", value1, value2, "app_poi_code");
			return (Criteria) this;
		}

		public Criteria andCzsjIsNull() {
			addCriterion("czsj is null");
			return (Criteria) this;
		}

		public Criteria andCzsjIsNotNull() {
			addCriterion("czsj is not null");
			return (Criteria) this;
		}

		public Criteria andCzsjEqualTo(Date value) {
			addCriterion("czsj =", value, "czsj");
			return (Criteria) this;
		}

		public Criteria andCzsjNotEqualTo(Date value) {
			addCriterion("czsj <>", value, "czsj");
			return (Criteria) this;
		}

		public Criteria andCzsjGreaterThan(Date value) {
			addCriterion("czsj >", value, "czsj");
			return (Criteria) this;
		}

		public Criteria andCzsjGreaterThanOrEqualTo(Date value) {
			addCriterion("czsj >=", value, "czsj");
			return (Criteria) this;
		}

		public Criteria andCzsjLessThan(Date value) {
			addCriterion("czsj <", value, "czsj");
			return (Criteria) this;
		}

		public Criteria andCzsjLessThanOrEqualTo(Date value) {
			addCriterion("czsj <=", value, "czsj");
			return (Criteria) this;
		}

		public Criteria andCzsjIn(List<Date> values) {
			addCriterion("czsj in", values, "czsj");
			return (Criteria) this;
		}

		public Criteria andCzsjNotIn(List<Date> values) {
			addCriterion("czsj not in", values, "czsj");
			return (Criteria) this;
		}

		public Criteria andCzsjBetween(Date value1, Date value2) {
			addCriterion("czsj between", value1, value2, "czsj");
			return (Criteria) this;
		}

		public Criteria andCzsjNotBetween(Date value1, Date value2) {
			addCriterion("czsj not between", value1, value2, "czsj");
			return (Criteria) this;
		}

		public Criteria andCznsIsNull() {
			addCriterion("czns is null");
			return (Criteria) this;
		}

		public Criteria andCznsIsNotNull() {
			addCriterion("czns is not null");
			return (Criteria) this;
		}

		public Criteria andCznsEqualTo(Integer value) {
			addCriterion("czns =", value, "czns");
			return (Criteria) this;
		}

		public Criteria andCznsNotEqualTo(Integer value) {
			addCriterion("czns <>", value, "czns");
			return (Criteria) this;
		}

		public Criteria andCznsGreaterThan(Integer value) {
			addCriterion("czns >", value, "czns");
			return (Criteria) this;
		}

		public Criteria andCznsGreaterThanOrEqualTo(Integer value) {
			addCriterion("czns >=", value, "czns");
			return (Criteria) this;
		}

		public Criteria andCznsLessThan(Integer value) {
			addCriterion("czns <", value, "czns");
			return (Criteria) this;
		}

		public Criteria andCznsLessThanOrEqualTo(Integer value) {
			addCriterion("czns <=", value, "czns");
			return (Criteria) this;
		}

		public Criteria andCznsIn(List<Integer> values) {
			addCriterion("czns in", values, "czns");
			return (Criteria) this;
		}

		public Criteria andCznsNotIn(List<Integer> values) {
			addCriterion("czns not in", values, "czns");
			return (Criteria) this;
		}

		public Criteria andCznsBetween(Integer value1, Integer value2) {
			addCriterion("czns between", value1, value2, "czns");
			return (Criteria) this;
		}

		public Criteria andCznsNotBetween(Integer value1, Integer value2) {
			addCriterion("czns not between", value1, value2, "czns");
			return (Criteria) this;
		}

		public Criteria andCzjeIsNull() {
			addCriterion("czje is null");
			return (Criteria) this;
		}

		public Criteria andCzjeIsNotNull() {
			addCriterion("czje is not null");
			return (Criteria) this;
		}

		public Criteria andCzjeEqualTo(Float value) {
			addCriterion("czje =", value, "czje");
			return (Criteria) this;
		}

		public Criteria andCzjeNotEqualTo(Float value) {
			addCriterion("czje <>", value, "czje");
			return (Criteria) this;
		}

		public Criteria andCzjeGreaterThan(Float value) {
			addCriterion("czje >", value, "czje");
			return (Criteria) this;
		}

		public Criteria andCzjeGreaterThanOrEqualTo(Float value) {
			addCriterion("czje >=", value, "czje");
			return (Criteria) this;
		}

		public Criteria andCzjeLessThan(Float value) {
			addCriterion("czje <", value, "czje");
			return (Criteria) this;
		}

		public Criteria andCzjeLessThanOrEqualTo(Float value) {
			addCriterion("czje <=", value, "czje");
			return (Criteria) this;
		}

		public Criteria andCzjeIn(List<Float> values) {
			addCriterion("czje in", values, "czje");
			return (Criteria) this;
		}

		public Criteria andCzjeNotIn(List<Float> values) {
			addCriterion("czje not in", values, "czje");
			return (Criteria) this;
		}

		public Criteria andCzjeBetween(Float value1, Float value2) {
			addCriterion("czje between", value1, value2, "czje");
			return (Criteria) this;
		}

		public Criteria andCzjeNotBetween(Float value1, Float value2) {
			addCriterion("czje not between", value1, value2, "czje");
			return (Criteria) this;
		}

		public Criteria andCzfsIsNull() {
			addCriterion("czfs is null");
			return (Criteria) this;
		}

		public Criteria andCzfsIsNotNull() {
			addCriterion("czfs is not null");
			return (Criteria) this;
		}

		public Criteria andCzfsEqualTo(String value) {
			addCriterion("czfs =", value, "czfs");
			return (Criteria) this;
		}

		public Criteria andCzfsNotEqualTo(String value) {
			addCriterion("czfs <>", value, "czfs");
			return (Criteria) this;
		}

		public Criteria andCzfsGreaterThan(String value) {
			addCriterion("czfs >", value, "czfs");
			return (Criteria) this;
		}

		public Criteria andCzfsGreaterThanOrEqualTo(String value) {
			addCriterion("czfs >=", value, "czfs");
			return (Criteria) this;
		}

		public Criteria andCzfsLessThan(String value) {
			addCriterion("czfs <", value, "czfs");
			return (Criteria) this;
		}

		public Criteria andCzfsLessThanOrEqualTo(String value) {
			addCriterion("czfs <=", value, "czfs");
			return (Criteria) this;
		}

		public Criteria andCzfsLike(String value) {
			addCriterion("czfs like", value, "czfs");
			return (Criteria) this;
		}

		public Criteria andCzfsNotLike(String value) {
			addCriterion("czfs not like", value, "czfs");
			return (Criteria) this;
		}

		public Criteria andCzfsIn(List<String> values) {
			addCriterion("czfs in", values, "czfs");
			return (Criteria) this;
		}

		public Criteria andCzfsNotIn(List<String> values) {
			addCriterion("czfs not in", values, "czfs");
			return (Criteria) this;
		}

		public Criteria andCzfsBetween(String value1, String value2) {
			addCriterion("czfs between", value1, value2, "czfs");
			return (Criteria) this;
		}

		public Criteria andCzfsNotBetween(String value1, String value2) {
			addCriterion("czfs not between", value1, value2, "czfs");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table charge_record
	 * @mbggenerated  Wed Dec 16 21:44:58 CST 2015
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table charge_record
     *
     * @mbggenerated do_not_delete_during_merge Wed Dec 16 21:28:12 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}