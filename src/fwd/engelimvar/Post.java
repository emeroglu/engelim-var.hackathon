package fwd.engelimvar;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Post extends Activity implements OnClickListener {
	private static String path;
	private static Uri uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);

		Initilize();

		if (getIntent() != null) {
			Intent i = getIntent();
			path = i.getExtras().getString("path");
			uri = (Uri) i.getExtras().get("uri");
			tvTakenPath.setText(path);
		}

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnUpload) {
			RequestParams rp = new RequestParams();
			try {
				rp.put("file", new File(path));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "File Failed",
						Toast.LENGTH_LONG).show();
			}

			AsyncHttpResponseHandler ahrh = new AsyncHttpResponseHandler() {

				Context context = getApplicationContext();

				@Override
				public void onStart() {
					Toast.makeText(context, "Upload Started", Toast.LENGTH_LONG)
							.show();
				}

				@Override
				public void onFinish() {
					Toast.makeText(context, "Upload Finished",
							Toast.LENGTH_LONG).show();
				}

				@Override
				public void onSuccess(String content) {
					// TODO Auto-generated method stub
					super.onSuccess(content);

					Toast.makeText(context, "Upload Succeded",
							Toast.LENGTH_LONG).show();
				}

				@Override
				@Deprecated
				public void onFailure(Throwable error) {
					// TODO Auto-generated method stub
					super.onFailure(error);

					Toast.makeText(context, error.getMessage(),
							Toast.LENGTH_LONG).show();
				}

			};

			AsyncHttpClient client = new AsyncHttpClient();
			client.post(getApplicationContext(),
					"http://emeroglu.com/Upload.aspx", rp, ahrh);

		}
	}

	private void Initilize() {

		ivCamera = (ImageView) findViewById(R.id.ivCamera);
		ivCamera.setOnClickListener(this);

		ivPost = (ImageView) findViewById(R.id.ivPost);
		ivPost.setOnClickListener(this);

		ivTaken = (ImageView) findViewById(R.id.ivTaken);

		tvTakenPath = (TextView) findViewById(R.id.tvTakenPath);

		btnUpload = (Button) findViewById(R.id.btnUpload);
		btnUpload.setOnClickListener(this);
	}

	ImageView ivNews, ivCamera, ivPost, ivTaken;
	Intent intent;
	TextView tvTakenPath;
	Button btnUpload;

}
