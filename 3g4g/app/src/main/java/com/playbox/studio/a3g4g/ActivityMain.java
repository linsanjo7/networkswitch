package com.playbox.studio.a3g4g;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class ActivityMain extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    private Context context;
    private TelephonyManager tm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this,
                "ca-app-pub-1823748384153404~8993515622");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1823748384153404/3188802408");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        setContentView(R.layout.layout_main);
        Button button= (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network();
            }
        });
        mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void network(){
        context = getApplicationContext();

        // Toast current Network Type
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String type = "UNKNOWN";
        switch (tm.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS: type = "GPRS"; break;
            case TelephonyManager.NETWORK_TYPE_EDGE: type = "EDGE"; break;
            case TelephonyManager.NETWORK_TYPE_UMTS: type = "UMTS"; break;
            case TelephonyManager.NETWORK_TYPE_HSDPA: type = "HSDPA"; break;
            case TelephonyManager.NETWORK_TYPE_HSUPA: type = "HSUPA"; break;
            case TelephonyManager.NETWORK_TYPE_HSPA: type = "HSPA"; break;
            case TelephonyManager.NETWORK_TYPE_HSPAP: type = "HSPA+"; break;
            case TelephonyManager.NETWORK_TYPE_LTE: type = "LTE"; break;
            case TelephonyManager.NETWORK_TYPE_IDEN: type = "iDEN"; break;
            case TelephonyManager.NETWORK_TYPE_CDMA: type = "cmdaOne"; break;
            case TelephonyManager.NETWORK_TYPE_1xRTT: type = "CDMA2000 1xRTT"; break;
            case TelephonyManager.NETWORK_TYPE_EVDO_0: type = "CDMA2000 1xEV-DO Rev. 0"; break;
            case TelephonyManager.NETWORK_TYPE_EVDO_A: type = "CDMA2000 1xEV-DO Rev. A"; break;
            case TelephonyManager.NETWORK_TYPE_EVDO_B: type = "CDMA2000 1xEV-DO Rev. B"; break;
            case TelephonyManager.NETWORK_TYPE_EHRPD: type = "CDMA2000 eHRPD"; break;
            case TelephonyManager.NETWORK_TYPE_GSM: type = "GSM"; break;
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA: type = "UMTS (TD-SDCDMA)"; break;
            case TelephonyManager.NETWORK_TYPE_IWLAN: type = "IWLAN"; break;
            /* Temporarily allocated by custom ROMs */
            case 30 /* NETWORK_TYPE_DCHSPAP */: type = "DC-HSPA+"; break;

        }

        Toast.makeText(context, type, Toast.LENGTH_LONG).show();

        // Launch Activity RadioInfo
        Intent i = new Intent();
        Intent in = new Intent(ActivityMain.this,SimpleWidgetProvider.class);
        i.setClassName( "com.android.settings", "com.android.settings.RadioInfo" );
        in.putExtra("flag",1);
        startActivity(i);

    finish();
    }

}

