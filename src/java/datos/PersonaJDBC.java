/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.sql.*;
import java.util.*;

/**
 *
 * @author USUARIO
 */
public class PersonaJDBC 
{
    // se utilizara un PrepareStatement por lo que se puede utilizar 
    // los ? para definir parametros
    
    private java.sql.Connection userConn;
    private final String SQL_Update = "Update datos set nombre=?, cedula=?,telefono=? where Numero=?";
    private final String SQL_Delete = "Delete from persona where id = ?";
    private final String SQL_Select = "Select * from datos ORDER by CONVERT(Numero,int)";
    private final String SQL_Select2 = "Select * from datos where Cedula<>'0'";


    public PersonaJDBC() {}

    public PersonaJDBC(Connection userConn) {
        this.userConn = userConn;
    }
    
    
    public int update(String numero, String nombre, String cedula, String telefono)throws SQLException
    {
        Connection conn=null;
        PreparedStatement st= null;
        ResultSet rs = null;
        int rows=0;
        
        try {
            conn=(this.userConn != null) ? this.userConn : Conexion.getConnection();
            st=conn.prepareStatement(SQL_Update);
            st.setString(1, nombre);
            st.setString(2, cedula);
            st.setString(3, telefono);
            st.setString(4, numero);
            
            //g.MostrarInfo("Ejecutando query: "+SQL_Update);
            rows = st.executeUpdate();
            //g.MostrarInfo("Registros actualizados: "+rows);
        } 
        finally
        {
            Conexion.close(st);
            if (this.userConn == null) 
            {
                Conexion.close(conn);
            }
        }
        return rows;
    }
    
    public int delete(int id)throws SQLException
    {
        Connection conn=null;
        PreparedStatement st= null;
        ResultSet rs = null;
        int rows=0;
        
        try {
            conn=(this.userConn != null) ? this.userConn : Conexion.getConnection();
            st=conn.prepareStatement(SQL_Delete);
            st.setInt(1, id);
            //g.MostrarInfo("Ejecutando query: "+SQL_Delete);
            rows = st.executeUpdate();
           //g.MostrarInfo("Registros eliminados: "+rows);
        } 
        finally
        {
            Conexion.close(st);
            if (this.userConn == null) 
            {
                Conexion.close(conn);
            }
        }
        return rows;
    }
    
    public String select()throws SQLException
    {
        Connection conn=null;
        PreparedStatement st= null;
        ResultSet rs = null;
        String numeros="";
        //Persona per;
        try {
            conn=(this.userConn != null) ? this.userConn : Conexion.getConnection();
            st=conn.prepareStatement(SQL_Select);
            rs = st.executeQuery();
            while (rs.next()) 
            {          
                  numeros += rs.getString(1)+",";
            }
        }
        finally
        {
            Conexion.close(rs);
            Conexion.close(st);
            if (this.userConn == null) 
            {
                Conexion.close(conn);
            }
        }
        return numeros;
    }
    
    public String select2()throws SQLException
    {
        Connection conn=null;
        PreparedStatement st= null;
        ResultSet rs = null;
        String numeros="";
        //Persona per;
        try {
            conn=(this.userConn != null) ? this.userConn : Conexion.getConnection();
            st=conn.prepareStatement(SQL_Select2);
            rs = st.executeQuery();
            while (rs.next()) 
            {          
                  numeros += rs.getString(1)+",";
            }
        }
        finally
        {
            Conexion.close(rs);
            Conexion.close(st);
            if (this.userConn == null) 
            {
                Conexion.close(conn);
            }
        }
        return numeros;
    }
}
