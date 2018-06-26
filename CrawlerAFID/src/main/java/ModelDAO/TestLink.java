package ModelDAO;

import Model.Link;
import MongoDB.MyConstants;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class TestLink {
    public static void main(String[] args) throws UnknownHostException {
        Link link = new Link();
        link.setFlag(true);
        link.setLinkText("http.google.com");
        link.setNumberFailedRequest(0);
        link.setNumberRequest(0);

        Link link1 = new Link();
        link1.setFlag(true);
        link1.setLinkText("http.google.com");
        link1.setNumberFailedRequest(0);
        link1.setNumberRequest(0);
        List<Link> linkList = new ArrayList<Link>();
        linkList.add(link);
        linkList.add(link1);
        LinkDAO linkDAO = new LinkDAO();
        linkDAO.InsertListLink(linkList);
        //linkDAO.DeleteLink(link);



    }


}
