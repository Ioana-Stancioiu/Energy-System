package entities;

public interface Entity {
    /**
     *
     * @return entity's id
     */
    int getId();

    /**
     *
     * @return entity's budget
     */
    long getBudget();

    /**
     * Sets a new value to entity's budget
     * @param budget the new budget
     */
    void setBudget(long budget);

    /**
     *
     * @return true id entity is bankrupt and false otherwise
     */
    boolean isBankrupt();

    /**
     * Sets the value of an entity's bankrupt status
     * @param status the new bankrupt status
     */
    void setBankrupt(boolean status);
}
