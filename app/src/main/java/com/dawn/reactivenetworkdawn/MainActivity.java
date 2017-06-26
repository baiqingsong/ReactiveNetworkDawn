package com.dawn.reactivenetworkdawn;

import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = (TextView) findViewById(R.id.tv_content);
        checkNetwork();
    }
    public void checkNetwork(){
        ReactiveNetwork.observeNetworkConnectivity(this)
                .subscribeOn(Schedulers.io())
                // anything else what you can do with RxJava
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Connectivity>() {
                    @Override public void accept(final Connectivity connectivity) {
                        // do something with connectivity
                        // you can call connectivity.getState();
                        // connectivity.getType(); or connectivity.toString();
                        NetworkInfo.State state = connectivity.getState();
                        String name = connectivity.getTypeName();
                        tvContent.setText(String.format("state: %s, typeName: %s", state, name));
                    }
                });

    }
}
