package dzumi.app.demo.demoencrypteddatabases;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import dzumi.app.demo.demoencrypteddatabases.db.Country;
import dzumi.app.demo.demoencrypteddatabases.main.Encryptor;

public class EncryptedNameAdapter extends ResourceCursorAdapter {

    private String pw;
   
    public EncryptedNameAdapter(Context context, int layout, Cursor c,
                                    boolean autoRequery) {
        super(context, layout, c, autoRequery);
    }

    public EncryptedNameAdapter(Context context, int layout, Cursor c,
            int flags) {
        super(context, layout, c, flags);
    }

    // This class must know what the encryption key is for the DB before filling the list,
    // so this call must be made before the list is populated. The first call after the constructor works.
    public void setPW(String pww) {
        pw = pww;
    }

   
    @Override
      public View newView(Context context, Cursor cur, ViewGroup parent) {
       LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       return li.inflate(R.layout.my_list_entry, parent, false);
      }
   
    @Override
    public void bindView(View arg0, Context arg1, Cursor cursor) {
        // Get an encryptor/decryptor for our data.
           Encryptor enc = new Encryptor(pw);

        // Get the TextView we're placing the data into.
           TextView tvNameEn = (TextView)arg0.findViewById(R.id.tvNameEn);
        // Get the bytes from the cursor
           byte[] bNameEn = cursor.getBlob(cursor.getColumnIndex(Country.COLUMN_NAME_EN ));
        // Convert bytes to a string
           String nameEn = new String(bNameEn);

           try {
            // decrypt the string
               nameEn = enc.decrypt(nameEn);
           } catch(Exception e) {
            System.out.println(e.toString());

            // local holds the encrypted version at this point, fix it.

            // We’ll return an empty string for simplicity
            nameEn = new String("unknown");
           }
        tvNameEn.setText(nameEn);
    }

}