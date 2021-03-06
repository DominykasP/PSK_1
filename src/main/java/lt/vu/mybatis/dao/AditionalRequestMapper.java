package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.AditionalRequest;
import org.mybatis.cdi.Mapper;

@Mapper
public interface AditionalRequestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ADITIONAL_REQUEST
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ADITIONAL_REQUEST
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    int insert(AditionalRequest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ADITIONAL_REQUEST
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    AditionalRequest selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ADITIONAL_REQUEST
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    List<AditionalRequest> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ADITIONAL_REQUEST
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    int updateByPrimaryKey(AditionalRequest record);
}