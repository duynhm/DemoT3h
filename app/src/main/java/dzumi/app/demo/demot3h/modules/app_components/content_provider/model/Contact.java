package dzumi.app.demo.demot3h.modules.app_components.content_provider.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzumi on 6/6/2016.
 */
public class Contact {
    long _id;
    String displayName;
    List<Phone> phones;
    List<Email> emails;

    public Contact() {
        phones = new ArrayList<>();
        emails = new ArrayList<>();
    }

    public void addPhone(Phone phone){
        phones.add(phone);
    }

    public void addEmail(Email email){
        emails.add(email);
    }

    //TODO: thêm các thuộc tính khác còn lại của contact.
    //xem thêm: https://developer.android.com/reference/android/provider/ContactsContract.Data.html
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
}
