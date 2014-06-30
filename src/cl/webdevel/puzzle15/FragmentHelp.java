package cl.webdevel.puzzle15;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class FragmentHelp extends Fragment {
 
    public static Fragment newInstance(Context context) {
    	FragmentHelp f = new FragmentHelp();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_help, container, false);
        return root;
    }
 
}