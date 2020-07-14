package ir.webold.framework.service;

import ir.webold.framework.anotations.Log;
import ir.webold.framework.client.BaseAggClient;
import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.domain.dto.PageDTO;
import ir.webold.framework.utility.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@Log
public abstract class BaseAggService<S, R, I extends Serializable> {


    @Autowired
    BaseAggClient<S, R, I> baseAggClient;
    @Autowired
    ApplicationRequest applicationRequest;


    public BaseDTO<R> save(S s) {
        return baseAggClient.save(getRrn(),s).getBody();
    }

    public BaseDTO<R> update(S s) {
        return baseAggClient.update(getRrn(),s).getBody();
    }

    public BaseDTO<R> deleteById(I id) {
        return baseAggClient.deleteById(getRrn(),id).getBody();
    }


    public BaseDTO<R> loadById(I id) {
        return baseAggClient.getByID(getRrn(),id).getBody();
    }

    public BaseDTO<List<R>> getAll() {
        return baseAggClient.getAll(getRrn()).getBody();
    }

    public BaseDTO<List<R>> allById(List<I> idList) {
        return baseAggClient.getAllById(getRrn(),idList).getBody();
    }

    public BaseDTO<Boolean> existsById(I id) {
        return baseAggClient.existsById(getRrn(),id).getBody();
    }


    public BaseDTO<PageDTO<List<R>>> getByPagination(Integer page,Integer pageSize) {
        return baseAggClient.getByPagination(getRrn(),page,pageSize).getBody();
    }

    public BaseDTO<List<R>> filter(S s) {
        return baseAggClient.filter(getRrn(),s).getBody();
    }

    public BaseDTO<Long> count() {
        return baseAggClient.count(getRrn()).getBody();
    }


    public String getRrn(){
        return applicationRequest.getHeader("rrn");
    }
}
