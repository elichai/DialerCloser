package com.elichai.dialercloser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class MyDialog extends Activity {
	static Context mContext;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		create(this);
	}
	public static void create (final Context context) {
		mContext = context;
		String Pbutton = context.getResources().getString(R.string.PositiveButton);
		String Nbutton = context.getResources().getString(R.string.NegetiveButton);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		builder.setTitle("DialerCloser");
		builder.setMessage("לסגור את החייגן? \n 00:10");
		builder.setInverseBackgroundForced(false);
		builder.setPositiveButton(Pbutton,
		new DialogInterface.OnClickListener() {
			@Override
            public void onClick(DialogInterface dialog, int which) {
				HomeRun.run();
			}
        });
		builder.setNegativeButton(Nbutton,
    	new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				((Activity) context).onBackPressed();
			}
		});
		final AlertDialog alert = builder.create();
		alert.show();
		new CountDownTimer(10000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				alert.setMessage("לסגור את החייגן? \n 00:"+ (millisUntilFinished/1000));
			}
			@Override
			public void onFinish() {
				HomeRun.run();
			}
		}.start();
	}
	static final Runnable HomeRun = new Runnable() {
		public void run() {
			Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(startMain);
		}
	};
}
