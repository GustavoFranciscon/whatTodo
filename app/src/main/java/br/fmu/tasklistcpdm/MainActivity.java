package br.fmu.tasklistcpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> list;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private Button addButton;
    private int coratual = 0;

    private Calendar lastClick;

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
                Toast.makeText(context, "Item Deletado", Toast.LENGTH_LONG).show();
                list.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();

                view.setBackgroundColor(Color.WHITE);
                ArrayList<Integer> cores = new ArrayList<>();


                cores.add(Color.rgb(143, 229, 88));
                cores.add(Color.rgb(88, 229, 210));
                cores.add(Color.rgb(88, 180, 229));
                cores.add(Color.rgb(193, 88, 229));
                cores.add(Color.rgb(229, 88, 193));
                cores.add(Color.rgb(229, 88, 88));
                cores.add(Color.rgb(229, 129, 88));


                if(coratual < cores.size()-1){
                coratual++;
                }else
                    {
                    coratual = 0;
                }

                view.setBackgroundColor(cores.get(coratual));
                Calendar now = Calendar.getInstance();
                Format formato = new SimpleDateFormat("HHmmss");
                if(lastClick == null){
                    now.add(Calendar.SECOND, 30);
                    lastClick = now;
                }else{
                    if(lastClick.before(now)){
                        now.add(Calendar.SECOND, 30);
                        lastClick = now;
                    }else{
                        return;
                    }
                }

                Toast.makeText(context, "Segure para Deletar", Toast.LENGTH_LONG).show();

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
            Toast.makeText(getApplicationContext(), "Preencha o texto",Toast.LENGTH_LONG).show();
        }
    }


}