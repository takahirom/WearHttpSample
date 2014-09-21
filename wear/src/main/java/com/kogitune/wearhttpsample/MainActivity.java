package com.kogitune.wearhttpsample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.kogitune.wearhttp.WearGetImageContents;
import com.kogitune.wearhttp.WearGetTextContents;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                mImageView = (ImageView) stub.findViewById(R.id.image);

                mTextView.setText("start");
                new WearGetTextContents(MainActivity.this).getContents("http://headers.jsontest.com/", new WearGetTextContents.WearGetContentsCallBack() {
                    @Override
                    public void onGetContents(String contents) {
                        mTextView.setText(contents);
                    }

                    @Override
                    public void onFail(final Exception e) {
                        mTextView.setText(e.getMessage());
                    }
                }, 10);
                new WearGetImageContents(MainActivity.this).getContents("https://cloud.githubusercontent.com/assets/1386930/4347967/65f420c4-4176-11e4-8cb6-d70f1867f8cb.png",
                        new WearGetImageContents.WearGetContentsCallBack() {
                            @Override
                            public void onGetContents(Bitmap image) {
                                mImageView.setImageBitmap(image);
                            }

                            @Override
                            public void onFail(final Exception e) {
                                mTextView.setText(e.getMessage());
                            }
                        }, 10
                );


            }
        });
    }
}
