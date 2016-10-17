package com.code.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.videumcorp.android.tabmain.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

import java.io.InputStream;
import java.lang.ref.WeakReference;

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
	private final WeakReference<ImageView> imageViewReference;
	private static ImageView imageView;
	private String url;

	public ImageDownloaderTask(ImageView imageView) {
		imageViewReference = new WeakReference<ImageView>(imageView);
		this.imageView = imageView;
	}

	@Override
	// Actual download method, run in the task thread
	protected Bitmap doInBackground(String... params) {
		// params comes from the execute() call: params[0] is the url.
		url = params[0];
//		Bitmap t = downloadBitmap(params[0]);
//		if(t!=null) return t;
		return downloadBitmap(params[0]);
	}

	@Override
	// Once the image is downloaded, associates it to the imageView
	protected void onPostExecute(Bitmap bitmap) {
		if (isCancelled()) {
			bitmap = null;
		}
		if(bitmap==null){
			imageView.setImageResource(R.drawable.logo);
			return;
		}

		if (imageViewReference != null) {
			ImageView imageView = imageViewReference.get();
			if (imageView != null) {

				if (bitmap != null) {

					imageView.setImageBitmap(bitmap);
				} else {
					
						imageView.setImageDrawable(imageView.getContext()
								.getResources()
								.getDrawable(R.drawable.logo));
				}
			}

		} else
			return;
	}

	static Bitmap downloadBitmap(String url) {
		// url =
		// "http://images.tienphong.vn/uploaded/phamlinh/2015_08_07/khai_thac_thiec_kacz.jpg?width=80";

		if (URLUtil.isValidUrl(url)) {
			Bitmap bitmap = null;
			Log.d("Img Link", url);
			final AndroidHttpClient client = AndroidHttpClient
					.newInstance("Android");
			try {
				final HttpGet getRequest = new HttpGet(url);
				HttpResponse response = client.execute(getRequest);
				final int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != HttpStatus.SC_OK) {
					Log.w("ImageDownloader", "Error " + statusCode
							+ " while retrieving bitmap from " + url);
					return null;
				}

				final HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream inputStream = null;
					try {
						inputStream = entity.getContent();
						bitmap = BitmapFactory.decodeStream(inputStream);
						return bitmap;
					} finally {
						if (inputStream != null) {
							inputStream.close();
						}
						entity.consumeContent();
					}
				}
			} catch (Exception e) {
				// Could provide a more explicit error message for IOException
				// or
				// IllegalStateException
				// getRequest.abort();
				Log.w("ImageDownloader", "Error while retrieving bitmap from "
						+ url);
				//imageView.setImageResource(R.drawable.list_placeholder);
			} finally {
				if (client != null) {
					client.close();
				}
			}
			return null;

		}
		return null;
	}

}