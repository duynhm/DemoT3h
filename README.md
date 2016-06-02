# DemoT3h
Demo Hướng dẫn lập trình Android

[Tài liệu Bài Tài Nguyên Ứng Dụng][res]

#Module 3
#Bài 1: Làm quen với csdl sqlite
- Tạo file country.db bằng tool SQLite expert
- Tạo project android
    - Tạo thư mục asset: new --> folder --> asset
    - Copy file country.db vào folder asset
    - Code load data từ country.db lên listview. Xem source: /app/src/main/java/dzumi/app/demo/demot3h/modules/storage/database/

#Bài 2: Làm quen với csdl sqlite (tt)
- Nội dung:
  - Demo Sample 3 Query data trong file country.db từ bài trước --> insert vào countryDB tạo bằng javaCode.
  - Demo Sample 2 Thực hiện thêm, xóa, sửa data trong country.db từ bài trước.
- Các điểm cần lưu ý:
  - Sample 2:
    + Để truyền giá trị (thao tác insert, update) sử dụng đối tượng ContentValues
    + Trong mệnh đề where, để so sánh các điều kiện, cần chú ý kiểu dữ liệu text cần có ''. Ví dụ: 
>database.delete(Country.TABLE_NAME, Country.COL_NAME_EN + " = '" + nameENLastest + "'", null );
  - Sample 3:
    + Tạo lớp kế thừa từ SQLiteOpenHelper để quản lý csdl sqlite
    + Phương thức khởi tạo cần truyền DBName, DBVersion -> Tạo Database tại phương thức này nếu DBName chưa tồn tại.
      - Đường dẫn lưu trữ DB: data/data/package_name/databases/
    + Phương thức onCreate được dùng để tạo các Table có trong DB
    + Phương thức onUpgrade được dùng để tạo ra các sự thay đổi khi DBVersion thay đổi.
    + Lưu ý: Lớp kế thức từ SQLiteOpenHelper và các phương thức chỉ dùng để quản lý cấu trúc DB, ko quản lý data trong DB.
    
- Bài tập: 
  + Dựa vào Sample 3: log các id từ phương thức insert xem có được tự tạo hay không?
  + Dựa vào Sample 3: tối ưu code
  + Dựa vào Sample 2,3: thực hiện hoàn chỉnh chức năng thêm, sửa 1 country được chọn.
 
- Source: /app/src/main/java/dzumi/app/demo/demot3h/modules/storage/database/ 
 
[res]:https://drive.google.com/file/d/0B4o7SM4PhfqWV1lmRnRGc2tWbEE/view?usp=sharing
