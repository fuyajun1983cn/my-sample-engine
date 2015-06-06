package fyj.engine.core;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class StaticTextTexture extends Texture {
	
	private String mText;

	public StaticTextTexture(TextureEngine textureEngine, String text) {
		super(textureEngine);
		// TODO Auto-generated constructor stub
		mText = text;
	}

	@Override
	public Bitmap create() {
		// TODO Auto-generated method stub
		Bitmap paintBitmap = Bitmap.createBitmap(200, 40, Config.ARGB_8888);
		Paint paint = new Paint();
		paint.setTypeface(Typeface.MONOSPACE);
		paint.setTextSize(10.0f);
		paint.setColor(Color.BLUE);
		paint.setAntiAlias(true);
		Canvas canvas = new Canvas(paintBitmap);

		canvas.drawText(mText, 0, mText.length(), 10, 10, paint);
		Bitmap mBitmap=Bitmap.createBitmap(paintBitmap, 0, 0, paintBitmap.getWidth(), paintBitmap.getHeight());
		paintBitmap.recycle();			
		
		return mBitmap;
	}
	
	public String getText() {
		return mText;
	}

}
