package com.fnincnellinc.fninccalculator;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CapitalGainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital_gain);


        // Get the widgets reference from XML layout

        final WebView wv = (WebView) findViewById(R.id.webView);

        // Set a click listener for Button widget

                /*
                    setWebViewClient(WebViewClient client)
                        Sets the WebViewClient that will receive
                        various notifications and requests.
                 */
                wv.setWebViewClient(new WebViewClient());

                /*
                    WebSettings
                        Manages settings state for a WebView. When a
                        WebView is first created, it obtains a set
                        of default settings.

                    setJavaScriptEnabled(boolean flag)
                        Tells the WebView to enable JavaScript execution.
                 */
                wv.getSettings().setJavaScriptEnabled(true);

                // Get the Android assets folder path
                String folderPath = "file:android_asset/";

                // Get the HTML file name
                String fileName = "calculator.html";

                // Get the exact file location
                String file = folderPath + fileName;

                /*
                    loadUrl(String url)
                        Loads the given URL.
                 */

                // Render the HTML file on WebView
                wv.loadUrl(file);

    }
}