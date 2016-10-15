# Bài 6: AsyncTask - Thread - Handler
 - Các điểm cần nắm:
   + Một ứng dụng Android có
     - 1 Luồng chính, gọi là Main Thread / UI Thread.
     - n luồng khác dùng để xử lý một chức năng nào đó. Ví dụ: kết nối server, download file,...
   + Chỉ có Main Thread mới được phép tương tác với UI. Ví dụ: cập nhật listview, xuất thông báo.
   + Để Thread có thể tương tác được với Main Thread --> sử dụng Handler để nhận thông báo.
   + Handler là một thành phần nằm trên Main Thread nên có thể tương tác được với giao diện.
   + Android giới thiệu Asynctask thực hiện cả 2 nhiệm vụ: background và tương tác UI.
   + Asynctask --> execute -> doInBackGround()  --> onPostExecute().
                              doInBackGround()  <--> onProgressUpdate()
   + Asynctask chỉ được execute một lần, sau khi nhiệm vụ thành công. Asyntask sẽ hết tác dụng, nếu cần thực hiện lại công việc đó --> new 1 đối tượng Asynctask mới.

   ![asynctask]

 - Bài tập:
   + Tham khảo hướng dẫn và thực hiện mini game: http://www.edu4java.com/en/androidgame/androidgame1.html
   + fix bug //TODO

 - [Source][source]: ./modules/thread

[asynctask]:https://github.com/duynhm/DemoT3h/blob/master/guide/img/Asyntask.PNG
[source]: https://github.com/duynhm/DemoT3h/tree/master/app/src/main/java/dzumi/app/demo/demot3h/modules/thread
