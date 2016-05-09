package dzumi.app.demo.demot3h.modules.user_interface.adapter_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dzumi.app.demo.demot3h.R;
import dzumi.app.demo.demot3h.model.Country;

/**
 * Created by Dzumi on 5/6/2016.
 */
public class CountryAdapterSimple2 extends BaseAdapter {

    Context context;
    int layoutRes;
    List<Country> countries;

    public CountryAdapterSimple2(Context context, int layoutRes,
                                 List<Country> countries) {
        this.context = context;
        this.layoutRes = layoutRes;
        this.countries = countries;
    }
    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


   /* @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //doi tuong layoutInflater lam nhiem vu "nhet" layout tu xml vao View trong java code
        ViewHolder viewHolder = null;
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //dai dien cho cai layoutRes
            convertView = layoutInflater.inflate(layoutRes, parent, false);
        }

        viewHolder = new ViewHolder();
        viewHolder.tvNameEn = (TextView) convertView.findViewById(R.id.tvNameEn);
        viewHolder.tvNameVi = (TextView) convertView.findViewById(R.id.tvNameVi);


        viewHolder.tvNameEn.setText(countries.get(position).getNameEn());
        viewHolder.tvNameVi.setText(countries.get(position).getNameVi());
        return convertView;
    }*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //doi tuong layoutInflater lam nhiem vu "nhet" layout tu xml vao View trong java code
        ViewHolder viewHolder = null;
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             //dai dien cho cai layoutRes
            convertView = layoutInflater.inflate(layoutRes, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvNameEn = (TextView) convertView.findViewById(R.id.tvNameEn);
            viewHolder.tvNameVi = (TextView) convertView.findViewById(R.id.tvNameVi);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvNameEn.setText(countries.get(position).getNameEn());
        viewHolder.tvNameVi.setText(countries.get(position).getNameVi());
        return convertView;
    }

    class ViewHolder{
        TextView tvNameEn;
        TextView tvNameVi;
    }
}
