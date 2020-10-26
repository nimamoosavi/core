package ir.webold.framework.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import ir.webold.framework.anotations.Oauth;
import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.domain.dto.PageDTO;
import ir.webold.framework.domain.entity.BaseEntity;
import ir.webold.framework.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

import static ir.webold.framework.config.general.GeneralStatic.APP_KEY;
import static ir.webold.framework.config.general.GeneralStatic.AUTHORIZATION;

//s request view model
//R response view model
@Oauth
public abstract class BaseController<T extends BaseEntity<I>, S, R, I extends Serializable> {

    @Autowired
    GeneralService<T, S, R, I> generalService;

    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = APP_KEY, value = APP_KEY, required = true, dataType = "string", paramType = "header")})
    @PostMapping
    public ResponseEntity<BaseDTO<R>> save(@Valid @RequestBody S s) {
        return new ResponseEntity<>(generalService.save(s), HttpStatus.OK);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = APP_KEY, value = APP_KEY, required = true, dataType = "string", paramType = "header")})
    @PutMapping
    public ResponseEntity<BaseDTO<R>> update(@Valid @RequestBody S s, @Valid @RequestParam I id) {
        return new ResponseEntity<>(generalService.update(s, id), HttpStatus.OK);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = APP_KEY, value = APP_KEY, required = true, dataType = "string", paramType = "header")})
    @DeleteMapping
    public ResponseEntity<BaseDTO<Boolean>> deleteById(@Valid @RequestParam I id) {
        return new ResponseEntity<>(generalService.deleteById(id), HttpStatus.OK);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = APP_KEY, value = APP_KEY, required = true, dataType = "string", paramType = "header")})
    @GetMapping
    public ResponseEntity<BaseDTO<R>> getByID(@Valid @RequestParam I id) {
        return new ResponseEntity<>(generalService.loadById(id), HttpStatus.OK);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = APP_KEY, value = APP_KEY, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/all")
    public ResponseEntity<BaseDTO<List<R>>> getAll() {
        return new ResponseEntity<>(generalService.getAll(), HttpStatus.OK);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = APP_KEY, value = APP_KEY, required = true, dataType = "string", paramType = "header")})
    @PostMapping(value = "/all/ById")
    public ResponseEntity<BaseDTO<List<R>>> getAllById(@Valid @RequestBody List<I> id) {
        return new ResponseEntity<>(generalService.allById(id), HttpStatus.OK);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = APP_KEY, value = APP_KEY, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/all/pagination")
    public ResponseEntity<BaseDTO<PageDTO<List<R>>>> getByPagination(@Valid @RequestParam Integer page, @RequestParam Integer pageSize) {
        return new ResponseEntity<>(generalService.getByPagination(page, pageSize), HttpStatus.OK);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = APP_KEY, value = APP_KEY, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/exists/ById")
    public ResponseEntity<BaseDTO<Boolean>> existsById(@Valid @RequestParam I id) {
        return new ResponseEntity<>(generalService.existsById(id), HttpStatus.OK);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = APP_KEY, value = APP_KEY, required = true, dataType = "string", paramType = "header")})
    @PostMapping(value = "/filter")
    public ResponseEntity<BaseDTO<List<R>>> filter(@Valid @RequestBody S s) {
        return new ResponseEntity<>(generalService.filter(s), HttpStatus.OK);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = AUTHORIZATION, value = AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = APP_KEY, value = APP_KEY, required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/count")
    public ResponseEntity<BaseDTO<Long>> count() {
        return new ResponseEntity<>(generalService.count(), HttpStatus.OK);
    }

}
