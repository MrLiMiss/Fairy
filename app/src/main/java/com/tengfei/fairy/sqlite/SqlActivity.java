package com.tengfei.fairy.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Sqlite 相关测试
 */
public class SqlActivity extends BaseActivity {

    @BindView(R.id.btn_insert)
    Button btn_insert;
    @BindView(R.id.btn_update)
    Button btn_update;
    @BindView(R.id.btn_query)
    Button btn_query;
    @BindView(R.id.btn_delete)
    Button btn_delete;

    private Context mContext;
    private SQLiteDatabase db;
    private MyDBOpenHelper myDBHelper;
    private StringBuilder sb;
    private int i = 1;
    
    @OnClick({R.id.btn_insert,R.id.btn_update,R.id.btn_query,R.id.btn_delete})
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_insert:
                insert();
                break;
            case R.id.btn_query:
                query();
                break;
            case R.id.btn_update:
                update();
                break;
            case R.id.btn_delete:
                delete();
                break;
            default:
                break;
        }
    }

    private void delete() {
        //参数依次是表名，以及where条件与约束
        db.delete("person", "personid = ?", new String[]{"3"});

    }

    private void update() {
        ContentValues values2 = new ContentValues();
        values2.put("name", "嘻嘻~");
        //参数依次是表名，修改后的值，where条件，以及约束，如果不指定三四两个参数，会更改所有行
        db.update("person", values2, "name = ?", new String[]{"呵呵~2"});
    }

    private void query() {
        sb = new StringBuilder();
        //参数依次是:表名，列名，where约束条件，where中占位符提供具体的值，指定group by的列，进一步约束
        //指定查询结果的排序方式
        Cursor cursor = db.query("person", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int pid = cursor.getInt(cursor.getColumnIndex("personid"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                sb.append("id：" + pid + "：" + name + "\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        Toast.makeText(mContext, sb.toString(), Toast.LENGTH_SHORT).show();
    }

    private void insert() {
        ContentValues values1 = new ContentValues();
        values1.put("name", "呵呵~" + i);
        i++;
        //参数依次是：表名，强行插入null值得数据列的列名，一行记录的数据
        db.insert("person", null, values1);
        Toast.makeText(mContext, "插入完毕~", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_sql;

    }

    @Override
    protected void initGui() {
        mContext = SqlActivity.this;
        myDBHelper = new MyDBOpenHelper(mContext, "my.db", null, 1);
        db = myDBHelper.getWritableDatabase();

    }

    @Override
    protected void initAction() {


    }

    @Override
    protected void initData() {

    }
}
