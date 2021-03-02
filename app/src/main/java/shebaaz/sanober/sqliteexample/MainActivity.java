package shebaaz.sanober.sqliteexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    SQLiteEX myDb;
    Button btnAdd,btnView,btnUpdate,btnDelete;
    EditText editName,editSurname,editMarks,editId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new SQLiteEX(this);

        btnAdd = (Button) findViewById(R.id.add);
        btnView = (Button) findViewById(R.id.show);
        btnUpdate = (Button) findViewById(R.id.update);
        btnDelete = (Button) findViewById(R.id.delete);

        editId = (EditText) findViewById(R.id.id);
        editName = (EditText) findViewById(R.id.name);
        editSurname = (EditText) findViewById(R.id.surname);
        editMarks = (EditText) findViewById(R.id.marks);
        AddData();
        viewAll();
        updatingData();
        deleteData();


    }

    public  void AddData()
    {
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
              boolean isInserted =  myDb.insertData(editName.getText().toString(),
                       editSurname.getText().toString(),
                       editMarks.getText().toString());

              if(isInserted = true)
                  Toast.makeText(MainActivity.this, "Data Inserted SuccessFully", Toast.LENGTH_SHORT).show();
              else
                  Toast.makeText(MainActivity.this, "Data Not Inserted SuccessFully", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(editName.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(editSurname.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Sur Name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(editMarks.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please Enter marks", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // above top code is used foe insert data in database

    public  void  viewAll()
    {
        btnView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0)         //this is ued for the , if row is not present then it return.
                {
                    //show message
                    showMessage("Problem" , "Nothing to found..");          //this us used for if no data found tnen method willl be call and show this message
                    return;
                }

                StringBuffer buffer  = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Id : " + res.getString(0) + "\n");
                    buffer.append("Name : " + res.getString(1) + "\n");
                    buffer.append("Sur Name : " + res.getString(2) + "\n");
                    buffer.append("Marks : " + res.getString(3) + "\n\n");

                }

                //show All Data
                showMessage("Data", buffer.toString());
            }
        });
    }


    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    // above top code is used for show data in database



    public void updatingData()
    {
        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                boolean isUpdated =  myDb.updateData(editId.getText().toString(),editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString());


                if(isUpdated = true)
                    Toast.makeText(MainActivity.this, "Data Updated SuccessFully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Updated SuccessFully", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(editName.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(editSurname.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Sur Name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(editMarks.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please Enter marks", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // above top code is used for updating data in database

    public void deleteData()
    {
        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Integer deletedRows  = myDb.deletingdata(editId.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(MainActivity.this, "Data Deleted SuccessFully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Deleted SuccessFully", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // above top code is used for delete data in database

}