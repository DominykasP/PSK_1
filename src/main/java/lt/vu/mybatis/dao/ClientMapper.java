package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.Client;
import org.mybatis.cdi.Mapper;

@Mapper
public interface ClientMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CLIENT
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CLIENT
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    int insert(Client record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CLIENT
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    Client selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CLIENT
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    List<Client> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CLIENT
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    int updateByPrimaryKey(Client record);
}