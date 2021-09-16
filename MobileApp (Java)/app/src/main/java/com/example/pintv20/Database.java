package com.example.pintv20;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Database extends Object implements Serializable {

    /**
     * IP do computador em que esta hospedada a API
     */
    private static String API_IP = "https://crowdzeroapieuropeappandsite.herokuapp.com/android";
    private static String APIGoogleGeoCoding = "AIzaSyBRkqEW-Cqkx0aRzjLyeoTO4gLVh2x8J3c";

    //private static String API_IP_NOANDROID = "https://crowdzeroapieuropeappandsite.herokuapp.com";
    //https://crowzeroapieurope.herokuapp.com
    //https://morning-spire-98820.herokuapp.com
    //http://192.168.1.70:3000
    //https://maps.googleapis.com/maps/api/geocode/json?&latlng=40.650673,-7.95015&key=AIzaSyBRkqEW-Cqkx0aRzjLyeoTO4gLVh2x8J3c

    /**
     * Utilizador que fez login na aplicacao
     */
    private static Integer currentUserId = null;
    private static String currentUserName = null;
    private static String currentUserEmail = null;
    private static Integer currentUserPhone = null;
    private static String currentUserPassword = null;

    public Database() {

    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(Object response);
    }

    /**
     * Devolve dados do utilizador logado
     */
    public static Integer getCurrentUserId() {
        return currentUserId;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public static Integer getCurrentUserPhone() {
        return currentUserPhone;
    }

    public static String getCurrentUserPassword() {
        return currentUserPassword;
    }

    /**
     * Chamadas à base de dados
     */
    public void Login(Context c, String email, final String pw, final VolleyResponseListener volleyResponseListener){
        String url = API_IP + "/utilizador/password/"+ email;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Boolean aux = false;
                        String password;
                        try {
                            password = response.getJSONArray(0).getJSONObject(0).getString("getpassword");

                            if(password != "null"){
                                if(BCrypt.checkpw(pw, password)){
                                    aux = true;
                                }
                            }

                            volleyResponseListener.onResponse(aux);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                        volleyResponseListener.onError("Volley Error Response!");
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void getUserId(Context c, String email){
        String url = API_IP + "/utilizador/getid/" + email;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Integer userId = null;
                        try {
                            System.out.println("USER ID\n" + response);
                            JSONObject user = response.getJSONObject(0);
                            userId = user.getInt("u_idutilizador");
                            String uName = user.getString("u_nome");
                            String email = user.getString("u_email");
                            Integer phone = user.getInt("u_telefone");
                            String pw = user.getString("u_password");
                            System.out.println(userId);
                            currentUserId = userId;
                            currentUserName = uName;
                            currentUserEmail = email;
                            currentUserPhone = phone;
                            currentUserPassword = pw;
                            System.out.println("CURRENT USER NAME = " + currentUserName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void getUserData(Context c, final VolleyResponseListener volleyResponseListener){
        String url = API_IP + "/utilizador/get/" + currentUserId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            System.out.println("USER DATA\n" + response);
                            JSONObject user = response.getJSONObject(0);
                            ArrayList<String> data = new ArrayList<>();
                            String username = user.getString("u_nome");
                            String email = /*"email@email.pt";*/user.getString("u_email");
                            String phone = String.valueOf(/*912345678*/user.getInt("u_telefone"));
                            String avatar = String.valueOf(user.getInt("a_idavatar"));
                            String points = String.valueOf(user.getInt("u_likes")/2);
                            data.add(username);
                            data.add(email);
                            data.add(phone);
                            data.add(avatar);
                            data.add(points);
                            System.out.println(user);
                            volleyResponseListener.onResponse(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                        volleyResponseListener.onError("Volley Error Response!");
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void getMapPins(Context c, final VolleyResponseListener volleyResponseListener){
        String url = API_IP + "/report/list";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        LinkedHashMap<Integer, HashMap<LatLng, Integer>> pins = new LinkedHashMap<>();
                        try {
                            JSONArray reports = response;
                            System.out.println(reports);
                            for(int i = 0; i < reports.length(); ++i)
                            {
                                JSONObject object = reports.getJSONObject(i);
                                Boolean limite = object.getBoolean("r_limite");
                                if(limite == false){
                                    Integer reportId = object.getInt("r_idreport");
                                    Integer level = object.getInt("n_idnivel");
                                    JSONObject local = object.getJSONObject("local");
                                    Double latitude = local.getDouble("l_latitude");
                                    Double longitude = local.getDouble("l_longitude");

                                    String date = object.getString("r_data");
                                    String time = object.getString("r_hora");
                                    System.out.println("Report " + reportId);
                                    System.out.println("Data " + date);
                                    System.out.println("Hora " + time);
                                    System.out.println("Nivel " + level);
                                    System.out.println("Coordenadas " + latitude + " , " + longitude);

                                    LatLng latLng = new LatLng(latitude,longitude);
                                    HashMap<LatLng, Integer> data = new HashMap<>();
                                    data.put(new LatLng(latitude,longitude), level);

                                    pins.put(pins.size() + 1, data);
                                }
                                //System.out.println(object);*/

                            }
                            //System.out.println(object);
                            volleyResponseListener.onResponse(pins);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                        volleyResponseListener.onError("Volley Error Response!");
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void reportLocation(final Context c, final Integer level, final Double latitude, final Double longitude){

        getAddress(c, new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(Object response) {
                System.out.println("Local do report " + response);
                String local = (String) response;
                System.out.println(local);

                String url = API_IP + "/utilizador/report/" + currentUserId + "/" + level + "/" + latitude + "/" + longitude + "/" + local;

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                System.out.println("Report Done!");
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("Response Error:\n" + error.toString());
                            }
                        });
                SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
            }
        }, latitude, longitude);
    }

    public void getReportInfo(Context c, final VolleyResponseListener volleyResponseListener, Integer reportID){
        String url = API_IP + "/report/get/" + reportID;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject report = response.getJSONObject(0);
                            ArrayList<String> data = new ArrayList<>();
                            String nivel = String.valueOf(report.getInt("n_idnivel"));
                            String date = report.getString("r_data");
                            String time = report.getString("r_hora");
                            data.add(nivel);
                            data.add(date);
                            data.add(time);
                            System.out.println(report);
                            volleyResponseListener.onResponse(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                        volleyResponseListener.onError("Volley Error Response!");
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void evaluateReport(Context c, Integer reportID, String comment, Boolean like){
        String url = API_IP + "/utilizador/avaliar/" + currentUserId + "/" + comment + "/" + like + "/" + reportID;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("Evaluation Done!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void getReportLikes(Context c, final VolleyResponseListener volleyResponseListener, final Integer reportID){
        String url = API_IP + "/report/getlikes/" + reportID;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONArray likes = response.getJSONArray(0);
                            String likeString = likes.getJSONObject(0).getString("devolvelikes");

                            ArrayList<Integer> result = new ArrayList<>();

                            likeString = likeString.replace("[", "");
                            likeString = likeString.replace("]", "");
                            String[] data = likeString.split(",");

                            result.add(Integer.parseInt(data[0]));
                            result.add(Integer.parseInt(data[1]));

                            /*
                            for(int i = 0; i < result.size(); ++i){
                                System.out.println("LIKE POSICAO "+ i + " ->" + result.get(i));
                            }*/

                            volleyResponseListener.onResponse(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                        volleyResponseListener.onError("Volley Error Response!");
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void getReportEvaluations(Context c, final VolleyResponseListener volleyResponseListener, Integer reportID){
        String url = API_IP + "/avaliacao/getR/" + reportID;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                        JSONArray evaluationArray = response;
                        ArrayList<String> data = new ArrayList<>();

                        for(int i = 0; i < evaluationArray.length(); ++i)
                        {
                            JSONObject object = (JSONObject) response.get(i);
                            String comentario = object.getString("av_comentario");
                            Boolean like = object.getBoolean("av_like");
                            String date = object.getString("av_data");
                            String time = object.getString("av_hora");
                            data.add(comentario);
                            data.add(like.toString());
                            data.add(date);
                            data.add(time);
                            //System.out.println("AVALIACAO " + i + comentario + " " + like + " " + date + " " + time);//evaluationArray.get(i));
                        }
                        //System.out.println(response);
                        volleyResponseListener.onResponse(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                        volleyResponseListener.onError("Volley Error Response!");
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void getUserEvaluations(Context c, final VolleyResponseListener volleyResponseListener){
        String url = API_IP + "/avaliacao/getU/" + currentUserId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            System.out.println("USER EVALUATIONS\n" + response);
                            JSONArray evaluationArray = response;
                            ArrayList<String> data = new ArrayList<>();

                            for(int i = 0; i < evaluationArray.length(); ++i)
                            {
                                JSONObject object = (JSONObject) response.get(i);
                                String comentario = object.getString("av_comentario");
                                Boolean like = object.getBoolean("av_like");
                                String date = object.getString("av_data");
                                String time = object.getString("av_hora");
                                data.add(comentario);
                                data.add(like.toString());
                                data.add(date);
                                data.add(time);
                                //System.out.println("AVALIACAO " + i + comentario + " " + like + " " + date + " " + time);//evaluationArray.get(i));
                            }
                            //System.out.println(response);
                            volleyResponseListener.onResponse(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                        volleyResponseListener.onError("Volley Error Response!");
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void getUserReports(Context c, final VolleyResponseListener volleyResponseListener){
        String url = API_IP + "/report/getReportsByUser/" + currentUserId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            System.out.println("USER REPORTS\n" + response);
                            JSONArray reportsArray = response;
                            ArrayList<String> data = new ArrayList<>();

                            for(int i = 0; i < reportsArray.length(); ++i)
                            {
                                JSONObject object = (JSONObject) response.get(i);
                                String date = object.getString("r_data");
                                String time = object.getString("r_hora");
                                Integer level = object.getInt("n_idnivel");
                                String localName = (String) object.getJSONObject("local").get("l_nome");
                                //System.out.println("Report" + i + "\n" + localName + " " + date + " " + time + " " + level);
                                data.add(date);
                                data.add(time);
                                data.add(String.valueOf(level));
                                data.add(localName);
                            }
                            volleyResponseListener.onResponse(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                        volleyResponseListener.onError("Volley Error Response!");
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void getRanking(Context c, final VolleyResponseListener volleyResponseListener){
        String url = API_IP + "/utilizador/ranking";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray array = response.getJSONArray(0);

                            ArrayList<String> data = new ArrayList<>();

                            for(int i = 0; i < array.length(); ++i){
                                JSONObject user = array.getJSONObject(i);
                                String name = user.getString("u_nome");
                                Integer points = user.getInt("pontos");
                                Integer avatar = user.getInt("a_idavatar");
                                System.out.println("Utilizador " + i + " - " + name + " | " + points + " | " + avatar);
                                data.add(name);
                                data.add(String.valueOf(points));
                                data.add(String.valueOf(avatar));
                            }

                            volleyResponseListener.onResponse(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                        volleyResponseListener.onError("Volley Error Response!");
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void updateProfile(Context c, String nome, String pw, String email, Integer telefone){
        String hashed = null;
        hashed = BCrypt.hashpw(pw, BCrypt.gensalt(4));
        System.out.println("HASHED " + hashed);
        while(hashed.contains("\\") || hashed.contains("/") || hashed.contains(".")){
            hashed = BCrypt.hashpw(pw, BCrypt.gensalt(4));
            System.out.println("HASHED " + hashed);
        }

        String url = API_IP + "/utilizador/profile/" + currentUserId + "/" + nome + "/" + hashed + "/" + email + '/' + telefone;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("Profile Changed!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public void getAddress(Context c, final VolleyResponseListener volleyResponseListener, double latitude, double longitude){
        String url = "https://maps.googleapis.com/maps/api/geocode/json?&latlng="+ latitude + "," + longitude + "&key=" + APIGoogleGeoCoding;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String location = "";
                        try {
                            System.out.println("Database: Success");
                            //Obter o nome do local
                            location = response.getJSONArray("results").getJSONObject(0).getString("formatted_address");
                            System.out.println(location);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        volleyResponseListener.onResponse(location);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        volleyResponseListener.onError("Volley Error Response!");
                    }
                });

        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonObjectRequest);
    }

    public void registerUser(final Context c, final VolleyResponseListener volleyResponseListener, String nome, String email, Integer telefone, String password){

        String hashed = null;
        hashed = BCrypt.hashpw(password, BCrypt.gensalt(4));
        System.out.println("HASHED " + hashed);
        while(hashed.contains("\\") || hashed.contains("/") || hashed.contains(".")){
            hashed = BCrypt.hashpw(password, BCrypt.gensalt(4));
            System.out.println("HASHED " + hashed);
        }

        String url = API_IP + "/utilizador/register/" + nome + "/" + email + "/" + telefone + "/" + hashed;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Boolean aux;
                        try {
                            JSONArray array = response.getJSONArray(0);
                            System.out.println(array);
                            JSONObject register = array.getJSONObject(0);
                            aux = register.getBoolean("inserir_utilizador");
                            volleyResponseListener.onResponse(aux);
                            System.out.println(aux);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error:\n" + error.toString());
                        volleyResponseListener.onError("Volley Error Response!");
                    }
                });
        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

}

/**
 * Fake Database, before APi implementation
 * Tentative of using java driver to connect to database
 */

/**
 * Database connectin info
 *//*
    private  String url = "jdbc:postgresql://ec2-34-255-134-200.eu-west-1.compute.amazonaws.com/da517i077iuo9e";
    private  String user = "hallmcvziedddi";
    private  String pw = "a181793df10c14c6629a575be243943fcc83d786c94872440aec3567702d092e";
    private  static Connection c = null;*/

/**
 * Dados dos reports
 * Esta a funcionar com os pins2, tem o id do report, local e nivel
 *//*
    private static LinkedHashMap<LatLng, Integer> pins = new LinkedHashMap<>();
    private static LinkedHashMap<Integer, HashMap<LatLng, Integer>> pins2 = new LinkedHashMap<>();

    private static HashMap<Integer, String> StoreDate = new HashMap<>();
    private static HashMap<Integer, String> StoreTime = new HashMap<>();
    private static HashMap<Integer, Integer> StoreLevel = new HashMap<>();*/

/**
 * Dados das avaliacoes
 * N avaliacao, Commentario, Like
 * Esta a funcionar com o segundo
 *//*
    private HashMap<Integer, HashMap<String, Boolean>> avaliacoes = new LinkedHashMap<>();
    private static LinkedHashMap<Integer, HashMap<Integer, HashMap<String, Boolean>>> evaluations = new LinkedHashMap<>();*/


/**
 * Ir buscar o local com estas coordenadas à base de dados, se existir receber o id
 * Se não, inserir na bd e devolver o local
 * O local vai ser inserido na tabela report
 * Esta a devolver true, se o report nao tiver informacao nula e false se houve erro
 * Tenho que devolver o Id do Report
 *//*
    public boolean Report(String time, String date, Integer level, Double latitude, Double longitude){
        if(time != null && date != null && level != null && latitude != null && longitude != null){
            //pins.put(new LatLng(latitude,longitude), level);

            StoreDate.put(pins2.size() + 1, date);
            StoreTime.put(pins2.size() + 1, time);
            StoreLevel.put(pins2.size() + 1, level);

            HashMap<LatLng, Integer> data = new HashMap<>();
            data.put(new LatLng(latitude,longitude), level);

            pins2.put(pins2.size() + 1, data);

            System.out.println("Database: Report Efetuado\nNivel" + level + "\n" + latitude + ", " + longitude);
            return true;
        }
        else
            return false;
    }*/

/**
 * Devolver hashmap com o nome da localizaçao e com as coordenadas
 * Depois na classe do mapa fazer um ciclo for e mostrar cada uma delas no mapa
 * Neste momento esta a devolver um hashmap com localizacoes e niveis
 *
 * Tenho que carregar o metodo de baixo com o id, coordenadas e nivel do report
 *//*
    public LinkedHashMap<LatLng, Integer> getPins(){
        LatLng location = new LatLng(40.650940156798974,-7.949127284380675);
        pins.put(location, 1);

        LatLng location2 = new LatLng(40.651076842002844, -7.952059376707155);
        pins.put(location2, 3);

        LatLng location3 = new LatLng(40.64997388052115, -7.950997221916282);
        pins.put(location3, 2);

        return pins;
    }

    public LinkedHashMap<Integer, HashMap<LatLng, Integer>> getPins2(){
        LatLng location = new LatLng(40.650940156798974,-7.949127284380675);
        LatLng location2 = new LatLng(40.651076842002844, -7.952059376707155);
        LatLng location3 = new LatLng(40.64997388052115, -7.950997221916282);
        HashMap<LatLng, Integer> data = new HashMap<>();
        HashMap<LatLng, Integer> data2 = new HashMap<>();
        HashMap<LatLng, Integer> data3 = new HashMap<>();

        data.put(location, 1);
        pins2.put(1, data);
        StoreLevel.put(1,1);

        data2.put(location2, 3);
        pins2.put(2, data2);
        StoreLevel.put(2,3);

        data3.put(location3, 2);
        pins2.put(3, data3);
        StoreLevel.put(3,2);

        return pins2;
    }*/

/**
 * Devolver informacao sobre um report
 *//*
    public String getReportDate(Integer reportID){
        if(StoreDate.get(reportID) != null)
            return StoreDate.get(reportID);
        else
            return "---";
    }

    public String getReportTime(Integer reportID){
        if(StoreTime.get(reportID) != null)
            return StoreTime.get(reportID);
        else
            return "---";
    }

    public Integer getReportLevel(Integer reportID){
        if(StoreLevel.get(reportID) != null)
            return StoreLevel.get(reportID);
        else
            return 0;
    }*/

/**
 * Avaliar um report,
 * criar uma avaliacao sobre um determinado report
 * falta criar aqui a data e hora,
 * devia tambem por a gerar data e hora aqui na bd, quando um report é feito
 */
    /*
    public boolean Evaluate(Integer reportID, String comment, boolean like){

        HashMap<String, Boolean> data = new HashMap<>();
        data.put(comment, like);
        avaliacoes.put(reportID, data);
        System.out.println("Database: Evalution " + evaluations.size() + 1 + " Received -> " + comment + ", " + like);
        HashMap<String, Boolean> commentLike = new HashMap<>();
        commentLike.put(comment,like);

        HashMap<Integer, HashMap<String, Boolean>> report = new HashMap<>();
        report.put(reportID, commentLike);

        evaluations.put(evaluations.size() + 1, report);
        return  true;
    }

    public Integer getEvaluations(){
        if(evaluations.size() == 0){
            return 0;
        }
        else
            return 1;
    }

    public ArrayList<String> getComments(Integer reportID){
        ArrayList<String> result = new ArrayList<>();

        for(Integer i: evaluations.keySet()) {
            HashMap<Integer, HashMap<String, Boolean>> reportMap;
            //System.out.println("Avaliacao: " + i);

            reportMap = evaluations.get(i);
            for (Integer j: reportMap.keySet()){
                if(j == reportID){
                    HashMap<String, Boolean> commentLike;
                    //System.out.println("Report: " + j);

                    commentLike = reportMap.get(j);
                    for(String k:commentLike.keySet()){
                        //System.out.println("Comment: " + k);
                        //System.out.println("Like: " + commentLike.get(k));
                        result.add(k);
                    }
                }

            }
        }
        System.out.println("Database: Comments of Report " + reportID);
        for (int i = 0; i < result.size(); ++i){
            System.out.println(result.get(i));
        }
        result.add("Fake Comment 1");
        result.add("Fake Comment 2");
        result.add("Fake Comment 3");
        result.add("Fake Comment 4");

        return result;
    }

    public ArrayList<Boolean> getLikes(Integer reportID){
        ArrayList<Boolean> result = new ArrayList<>();

        for(Integer i: evaluations.keySet()) {
            HashMap<Integer, HashMap<String, Boolean>> reportMap;
            //System.out.println("Avaliacao: " + i);

            reportMap = evaluations.get(i);
            for (Integer j: reportMap.keySet()){
                if(j == reportID){
                    HashMap<String, Boolean> commentLike;
                    //System.out.println("Report: " + j);

                    commentLike = reportMap.get(j);
                    for(String k:commentLike.keySet()){
                        //System.out.println("Comment: " + k);
                        //System.out.println("Like: " + commentLike.get(k));
                        result.add(commentLike.get(k));
                    }
                }

            }
        }
        System.out.println("Database: Comments of Report " + reportID);
        for (int i = 0; i < result.size(); ++i){
            System.out.println(result.get(i));
        }
        result.add(true);
        result.add(true);
        result.add(false);
        result.add(true);

        return result;
    }

    public ArrayList<Integer> getNumberOfLikes(Integer reportID){
        int totalLikes = 0;
        int totalDisLikes = 0;
        //System.out.println("Database!!!\n\n");
        for(Integer i: evaluations.keySet()) {
            HashMap<Integer, HashMap<String, Boolean>> reportMap;
            //System.out.println("Avaliacao: " + i);

            reportMap = evaluations.get(i);
            for (Integer j: reportMap.keySet()){
                HashMap<String, Boolean> commentLike;
                //System.out.println("Report: " + j);

                commentLike = reportMap.get(j);
                for(String k:commentLike.keySet()){
                    //System.out.println("Comment: " + k);
                    //System.out.println("Like: " + commentLike.get(k));
                    if(commentLike.get(k))
                        totalLikes++;
                    else
                        totalDisLikes++;
                }
            }
        }
        //System.out.println("Number of Likes: " + totalLikes + "\nNumber of Dislikes: " + totalDisLikes);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(totalLikes);
        result.add(totalDisLikes);
        return result;
    }*/

/**
 * Devolve o id do avatar do utilizador
 *//*
    public Integer getAvatar(int userId){
        return 3;
    }*/

/**
 * Devolve o nome do utilizador
 *//*
    public String getUserName(int userId){
        return "Marcelo Figueiredo";
    }*/

/**
 * Devolve o telefone do utilizador
 *//*
    public String getUserPhone(int userId)
    {
        return "912345678";
    }*/

/**
 * Devolve o email do utilizador
 *//*
    public String getUserEmail(int userId){
        return "email@email.com";
    }
    private Geocoder geocoder;

        public void setGeocoder(Context c, double latitude, double longitude){
        geocoder = new Geocoder(c);
        try {
            System.out.println("ADDRESS " + geocoder.getFromLocation(latitude, longitude, 1));
            System.out.println(geocoder.getFromLocation(latitude, longitude, 1).getClass());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    */

/***
 * Old Login Without Encryption
 */
//    public void Login(Context c, String email, String pw, final VolleyResponseListener volleyResponseListener){
//
//        String url = API_IP + "/utilizador/login/"+ email + "/"+ pw;
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        System.out.println("LOGIN RESPONSE\n" + response);
//                        Boolean aux;
//                        try {
//                            JSONArray array = response.getJSONArray(0);
//                            JSONObject login = array.getJSONObject(0);
//                            aux = login.getBoolean("login");
//                            volleyResponseListener.onResponse(aux);
//                            System.out.println(aux);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        System.out.println("Response Error:\n" + error.toString());
//                        volleyResponseListener.onError("Volley Error Response!");
//                    }
//                });
//        SingletonRequestQueue.getInstance(c).addToRequestQueue(jsonArrayRequest);
//    }
