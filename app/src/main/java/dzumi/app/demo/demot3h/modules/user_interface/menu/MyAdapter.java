package dzumi.app.demo.demot3h.modules.user_interface.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demot3h.R;

/**
 * Created by Dzumi on 6/13/2016.
 */
public class MyAdapter extends ArrayAdapter<String> {
    Context context;
    int layout;
    List<String> data; //display
    List<String> dataOriginal;//search
    public MyAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        layout =resource;
        data = objects;
        dataOriginal = new ArrayList<>(data);//?
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                //đi tìm kiếm item chứa từ khóa cần tìm (constraint)
                FilterResults filterResults = new FilterResults();

                List<String> temp = new ArrayList<>();
                //TODO: search UNICODE
                for (String s : dataOriginal) {
                    if(s.toUpperCase().contains(constraint.toString().toUpperCase()))
                        temp.add(s);
                }

                //sau khi tìm kiếm ==> trả kết quả cho publishResults
                filterResults.values = temp;
                filterResults.count = temp.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //show kết quả
//                data = (List<String>) results.values;
                data.clear();
                data.addAll((List<String>) results.values);
                notifyDataSetChanged();

            }
        };
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layout,parent,false);
        }
        ((TextView)convertView).setText(data.get(position));
        return convertView;
    }
}
