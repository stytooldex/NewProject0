package com.example.blade.activity.All;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.blade.R;
import com.example.blade.base.BaseFLazyFragment;

import java.util.ArrayList;

import static com.example.blade.ReaderUtil.isis;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends BaseFLazyFragment {


    public BlankFragment2() {
        // Required empty public constructor
    }

    @Override
    public int setLayoutRes() {
        return R.layout.fragment_blank_fragment2;
    }


    @Override
    public void initView(View view) {
        Date(view);
    }

    private void Date(View view_GO) {
        RecyclerView r_1 = view_GO.findViewById(R.id.rec_1);
        //r_1.addItemDecoration(new RVItemDecoration(LinearLayoutCompat.VERTICAL, 24, ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.item_activated_color_F), 3));
        r_1.setLayoutManager(new GridLayoutManager(requireActivity(), 2));

        BaseQuickAdapter adapter = new BaseQuickAdapter<Main_data_1, BaseViewHolder>(R.layout.item_rv_1, createSampleData()) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, Main_data_1 item) {
                helper.setText(R.id.tv_title, item.getTitle()).setText(R.id.tv_title_1, item.getContent());
                //JsonAssetsReaderUtil.setTextMarquee(helper.getView(R.id.tv_title));
            }
        };


        r_1.setAdapter(adapter);
        //条目点击事件
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                isis(requireActivity(), Rom(view).getText().toString().trim());
            }
        });

        //adapter.openLoadAnimation(SCALEIN);
        //adapter.isFirstOnly(false);
    }

    private ArrayList<Main_data_1> createSampleData() {
        ArrayList<Main_data_1> data_1 = new ArrayList<>();
        data_1.add(new Main_data_1("2000套电视直播在线观看", "http://m.haoqu.net/", null));
        data_1.add(new Main_data_1("搜（情头）配对", "https://m.duitang.com/album/?id=89463087&start=0&limit=24", null));
        data_1.add(new Main_data_1("图片文字识别", "http://www.atoolbox.net/Tool.php?Id=735", null));
        data_1.add(new Main_data_1("智能识图题目解答", "https://dwz.cn/GASfs3qU", null));
        //data_1.add(new Main_data_1("MiKuTools", "https://tools.miku.ac/", null));
        data_1.add(new Main_data_1("爱优导", "https://sharecuts.design", null));
        //data_1.add(new Main_data_1("情头配对", "http://qq.okjike.com", null));
        data_1.add(new Main_data_1("加密/解密", "https://www.sojson.com/encrypt.html", null));
        data_1.add(new Main_data_1("APK反编译", "https://www.toolfk.com/tool-decompile-apk", null));
        //
        data_1.add(new Main_data_1("鬼畜字符生成器", "http://tool.mkblog.cn/guichu/", null));
        data_1.add(new Main_data_1("花样撤回消息", "http://tool.mkblog.cn/qqche/", null));
        data_1.add(new Main_data_1("好友克隆", "http://vip.qq.com/client/fr_index.html", null));
        data_1.add(new Main_data_1("防沉迷游戏解封", "http://gamesafe.qq.com/comm_auth.shtml", null));

        //
        data_1.add(new Main_data_1("转换视频文件", "https://www.aconvert.com/cn/video/", null));
        data_1.add(new Main_data_1("转换图像文件", "https://www.aconvert.com/cn/image/", null));
        data_1.add(new Main_data_1("转换音频文件", "https://www.aconvert.com/cn/audio/", null));
        data_1.add(new Main_data_1("转换压缩文件", "https://www.aconvert.com/cn/archive/", null));
        data_1.add(new Main_data_1("ConVerTio-文件转换器", "https://convertio.co/zh/", null));
        data_1.add(new Main_data_1("office-文件转换器", "http://t.cn/RhusCJ4", null));
        data_1.add(new Main_data_1("图片放大", "http://bigjpg.com/", null));
        data_1.add(new Main_data_1("QChan图床", "http://tool.uixsj.cn/qchan/", null));
        data_1.add(new Main_data_1("新浪图床", "http://tool.uixsj.cn/sinaimg/", null));
        //data_1.add(new Main_data_1("极简图床", "http://www.jiantuku.com/#/", null));
        data_1.add(new Main_data_1("tiny压缩图片", "https://tinypng.com/", null));
        // data_1.add(new Main_data_1("极光ROM", "https://m.weibo.cn/p/1005055272025033", null));
        //
        data_1.add(new Main_data_1("在线文本比较", "http://wenbenbijiao.renrensousuo.com/", null));
        data_1.add(new Main_data_1("手写体在线转换", "http://www.qqxiuzi.cn/zh/shouxie-shufa/", null));
        data_1.add(new Main_data_1("制作尖叫字体图片", "http://tool.uixsj.cn/jjzt/", null));
        data_1.add(new Main_data_1("网盘链接密码查看", "https://jlwz.cn/wapindex-1000-369.html", null));
        data_1.add(new Main_data_1("星座屋-(星座运势)", "https://m.xzw.com/", null));
        //
        data_1.add(new Main_data_1("小米ROM历史版本", "http://rom.52mail.cn/", null));
        data_1.add(new Main_data_1("MIui国际", "http://en.miui.com/getrom.php?m=yes", null));
        data_1.add(new Main_data_1("MIui官方", "http://www.miui.com/getrom.php?m=yes", null));
        data_1.add(new Main_data_1("MIui意大利", "http://www.miui.it/#", null));
        data_1.add(new Main_data_1("MIui俄罗斯", "https://miui.su/download", null));
        data_1.add(new Main_data_1("MIui希腊", "https://xiaomi-miui.gr/community/wsif/index.php/CategoryList/?s=22ae3404fd063af2312f867fbecc84df8fc1eab1", null));
        data_1.add(new Main_data_1("MIui波兰", "https://miuipolska.pl/download/", null));
        //data_1.add(new Main_data_1("MIui台湾", "http://tw.miui.com/extra.php?mod=rom/download&mobile=no", null));
        data_1.add(new Main_data_1("大佬站点", "https://sagit.sbwml.net/", null));
        // data_1.add(new Main_data_1("XDA的搬运工", "http://srom.tk/", null));
        //data_1.add(new Main_data_1("RomBox", "https://www.71geek.com/", null));
        //
        return data_1;
    }

    private TextView Rom(View view) {
        return view.findViewById(R.id.tv_title_1);
    }
}
