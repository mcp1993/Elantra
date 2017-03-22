package com.mcp1993.elantra;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.mcp1993.elantra.challenge.ChallengeFragment;
import com.mcp1993.elantra.explorer.ExplorerFragment;
import com.mcp1993.elantra.home.HomeFragment;
import com.mcp1993.elantra.profile.ProfileFragment;
import com.mcp1993.elantra.social.SocialFragment;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AutoLayoutActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback{

    @BindView(com.mcp1993.elantra .R.id.frame_content)
    FrameLayout frame_content;
    @BindView(com.mcp1993.elantra .R.id.layout_home)
     RelativeLayout layout_home;
    @BindView(com.mcp1993.elantra .R.id.layout_challenge)
    RelativeLayout layout_challenge;
    @BindView(com.mcp1993.elantra .R.id.layout_explorer)
    RelativeLayout layout_explorer;
    @BindView(com.mcp1993.elantra .R.id.layout_social)
    RelativeLayout layout_social;
    @BindView(com.mcp1993.elantra .R.id.layout_profile)
    RelativeLayout layout_profile;

    @BindView(com.mcp1993.elantra .R.id.img_home)
    ImageView img_home;
    @BindView(com.mcp1993.elantra .R.id.tv_home)
    TextView tv_home;

    @BindView(com.mcp1993.elantra .R.id.img_challenge)
    ImageView img_challenge;
    @BindView(com.mcp1993.elantra .R.id.tv_challenge)
    TextView tv_challenge;

    @BindView(com.mcp1993.elantra .R.id.img_explorer)
    ImageView img_explorer;
    @BindView(com.mcp1993.elantra .R.id.tv_explorer)
    TextView tv_explorer;

    @BindView(com.mcp1993.elantra .R.id.img_social)
    ImageView img_social;
    @BindView(com.mcp1993.elantra .R.id.tv_social)
    TextView tv_social;

    @BindView(com.mcp1993.elantra .R.id.img_profile)
    ImageView img_profile;
    @BindView(com.mcp1993.elantra .R.id.tv_profile)
    TextView tv_profile;

    private int selectColor;
    private int unSelectColor;
    private Fragment[] fragments;
    private int currentIndex =1;
    private int index ;

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA

    };

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mcp1993.elantra .R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, R.color.bg_all, false);
        getPermission();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isNeedCheck){
            checkPermissions(needPermissions);
        }
    }
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }
    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     *
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }
    /**
     * 显示提示信息
     *
     * @since 2.5.0
     *
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前应用缺少必要权限。\\n\\n请点击\\\"设置\"-\"权限\"-打开所需权限。");

        // 拒绝, 退出应用
        builder.setNegativeButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    private void getPermission(){
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.CAMERA)
                .send();
        AndPermission.with(this)
                .requestCode(101)
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .send();
    }

    private void initView(){

        selectColor=getResources().getColor(com.mcp1993.elantra .R.color.bottom_text_color_pressed);
        unSelectColor=getResources().getColor(com.mcp1993.elantra .R.color.bottom_text_color_normal);

        fragments=new Fragment[5];
        fragments[0]= HomeFragment.getInstance();
        fragments[1]= ChallengeFragment.getInstance();
        fragments[2]= ExplorerFragment.getInstance();
        fragments[3]= SocialFragment.getInstance();
        fragments[4]= ProfileFragment.getInstance();
        getSupportFragmentManager().beginTransaction().add(com.mcp1993.elantra .R.id.frame_content,fragments[1]).commit();
        img_challenge.setImageResource(R.mipmap.ic_tab_challenge_selected);
        tv_challenge.setTextColor(selectColor);
    }

    @OnClick({com.mcp1993.elantra .R.id.layout_home, com.mcp1993.elantra .R.id.layout_challenge, com.mcp1993.elantra .R.id.layout_explorer,
            com.mcp1993.elantra .R.id.layout_social, com.mcp1993.elantra .R.id.layout_profile} )
    public void onclick(View v){
        switch (v.getId()){
            case com.mcp1993.elantra .R.id.layout_home :
                index=0;
                setTabs(index);
                break;
            case com.mcp1993.elantra .R.id.layout_challenge :
                index=1;
                setTabs(index);
                break;
            case com.mcp1993.elantra .R.id.layout_explorer :
                index=2;
                setTabs(index);
                break;
            case com.mcp1993.elantra .R.id.layout_social :
                index=3;
                setTabs(index);
                break;
            case com.mcp1993.elantra .R.id.layout_profile :
                index=4;
                setTabs(index);
                break;

        }
        if(currentIndex!=index){
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.hide(fragments[currentIndex]);
            if(!fragments[index].isAdded()){
                ft.add(com.mcp1993.elantra .R.id.frame_content,fragments[index]);
            }
            ft.show(fragments[index]).commit();
        }
        currentIndex=index;
    }

    public void setTabs(int pos) {
        resetColor();
        switch (pos) {
            case 0:
                img_home.setImageResource(com.mcp1993.elantra .R.mipmap.ic_tab_sports_selected);
                tv_home.setTextColor(selectColor);
                break;
            case 1:
                img_challenge.setImageResource(com.mcp1993.elantra .R.mipmap.ic_tab_challenge_selected);
                tv_challenge.setTextColor(selectColor);
                break;
            case 2:
                img_explorer.setImageResource(com.mcp1993.elantra .R.mipmap.ic_tab_explore_selected);
                tv_explorer.setTextColor(selectColor);
                break;
            case 3:
                img_social.setImageResource(com.mcp1993.elantra .R.mipmap.ic_tab_social_selected);
                tv_social.setTextColor(selectColor);
                break;
            case 4:
                img_profile.setImageResource(com.mcp1993.elantra .R.mipmap.ic_tab_profile_selected);
                tv_profile.setTextColor(selectColor);
                break;
        }
    }

    public void resetColor(){
        img_home.setImageResource(com.mcp1993.elantra .R.mipmap.ic_tab_sports_normal);
        img_challenge.setImageResource(com.mcp1993.elantra .R.mipmap.ic_tab_challenge_normal);
        img_explorer.setImageResource(com.mcp1993.elantra .R.mipmap.ic_tab_explore_normal);
        img_social.setImageResource(com.mcp1993.elantra .R.mipmap.ic_tab_social_normal);
        img_profile.setImageResource(com.mcp1993.elantra .R.mipmap.ic_tab_profile_normal);
        tv_home.setTextColor(unSelectColor);
        tv_challenge.setTextColor(unSelectColor);
        tv_explorer.setTextColor(unSelectColor);
        tv_social.setTextColor(unSelectColor);
        tv_profile.setTextColor(unSelectColor);
    }

    @PermissionYes(100)
    private void getCAMERAYes() {
        Toast.makeText(MainActivity.this,"成功CAMERA获取",Toast.LENGTH_SHORT).show();
    }
    @PermissionNo(100)
    private void getCAMERANo() {
        Toast.makeText(MainActivity.this,"成功CAMERA失败",Toast.LENGTH_SHORT).show();
    }
    @PermissionYes(101)
    private void getSTORAGEYes() {
        Toast.makeText(MainActivity.this,"成功STORAGE获取",Toast.LENGTH_SHORT).show();
    }
    @PermissionNo(101)
    private void getSTORAGENo() {
        Toast.makeText(MainActivity.this,"成功STORAGE失败",Toast.LENGTH_SHORT).show();
    }
}
