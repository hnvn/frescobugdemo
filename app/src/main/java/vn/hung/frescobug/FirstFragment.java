package vn.hung.frescobug;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hunghd on 11/20/2017.
 */

public class FirstFragment extends Fragment {

    String[] IMAGES_URL = {"https://upload.wikimedia.org/wikipedia/commons/b/b1/Bass_harbor_head_light_20041002_123635_1.1504x1000.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/1/13/Ofu_Beach_NPS.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/0/06/Delicatearch1.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/6/6d/BadlandsView3.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/d/df/Biscayne_NP_snorkeling.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/9/9e/Black_canyon_gunnison_Colorado.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/b/b5/Crater_lake_oregon.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/8/87/Mesquite_Sand_Dunes_in_Death_Valley.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/e/ed/Mount_McKinley_and_Denali_National_Park_Road_2048px.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/e/e4/GatesofArctic.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/6/6d/Puu_Oo_cropped.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/c/c4/Mesa_Verde_National_Park_Cliff_Palace_Right_Part_2006_09_12.jpg",
                            "https://upload.wikimedia.org/wikipedia/commons/1/10/Zion_angels_landing_view.jpg"};

    NavigationListener navigationListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        navigationListener = (NavigationListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigationListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<String> images = new ArrayList<>();
        Collections.addAll(images, IMAGES_URL);
        recyclerView.setAdapter(new ParkAdapter(images));

        Button nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationListener.next();
            }
        });
    }

    class ParkViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imageView;

        public ParkViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_view);
        }

    }

    class ParkAdapter extends RecyclerView.Adapter<ParkViewHolder> {
        List<String> images;

        public ParkAdapter(List<String> images) {
            this.images = images;
        }

        @Override
        public ParkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_park, parent, false);
            return new ParkViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ParkViewHolder holder, int position) {
            String image = images.get(position);
            holder.imageView.setImageURI(image);
        }

        @Override
        public int getItemCount() {
            return images == null ? 0 : images.size();
        }
    }
}
