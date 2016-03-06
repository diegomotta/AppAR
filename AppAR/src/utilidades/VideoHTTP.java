package utilidades;

import java.util.HashMap;

public class VideoHTTP  {
	private HashMap<String, Object> videosObt;
	public VideoHTTP (){
		setVideosObt(new HashMap<String, Object>());
	}
	public HashMap<String, Object> getVideosObt() {
		return videosObt;
	}

	public void setVideosObt(HashMap<String, Object> videosObt) {
		this.videosObt = videosObt;
	}
		
}