package br.fmu.tasklistcpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> list;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        addButton = findViewById(R.id.buttonadd);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        list = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        setUpListViewListener();


    }

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();
                list.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void addItem(View view) {
        EditText input = findViewById(R.id.add_input);
        String itemText = input.getText().toString();

        if(!(itemText.equals(""))){
            arrayAdapter.add(itemText);
            input.setText("");

        }else{
            Toast.makeText(getApplicationContext(), "Please Enter Text",Toast.LENGTH_LONG).show();
        }
    }


}