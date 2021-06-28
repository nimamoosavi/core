package com.nicico.cost.framework.packages.crud.view;

import lombok.Data;

import java.util.List;

@Data
public class Criteria {
    private String fieldName;
    private Operator operator;
    private Object value;
    private String start;
    private String end;
    private List<Criteria> criteriaChain;
    private List<Sort> sorts;
    public static CriteriaBuilder builder(){
        return new CriteriaBuilder();
    }

    public static final class CriteriaBuilder {
        private String fieldName;
        private Operator operator;
        private Object value;
        private String start;
        private String end;
        private List<Criteria> criteriaChain;
        private List<Sort> sorts;

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

        public CriteriaBuilder start(String start) {
            this.start = start;
            return this;
        }

        public CriteriaBuilder end(String end) {
            this.end = end;
            return this;
        }

        public CriteriaBuilder criteria(List<Criteria> criteriaChain) {
            this.criteriaChain = criteriaChain;
            return this;
        }

        public CriteriaBuilder sorts(List<Sort> sorts) {
            this.sorts = sorts;
            return this;
        }

        public Criteria build() {
            Criteria criteria = new Criteria();
            criteria.setFieldName(fieldName);
            criteria.setOperator(operator);
            criteria.setValue(value);
            criteria.setStart(start);
            criteria.setEnd(end);
            criteria.setCriteriaChain(this.criteriaChain);
            criteria.setSorts(sorts);
            return criteria;
        }
    }
}
