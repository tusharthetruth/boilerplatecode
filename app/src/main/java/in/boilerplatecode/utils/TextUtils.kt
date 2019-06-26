package `in`.boilerplatecode

import android.text.TextUtils
import android.widget.EditText

class TextUtils {
    companion object {
        fun isEmpty(param: String): Boolean {
            if (TextUtils.isEmpty(param) || param.equals("null")) {
                return true
            }
            return false
        }

        fun getString(et: EditText): String {
            if (et == null)
                return ""
            return et.text.toString().trim()
        }
    }
}