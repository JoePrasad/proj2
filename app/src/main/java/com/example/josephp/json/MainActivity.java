package com.example.josephp.json;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;





public class MainActivity extends AppCompatActivity {

 private String sfsu = "http://sfsuswe.com/413/get_token/?username=jprasad1&password=911109178";

    private String sfsu1 = "http://sfsuswe.com/413/get_question/?token=";







    private static String TAG = MainActivity.class.getSimpleName();
    private String jsonResponse;



    private TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnHit = (Button) findViewById(R.id.btnHit);
       txtData = (TextView) findViewById(R.id.tvJsonItem);

        txtData.setMovementMethod(new ScrollingMovementMethod());

        btnHit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                new JSONTask().execute(sfsu);


                makeJsonObjectRequest();




            }

            });


        }

    public void buttonOnClick(View v) {
        Button button = (Button) v;

        ((Button) v).setText("Incorrect");
    }

    public void button2OnClick(View v) {
        Button button2 = (Button) v;

        ((Button) v).setText("Incorrect");
    }

    public void button4OnClick(View v) {
        Button button4 = (Button) v;

        ((Button) v).setText("Incorrect");
    }

    public void button6OnClick(View v) {
        Button button6 = (Button) v;

        ((Button) v).setText("Incorrect");
    }

    public void button5OnClick(View v) {
        Button button5 = (Button) v;

        ((Button) v).setText("Incorrect");
    }
    public void button3OnClick(View v) {
        Button button3 = (Button) v;

        ((Button) v).setText("Correct");
    }

    private void makeJsonObjectRequest() {





        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, sfsu, null,
                new Response.Listener<JSONObject>() {

                    @Override

                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        try {

                            String token = response.getString("token");

                            jsonResponse = "\n\n\n";
                            jsonResponse += "token: " +  token + "\n\n\n";



                            sfsu = sfsu1 + token;








                            txtData.setText(jsonResponse);
                        } catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }




                    }



                }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);





    }








    public class JSONTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;




            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);

                }






                return buffer.toString() ;




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return null;




        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            txtData.setText(result);

        }

    }


}
