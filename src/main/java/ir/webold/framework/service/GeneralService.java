package ir.webold.framework.service;

import ir.webold.framework.anotations.Log;
import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.domain.dto.PageDTO;
import ir.webold.framework.domain.entity.BaseEntity;
import ir.webold.framework.enums.exception.ExceptionEnum;
import ir.webold.framework.enums.ResultStatus;
import ir.webold.framework.exception.ApplicationException;
import ir.webold.framework.mapper.GeneralMapper;
import ir.webold.framework.repository.GeneralRepository;
import ir.webold.framework.utility.ApplicationPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

import static ir.webold.framework.utility.ApplicationResource.successResource;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Log
public abstract class GeneralService<T extends BaseEntity<I>, S, R, I extends Serializable> {

    @Autowired
    GeneralRepository<T, I> generalRepository;
    @Autowired
    public ApplicationException applicationException;
    @Autowired
    ApplicationPagination applicationPagination;



    @Autowired
    @Lazy
    GeneralMapper<T, S, R> generalMapper;

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    public BaseDTO<R> save(S s) {
        T t = generalMapper.requestToEntity(s);
        T save = generalRepository.save(t);
        R r = generalMapper.toResponseModel(save);
        return successResponse(r);
    }

    @Transactional
    public BaseDTO<R> update(S s) {
        T t = generalMapper.requestToEntity(s);
        T merge = entityManager.merge(t);
        R r = generalMapper.toResponseModel(merge);
        return successResponse(r);

    }

    @Transactional
    public BaseDTO<Boolean> deleteById(I id) {
        T t = generalRepository.findById(id).orElseThrow(
                () -> applicationException.createApplicationException(ExceptionEnum.NOTFOUND, HttpStatus.NOT_FOUND)
        );
        t.setDeleted(true);
        generalRepository.save(t);
        return successCustomResponse(true);
    }


    public BaseDTO<R> loadById(I id) {
        T t = generalRepository.findById(id).orElseThrow(
                () -> applicationException.createApplicationException(ExceptionEnum.NOTFOUND, HttpStatus.NOT_FOUND)
        );
        R r = generalMapper.toResponseModel(t);
        return successResponse(r);
    }

    public BaseDTO<List<R>> allById(List<I> idList) {
        List<R> rs = generalMapper.toResponseModel(generalRepository.findAllById(idList));
        return successListResponse(rs);
    }

    public BaseDTO<Boolean> existsById(I id) {
        boolean exists = generalRepository.existsById(id);
        return successCustomResponse(exists);
    }

    public BaseDTO<List<R>> getAll() {
        List<R> rs = generalMapper.toResponseModel(generalRepository.findAll());
        return successListResponse(rs);
    }


    public BaseDTO<PageDTO<List<R>>> getByPagination(Integer page, Integer pageSize) {
        Page<T> pagination = generalRepository.findAll(applicationPagination.pagination(page, pageSize, "id"));
        List<R> rs = generalMapper.toResponseModel(pagination.toList());
        return successPageResponse(rs, pagination);
    }

    public BaseDTO<List<R>> filter(S s) {
        T t = generalMapper.requestToEntity(s);
        t.setDeleted(false);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("deleted", exact());
        Example<T> filter = Example.of(t, exampleMatcher);
        List<R> model = generalMapper.toResponseModel(generalRepository.findAll(filter, Sort.by("id")));
        return successListResponse(model);
    }

    public BaseDTO<Long> count() {
        long count = generalRepository.count();
        return successCustomResponse(count);
    }


    //...Response Methodes................................................................................................................................................

    public BaseDTO<R> successResponse(R o) {
        return BaseDTO.<R>builder().data(o)
                .resultCode(successResource().getResultCode())
                .resultMessage(successResource().getResultMessage())
                .status(ResultStatus.SUCCESS).build();
    }

    public static <G> BaseDTO<G> successCustomResponse(G o) {
        return BaseDTO.<G>builder().data(o)
                .resultCode(successResource().getResultCode())
                .resultMessage(successResource().getResultMessage())
                .status(ResultStatus.SUCCESS).build();
    }

    public static <G> BaseDTO<List<G>> successCustomListResponse(List<G> o) {
        return BaseDTO.<List<G>>builder().data(o)
                .resultCode(successResource().getResultCode())
                .resultMessage(successResource().getResultMessage())
                .status(ResultStatus.SUCCESS).build();
    }

    public BaseDTO<List<R>> successListResponse(List<R> o) {
        return BaseDTO.<List<R>>builder().data(o)
                .resultCode(successResource().getResultCode())
                .resultMessage(successResource().getResultMessage())
                .status(ResultStatus.SUCCESS).build();
    }

    public BaseDTO<PageDTO<List<R>>> successPageResponse(List<R> o, Page<T> page) {
        PageDTO<List<R>> pageDTO = PageDTO.<List<R>>builder().totalElement(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .pageSize(page.getSize()).object(o).build();
        return BaseDTO.<PageDTO<List<R>>>builder().data(pageDTO)
                .resultCode(successResource().getResultCode())
                .resultMessage(successResource().getResultMessage())
                .status(ResultStatus.SUCCESS).build();
    }

}
