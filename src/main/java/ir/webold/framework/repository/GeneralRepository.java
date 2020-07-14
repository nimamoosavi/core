package ir.webold.framework.repository;

import ir.webold.framework.domain.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


public interface GeneralRepository<T extends BaseEntity<I>, I extends Serializable> extends JpaRepository<T, I> {
}
