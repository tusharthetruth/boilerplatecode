package `in`.boilerplatecode.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference


class AppLifeCycle : Application.ActivityLifecycleCallbacks {
    public var mListener: WeakReference<AppLifeCycleListener> = WeakReference(null)
    private var mState = AppState.NONE
    private var mStartedActivityCount = 0

    private enum class AppState {
        NONE,
        FOREGROUND,
        BACKGROUND
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle) {
        //        App.log().v(getLifeCycleLogTag(activity), "onCreate" + (bundle == null ? " [first launch]" : " [relaunch]"));
    }

    override fun onActivityStarted(activity: Activity) {
        //        App.log().v(getLifeCycleLogTag(activity), "onStart");
        mStartedActivityCount++
        if (mStartedActivityCount > 0)
            changeState(AppState.FOREGROUND)
    }

    override fun onActivityResumed(activity: Activity) {
        //        App.log().v(getLifeCycleLogTag(activity), "onResume");
    }

    override fun onActivityPaused(activity: Activity) {
        //        App.log().v(getLifeCycleLogTag(activity), "onPause");
    }

    override fun onActivityStopped(activity: Activity) {
        //        App.log().v(getLifeCycleLogTag(activity), "onStop");
        mStartedActivityCount--
        if (mStartedActivityCount <= 0)
            changeState(AppState.BACKGROUND)
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        //        App.log().v(getLifeCycleLogTag(activity), "onSaveInstanceState");
    }

    override fun onActivityDestroyed(activity: Activity) {
        //        App.log().v(getLifeCycleLogTag(activity), "onDestroy");
    }

    fun register(listener: AppLifeCycleListener) {
        mListener = WeakReference(listener)
    }

    private fun changeState(newState: AppState) {
        if (mState == newState)
            return

        mState = newState

        if (mListener.get() == null)
            return

        when (mState) {
            AppState.NONE -> {
            }
            AppState.FOREGROUND ->
                //                App.log().v(LOG_TAG, "onAppForegrounded");
                mListener.get()!!.onAppForegrounded()
            AppState.BACKGROUND ->
//                               App.log().v(LOG_TAG, "onAppBackgrounded");
                mListener.get()!!.onAppBackgrounded()
        }
    }

    interface AppLifeCycleListener {
        fun onAppForegrounded()

        fun onAppBackgrounded()
    }

    companion object {
        private val LOG_TAG = "APP"
        fun getLifeCycleLogTag(`object`: Any): String {
            return "LIFECYCLE::" + `object`.javaClass.simpleName + `object`.hashCode()
        }
    }
}
