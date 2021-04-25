package com.webold.core.controller;

import com.webold.core.domain.dto.BaseDTO;
import com.webold.core.domain.dto.PageDTO;
import com.webold.core.service.BaseAggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public class BaseAggController<S, R, I extends Serializable> {

    @Autowired
    BaseAggService<S, R, I> baseAggService;

    @PostMapping
    public ResponseEntity<BaseDTO<R>> save(@Valid @RequestBody S s) {
        return new ResponseEntity<>(baseAggService.save(s), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BaseDTO<R>> update(@RequestBody S s) {
        return new ResponseEntity<>(baseAggService.update(s), HttpStatus.OK);
    }


    @DeleteMapping(value = "/byId")
    public ResponseEntity<BaseDTO<R>> deleteVirtualById(@RequestParam I id) {
        return new ResponseEntity<>(baseAggService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BaseDTO<R>> getById(@RequestParam I id) {
        return new ResponseEntity<>(baseAggService.loadById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<BaseDTO<List<R>>> getAll() {
        return new ResponseEntity<>(baseAggService.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/all/ById")
    public ResponseEntity<BaseDTO<List<R>>> getAllById(@RequestBody List<I> id) {
        return new ResponseEntity<>(baseAggService.allById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/all/pagination")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> getByPagination(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return new ResponseEntity<>(baseAggService.getByPagination(page, pageSize), HttpStatus.OK);
    }

    @GetMapping(value = "/exists/ById")
    public ResponseEntity<BaseDTO<Boolean>> existsById(@RequestParam I id) {
        return new ResponseEntity<>(baseAggService.existsById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/filter")
    public ResponseEntity<BaseDTO<List<R>>> filter(@RequestBody S s) {
        return new ResponseEntity<>(baseAggService.filter(s), HttpStatus.OK);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<BaseDTO<Long>> count() {
        return new ResponseEntity<>(baseAggService.count(), HttpStatus.OK);
    }
}
