package cl.webdevel.puzzle15;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

public class FragmentGame extends Fragment {

	private static final String TAG = "Puzzle15";

	public static Fragment newInstance(Context context) {
		FragmentGame f = new FragmentGame();

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_game, container, false);

		final LinearLayout board = (LinearLayout) root.findViewById(R.id.board);
		
		for (int i = 0; i < 4; i++){
			LinearLayout l = new LinearLayout(getActivity());
			l.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,0,1f));
			l.setBackgroundColor(Color.BLACK);
			l.setOrientation(LinearLayout.HORIZONTAL);
			board.addView(l);
			Log.d(TAG, "LinearLayout Seteado!");
			for(int j = 0; j < 4; j++){
				TextView tv = new TextView(getActivity());
				tv.setLayoutParams(new LayoutParams(0,LayoutParams.MATCH_PARENT,1f));
				tv.setBackgroundColor(Color.GREEN);
				tv.setGravity(Gravity.CENTER);
				tv.setText((i+1)+""+(j+1));
				tv.setOnTouchListener(new View.OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						
						if(event.getAction() == MotionEvent.ACTION_MOVE)
						{
							Log.d(TAG,"Tocaste " + v.toString());
							
							
						}
						
						return true;
					}
				});
				l.addView(tv);
				Log.d(TAG, "TextView Seteado!");
			}
		}

//		LinearLayout row;
//		for (int i = 0; i < board.getChildCount(); i++) {
//			row = (LinearLayout) board.getChildAt(i);
//			for (int j = 0; j < row.getChildCount(); j++) {
//				View v;
//				v = row.getChildAt(j);
//				if (v instanceof TextView) {
//
//					Log.d(TAG, "Listener Seteado!");
//
//					v.setOnDragListener(new View.OnDragListener() {
//
//						@Override
//						public boolean onDrag(View v, DragEvent event) {
//
//							switch (event.getAction()) {
//							case DragEvent.ACTION_DRAG_STARTED:
//								Toast.makeText(getActivity(), "Drag Started",
//										Toast.LENGTH_SHORT).show();
//								break;
//							case DragEvent.ACTION_DRAG_ENTERED:
//								Toast.makeText(getActivity(), "Drag Entered",
//										Toast.LENGTH_SHORT).show();
//								break;
//							case DragEvent.ACTION_DRAG_EXITED:
//								Toast.makeText(getActivity(), "Drag Exited",
//										Toast.LENGTH_SHORT).show();
//								break;
//							case DragEvent.ACTION_DROP:
//								Toast.makeText(getActivity(), "Drop",
//										Toast.LENGTH_SHORT).show();
//								break;
//							case DragEvent.ACTION_DRAG_ENDED:
//								Toast.makeText(getActivity(), "Drag Ended",
//										Toast.LENGTH_SHORT).show();
//								break;
//							}
//							return true;
//						}
//					});
//				}
//			}
//		}

		return root;
	}

}