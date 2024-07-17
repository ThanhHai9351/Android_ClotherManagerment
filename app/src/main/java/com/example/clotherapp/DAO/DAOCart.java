package com.example.clotherapp.DAO;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.clotherapp.MODEL.Cart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DAOCart {
    static String url = ConnectDB.getUrl();

    public void getAllCarts(Context context, int idUser, CartLoadedCallback callback) {
        ArrayList<Cart> list = new ArrayList<>();

        JsonObjectRequest request = null;
        String requestUrl = url + "cart/getAllCart.php";
        try {
            request = new JsonObjectRequest(
                    Request.Method.POST,
                    requestUrl,
                    new JSONObject().put("idUser", idUser),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray array = response.getJSONArray("carts");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.getJSONObject(i);
                                    int id = obj.getInt("ID");
                                    int userID = obj.getInt("IDUser");
                                    int idProduct = obj.getInt("IDProduct");
                                    String image = obj.getString("Image");
                                    String name = obj.getString("NameProduct");
                                    String nameCategory = obj.getString("NameCategory");
                                    double price = obj.getDouble("Money");
                                    int quantityReview = obj.getInt("QuantityReview");
                                    int quantity = obj.getInt("Quantity");
                                    String color = obj.getString("Color");
                                    int size = obj.getInt("Size");
                                    System.out.println("Image: " + image);
                                    list.add(new Cart(id, userID, idProduct, image, name, nameCategory, price, quantityReview, quantity, color, size));
                                }
                                callback.onCartLoaded(list);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Lỗi lấy json " + error.toString(), Toast.LENGTH_SHORT).show();
                            System.out.println("Loi:" + error.toString());
                            callback.onError(error);
                        }
                    }
            );
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public interface CartLoadedCallback {
        void onCartLoaded(ArrayList<Cart> carts);

        void onError(VolleyError error);
    }

    public void addCart(Context context, int idUser, int idProduct, int quantity, Double money, String color, int size) throws JSONException {
        // check product in cart
        System.out.println(""+idUser);
        checkProductInCart(context, idUser, idProduct, color, size, new CartCountCallback() {
            @Override
            public void onCartLoaded(int count) {
                if (count > 0) {
                    updateQuantity(context, idUser, idProduct, count + quantity, color, size);
                } else {
                    addToPHP(context, url + "cart/insertCart.php", addJson(idUser, idProduct, quantity, money, color, size));
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    public void updateQuantity(Context context, int idUser, int idProduct, int quantity, String color, int size) {
        addToPHP(context, url + "cart/updateCart.php", addJson(idUser, idProduct, quantity, 0, color, size));
    }

    public void deleteProductInCart(Context context, int idUser, int idProduct) {
        addToPHP(context, url + "cart/deleteCart.php", addJson(idUser, idProduct, 0, 0, null, 0));
    }

    public void clearCartByUserId(Context context, int userId) {
        JSONObject json = new JSONObject();
        try {
            json.put("IDUser", userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url + "cart/clearCartByUserId.php",
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response here
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error here
                    }
                }
        );

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    private void addToPHP(Context context, String urlWeb, JSONObject json) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlWeb, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(context, "Tao thanh cong json", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Tao khong thanh cong json", Toast.LENGTH_SHORT).show();
                System.out.println("Loi:" + error.toString());
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void checkProductInCart(Context context, int idUser, int idProduct, String color, int size, CartCountCallback callback) throws JSONException {
        JSONObject json = new JSONObject();
        try {
            json.put("IDUser", idUser);
            json.put("IDProduct", idProduct);
            json.put("Color", color);
            json.put("Size", size);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(
                Request.Method.POST,
                url + "cart/checkCart.php",
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            callback.onCartLoaded(response.getInt("Count"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(context, "Lỗi lấy json " + error.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("Loi check product:" + error.toString());
                    }
                }
        );
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    private JSONObject addJson(int idUser, int idProduct, int quantity, double money, String color, int size) {
        JSONObject json = new JSONObject();
        try {
            json.put("IDUser", idUser);
            json.put("IDProduct", idProduct);
            json.put("Quantity", quantity);
            json.put("Money", money);
            json.put("Color", color);
            json.put("Size", size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Json: "+json);
        return json;
    }

    public interface CartCountCallback {
        void onCartLoaded(int count);

        void onError(VolleyError error);
    }
}

