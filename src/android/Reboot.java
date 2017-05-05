package dk.fadeit.reboot;

import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.os.PowerManager;
/**
 * This class echoes a string called from JavaScript.
 */
public class Reboot extends CordovaPlugin {

    private static final String TAG = "RebootPlugin";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("reboot")) {
            this.reboot(callbackContext);
            return true;
        }
        return false;
    }

    private void reboot(CallbackContext callbackContext) {
        try {
          PowerManager pm = (PowerManager) this.cordova.getActivity().getApplicationContext().getSystemService(Context.POWER_SERVICE);
          pm.reboot(null);
          callbackContext.success("Rebooting...");
        } catch (Exception ex) {
          Log.i(TAG, "Could not reboot", ex);
          callbackContext.error("Reboot failed.");
        }
    }
}
