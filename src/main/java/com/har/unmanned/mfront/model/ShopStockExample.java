package com.har.unmanned.mfront.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopStockExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopStockExample() {
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

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Long value) {
            addCriterion("shop_id =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Long value) {
            addCriterion("shop_id <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Long value) {
            addCriterion("shop_id >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("shop_id >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Long value) {
            addCriterion("shop_id <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Long value) {
            addCriterion("shop_id <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Long> values) {
            addCriterion("shop_id in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Long> values) {
            addCriterion("shop_id not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Long value1, Long value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Long value1, Long value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
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

        public Criteria andLayerIsNull() {
            addCriterion("layer is null");
            return (Criteria) this;
        }

        public Criteria andLayerIsNotNull() {
            addCriterion("layer is not null");
            return (Criteria) this;
        }

        public Criteria andLayerEqualTo(Integer value) {
            addCriterion("layer =", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerNotEqualTo(Integer value) {
            addCriterion("layer <>", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerGreaterThan(Integer value) {
            addCriterion("layer >", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerGreaterThanOrEqualTo(Integer value) {
            addCriterion("layer >=", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerLessThan(Integer value) {
            addCriterion("layer <", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerLessThanOrEqualTo(Integer value) {
            addCriterion("layer <=", value, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerIn(List<Integer> values) {
            addCriterion("layer in", values, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerNotIn(List<Integer> values) {
            addCriterion("layer not in", values, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerBetween(Integer value1, Integer value2) {
            addCriterion("layer between", value1, value2, "layer");
            return (Criteria) this;
        }

        public Criteria andLayerNotBetween(Integer value1, Integer value2) {
            addCriterion("layer not between", value1, value2, "layer");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityIsNull() {
            addCriterion("dispatch_quantity is null");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityIsNotNull() {
            addCriterion("dispatch_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityEqualTo(Integer value) {
            addCriterion("dispatch_quantity =", value, "dispatchQuantity");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityNotEqualTo(Integer value) {
            addCriterion("dispatch_quantity <>", value, "dispatchQuantity");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityGreaterThan(Integer value) {
            addCriterion("dispatch_quantity >", value, "dispatchQuantity");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("dispatch_quantity >=", value, "dispatchQuantity");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityLessThan(Integer value) {
            addCriterion("dispatch_quantity <", value, "dispatchQuantity");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("dispatch_quantity <=", value, "dispatchQuantity");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityIn(List<Integer> values) {
            addCriterion("dispatch_quantity in", values, "dispatchQuantity");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityNotIn(List<Integer> values) {
            addCriterion("dispatch_quantity not in", values, "dispatchQuantity");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityBetween(Integer value1, Integer value2) {
            addCriterion("dispatch_quantity between", value1, value2, "dispatchQuantity");
            return (Criteria) this;
        }

        public Criteria andDispatchQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("dispatch_quantity not between", value1, value2, "dispatchQuantity");
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

        public Criteria andShopBaseQuantityIsNull() {
            addCriterion("shop_base_quantity is null");
            return (Criteria) this;
        }

        public Criteria andShopBaseQuantityIsNotNull() {
            addCriterion("shop_base_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andShopBaseQuantityEqualTo(Integer value) {
            addCriterion("shop_base_quantity =", value, "shopBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andShopBaseQuantityNotEqualTo(Integer value) {
            addCriterion("shop_base_quantity <>", value, "shopBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andShopBaseQuantityGreaterThan(Integer value) {
            addCriterion("shop_base_quantity >", value, "shopBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andShopBaseQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_base_quantity >=", value, "shopBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andShopBaseQuantityLessThan(Integer value) {
            addCriterion("shop_base_quantity <", value, "shopBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andShopBaseQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("shop_base_quantity <=", value, "shopBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andShopBaseQuantityIn(List<Integer> values) {
            addCriterion("shop_base_quantity in", values, "shopBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andShopBaseQuantityNotIn(List<Integer> values) {
            addCriterion("shop_base_quantity not in", values, "shopBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andShopBaseQuantityBetween(Integer value1, Integer value2) {
            addCriterion("shop_base_quantity between", value1, value2, "shopBaseQuantity");
            return (Criteria) this;
        }

        public Criteria andShopBaseQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_base_quantity not between", value1, value2, "shopBaseQuantity");
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

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(Long value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(Long value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(Long value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(Long value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<Long> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<Long> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(Long value1, Long value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
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