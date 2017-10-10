package com.example.lesson31_view_size_location;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 怪蜀黍 on 2016/12/27.
 */

public class MyEdit extends LinearLayout {
    private EditText input;
    private ImageView del;
    private TextView lable;

    public MyEdit(Context context) {
        this(context, null);
    }

    public MyEdit(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.VERTICAL);
//        setBackgroundResource(R.drawable.selector_edit_bg);
//        初始化
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_edit, this);//直接添加到布局
        input = (EditText) findViewById(R.id.input);
        lable = (TextView) findViewById(R.id.lable);
        del = (ImageView) findViewById(R.id.del);
        del.setOnClickListener(clickLis);
        input.setOnFocusChangeListener(focusChangeLis);//有焦点时发生
        input.addTextChangedListener(watcher);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.MyEdit);
        for (int i = 0; i < array.length(); i++) {
            int index = array.getIndex(i);//获取指定下标的属性的index
            switch (index) {
                case R.styleable.MyEdit_mText:
                    input.setText(array.getString(index));
                    break;
                case R.styleable.MyEdit_maxLength:
                    int maxLength = array.getInt(index, -1);
                    if (maxLength != -1) {
                        input.setFilters(new InputFilter[]{new InputFilter.LengthFilter.LengthFilter(maxLength)});
                    }
                    break;
                case R.styleable.MyEdit_mHint:
                    input.setHint(array.getString(index));
                    break;
                case R.styleable.MyEdit_mInputType:
                    int v = array.getInt(index, 0);
                    switch (v) {
                        case 0:
                            input.setInputType(InputType.TYPE_CLASS_TEXT);
                            break;
                        case 1:
                            input.setInputType(InputType.TYPE_CLASS_NUMBER);
                            break;
                        case 2:
                            input.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            break;
                    }
                    break;
                case R.styleable.MyEdit_mTextColor:
                    input.setTextColor(array.getColor(index, Color.BLACK));
                    break;
                case R.styleable.MyEdit_mTextSize:

                    break;
            }
        }
    }

    /**
     * 文本改变监听
     */
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                del.setVisibility(VISIBLE);
            } else {
                del.setVisibility(GONE);
            }
        }
    };
    /**
     * 焦点的改变监听
     */
    private OnFocusChangeListener focusChangeLis = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            MyEdit.this.setSelected(hasFocus);
//            有焦点，并且文本框的长度大于0，这是，隐藏的内容才显示
            if (hasFocus && input.getText().length() > 0) {
                del.setVisibility(VISIBLE);
            } else {
                del.setVisibility(GONE);
            }
        }
    };

    /**
     * 点击监听
     */
    private OnClickListener clickLis = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.del:
//                    清除文本
                    input.getText().clear();
                    break;
            }
        }
    };

    public void setLable(String lable) {
        this.lable.setText(lable);
    }

    public Editable getText() {
        return input.getText();
    }

    public void setText(CharSequence text) {
        input.setText(text);
    }

    public void setHint(String hint) {
        input.setHint(hint);
    }

    public void setTextSize(int size) {
        input.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }
}
