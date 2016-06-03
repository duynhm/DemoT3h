package dzumi.app.demo.demodbprovider.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dzumi.app.demo.demodbprovider.R;
import dzumi.app.demo.demodbprovider.model.Country;


/**
 * Created by Dzumi on 5/23/2016.
 */
public class CountryAdapter extends ArrayAdapter<Country>{
    Context mContext;
    int mResource;
    List<Country> mCountries;
    public CountryAdapter(Context context, int resource,
                          List<Country> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mCountries = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            //
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //
            convertView = layoutInflater.inflate(mResource, parent, false);

            //
            viewHolder = new ViewHolder();
            //
            viewHolder.tvNameEn = (TextView) convertView.findViewById(R.id.tvNameEn);
            viewHolder.tvNameVi = (TextView) convertView.findViewById(R.id.tvNameVi);
            viewHolder.ivFlag = (ImageView) convertView.findViewById(R.id.ivFlag);

            //
            convertView.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) convertView.getTag();

        //
        viewHolder.tvNameEn.setText(mCountries.get(position).getNameEn());
        viewHolder.tvNameVi.setText(mCountries.get(position).getNameVi());

        return convertView;
    }

    class ViewHolder {
        TextView tvNameEn;
        TextView tvNameVi;
        ImageView ivFlag;
    }
}
