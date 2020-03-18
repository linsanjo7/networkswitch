package com.playbox.studio.a3g4g;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.king.thread.nevercrash.NeverCrash;


public class ActivitySwitch extends AppCompatActivity {


    private InterstitialAd mInterstitialAd;
    private Context context1;
    private TelephonyManager tm1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Log.d("Jenly", Log.getStackTraceString(e));
//                e.printStackTrace();
                showToast(e.getMessage());


            }
        });
        MobileAds.initialize(this,
                "ca-app-pub-5984349740572515~6299673565");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5984349740572515/7463559266");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        network1();
    }
    private void showToast(final String text){

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mInterstitialAd.isLoaded())
            mInterstitialAd.show();
    }

    public void network1(){
        context1 = getApplicationContext();

        // Toast current Network Type
        tm1 = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String type1 = "UNKNOWN";
        switch (tm1.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS: type1 = "GPRS"; break;
            case TelephonyManager.NETWORK_TYPE_EDGE: type1 = "EDGE"; break;
            case TelephonyManager.NETWORK_TYPE_UMTS: type1 = "UMTS"; break;
            case TelephonyManager.NETWORK_TYPE_HSDPA: type1 = "HSDPA"; break;
            case TelephonyManager.NETWORK_TYPE_HSUPA: type1 = "HSUPA"; break;
            case TelephonyManager.NETWORK_TYPE_HSPA: type1 = "HSPA"; break;
            case TelephonyManager.NETWORK_TYPE_HSPAP: type1 = "HSPA+"; break;
            case TelephonyManager.NETWORK_TYPE_LTE: type1 = "LTE"; break;
            case TelephonyManager.NETWORK_TYPE_IDEN: type1 = "iDEN"; break;
            case TelephonyManager.NETWORK_TYPE_CDMA: type1 = "cmdaOne"; break;
            case TelephonyManager.NETWORK_TYPE_1xRTT: type1 = "CDMA2000 1xRTT"; break;
            case TelephonyManager.NETWORK_TYPE_EVDO_0: type1 = "CDMA2000 1xEV-DO Rev. 0"; break;
            case TelephonyManager.NETWORK_TYPE_EVDO_A: type1 = "CDMA2000 1xEV-DO Rev. A"; break;
            case TelephonyManager.NETWORK_TYPE_EVDO_B: type1 = "CDMA2000 1xEV-DO Rev. B"; break;
            case TelephonyManager.NETWORK_TYPE_EHRPD: type1 = "CDMA2000 eHRPD"; break;
            case TelephonyManager.NETWORK_TYPE_GSM: type1 = "GSM"; break;
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA: type1 = "UMTS (TD-SDCDMA)"; break;
            case TelephonyManager.NETWORK_TYPE_IWLAN: type1 = "IWLAN"; break;
            /* Temporarily allocated by custom ROMs */
            case 30 /* NETWORK_TYPE_DCHSPAP */: type1 = "DC-HSPA+"; break;

        }

        Toast.makeText(context1, type1, Toast.LENGTH_LONG).show();

        // Launch Activity RadioInfo
        Intent i1 = new Intent();
        Intent in1 = new Intent(ActivitySwitch.this,SimpleWidgetProvider.class);
        i1.setClassName( "com.android.settings", "com.android.settings.RadioInfo" );
        in1.putExtra("flag",1);
        startActivity(i1);


    }

}
