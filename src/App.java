import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        Scanner scanner = new Scanner(System.in);

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
                    scanner.nextLine();
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
                    System.out.println("Da them sinh vien.");
                    break;
                case 2:
                    System.out.print("Nhap ten sinh vien can sua: ");
                    String oldName = scanner.nextLine();
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
                    System.out.println("Da sua thong tin sinh vien.");
                    break;
                case 3:
                    System.out.print("Nhap ten sinh vien can xoa: ");
                    String nameToDelete = scanner.nextLine();
                    management.deleteStudent(nameToDelete);
                    System.out.println("Da xoa sinh vien.");
                    break;
                case 4:
                    System.out.print("Nhap ten sinh vien can tim: ");
                    String nameToSearch = scanner.nextLine();
                    Student foundStudent = management.searchStudent(nameToSearch);
                    if (foundStudent != null) {
                        System.out.println("Da tim thay sinh vien: " + foundStudent.ten);
                    } else {
                        System.out.println("Khong tim thay sinh vien.");
                    }
                    break;
                case 5:
                    int countSLDoanVien = management.countSLDoanVien();
                    System.out.println("So luong Doan vien: " + countSLDoanVien);
                    break;
                case 6:
                    int countSLDangVien = management.countSLDangVien();
                    System.out.println("So luong Dang vien: " + countSLDangVien);
                    break;
                case 0:
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
}

class StudentManagement {
    List<Student> students = new ArrayList<>();
    String url = "jdbc:mysql://localhost:3306/myDatabase";
    String user = "root";
    String password = "1234";

    void addStudent(Student student) {
        students.add(student);

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO students (name, is_doan_vien, is_dang_vien, has_paid_fee, phong_trao, khen_thuong) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, student.ten);
            statement.setBoolean(2, student.doanVien);
            statement.setBoolean(3, student.dangVien);
            statement.setBoolean(4, student.dongPhi);
            statement.setString(5, student.phongTrao);
            statement.setString(6, student.khenThuong);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
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
            }
        }
    }

    void deleteStudent(String ten) {
        students.removeIf(student -> student.ten.equals(ten));
    }

    Student searchStudent(String ten) {
        for (Student student : students) {
            if (student.ten.equals(ten)) {
                return student;
            }
        }
        return null;
    }

    int countSLDoanVien() {
        int count = 0;
        for (Student student : students) {
            if (student.doanVien) {
                count++;
            }
        }
        return count;
    }

    int countSLDangVien() {
        int count = 0;
        for (Student student : students) {
            if (student.dangVien) {
                count++;
            }
        }
        return count;
    }
}