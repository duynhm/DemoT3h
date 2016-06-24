# Các bước tạo chức năng Search sử dụng SearchView
 - Bước 1: tạo activity
 - Bước 2: tạo layout gồm: listView
 - Bước 3: tạo menu có itemMenu chứa actionViewClass --> SearchView
 - Bước 4: findViewByID ListView, initData, setAdapter --> build
 - Bước 5: createOptionMenu, findId, getActionViewClass (SearchView) từ itemMenu
 - Bước 6: searchView.setOnQueryTextListener() --> bắt sự kiện khi gõ phím. Xử lý trong phương thức onQueryTextChange().
 - Bước 7: override getFilter trong Adapter
 - Bước 8: tìm kiếm item trong phương thức performFiltering()
 - Bước 9: show kết quả trong phương thức publishResults()

## Lưu ý:
 - Khi tìm kiếm cần bật UpperCase với cả item data và từ khóa tìm kiếm.
 - Tìm kiếm theo list dataOriginal
 - Display theo list data
 - Khi publishResults cần clear data và add lại dữ liệu mới, không được gắn bằng:     data = (List<String>) results.values;