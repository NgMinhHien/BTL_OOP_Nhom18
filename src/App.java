import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class App {
    static String url = "jdbc:mysql://localhost:3306/ds_sinh-vien";
    static String user = "root";
    static String password = "1234";

    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Da ket noi");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        while (true) {
            System.out.println("1. Them sinh vien");
            System.out.println("2. Sua thong tin sinh vien");
            System.out.println("3. Xoa sinh vien");
            System.out.println("4. Tim kiem sinh vien");
            System.out.println("5. Thong ke so luong Doan vien");
            System.out.println("6. Thong ke so luong Dang vien");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhap ten sinh vien: ");
                    String ten = scanner.nextLine();
                    System.out.print("La Doan vien? (y/n): ");
                    String doanVienInput = scanner.nextLine();
                    boolean doanVien = doanVienInput.equalsIgnoreCase("y");
                    System.out.print("La Dang vien? (y/n): ");
                    String dangVienInput = scanner.nextLine();
                    boolean dangVien = dangVienInput.equalsIgnoreCase("y");
                    System.out.print("Da dong phi? (y/n): ");
                    String dongPhiInput = scanner.nextLine();
                    boolean dongPhi = dongPhiInput.equalsIgnoreCase("y");
                    System.out.print("Tham gia phong trao (neu co): ");
                    String phongTrao = scanner.nextLine();
                    System.out.print("Khen thuong (neu co): ");
                    String khenThuong = scanner.nextLine();

                    Student newStudent = new Student();

                    newStudent.ten = ten;
                    newStudent.doanVien = doanVien;
                    newStudent.dangVien = dangVien;
                    newStudent.dongPhi = dongPhi;
                    newStudent.phongTrao = phongTrao;
                    newStudent.khenThuong = khenThuong;

                    management.addStudent(newStudent);
                    break;
                case 2:
                    System.out.print("Nhap ten sinh vien can sua: ");
                    String oldName = scanner.nextLine();
                    System.out.println("");
                    System.out.print("Nhap ten sinh vien moi: ");
                    String newName = scanner.nextLine();
                    System.out.print("La Doan vien? (y/n): ");
                    String doanVienInput2 = scanner.nextLine();
                    boolean newDoanVien = doanVienInput2.equalsIgnoreCase("y");
                    System.out.print("La Dang vien? (y/n): ");
                    String dangVienInput2 = scanner.nextLine();
                    boolean newDangVien = dangVienInput2.equalsIgnoreCase("y");
                    System.out.print("Da dong phi? (y/n): ");
                    String dongPhiInput2 = scanner.nextLine();
                    boolean newDongPhi = dongPhiInput2.equalsIgnoreCase("y");
                    System.out.print("Tham gia phong trao (neu co): ");
                    String newPhongTrao = scanner.nextLine();
                    System.out.print("Khen thuong (neu co): ");
                    String newKhenThuong = scanner.nextLine();

                    Student newInfo = new Student();
                    newInfo.ten = newName;
                    newInfo.doanVien = newDoanVien;
                    newInfo.dangVien = newDangVien;
                    newInfo.dongPhi = newDongPhi;
                    newInfo.phongTrao = newPhongTrao;
                    newInfo.khenThuong = newKhenThuong;

                    management.editStudent(oldName, newInfo);
                    break;
                case 3:
                    System.out.print("Nhap ten sinh vien can xoa: ");
                    String nameToDelete = scanner.nextLine();
                    management.deleteStudent(nameToDelete);
                    break;
                case 4:
                    System.out.print("Nhap ten sinh vien can tim: ");
                    String nameToSearch = scanner.nextLine();
                    Student foundStudent = management.searchStudent(nameToSearch);
                    if (foundStudent != null) {
                        System.out.println("Da tim thay sinh vien: " + foundStudent.toString());
                    } else {
                        System.out.println("Khong tim thay sinh vien.");
                    }
                    System.out.println("");
                    break;
                case 5:
                    int countSLDoanVien = management.countSLDoanVien();
                    System.out.println("So luong Doan vien: " + countSLDoanVien);
                    System.out.println("");
                    break;
                case 6:
                    int countSLDangVien = management.countSLDangVien();
                    System.out.println("So luong Dang vien: " + countSLDangVien);
                    System.out.println("");
                    break;
                case 0:
                    scanner.close();
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long thu lai.");
            }
        }
    }
}

class Student {
    String ten;
    boolean doanVien;
    boolean dangVien;
    boolean dongPhi;
    String phongTrao;
    String khenThuong;

    @Override
    public String toString() {
        return "Ten: " + ten + ", Doan vien: " + doanVien + ", Dang vien: " + dangVien + ", Dong phi: " + dongPhi + ", Phong trao: " + phongTrao + ", Khen thuong: " + khenThuong;
    }
}

class StudentManagement {
    List<Student> students = new ArrayList<>();
    String url = "jdbc:mysql://localhost:3306/ds_sinh-vien";
    String user = "root";
    String password = "1234";

    void addStudent(Student student) {
        students.add(student);

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO `ds_sinh-vien`.`sinhvien` (`TenSV`, `doanVien`, `dangVien`, `dongPhi`, `phongTrao`, `khenThuong`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, student.ten);
            statement.setBoolean(2, student.doanVien);
            statement.setBoolean(3, student.dangVien);
            statement.setBoolean(4, student.dongPhi);
            statement.setString(5, student.phongTrao);
            statement.setString(6, student.khenThuong);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                conn.commit();
                System.out.println("Da them sinh vien.");
                System.out.println("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void editStudent(String ten, Student newInfo) {
        for (Student student : students) {
            if (student.ten.equals(ten)) {
                student.doanVien = newInfo.doanVien;
                student.dangVien = newInfo.dangVien;
                student.dongPhi = newInfo.dongPhi;
                student.phongTrao = newInfo.phongTrao;
                student.khenThuong = newInfo.khenThuong;
    
                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    conn.setAutoCommit(false);
                    String sql = "UPDATE `ds_sinh-vien`.`sinhvien` SET `doanVien` = ?, `dangVien` = ?, `dongPhi` = ?, `phongTrao` = ?, `khenThuong` = ? WHERE `TenSV` = ?";
                    PreparedStatement statement = conn.prepareStatement(sql);
    
                    statement.setBoolean(1, student.doanVien);
                    statement.setBoolean(2, student.dangVien);
                    statement.setBoolean(3, student.dongPhi);
                    statement.setString(4, student.phongTrao);
                    statement.setString(5, student.khenThuong);
                    statement.setString(6, student.ten);
    
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        conn.commit();
                        System.out.println("Da sua thong tin sinh vien.");
                        System.out.println("");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    void deleteStudent(String ten) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);
            String sql = "DELETE FROM `ds_sinh-vien`.`sinhvien` WHERE `TenSV` = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, ten);
    
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                conn.commit();
                System.out.println("Da xoa sinh vien.");
                System.out.println("");
            } else {
                System.out.println("Khong tim thay sinh vien.");
                System.out.println("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    Student searchStudent(String ten) {
        Student student = null;
    
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);
            String sql = "SELECT * FROM `ds_sinh-vien`.`sinhvien` WHERE `TenSV` = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, ten);
    
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                conn.commit();
                student = new Student();
                student.ten = resultSet.getString("TenSV");
                student.doanVien = resultSet.getBoolean("doanVien");
                student.dangVien = resultSet.getBoolean("dangVien");
                student.dongPhi = resultSet.getBoolean("dongPhi");
                student.phongTrao = resultSet.getString("phongTrao");
                student.khenThuong = resultSet.getString("khenThuong");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return student;
    }

    int countSLDoanVien() {
        int count = 0;
    
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);
            String sql = "SELECT COUNT(*) FROM `ds_sinh-vien`.`sinhvien` WHERE `doanVien` = true";
            PreparedStatement statement = conn.prepareStatement(sql);
    
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                conn.commit();
                count = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    int countSLDangVien() {
        int count = 0;
    
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);
            String sql = "SELECT COUNT(*) FROM `ds_sinh-vien`.`sinhvien` WHERE `dangVien` = true";
            PreparedStatement statement = conn.prepareStatement(sql);
    
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                conn.commit();
                count = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
}