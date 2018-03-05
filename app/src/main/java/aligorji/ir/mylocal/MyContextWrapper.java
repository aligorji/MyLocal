package aligorji.ir.mylocal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

public class MyContextWrapper extends ContextWrapper
{

    public MyContextWrapper(Context base)
    {
        super(base);
    }

    public static ContextWrapper wrap(Context context, String language)
    {
        Configuration config = context.getResources().getConfiguration();
        Locale sysLocale = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            sysLocale = getSystemLocale(config);
        }
        else
        {
            sysLocale = getSystemLocaleLegacy(config);
        }
        if (!language.equals("") && !sysLocale.getLanguage().equals(language))
        {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                setSystemLocale(config, locale);
            }
            else
            {
                setSystemLocaleLegacy(config, locale);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            {
                context = context.createConfigurationContext(config);
            }
            else
            {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }
        }
        return new MyContextWrapper(context);
    }

    public static Locale getSystemLocaleLegacy(Configuration config)
    {
        return config.locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getSystemLocale(Configuration config)
    {
        return config.getLocales().get(0);
    }

    public static void setSystemLocaleLegacy(Configuration config, Locale locale)
    {
        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static void setSystemLocale(Configuration config, Locale locale)
    {
        config.setLocale(locale);
    }

    //=================================

    //https://bhavyanshu.me/tutorials/provide-multiple-language-support-in-your-android-app/08/20/2015

    /*public void go()
    {
        Configuration config = getBaseContext().getResources().getConfiguration();
        String lang = "";
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }*/
}