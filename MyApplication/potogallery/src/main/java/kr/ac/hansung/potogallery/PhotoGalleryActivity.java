package kr.ac.hansung.potogallery;

import android.support.v4.app.Fragment;

public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragmnet() {
        return new PhotoGalleryFragment();
    }
}
