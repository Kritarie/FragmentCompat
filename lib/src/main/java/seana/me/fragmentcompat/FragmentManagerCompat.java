package seana.me.fragmentcompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class FragmentManagerCompat {

    public static FragmentManagerCompat create(Activity activity) {
        if (activity instanceof FragmentActivity) {
            return new Support(((FragmentActivity) activity).getSupportFragmentManager());
        } else {
            return new Platform(activity.getFragmentManager());
        }
    }

    public static FragmentManagerCompat create(Object fragmentManager) {
        if (fragmentManager instanceof android.app.FragmentManager) {
            return new Platform((android.app.FragmentManager) fragmentManager);
        }
        if (fragmentManager instanceof android.support.v4.app.FragmentManager) {
            return new Support((android.support.v4.app.FragmentManager) fragmentManager);
        }

        throw new IllegalArgumentException("Requires a FragmentManager instance.");
    }

    public abstract android.app.FragmentManager toPlatformFragmentManager();

    public abstract android.support.v4.app.FragmentManager toSupportFragmentManager();

    public abstract void popBackStack();

    public abstract boolean popBackStackImmediate();

    public abstract void popBackStack(String name, int flags);

    public abstract boolean popBackStackImmediate(String name, int flags);

    public abstract void popBackStack(int id, int flags);

    public abstract boolean popBackStackImmediate(int id, int flags);

    public abstract int getBackStackEntryCount();

    // TODO back stack changed listener

    @Nullable
    public abstract <T> T findFragmentById(@IdRes int id);

    @Nullable
    public abstract <T> T findFragmentByTag(String tag);

    public abstract void putFragment(Bundle bundle, String key, FragmentCompat fragment);

    public abstract FragmentCompat getFragment(Bundle bundle, String key);

    public abstract List<FragmentCompat> getFragments();

    public abstract FragmentTransactionCompat beginTransaction();

    public abstract void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacksCompat callbacks, boolean recursive);

    public abstract void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacksCompat callbacks);

    public abstract boolean isStateSaved();

    private static final class Platform extends FragmentManagerCompat {

        private final android.app.FragmentManager fragmentManager;

        private Platform(android.app.FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
        }

        @Override
        public android.app.FragmentManager toPlatformFragmentManager() {
            return fragmentManager;
        }

        @Override
        public android.support.v4.app.FragmentManager toSupportFragmentManager() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void popBackStack() {
            fragmentManager.popBackStack();
        }

        @Override
        public boolean popBackStackImmediate() {
            return fragmentManager.popBackStackImmediate();
        }

        @Override
        public void popBackStack(String name, int flags) {
            fragmentManager.popBackStack(name, flags);
        }

        @Override
        public boolean popBackStackImmediate(String name, int flags) {
            return fragmentManager.popBackStackImmediate(name, flags);
        }

        @Override
        public void popBackStack(int id, int flags) {
            fragmentManager.popBackStack(id, flags);
        }

        @Override
        public boolean popBackStackImmediate(int id, int flags) {
            return fragmentManager.popBackStackImmediate(id, flags);
        }

        @Override
        public int getBackStackEntryCount() {
            return fragmentManager.getBackStackEntryCount();
        }

        @Nullable
        @Override
        public <T> T findFragmentById(@IdRes int id) {
            //noinspection unchecked
            return (T) fragmentManager.findFragmentById(id);
        }

        @Nullable
        @Override
        public <T> T findFragmentByTag(String tag) {
            //noinspection unchecked
            return (T) fragmentManager.findFragmentByTag(tag);
        }

        @Override
        public void putFragment(Bundle bundle, String key, FragmentCompat fragment) {
            fragmentManager.putFragment(bundle, key, fragment.toPlatformFragment());
        }

        @Override
        public FragmentCompat getFragment(Bundle bundle, String key) {
            return FragmentCompat.create(fragmentManager.getFragment(bundle, key));
        }

        @Override
        @RequiresApi(api = Build.VERSION_CODES.O)
        public List<FragmentCompat> getFragments() {
            final List<android.app.Fragment> fragments = fragmentManager.getFragments();
            final List<FragmentCompat> ret = new ArrayList<>(fragments.size());
            for (android.app.Fragment f : fragments) {
                ret.add(FragmentCompat.create(f));
            }
            return ret;
        }

        @SuppressLint("CommitTransaction")
        @Override
        public FragmentTransactionCompat beginTransaction() {
            return FragmentTransactionCompat.beginTransaction(fragmentManager.beginTransaction());
        }

        @Override
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacksCompat callbacks, boolean recursive) {
            fragmentManager.registerFragmentLifecycleCallbacks(callbacks.toPlatformCallbacks(), recursive);
        }

        @Override
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacksCompat callbacks) {
            fragmentManager.unregisterFragmentLifecycleCallbacks(callbacks.toPlatformCallbacks());
        }

        @Override
        @RequiresApi(api = Build.VERSION_CODES.O)
        public boolean isStateSaved() {
            return fragmentManager.isStateSaved();
        }
    }

    private static final class Support extends FragmentManagerCompat {

        private final android.support.v4.app.FragmentManager fragmentManager;

        private Support(android.support.v4.app.FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
        }

        @Override
        public android.app.FragmentManager toPlatformFragmentManager() {
            throw new UnsupportedOperationException();
        }

        @Override
        public android.support.v4.app.FragmentManager toSupportFragmentManager() {
            return fragmentManager;
        }

        @Override
        public void popBackStack() {
            fragmentManager.popBackStack();
        }

        @Override
        public boolean popBackStackImmediate() {
            return fragmentManager.popBackStackImmediate();
        }

        @Override
        public void popBackStack(String name, int flags) {
            fragmentManager.popBackStack(name, flags);
        }

        @Override
        public boolean popBackStackImmediate(String name, int flags) {
            return fragmentManager.popBackStackImmediate(name, flags);
        }

        @Override
        public void popBackStack(int id, int flags) {
            fragmentManager.popBackStack(id, flags);
        }

        @Override
        public boolean popBackStackImmediate(int id, int flags) {
            return fragmentManager.popBackStackImmediate(id, flags);
        }

        @Override
        public int getBackStackEntryCount() {
            return fragmentManager.getBackStackEntryCount();
        }

        @Nullable
        @Override
        public <T> T findFragmentById(@IdRes int id) {
            //noinspection unchecked
            return (T) fragmentManager.findFragmentById(id);
        }

        @Nullable
        @Override
        public <T> T findFragmentByTag(String tag) {
            //noinspection unchecked
            return (T) fragmentManager.findFragmentByTag(tag);
        }

        @Override
        public void putFragment(Bundle bundle, String key, FragmentCompat fragment) {
            fragmentManager.putFragment(bundle, key, fragment.toSupportFragment());
        }

        @Override
        public FragmentCompat getFragment(Bundle bundle, String key) {
            return FragmentCompat.create(fragmentManager.getFragment(bundle, key));
        }

        @Override
        public List<FragmentCompat> getFragments() {
            final List<android.support.v4.app.Fragment> fragments = fragmentManager.getFragments();
            final List<FragmentCompat> ret = new ArrayList<>(fragments.size());
            for (android.support.v4.app.Fragment f : fragments) {
                ret.add(FragmentCompat.create(f));
            }
            return ret;
        }

        @SuppressLint("CommitTransaction")
        @Override
        public FragmentTransactionCompat beginTransaction() {
            return FragmentTransactionCompat.beginTransaction(fragmentManager.beginTransaction());
        }

        @Override
        public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacksCompat callbacks, boolean recursive) {
            fragmentManager.registerFragmentLifecycleCallbacks(callbacks.toSupportCallbacks(), recursive);
        }

        @Override
        public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacksCompat callbacks) {
            fragmentManager.unregisterFragmentLifecycleCallbacks(callbacks.toSupportCallbacks());
        }

        @Override
        public boolean isStateSaved() {
            return fragmentManager.isStateSaved();
        }
    }
}