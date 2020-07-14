package ir.webold.framework.client;

import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.domain.dto.PageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public interface BaseAggClient<S, R, ID extends Serializable> {

    @PostMapping
    public ResponseEntity<BaseDTO<R>> save(@RequestHeader("rrn") String rrn, @RequestBody S s);

    @PutMapping
    public ResponseEntity<BaseDTO<R>> update(@RequestHeader("rrn") String rrn, @Valid @RequestBody S s);

    @DeleteMapping
    public ResponseEntity<BaseDTO<R>> deleteById(@RequestHeader("rrn") String rrn, @Valid @RequestParam("id") ID id);

    @GetMapping
    public ResponseEntity<BaseDTO<R>> getByID(@RequestHeader("rrn") String rrn, @Valid @RequestParam("id") ID id);

    @GetMapping(value = "/all")
    public ResponseEntity<BaseDTO<List<R>>> getAll(@RequestHeader("rrn") String rrn);

    @PostMapping(value = "/all/ById")
    public ResponseEntity<BaseDTO<List<R>>> getAllById(@RequestHeader("rrn") String rrn, @Valid @RequestBody List<ID> id);

    @GetMapping(value = "/all/pagination")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> getByPagination(@RequestHeader("rrn") String rrn, @Valid @RequestParam("page") Integer page,@Valid @RequestParam("page") Integer pageSize);

    @GetMapping(value = "/exists/ById")
    public ResponseEntity<BaseDTO<Boolean>> existsById(@RequestHeader("rrn") String rrn, @Valid @RequestParam("id") ID id);

    @PostMapping(value = "/filter")
    public ResponseEntity<BaseDTO<List<R>>> filter(@RequestHeader("rrn") String rrn, @Valid @RequestBody S s);

    @GetMapping(value = "/count")
    public ResponseEntity<BaseDTO<Long>> count(@RequestHeader("rrn") String rrn);
}
