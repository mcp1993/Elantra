package com.mcp1993.elantra.social;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.mcp1993.elantra.R;
import com.mcp1993.elantra.social.adapter.GridLayoutItemDecoration;
import com.mcp1993.elantra.social.adapter.SelectImgRecyAdapter;
import com.mcp1993.elantra.social.imgselector.ImageConfig;
import com.mcp1993.elantra.social.imgselector.ImageSelector;
import com.mcp1993.elantra.social.imgselector.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/21 0021.
 */

public class ReplyActivity extends AppCompatActivity {
    @BindView(R.id.recy_imgs_selector)
    RecyclerView recy_imgs_selector;
    @BindView(R.id.edit_content)
    EditText edit_content;
    @BindView(R.id.tv_et_changed)
    TextView  tv_et_changed;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    private SelectImgRecyAdapter adapter;

    private ArrayList<String> paths = new ArrayList<>();

    public static final int REQUEST_CODE = 123;
    private int MAX_LENGTH = 6;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, R.color.bottom_text_color_normal, false);

        setEdit();
        initRecy();
    }
    @Nullable
    @OnClick(R.id.iv_back)
    public void goBack(View view){
        finish();
    }
    //设置edittext字数
    private void setEdit(){
        edit_content.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void afterTextChanged(Editable s) {
                selectionStart = edit_content.getSelectionStart();
                selectionEnd = edit_content.getSelectionEnd();
                if (temp.length() > MAX_LENGTH) {
                    Toast.makeText(ReplyActivity.this, "只能输入6个字",
                            Toast.LENGTH_SHORT).show();
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    edit_content.setText(s);
                    edit_content.setSelection(tempSelection);
                }else {
                    tv_et_changed.setText(temp.length()+"/200");
                }
            }
        });
        }
    private void initRecy(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ReplyActivity.this,3);
        recy_imgs_selector.setLayoutManager(gridLayoutManager);
        GridLayoutItemDecoration itemDecoration = new GridLayoutItemDecoration(3);
        itemDecoration.setDivideParams(20, 20);
        recy_imgs_selector.addItemDecoration(itemDecoration);
        adapter = new SelectImgRecyAdapter(paths,ReplyActivity.this);
        adapter.setOnItemClickListener(new SelectImgRecyAdapter.OnItemClickListener() {
            @Override
            public void onClick(RecyclerView.ViewHolder VH, int position) {
                ImageConfig imageConfig = new ImageConfig.Builder(
                        // GlideLoader 可用自己用的缓存库
                        new PicassoLoader())
                        // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                        .steepToolBarColor(getResources().getColor(R.color.titleBlue))
                        // 标题的背景颜色 （默认黑色）
                        .titleBgColor(getResources().getColor(R.color.titleBlue))
                        // 提交按钮字体的颜色  （默认白色）
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        // 标题颜色 （默认白色）
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）  (单选 为 singleSelect)
                        //.singleSelect()
                        //裁剪
                        //.crop()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(9)
                        // 已选择的图片路径
                        .pathList(paths)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/temp")
                        // 开启拍照功能 （默认开启）
                        .showCamera()
                        .requestCode(REQUEST_CODE)
                        .build();
                ImageSelector.open(ReplyActivity.this, imageConfig);
                Toast.makeText(ReplyActivity.this,"1111111111111",Toast.LENGTH_SHORT).show();
            }
        });
        recy_imgs_selector.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            paths.clear();
            paths.addAll(pathList);
            adapter.notifyDataSetChanged();

        }
    }
}
