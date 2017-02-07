package com.example.final10;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class p3 extends Activity {

	private ListView listview;
	private MyDBN db=null;
	private Cursor cursor;
	private Button btnp3;
	long myid,sum=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.p3);
		btnp3=(Button)findViewById(R.id.btnp3);
		btnp3.setOnClickListener(l);
		
		listview=(ListView)findViewById(R.id.lvv1);
		listview.setOnItemClickListener(ol);
		
		db=new MyDBN(this);
		db.open();
		cursor=db.getAll();
		UpdateAdapter(cursor);
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.final10, menu);
		return true;
	}

	private Button.OnClickListener l =new Button.OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0.getId()==R.id.btnp3)
			{
				Cursor c=db.getAll();
				for(int i=1;i<=c.getCount();i++)
				{
					Cursor cc=db.getid(i);
					long coco =Long.parseLong(cc.getString(3));
					sum=sum+coco;
				}
				Toast.makeText(getApplicationContext(), "總共花費為"+sum+"元", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	private ListView.OnItemClickListener ol=new ListView.OnItemClickListener(){
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long id) {
			// TODO Auto-generated method stub
			
			Intent intent = new Intent();
			intent.setClass(p3.this, p4.class);
			Cursor c=db.getid(id);
			myid=id;
			int _id=Integer.parseInt(c.getString(0));
			int day =Integer.parseInt(c.getString(1));
			String name =c.getString(2);
			long coco =Long.parseLong(c.getString(3));
			
			Bundle bundle = new Bundle();
			bundle.putInt("day",day);
			bundle.putString("name",name);
			bundle.putLong("coco", coco);
			bundle.putInt("_id",_id);
			intent.putExtras(bundle);
			cursor.moveToPosition(arg2);
			startActivity(intent);
		}
	};
	
	void UpdateAdapter(Cursor cursor){
		if(cursor != null && cursor.getCount()>=0){
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					R.layout.mylayout,//改成自己LAYOUT
					cursor,
					new String[]{"day", "name", "coco"},//
					new int[] {R.id.txtday,R.id.txtname,R.id.txtcoco},//
					0);
			listview.setAdapter(adapter);
		}
	}
	
	protected void onDestroy(){
		super.onDestroy();
		db.close();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id =item.getItemId();
		if (id == R.id.about) {
			Toast.makeText(getApplicationContext(), "10211226  10211248", Toast.LENGTH_SHORT).show();
		}
		else if (id == R.id.back) 
			finish();
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		cursor=db.getAll();
		UpdateAdapter(cursor);
	}

}
