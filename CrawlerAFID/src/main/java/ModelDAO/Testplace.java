package ModelDAO;

import Model.Comment;
import Model.Place;
import com.sun.media.jfxmedia.events.PlayerEvent;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Testplace {
    public static void main(String[] args) throws UnknownHostException {
        List<Comment> listcmt = new ArrayList<Comment>();
        Comment cmt = new Comment();
        cmt.setUserName("Nguyen A");
        cmt.setCommentDesciption("DEp qua");
        cmt.setCreatedDate("22-2-2018");
        listcmt.add(cmt);
        Comment cmt2 = new Comment();
        cmt2.setUserName("Nguyen B");
        cmt2.setCommentDesciption("Xau qua");
        cmt2.setCreatedDate("22-2-2019");
        listcmt.add(cmt2);
        String img = "hinh anh 1";

        List<String> lisimg = new ArrayList<String>();
        lisimg.add(img);
        Place place = new Place();
        place.setListComment(listcmt);
        place.setId("123");
        place.setPlaceAddress("nguyen thi xuan");
        place.setPlaceDesciption("cong vien văn hoa");
        place.setPlaceImages(lisimg);
        place.setPlaceName("suoi tien");
        place.setPlaceOpenTime("9h");
        place.setPlacePrice("1000k");
        place.setPlaceRating("5*");
        place.setPlaceType("cong vien du lich");
        place.setLinkText("http.google");
        PlaceDAO placeDAO = new PlaceDAO();

        List<Place> placeList = new ArrayList<Place>();
        placeList.add(place);
        Place place1 = new Place();
        place1.setListComment(listcmt);
        place1.setId("12345");
        place1.setPlaceAddress("nguyen thi xuan1");
        place1.setPlaceDesciption("cong vien văn hoa");
        place1.setPlaceImages(lisimg);
        place1.setPlaceName("suoi tien");
        place1.setPlaceOpenTime("9h");
        place1.setPlacePrice("1000k");
        place1.setPlaceRating("5*");
        place1.setPlaceType("cong vien du lich");
        place1.setLinkText("http.google");

        placeList.add(place1);

        placeDAO.InserListPlace(placeList);

    }


}
