package com.example.final10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MyDBN {
	public SQLiteDatabase db = null;
	private final static String DATABASE_NAME = "db1.db";
	private final static String TABLE_NAME = "table02";		//資料表名稱
	private final static String _ID = "_id";		//資料表欄位
	private final static String DAY = "day";
	private final static String NAME= "name";
	private final static String COCO= "coco";
	
	//建立資料表的欄位
	private final static String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+_ID+" INTEGER PRIMARY KEY,"+DAY+" INTEGER,"+NAME+" TEXT,"+COCO+" INTEGER)";
	private Context mCtx = null;	//mCtx就是代表建立此物件的Activity
	public MyDBN(Context ctx){	//建構式
		this.mCtx = ctx;
	}
	public void open() throws SQLException {	//開啟已經存在的資料夾
		db = mCtx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		try{
			db.execSQL(CREATE_TABLE); 	//建立資料表
		}catch (Exception e){}
	}
	public void close(){	//關閉資料庫
		db.close();
	}
	public Cursor getAll(){	//查詢所有資料，取出所有欄位
		return db.query(TABLE_NAME,new String[] {_ID,DAY,NAME,COCO}, null, null, null, null, null, null);
	}
		
	public Cursor getday(long rowId) throws SQLException{	
		//查詢指定DAy資料
		Cursor mCursor = db.query(TABLE_NAME,new String[] {_ID,DAY,NAME,COCO}, DAY+"="+ rowId, null, null, null, null, null);
		if(mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	public Cursor getid(long rowId) throws SQLException{	
		//查詢指定ID資料
		Cursor mCursor = db.query(TABLE_NAME,new String[] {_ID,DAY,NAME,COCO},_ID+"="+ rowId, null, null, null, null, null);
		if(mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	public long append(int day,String name,long coco){	//新增一筆資料
		ContentValues args = new ContentValues();
		args.put(DAY, day);
		args.put(NAME, name);
		args.put(COCO, coco);
		return db.insert(TABLE_NAME, null, args);
	}
	public boolean delete(long rowId){	//刪除指定的資料
		return db.delete(TABLE_NAME, _ID+"="+rowId, null) >0;
	}
	public boolean update(long id,int day,String name,long coco){	//更新指定的資料
		ContentValues args = new ContentValues();
		args.put(DAY, day);
		args.put(NAME, name);
		args.put(COCO, coco);
		return db.update(TABLE_NAME,args, _ID+"="+id, null) >0;
	}
}
