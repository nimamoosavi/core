package ir.webold.framework.utility;


import ir.webold.framework.enums.exception.ExceptionEnum;
import ir.webold.framework.enums.SortEnum;
import ir.webold.framework.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ApplicationPagination {

    private final ApplicationException applicationException;

    @Autowired
    public ApplicationPagination(ApplicationException applicationException) {
        this.applicationException = applicationException;
    }

    @Value("${pagable.size.response}")
    private Integer pageSizeDefault;

    public Pageable pagination(Integer page, Integer pageSize) {
        return PageRequest.of(page - 1, pageSize != null ? pageSize : pageSizeDefault);
    }

    public Pageable pagination(Integer page, Integer pageSize, String sortField) {
        return PageRequest.of(page - 1, pageSize != null ? pageSize : pageSizeDefault, Sort.by(sortField));
    }

    public Pageable pagination(Integer page, Integer pageSize, String sortField, SortEnum sortEnum) {
        if (sortEnum.name().equals("asc")) {
            return PageRequest.of(page - 1, pageSize != null ? pageSize : pageSizeDefault, Sort.by(sortField).ascending());
        } else if (sortEnum.name().equals("desc")) {
            return PageRequest.of(page - 1, pageSize != null ? pageSize : pageSizeDefault, Sort.by(sortField).descending());
        } else {
            throw applicationException.createApplicationException(ExceptionEnum.INTERNALSERVER, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
