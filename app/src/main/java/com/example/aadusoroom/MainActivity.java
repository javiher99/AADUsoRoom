package com.example.aadusoroom;

import android.os.Bundle;

import com.example.aadusoroom.entity.User;
import com.example.aadusoroom.model.Repository;
import com.example.aadusoroom.view.MainViewModel;
import com.example.aadusoroom.view.UserAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Variable
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setApellidos("Franco");
                user.setNombre("Francisco");
                viewModel.insert(user);
            }
        });

        RecyclerView rvList = findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        final UserAdapter adapter = new UserAdapter(this); // Se hace final porque es una clase anonima
        rvList.setAdapter(adapter);

        // viewModel = new ViewModelProvider(this).get(MainViewModel.class); Asin NO
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class); // Asin SI

        viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setUser(users);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
