package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CustomerDao {

    private Connection con;

    public CustomerDao() throws SQLException {
        this.con = new ConnectionFactory().getConnection();
        System.out.println("Conexão OK!");
    }

    public void insertCustomer(Customer c) throws SQLException {
    
    String sql = "insert into customer"
            + " (store_id, first_name, last_name, email, address_id, active)"
            + " values"
            + " (?, ?, ?, ?, ?, ?)";
    
    PreparedStatement pst = con.prepareStatement(sql);
    
    pst.setInt(1, c.getStore_id());
    pst.setString(2, c.getFirst_name());
    pst.setString(3, c.getLast_name());
    pst.setString(4, c.getEmail());
    pst.setInt(5, c.getAddress_id());
    pst.setInt(6, c.getActive());
    
    pst.execute();
    pst.close();
    
    }

    public void deleteCustomer(int id) throws SQLException {
        String sql = "delete from customer where customer_id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        pst.execute();
        pst.close();
        System.out.println("Delete OK!");
    }

    public void updateCustomer(int id, Customer c) throws SQLException {
        String sql = "update customer"
                + " set store_id=?, first_name=?, last_name=?, email=?, address_id=?, active=?"
                + " where customer_id=?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, c.getStore_id());
        pst.setString(2, c.getFirst_name());
        pst.setString(3, c.getLast_name());
        pst.setString(4, c.getEmail());
        pst.setInt(5, c.getAddress_id());
        pst.setInt(6, c.getActive());
        pst.setInt(7, id);

        pst.execute();
        pst.close();
        System.out.println("Update OK!");
    }

    public void showCustomers(DefaultTableModel tableModel) throws SQLException {
        Statement st = con.createStatement();

        String query = "select * from customer"
                + " order by customer_id desc";

        ResultSet rs = st.executeQuery(query);
        
        
        
        tableModel.setRowCount(0);
        

        while (rs.next()) {
            Object[] rowData = new Object[]{
                rs.getInt("Customer_id"),
                rs.getInt("store_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getInt("address_id"),
                rs.getInt("active"),
                rs.getString("create_date"),
                rs.getString("last_update")
            };
            tableModel.addRow(rowData);
        }
        
        st.close();
        
    }

    public List<Customer> getCustomer() throws SQLException {
        List<Customer> lista = new ArrayList<Customer>();

        Statement st = con.createStatement();

        String query = "select * from customer"
                + " order by customer_id desc";

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            lista.add(new Customer(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getTimestamp(8), rs.getTimestamp(9)));
        }

        return lista;
    }
}
