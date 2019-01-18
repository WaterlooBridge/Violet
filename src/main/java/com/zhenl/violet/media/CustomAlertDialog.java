package com.zhenl.violet.media;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zhenl.violet.R;
import com.zhenl.violet.utils.VHUtil;

import java.util.LinkedList;
import java.util.List;

public class CustomAlertDialog extends AlertDialog {

    private Context context;

    private int itemSize = 0;

    private View titleView;

    private TextView titleTextView;

    private ImageButton titleBtn;

    private ListView listView;

    private boolean isTitleVisible = false;

    private boolean isTitleBtnVisible = false;

    private String title;

    private View.OnClickListener titleListener = null;

    private List<Pair<String, Integer>> itemTextList = new LinkedList<Pair<String, Integer>>();

    private List<onSeparateItemClickListener> itemListenerList = new LinkedList<onSeparateItemClickListener>();

    private OnClickListener listListener;

    private BaseAdapter listAdapter;

    private OnItemClickListener itemListener;

    private int defaultColor = R.color.color_black_333333;

    public CustomAlertDialog(Context context) {
        super(context, R.style.dialog_default_style);
        this.context = context;
        initAdapter();
    }

    public CustomAlertDialog(Context context, int itemSize) {
        super(context, R.style.dialog_default_style);
        this.context = context;
        this.itemSize = itemSize;
    }

    private void initAdapter() {
        listAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return itemTextList.size();
            }

            @Override
            public Pair<String, Integer> getItem(int position) {
                return itemTextList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null)
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nim_custom_dialog_list_item, parent, false);
                TextView itemView = VHUtil.get(convertView, R.id.custom_dialog_text_view);
                Pair<String, Integer> pair = getItem(position);
                itemView.setText(pair.first);
                itemView.setTextColor(context.getResources().getColor(pair.second));
                return convertView;
            }
        };
        itemListener = new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemListenerList.get(position).onClick();
                dismiss();
            }
        };
    }

    public void setAdapter(final BaseAdapter adapter, final OnClickListener listener) {
        listAdapter = adapter;
        listListener = listener;
        itemListener = new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                listListener.onClick(CustomAlertDialog.this, position);
            }
        };
    }

    public void setAdapter(final BaseAdapter adapter, final OnItemClickListener listener) {
        listAdapter = adapter;
        itemListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nim_easy_alert_dialog_with_listview);
        LinearLayout root = (LinearLayout) findViewById(R.id.easy_alert_dialog_layout);
        ViewGroup.LayoutParams params = root.getLayoutParams();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        int screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
        params.width = (int) (screenMin * 0.85);
        root.setLayoutParams(params);
        addFootView(root);
        titleView = findViewById(R.id.easy_dialog_title_view);
        if (titleView != null) {
            setTitleVisible(isTitleVisible);
        }
        titleTextView = (TextView) findViewById(R.id.easy_dialog_title_text_view);
        if (titleTextView != null) {
            setTitle(title);
        }
        titleBtn = (ImageButton) findViewById(R.id.easy_dialog_title_button);
        if (titleBtn != null) {
            setTitleBtnVisible(isTitleBtnVisible);
            setTitleBtnListener(titleListener);
        }
        listView = (ListView) findViewById(R.id.easy_dialog_list_view);
        if (itemSize > 0) {
            updateListView();
        }
    }

    protected void addFootView(LinearLayout parent) {

    }

    public void setTitle(String title) {
        this.title = title;
        isTitleVisible = TextUtils.isEmpty(title) ? false : true;
        setTitleVisible(isTitleVisible);
        if (isTitleVisible && titleTextView != null) {
            titleTextView.setText(title);
        }
    }

    public void setTitle(int resId) {
        this.title = context.getString(resId);
        isTitleVisible = TextUtils.isEmpty(title) ? false : true;
        setTitleVisible(isTitleVisible);
        if (isTitleVisible && titleTextView != null) {
            titleTextView.setText(title);
        }
    }

    public void setTitleVisible(boolean visible) {
        isTitleVisible = visible;
        if (titleView != null) {
            titleView.setVisibility(isTitleVisible ? View.VISIBLE : View.GONE);
        }
    }

    public void setTitleBtnVisible(boolean visible) {
        isTitleBtnVisible = visible;
        if (titleBtn != null) {
            titleBtn.setVisibility(isTitleBtnVisible ? View.VISIBLE : View.GONE);
        }
    }

    public void setTitleBtnListener(View.OnClickListener titleListener) {
        this.titleListener = titleListener;
        if (titleListener != null && titleBtn != null) {
            titleBtn.setOnClickListener(titleListener);
        }
    }

    public void addItem(String itemText, onSeparateItemClickListener listener) {
        addItem(itemText, defaultColor, listener);
    }

    public void addItem(String itemText, int color, onSeparateItemClickListener listener) {
        itemTextList.add(new Pair<String, Integer>(itemText, color));
        itemListenerList.add(listener);
        itemSize = itemTextList.size();
    }

    public void addItem(int resId, onSeparateItemClickListener listener) {
        addItem(context.getString(resId), listener);
    }

    public void addItem(int resId, int color, onSeparateItemClickListener listener) {
        addItem(context.getString(resId), color, listener);
    }

    public void addItemAfterAnother(String itemText, String another, onSeparateItemClickListener listener) {
        int index = itemTextList.indexOf(another);
        itemTextList.add(index + 1, new Pair<String, Integer>(itemText, defaultColor));
        itemListenerList.add(index + 1, listener);
        itemSize = itemTextList.size();
    }

    public void clearData() {
        itemTextList.clear();
        itemListenerList.clear();
        itemSize = 0;
    }

    private void updateListView() {
        listAdapter.notifyDataSetChanged();
        if (listView != null) {
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(itemListener);
        }
    }

    public interface onSeparateItemClickListener {

        void onClick();
    }

    @Override
    public void show() {
        if (itemSize <= 0) {
            return;
        }
        updateListView();
        super.show();
    }

}