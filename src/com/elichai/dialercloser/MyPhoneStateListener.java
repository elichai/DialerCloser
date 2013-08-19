package com.elichai.dialercloser;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyPhoneStateListener extends PhoneStateListener {
    public static Boolean phoneHangUp = false;
    Context mContext = PhoneReciever.PhoneContext;

	public void onCallStateChanged(int state, String incomingNumber) {
    	if(!phoneHangUp)
        switch (state) {
        case TelephonyManager.CALL_STATE_IDLE:
            Log.d("DEBUG", "IDLE");
            phoneHangUp = true;
            PhoneReciever.telephony.listen(null, PhoneStateListener.LISTEN_NONE);
            Intent intent = new Intent(mContext, MyDialog.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            break;
        case TelephonyManager.CALL_STATE_OFFHOOK:
            Log.d("DEBUG", "OFFHOOK");
            phoneHangUp = false;
            break;
        case TelephonyManager.CALL_STATE_RINGING:
            Log.d("DEBUG", "RINGING");
            phoneHangUp = false;
            break;
        }
        super.onCallStateChanged(state, incomingNumber);
        PhoneReciever.telephony.listen(null, PhoneStateListener.LISTEN_NONE);
    }

}
