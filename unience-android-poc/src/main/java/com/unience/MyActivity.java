package com.unience;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MyActivity extends Activity {

  private static final String URL_PRODUCTION = "www.unience.com";
  private static final String URL_STAGE = "stage.uniience.com";
  private static final String URL_DEV = "dev.uniience.com";

  private WebView webView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my);

    webView = (WebView) findViewById(R.id.webview);
    webView.setWebViewClient(new MyWebViewClient());
    webView.getSettings().setJavaScriptEnabled(true);
    webView.loadUrl("https://" + URL_PRODUCTION);
  }


  private class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      if (isTargetUrlFromUnience(url)) {
        // This is my web site, so do not override; let my WebView load the page
        return false;
      }
      // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
      startActivity(intent);
      return true;
    }
  }

  private boolean isTargetUrlFromUnience(String url) {

    String targetHost = Uri.parse(url).getHost();
    return URL_PRODUCTION.equalsIgnoreCase(targetHost) ||
           URL_STAGE.equalsIgnoreCase(targetHost) ||
           URL_DEV.equalsIgnoreCase(targetHost);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_my, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
