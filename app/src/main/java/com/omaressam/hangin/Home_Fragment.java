package com.omaressam.hangin;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.omaressam.hangin.network.api.ApiClient;
import com.omaressam.hangin.network.services.PlacesService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Fragment extends Fragment implements PlaceItemClick {
    private RecyclerView recyclerView;
    private NavController navController;

    public Home_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_home_, container, false);
            recyclerView = view.findViewById(R.id.home_recyclerView);
            getPlace(view);
            return view;
        }
        private void getPlace(View view){

            PlacesService placesService= ApiClient.getClient().create(PlacesService.class);
            placesService.getPlaces().enqueue(new Callback<List<Place>>() {
                @Override
                public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {

                    List<Place>places = response.body();

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    recyclerView.setLayoutManager(gridLayoutManager);

                    // TODO: StaggeredGridLayoutManager
                    // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    // recyclerView.setLayoutManager(staggeredGridLayoutManager);

                    PlaceAdapter placeAdapter = new PlaceAdapter(places,Home_Fragment.this);

                    recyclerView.setAdapter(placeAdapter);

                }

                @Override
                public void onFailure(Call<List<Place>> call, Throwable t) {

                }
            });
            // TODO: LinearLayoutManager

        }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onItemClicked(int position) {
        navController.navigate(R.id.action_home_Fragment_to_detals_Fragment);
    }
}


