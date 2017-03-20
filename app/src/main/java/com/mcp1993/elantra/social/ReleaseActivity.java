package com.mcp1993.elantra.social;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.mcp1993.elantra.R;
import com.mcp1993.elantra.social.adapter.GridLayoutItemDecoration;
import com.mcp1993.elantra.social.adapter.ReleaseImgRecyAdapter;
import com.mcp1993.elantra.social.adapter.SelectTagRecyAdapter;
import com.mcp1993.elantra.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class ReleaseActivity extends AppCompatActivity {
    @BindView(R.id.recy_tag_selector)
    RecyclerView recy_tag_selector;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.et_title)
    EditText et_title;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.recy_erlease_imgs_selector)
    RecyclerView recy_erlease_imgs_selector;
    @BindView(R.id.txt_adress)
    TextView txt_adress;
    private SelectTagRecyAdapter selectTagRecyAdapter;
    private ReleaseImgRecyAdapter releaseImgRecyAdapter;
    private List<String> TagDatas = new ArrayList<>();
    private List<String> ImgPaths = new ArrayList<>();

    //声明AMapLocationClient类对象
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();
    InputMethodManager imm;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        ButterKnife.bind(this);
        initImgRecy();
        initTagRecy();
        startLocation();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }
    /*
    * 初始化选择图片RecyclerView
    * */
    private void initImgRecy(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ReleaseActivity.this,5);
        recy_erlease_imgs_selector.setLayoutManager(gridLayoutManager);
        recy_erlease_imgs_selector.setItemAnimator(new DefaultItemAnimator());
        GridLayoutItemDecoration itemDecoration = new GridLayoutItemDecoration(3);
        itemDecoration.setDivideParams(10, 10);
        recy_erlease_imgs_selector.addItemDecoration(itemDecoration);
//        recy_erlease_imgs_selector.setNestedScrollingEnabled(false);
        releaseImgRecyAdapter = new ReleaseImgRecyAdapter(ImgPaths,ReleaseActivity.this);
        recy_erlease_imgs_selector.setAdapter(releaseImgRecyAdapter);
        releaseImgRecyAdapter.setOnItemClickListener(new ReleaseImgRecyAdapter.OnItemClickListener() {
            @Override
            public void onClick(RecyclerView.ViewHolder VH, int position) {
                //点击弹出弹窗
                showDialog();
            }
        });
    }
    private void showDialog(){

       new AlertDialog.Builder(this).setItems(new String[]{"打开相机", "打开图库"}, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               switch (i){

                   case  0:
                       Toast.makeText(ReleaseActivity.this,"打开相机",Toast.LENGTH_SHORT).show();
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                       startActivityForResult(intent, 0x12);
                       break;
                   case  1:
                       intent = new Intent(Intent.ACTION_GET_CONTENT);
                       intent.setType("image/*");
                       startActivityForResult(intent, 0x13);
                       Toast.makeText(ReleaseActivity.this,"打开图库",Toast.LENGTH_SHORT).show();
                       break;
               }
           }
       }).show();

    }
    private void initTagRecy(){
        recy_tag_selector.setLayoutManager(new LinearLayoutManager(ReleaseActivity.this, LinearLayoutManager.HORIZONTAL ,false));
        recy_tag_selector.setHasFixedSize(true);
        recy_tag_selector.setItemAnimator(new DefaultItemAnimator());
        recy_tag_selector.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildPosition(view) !=-1){
                    outRect.right = 20;
                }
            }
        });
        setTagData();
        selectTagRecyAdapter = new SelectTagRecyAdapter(TagDatas,ReleaseActivity.this);
        recy_tag_selector.setAdapter(selectTagRecyAdapter);
        selectTagRecyAdapter.setOnItemClickListener(new SelectTagRecyAdapter.OnItemClickListener() {
            @Override
            public void onClick(RecyclerView.ViewHolder VH, int position) {
                Toast.makeText(ReleaseActivity.this,"postion +"+position,Toast.LENGTH_SHORT).show();
                String oldContent = et_content.getText().toString();
                String newContent = oldContent + TagDatas.get(position);
                et_content.setText(newContent);
                recy_tag_selector.setFocusable(true);
//                et_content.setFocusable(false);
                et_content.clearFocus(); //清除焦点
                imm.hideSoftInputFromWindow(et_content.getWindowToken(),0);
            }
        }, new SelectTagRecyAdapter.OnItemLastClickListener() {
            @Override
            public void onClick() {
                //光标设置在倒数第二个字符
                String oldContent = et_content.getText().toString();
                String newContent = oldContent +"##";
                et_content.setText(newContent);
                et_content.requestFocus();
                et_content.setSelection(newContent.length()-1);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.SHOW_FORCED);
//                imm.showSoftInput(et_content,InputMethodManager.SHOW_FORCED);
                Toast.makeText(ReleaseActivity.this,"最后一个",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setTagData(){
        TagDatas.add(0,new String("#越运动，越年轻#"));
        TagDatas.add(1,new String("#运动，遇见更好的自己#"));
        TagDatas.add(2,new String("#运动会上瘾#"));
        TagDatas.add(3,new String("#新年新气象#"));
        TagDatas.add(4,new String("#自定义标签#"));
    }

    @OnClick(R.id.et_content)
    public void setETContent(View view){
        et_content.setFocusable(true);
        et_content.setFocusableInTouchMode(true);
        et_content.requestFocus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 从相机跳回来
        if (requestCode == 0x12) {
            if (data == null) {
                return;
            } else {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Bitmap bitmap = bundle.getParcelable("data");
                    // 记住： 要转化为 file 类型的Uri
                    Uri uri = FileUtils.saveBitmap(bitmap);
                    // 启动裁剪器
                    starImageZoom(uri);
                }
            }
        }
// 从图库跳回来
        if (requestCode == 0x13) {
            if (data == null) {
                return;
            }
            Uri uri;
            // 此处的uri 是content类型的。 还有一种是file 型的。应该转换为后者
            uri = data.getData();
            // 记住： 要转化为file类型的uri
            Uri fileuUri = FileUtils.converUri(ReleaseActivity.this,uri);
            // 启动裁剪
            starImageZoom(fileuUri);

        }
        // 从裁剪处跳回来
        if (requestCode == 0x14) {
            if (data == null) {
                return;
            }
            Bundle bundle = data.getExtras();
            Bitmap bitmap = bundle.getParcelable("data");
            Uri uri =FileUtils.saveBitmap(bitmap);

            ImgPaths.add(getRealFilePath(ReleaseActivity.this,uri));
            imageView.setImageBitmap(bitmap);
        }

    }
    public static String getRealFilePath( final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 通过 file 类型的 uri 去启动系统图片裁剪器
     *
     * @param uri
     */
    private void starImageZoom(Uri uri) {
        // 打开裁剪器
        Intent intent = new Intent("com.android.camera.action.CROP");
        // 设置 裁剪的数据uri 和类型 image
        intent.setDataAndType(uri, "image/*");
        // 是可裁剪的
        intent.putExtra("crop", true);
        // 设置裁剪宽高的比例 1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 设置最终裁剪出来的图片的宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        // 设置 最终裁剪完是通过intent 传回来的
        intent.putExtra("return-data", true);
        // 授予目录临时共享权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, 0x14);
    }

   @OnClick(R.id.iv_back)
    public void goBack(){
       finish();
   }

    private void startLocation(){
        if (null == locationOption) {
            locationOption = new AMapLocationClientOption();
        }
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //初始化client
        locationClient = new AMapLocationClient(getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);

        // 启动定位
        locationClient.startLocation();
    }

    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //解析定位结果
//                String result = LocationUtil.getLocationStr(loc);
                String result = loc.getAddress();
                txt_adress.setText(result);

            } else {
                txt_adress.setText("定位失败，loc is null");
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }
    /*
    * 销毁定位
    */
    private void destroyLocation(){
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }
}
