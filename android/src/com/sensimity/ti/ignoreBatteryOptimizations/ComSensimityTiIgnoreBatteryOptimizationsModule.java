package com.sensimity.ti.ignoreBatteryOptimizations;

import org.appcelerator.titanium.TiApplication;

import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;


@Kroll.module(name="ComSensimityTiIgnoreBatteryOptimizations", id="com.sensimity.ti.ignoreBatteryOptimizations")
public class ComSensimityTiIgnoreBatteryOptimizationsModule extends KrollModule
{
	private static final String LCAT = "ComSensimityTiIgnoreBatteryOptimizationsModule";
	private static final boolean DBG = TiConfig.LOGD;

	public ComSensimityTiIgnoreBatteryOptimizationsModule()
	{
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
		Log.d(LCAT, "inside onAppCreate");
	}

    @Kroll.method
    public void requestIgnoreBatteryOptimizations() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            String pkg = TiApplication.getInstance().getApplicationContext().getPackageName();
            PowerManager pm = TiApplication.getInstance().getApplicationContext().getSystemService(PowerManager.class);

            if (!pm.isIgnoringBatteryOptimizations(pkg)) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + pkg));

                TiApplication.getInstance().getRootActivity().startActivity(intent);
            }
        }
    }
}

