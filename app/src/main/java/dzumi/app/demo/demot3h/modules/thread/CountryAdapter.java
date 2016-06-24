package dzumi.app.demo.demot3h.modules.thread;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dzumi.app.demo.demot3h.R;

/**
 * Created by Dzumi on 6/24/2016.
 */
public class CountryAdapter extends ArrayAdapter<Country> {
    Context mContext;
    int resource;
    List<Country> countries;
    public CountryAdapter(Context context, int resource, List<Country> objects) {
        super(context, resource, objects);
        mContext = context;
        this.resource = resource;
        countries = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.ivFlag);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.imageView.setImageResource(countries.get(position).getFlag());
        viewHolder.textView.setText(countries.get(position).getName());
        return  convertView;
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
