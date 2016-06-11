# DemoT3h
Demo Hướng dẫn lập trình Android

#Ghi chú
- Để active module trong project:
 - 1. Vào file settings.gradle
 - 2. thêm tên module cần active theo cú pháp:  ':module_name'. 
   - Ví dụ: include ':app',':demodbprovider'


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
> database.delete(Country.TABLE_NAME, Country.COL_NAME_EN + " = '" + nameENLastest + "'", null );
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
 
#Bài 3: Làm quen với csdl sqlite (tt)
- Nội dung:
 - Xây dựng project quản lý danh sách quốc gia theo mô hình
 - Mô hình gợi ý: UI (Activity, Fragment) <--> IDataprovider <--implement-- DataProvder --> DAO --> DB

- Các vấn đề cần lưu ý:
 - Sử dụng interface để tạo ra nhiều hình thức đối tượng khác nhau (Provider 1 truy cập DB, Provider2 tạo dump DB)
 - Các phương thức mapContentValues, fetch, fetchAll dùng để sử dụng chung cho nhiều phương thức trong DAO

- Bài tập:
 - Hoàn thành code tại các vị trí có từ khóa //TODO:
 - Fix bug nếu có.
 
- Source: demodbprovider/  

#Bài 5: Menu
- Nội dung:
 - Menu có nhiều loại, thường sử dụng 2 loại chính: 
   + Option Menu: là menu chính trên một activity.
   + ContextMenu: menu ngữ cảnh, nghĩa là nó sẽ xuất hiện khi thao tác với một item nào đó. Ví dụ: nhấn giữ lâu vào 1 item trên listView --> xuất hiện menu: xóa hoặc cập nhật. Có 2 loại contextMenu:
     - Floating ContextMenu --> single choice
     - Action mode --> multichoice
	 
- Các vấn đề cần lưu ý:
  - Phân biệt thanh ActionBar và thanh ActionMode.
    + Action Bar chứa option menu
    + Action mode chứa context menu.
  - Các vấn đề khi sử dụng option menu: 
    + Bước 1: create --> onCreateOptionsMenu()
    + Bước 2: bắt sự kiện --> onOptionsItemSelected()

  - các vấn đề khi sử dụng floating menu:
    + Bước 1: create -->  onCreateContextMenu()
    + Bước 2: bắt sự kiện --> onContextItemSelected(). Để lấy được position --> getContextMenuInfo()
    + Bước 3: (chỉ áp dụng cho contextMenu) đăng kí contextMenu cho View cần sử dụng
	
	
  - Các vấn đề khi sử dụng contextMenu cho ListView:
    + setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL)
    + setMultiChoiceModeListener()
    + Các method trong MultiChoiceModeListener() và sử dụng khi nào.
    + Tạo điểm nhấn cho item được chọn --> statelist
    + Để lấy được số lượng item được chọn --> listView.getCheckedItemCount()
    + Để lấy được position của các item được chọn --> listView.getCheckedItemPositions(). Đây là một mảng chứa n phần tử tương ứng với các item được chọn. Để lấy ra position --> keyAt(index)
    + Để tương tác với action mode (setTitle, ...) --> thông qua đối tượng ActionMode
    
  - Lưu ý: Khi sử dụng actionMode --> ko nên sử dụng kèm floating menu 
  
- Bài tập:
 - Fix bug TODO
 - Áp dụng vào tập thêm, xóa, sửa countryList
 
- Xem trước:
 - Xem trước SearchView.         
- Source:
 -  /app/src/main/java/dzumi/app/demo/demot3h/modules/user_interface/menu

[res]:https://drive.google.com/file/d/0B4o7SM4PhfqWV1lmRnRGc2tWbEE/view?usp=sharing
