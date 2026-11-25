package com.fathr.warkopgenz;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.*;
import android.view.*;
import android.widget.*;
import android.graphics.Color;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends Activity {
    private WebView web;
    private FrameLayout root;
    private static final String GAME = "https://warkopsimulator.pages.dev/";
    private void open(String url) {
        try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); }
        catch (Exception ignored) { Toast.makeText(this, "Tidak dapat membuka tautan", Toast.LENGTH_SHORT).show(); }
    }
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.rgb(7,7,14));
        getWindow().setNavigationBarColor(Color.rgb(7,7,14));
        root = new FrameLayout(this);
        web = new WebView(this);
        web.setBackgroundColor(Color.rgb(7,7,14));
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setLoadsImagesAutomatically(true);
        s.setSupportZoom(false);
        s.setMediaPlaybackRequiresUserGesture(false);
        CookieManager.getInstance().setAcceptCookie(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(web, true);
        web.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r) {
                String u = r.getUrl().toString();
                if (u.startsWith("https://warkopsimulator.pages.dev/") || u.startsWith("https://")) return false;
                return true;
            }
        });
        web.setWebChromeClient(new WebChromeClient());
        root.addView(web, new FrameLayout.LayoutParams(-1,-1));
        Button menu = new Button(this);
        menu.setText("☰");
        menu.setTextSize(18);
        menu.setTextColor(Color.WHITE);
        menu.setBackgroundColor(Color.rgb(92,45,145));
        FrameLayout.LayoutParams mp = new FrameLayout.LayoutParams(dp(52),dp(48),Gravity.RIGHT|Gravity.TOP);
        mp.setMargins(0,dp(8),dp(8),0);
        root.addView(menu,mp);
        menu.setOnClickListener(v -> showMenu());
        setContentView(root);
        if (state == null) web.loadUrl(GAME); else web.restoreState(state);
    }
    private int dp(int n) { return (int)(n*getResources().getDisplayMetrics().density+0.5f); }
    private void showMenu() {
        LinearLayout panel = new LinearLayout(this);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(dp(20),dp(18),dp(20),dp(18));
        panel.setBackgroundColor(Color.rgb(20,17,31));
        TextView title = new TextView(this);
        title.setText("WARKOP GEN Z  •  Pengaturan");
        title.setTextColor(Color.WHITE); title.setTextSize(18);
        panel.addView(title);
        TextView credit = new TextView(this);
        credit.setText("Dibuat oleh @fathr");
        credit.setTextColor(Color.LTGRAY); credit.setPadding(0,dp(12),0,dp(12));
        panel.addView(credit);
        addAction(panel,"☕ Traktir Kopi Developer",()->open("https://sociabuzz.com/fiwvyp2"));
        addAction(panel,"🐞 Lapor Bug / Saran via Instagram",()->open("https://www.instagram.com/fathr/"));
        addAction(panel,"Kembali ke Game",()->{});
        AlertDialogHolder.show(this,panel);
    }
    private void addAction(LinearLayout p,String label,Runnable action) {
        Button b = new Button(this); b.setText(label); b.setAllCaps(false);
        p.addView(b,new LinearLayout.LayoutParams(-1,dp(52)));
        b.setOnClickListener(v->action.run());
    }
    @Override public void onBackPressed() {
        if (web != null && web.canGoBack()) web.goBack(); else super.onBackPressed();
    }
    @Override protected void onSaveInstanceState(Bundle out) {
        if (web != null) web.saveState(out);
        super.onSaveInstanceState(out);
    }
    static class AlertDialogHolder {
        static void show(Activity a, View v) {
            new android.app.AlertDialog.Builder(a).setView(v).setNegativeButton("Tutup", (d,w)->d.dismiss()).show();
        }
    }
}
