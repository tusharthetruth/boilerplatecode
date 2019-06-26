package `in`.boilerplatecode

import org.json.JSONObject

class JsonUtil {
    companion object {
        fun getJSONString(jsonObject: JSONObject, key: String): String {
            var value = jsonObject.optString(key)
            if (TextUtils.isEmpty(value))
                return ""
            return value
        }

        fun getJSONDouble(jsonObject: JSONObject, key: String): Double {
            var value = jsonObject.optDouble(key)
            if (TextUtils.isEmpty(value.toString()))
                return 0.toDouble()
            return value
        }

        fun getJSONInt(jsonObject: JSONObject, key: String): Int {
            var value = jsonObject.optInt(key)
            if (TextUtils.isEmpty(value.toString()))
                return 0
            return value
        }

        fun getJSONBoolean(jsonObject: JSONObject, key: String): Boolean {
            var value = jsonObject.optBoolean(key)
            if (TextUtils.isEmpty(value.toString()))
                return false
            return value
        }
    }
}