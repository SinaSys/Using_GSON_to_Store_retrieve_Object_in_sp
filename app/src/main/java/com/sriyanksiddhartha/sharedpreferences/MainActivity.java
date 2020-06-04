package com.sriyanksiddhartha.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Author : Sriyank Siddhartha
 * Module 3 : Using GSON to Save and Retrieve Non-primitive Data Type
 *
 * 		"AFTER" Demo Project
 * */
public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private TextView txvDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txvDisplay = (TextView) findViewById(R.id.txvDisplay);
	}

	public void saveObjectType(View view) {

		Employee employee = getEmployee();

		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

		// Serialization
		Gson gson = new Gson();
		String jsonStr = gson.toJson(employee, Employee.class);
		Log.i(TAG + " SAVE", jsonStr);

		prefsEditor.putString("employee_key", jsonStr);
		prefsEditor.apply();
	}

	public void loadObjectType(View view) {

		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		String jsonStr = sharedPreferences.getString("employee_key", "");
		Log.i(TAG + " LOAD", jsonStr);

		// Deserialization
		Gson gson = new Gson();
		Employee employeeObj = gson.fromJson(jsonStr, Employee.class);

		displayText(employeeObj);
	}



	private Employee getEmployee() {

		Employee employee = new Employee();
		employee.setName("Sriyank Siddhartha");
		employee.setProfession("Android Developer");
		employee.setProfId(287);
		employee.setRoles(Arrays.asList("Developer", "Admin"));

		return employee;
	}

	private void displayText(Employee employeeObj) {

		if (employeeObj == null)
			return;

		String display = employeeObj.getName()
				+ "\n" + employeeObj.getProfession()
				+ " - " + employeeObj.getProfId()
				+ "\n" + employeeObj.getRoles().toString();

		txvDisplay.setText(display);
	}
}
