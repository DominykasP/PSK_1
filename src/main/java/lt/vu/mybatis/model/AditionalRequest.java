package lt.vu.mybatis.model;

public class AditionalRequest {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.ADITIONAL_REQUEST.ID
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.ADITIONAL_REQUEST.REQUEST
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    private String request;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.ADITIONAL_REQUEST.ID
     *
     * @return the value of PUBLIC.ADITIONAL_REQUEST.ID
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.ADITIONAL_REQUEST.ID
     *
     * @param id the value for PUBLIC.ADITIONAL_REQUEST.ID
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.ADITIONAL_REQUEST.REQUEST
     *
     * @return the value of PUBLIC.ADITIONAL_REQUEST.REQUEST
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    public String getRequest() {
        return request;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.ADITIONAL_REQUEST.REQUEST
     *
     * @param request the value for PUBLIC.ADITIONAL_REQUEST.REQUEST
     *
     * @mbg.generated Mon Apr 20 21:45:59 EEST 2020
     */
    public void setRequest(String request) {
        this.request = request;
    }
}