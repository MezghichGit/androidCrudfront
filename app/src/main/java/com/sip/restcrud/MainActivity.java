package com.sip.restcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sip.restcrud.model.User;
import com.sip.restcrud.remote.APIUtils;
import com.sip.restcrud.remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {
    Button btnAddUser;
    Button btnGetUsersList;
    ListView listView;

    UserService userService;
    List<User> list = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("CRUD REST API");

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnGetUsersList = (Button) findViewById(R.id.btnGetUsersList);
        listView = (ListView) findViewById(R.id.listView);
        userService = APIUtils.getUserService();

        /*btnGetUsersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getUsersList();
            }
        });*/


        /*btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(MainActivity.this, UserActivity.class);
               // intent.putExtra("user_name", "");
               // startActivity(intent);
                addUser();
            }
        });*/
    }

    public void getUsersList(View v){
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    Log.i("Data: ", list.toString());

                    StringBuffer buffer=new StringBuffer();
                    for (User user : list)
                    {
                        buffer.append("ID: "+user.getId()+"\n");
                        buffer.append("Name: "+user.getName()+"\n\n");

                    }
                    showMessage("Users List", buffer.toString());

                   // listView.setAdapter(new UserAdapter(MainActivity.this, R.layout.list_user, list));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }


    public void addUser(View v){
        User u = new User("Mohamed Amine");
        Call<User> call = userService.addUser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }


    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}