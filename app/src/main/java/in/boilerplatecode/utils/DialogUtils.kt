package `in`.boilerplatecode

import android.app.AlertDialog
import android.content.Context

class DialogUtils {
    companion object {
        fun showDialog(
            context: Context,
            msg: String,
            yes: String,
            no: String,
            listener: IDialogListener,
            requestCode: Int
        ) {
            val dialog = android.app.AlertDialog.Builder(context)
            dialog.setCancelable(false)
            dialog.setMessage(msg)
            dialog.setPositiveButton(yes) { dialog, which ->
                listener.onYesClick(requestCode)
                dialog.dismiss()
            }
            dialog.setNegativeButton(no) { dialog, which ->
                listener.onNoClick(requestCode)
                dialog.dismiss()
            }
            dialog.show()
        }

        fun showOkDialog(context: Context, msg: String, listener: IDialogListener, rcode: Int) {
            val dialog = AlertDialog.Builder(context)
            dialog.setMessage(msg)
            dialog.setCancelable(false)
            dialog.setPositiveButton("ok") { dialog, which ->
                listener.onYesClick(rcode)
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    interface IDialogListener {
        fun onYesClick(requestCode: Int)
        fun onNoClick(requestCode: Int)
    }
}