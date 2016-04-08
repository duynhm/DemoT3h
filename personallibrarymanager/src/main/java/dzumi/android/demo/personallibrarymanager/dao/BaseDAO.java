package dzumi.android.demo.personallibrarymanager.dao;

import android.content.ContentResolver;
import android.content.Context;

/**
 * Created by Dzumi on 3/28/2016.
 */
public abstract class BaseDAO {
    ContentResolver mContentResolver;
    Context mContext;
    public BaseDAO(Context context){
        mContext = context;
        mContentResolver = mContext.getContentResolver();
    }


}
