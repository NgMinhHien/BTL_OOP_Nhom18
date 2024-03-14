CREATE SCHEMA `ds_sinh-vien` ;

CREATE TABLE `ds_sinh-vien`.`sinhvien` (
  `TenSV` CHAR(30) NOT NULL,
  `doanVien` TINYINT NOT NULL,
  `dangVien` TINYINT NOT NULL,
  `dongPhi` TINYINT NOT NULL,
  `phongTrao` VARCHAR(45) NULL,
  `khenThuong` VARCHAR(45) NULL,
  PRIMARY KEY (`TenSV`));