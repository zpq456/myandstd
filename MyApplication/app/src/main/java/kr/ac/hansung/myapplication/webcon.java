package kr.ac.hansung.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Owner on 2016-02-22.
 */

//테스트테스트
public class webcon extends Activity {
    TextView textView01;
    ProgressBar progress;
    BackgroundTask task;
    int value;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webcon);

        textView01 = (TextView) findViewById(R.id.textView01);
        progress = (ProgressBar) findViewById(R.id.progress_bar);

        // 실행 버튼 이벤트 처리
        Button executeBtn = (Button) findViewById(R.id.stbt);
        executeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 새로운 Task 객체를 만들고 실행
                task = new BackgroundTask();
                task.execute(100);
            }
        });

        // 취소 버튼 이벤트 처리
        Button cancelBtn = (Button) findViewById(R.id.edbt);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                task.cancel(true);
            }
        });
    }


    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        protected void onPreExecute() {
            value = 0;
            progress.setProgress(value);
        }

        protected Integer doInBackground(Integer... values) {
            while (isCancelled() == false) {
                value++;
                if (value >= 100) {
                    break;
                } else {
                    publishProgress(value);
                }

                //Todo: http request

                /*   example.
                     이런 형식으로 할것 참고로 밑에 코드는 try-catch를 막붙인것 이므로 정리해서 쓸것


                 URL url = null;

                    url = new URL("www.naver.com");

                HttpURLConnection urlConnection = null;

                    urlConnection = (HttpURLConnection) url.openConnection();


                    urlConnection.setRequestMethod("GET");

                urlConnection.setRequestProperty("Accept", "application/json");

                    urlConnection.connect();

                InputStream is = null;

                    is = urlConnection.getInputStream();

                if (is == null) {
                    return;
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuffer buffer = new StringBuffer();

                    while ((line = reader.readLine()) != null) {
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line);
                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                        }
                    }

                 */
            }
            return value;
        }

        protected void onProgressUpdate(Integer... values) {
            progress.setProgress(values[0].intValue());
            textView01.setText("Current Value : " + values[0].toString());
        }

        protected void onPostExecute(Integer result) {
            progress.setProgress(0);
            textView01.setText("Finished.");
        }

        protected void onCancelled() {
            progress.setProgress(0);
            textView01.setText("Cancelled.");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

