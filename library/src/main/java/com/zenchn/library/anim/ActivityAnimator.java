package com.zenchn.library.anim;

import com.zenchn.library.R;

/**
 * 作    者：wangr on 2017/5/6 0006 17:48
 * 描    述：
 * 修订记录：
 */
public class ActivityAnimator {

    public static int[] flipHorizontalAnimation() {
        return new int[]{R.anim.activity_animator_flip_horizontal_in, R.anim.activity_animator_flip_horizontal_out};
    }

    public static int[] flipVerticalAnimation() {
        return new int[]{R.anim.activity_animator_flip_vertical_in, R.anim.activity_animator_flip_vertical_out};
    }

    public static int[] fadeAnimation() {
        return new int[]{R.anim.activity_animator_fade_in, R.anim.activity_animator_fade_out};
    }

    public static int[] disappearTopLeftAnimation() {
        return new int[]{R.anim.activity_animator_disappear_top_left_in, R.anim.activity_animator_disappear_top_left_out};
    }

    public static int[] appearTopLeftAnimation() {
        return new int[]{R.anim.appear_top_left_in, R.anim.appear_top_left_out};
    }

    public static int[] disappearBottomRightAnimation() {
        return new int[]{R.anim.activity_animator_disappear_bottom_right_in, R.anim.activity_animator_disappear_bottom_right_out};
    }

    public static int[] appearBottomRightAnimation() {
        return new int[]{R.anim.appear_bottom_right_in, R.anim.appear_bottom_right_out};
    }

    public static int[] unzoomAnimation() {
        return new int[]{R.anim.activity_animator_unzoom_in, R.anim.activity_animator_unzoom_out};
    }

    public static int[] pullRightPushLeft() {
        return new int[]{R.anim.activity_animator_pull_in_right, R.anim.activity_animator_push_out_left};
    }

    public static int[] pullLeftPushRight() {
        return new int[]{R.anim.activity_animator_pull_in_left, R.anim.activity_animator_push_out_right};
    }
}
