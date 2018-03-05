package aligorji.ir.mylocal;


import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.widget.Toast;

import java.util.Locale;

public class App extends Application
{

    private static App _INSTANCE = null;

    public static App getInstance()
    {
        return _INSTANCE;
    }

    @Override
    public void onCreate()
    {
        _INSTANCE = this;



        super.onCreate();

        final String lng = DAL.get();

        Toast.makeText(this, "Created set " + lng, Toast.LENGTH_SHORT).show();

        restartInLocale(lng == null ? "en" : lng);

    }

    public static void restartInLocale(String languageToLoad)
    {

        //String languageToLoad  = "fa";

        Locale locale = new Locale(languageToLoad);

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            config.setLocale(locale);
        }
        else
        {
            config.locale = locale;
        }
        Resources resources = App.getInstance().getApplicationContext().getResources();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        //recreate();
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();

        Toast.makeText(this, "onTrimMemory"    , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);

        Toast.makeText(this, "onTrimMemory " + level, Toast.LENGTH_SHORT).show();
    }
}
