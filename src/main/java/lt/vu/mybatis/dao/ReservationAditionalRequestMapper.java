package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.ReservationAditionalRequest;
import org.mybatis.cdi.Mapper;

@Mapper
public interface ReservationAditionalRequestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.RESERVATION_ADITIONAL_REQUEST
     *
     * @mbg.generated Mon Apr 20 22:14:21 EEST 2020
     */
    int insert(ReservationAditionalRequest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.RESERVATION_ADITIONAL_REQUEST
     *
     * @mbg.generated Mon Apr 20 22:14:21 EEST 2020
     */
    List<ReservationAditionalRequest> selectAll();
}