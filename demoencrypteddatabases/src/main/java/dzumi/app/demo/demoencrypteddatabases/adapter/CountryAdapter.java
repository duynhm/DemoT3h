package dzumi.app.demo.demoencrypteddatabases.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dzumi.app.demo.demoencrypteddatabases.R;
import dzumi.app.demo.demoencrypteddatabases.db.Country;

/**
 * Created by Dzumi on 3/19/2016.
 */
public class CountryAdapter extends ArrayAdapter<Country> {
    List<Country> mCountries;
    Context mContext;
    int mLayout;
    public CountryAdapter(Context context, int resource, List<Country> countries) {
        super(context, resource, countries);
        mContext = context;
        mLayout = resource;
        mCountries = countries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mLayout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.setInfo(mCountries.get(position));
        return convertView;
    }

    class ViewHolder{
        @Bind(R.id.tvNameEn)
        TextView tvNameEn;

        @Bind(R.id.tvNameVi)
        TextView tvNameVi;

        ViewHolder(View rootView){
            ButterKnife.bind(this,rootView);
        }

        void setInfo(Country country){
            tvNameEn.setText(country.getNameEn());
            tvNameVi.setText(country.getNameVi());
        }
    }
}
