package ir.webold.framework.client;

import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.domain.dto.PageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

import static ir.webold.framework.config.general.GeneralStatic.RRN;


public interface BaseAggClient<S, R, I extends Serializable> {

    @PostMapping
    public ResponseEntity<BaseDTO<R>> save(@RequestHeader(RRN) String rrn, @RequestBody S s);

    @PutMapping
    public ResponseEntity<BaseDTO<R>> update(@RequestHeader(RRN) String rrn, @Valid @RequestBody S s);

    @DeleteMapping
    public ResponseEntity<BaseDTO<R>> deleteById(@RequestHeader(RRN) String rrn, @Valid @RequestParam("id") I id);

    @GetMapping
    public ResponseEntity<BaseDTO<R>> getByID(@RequestHeader(RRN) String rrn, @Valid @RequestParam("id") I id);

    @GetMapping(value = "/all")
    public ResponseEntity<BaseDTO<List<R>>> getAll(@RequestHeader(RRN) String rrn);

    @PostMapping(value = "/all/ById")
    public ResponseEntity<BaseDTO<List<R>>> getAllById(@RequestHeader(RRN) String rrn, @Valid @RequestBody List<I> id);

    @GetMapping(value = "/all/pagination")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> getByPagination(@RequestHeader(RRN) String rrn, @Valid @RequestParam("page") Integer page,@Valid @RequestParam("page") Integer pageSize);

    @GetMapping(value = "/exists/ById")
    public ResponseEntity<BaseDTO<Boolean>> existsById(@RequestHeader(RRN) String rrn, @Valid @RequestParam("id") I id);

    @PostMapping(value = "/filter")
    public ResponseEntity<BaseDTO<List<R>>> filter(@RequestHeader(RRN) String rrn, @Valid @RequestBody S s);

    @GetMapping(value = "/count")
    public ResponseEntity<BaseDTO<Long>> count(@RequestHeader(RRN) String rrn);
}
