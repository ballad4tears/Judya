package com.judya.judya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.com.grafixartist.gallery.ImageModel;
import com.com.grafixartist.gallery.Image_DetailActivity;
import com.com.grafixartist.gallery.Image_GalleryAdapter;
import com.com.grafixartist.gallery.Image_RecyclerItemClickListener;

import java.util.ArrayList;


public class Image_Gallary extends Fragment {

    Image_GalleryAdapter mAdapter;
    RecyclerView mRecyclerView;

    ArrayList<ImageModel> data = new ArrayList<>();


    View v =null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

          v = inflater.inflate(R.layout.image_activity_main, container, false);
        Setviwe();
        return v;
    }

    private void Setviwe() {

        data = new ArrayList<>();
        for (int i = 0; i < File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getImage_product().size(); i++) {

            ImageModel imageModel = new ImageModel();
            imageModel.setName(File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getHealthy_name()+"  "    );
            imageModel.setUrl(File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getImage_product().get(i).toString());
            Log.d("warawat image",File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getImage_product().get(i).toString());
            data.add(imageModel);


        }


        mRecyclerView = (RecyclerView) v.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.setHasFixedSize(true);


        mAdapter = new Image_GalleryAdapter(getActivity(), data);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new Image_RecyclerItemClickListener(getActivity(),
                new Image_RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(getActivity(), Image_DetailActivity.class);
                        intent.putParcelableArrayListExtra("data", data);
                        intent.putExtra("pos", position);
                        startActivity(intent);

                    }
                }));

    }



}
