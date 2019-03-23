package fwd.engelimvar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Camera extends Activity implements OnClickListener {

	private String path;
	public static Uri uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);

		Initilize();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnTakePicture)
			startActivityForResult(new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE),
					REQUEST_CODE);
		else if (v.getId() == R.id.btnFillAndSend) {
			Intent intent = new Intent("fwd.engelimvar.POST");
			intent.putExtra("path", path);
			intent.putExtra("uri", uri);
			startActivity(intent);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			ivPicture.setImageURI(data.getData());

			uri=data.getData();
			path = getRealPathFromURI(data.getData());

			Toast t = Toast.makeText(getApplicationContext(),
					"Image saved to: " + path, Toast.LENGTH_LONG);
			t.show();

			btnFillAndSend.setEnabled(true);
			btnTakePicture.setEnabled(false);
		}

	}

	private String getRealPathFromURI(Uri contentUri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	private void Initilize() {		

		ivCamera = (ImageView) findViewById(R.id.ivCamera);
		ivCamera.setOnClickListener(this);

		ivPost = (ImageView) findViewById(R.id.ivPost);
		ivPost.setOnClickListener(this);

		ivPicture = (ImageView) findViewById(R.id.ivPicture);

		btnTakePicture = (Button) findViewById(R.id.btnTakePicture);
		btnTakePicture.setOnClickListener(this);

		btnFillAndSend = (Button) findViewById(R.id.btnFillAndSend);
		btnFillAndSend.setOnClickListener(this);
	}

	ImageView ivNews, ivCamera, ivPost, ivPicture;
	Button btnTakePicture, btnFillAndSend;
	Intent intent;

	private int REQUEST_CODE = 0;
}
