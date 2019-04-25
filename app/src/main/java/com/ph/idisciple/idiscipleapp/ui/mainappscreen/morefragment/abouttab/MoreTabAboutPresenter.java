package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment.abouttab;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.ListWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MoreTabAboutPresenter implements MoreTabAboutContract.Presenter{

    private MoreTabAboutContract.View mView;

    public MoreTabAboutPresenter(MoreTabAboutContract.View view){
        mView = view;
    }

    @Override
    public void fetchAboutUs() {
        new JsonTask().execute("https://idisciple.ph/2019/apbycon/assets/about.json");
    }

    /**
     * Get Json from link
     */
    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

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
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    //Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                mView.onFetchAboutUsFailed(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                mView.onFetchAboutUsFailed(e.getMessage());
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
                    mView.onFetchAboutUsFailed(e.getMessage());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Remove \n and make it as object not Array
            if (result.startsWith("\uFEFF")) {
                result = result.substring(1);
            }

            result = result.substring(1, result.length() - 2).replace("\n","");
            // save json items
            Gson jsonReturned =  new Gson();
            Type typeAboutContentWrapper = new TypeToken<ListWrapper<AboutContent>>() {}.getType();
            ListWrapper<AboutContent> wrapperAboutContent = jsonReturned.fromJson(result, typeAboutContentWrapper);
            List<AboutContent> jsonAboutContentDetails = wrapperAboutContent.getData();
            mView.onFetchAboutUsSuccess(jsonAboutContentDetails);
        }
    }

}
