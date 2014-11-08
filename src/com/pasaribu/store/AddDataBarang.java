package com.pasaribu.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pasaribu.store.control.AppsController;
import com.pasaribu.store.view.DialogAddSupplier;

public class AddDataBarang extends Activity 
	implements 	OnItemSelectedListener, 
				OnClickListener,
				DialogAddSupplier.DialogAddSupplierListener{
	
	final String TAG = AddDataBarang.class.getSimpleName();
	
	private AppsController aController;
	
	//Form 
	private TextView 	title_add_data_barang,
						lbl_product_name,
						lbl_product_brand,
						lbl_product_price,
						lbl_product_unit,
						lbl_product_category,
						lbl_supplier,
						lbl_product_description;
	
	private EditText 	editText_product_name,
						editText_product_price,
						editText_product_stock,
						editText_product_description;
	
	private AutoCompleteTextView 	autoComp_product_unit,
									autoComp_product_brand;
	
	private Spinner		spinner_product_category,
						spinner_supplier;
	
	private Button btn_add_supplier;
	
	private List<String> list_data_category = new ArrayList<String>();
	private List<String> list_data_supplier = new ArrayList<String>();
	private List<String> list_data_product_unit = new ArrayList<String>();
	private List<String> list_data_product_brand = new ArrayList<String>();
	
	//private DialogAddSupplier dialodAddSupplier;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_data_barang);
		
		aController = (AppsController) getApplicationContext();
		
		lbl_product_name = (TextView) findViewById(R.id.lbl_product_name);
		lbl_product_brand = (TextView) findViewById(R.id.lbl_product_brand);
		lbl_product_price = (TextView) findViewById(R.id.lbl_product_price);
		lbl_product_unit = (TextView) findViewById(R.id.lbl_product_unit);
		lbl_product_category = (TextView) findViewById(R.id.lbl_product_category);
		lbl_supplier = (TextView) findViewById(R.id.lbl_supplier);
		lbl_product_description = (TextView) findViewById(R.id.lbl_product_description);
		
		editText_product_name = (EditText) findViewById(R.id.editText_product_name);
		autoComp_product_brand = (AutoCompleteTextView) findViewById(R.id.autoComp_product_brand);
		editText_product_price = (EditText) findViewById(R.id.editText_product_price);
		editText_product_stock = (EditText) findViewById(R.id.editText_product_stock);
		autoComp_product_unit = (AutoCompleteTextView) findViewById(R.id.autoComp_product_unit);
		editText_product_description = (EditText) findViewById(R.id.editText_product_description);
		
		spinner_product_category = (Spinner) findViewById(R.id.spinner_product_category);
		spinner_supplier = (Spinner) findViewById(R.id.spinner_supplier);
		
		btn_add_supplier = (Button) findViewById(R.id.btn_add_supplier);
		
		
		
		//dialodAddSupplier.setContentView(R.layout.add_data_supplier);
		
		
		autoComp_product_brand.setThreshold(2);
		autoComp_product_unit.setThreshold(2);
		
		//Memberikan Click Listener pada view
		spinner_product_category.setOnItemSelectedListener(this);
		spinner_supplier.setOnItemSelectedListener(this);
		btn_add_supplier.setOnClickListener(this);
		
		//TODO Membuat list kategori. Berikutnya data diperoleh dari database
		list_data_category.add("Elektronik");
		list_data_category.add("Automotif");
		list_data_category.add("Lainnya");
		
		list_data_supplier.add("Hikmah Jaya");
		list_data_supplier.add("Bintang Motor");
		list_data_supplier.add("Sumatera Diesel");
		list_data_supplier.add("Sumatera Tehnik");
		list_data_supplier.add("Zaman Baru");
		
		list_data_product_unit.add("Kotak");
		list_data_product_unit.add("Lusin");
		list_data_product_unit.add("Unit");
		list_data_product_unit.add("Kaleng");
		list_data_product_unit.add("Botol");
		list_data_product_unit.add("Buah");
		list_data_product_unit.add("Plastik");
		
		list_data_product_brand.add("Samsung");
		list_data_product_brand.add("LG");
		list_data_product_brand.add("Hannocs");
		list_data_product_brand.add("Hitachi");
		list_data_product_brand.add("Aspira");
		list_data_product_brand.add("Dunlop");
		
		
		spinner_product_category.setAdapter(generateSpinnerAdapter(list_data_category));
		spinner_supplier.setAdapter(generateSpinnerAdapter(list_data_supplier));
		autoComp_product_unit.setAdapter(generateSpinnerAdapter(list_data_product_unit));
		autoComp_product_brand.setAdapter(generateSpinnerAdapter(list_data_product_brand));
		
		
	}
	
	public ArrayAdapter<String> generateSpinnerAdapter (List<String> list_data) {
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, list_data);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		
		return adapter;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_data_barang, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		
		switch (id) {
		case R.id.option_done_adding:
			
			//TODO aksi menyimpan data ke SQLite, ke MySQL jika online
			Map<String, String> data_barang_to_send = new HashMap<String, String>();
			data_barang_to_send.put("nama_barang", "Babi");	
			
			break;
			
		case R.id.action_settings :
			
			Toast.makeText(getApplicationContext(), 
					" Settings " + AddDataBarang.class.getName(), 
					Toast.LENGTH_SHORT).show();

			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	//Menangani Seleksi pada spinner
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
		editText_product_description.setText("");
		
		 
		Toast.makeText(getApplicationContext(), 
				"Selected : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
		
		editText_product_description.setText("Spinner Aktif : " + parent.getItemAtPosition(position).toString());
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
		Log.i(TAG, " Parent : " + parent.toString());		
		
		Toast.makeText(getApplicationContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		
		switch (id) {
		case R.id.btn_add_supplier:
			
			//TODO Jika mengklik tombol Tambah Penjual
			final DialogFragment dialogAddSupplier = new DialogAddSupplier();
			dialogAddSupplier.show(getFragmentManager(), "add_supplier");
			
			break;

		default:
			break;
		}
		
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Menangani positive action saat membuka dialog add supplier
				
		Toast.makeText(AddDataBarang.this, "Dialog Data : " +dialog.toString(), Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Menangani negative action saat membuka dialog add supplier
		
		Toast.makeText(AddDataBarang.this, "Negatif : " + dialog.toString(), Toast.LENGTH_LONG).show();
	}
}
