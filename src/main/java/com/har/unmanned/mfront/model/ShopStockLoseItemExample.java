package com.har.unmanned.mfront.model;

import java.util.ArrayList;
import java.util.List;

public class ShopStockLoseItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopStockLoseItemExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLoseIdIsNull() {
            addCriterion("lose_id is null");
            return (Criteria) this;
        }

        public Criteria andLoseIdIsNotNull() {
            addCriterion("lose_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoseIdEqualTo(Long value) {
            addCriterion("lose_id =", value, "loseId");
            return (Criteria) this;
        }

        public Criteria andLoseIdNotEqualTo(Long value) {
            addCriterion("lose_id <>", value, "loseId");
            return (Criteria) this;
        }

        public Criteria andLoseIdGreaterThan(Long value) {
            addCriterion("lose_id >", value, "loseId");
            return (Criteria) this;
        }

        public Criteria andLoseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("lose_id >=", value, "loseId");
            return (Criteria) this;
        }

        public Criteria andLoseIdLessThan(Long value) {
            addCriterion("lose_id <", value, "loseId");
            return (Criteria) this;
        }

        public Criteria andLoseIdLessThanOrEqualTo(Long value) {
            addCriterion("lose_id <=", value, "loseId");
            return (Criteria) this;
        }

        public Criteria andLoseIdIn(List<Long> values) {
            addCriterion("lose_id in", values, "loseId");
            return (Criteria) this;
        }

        public Criteria andLoseIdNotIn(List<Long> values) {
            addCriterion("lose_id not in", values, "loseId");
            return (Criteria) this;
        }

        public Criteria andLoseIdBetween(Long value1, Long value2) {
            addCriterion("lose_id between", value1, value2, "loseId");
            return (Criteria) this;
        }

        public Criteria andLoseIdNotBetween(Long value1, Long value2) {
            addCriterion("lose_id not between", value1, value2, "loseId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Long value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Long value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Long value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Long value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Long> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Long> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Long value1, Long value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Long value1, Long value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityIsNull() {
            addCriterion("goods_original_quantity is null");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityIsNotNull() {
            addCriterion("goods_original_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityEqualTo(Integer value) {
            addCriterion("goods_original_quantity =", value, "goodsOriginalQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityNotEqualTo(Integer value) {
            addCriterion("goods_original_quantity <>", value, "goodsOriginalQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityGreaterThan(Integer value) {
            addCriterion("goods_original_quantity >", value, "goodsOriginalQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_original_quantity >=", value, "goodsOriginalQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityLessThan(Integer value) {
            addCriterion("goods_original_quantity <", value, "goodsOriginalQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("goods_original_quantity <=", value, "goodsOriginalQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityIn(List<Integer> values) {
            addCriterion("goods_original_quantity in", values, "goodsOriginalQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityNotIn(List<Integer> values) {
            addCriterion("goods_original_quantity not in", values, "goodsOriginalQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityBetween(Integer value1, Integer value2) {
            addCriterion("goods_original_quantity between", value1, value2, "goodsOriginalQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsOriginalQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_original_quantity not between", value1, value2, "goodsOriginalQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityIsNull() {
            addCriterion("goods_now_quantity is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityIsNotNull() {
            addCriterion("goods_now_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityEqualTo(Integer value) {
            addCriterion("goods_now_quantity =", value, "goodsNowQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityNotEqualTo(Integer value) {
            addCriterion("goods_now_quantity <>", value, "goodsNowQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityGreaterThan(Integer value) {
            addCriterion("goods_now_quantity >", value, "goodsNowQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_now_quantity >=", value, "goodsNowQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityLessThan(Integer value) {
            addCriterion("goods_now_quantity <", value, "goodsNowQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("goods_now_quantity <=", value, "goodsNowQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityIn(List<Integer> values) {
            addCriterion("goods_now_quantity in", values, "goodsNowQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityNotIn(List<Integer> values) {
            addCriterion("goods_now_quantity not in", values, "goodsNowQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityBetween(Integer value1, Integer value2) {
            addCriterion("goods_now_quantity between", value1, value2, "goodsNowQuantity");
            return (Criteria) this;
        }

        public Criteria andGoodsNowQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_now_quantity not between", value1, value2, "goodsNowQuantity");
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