package se9;

/**
 * Created by ksu on 03.10.2016.
 */
public interface DaoInt {
    public void selectAll(String table);

    public void select(String table, Object condition1, Object condition2);

    public void insert(String table, String new_values);

    public void update(String table, String column, Object old_value, Object new_value);

    public void delete(String table, String column, Object condition);
}
