import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.*;
import dbutil.DBUtil;

public class StudentDAO {
    public List<Student> getAllStudents()
    {
        List<Student> studentList = new ArrayList<Student>();
        try
        {
            //typical jdbc coding
            Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM product");
            while(rs.next())
            {
                Student student = new Studnet(rs.getString("prod_id"), rs.getString("prod_name"), rs.getInt("prod_price"));
                studentList.add(student);
            }
            DBUtil.closeConnection(conn);  //close connection
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return studentList;
    }

    //different query
    public Student getStudentByID(String studentID)
    {
        Student student = null;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE student_id = ?");
            ps.setString(1, studentID);
            ResultSet rs = ps.executeQuery();
            //iterate through result
            while(rs.next())
            {
                student = new Student(rs.getString("student_id"), rs.getString("student_name"), rs.getInt("prod_price"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return student;
    }

    public int addProduct(Student student)
    {
        //status displays 1 if successfully inserted data or error; successful or not
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO product VALUES(?,?,?)");
            //set parameters of query here but using the values for the product object
            ps.setString(1, student.getProductid());
            ps.setString(2, student.getProductName());
            ps.setInt(3, student.getProductPrice());
            status = ps.executeUpdate();  //if successful status should return 1
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    //updates a product already in the table
    public int updateStudent(Student student)
    {
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET prod_name=?, prod_price=? WHERE prod_id=?");
            //set parameters of query here but using the values for the product object
            ps.setString(1, student.getProductName());
            ps.setInt(2, student.getProductPrice());
            ps.setString(3, student.getProductid());
            status = ps.executeUpdate();  //if successful status should return 1
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    //deltes product already in the table
    public int deleteProduct(String productId)
    {
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM product where prod_id = ?");
            //set parameters of query here but using the values for the product object
            ps.setString(1, productId);
            status = ps.executeUpdate();  //if successful status should return 1

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }
}
