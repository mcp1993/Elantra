package com.mcp1993.elantra.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcp1993.elantra.R;
import com.mcp1993.elantra.base.MFragment;
import com.mcp1993.elantra.profile.adapter.MineAdapter;
import com.mcp1993.elantra.social.circularimage.CircularImage;
import com.mcp1993.elantra.view.MyGridView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class ProfileFragment extends MFragment<ProfilePresenter> implements ProfileView {

    public static ProfileFragment profileFragment;
    @BindView(R.id.cirImg_person)
    CircularImage cirImg_person;
    @BindView(R.id.tv_person_name)
    TextView tv_person_name;
    @BindView(R.id.tv_profile_lv)
    TextView tv_profile_lv;
    @BindView(R.id.gv_mine)
    MyGridView gv_mine;
    private int requestCode = 0x001;
    private MineAdapter mineAdapter;


    @Override
    public ProfilePresenter createPresenter() {
        return new ProfilePresenter(this);
    }

    public static ProfileFragment getInstance(){
        if (profileFragment!=null){
            return profileFragment;
        }
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileFragment=new ProfileFragment();
        View view = inflater.inflate(R.layout.fragment_profile, null);
        ButterKnife.bind(this,view);
        mineAdapter = new MineAdapter(getActivity());
        gv_mine.setAdapter(mineAdapter);
        return view;
    }

    @OnClick(R.id.cirImg_person)
    public void goLogin(){
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivityForResult(intent,requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case 0x002:
                Picasso.with(getActivity()).load(data.getExtras()
                        .getString("profile_image_url"))
                        .into(cirImg_person);
                tv_person_name.setText(data.getExtras().getString("screen_name"));
                tv_profile_lv.setText("LV"+data.getExtras().getString("vip"));
                break;
            case 0x003:
                Picasso.with(getActivity()).load(data.getExtras()
                        .getString("iconurl"))
                        .into(cirImg_person);
                tv_person_name.setText(data.getExtras().getString("screen_name"));
                tv_profile_lv.setText("LV"+data.getExtras().getString("statuses_count"));
        }

    }
}
