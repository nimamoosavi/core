package app.ladderproject.core.packages.jdbc;

import app.ladderproject.core.domain.dto.PageDTO;
import app.ladderproject.core.packages.crud.view.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @param <T> is the entity class
 * @param <I> is the IncrementalId Type Of Relation Data base
 * @author nima
 * @version 1.0.1
 * @apiNote this interface is the implementation of JpaRepository of Spring Data you can find know about it in {@link <a <a href="https://spring.io/projects/spring-data-jpa">...</a></a>}
 * and Used NameParameterJdbcTemplate And JdbcTemplate of Spring Framework For native Query.
 * you must create an interface and extend it and generate a bean of your interface
 */
public interface Jdbc<T, I extends Serializable> {

    /**
     * @param sql                is the native query for execute in Data Base
     * @param sqlParameterSource is the parameter source for executed parametric
     * @return List<Map < String, Object>> of the result of database
     */
    List<Map<String, Object>> queryForList(String sql, MapSqlParameterSource sqlParameterSource);

    /**
     * @param sql                is the native query for execute in Data Base
     * @param sqlParameterSource is the parameter source for executed parametric
     * @param tClass             the class you want cast it
     * @return List<T> the object that cast it
     */
    List<T> queryForList(String sql, MapSqlParameterSource sqlParameterSource, Class<T> tClass);

    /**
     * @param sql          is the native query for execute in Data Base
     * @param queryTimeOut is the time that you want method wait for response
     * @param tClass       the class you want cast it
     * @return List<T> the object that cast it
     */
    List<T> queryForList(String sql, int queryTimeOut, Class<T> tClass);

    /**
     * @param sql          is the native query for execute in Data Base
     * @param queryTimeOut is the time that you want method wait for response
     * @return List<Map < String, Object>> is the result of data Base
     */
    List<Map<String, Object>> queryForList(String sql, int queryTimeOut);

    /**
     * @param sql                is the native query for execute in Data Base
     * @param sqlParameterSource is the parameter source for executed parametric
     * @param tClass             the class you want cast it
     * @return the single result that result from data Base
     * @apiNote you must know if the result not been single the methode throw Runtime Exception
     */
    T query(String sql, MapSqlParameterSource sqlParameterSource, Class<T> tClass);

    /**
     * @param sql          is the native query for execute in Data Base
     * @param queryTimeOut is the time that you want method wait for response
     * @param tClass       the class you want cast it
     * @return T is the Object of result from a database
     */
    T query(String sql, int queryTimeOut, Class<T> tClass);

    /**
     * @param sql                is the native query for execute in Data Base
     * @param sqlParameterSource is the parameter source for executed parametric
     * @return Map<String, Object> the result of Data base
     * @apiNote you must know if the result not been a single result the methode throw Runtime Exception
     */
    Map<String, Object> query(String sql, MapSqlParameterSource sqlParameterSource);

    /**
     * @return the number of max rows in table
     */
    int maxRows();

    /**
     * @return the default Time out of Connection
     */
    int queryTimeOut();

    /**
     * @param namedParameterJdbcTemplate is the Object of Spring that you must use it for an executed query
     * @return the Object Time out of Connection
     */
    int queryTimeOut(NamedParameterJdbcTemplate namedParameterJdbcTemplate);

    /**
     * @param jdbcTemplate is the Object of Spring that you must use it for an executed query
     * @return the Object Time out of Connection
     */
    int queryTimeOut(JdbcTemplate jdbcTemplate);

    /**
     * @param t the Entity View Model that you must Add To Data Base
     * @return the Optional Of Entity that saves it in a database
     */
    T save(T t);

    /**
     * @param t the Entity View Model that you must Add To Data Base
     * @return the Entity that saves it in a database
     * @apiNote this method used SpringJpa
     */
    T update(T t);

    /**
     * @param tList the list of Entity that you must save it in Data base
     * @return the List Of Entity and their Ids
     * @apiNote this method used SpringJpa
     */
    List<T> saveAll(List<T> tList);

    /**
     * @param id the incrementalId of database Object
     * @return the Entity that saves it in a database
     * @apiNote used for fetch Data By IncrementalId And this method used SpringJpa
     */
    Optional<T> findById(I id);

    /**
     * @return the List Of Entities
     * @apiNote this method used SpringJpa
     */
    List<T> findAll();

    /**
     * @return the List Of Entities
     * @apiNote this method used SpringJpa
     */
    List<T> findAll(Query query);

    /**
     * @param page     the page number that you must fetch it
     * @param pageSize the page Size of that you needs to split Data
     * @apiNote this method used SpringJpa
     */
    PageDTO<List<T>> findAll(int page, int pageSize);

    /**
     * @param page     the page number that you must fetch it
     * @param pageSize the page Sizes of that you need to split Data
     * @param query   is the Object for query list
     * @return the List Of Entity from Response Of Data Base
     * @apiNote this method used SpringJpa
     */
    PageDTO<List<T>> findAll(int page, int pageSize, Query query);


    /**
     * @return the Number Of data
     * @apiNote this method used SpringJpa
     */
    long count();

    /**
     * @return the Number Of data
     * @apiNote this method used SpringJpa
     */
    long count(Query query);

    /**
     * @param id is the incrementalId of Object that you need too remove it from Data Base
     * @throws RuntimeException has throw if Delete Method Not Acceptable
     * @apiNote this method used SpringJpa
     */
    void deleteById(I id);
}
