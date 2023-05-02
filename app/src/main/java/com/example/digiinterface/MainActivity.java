package com.example.digiinterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView,recyclerView1,recyclerView2;
    private ArrayList<Parentmodel> parentmodels;
    private ArrayList<ParentPCmodel> parentNewVideomodels;
    private ArrayList<ParentNVmodel> parentNVmodels;
    ArrayList<Childmodel> latst;
    ArrayList<Childmodel> all_pg;
    ArrayList<ChildPCmodel> pg_cat;
    ArrayList<ChildNVmodel> n_video;
    ParentAdapter parentAdapter;
    ParentPCAdapter parentNewVideoAdapter;
    ParentNVAdapter parentNVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rec_1);
        recyclerView1=findViewById(R.id.rec_2);
        recyclerView2=findViewById(R.id.rec_3);

        latst = new ArrayList<>();
        all_pg = new ArrayList<>();
        pg_cat = new ArrayList<>();
        n_video = new ArrayList<>();

        fetchData();

        parentmodels = new ArrayList<>();
        parentNewVideomodels = new ArrayList<>();
        parentNVmodels = new ArrayList<>();

        parentAdapter=new ParentAdapter(parentmodels,MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(parentAdapter);
        parentAdapter.notifyDataSetChanged();

        parentNewVideoAdapter=new ParentPCAdapter(parentNewVideomodels,MainActivity.this);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(parentNewVideoAdapter);
        parentNewVideoAdapter.notifyDataSetChanged();

        parentNVAdapter=new ParentNVAdapter(parentNVmodels,MainActivity.this);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(parentNVAdapter);
        parentNVAdapter.notifyDataSetChanged();

    }

    private void fetchData() {
        String url = "https://lollypopy.in/iplustv_app/api/v1/tv/dashboard/";
        JsonObjectRequest getRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,null,
                response -> {
                    try {
                        JSONArray newsJsonArray = response.getJSONObject("data").getJSONArray("video_list");
                        ArrayList<Childmodel> programArray = new ArrayList<>();
                        ArrayList<ChildPCmodel> programArray1 = new ArrayList<>();
                        ArrayList<ChildNVmodel> programArray2 = new ArrayList<>();
                        for (int i = 0; i < newsJsonArray.length(); i++) {
                            JSONObject videoItem = newsJsonArray.getJSONObject(i);

                            if (videoItem.getString("title").equals("Program Categories")){
                                parentNewVideomodels.add(new ParentPCmodel("Program Categories",pg_cat));
                                JSONArray listArray1 = videoItem.getJSONArray("list");
                                for (int j2=0;j2<listArray1.length();j2++){
                                    JSONObject listItem2 = listArray1.getJSONObject(j2);
                                    ChildPCmodel childmodel2 = new ChildPCmodel(
//                                            listItem.getString("title"),
                                            listItem2.getString("image")
                                    );
//                                    programArray.add(childmodel);
                                    pg_cat.add(childmodel2);
                                }
                            }
                            if (videoItem.getString("title").equals("All Programs")){
                                parentmodels.add(new Parentmodel("All Programs",latst));
                                JSONArray listArray = videoItem.getJSONArray("list");
                                for (int j=0;j<listArray.length();j++){
                                    JSONObject listItem = listArray.getJSONObject(j);
                                    Childmodel childmodel = new Childmodel(
                                            listItem.getString("image_url")
                                    );
                                    latst.add(childmodel);
                                }
                            }
                            if (videoItem.getString("title").equals("Speakers")){
                                parentmodels.add(new Parentmodel("Speakers",all_pg));
                                JSONArray listArray1 = videoItem.getJSONArray("list");
                                for (int j1=0;j1<listArray1.length();j1++){
                                    JSONObject listItem1 = listArray1.getJSONObject(j1);
                                    Childmodel childmodel1 = new Childmodel(
                                            listItem1.getString("image_url")
                                    );
                                    all_pg.add(childmodel1);
                                }
                            }
                            if (videoItem.getString("title").equals("New Videos")){
                                parentNVmodels.add(new ParentNVmodel("New Videos",n_video));
                                JSONArray listArray3 = videoItem.getJSONArray("list");
                                for (int j3=0; j3<listArray3.length(); j3++){
                                    JSONObject listItem3 = listArray3.getJSONObject(j3);
                                    JSONObject thumbnailsObject = new JSONObject(listItem3.getString("thumbnails"));
                                    JSONObject defaultThumbnailObject = thumbnailsObject.getJSONObject("default");
                                    String thumbnailUrl = defaultThumbnailObject.getString("url");
//                                    JSONObject listItem = listArray.getJSONObject(j);
//                                    Toast.makeText(this, ""+thumbnailUrl, Toast.LENGTH_SHORT).show();
                                    ChildNVmodel childNVmodel = new ChildNVmodel(thumbnailUrl.replace("\\",""));
                                    n_video.add(childNVmodel);
                                }
                            }
                        }
                        parentNewVideoAdapter.updateData(programArray1);
                        parentAdapter.updateData(programArray);
                        parentNVAdapter.updateData(programArray2);

                    } catch (JSONException e) {
                        System.out.println("check here: " + e.getMessage());
                        Toast.makeText(MainActivity.this, "json exp : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    // Handle error
                    Toast.makeText(MainActivity.this, "error!"+ error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                params.put("User-agent", "Mozilla/5.0");
                params.put("x-api-key","iplustv@123");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(getRequest);
    }
}
