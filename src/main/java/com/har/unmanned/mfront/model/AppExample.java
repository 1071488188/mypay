package com.har.unmanned.mfront.model;

import java.util.ArrayList;
import java.util.List;

public class AppExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAppNameIsNull() {
            addCriterion("APP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAppNameIsNotNull() {
            addCriterion("APP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAppNameEqualTo(String value) {
            addCriterion("APP_NAME =", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotEqualTo(String value) {
            addCriterion("APP_NAME <>", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameGreaterThan(String value) {
            addCriterion("APP_NAME >", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameGreaterThanOrEqualTo(String value) {
            addCriterion("APP_NAME >=", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLessThan(String value) {
            addCriterion("APP_NAME <", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLessThanOrEqualTo(String value) {
            addCriterion("APP_NAME <=", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameLike(String value) {
            addCriterion("APP_NAME like", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotLike(String value) {
            addCriterion("APP_NAME not like", value, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameIn(List<String> values) {
            addCriterion("APP_NAME in", values, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotIn(List<String> values) {
            addCriterion("APP_NAME not in", values, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameBetween(String value1, String value2) {
            addCriterion("APP_NAME between", value1, value2, "appName");
            return (Criteria) this;
        }

        public Criteria andAppNameNotBetween(String value1, String value2) {
            addCriterion("APP_NAME not between", value1, value2, "appName");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNull() {
            addCriterion("APP_ID is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("APP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("APP_ID =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("APP_ID <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("APP_ID >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("APP_ID >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("APP_ID <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("APP_ID <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("APP_ID like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("APP_ID not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("APP_ID in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("APP_ID not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("APP_ID between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("APP_ID not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyIsNull() {
            addCriterion("FRONT_PRIKEY is null");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyIsNotNull() {
            addCriterion("FRONT_PRIKEY is not null");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyEqualTo(String value) {
            addCriterion("FRONT_PRIKEY =", value, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyNotEqualTo(String value) {
            addCriterion("FRONT_PRIKEY <>", value, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyGreaterThan(String value) {
            addCriterion("FRONT_PRIKEY >", value, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyGreaterThanOrEqualTo(String value) {
            addCriterion("FRONT_PRIKEY >=", value, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyLessThan(String value) {
            addCriterion("FRONT_PRIKEY <", value, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyLessThanOrEqualTo(String value) {
            addCriterion("FRONT_PRIKEY <=", value, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyLike(String value) {
            addCriterion("FRONT_PRIKEY like", value, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyNotLike(String value) {
            addCriterion("FRONT_PRIKEY not like", value, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyIn(List<String> values) {
            addCriterion("FRONT_PRIKEY in", values, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyNotIn(List<String> values) {
            addCriterion("FRONT_PRIKEY not in", values, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyBetween(String value1, String value2) {
            addCriterion("FRONT_PRIKEY between", value1, value2, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPrikeyNotBetween(String value1, String value2) {
            addCriterion("FRONT_PRIKEY not between", value1, value2, "frontPrikey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyIsNull() {
            addCriterion("FRONT_PUBKEY is null");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyIsNotNull() {
            addCriterion("FRONT_PUBKEY is not null");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyEqualTo(String value) {
            addCriterion("FRONT_PUBKEY =", value, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyNotEqualTo(String value) {
            addCriterion("FRONT_PUBKEY <>", value, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyGreaterThan(String value) {
            addCriterion("FRONT_PUBKEY >", value, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyGreaterThanOrEqualTo(String value) {
            addCriterion("FRONT_PUBKEY >=", value, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyLessThan(String value) {
            addCriterion("FRONT_PUBKEY <", value, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyLessThanOrEqualTo(String value) {
            addCriterion("FRONT_PUBKEY <=", value, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyLike(String value) {
            addCriterion("FRONT_PUBKEY like", value, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyNotLike(String value) {
            addCriterion("FRONT_PUBKEY not like", value, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyIn(List<String> values) {
            addCriterion("FRONT_PUBKEY in", values, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyNotIn(List<String> values) {
            addCriterion("FRONT_PUBKEY not in", values, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyBetween(String value1, String value2) {
            addCriterion("FRONT_PUBKEY between", value1, value2, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andFrontPubkeyNotBetween(String value1, String value2) {
            addCriterion("FRONT_PUBKEY not between", value1, value2, "frontPubkey");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitIsNull() {
            addCriterion("APP_PRIKEY_INIT is null");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitIsNotNull() {
            addCriterion("APP_PRIKEY_INIT is not null");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitEqualTo(String value) {
            addCriterion("APP_PRIKEY_INIT =", value, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitNotEqualTo(String value) {
            addCriterion("APP_PRIKEY_INIT <>", value, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitGreaterThan(String value) {
            addCriterion("APP_PRIKEY_INIT >", value, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitGreaterThanOrEqualTo(String value) {
            addCriterion("APP_PRIKEY_INIT >=", value, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitLessThan(String value) {
            addCriterion("APP_PRIKEY_INIT <", value, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitLessThanOrEqualTo(String value) {
            addCriterion("APP_PRIKEY_INIT <=", value, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitLike(String value) {
            addCriterion("APP_PRIKEY_INIT like", value, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitNotLike(String value) {
            addCriterion("APP_PRIKEY_INIT not like", value, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitIn(List<String> values) {
            addCriterion("APP_PRIKEY_INIT in", values, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitNotIn(List<String> values) {
            addCriterion("APP_PRIKEY_INIT not in", values, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitBetween(String value1, String value2) {
            addCriterion("APP_PRIKEY_INIT between", value1, value2, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPrikeyInitNotBetween(String value1, String value2) {
            addCriterion("APP_PRIKEY_INIT not between", value1, value2, "appPrikeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitIsNull() {
            addCriterion("APP_PUBKEY_INIT is null");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitIsNotNull() {
            addCriterion("APP_PUBKEY_INIT is not null");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitEqualTo(String value) {
            addCriterion("APP_PUBKEY_INIT =", value, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitNotEqualTo(String value) {
            addCriterion("APP_PUBKEY_INIT <>", value, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitGreaterThan(String value) {
            addCriterion("APP_PUBKEY_INIT >", value, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitGreaterThanOrEqualTo(String value) {
            addCriterion("APP_PUBKEY_INIT >=", value, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitLessThan(String value) {
            addCriterion("APP_PUBKEY_INIT <", value, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitLessThanOrEqualTo(String value) {
            addCriterion("APP_PUBKEY_INIT <=", value, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitLike(String value) {
            addCriterion("APP_PUBKEY_INIT like", value, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitNotLike(String value) {
            addCriterion("APP_PUBKEY_INIT not like", value, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitIn(List<String> values) {
            addCriterion("APP_PUBKEY_INIT in", values, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitNotIn(List<String> values) {
            addCriterion("APP_PUBKEY_INIT not in", values, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitBetween(String value1, String value2) {
            addCriterion("APP_PUBKEY_INIT between", value1, value2, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andAppPubkeyInitNotBetween(String value1, String value2) {
            addCriterion("APP_PUBKEY_INIT not between", value1, value2, "appPubkeyInit");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`STATUS` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`STATUS` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`STATUS` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`STATUS` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`STATUS` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`STATUS` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`STATUS` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`STATUS` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`STATUS` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`STATUS` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`STATUS` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`STATUS` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIsNull() {
            addCriterion("PARTNER_ID is null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIsNotNull() {
            addCriterion("PARTNER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdEqualTo(Integer value) {
            addCriterion("PARTNER_ID =", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotEqualTo(Integer value) {
            addCriterion("PARTNER_ID <>", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdGreaterThan(Integer value) {
            addCriterion("PARTNER_ID >", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARTNER_ID >=", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLessThan(Integer value) {
            addCriterion("PARTNER_ID <", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLessThanOrEqualTo(Integer value) {
            addCriterion("PARTNER_ID <=", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIn(List<Integer> values) {
            addCriterion("PARTNER_ID in", values, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotIn(List<Integer> values) {
            addCriterion("PARTNER_ID not in", values, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdBetween(Integer value1, Integer value2) {
            addCriterion("PARTNER_ID between", value1, value2, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PARTNER_ID not between", value1, value2, "partnerId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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
}