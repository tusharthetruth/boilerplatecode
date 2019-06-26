package in.boilerplatecode.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import in.boilerplatecode.R;

import java.util.ArrayList;


public class PermissionUtils {
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 999;
    public static final int CONTACT_READ_PERMISSION_REQUEST_CODE = 998;
    public static final int READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 997;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 996;
    public static final int WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 995;
    public static final int READ_PHONE_STATE_REQUEST_CODE = 994;
    public static final int SMS_READ_REQUEST_CODE = 993;
    public static final int PERMISSION_REQUEST_CODE = 992;

    public static final String READ_CONTACT = Manifest.permission.READ_CONTACTS;
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String CAMERA_REQUIRED = Manifest.permission.CAMERA;
    public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String SMS_READ = Manifest.permission.READ_SMS;

    public static boolean isPermissionGranted(Context context, String permission) {
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPermissionPreviouslyDenied(AppCompatActivity activity, String permission) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            return true;
        } else {
            return false;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void getPermission(final AppCompatActivity context, String permission, int requestCode, boolean showDialog) {
        if (!isPermissionGranted(context, permission)) {
            if (isPermissionPreviouslyDenied(context, permission)) {
                if (showDialog)
                    showDialog(context, permission);
            } else {
                context.requestPermissions(new String[]{permission}, requestCode);
            }
        }
    }

    public static void getPermission(final Fragment context, String permission, int requestCode, boolean showDialog) {
        if (!isPermissionGranted(context.getActivity(), permission)) {
            if (isPermissionPreviouslyDenied((AppCompatActivity) context.getActivity(), permission)) {
                if (showDialog)
                    showDialog(context.getActivity(), permission);
            } else {
                (context).requestPermissions(new String[]{permission}, requestCode);
            }
        }
    }

    public static void showDialog(final Activity activity, String permission) {
        String explaination = activity.getString(R.string.err_generic_permission);
        switch (permission) {
            case READ_CONTACT:
                explaination = activity.getString(R.string.err_contact_permission);
                break;
            case READ_EXTERNAL_STORAGE:
                explaination = activity.getString(R.string.err_read_permission);
                break;
            case CAMERA_REQUIRED:
                explaination = activity.getString(R.string.err_camera_permission);
                break;
            case WRITE_EXTERNAL_STORAGE:
                explaination = activity.getString(R.string.err_write_permission);
                break;
            case READ_PHONE_STATE:
                explaination = activity.getString(R.string.err_phone_permission);
                break;
        }
        showPermissionNotAllow(activity, explaination, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startInstalledAppDetailsActivity(activity);
                dialog.dismiss();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void groupPermission(AppCompatActivity context) {
        ArrayList<String> list = new ArrayList<>();
        if (!isPermissionGranted(context, READ_PHONE_STATE))
            list.add(READ_PHONE_STATE);
        if (!isPermissionGranted(context, CAMERA_REQUIRED))
            list.add(CAMERA_REQUIRED);
        if (!isPermissionGranted(context, WRITE_EXTERNAL_STORAGE))
            list.add(WRITE_EXTERNAL_STORAGE);

        String[] reqPermissionList = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            reqPermissionList[i] = list.get(i);
        }
        if (reqPermissionList.length > 0) {
            context.requestPermissions(reqPermissionList, PERMISSION_REQUEST_CODE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean havePermission(AppCompatActivity context) {
        ArrayList<String> list = new ArrayList<>();
        if (!isPermissionGranted(context, READ_PHONE_STATE))
            list.add(READ_PHONE_STATE);
        if (!isPermissionGranted(context, CAMERA_REQUIRED))
            list.add(CAMERA_REQUIRED);
        if (!isPermissionGranted(context, WRITE_EXTERNAL_STORAGE))
            list.add(WRITE_EXTERNAL_STORAGE);

        return list.size() == 0;
    }

    public static void showPermissionNotAllow(final Activity contxt, String explanation, DialogInterface.OnClickListener positiveButton, DialogInterface.OnClickListener onNegativeAction) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(contxt);
        // set title
        alertDialogBuilder.setTitle(null);
        // set dialog message
        alertDialogBuilder
                .setMessage(explanation)
                .setCancelable(false)
                .setPositiveButton(contxt.getResources().getString(R.string.open_settings), positiveButton)
                .setNegativeButton(contxt.getResources().getString(R.string.cancel), onNegativeAction);
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    public static void startInstalledAppDetailsActivity(final Activity context) {
        if (context == null) {
            return;
        }
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }

}
