package aligorji.ir.mylocal;

import android.content.Context;
import android.content.SharedPreferences;


public class DAL
{
    public static void save( String lng)
    {
        SharedPreferences prefs = App.getInstance().getApplicationContext().getSharedPreferences("setting", Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = prefs.edit();
        edit.putString("lng", lng);
        edit.commit();
    }

    public static String get( )
    {
        SharedPreferences prefs = App.getInstance().getApplicationContext().getSharedPreferences("setting", Context.MODE_PRIVATE);
        return prefs.getString("lng", null);

    }
}
