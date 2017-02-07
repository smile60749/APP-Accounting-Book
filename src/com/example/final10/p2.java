package com.example.final10;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class p2 extends Activity {
	private MyDBN db = null;
	Button btnadd,btnsh,btnlook,btnback;
	EditText editday, editcoco,editname;
	TextView txtid;
	Cursor cursor;
	long myid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.p2);
		btnadd=(Button)findViewById(R.id.btn2add);
		btnsh=(Button)findViewById(R.id.btn2sh);
		btnlook=(Button)findViewById(R.id.btn2look);
		btnback=(Button)findViewById(R.id.btn2back);
		editname=(EditText)findViewById(R.id.edit4item);
		editcoco=(EditText)findViewById(R.id.edit4coco);
		editday=(EditText)findViewById(R.id.edit4day);
		
		btnadd.setOnClickListener(listener);
		btnsh.setOnClickListener(listener);
		btnlook.setOnClickListener(listener);
		btnback.setOnClickListener(listener);
		
		db=new MyDBN(this);		//建立MyDB物件
		db.open();
	}
	private Button.OnClickListener listener = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try
			{
				if(v.getId()==R.id.btn2sh)
				{
					int day=Integer.parseInt(editday.getText().toString());
					cursor=db.getday(day);
					Intent intentp25 = new Intent();
					intentp25.setClass(p2.this, p5.class);
					Bundle bundlep25 = new Bundle();
					bundlep25.putInt("day",day);
					intentp25.putExtras(bundlep25);
					startActivity(intentp25);
				}
				else if(v.getId()==R.id.btn2add)
				{
					int day = Integer.parseInt(editday.getText().toString());
					String name=editname.getText().toString();
					long coco=Long.parseLong(editcoco.getText().toString());
					if(db.append(day,name,coco)>0)
						ClearEdit();
					Toast.makeText(getApplicationContext(), "新增成功", Toast.LENGTH_SHORT).show();
				}
				
				else if(v.getId()==R.id.btn2back)
				{
					finish();
				}
				else if(v.getId()==R.id.btn2look)
				{
					Intent intent=new Intent();
					intent.setClass(p2.this, p3.class);
					startActivity(intent);
				}
			}
			catch(Exception err){
				Toast.makeText(getApplicationContext(), "資料不正確", Toast.LENGTH_SHORT).show();
			}
		}
		
	};
	
	public void ClearEdit(){
		editday.setText("");
		editcoco.setText("");
		editname.setText("");
	}
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		ClearEdit();
	}
}
