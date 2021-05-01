package com.webold.core.client;

import com.webold.core.config.general.GeneralStatic;
import com.webold.core.domain.dto.BaseDTO;
import com.webold.core.domain.dto.PageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;




public interface BaseAggClient<S, R, I extends Serializable> {

    @PostMapping
    public ResponseEntity<BaseDTO<R>> save(@RequestHeader(GeneralStatic.CORRELATION_ID) String correlationId, @RequestBody S s);

    @PutMapping
    public ResponseEntity<BaseDTO<R>> update(@RequestHeader(GeneralStatic.CORRELATION_ID) String rrn, @Valid @RequestBody S s);

    @DeleteMapping
    public ResponseEntity<BaseDTO<R>> deleteById(@RequestHeader(GeneralStatic.CORRELATION_ID) String rrn, @Valid @RequestParam("id") I id);

    @GetMapping
    public ResponseEntity<BaseDTO<R>> getByID(@RequestHeader(GeneralStatic.CORRELATION_ID) String rrn, @Valid @RequestParam("id") I id);

    @GetMapping(value = "/all")
    public ResponseEntity<BaseDTO<List<R>>> getAll(@RequestHeader(GeneralStatic.CORRELATION_ID) String rrn);

    @PostMapping(value = "/all/ById")
    public ResponseEntity<BaseDTO<List<R>>> getAllById(@RequestHeader(GeneralStatic.CORRELATION_ID) String rrn, @Valid @RequestBody List<I> id);

    @GetMapping(value = "/all/pagination")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> getByPagination(@RequestHeader(GeneralStatic.CORRELATION_ID) String rrn, @Valid @RequestParam("page") Integer page, @Valid @RequestParam("page") Integer pageSize);

    @GetMapping(value = "/exists/ById")
    public ResponseEntity<BaseDTO<Boolean>> existsById(@RequestHeader(GeneralStatic.CORRELATION_ID) String rrn, @Valid @RequestParam("id") I id);

    @PostMapping(value = "/filter")
    public ResponseEntity<BaseDTO<List<R>>> filter(@RequestHeader(GeneralStatic.CORRELATION_ID) String rrn, @Valid @RequestBody S s);

    @GetMapping(value = "/count")
    public ResponseEntity<BaseDTO<Long>> count(@RequestHeader(GeneralStatic.CORRELATION_ID) String rrn);
}
