package dzumi.app.demo.demot3h.modules.app_components.content_provider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demot3h.R;
import dzumi.app.demo.demot3h.modules.app_components.content_provider.model.Contact;
import dzumi.app.demo.demot3h.modules.app_components.content_provider.model.Phone;

public class DemoContactListActivity extends AppCompatActivity {
    private static final String TAG = "CONTACT";
    ListView listView;
    List<Contact> contacts;
    List<String> displayNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_contact_list);
        listView = (ListView) findViewById(R.id.listView);

        initData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, displayNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
//                        null, ContactsContract.Data._ID + "= ?",
                        null, ContactsContract.Data.RAW_CONTACT_ID + "= ?",
                        new String[]{contacts.get(position).get_id()+""},
                        null, null);
                if(cursor!= null){
                    if(cursor.moveToFirst()){
                        do{
                            //kiem tra mime_type --> record thuoc loai nao
                            String mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
                            switch (mimeType){
                                case ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE:
                                    Phone phone = new Phone();
                                    phone.setNumber(cursor.getString(
                                            cursor.getColumnIndex(
                                                    ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    phone.setType(cursor.getInt(
                                            cursor.getColumnIndex(
                                                    ContactsContract.CommonDataKinds.Phone.TYPE)));
                                    //TODO: chuyen type tu int --> string
                                    Log.d(TAG, "data: phone: " + phone.getNumber() + "/" + phone.getType() );
                                    contacts.get(position).addPhone(phone);
                                    break;
                                case ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE:
                                    //TODO: get thong tin email
                                    break;
                            }
                            //TODO: startActivity(detail contact)
                        }while (cursor.moveToNext());
                    }
                }
            }
        });
    }

    void initData(){
        //muốn truy xuất dữ liệu từ contentProvider --> content Resolver
        //TODO: chuyển phần lấy dữ liệu về DAO layer
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI,
                new String[]{ContactsContract.RawContacts._ID, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY},
                null,null,null,null);
        contacts = new ArrayList<>();
        displayNames = new ArrayList<>();
        if(cursor!= null){

            if(cursor.moveToFirst()){
                do {
                    Contact contact = new Contact();
                    contact.set_id(cursor.getLong(cursor.getColumnIndex(ContactsContract.RawContacts._ID)));
                    contact.setDisplayName(cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY)));

                    contacts.add(contact);
                    displayNames.add(contact.getDisplayName());
                }while (cursor.moveToNext());
            }
        }
    }
}
