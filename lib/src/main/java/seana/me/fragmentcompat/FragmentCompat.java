package seana.me.fragmentcompat;

import android.app.Fragment;

public abstract class FragmentCompat {

    public static FragmentCompat create(android.app.Fragment fragment) {
        return new Android(fragment);
    }

    public static FragmentCompat create(android.support.v4.app.Fragment fragment) {
        return new Support(fragment);
    }

    public abstract android.app.Fragment toPlatformFragment();

    public abstract android.support.v4.app.Fragment toSupportFragment();

    private static class Android extends FragmentCompat {

        private final android.app.Fragment fragment;

        public Android(android.app.Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public Fragment toPlatformFragment() {
            return fragment;
        }

        @Override
        public android.support.v4.app.Fragment toSupportFragment() {
            throw new UnsupportedOperationException();
        }
    }

    private static class Support extends FragmentCompat {

        public final android.support.v4.app.Fragment fragment;

        public Support(android.support.v4.app.Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public Fragment toPlatformFragment() {
            throw new UnsupportedOperationException();
        }

        @Override
        public android.support.v4.app.Fragment toSupportFragment() {
            return fragment;
        }
    }
}