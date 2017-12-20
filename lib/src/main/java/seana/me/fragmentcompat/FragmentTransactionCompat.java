package seana.me.fragmentcompat;

import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

public abstract class FragmentTransactionCompat {

    public abstract FragmentTransactionCompat add(FragmentCompat fragment, @Nullable String tag);

    public abstract FragmentTransactionCompat add(@IdRes int containerViewId, FragmentCompat fragment);

    public abstract FragmentTransactionCompat add(@IdRes int containerViewId, FragmentCompat fragment, @Nullable String tag);

    public abstract FragmentTransactionCompat remove(FragmentCompat fragment);

    public abstract int commit();

    public abstract void commitNow();

    public abstract int commitAllowingStateLoss();

    public abstract void commitNowAllowingStateLoss();

    static FragmentTransactionCompat beginTransaction(android.app.FragmentTransaction transaction) {
        return new Platform(transaction);
    }

    static FragmentTransactionCompat beginTransaction(android.support.v4.app.FragmentTransaction transaction) {
        return new Support(transaction);
    }

    private static final class Platform extends FragmentTransactionCompat {

        private final android.app.FragmentTransaction transaction;

        private Platform(android.app.FragmentTransaction transaction) {
            this.transaction = transaction;
        }

        @Override
        public FragmentTransactionCompat add(FragmentCompat fragment, @Nullable String tag) {
            transaction.add(fragment.toPlatformFragment(), tag);
            return this;
        }

        @Override
        public FragmentTransactionCompat add(int containerViewId, FragmentCompat fragment) {
            transaction.add(containerViewId, fragment.toPlatformFragment());
            return this;
        }

        @Override
        public FragmentTransactionCompat add(int containerViewId, FragmentCompat fragment, @Nullable String tag) {
            transaction.add(containerViewId, fragment.toPlatformFragment(), tag);
            return this;
        }

        @Override
        public FragmentTransactionCompat remove(FragmentCompat fragment) {
            transaction.remove(fragment.toPlatformFragment());
            return this;
        }

        @Override
        public int commit() {
            return transaction.commit();
        }

        @Override
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void commitNow() {
            transaction.commitNow();
        }

        @Override
        public int commitAllowingStateLoss() {
            return transaction.commitAllowingStateLoss();
        }

        @Override
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void commitNowAllowingStateLoss() {
            transaction.commitNowAllowingStateLoss();
        }
    }

    private static final class Support extends FragmentTransactionCompat {

        private final android.support.v4.app.FragmentTransaction transaction;

        private Support(android.support.v4.app.FragmentTransaction transaction) {
            this.transaction = transaction;
        }

        @Override
        public FragmentTransactionCompat add(FragmentCompat fragment, @Nullable String tag) {
            transaction.add(fragment.toSupportFragment(), tag);
            return this;
        }

        @Override
        public FragmentTransactionCompat add(@IdRes int containerViewId, FragmentCompat fragment) {
            transaction.add(containerViewId, fragment.toSupportFragment());
            return this;
        }

        @Override
        public FragmentTransactionCompat add(@IdRes int containerViewId, FragmentCompat fragment, @Nullable String tag) {
            transaction.add(containerViewId, fragment.toSupportFragment(), tag);
            return this;
        }

        @Override
        public FragmentTransactionCompat remove(FragmentCompat fragment) {
            transaction.remove(fragment.toSupportFragment());
            return this;
        }

        @Override
        public int commit() {
            return transaction.commit();
        }

        @Override
        public void commitNow() {
            transaction.commitNow();
        }

        @Override
        public int commitAllowingStateLoss() {
            return transaction.commitAllowingStateLoss();
        }

        @Override
        public void commitNowAllowingStateLoss() {
            transaction.commitAllowingStateLoss();
        }
    }
    
}