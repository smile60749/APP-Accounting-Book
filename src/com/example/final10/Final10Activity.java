package com.example.final10;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Final10Activity extends Activity {
	
	private EditText editid,editpw;
	private Button btnenter,btnclean,btnadd;
	private String[] login;
	private static final String filename="login.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final10);
		setTitle("登入畫面");
		
		editid=(EditText)findViewById(R.id.editid);
		editpw=(EditText)findViewById(R.id.editpw);
		btnenter=(Button)findViewById(R.id.btnenter);
		btnclean=(Button)findViewById(R.id.btnclean);
		btnadd=(Button)findViewById(R.id.btn2edit);
		
		btnenter.setOnClickListener(l);
		btnclean.setOnClickListener(l);
		btnadd.setOnClickListener(l);
		
		FileInputStream file=null;
		BufferedInputStream bf=null;
		String data="";
		try
		{
			file=openFileInput(filename);
			bf=new BufferedInputStream(file);
			byte[] buf=new byte[20];
			data="";
			do
			{
				int fla=bf.read(buf);
				if(fla==-1) break;
				else data += new String(buf);
			}
			while(true);
			buf.clone();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		login=data.split(" ");
		
	}
	private Button.OnClickListener l=new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.btnenter:
				
				if(editid.getText().toString().equals("")||editpw.getText().toString().equals("")){	//如果帳號密碼欄未輸入
					Toast.makeText(getApplicationContext(), "請輸入帳號密碼", Toast.LENGTH_SHORT).show();
					break;}
				Boolean flag=false;
				for(int i=0;i<login.length;i+=2){
					if(editid.getText().toString().equals(login[i])){
						flag=true;
						if(editpw.getText().toString().equals(login[i+1])){
							new AlertDialog.Builder(Final10Activity.this)
							.setTitle("登入")
							.setMessage("登入成功~\n你快破產了!!")
							.setPositiveButton("確定",new DialogInterface.OnClickListener() 
							{
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									Intent intent=new Intent();
									intent.setClass(Final10Activity.this, p2.class);
									startActivity(intent);
								}
							})
							.show();
							break;
						}else{
							Toast.makeText(getApplicationContext(),"密碼不正確", Toast.LENGTH_SHORT).show();
							editpw.setText("");
							break;
						}
					}
				}
				if(!flag){
					Toast.makeText(getApplicationContext(), "帳號不正確", Toast.LENGTH_SHORT).show();
					editid.setText("");
					editpw.setText("");
				}
				break;
			case R.id.btnclean:
				editid.setText("");
				editpw.setText("");
				break;
			case R.id.btn2edit:
				if(editid.getText().toString().equals("")||editpw.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "欄位不能為空白", Toast.LENGTH_SHORT).show();
					break;
				}
				Boolean flag2=true;
				for(int i=0;i<login.length;i+=2){
					if(editid.getText().toString().equals(login[i]))
						flag2=false;
					}
				if(flag2){
					FileOutputStream fo=null;
					BufferedOutputStream bo=null;
					try
					{
						fo=openFileOutput(filename,MODE_APPEND);
						bo=new BufferedOutputStream(fo);
						bo.write(editid.getText().toString().getBytes());
						bo.write(" ".getBytes());
						bo.write(editpw.getText().toString().getBytes());
						bo.write(" ".getBytes());
						bo.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					editid.setText("");
					editpw.setText("");
					Toast.makeText(getApplicationContext(), "新增成功", Toast.LENGTH_SHORT).show();
					onRestart();
				}
				else
					Toast.makeText(getApplicationContext(),"此帳號存在", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		editid.setText("");
		editpw.setText("");
		FileInputStream file=null;
		BufferedInputStream bf=null;
		String data="";
		try
		{
			file=openFileInput(filename);
			bf=new BufferedInputStream(file);
			byte[] buf=new byte[20];
			data="";
			do
			{
				int fla=bf.read(buf);
				if(fla==-1) break;
				else data += new String(buf);
			}
			while(true);
			buf.clone();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		login=data.split(" ");	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.final10, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.about) {
			Toast.makeText(getApplicationContext(), "10211226 & 10211248", Toast.LENGTH_SHORT).show();
		}
		else if (id == R.id.back) 
			finish();
		return super.onOptionsItemSelected(item);
	}
}
