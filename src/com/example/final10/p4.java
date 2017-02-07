package com.example.final10;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class p4 extends Activity {
	private MyDBN db = null;
	Button btnedit,btndel;
	EditText editday, editcoco,editname;
	Cursor cursor;
	long myid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.p4);
		
		btnedit=(Button)findViewById(R.id.btnedit4);
		btndel=(Button)findViewById(R.id.btndel4);
		
		editname=(EditText)findViewById(R.id.edititem4);
		editcoco=(EditText)findViewById(R.id.editcoco4);
		editday=(EditText)findViewById(R.id.editday4);
		
		btnedit.setOnClickListener(listener);
		btndel.setOnClickListener(listener);

		db=new MyDBN(this);		//建立MyDB物件
		db.open();
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		int day = bundle.getInt("day");
		String name = bundle.getString("name");
		long coco = bundle.getLong("coco");
		int _id=bundle.getInt("_id");
		
		editday.setText(day+"");
		editcoco.setText(coco+"");
		editname.setText(name);
		
		cursor=db.getid(_id);
		myid=_id;
	}
	
	private Button.OnClickListener listener = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try
			{
				if(v.getId()==R.id.btndel4)
				{
					if(cursor!=null&&cursor.getCount()>=0)
					{
						AlertDialog.Builder bu=new AlertDialog.Builder(p4.this);
						bu.setTitle("確定刪除");
						bu.setMessage("確定要刪除這筆資料?");
						bu.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								
							}
						});
						bu.setPositiveButton("確定 ", 
								new DialogInterface.OnClickListener(){
									public void onClick(DialogInterface dialog, int i){
										if(db.delete(myid))
											ClearEdit();
										finish();
									}
								});
						bu.show();
						
					}
					
				}
				else if(v.getId()==R.id.btnedit4)
				{
					String name2=editname.getText().toString();
					long coco2=Long.parseLong(editcoco.getText().toString());
					int day2= Integer.parseInt(editday.getText().toString());
					if(db.update(myid,day2,name2,coco2)){
						ClearEdit();
					}
					finish();
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.final10, menu);
		return true;
	}
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
}
