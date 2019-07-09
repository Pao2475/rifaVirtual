/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.sql.*;
/**
 *
 * @author USUARIO
 */
public class Conexion 
{
    private static String JDBCdriver = "com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/rifavirtual?useSSL=false";
    private static String user="root";
    private static String pass="";
    private static Driver driver=null;
    
    //synchronized sirve para que solo un hilo utilize este metodo a la vez
    public static synchronized Connection getConnection() throws SQLException
    {
        if (driver==null) 
        {
            try {
                Class jdbcDriverClass = Class.forName(JDBCdriver);
                driver = (Driver)jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } 
            catch(ClassNotFoundException e1)
            {
                System.out.println("ClassNotFoundException "+e1.getMessage());
            }
            catch (InstantiationException e2)
            {
               System.out.println("InstantiationException "+e2.getMessage());
            }
            catch (IllegalAccessException e3)
            {
                    System.out.println("IllegalAccessException "+e3.getMessage());
            }
            catch (Exception e) 
            {
                System.out.println("Fallo al cargar la conexión");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(url, user, pass);
    }
    
    public static void close(ResultSet rs)
    {
        try {
            if (rs!=null) 
            {
                rs.close();
            }
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public static void close(PreparedStatement st)
    {
        try {
            if (st!=null) 
            {
                st.close();
            }
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public static void close(Connection conn)
    {
        try {
            if (conn!=null) 
            {
                conn.close();
            }
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}
