package `in`.boilerplatecode.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class AppUtil {
    companion object {
        fun hideKeyboard(activity: Activity) {
            try {
                var im: InputMethodManager =
                    activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                var view = activity.currentFocus
                view = view ?: View(activity)
                im.hideSoftInputFromWindow(view!!.windowToken, 0)
            } catch (e: Exception) {

            }
        }

        fun hideKeyboard(context: Context, view: View) {
            val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
