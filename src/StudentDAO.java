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
            Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM student");
            while(rs.next())
            {
                Student student = new Student(rs.getString("TenSV"), rs.getBoolean("doanVien"), rs.getBoolean("dangVien"), rs.getDouble("dongPhi"), rs.getString("phongTrao"), rs.getString("khenThuong"));
                studentList.add(student);
            }
            DBUtil.closeConnection(conn);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return studentList;
    }

    public Student getStudentByID(String studentID)
    {
        Student student = null;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE TenSV = ?");
            ps.setString(1, studentID);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                student = new Student(rs.getString("TenSV"), rs.getBoolean("doanVien"), rs.getBoolean("dangVien"), rs.getDouble("dongPhi"), rs.getString("phongTrao"), rs.getString("khenThuong"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return student;
    }

    public int addStudent(Student student)
    {
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO student(TenSV, doanVien, dangVien, dongPhi, phongTrao, khenThuong) VALUES(?,?,?,?,?,?)");
            ps.setString(1, student.getTenSV());
            ps.setBoolean(2, student.getDoanVien());
            ps.setBoolean(3, student.getDangVien());
            ps.setDouble(4, student.getDongPhi());
            ps.setString(5, student.getPhongTrao());
            ps.setString(6, student.getKhenThuong());
            status = ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    public int updateStudent(Student student)
    {
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE student SET doanVien=?, dangVien=?, dongPhi=?, phongTrao=?, khenThuong=? WHERE TenSV=?");
            ps.setBoolean(1, student.getDoanVien());
            ps.setBoolean(2, student.getDangVien());
            ps.setDouble(3, student.getDongPhi());
            ps.setString(4, student.getPhongTrao());
            ps.setString(5, student.getKhenThuong());
            ps.setString(6, student.getTenSV());
            status = ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    public int deleteStudent(String studentId)
    {
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM student where TenSV = ?");
            ps.setString(1, studentId);
            status = ps.executeUpdate();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }
}