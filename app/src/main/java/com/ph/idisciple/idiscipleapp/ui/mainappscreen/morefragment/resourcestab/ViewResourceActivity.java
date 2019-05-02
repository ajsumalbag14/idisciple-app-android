package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment.resourcestab;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import butterknife.OnClick;

public class ViewResourceActivity extends BaseActivity {

    @OnClick(R.id.clBackOption)
    public void onBackButtonClick(){
        onBackPressed();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_view_resource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView mWebView = (WebView) findViewById(R.id.webView);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String type = bundle.getString("type", "file");
            switch (type){
                case "file":
                    mWebView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + bundle.getString("url", ""));
                    break;
                case "video":
                    mWebView.loadUrl(bundle.getString("url", ""));
                    break;
            }

        }
    }

//    /**
//     * Background Async Task to download file
//     */
//    class DownloadFileFromURL extends AsyncTask<String, String, String>
//    {
//        /**
//         * Before starting background thread Show Progress Bar Dialog
//         * */
//        @Override
//        protected void onPreExecute()
//        {
//            super.onPreExecute();
//            //Show Progress Bar Dialog
//            //showLoadingDialog();
//        }
//
//        /**
//         * Downloading file in background thread
//         */
//        @Override
//        protected String doInBackground(String... f_url)
//        {
//            int count;
//
//            //Make directory for PDF File to be located
//            File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "Maxicare");
//            if(!directory.exists()) directory.mkdirs();
//
//            try
//            {
//                URL url = new URL(f_url[0]);
//                URLConnection conection = url.openConnection();
//                conection.connect();
//
//                // this will be useful so that you can show a tipical 0-100%
//                // progress bar
//                int lenghtOfFile = conection.getContentLength();
//
//                // download the file
//                InputStream input = new BufferedInputStream(url.openStream(),
//                        8192);
//
//                // Output stream
//                OutputStream output = new FileOutputStream(directory.toString() + "/AndroidUsersGuide-40-en.pdf");
//
//                byte data[] = new byte[1024];
//
//                long total = 0;
//
//                while ((count = input.read(data)) != -1)
//                {
//                    total += count;
//                    // publishing the progress....
//                    // After this onProgressUpdate will be called
//                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
//
//                    // writing data to file
//                    output.write(data, 0, count);
//                }
//
//                // flushing output
//                output.flush();
//
//                // closing streams
//                output.close();
//                input.close();
//
//            }
//            catch (Exception e)
//            {
//                Log.e("Error: ", e.getMessage());
//            }
//
//            return null;
//        }
//
//        /**
//         * Updating progress bar
//         */
//        protected void onProgressUpdate(String... progress)
//        {
//            // setting progress percentage
////            fragmentProgressBarDialog.updateProgress(Integer.parseInt(progress[0]));
//        }
//
//        /**
//         * After completing background task Dismiss the progress dialog
//         */
//        @Override
//        protected void onPostExecute(String file_url)
//        {
//            // dismiss the dialog after the file was downloaded
////            dismissLoadingDialog();
//        }
//    }
}
