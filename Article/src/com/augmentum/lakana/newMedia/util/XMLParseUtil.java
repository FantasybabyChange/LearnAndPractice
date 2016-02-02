package com.augmentum.lakana.newMedia.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.augmentum.lakana.newMedia.model.ArticleItem;
import com.augmentum.lakana.newMedia.model.Categories;
import com.augmentum.lakana.newMedia.model.Category;
import com.augmentum.lakana.newMedia.model.Video;
import com.augmentum.lakana.newMedia.model.VideoPoster;
import com.augmentum.lakana.newMedia.model.VideoSource;

public class XMLParseUtil {

    private static InputStream is = null;
    private static Logger logger = Logger.getLogger(XMLParseUtil.class);
    public static void main(String[] args) throws Exception {
            parseXML();
    }

    private static void getXMLDoc() {

        URL url;
        try {
            url = new URL("http://ent-uat-nme.endplay.com/feeds/rssFeed?obfType=ARTICLE_DETAIL&siteId=1000909&articleId=15540303");
            URLConnection urlConnection = url.openConnection();
            is = urlConnection.getInputStream();
        } catch (Exception e) {
            logger.error("Trying to get network error, Please check your network connection and try again");
            return;
        }
    }

    public static Object parseXML() {
        try {
            getXMLDoc();
            long startTime = System.currentTimeMillis();
            System.out.println("开始解析：" + startTime + " :" + " " + is);
            ArticleItem articleItem = processXML();
            FileOutputStream fos = new FileOutputStream(PropertyUtil.getProperty("filePath") + articleItem.getGuid() +".json");
            JSONObject jsonObject = JSONObject.fromObject(articleItem);
            fos.write(jsonObject.toString().getBytes("UTF-8"));
            fos.flush();
            fos.close();
            long endTime = System.currentTimeMillis();
            System.out.println("结束解析：" + endTime);
            System.out.println("共用时：" + (endTime - startTime)/1000);
            System.out.print("*********************\n");
        }catch(Exception e) {
            logger.error(e);
        }  finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


    private static ArticleItem processXML() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(is);
        Element element = document.getDocumentElement();
        NodeList itemList = element.getElementsByTagName("item");

        for(int i=0; i<=itemList.getLength(); i++) {
            Element itemElement = (Element) itemList.item(i);

            NodeList headlineNode =  itemElement.getElementsByTagName("headline");
            String headline = headlineNode.item(0).getTextContent();

            NodeList subHeadlineNode =  itemElement.getElementsByTagName("sub-headline");
            String subHeadline = subHeadlineNode.item(0).getTextContent();

            NodeList shortHeadlineNode =  itemElement.getElementsByTagName("short-headline");
            String shortHeadline = shortHeadlineNode.item(0).getTextContent();

            NodeList linkNode =  itemElement.getElementsByTagName("link");
            String link = linkNode.item(0).getTextContent();

            NodeList bodyNode =  itemElement.getElementsByTagName("body");
            String body = bodyNode.item(0).getTextContent();

            NodeList guidNode =  itemElement.getElementsByTagName("guid");
            String guid = guidNode.item(0).getTextContent();

            NodeList keywordsNode =  itemElement.getElementsByTagName("keywords");
            String keywords = keywordsNode.item(0).getTextContent();

            NodeList pubDateNode =  itemElement.getElementsByTagName("pubDate");
            String pubDate = pubDateNode.item(0).getTextContent();

            NodeList lastEditDateNode =  itemElement.getElementsByTagName("lastEditDate");
            String lastEditDate = lastEditDateNode.item(0).getTextContent();

            NodeList copyrightNode =  itemElement.getElementsByTagName("copyright");
            String copyright = copyrightNode.item(0).getTextContent();

            /*NodeList issueNode =  itemElement.getElementsByTagName("issue");
            String issue = headlineNode.item(0).getTextContent();*/

            ArticleItem articleItem = new ArticleItem();
            articleItem.setHeadline(headline);
            articleItem.setSubHeadline(subHeadline);
            articleItem.setShortHeadline(shortHeadline);
            articleItem.setLink(link);
            articleItem.setBody(body);
            articleItem.setGuid(Integer.parseInt(guid));
            articleItem.setKeywords(keywords);
            articleItem.setPubDate(pubDate);
            articleItem.setLastEditDate(lastEditDate);
            articleItem.setCopyright(copyright);

            Video video = new Video();
            NodeList videoNode = itemElement.getElementsByTagName("video");
            processVideo(videoNode, video);
            articleItem.setVideo(video);

            Categories categories = new Categories();
            NodeList categoriesNode = itemElement.getElementsByTagName("categories");
            processCategories(categoriesNode, categories);
            articleItem.setCategories(categories);

            return articleItem;
        }
        return null;
    }

    private static void processCategories(NodeList categoriesNode, Categories categories) {
    	if (categoriesNode.getLength() == 0) {
    		return;
    	}
		List<Category> categoryList = new ArrayList<Category>();
		Category category = new Category();
		Element categoriesElement = (Element) categoriesNode.item(0);
		try {
			NodeList categoryNode = categoriesElement.getElementsByTagName("category");
			for (int j = 0; j < categoryNode.getLength(); j++) {
				Element categoryElement = (Element)categoriesNode.item(j);
				category.setName(categoryElement.getAttribute("name"));
				category.setPrimary(Integer.parseInt(categoryElement.getAttribute("primary")));
				category.setId(Integer.parseInt(categoryElement.getAttribute("id")));
				categoryList.add(category);
			};
		} catch (NullPointerException e) {
			logger.error("Categories tag parse exception");
		} catch (java.lang.NumberFormatException e) {
			logger.error("Categories tag parseInt exception");
		}
		categories.setCategories(categoryList);
    }

    private static void processVideo(NodeList videoNode, Video video) {
        if (videoNode.getLength() == 0) {
            return;
        }
        for(int j=0; j<videoNode.getLength(); j++) {
            Element videoElement = (Element) videoNode.item(j);
            try {
                video.setId(videoElement.getAttribute("id"));
                video.setType(videoElement.getAttribute("type"));
                video.setTitle(videoElement.getAttribute("title"));

                NodeList posterNode = videoElement.getElementsByTagName("poster");
                List<VideoPoster> videoPosters = new ArrayList<VideoPoster>();
                for (int k = 0; k < posterNode.getLength(); k++) {
                	VideoPoster videoPoster = new VideoPoster();
                	Element posterElement = (Element) posterNode.item(k);
                	videoPoster.setUrl(posterElement.getAttribute("url"));
                	videoPoster.setHeight(Integer.parseInt(posterElement.getAttribute("height")));
                	videoPoster.setWidth(Integer.parseInt(posterElement.getAttribute("width")));
                	videoPoster.setWatermark(Integer.parseInt(posterElement.getAttribute("watermark")));
                	videoPosters.add(videoPoster);
				}
                video.setVideoPosters(videoPosters);
                NodeList sourceNode = videoElement.getElementsByTagName("source");
                List<VideoSource> videoSources = new ArrayList<VideoSource>();
                for (int k = 0; k < sourceNode.getLength(); k++) {
                	VideoSource videoSource = new VideoSource();
                	Element sourceElement = (Element) sourceNode.item(k);
                	videoSource.setQuality(sourceElement.getAttribute("quality"));
                	videoSource.setFileSize(Integer.parseInt(sourceElement.getAttribute("fileSize")));
                	videoSource.setHeight(Integer.parseInt(sourceElement.getAttribute("height")));
                	videoSource.setWidth(Integer.parseInt(sourceElement.getAttribute("width")));
                	videoSource.setUrl(sourceElement.getAttribute("url"));
                	videoSources.add(videoSource);
				}
                video.setVideoSources(videoSources);
            } catch (NullPointerException e) {
                logger.error("video tag parse exception");
            }
        }
    }

}
