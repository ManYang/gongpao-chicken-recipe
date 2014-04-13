package recipe.alpha.google;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.google.api.services.mirror.Mirror;
import com.google.api.services.mirror.Mirror.Timeline;
import com.google.api.services.mirror.model.TimelineItem;

public class KongbaoChicken {
	public static void insertSimpleTextTimelineItem( HttpServletRequest req )
			throws IOException
			{
			Mirror mirror = MirrorUtils.getMirror( req );
			Timeline timeline = mirror.timeline();
			TimelineItem timelineItem = new TimelineItem()
			.setText( getRandomCuisine() );
			timeline.insert( timelineItem ).executeAndDownloadTo( System.out );
			}

	public static String getRandomCuisine()
	{
	String[] lunchOptions = {
	"American", "Chinese", "French", "Italian", "Japenese", "Thai"
	};
	int choice = new Random().nextInt(lunchOptions.length);
	return lunchOptions[choice];
	}
	
	TimelineItem timelineItem = new TimelineItem()
	.setTitle( "Kongbao Chicken" )
	.setText( getRandomCuisine() );
	TimelineItem tiResp = timeline.insert( timelineItem ).execute();
	setLunchRouletteId( userId, tiResp.getId() );
	
	
	
}
