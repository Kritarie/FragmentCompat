package seana.me.fragmentcompat;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;

public abstract class FragmentLifecycleCallbacksCompat {

    public abstract void onFragmentPreAttached(FragmentManagerCompat fm, FragmentCompat f, Context context);

    public abstract void onFragmentAttached(FragmentManagerCompat fm, FragmentCompat f, Context context);

    public abstract void onFragmentCreated(FragmentManagerCompat fm, FragmentCompat f, Bundle savedInstanceState);

    public abstract void onFragmentViewCreated(FragmentManagerCompat fm, FragmentCompat f, View v, Bundle savedInstanceState);

    public abstract void onFragmentStarted(FragmentManagerCompat fm, FragmentCompat f);

    public abstract void onFragmentResumed(FragmentManagerCompat fm, FragmentCompat f);

    public abstract void onFragmentPaused(FragmentManagerCompat fm, FragmentCompat f);

    public abstract void onFragmentStopped(FragmentManagerCompat fm, FragmentCompat f);

    public abstract void onFragmentViewDestroyed(FragmentManagerCompat fm, FragmentCompat f);

    public abstract void onFragmentDestroyed(FragmentManagerCompat fm, FragmentCompat f);

    public abstract void onFragmentDetached(FragmentManagerCompat fm, FragmentCompat f);

    @RequiresApi(api = Build.VERSION_CODES.O)
    final android.app.FragmentManager.FragmentLifecycleCallbacks toPlatformCallbacks() {
        return new Platform(this);
    }

    final android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks toSupportCallbacks() {
        return new Support(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static final class Platform extends android.app.FragmentManager.FragmentLifecycleCallbacks {

        private final FragmentLifecycleCallbacksCompat callbacks;

        public Platform(FragmentLifecycleCallbacksCompat callbacks) {
            this.callbacks = callbacks;
        }

        @Override
        public void onFragmentPreAttached(android.app.FragmentManager fm, android.app.Fragment f, Context context) {
            callbacks.onFragmentPreAttached(FragmentManagerCompat.create(fm), FragmentCompat.create(f), context);
        }

        @Override
        public void onFragmentAttached(android.app.FragmentManager fm, android.app.Fragment f, Context context) {
            callbacks.onFragmentAttached(FragmentManagerCompat.create(fm), FragmentCompat.create(f), context);
        }

        @Override
        public void onFragmentCreated(android.app.FragmentManager fm, android.app.Fragment f, Bundle savedInstanceState) {
            callbacks.onFragmentCreated(FragmentManagerCompat.create(fm), FragmentCompat.create(f), savedInstanceState);
        }

        @Override
        public void onFragmentViewCreated(android.app.FragmentManager fm, android.app.Fragment f, View v, Bundle savedInstanceState) {
            callbacks.onFragmentViewCreated(FragmentManagerCompat.create(fm), FragmentCompat.create(f), v, savedInstanceState);
        }

        @Override
        public void onFragmentStarted(android.app.FragmentManager fm, android.app.Fragment f) {
            callbacks.onFragmentStarted(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentResumed(android.app.FragmentManager fm, android.app.Fragment f) {
            callbacks.onFragmentResumed(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentPaused(android.app.FragmentManager fm, android.app.Fragment f) {
            callbacks.onFragmentPaused(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentStopped(android.app.FragmentManager fm, android.app.Fragment f) {
            callbacks.onFragmentStopped(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentViewDestroyed(android.app.FragmentManager fm, android.app.Fragment f) {
            callbacks.onFragmentViewDestroyed(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentDestroyed(android.app.FragmentManager fm, android.app.Fragment f) {
            callbacks.onFragmentDestroyed(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentDetached(android.app.FragmentManager fm, android.app.Fragment f) {
            callbacks.onFragmentDetached(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (!(obj instanceof Platform)) {
                return false;
            }
            Platform platform = (Platform) obj;
            return callbacks.equals(platform.callbacks);
        }
    }

    private static final class Support extends android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks {

        private final FragmentLifecycleCallbacksCompat callbacks;

        public Support(FragmentLifecycleCallbacksCompat callbacks) {
            this.callbacks = callbacks;
        }

        @Override
        public void onFragmentPreAttached(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment f, Context context) {
            callbacks.onFragmentPreAttached(FragmentManagerCompat.create(fm), FragmentCompat.create(f), context);
        }

        @Override
        public void onFragmentAttached(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment f, Context context) {
            callbacks.onFragmentAttached(FragmentManagerCompat.create(fm), FragmentCompat.create(f), context);
        }

        @Override
        public void onFragmentCreated(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment f, Bundle savedInstanceState) {
            callbacks.onFragmentCreated(FragmentManagerCompat.create(fm), FragmentCompat.create(f), savedInstanceState);
        }

        @Override
        public void onFragmentViewCreated(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment f, View v, Bundle savedInstanceState) {
            callbacks.onFragmentViewCreated(FragmentManagerCompat.create(fm), FragmentCompat.create(f), v, savedInstanceState);
        }

        @Override
        public void onFragmentStarted(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment f) {
            callbacks.onFragmentStarted(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentResumed(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment f) {
            callbacks.onFragmentResumed(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentPaused(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment f) {
            callbacks.onFragmentPaused(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentStopped(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment f) {
            callbacks.onFragmentStopped(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentViewDestroyed(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment f) {
            callbacks.onFragmentViewDestroyed(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentDestroyed(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment f) {
            callbacks.onFragmentDestroyed(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public void onFragmentDetached(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment f) {
            callbacks.onFragmentDetached(FragmentManagerCompat.create(fm), FragmentCompat.create(f));
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (!(obj instanceof Support)) {
                return false;
            }
            Support support = (Support) obj;
            return callbacks.equals(support.callbacks);
        }
    }
}