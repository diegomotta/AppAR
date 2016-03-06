package video;
import java.util.HashMap;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.appar.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTubeFragment extends YouTubeBaseActivity implements OnInitializedListener{
	
	//private static final String APY_KEY = "AIzaSyBRO9obHawefmpSaUiF9pHBOiGBAyV5d64";
	//private String ID_VIDEO = "eguctGjUNLI";
	private YouTubePlayerView youtube;
	private String urlFactory;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.videoproducto);
	    youtube  =(YouTubePlayerView) findViewById (R.id.youtube_view);
	    youtube.initialize(DeveloperKey.DEVELOPER_KEY, this);
		HashMap<String, Object> objetoUrlFactory = (HashMap<String, Object>) getIntent().getExtras().get("objetoUrlFactory");
		urlFactory =(String) objetoUrlFactory.get("urlFactory");
		System.out.println(urlFactory);
	}
	
	@Override
	public void onInitializationFailure(Provider proviver,YouTubeInitializationResult error) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "OnInitializationFailure()", Toast.LENGTH_SHORT).show();
	}
		
	@Override
	public void onInitializationSuccess(Provider proviver, YouTubePlayer player,boolean loadAgain) {
		// TODO Auto-generated method stub
		Log.i("SCRIP","Raiz 1");
		if(!loadAgain){
			Log.i("SCRIP","Raiz 2");
			player.cueVideo(urlFactory);
		}
	}
}



