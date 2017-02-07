package com.example.final10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MyDBN {
	public SQLiteDatabase db = null;
	private final static String DATABASE_NAME = "db1.db";
	private final static String TABLE_NAME = "table02";		//��ƪ�W��
	private final static String _ID = "_id";		//��ƪ����
	private final static String DAY = "day";
	private final static String NAME= "name";
	private final static String COCO= "coco";
	
	//�إ߸�ƪ����
	private final static String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+_ID+" INTEGER PRIMARY KEY,"+DAY+" INTEGER,"+NAME+" TEXT,"+COCO+" INTEGER)";
	private Context mCtx = null;	//mCtx�N�O�N��إߦ�����Activity
	public MyDBN(Context ctx){	//�غc��
		this.mCtx = ctx;
	}
	public void open() throws SQLException {	//�}�Ҥw�g�s�b����Ƨ�
		db = mCtx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		try{
			db.execSQL(CREATE_TABLE); 	//�إ߸�ƪ�
		}catch (Exception e){}
	}
	public void close(){	//������Ʈw
		db.close();
	}
	public Cursor getAll(){	//�d�ߩҦ���ơA���X�Ҧ����
		return db.query(TABLE_NAME,new String[] {_ID,DAY,NAME,COCO}, null, null, null, null, null, null);
	}
		
	public Cursor getday(long rowId) throws SQLException{	
		//�d�߫��wDAy���
		Cursor mCursor = db.query(TABLE_NAME,new String[] {_ID,DAY,NAME,COCO}, DAY+"="+ rowId, null, null, null, null, null);
		if(mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	public Cursor getid(long rowId) throws SQLException{	
		//�d�߫��wID���
		Cursor mCursor = db.query(TABLE_NAME,new String[] {_ID,DAY,NAME,COCO},_ID+"="+ rowId, null, null, null, null, null);
		if(mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	public long append(int day,String name,long coco){	//�s�W�@�����
		ContentValues args = new ContentValues();
		args.put(DAY, day);
		args.put(NAME, name);
		args.put(COCO, coco);
		return db.insert(TABLE_NAME, null, args);
	}
	public boolean delete(long rowId){	//�R�����w�����
		return db.delete(TABLE_NAME, _ID+"="+rowId, null) >0;
	}
	public boolean update(long id,int day,String name,long coco){	//��s���w�����
		ContentValues args = new ContentValues();
		args.put(DAY, day);
		args.put(NAME, name);
		args.put(COCO, coco);
		return db.update(TABLE_NAME,args, _ID+"="+id, null) >0;
	}
}
