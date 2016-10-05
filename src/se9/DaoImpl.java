package se9;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by ksu on 03.10.2016.
 */
public class DaoImpl implements DaoInt {
    static DbHelper dbHelper = DbHelper.getInstance();

    @Override
    public void selectAll(String table) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = dbHelper.getConnection().createStatement();
            String sql = String.format("SELECT * FROM %s", table);
            resultSet = statement.executeQuery(sql);
            view(resultSet, table);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeConnection();
            DbHelper.closeResource(statement);
        }
    }

    @Override
    public void select(String table, Object column, Object condition) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = String.format("SELECT * FROM %s WHERE %s = ?", table, column);
            preparedStatement = dbHelper.getConnection().prepareStatement(sql);
            preparedStatement.setObject(1, condition);

            resultSet = preparedStatement.executeQuery();
            view(resultSet, table);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeConnection();
            DbHelper.closeResource(preparedStatement);
        }
    }

    @Override
    public void insert(String table, String new_values) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = dbHelper.getConnection().createStatement();
            String sql = String.format("INSERT INTO %s VALUES(%s)", table, new_values);
            resultSet = statement.executeQuery(sql);
            selectAll(table);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeConnection();
            DbHelper.closeResource(statement);
        }
    }

    @Override
    public void update(String table, String column, Object new_value, Object old_value) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", table, column, column);
            preparedStatement = dbHelper.getConnection().prepareStatement(sql);
            preparedStatement.setObject(1, new_value);
            preparedStatement.setObject(2, old_value);

            resultSet = preparedStatement.executeQuery();
            select(table, column, new_value);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeConnection();
            DbHelper.closeResource(preparedStatement);
        }
    }

    @Override
    public void delete(String table, String column, Object condition) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = String.format("DELETE FROM %s WHERE %s=?", table, column);
            preparedStatement = dbHelper.getConnection().prepareStatement(sql);
            preparedStatement.setObject(1, condition);

            resultSet = preparedStatement.executeQuery();
            selectAll(table);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeConnection();
            DbHelper.closeResource(preparedStatement);
        }
    }

    public void view(ResultSet resultSet, String table) {
        StringBuilder result = new StringBuilder();
        switch (table) {
            case "CUSTOMER":
                try {
                    while (resultSet.next()) {
                        int customer_id = resultSet.getInt("customer_id");
                        String first_name = resultSet.getString("first_name");
                        String last_name = resultSet.getString("last_name");
                        int card_number = resultSet.getInt("card_number");
                        result.append(String.format("%d %s %s %d\n", customer_id, first_name, last_name, card_number));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "CUSTOMER_ORDER":
                try {
                    while (resultSet.next()) {
                        int order_id = resultSet.getInt("order_id");
                        Date date_order = resultSet.getDate("date_order");
                        Integer customer_id = resultSet.getInt("customer_id");
                        result.append(String.format("%d %tD %d\n", order_id, date_order, customer_id));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            case "ORDER_PRODUCT":
                try {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        int order_id = resultSet.getInt("order_id");
                        int product_id = resultSet.getInt("product_id");
                        result.append(String.format("%d %d %d\n", id, order_id, product_id));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            case "PRODUCT":

                try {
                    while (resultSet.next()) {
                        int product_id = resultSet.getInt("product_id");
                        String name_product = resultSet.getString("name_product");
                        int cost_product = resultSet.getInt("cost_product");
                        result.append(String.format("%d %s %d\n", product_id, name_product, cost_product));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                default:table="Invalid table";
                    break;

        }
        System.out.println(result);
    }
}
