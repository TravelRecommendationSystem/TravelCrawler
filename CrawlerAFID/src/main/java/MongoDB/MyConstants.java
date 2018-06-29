package MongoDB;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class MyConstants {
	public static final String DB_NAME = "Place";
	public static final String LINK_COLLECTION_NAME = "Link";
	public static final String PLACE_COLLECTION_NAME = "Place";

	// field in link collection
	public static final String URL_FIELD = "URL";
	public static final String FLAG_FIELD = "Flag";
	public static final String NUMBER_FAIL_REQUEST_FIELD = "NFRequest";// NUMBER FAILD REQUEST
	public static final String NUMBER_REQUEST_FIELD = "NRequest";// NUMBER REQUEST

	// field in Place Collection
	public static final String PLACE_NAME_FIELD = "PlaceName";
	public static final String PLACE_DESCRIPTION_FIELD = "PlaceDescription";
	public static final String PLACE_IMAGE_FIELD = "PlaceImages";
	public static final String PLACE_RATING_FIELD = "PlaceRating";
	public static final String PLACE_ADDRESS_FIELD = "PlaceAddress";
	public static final String PLACE_OPENTIME_FIELD = "PlaceOpenTime";
	public static final String PLACE_TYPE_FIELD = "PlaceType";
	public static final String PLACE_PRICE_FIELD = "PlacePrice";
	public static final String PLACE_COMMENTS_FIELD = "PlaceComments";
	public static final String PLACE_lINKTEXT_FIELD = "PlaceLinkText";
	public static final String PLACE_ID_FIELD = "_id";
	// field in comment
	public static final String COMMENT_USER_FIELD = "CommentUser";
	public static final String COMMENT_CREATEDATE_FIELD = "CommentCreateDate";
	public static final String COMMENT_CONTENT_FIELD = "CommentContent";

}
