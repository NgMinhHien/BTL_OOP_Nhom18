public class Student {
    String ten;
    int doanVien;
    int dangVien;
    int dongPhi;
    String phongTrao;
    String khenThuong;

    public Student( int Id, String ten, int doanVien, int dangVien, int dongPhi, String phongTrao, String khenThuong) {
        this.ten = ten;
        this.doanVien = doanVien;
        this.dangVien = dangVien;
        this.dongPhi = dongPhi;
        this.phongTrao = phongTrao;
        this.khenThuong = khenThuong;
    }

    public Student() {

    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getDoanVien() {
        return doanVien;
    }

    
    public void setDoanVien(int doanVien) {
        this.doanVien = doanVien;
    }

    public int getDangVien() {
        return dangVien;
    }

    public void setDangVien(int dangVien) {
        this.dangVien = dangVien;
    }

    public int getDongPhi() {
        return dongPhi;
    }

    public void setDongPhi(int dongPhi) {
        this.dongPhi = dongPhi;
    }

    public String getPhongTrao() {
        return phongTrao;
    }

    public void setPhongTrao(String phongTrao) {
        this.phongTrao = phongTrao;
    }

    public String getKhenThuong() {
        return khenThuong;
    }

    public void setKhenThuong(String khenThuong) {
        this.khenThuong = khenThuong;
    }
}