package com.webold.core.client;

import com.webold.core.domain.dto.BaseDTO;
import com.webold.core.domain.dto.PageDTO;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public class BaseAggClientHystrix<S, R, I extends Serializable> implements BaseAggClient<S, R, I> {

    @Override
    public ResponseEntity<BaseDTO<R>> save(String rrn, S s) {
        return null;
    }

    @Override
    public ResponseEntity<BaseDTO<R>> update(String rrn, @Valid S s) {
        return null;
    }

    @Override
    public ResponseEntity<BaseDTO<R>> deleteById(String rrn, @Valid I id) {
        return null;
    }

    @Override
    public ResponseEntity<BaseDTO<R>> getByID(String rrn, @Valid I id) {
        return null;
    }

    @Override
    public ResponseEntity<BaseDTO<List<R>>> getAll(String rrn) {
        return null;
    }

    @Override
    public ResponseEntity<BaseDTO<List<R>>> getAllById(String rrn, @Valid List<I> id) {
        return null;
    }

    @Override
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> getByPagination(String rrn, @Valid Integer page, @Valid Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<BaseDTO<Boolean>> existsById(String rrn, @Valid I id) {
        return null;
    }

    @Override
    public ResponseEntity<BaseDTO<List<R>>> filter(String rrn, @Valid S s) {
        return null;
    }

    @Override
    public ResponseEntity<BaseDTO<Long>> count(String rrn) {
        return null;
    }
}
