package github.shardul.cats.network;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Shardul on 29/03/18.
 */

@Root(name="Response", strict = false)
public class CatsResponse {
    @Element
    CatsData data;
}

@Root(name = "data")
class CatsData {
    @Element
    Images images;
}

@Root(name = "images")
class Images {
    @ElementList(inline = true)
    List<Image> imageItems;

}

@Root(name = "image")
class Image {
    @Element(name = "url")
    String url;
    @Element(name = "id")
    String id;
    @Element(name = "source_url")
    String sourceUrl;
}
