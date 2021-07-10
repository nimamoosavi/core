package com.nicico.cost.framework.packages.crud.view;

import lombok.Data;

import java.util.List;

@Data
public class Criteria {
    private String fieldName;
    private Operator operator;
    private Object value;
    private List<Criteria> criteriaChain;
    public static CriteriaBuilder builder(){
        return new CriteriaBuilder();
    }

    public static final class CriteriaBuilder {
        private String fieldName;
        private Operator operator;
        private Object value;
        private List<Criteria> criteriaChain;

        private CriteriaBuilder() {
        }

        public static CriteriaBuilder aCriteria() {
            return new CriteriaBuilder();
        }

        public CriteriaBuilder fieldName(String fieldName) {
            this.fieldName = fieldName;
            return this;
        }

        public CriteriaBuilder operator(Operator operator) {
            this.operator = operator;
            return this;
        }

        public CriteriaBuilder value(Object value) {
            this.value = value;
            return this;
        }

        public CriteriaBuilder criteria(List<Criteria> criteriaChain) {
            this.criteriaChain = criteriaChain;
            return this;
        }

        public Criteria build() {
            Criteria criteria = new Criteria();
            criteria.setFieldName(fieldName);
            criteria.setOperator(operator);
            criteria.setValue(value);
            criteria.setCriteriaChain(this.criteriaChain);
            return criteria;
        }
    }
}
