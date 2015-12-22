package com.meituan.users.entity;

import java.util.ArrayList;
import java.util.List;

public class LoginUsersExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	public LoginUsersExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
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

		public Criteria andUser_idIsNull() {
			addCriterion("user_id is null");
			return (Criteria) this;
		}

		public Criteria andUser_idIsNotNull() {
			addCriterion("user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUser_idEqualTo(Integer value) {
			addCriterion("user_id =", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idNotEqualTo(Integer value) {
			addCriterion("user_id <>", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idGreaterThan(Integer value) {
			addCriterion("user_id >", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idGreaterThanOrEqualTo(Integer value) {
			addCriterion("user_id >=", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idLessThan(Integer value) {
			addCriterion("user_id <", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idLessThanOrEqualTo(Integer value) {
			addCriterion("user_id <=", value, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idIn(List<Integer> values) {
			addCriterion("user_id in", values, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idNotIn(List<Integer> values) {
			addCriterion("user_id not in", values, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idBetween(Integer value1, Integer value2) {
			addCriterion("user_id between", value1, value2, "user_id");
			return (Criteria) this;
		}

		public Criteria andUser_idNotBetween(Integer value1, Integer value2) {
			addCriterion("user_id not between", value1, value2, "user_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idIsNull() {
			addCriterion("login_id is null");
			return (Criteria) this;
		}

		public Criteria andLogin_idIsNotNull() {
			addCriterion("login_id is not null");
			return (Criteria) this;
		}

		public Criteria andLogin_idEqualTo(String value) {
			addCriterion("login_id =", value, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idNotEqualTo(String value) {
			addCriterion("login_id <>", value, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idGreaterThan(String value) {
			addCriterion("login_id >", value, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idGreaterThanOrEqualTo(String value) {
			addCriterion("login_id >=", value, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idLessThan(String value) {
			addCriterion("login_id <", value, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idLessThanOrEqualTo(String value) {
			addCriterion("login_id <=", value, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idLike(String value) {
			addCriterion("login_id like", value, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idNotLike(String value) {
			addCriterion("login_id not like", value, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idIn(List<String> values) {
			addCriterion("login_id in", values, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idNotIn(List<String> values) {
			addCriterion("login_id not in", values, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idBetween(String value1, String value2) {
			addCriterion("login_id between", value1, value2, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_idNotBetween(String value1, String value2) {
			addCriterion("login_id not between", value1, value2, "login_id");
			return (Criteria) this;
		}

		public Criteria andLogin_passIsNull() {
			addCriterion("login_pass is null");
			return (Criteria) this;
		}

		public Criteria andLogin_passIsNotNull() {
			addCriterion("login_pass is not null");
			return (Criteria) this;
		}

		public Criteria andLogin_passEqualTo(String value) {
			addCriterion("login_pass =", value, "login_pass");
			return (Criteria) this;
		}

		public Criteria andLogin_passNotEqualTo(String value) {
			addCriterion("login_pass <>", value, "login_pass");
			return (Criteria) this;
		}

		public Criteria andLogin_passGreaterThan(String value) {
			addCriterion("login_pass >", value, "login_pass");
			return (Criteria) this;
		}

		public Criteria andLogin_passGreaterThanOrEqualTo(String value) {
			addCriterion("login_pass >=", value, "login_pass");
			return (Criteria) this;
		}

		public Criteria andLogin_passLessThan(String value) {
			addCriterion("login_pass <", value, "login_pass");
			return (Criteria) this;
		}

		public Criteria andLogin_passLessThanOrEqualTo(String value) {
			addCriterion("login_pass <=", value, "login_pass");
			return (Criteria) this;
		}

		public Criteria andLogin_passLike(String value) {
			addCriterion("login_pass like", value, "login_pass");
			return (Criteria) this;
		}

		public Criteria andLogin_passNotLike(String value) {
			addCriterion("login_pass not like", value, "login_pass");
			return (Criteria) this;
		}

		public Criteria andLogin_passIn(List<String> values) {
			addCriterion("login_pass in", values, "login_pass");
			return (Criteria) this;
		}

		public Criteria andLogin_passNotIn(List<String> values) {
			addCriterion("login_pass not in", values, "login_pass");
			return (Criteria) this;
		}

		public Criteria andLogin_passBetween(String value1, String value2) {
			addCriterion("login_pass between", value1, value2, "login_pass");
			return (Criteria) this;
		}

		public Criteria andLogin_passNotBetween(String value1, String value2) {
			addCriterion("login_pass not between", value1, value2, "login_pass");
			return (Criteria) this;
		}

		public Criteria andNfdjIsNull() {
			addCriterion("nfdj is null");
			return (Criteria) this;
		}

		public Criteria andNfdjIsNotNull() {
			addCriterion("nfdj is not null");
			return (Criteria) this;
		}

		public Criteria andNfdjEqualTo(Float value) {
			addCriterion("nfdj =", value, "nfdj");
			return (Criteria) this;
		}

		public Criteria andNfdjNotEqualTo(Float value) {
			addCriterion("nfdj <>", value, "nfdj");
			return (Criteria) this;
		}

		public Criteria andNfdjGreaterThan(Float value) {
			addCriterion("nfdj >", value, "nfdj");
			return (Criteria) this;
		}

		public Criteria andNfdjGreaterThanOrEqualTo(Float value) {
			addCriterion("nfdj >=", value, "nfdj");
			return (Criteria) this;
		}

		public Criteria andNfdjLessThan(Float value) {
			addCriterion("nfdj <", value, "nfdj");
			return (Criteria) this;
		}

		public Criteria andNfdjLessThanOrEqualTo(Float value) {
			addCriterion("nfdj <=", value, "nfdj");
			return (Criteria) this;
		}

		public Criteria andNfdjIn(List<Float> values) {
			addCriterion("nfdj in", values, "nfdj");
			return (Criteria) this;
		}

		public Criteria andNfdjNotIn(List<Float> values) {
			addCriterion("nfdj not in", values, "nfdj");
			return (Criteria) this;
		}

		public Criteria andNfdjBetween(Float value1, Float value2) {
			addCriterion("nfdj between", value1, value2, "nfdj");
			return (Criteria) this;
		}

		public Criteria andNfdjNotBetween(Float value1, Float value2) {
			addCriterion("nfdj not between", value1, value2, "nfdj");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Integer> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Integer> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andDescptionIsNull() {
			addCriterion("descption is null");
			return (Criteria) this;
		}

		public Criteria andDescptionIsNotNull() {
			addCriterion("descption is not null");
			return (Criteria) this;
		}

		public Criteria andDescptionEqualTo(String value) {
			addCriterion("descption =", value, "descption");
			return (Criteria) this;
		}

		public Criteria andDescptionNotEqualTo(String value) {
			addCriterion("descption <>", value, "descption");
			return (Criteria) this;
		}

		public Criteria andDescptionGreaterThan(String value) {
			addCriterion("descption >", value, "descption");
			return (Criteria) this;
		}

		public Criteria andDescptionGreaterThanOrEqualTo(String value) {
			addCriterion("descption >=", value, "descption");
			return (Criteria) this;
		}

		public Criteria andDescptionLessThan(String value) {
			addCriterion("descption <", value, "descption");
			return (Criteria) this;
		}

		public Criteria andDescptionLessThanOrEqualTo(String value) {
			addCriterion("descption <=", value, "descption");
			return (Criteria) this;
		}

		public Criteria andDescptionLike(String value) {
			addCriterion("descption like", value, "descption");
			return (Criteria) this;
		}

		public Criteria andDescptionNotLike(String value) {
			addCriterion("descption not like", value, "descption");
			return (Criteria) this;
		}

		public Criteria andDescptionIn(List<String> values) {
			addCriterion("descption in", values, "descption");
			return (Criteria) this;
		}

		public Criteria andDescptionNotIn(List<String> values) {
			addCriterion("descption not in", values, "descption");
			return (Criteria) this;
		}

		public Criteria andDescptionBetween(String value1, String value2) {
			addCriterion("descption between", value1, value2, "descption");
			return (Criteria) this;
		}

		public Criteria andDescptionNotBetween(String value1, String value2) {
			addCriterion("descption not between", value1, value2, "descption");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
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
     * This class corresponds to the database table login_users
     *
     * @mbggenerated do_not_delete_during_merge Sun Dec 20 13:55:13 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}