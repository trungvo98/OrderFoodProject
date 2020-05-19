package com.example.orderfood.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfood.DAO.EmployeeDAO;
import com.example.orderfood.DAO.RuleDAO;
import com.example.orderfood.R;
import com.example.orderfood.models.Employee;
import com.example.orderfood.models.Rule;

import java.util.ArrayList;
import java.util.List;

public class SignIn extends AppCompatActivity implements View.OnClickListener,View.OnFocusChangeListener {
    EditText etUsername, etPassword, etBirthday, etPhoneNumber;
    TextView tvTitle;
    RadioGroup rgGenre;
    RadioButton rdioMale,rdioFemale;
    Button btConfirm, btExit;
    Spinner spinRule;
    RelativeLayout rlRule;
    String genre;
    EmployeeDAO employeeDAO;
    RuleDAO ruleDAO;
    int idEmploy=0;
    int firstSignin = 0;
    List<Rule> listRule;
    List<String> nameRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        employeeDAO = new EmployeeDAO(this);
        ruleDAO = new RuleDAO(this);
        listRule = ruleDAO.getAllList();
        if (listRule.isEmpty()){
            ruleDAO.insert("admin");
            ruleDAO.insert("employ");
            listRule = ruleDAO.getAllList();
        }
        addControls();

        firstSignin = getIntent().getIntExtra("firstSignin",0);

        if(firstSignin == 0){
            nameRule = new ArrayList<>();for (int i = 0; i< listRule.size(); i++){
                nameRule.add(listRule.get(i).getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.custom_spinner,nameRule);
            spinRule.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else{
            rlRule.setVisibility(View.GONE);
        }

        idEmploy = getIntent().getIntExtra("T_idEmploy",0);

        if(idEmploy != 0){
            tvTitle.setText(getResources().getString(R.string.update_info));
            Employee employee = employeeDAO.getEmployeeWithID(idEmploy);

            etUsername.setText(employee.getUsername());
            etUsername.setEnabled(false);
            etUsername.setTextColor(Color.GRAY);
            etPassword.setText(employee.getPassword());
            etBirthday.setText(employee.getBirthday());
            etPhoneNumber.setText(String.valueOf(employee.getPhone()));
            String genre = employee.getGenre();
            if(genre.equals("Nam")){
                rdioMale.setChecked(true);
            } else if(genre.equals("Nữ")){
                rdioFemale.setChecked(true);
            }
        }
    }

    private void addControls() {
        etUsername = (EditText) findViewById(R.id.et_userName);
        etPassword = (EditText) findViewById(R.id.et_password);
        etBirthday = (EditText) findViewById(R.id.et_birthday);
        etPhoneNumber = (EditText) findViewById(R.id.et_phoneNumber);
        rdioMale = (RadioButton) findViewById(R.id.rdio_Male);
        rdioFemale = (RadioButton) findViewById(R.id.rdio_Female);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rgGenre = (RadioGroup) findViewById(R.id.rg_Genre);
        btConfirm = (Button) findViewById(R.id.bt_confirm);
        btExit = (Button) findViewById(R.id.bt_exit);
        spinRule = (Spinner) findViewById(R.id.spin_rule);
        rlRule = (RelativeLayout) findViewById(R.id.rlayout_rule);

        btConfirm.setOnClickListener(this);
        btExit.setOnClickListener(this);
        etBirthday.setOnFocusChangeListener(this);
    }

    private void processInsert(){
        String sUsername = etUsername.getText().toString();
        String sPassword = etPassword.getText().toString();
        switch (rgGenre.getCheckedRadioButtonId()){
            case R.id.rdio_Male:
                genre = "Nam";
                break;
            case R.id.rdio_Female:
                genre = "Nữ";
                break;
        }
        String sBirthday = etBirthday.getText().toString();
        int iPhone = Integer.parseInt(etPhoneNumber.getText().toString());

        if (sUsername == null || sUsername.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.err_input_username), Toast.LENGTH_SHORT).show();
        } else if (sPassword == null || sPassword.equals("")){
            Toast.makeText(this, getResources().getString(R.string.err_input_password), Toast.LENGTH_SHORT).show();
        } else {
            Employee dto = new Employee();
            dto.setUsername(sUsername);
            dto.setPassword(sPassword);
            dto.setGenre(genre);
            dto.setBirthday(sBirthday);
            dto.setPhone(iPhone);
            if(firstSignin != 0){
                // default sign in is admin
                dto.setIdRule(1);
            } else{
                // get rule in spinner
                int position = spinRule.getSelectedItemPosition();
                int idRule = listRule.get(position).getId();
                dto.setIdRule(idRule);
            }

            long rs = employeeDAO.insert(dto);
            if(rs != 0){
                Toast.makeText(this,getResources().getString(R.string.insert_success), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,getResources().getString(R.string.insert_fail), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void processUpdate(int idEmploy){
        String sUsername = etUsername.getText().toString();
        String sPassword = etPassword.getText().toString();
        switch (rgGenre.getCheckedRadioButtonId()){
            case R.id.rdio_Male:
                genre = "Nam";
                break;
            case R.id.rdio_Female:
                genre = "Nữ";
                break;
        }
        String sBirthday = etBirthday.getText().toString();
        int iPhone = Integer.parseInt(etPhoneNumber.getText().toString());

        if (sPassword == null || sPassword.equals("")){
            Toast.makeText(this, getResources().getString(R.string.err_input_password), Toast.LENGTH_SHORT).show();
        } else {
            Employee dto = new Employee();
            dto.setId(idEmploy);
            dto.setUsername(sUsername);
            dto.setPassword(sPassword);
            dto.setGenre(genre);
            dto.setBirthday(sBirthday);
            dto.setPhone(iPhone);
            boolean rs = employeeDAO.update(dto);
            if(rs){
                Toast.makeText(this,getResources().getString(R.string.update_success), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,getResources().getString(R.string.update_fail), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.bt_confirm:
                if(idEmploy != 0){
                    // update
                    processUpdate(idEmploy);
                } else{
                    // insert
                    processInsert();
                }
                break;
            case R.id.bt_exit:
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.et_birthday:
                if (b){
                    // show popup calendar
                   // DatePickerFragment datePickerFragment = new DatePickerFragment();
                    //datePickerFragment.show(getFragmentManager(),"Tag_Birthday");
                }
                break;
        }
    }
}
