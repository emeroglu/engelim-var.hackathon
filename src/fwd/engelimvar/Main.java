package fwd.engelimvar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Main extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Initilize();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.ivCamera)
			startActivity(new Intent("fwd.engelimvar.CAMERA"));
	}

	private void Initilize() {
		ivCamera = (ImageView) findViewById(R.id.ivCamera);
		ivCamera.setOnClickListener(this);

		ivPost = (ImageView) findViewById(R.id.ivPost);
		ivPost.setOnClickListener(this);
	}

	ImageView ivNews, ivCamera, ivPost;
	Intent intent;
}
