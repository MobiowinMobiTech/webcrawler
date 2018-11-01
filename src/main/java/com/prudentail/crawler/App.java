package com.prudentail.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.prudentail.crawler.bean.Domain;
import com.prudentail.crawler.bean.ImageInfo;
import com.prudentail.crawler.bean.SubDomain;
import com.prudentail.crawler.commons.ApplicationConstants;

/**
 * @author raman.gupta
 * @version
 * @date 11/1/18
 * @discription Web crawler
 */
@SuppressWarnings("restriction")
public class App {

	final static Logger logger = Logger.getLogger(App.class);

	public App() {

	}

	/*
	 * Main method to exetude the program.
	 * For example i took here "https://www.prudential.co.uk/" as an example
	 * you can pass any website name that you want to crawl
	 * */
	
	public static void main(String[] args) {
		new App().getPageLinks("https://www.prudential.co.uk/");
	}

	public void getPageLinks(String URL) {

		Domain domain = new Domain();
		domain.setCustomerList(new ArrayList<SubDomain>());
		SubDomain subDomain = null;
		ImageInfo imageInfo = null;
		List<ImageInfo> imageInfoList = null;
		try {

			/*
			 * JSOUP lib to connec to geiven website and it's arrtibute
			 * like Images,documents (pdf/words/etc)
			 * */
			Document document = Jsoup.connect(URL).timeout(10 * 1000).ignoreContentType(true).get();
			Elements links = document.select("a[href]");
			logger.info("Total count of links are : " + links.size());
			for (Element link : links) {
				subDomain = new SubDomain();
				subDomain.setName(link.attr("abs:href"));

				if (null != link.attr("abs:href") && !link.attr("abs:href").equals(" ")
						&& link.attr("href").contains("prudential")
						&& link.attr("abs:href").matches("[http].+[^(pdf|rar|zip)]")) {

					imageInfoList = new ArrayList<ImageInfo>();
					String innerUrl = link.attr("abs:href");
					logger.info(innerUrl);
					Document innerDoc = Jsoup.connect(innerUrl).timeout(10 * 1000).ignoreContentType(true).get();
					Elements imgLinks = innerDoc.select("img[src]");
					logger.info("Total count of img links in link : " + imgLinks.size());
					for (Element innerLink : imgLinks) {
						imageInfo = new ImageInfo();
						imageInfo.setImageUrl(innerLink.attr(ApplicationConstants.APP_KEY_CONSTANTS.IMG_SRC));
						imageInfo.setHeight(innerLink.attr(ApplicationConstants.APP_KEY_CONSTANTS.IMG_HEIGHT));
						imageInfo.setWidth(innerLink.attr(ApplicationConstants.APP_KEY_CONSTANTS.IMG_WIDTH));
						imageInfoList.add(imageInfo);

					}
					subDomain.setImageInfo(imageInfoList);

				}

				domain.getCustomerList().add(subDomain);
			}

			JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(domain, System.out);

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IOException while crawling link : " + e.getMessage(), e.getCause());
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("JAXBException while crawling link : " + e.getMessage(), e.getCause());
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception while crawling link : " + ex.getMessage(), ex.getCause());

		}

	}
}
