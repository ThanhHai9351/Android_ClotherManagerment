package com.example.clotherapp.UI.FavouriteFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clotherapp.ADAPTER.FavouriteAdapter;
import com.example.clotherapp.DAO.DAOFavourite;
import com.example.clotherapp.MODEL.DataHolder;
import com.example.clotherapp.MODEL.Favourite;
import com.example.clotherapp.R;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment {

    RecyclerView recycler_favourite;
    FavouriteAdapter adapter;
    ArrayList<Favourite> arrayList;

    TextView count;

    public String ip = DataHolder.getInstance().getIp();

    public String urlDeleteFavourite = ip + "/clotherapp/handle/deleteFavourite.php";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavouriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        controls(view);
        return view;
    }

    public void controls(View view) {
        recycler_favourite = view.findViewById(R.id.recycle_favorite);
        count = view.findViewById(R.id.tw_count_favourite);

        arrayList = new ArrayList<>();
        int idUser = DataHolder.getInstance().getId();

        DAOFavourite dao = new DAOFavourite(getContext());
        dao.getFavouriteFromData(new DAOFavourite.DataCallback() {
            @Override
            public void onSuccess(ArrayList<Favourite> favourites) {
                for (Favourite fv : favourites) {
                    if (fv.getIdUser() == idUser) {
                        arrayList.add(fv);
                    }
                }
                adapter = new FavouriteAdapter(getContext(), arrayList);
                recycler_favourite.setLayoutManager(new LinearLayoutManager(getContext()));
                recycler_favourite.setAdapter(adapter);

                int size = arrayList.size();
                count.setText("Số lượng sản phẩm: " + size);

                events();
            }

            @Override
            public void onError(String error) {
                // Handle error
            }
        });
    }

    public void events() {
        adapter.setOnItemClickListener(new FavouriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Favourite favourite) {
                // Show confirmation dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteItem(submit(favourite.getId()));
                                arrayList.remove(favourite);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    private JSONObject submit(int id) {
        JSONObject json = new JSONObject();
        try {
            json.put("id", id);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Loi tao json", Toast.LENGTH_SHORT).show();
        }
        return json;
    }


    private void deleteItem(JSONObject jsonObject) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlDeleteFavourite, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "That bai: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}
