package com.har.unmanned.mfront.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StorageStockExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StorageStockExample() {
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

        public Criteria andStorageIdIsNull() {
            addCriterion("storage_id is null");
            return (Criteria) this;
        }

        public Criteria andStorageIdIsNotNull() {
            addCriterion("storage_id is not null");
            return (Criteria) this;
        }

        public Criteria andStorageIdEqualTo(Integer value) {
            addCriterion("storage_id =", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdNotEqualTo(Integer value) {
            addCriterion("storage_id <>", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdGreaterThan(Integer value) {
            addCriterion("storage_id >", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("storage_id >=", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdLessThan(Integer value) {
            addCriterion("storage_id <", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdLessThanOrEqualTo(Integer value) {
            addCriterion("storage_id <=", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdIn(List<Integer> values) {
            addCriterion("storage_id in", values, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdNotIn(List<Integer> values) {
            addCriterion("storage_id not in", values, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdBetween(Integer value1, Integer value2) {
            addCriterion("storage_id between", value1, value2, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("storage_id not between", value1, value2, "storageId");
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

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityIsNull() {
            addCriterion("initial_quantity is null");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityIsNotNull() {
            addCriterion("initial_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityEqualTo(Integer value) {
            addCriterion("initial_quantity =", value, "initialQuantity");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityNotEqualTo(Integer value) {
            addCriterion("initial_quantity <>", value, "initialQuantity");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityGreaterThan(Integer value) {
            addCriterion("initial_quantity >", value, "initialQuantity");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("initial_quantity >=", value, "initialQuantity");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityLessThan(Integer value) {
            addCriterion("initial_quantity <", value, "initialQuantity");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("initial_quantity <=", value, "initialQuantity");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityIn(List<Integer> values) {
            addCriterion("initial_quantity in", values, "initialQuantity");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityNotIn(List<Integer> values) {
            addCriterion("initial_quantity not in", values, "initialQuantity");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityBetween(Integer value1, Integer value2) {
            addCriterion("initial_quantity between", value1, value2, "initialQuantity");
            return (Criteria) this;
        }

        public Criteria andInitialQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("initial_quantity not between", value1, value2, "initialQuantity");
            return (Criteria) this;
        }

        public Criteria andInitialTimeIsNull() {
            addCriterion("initial_time is null");
            return (Criteria) this;
        }

        public Criteria andInitialTimeIsNotNull() {
            addCriterion("initial_time is not null");
            return (Criteria) this;
        }

        public Criteria andInitialTimeEqualTo(Date value) {
            addCriterion("initial_time =", value, "initialTime");
            return (Criteria) this;
        }

        public Criteria andInitialTimeNotEqualTo(Date value) {
            addCriterion("initial_time <>", value, "initialTime");
            return (Criteria) this;
        }

        public Criteria andInitialTimeGreaterThan(Date value) {
            addCriterion("initial_time >", value, "initialTime");
            return (Criteria) this;
        }

        public Criteria andInitialTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("initial_time >=", value, "initialTime");
            return (Criteria) this;
        }

        public Criteria andInitialTimeLessThan(Date value) {
            addCriterion("initial_time <", value, "initialTime");
            return (Criteria) this;
        }

        public Criteria andInitialTimeLessThanOrEqualTo(Date value) {
            addCriterion("initial_time <=", value, "initialTime");
            return (Criteria) this;
        }

        public Criteria andInitialTimeIn(List<Date> values) {
            addCriterion("initial_time in", values, "initialTime");
            return (Criteria) this;
        }

        public Criteria andInitialTimeNotIn(List<Date> values) {
            addCriterion("initial_time not in", values, "initialTime");
            return (Criteria) this;
        }

        public Criteria andInitialTimeBetween(Date value1, Date value2) {
            addCriterion("initial_time between", value1, value2, "initialTime");
            return (Criteria) this;
        }

        public Criteria andInitialTimeNotBetween(Date value1, Date value2) {
            addCriterion("initial_time not between", value1, value2, "initialTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andMinQuantityIsNull() {
            addCriterion("min_quantity is null");
            return (Criteria) this;
        }

        public Criteria andMinQuantityIsNotNull() {
            addCriterion("min_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andMinQuantityEqualTo(Integer value) {
            addCriterion("min_quantity =", value, "minQuantity");
            return (Criteria) this;
        }

        public Criteria andMinQuantityNotEqualTo(Integer value) {
            addCriterion("min_quantity <>", value, "minQuantity");
            return (Criteria) this;
        }

        public Criteria andMinQuantityGreaterThan(Integer value) {
            addCriterion("min_quantity >", value, "minQuantity");
            return (Criteria) this;
        }

        public Criteria andMinQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_quantity >=", value, "minQuantity");
            return (Criteria) this;
        }

        public Criteria andMinQuantityLessThan(Integer value) {
            addCriterion("min_quantity <", value, "minQuantity");
            return (Criteria) this;
        }

        public Criteria andMinQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("min_quantity <=", value, "minQuantity");
            return (Criteria) this;
        }

        public Criteria andMinQuantityIn(List<Integer> values) {
            addCriterion("min_quantity in", values, "minQuantity");
            return (Criteria) this;
        }

        public Criteria andMinQuantityNotIn(List<Integer> values) {
            addCriterion("min_quantity not in", values, "minQuantity");
            return (Criteria) this;
        }

        public Criteria andMinQuantityBetween(Integer value1, Integer value2) {
            addCriterion("min_quantity between", value1, value2, "minQuantity");
            return (Criteria) this;
        }

        public Criteria andMinQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("min_quantity not between", value1, value2, "minQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityIsNull() {
            addCriterion("buy_base_quantity is null");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityIsNotNull() {
            addCriterion("buy_base_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityEqualTo(Integer value) {
            addCriterion("buy_base_quantity =", value, "buyBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityNotEqualTo(Integer value) {
            addCriterion("buy_base_quantity <>", value, "buyBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityGreaterThan(Integer value) {
            addCriterion("buy_base_quantity >", value, "buyBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_base_quantity >=", value, "buyBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityLessThan(Integer value) {
            addCriterion("buy_base_quantity <", value, "buyBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("buy_base_quantity <=", value, "buyBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityIn(List<Integer> values) {
            addCriterion("buy_base_quantity in", values, "buyBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityNotIn(List<Integer> values) {
            addCriterion("buy_base_quantity not in", values, "buyBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityBetween(Integer value1, Integer value2) {
            addCriterion("buy_base_quantity between", value1, value2, "buyBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyBaseQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_base_quantity not between", value1, value2, "buyBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityIsNull() {
            addCriterion("buy_quantity is null");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityIsNotNull() {
            addCriterion("buy_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityEqualTo(Integer value) {
            addCriterion("buy_quantity =", value, "buyQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityNotEqualTo(Integer value) {
            addCriterion("buy_quantity <>", value, "buyQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityGreaterThan(Integer value) {
            addCriterion("buy_quantity >", value, "buyQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_quantity >=", value, "buyQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityLessThan(Integer value) {
            addCriterion("buy_quantity <", value, "buyQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("buy_quantity <=", value, "buyQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityIn(List<Integer> values) {
            addCriterion("buy_quantity in", values, "buyQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityNotIn(List<Integer> values) {
            addCriterion("buy_quantity not in", values, "buyQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityBetween(Integer value1, Integer value2) {
            addCriterion("buy_quantity between", value1, value2, "buyQuantity");
            return (Criteria) this;
        }

        public Criteria andBuyQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_quantity not between", value1, value2, "buyQuantity");
            return (Criteria) this;
        }

        public Criteria andStorageLocationIsNull() {
            addCriterion("storage_location is null");
            return (Criteria) this;
        }

        public Criteria andStorageLocationIsNotNull() {
            addCriterion("storage_location is not null");
            return (Criteria) this;
        }

        public Criteria andStorageLocationEqualTo(String value) {
            addCriterion("storage_location =", value, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStorageLocationNotEqualTo(String value) {
            addCriterion("storage_location <>", value, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStorageLocationGreaterThan(String value) {
            addCriterion("storage_location >", value, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStorageLocationGreaterThanOrEqualTo(String value) {
            addCriterion("storage_location >=", value, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStorageLocationLessThan(String value) {
            addCriterion("storage_location <", value, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStorageLocationLessThanOrEqualTo(String value) {
            addCriterion("storage_location <=", value, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStorageLocationLike(String value) {
            addCriterion("storage_location like", value, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStorageLocationNotLike(String value) {
            addCriterion("storage_location not like", value, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStorageLocationIn(List<String> values) {
            addCriterion("storage_location in", values, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStorageLocationNotIn(List<String> values) {
            addCriterion("storage_location not in", values, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStorageLocationBetween(String value1, String value2) {
            addCriterion("storage_location between", value1, value2, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStorageLocationNotBetween(String value1, String value2) {
            addCriterion("storage_location not between", value1, value2, "storageLocation");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
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